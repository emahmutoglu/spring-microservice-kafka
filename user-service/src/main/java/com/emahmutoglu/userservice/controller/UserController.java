package com.emahmutoglu.userservice.controller;

import com.emahmutoglu.userservice.data.client.request.UserRequest;
import com.emahmutoglu.userservice.data.client.response.UserResponse;
import com.emahmutoglu.userservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(UUID.fromString(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.save(userRequest));
    }
}
