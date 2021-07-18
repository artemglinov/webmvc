package org.example.auth;

import lombok.RequiredArgsConstructor;
import org.example.repository.OrderRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthManager {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public boolean authenticate(String userName, String password) {
       return userRepository.isUserPresent(userName, password);
    }

    public boolean authorize(long id, String userName, String password) {
        return orderRepository.isUserMatchedWithOrder(id, userName, password);
    }
}
