package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private long userId;
  private String orderNumber;
  private int amount;
  private int currency;
  private int status;
  private String returnUrl;
  private String failUrl;
}
