package com.emahmutoglu.userservice.service.user;

import com.emahmutoglu.userservice.data.client.request.UserRequest;
import com.emahmutoglu.userservice.data.client.response.UserResponse;
import com.emahmutoglu.userservice.data.dbmodel.User;
import com.emahmutoglu.userservice.data.repository.UserRepository;
import com.emahmutoglu.userservice.mapper.UserMapper;
import com.emahmutoglu.userservice.service.kafka.KafkaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final static String SEND_EMAIL_NOTIFICATION = "send-email-notification";
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KafkaService kafkaService;

    @Override
    public List<UserResponse> getAll() {
        return userMapper.toResponseList(userRepository.findAll());
    }

    @Override
    public UserResponse getById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        User savedUser = userRepository.save(userMapper.toEntity(userRequest));
        kafkaService.send(SEND_EMAIL_NOTIFICATION, savedUser);
        return userMapper.toResponse(savedUser);
    }
}
