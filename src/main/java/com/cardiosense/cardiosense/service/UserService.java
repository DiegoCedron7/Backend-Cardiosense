package com.cardiosense.cardiosense.service;

import com.cardiosense.cardiosense.DTO.User.UserDTO;
import com.cardiosense.cardiosense.DTO.User.UserInfoDTO.DietDTO;
import com.cardiosense.cardiosense.DTO.User.UserInfoDTO.FullDataDTO;
import com.cardiosense.cardiosense.DTO.User.UserInfoDTO.TrainingDTO;
import com.cardiosense.cardiosense.model.User.UserEntity;
import com.cardiosense.cardiosense.model.User.Info.Diet;
import com.cardiosense.cardiosense.model.User.Info.FullData;
import com.cardiosense.cardiosense.model.User.Info.Training;
import com.cardiosense.cardiosense.repository.User.Info.DietRepository;
import com.cardiosense.cardiosense.repository.User.Info.TrainingRepository;
import com.cardiosense.cardiosense.repository.User.UserRepository;
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


    public void saveOrUpdateData(String id, FullData fullData) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }

        UserEntity userEntity = userOptional.get();

        if (fullData.getUser() != null) {
            if (fullData.getUser().getPhone() != null) {
                userEntity.setPhone(fullData.getUser().getPhone());
            }
            if (fullData.getUser().getAddress() != null) {
                userEntity.setAddress(fullData.getUser().getAddress());
            }
            if (fullData.getUser().getDocumentType() != null) {
                userEntity.setDocumentType(fullData.getUser().getDocumentType());
            }
        }

        userRepository.save(userEntity);

        // Diet
        Diet diet = dietRepository.findByUser(userEntity);
        if (diet == null) {
            diet = new Diet();
            diet.setUser(userEntity);
        }
        diet.setCalorias(fullData.getDiet().getCalorias());
        dietRepository.save(diet);

        // Training
        Training training = trainingRepository.findByUser(userEntity);
        if (training == null) {
            training = new Training();
            training.setUser(userEntity);
        }
        training.setKilometrosRecorridos(fullData.getTraining().getKilometrosRecorridos());
        trainingRepository.save(training);

        // First login update
        if (userEntity.isFirstLogin()) {
            userEntity.setFirstLogin(false);
            userRepository.save(userEntity);
        }
    }


    public Optional<FullDataDTO> getData(String id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserEntity userEntity = user.get();

            UserDTO userDTO = new UserDTO();
            userDTO.setName(userEntity.getName());


            Diet diet = dietRepository.findByUser(userEntity);
            DietDTO dietDTO = new DietDTO();
            dietDTO.setCalorias(diet.getCalorias());

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
