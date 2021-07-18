package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@NoArgsConstructor
@AllArgsConstructor
public class OrderIdRequestDto {
    private String userName;
    private String password;
    private long id;
}
