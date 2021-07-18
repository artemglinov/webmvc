package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
//@NoArgsConstructor
@AllArgsConstructor
public class OrderIdAmountRequestDto {
    private String userName;
    private String password;
    private long id;
    private int amount;
}
