package com.example.api.board.controller;

import com.example.api.board.dto.request.*;
import com.example.api.board.dto.response.*;
import com.example.api.board.dto.update.UpdateExternalCareerRequest;
import com.example.api.board.dto.update.UpdatePersonalInfoRequest;
import com.example.api.board.dto.update.UpdatePreferredCategoriesRequest;
import com.example.api.board.dto.update.UpdatePreferredDistrictsRequest;
import com.example.api.board.service.BoardService;
import com.example.api.board.service.CategoryService;
import com.example.api.board.service.EmployeeService;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/possible-board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final CategoryService categoryService;
    private final EmployeeService employeeService;

    /**
     *  1. 회원 정보
     *  2. 희망 근무지
     *  2. 희망 카테고리
     *  3. 근무 가능 시간 (캘린더)
     *  4. 외부 경력
     *  5. 내부 경력
     */
    @GetMapping("/personal-info")
    public ResponseEntity<PersonalInfoResponse> getPersonalInfo(@AuthenticationPrincipal final Long employeeId){
        return ResponseEntity.ok(boardService.getPersonalInfoResponse(new EmployeeIdRequest(employeeId)));
    }

    @GetMapping("/work-preferences/districts")
    public ResponseEntity<List<FlavoredDistrictResponse>> getPreferredDistricts(@AuthenticationPrincipal final Long employeeId){
        return ResponseEntity.ok(boardService.getPreferredDistricts(new EmployeeIdRequest(employeeId)));
    }

    @GetMapping("/work-preferences/category")
    public ResponseEntity<List<FlavoredCategoryResponse>> getPreferredCategories(@AuthenticationPrincipal final Long employeeId){
        return ResponseEntity.ok(boardService.getPreferredCategories(new EmployeeIdRequest(employeeId)));
    }

    @GetMapping("/work-hours")
    public ResponseEntity<List<WorkHourResponse>> getWorkHours(@AuthenticationPrincipal final Long employeeId){
        return ResponseEntity.ok(boardService.getWorkHours(new EmployeeIdRequest(employeeId)));
    }

    @GetMapping("/external-career")
    public ResponseEntity<List<ExternalCareerResponse>> getExternalCareers(@AuthenticationPrincipal final Long employeeId){
        return ResponseEntity.ok(boardService.getExternalCareers(new EmployeeIdRequest(employeeId)));
    }

    @GetMapping("/internal-career")
    public ResponseEntity<List<InternalCareerResponse>> getInternalCareers(@AuthenticationPrincipal final Long employeeId){
        return ResponseEntity.ok(boardService.getInternalCareers(new EmployeeIdRequest(employeeId)));
    }

    @PostMapping("/personal-info")
    public ResponseEntity<PersonalInfoResponse> updatePersonalInfo(
            @AuthenticationPrincipal final Long employeeId,
            @RequestBody final UpdatePersonalInfoRequest request
    ){
        employeeService.updatePersonalInfo(new EmployeeIdRequest(employeeId), request);
        return ResponseEntity.ok(employeeService.updatePersonalInfo(new EmployeeIdRequest(employeeId), request));
    }

    @PostMapping("/work-preferences/districts")
    public ResponseEntity<List<FlavoredDistrictResponse>> updatePreferredDistricts(
            @AuthenticationPrincipal final Long employeeId,
            @RequestBody final UpdatePreferredDistrictsRequest request
    ){
        return ResponseEntity.ok(boardService.updatePreferredDistrict(new EmployeeIdRequest(employeeId), request));
    }

    @PostMapping("/work-preferences/category")
    public ResponseEntity<List<FlavoredCategoryResponse>> updatePreferredCategories(
            @AuthenticationPrincipal final Long employeeId,
            @RequestBody final UpdatePreferredCategoriesRequest request){
        return ResponseEntity.ok(boardService.updatePreferredCategories(new EmployeeIdRequest(employeeId), request));
    }

    @PostMapping("/external-career")
    public ResponseEntity<List<ExternalCareerResponse>> updateExternalCareers(
            @AuthenticationPrincipal final Long employeeId,
            @RequestBody final UpdateExternalCareerRequest request){
        return ResponseEntity.ok(boardService.updateExternalCareers(new EmployeeIdRequest(employeeId), request));
    }

    @PostMapping("/work-hours")
    public ResponseEntity<?> updatePossibleTimes(
            @RequestBody final AddPossibleTimeRequest addPossibleTimeRequest,
            final Long requestMemberId
    ) {
        final AddPossibleTimeCommand addPossibleTimeCommand = addPossibleTimeRequest.toCommand(requestMemberId);
        boardService.addPossibleBoard(addPossibleTimeCommand);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity changeOpenStatus(
            @AuthenticationPrincipal final Long employeeId,
            @RequestParam("open-status") Boolean openStatus
    ) {
        EmployeeIdRequest employeeIdRequest = new EmployeeIdRequest(employeeId);
        employeeService.changeOpenStatus(employeeIdRequest, openStatus);
        return ResponseEntity.ok("사용자 정보가 성공적으로 업데이트되었습니다.");
    }
}