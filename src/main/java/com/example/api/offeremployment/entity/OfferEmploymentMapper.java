package com.example.api.offeremployment.entity;

import com.example.api.contracts.ContractRepository;
import com.example.api.domain.OfferEmployment;
import com.example.api.domain.repository.OfferEmploymentRepository;
import com.example.api.suggest.controller.dto.SuggestStatusDTO;
import com.example.api.suggest.controller.dto.request.OfferEmploymentDetailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferEmploymentMapper {
    private final ContractRepository contractRepository;
    private final OfferEmploymentRepository offerEmploymentRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public List<SuggestStatusDTO> currentSuggestStatusCheck(List<OfferEmployment> offerList) {
        List<SuggestStatusDTO> suggestStatusDTOList = new ArrayList<>();
        for (OfferEmployment offer : offerList) {
            if(contractRepository.existsById(offer.getSuggestId())) {
                OfferEmploymentDetailRequest contractDetail = contractRepository.findContractByContractId(offer.getSuggestId());
                suggestStatusDTOList.add(makeSuggestStatusDTO(contractDetail));
            } else {
                OfferEmploymentDetailRequest suggestDetail = offerEmploymentRepository.findSuggestByOfferEmploymentId(offer.getSuggestId());
                suggestStatusDTOList.add(makeSuggestStatusDTO(suggestDetail));
            }
        }
        return suggestStatusDTOList;
    }

    public SuggestStatusDTO makeSuggestStatusDTO(OfferEmploymentDetailRequest suggest) {
        String formattedDate = suggest.startTime().format(formatter);

        String workTimeStr = formattedDate +
                " " +
                String.format("%02d", suggest.startTime().getHour()) +
                ":" +
                String.format("%02d", suggest.startTime().getMinute()) +
                "~" +
                String.format("%02d", suggest.endTime().getHour()) +
                ":" +
                String.format("%02d", suggest.endTime().getMinute());

        return new SuggestStatusDTO(
                suggest.status(),
                suggest.name(),
                suggest.hourlyPayment(),
                suggest.businessName(),
                workTimeStr,
                suggest.chatRoomId()
        );
    }
}
