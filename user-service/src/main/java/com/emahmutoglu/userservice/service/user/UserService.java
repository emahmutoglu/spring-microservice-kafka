package com.emahmutoglu.userservice.service.user;

import com.emahmutoglu.userservice.data.client.request.UserRequest;
import com.emahmutoglu.userservice.data.client.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
    List<UserResponse> getAll();

    UserResponse getById(UUID id);

    UserResponse save(UserRequest userRequest);
}
