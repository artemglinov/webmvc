package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.exception.ItemNotFoundException;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public long getUserId(String userName, String password) {
        return repository.getUserId(userName, password).orElseThrow(ItemNotFoundException::new);
    }
}
