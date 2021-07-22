package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Order;
import org.example.dto.OrderIdAmountRequestDto;
import org.example.dto.OrderIdRequestDto;
import org.example.dto.OrderStatusResponseDto;
import org.example.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService service;

  @PostMapping("/register.do")
  public long register(Order order) {
    return service.register(order);
  }

  @PostMapping(path = "/getOrderStatus.do")
  public OrderStatusResponseDto getOrderStatus(OrderIdRequestDto dto) {
    return service.getOrderStatus(dto);
  }

  @PostMapping(path = "/deposit.do")
  @ResponseStatus(HttpStatus.OK)
  public void deposit(OrderIdAmountRequestDto dto) {
    service.deposit(dto);
  }


  @PostMapping(path = "/reverse.do", params = "id")
  @ResponseStatus(HttpStatus.OK)
  public void reverse(OrderIdRequestDto dto) {
    service.reverse(dto);
  }
}
