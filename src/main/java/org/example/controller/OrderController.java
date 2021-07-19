package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Order;
import org.example.dto.OrderIdAmountRequestDto;
import org.example.dto.OrderIdRequestDto;
import org.example.dto.OrderStatusResponseDto;
import org.example.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/rest")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService service;

//  @GetMapping
//  @ResponseBody
//  public List<Order> getAll() {
//    return service.getAll();
//  }

//  @GetMapping(params = "id")
//  @ResponseBody
//  public Order getById(@RequestParam long id) {
//    return service.getById(id);
//  }

  @PostMapping("/register.do")
  @ResponseBody
  public long register(Order order) {
    return service.register(order);
  }

  @PostMapping(path = "/getOrderStatus.do")
  @ResponseBody
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
