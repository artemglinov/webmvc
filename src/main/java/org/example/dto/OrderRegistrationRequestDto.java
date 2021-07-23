package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderRegistrationRequestDto {

    private final String userName;
    private final String password;

    private final String orderNumber;
    private final int amount;
    private final int currency;
    private final String returnUrl;
    private final String failUrl;
}
