package com.cardiosense.cardiosense.service;

import com.cardiosense.cardiosense.DTO.UserDTO;
import com.cardiosense.cardiosense.DTO.UserInfoDTO.DietDTO;
import com.cardiosense.cardiosense.DTO.UserInfoDTO.FullDataDTO;
import com.cardiosense.cardiosense.DTO.UserInfoDTO.TrainingDTO;
import com.cardiosense.cardiosense.model.UserEntity;
import com.cardiosense.cardiosense.model.UserInfo.Diet;
import com.cardiosense.cardiosense.model.UserInfo.FullData;
import com.cardiosense.cardiosense.model.UserInfo.Training;
import com.cardiosense.cardiosense.repository.UserInfo.DietRepository;
import com.cardiosense.cardiosense.repository.UserInfo.TrainingRepository;
import com.cardiosense.cardiosense.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DietRepository dietRepository;
    private final TrainingRepository trainingRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(String id) {
        if (id == null) return Optional.empty();

        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found")));
    }

    public UserEntity createUser(UserEntity userEntity) {
        if (userEntity == null) throw new IllegalArgumentException("User cannot be null");
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

    public void saveData(String id, FullData fullData) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserEntity userEntity = user.get();

            if (userEntity.isFirstLogin()) {
                userEntity.setFirstLogin(false);
                userRepository.save(userEntity);
            }

            // Guardar Dieta
            Diet diet = new Diet();
            diet.setUser(userEntity);
            diet.setCalorias(fullData.getDiet().getCalorias());
            dietRepository.save(diet);

            // Guardar Entrenamiento
            Training training = new Training();
            training.setUser(userEntity);
            training.setKilometrosRecorridos(fullData.getTraining().getKilometrosRecorridos());
            trainingRepository.save(training);

        } else {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
    }

    public Optional<FullDataDTO> getData(String id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserEntity userEntity = user.get();

            // Convertir UserEntity a UserDTO
            UserDTO userDTO = new UserDTO();
            userDTO.setName(userEntity.getName());


            // Convertir Diet a DietDataDTO
            Diet diet = dietRepository.findByUser(userEntity);
            DietDTO dietDTO = new DietDTO();
            dietDTO.setCalorias(diet.getCalorias());

            // Convertir Training a TrainingDataDTO
            Training training = trainingRepository.findByUser(userEntity);
            TrainingDTO trainingDTO = new TrainingDTO();
            trainingDTO.setKilometrosRecorridos(training.getKilometrosRecorridos());


            FullDataDTO fullDataDTO = new FullDataDTO();
            fullDataDTO.setUser(userDTO);
            fullDataDTO.setDiet(dietDTO);
            fullDataDTO.setTraining(trainingDTO);

            return Optional.of(fullDataDTO);
        }
        return Optional.empty();
    }

}
