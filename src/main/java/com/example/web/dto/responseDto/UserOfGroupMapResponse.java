package com.example.web.dto.responseDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserOfGroupMapResponse {
    private Long userId;
    private String name;
    private String surname;
    private String phoneNumber;
}
