package com.cardiosense.cardiosense.controller.training;

import com.cardiosense.cardiosense.model.training.UserInfoEntity;
import com.cardiosense.cardiosense.service.training.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/training/user-info")
@AllArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @PostMapping("/save-user-info")
    public ResponseEntity<UserInfoEntity> saveUserInfo(@RequestBody UserInfoEntity userInfo) {
        if (userInfo == null || userInfo.getUser().getId() == null) {
            throw new IllegalArgumentException("User info or user ID cannot be null");
        }
        return ResponseEntity.ok(userInfoService.saveUserInfo(userInfo));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoEntity> getUserInfo(@PathVariable String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return ResponseEntity.ok(userInfoService.getUserInfo(userId));
    }

    @PatchMapping
    public ResponseEntity<UserInfoEntity> updateUserInfo(@RequestBody UserInfoEntity userInfo) {
        if (userInfo == null || userInfo.getUser().getId() == null) {
            throw new IllegalArgumentException("User info or user ID cannot be null");
        }
        return ResponseEntity.ok(userInfoService.updateUserInfo(userInfo.getUser().getId(), userInfo));
    }
}
