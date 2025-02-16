package com.example.api.suggest.service;

import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.chat.repository.ChatRoomRepository;
import com.example.api.contracts.ContractMapper;
import com.example.api.contracts.ContractRepository;
import com.example.api.contracts.OfferRepository;
import com.example.api.contracts.dto.AcceptSuggestCommand;
import com.example.api.contracts.dto.QueryAllSuggestsForMeCommand;
import com.example.api.contracts.dto.SuggestedBusinessResponse;
import com.example.api.domain.ChatRoom;
import com.example.api.domain.Contract;
import com.example.api.domain.ProposalStatus;
import com.example.api.domain.repository.OfferEmploymentRepository;
import com.example.api.domain.OfferEmployment;
import com.example.api.offeremployment.entity.OfferEmploymentMapper;
import com.example.api.suggest.controller.dto.SuggestStatusDTO;
import com.example.api.suggest.controller.dto.request.BusinessIdRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.format.DateTimeFormatter;
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
    private final OfferEmploymentMapper offerEmploymentMapper;

    @Transactional(readOnly = true)
    public List<SuggestStatusDTO> getBusinessSuggestStatus(final BusinessIdRequest businessIdRequest) {
        List<OfferEmployment> offerList = offerEmploymentRepository.findAllByBusinessBusinessId(businessIdRequest.BusinessId());
        return offerEmploymentMapper.currentSuggestStatusCheck(offerList);
    }

    @Transactional(readOnly = true)
    public List<SuggestStatusDTO> getEmployeeSuggestStatus(final EmployeeIdRequest employeeIdRequest) {
        List<OfferEmployment> offerList = offerEmploymentRepository.findAllByEmployeeId(employeeIdRequest.employeeId());
        return offerEmploymentMapper.currentSuggestStatusCheck(offerList);
    }

    @Transactional
    public void acceptSuggest(@Validated final AcceptSuggestCommand acceptSuggestCommand) {
        final OfferEmployment offerEmployment = loadOffer(acceptSuggestCommand.suggestId());
        offerEmployment.setStatus(ProposalStatus.IN_PROGRESS);

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