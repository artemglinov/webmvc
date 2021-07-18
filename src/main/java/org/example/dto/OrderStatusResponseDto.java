package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusResponseDto {
    private long id;
    private String orderNumber;
    private int amount;
    private int currency;
    private int status;
}
