package com.example.api.suggest.service;

import com.example.api.board.repository.OfferEmploymentRepository;
import com.example.api.domain.OfferEmployment;
import com.example.api.suggest.controller.domain.SuggestStatusDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SuggestService {
    private final OfferEmploymentRepository offerEmploymentRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public List<SuggestStatusDTO> getSuggestStatus(long businessId) {
        List<OfferEmployment> offerList = offerEmploymentRepository.findAllByBusinessBusinessId(businessId);
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
}