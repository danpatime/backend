package com.example.api.suggest.controller.domain;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SuggestStatusDTO {
    private String status;
    private String name;
    private String businessName;
    private String workTime;
    // 채팅 방 번호 추가
}
