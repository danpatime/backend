package com.example.api.board.service;


import com.example.api.board.controller.domain.MyInfoDTO;
import com.example.api.board.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final MyInfoRepository myInfoRepository;
    private final OfferEmploymentRepository offerEmploymentRepository;
    private final ExternalCareerRepository externalCareerRepository;
    private final PossibleBoardRepository possibleBoardRepository;
    private final FlavoredRepository flavoredRepository;

    public MyInfoDTO findMyInfoById(long employeeId) {
        MyInfoDTO myInfoDTOById = myInfoRepository.findMyInfoDTOById(employeeId);
        myInfoDTOById.setInnerCarrerList(offerEmploymentRepository.findAllInnerCareerDTOByEmployeeId(employeeId));
        myInfoDTOById.setExternalCareerList(externalCareerRepository.findAllByEmployeeEmployeeId(employeeId));
        myInfoDTOById.setPossibleBoardList(possibleBoardRepository.findAllPossibleBoardByEmployeeEmployeeId(employeeId));
        myInfoDTOById.setFlavoredCategoryList(flavoredRepository.findAllFlavoredByEmployeeId(employeeId));

        return myInfoDTOById;
    }


}

