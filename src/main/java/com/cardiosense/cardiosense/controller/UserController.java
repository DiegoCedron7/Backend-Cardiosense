package com.cardiosense.cardiosense.controller;


import com.cardiosense.cardiosense.DTO.User.UserInfoDTO.FullDataDTO;
import com.cardiosense.cardiosense.model.User.UserEntity;
import com.cardiosense.cardiosense.model.User.Info.FullData;
import com.cardiosense.cardiosense.service.GeneratorService;
import com.cardiosense.cardiosense.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/users")
@RestController

public class UserController {

    private final UserService userService;
    private final GeneratorService generatorService;

    public UserController(UserService userService, GeneratorService generatorService) {
        this.userService = userService;
        this.generatorService = generatorService;
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

    @GetMapping("/by-email")
    public ResponseEntity<UserEntity> getUserByEmail(@RequestParam String email) {
        Optional<UserEntity> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/{id}/save-data")
    public ResponseEntity<String> saveData(@PathVariable String id, @RequestBody FullData fullData) {
        try {
            userService.saveOrUpdateData(id, fullData);
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

    @PutMapping("/{id}/change-weight")
    public ResponseEntity<String> changeWeight(@PathVariable String id, @RequestParam int newWeight) {
        try {
            userService.changeWeight(id, newWeight);
            generatorService.generateDiet(id);
            generatorService.generateTraining(id);
            return ResponseEntity.ok("Weight changed successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}