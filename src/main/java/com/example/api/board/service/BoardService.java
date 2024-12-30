package com.example.api.board.service;


import com.example.api.board.controller.domain.request.EmployeeIdRequest;
import com.example.api.board.controller.domain.response.MyInfoDTO;
import com.example.api.domain.repository.*;
import com.example.api.possbileboard.PossibleBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final MyInfoRepository myInfoRepository;
    private final OfferEmploymentRepository offerEmploymentRepository;
    private final ExternalCareerRepository externalCareerRepository;
    private final PossibleBoardRepository possibleBoardRepository;
    private final FlavoredRepository flavoredRepository;

    @Transactional(readOnly = true)
    public MyInfoDTO findMyInfoById(final EmployeeIdRequest employeeIdRequest) {
        MyInfoDTO myInfoDTOById = myInfoRepository.findMyInfoDTOById(employeeIdRequest.employeeId());
        myInfoDTOById.setInnerCarrerList(offerEmploymentRepository.findAllDTOByEmployeeId(employeeIdRequest.employeeId()));
        myInfoDTOById.setExternalCareerList(externalCareerRepository.findAllDTOByEmployeeAccountId(employeeIdRequest.employeeId()));
        myInfoDTOById.setPossibleBoardList(possibleBoardRepository.findAllDTOByEmployeeAccountId(employeeIdRequest.employeeId()));
        myInfoDTOById.setFlavoredCategoryList(flavoredRepository.findAllCategoryDTOByEmployeeId(employeeIdRequest.employeeId()));

        return myInfoDTOById;
    }


}

