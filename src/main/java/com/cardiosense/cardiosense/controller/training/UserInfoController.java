package com.cardiosense.cardiosense.controller.training;

import com.cardiosense.cardiosense.model.training.UserInfoEntity;
import com.cardiosense.cardiosense.service.UserService;
import com.cardiosense.cardiosense.service.training.UserInfoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/training/user-info")
@AllArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> saveUserInfo(@RequestBody @Valid UserInfoEntity userInfo) {
        if (userInfo == null || userInfo.getUser().getId() == null) {
            return ResponseEntity.badRequest().body("La información del usuario o el ID del usuario no puede ser nula");
        }
        if (!userService.existsById(userInfo.getUser().getId())) {
            return ResponseEntity.badRequest().body("El usuario con ID " + userInfo.getUser().getId() + " no fue encontrado");
        }

        if (userInfoService.existsByUserId(userInfo.getUser().getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya tiene información guardada");
        }

        UserInfoEntity savedUserInfo = userInfoService.saveUserInfo(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserInfo);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUserInfo(@PathVariable String userId, @RequestBody @Valid UserInfoEntity userInfo) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("El ID del usuario no puede ser nulo");
        }
        if (!userService.existsById(userId)) {
            return ResponseEntity.badRequest().body("El usuario con ID " + userId + " no fue encontrado");
        }

        userInfoService.updateUserInfo(userId, userInfo);
        return ResponseEntity.ok(userInfoService.getUserInfo(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoEntity> getUserInfo(@PathVariable String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }
        return ResponseEntity.ok(userInfoService.getUserInfo(userId));
    }
}
