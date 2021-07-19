package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderIdRequestDto {
    private final String userName;
    private final String password;
    private final long id;
}
