package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class OrderStatusResponseDto {
    private final long id;
    private final String orderNumber;
    private final int amount;
    private final int currency;
    private final int status;
}
