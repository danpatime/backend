package com.example.api.suggest.service;

import com.example.api.chat.repository.ChatRoomRepository;
import com.example.api.contracts.ContractMapper;
import com.example.api.contracts.ContractRepository;
import com.example.api.contracts.OfferRepository;
import com.example.api.contracts.dto.AcceptSuggestCommand;
import com.example.api.contracts.dto.QueryAllSuggestsForMeCommand;
import com.example.api.contracts.dto.SuggestedBusinessResponse;
import com.example.api.domain.ChatRoom;
import com.example.api.domain.Contract;
import com.example.api.domain.repository.OfferEmploymentRepository;
import com.example.api.domain.OfferEmployment;
import com.example.api.suggest.controller.dto.SuggestStatusDTO;
import com.example.api.suggest.controller.dto.request.BusinessIdRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuggestService {
    private final OfferEmploymentRepository offerEmploymentRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private final OfferRepository offerRepository;
    private final ContractRepository contractRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ContractMapper contractMapper;

    @Transactional(readOnly = true)
    public List<SuggestStatusDTO> getSuggestStatus(final BusinessIdRequest businessIdRequest) {
        List<OfferEmployment> offerList = offerEmploymentRepository.findAllByBusinessBusinessId(businessIdRequest.BusinessId());
        List<SuggestStatusDTO> suggestStatusDTOList = new ArrayList<>();

        String status;
        for (OfferEmployment offer : offerList) {
            if (offer.isSuggestReaded() == false) {
                status = "대기 중";
            } else if (offer.isSuggestReaded() == true && offer.isSuggestSucceeded() == false) {
                status = "거절";
            } else if (offer.isSuggestReaded() && offer.isSuggestSucceeded() == true) {
                if(offer.getContract().isContractSucceeded() == false)
                    status = "체결 중";
                else
                    status = "체결 완료";
            } else {
                status = "알 수 없는 상태";
            }
            List<Object[]> suggestList = offerEmploymentRepository.findSuggestByOfferEmploymentId(offer.getSuggestId());
            Object[] suggest = suggestList.get(0);
            suggestStatusDTOList.add(makeSuggestStatusDTO(suggest, status));
        }
        return suggestStatusDTOList;
    }

    public SuggestStatusDTO makeSuggestStatusDTO(Object[] suggest, String status) {
        String name = (String) suggest[0];
        String businessName = suggest[1].toString();
        LocalDateTime startTime = (LocalDateTime) suggest[2];
        LocalDateTime endTime = (LocalDateTime) suggest[3];

        String formattedDate = startTime.format(formatter);

        StringBuilder workTime = new StringBuilder();
        workTime.append(formattedDate)
                .append(" ")
                .append(String.format("%02d", startTime.getHour()))
                .append(":")
                .append(String.format("%02d", startTime.getMinute()))
                .append("~")
                .append(String.format("%02d", endTime.getHour()))
                .append(":")
                .append(String.format("%02d", endTime.getMinute()));
        String workTimeStr = workTime.toString();

        return new SuggestStatusDTO(
                status,
                name,
                businessName,
                workTimeStr
        );
    }

    @Transactional(readOnly = true)
    public List<SuggestedBusinessResponse> getAllRelatedSuggests(final QueryAllSuggestsForMeCommand allSuggestsForMeCommand) {
        return offerRepository.queryEmployersSuggests(allSuggestsForMeCommand.employeeId());
    }

    @Transactional
    public void acceptSuggest(@Validated final AcceptSuggestCommand acceptSuggestCommand) {
        final OfferEmployment offerEmployment = loadOffer(acceptSuggestCommand.suggestId());
        offerEmployment.succeeded();

        final Contract contract = contractMapper.notYetSucceeded(offerEmployment);
        contractRepository.save(contract);
    }

    @Transactional
    public void createChatRoom(@Validated final AcceptSuggestCommand acceptSuggestCommand) {
        final OfferEmployment offerEmployment = loadOffer(acceptSuggestCommand.suggestId());
        createChatRoom(offerEmployment);
    }

    private OfferEmployment loadOffer(final Long offerId) {
        return offerRepository.findById(offerId)
                .orElseThrow();
    }

    private void createChatRoom(final OfferEmployment offer) {
        ChatRoom chatRoom = new ChatRoom(offer);
        chatRoomRepository.save(chatRoom);
    }
}