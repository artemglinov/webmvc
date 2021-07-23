package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.auth.AuthManager;
import org.example.domain.Order;
import org.example.dto.OrderIdAmountRequestDto;
import org.example.dto.OrderIdRequestDto;
import org.example.dto.OrderRegistrationRequestDto;
import org.example.dto.OrderStatusResponseDto;
import org.example.exception.AppAuthenticationException;
import org.example.exception.AppAuthorizationException;
import org.example.exception.ItemNotFoundException;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final AuthManager authManager;

    private final OrderRepository repository;

    private final UserService userService;

    private final int COMPLETION_STATUS = 2;
    private final int CANCELLATION_STATUS = 3;

    public long register(OrderRegistrationRequestDto dto) {
        authenticate(dto.getUserName(), dto.getPassword());
        Order order = Order.builder()
                .userId(userService.getUserId(dto.getUserName(), dto.getPassword()))
                .orderNumber(dto.getOrderNumber())
                .amount(dto.getAmount())
                .currency(dto.getCurrency())
                .returnUrl(dto.getReturnUrl())
                .failUrl(dto.getFailUrl())
                .build();
        return repository.create(order).orElseThrow(ItemNotFoundException::new);
    }

    public void deposit(OrderIdAmountRequestDto dto) {
        authenticate(dto.getUserName(), dto.getPassword());
        authorize(dto.getId(), dto.getUserName(), dto.getPassword());
        if (!repository.setStatus(dto.getId(), COMPLETION_STATUS)) {
            throw new ItemNotFoundException();
        }
    }

    public OrderStatusResponseDto getOrderStatus(OrderIdRequestDto dto) {
        authenticate(dto.getUserName(), dto.getPassword());
        authorize(dto.getId(), dto.getUserName(), dto.getPassword());
        return repository.getStatus(dto.getId()).orElseThrow(ItemNotFoundException::new);

    }

    public void reverse(OrderIdRequestDto dto) {
        authenticate(dto.getUserName(), dto.getPassword());
        authorize(dto.getId(), dto.getUserName(), dto.getPassword());
        if (!repository.setStatus(dto.getId(), CANCELLATION_STATUS)) {
            throw new ItemNotFoundException();
        }
    }

    private void authenticate(String userName, String password) {
        if (!authManager.authenticate(userName, password)) {
            throw new AppAuthenticationException();
        }
    }

    private void authorize(long id, String userName, String password) {
        if (!authManager.authorize(id, userName, password)) {
            throw new AppAuthorizationException();
        }
    }
}
