package com.example.api.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public record BusinessNumberResponse(
        @JsonProperty("request_cnt") int requestCount,
        @JsonProperty("valid_cnt") int validCount,
        @JsonProperty("status_code") String statusCode,
        List<Data> data
) {
    private record Data(
            @JsonProperty("b_no") String businessNumber,
            String valid,
            @JsonProperty("request_param") RequestParam requestParam,
            Status status
    ) {
    }
    private record RequestParam(
            @JsonProperty("b_no") String businessNumber,
            @JsonProperty("start_dt") String startDate,
            @JsonProperty("p_nm") String name,
            @JsonProperty("b_nm") String businessName
    ) {
    }
    private record Status(
            @JsonProperty("b_no") String businessNumber,
            @JsonProperty("b_stt") String businessStatus,
            @JsonProperty("b_stt_cd") String businessStatusCode,
            @JsonProperty("tax_type") String taxType,
            @JsonProperty("tax_type_cd") String taxTypeCode,
            @JsonProperty("end_dt") String endDate,
            @JsonProperty("utcc_yn") String utccYn,
            @JsonProperty("tax_type_change_dt") String taxTypeChangeDate,
            @JsonProperty("invoice_apply_dt") String invoiceApplyDate,
            @JsonProperty("rbf_tax_type") String rbfTaxType,
            @JsonProperty("rbf_tax_type_cd") String rbfTaxTypeCode
    ) {
    }

    public String getValid(){
        return data.get(0).valid;
    }
}