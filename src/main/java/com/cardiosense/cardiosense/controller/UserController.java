package com.cardiosense.cardiosense.controller;


import com.cardiosense.cardiosense.DTO.User.UserInfoDTO.FullDataDTO;
import com.cardiosense.cardiosense.model.MercadoPago.MercadoPagoPayment;
import com.cardiosense.cardiosense.model.User.Info.FullData;
import com.cardiosense.cardiosense.model.User.UserEntity;
import com.cardiosense.cardiosense.repository.User.UserRepository;
import com.cardiosense.cardiosense.service.GeneratorService;
import com.cardiosense.cardiosense.service.MercadoPago.MercadoPagoService;
import com.cardiosense.cardiosense.service.UserService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    @PostMapping("/preference/{id}")
    public ResponseEntity<?> createPreference(@PathVariable String id) throws MPApiException, MPException {
        Optional<UserEntity> user = userService.getUserById(id);
        Preference preference = userService.createPreference(user);
        return ResponseEntity.ok(Collections.singletonMap("response", preference.getId()));
    }

    @PostMapping("/payment/mercado-pago")
    public ResponseEntity<?> getOrderPayment(@RequestBody MercadoPagoPayment mPagoPayment) {
        userService.getStatusPayment(mPagoPayment);
        return ResponseEntity.ok(Collections.singletonMap("response", "Payment status updated"));
    }

    @GetMapping("/count")
    public ResponseEntity<?> countUsers() {
        return ResponseEntity.ok(Collections.singletonMap("response", userService.countUsersByMonth()));
    }

    @GetMapping("/count-by-subscription")
    public ResponseEntity<?> countUsersBySubscription(@RequestParam boolean subscription) {
        long userCount = userService.countUsersBySubscription(subscription);
        return ResponseEntity.ok(Collections.singletonMap("response", userCount));
    }


}