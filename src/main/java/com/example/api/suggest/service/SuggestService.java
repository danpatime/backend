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
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import com.example.api.suggest.controller.dto.SuggestStatusDTO;
import com.example.api.suggest.controller.dto.request.BusinessIdRequest;
import com.example.api.suggest.controller.dto.request.OfferEmploymentDetailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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
        return currentSuggestStatusCheck(offerList);
    }

    private List<SuggestStatusDTO> currentSuggestStatusCheck(List<OfferEmployment> offerList) {
        List<SuggestStatusDTO> suggestStatusDTOList = new ArrayList<>();
        String status;
        for (OfferEmployment offer : offerList) {
            if (offer.isSuggestReaded() == false) {
                status = "대기 중";
            } else if (offer.isSuggestReaded() == true && offer.isSuggestSucceeded() == false) {
                status = "거절";
            } else if (offer.isSuggestReaded() && offer.isSuggestSucceeded() == true) {
                Contract contract = contractRepository.findById(offer.getSuggestId()).orElseThrow(() -> new BusinessException(ErrorCode.CONTRACT_EXCEPTION));
                if(!contract.isContractSucceeded())
                    status = "체결 중";
                else
                    status = "체결 완료";
            } else {
                status = "알 수 없는 상태";
            }

            if(contractRepository.existsById(offer.getSuggestId())) {
                OfferEmploymentDetailRequest contractDetail = contractRepository.findContractByContractId(offer.getSuggestId());
                suggestStatusDTOList.add(makeSuggestStatusDTO(contractDetail, status));
            } else {
                OfferEmploymentDetailRequest suggestDetail = offerEmploymentRepository.findSuggestByOfferEmploymentId(offer.getSuggestId());
                suggestStatusDTOList.add(makeSuggestStatusDTO(suggestDetail, status));
            }
        }
        return suggestStatusDTOList;
    }

    public SuggestStatusDTO makeSuggestStatusDTO(OfferEmploymentDetailRequest suggest, String status) {
        String formattedDate = suggest.startTime().format(formatter);

        StringBuilder workTime = new StringBuilder();
        workTime.append(formattedDate)
                .append(" ")
                .append(String.format("%02d", suggest.startTime().getHour()))
                .append(":")
                .append(String.format("%02d", suggest.startTime().getMinute()))
                .append("~")
                .append(String.format("%02d", suggest.endTime().getHour()))
                .append(":")
                .append(String.format("%02d", suggest.endTime().getMinute()));
        String workTimeStr = workTime.toString();

        return new SuggestStatusDTO(
                status,
                suggest.name(),
                suggest.hourlyPayment(),
                suggest.businessName(),
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
        offerEmployment.setContract(contract);
        offerEmploymentRepository.save(offerEmployment);
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