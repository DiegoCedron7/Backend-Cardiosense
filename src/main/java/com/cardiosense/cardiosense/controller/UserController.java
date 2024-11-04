package com.cardiosense.cardiosense.controller;


import com.cardiosense.cardiosense.DTO.UserInfoDTO.FullDataDTO;
import com.cardiosense.cardiosense.model.UserEntity;
import com.cardiosense.cardiosense.model.UserInfo.FullData;
import com.cardiosense.cardiosense.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserEntity>> getUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable String id) {
        Optional<UserEntity> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        UserEntity createdUser = userService.createUser(userEntity);
        return ResponseEntity.ok(createdUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable String id, @RequestBody UserEntity userEntity) {
        Optional<UserEntity> updatedUser = userService.updateUser(id, userEntity);
        return updatedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/{id}/save-data")
    public ResponseEntity<String> saveData(@PathVariable String id, @RequestBody FullData fullData) {
        try {
            userService.saveData(id, fullData);
            return ResponseEntity.ok("Data saved successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/get-data")
    public ResponseEntity<FullDataDTO> getData(@PathVariable String id) {
        Optional<FullDataDTO> fullData = userService.getData(id);
        return fullData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}