package com.cardiosense.cardiosense.service;

import com.cardiosense.cardiosense.model.UserEntity;
import com.cardiosense.cardiosense.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(String id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User with id " + id + " not found")
        ));
    }

    public UserEntity createUser(UserEntity userEntity) {
        if (userEntity == null) throw new IllegalArgumentException("User cannot be null");

        userEntity.setName(userEntity.getName());
        userEntity.setLastname(userEntity.getLastname());
        userEntity.setDocumentType(userEntity.getDocumentType());
        userEntity.setPhone(userEntity.getPhone());
        userEntity.setAddress(userEntity.getAddress());
        userEntity.setEmail(userEntity.getEmail());
        userEntity.setPassword(userEntity.getPassword());

        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> updateUser(String id, UserEntity userDTO) {

        if (id == null) return Optional.empty();

        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            userEntity.setName(userDTO.getName());
            userEntity.setLastname(userDTO.getLastname());
            userEntity.setDocumentType(userDTO.getDocumentType());
            userEntity.setPhone(userDTO.getPhone());
            userEntity.setAddress(userDTO.getAddress());
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setPassword(userDTO.getPassword());
            userEntity.setImage(userDTO.getImage());
            return Optional.of(userRepository.save(userEntity));
        }

        return Optional.empty();
    }

    public boolean deleteUser(String id) {
        // TODO: Implement this method
        return false;
    }



}
