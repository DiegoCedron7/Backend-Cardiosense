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

    public Optional<UserEntity> getUserByEmail(String email) {
        return email == null ? Optional.empty() : userRepository.findByEmail(email);
    }

    public Optional<UserEntity> getUserById(String id) {
        return id == null ? Optional.empty() : userRepository.findById(id);
    }

    public UserEntity createUser(UserEntity userEntity) {
        if (userEntity == null) throw new IllegalArgumentException("User cannot be null");
        return userRepository.save(userEntity);
    }

    public void saveOrUpdateData(String id, FullData fullData) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));

        updateUserInfo(userEntity, fullData);
        updateDietInfo(userEntity, fullData.getDiet());
        updateTrainingInfo(userEntity, fullData.getTraining());

        if (userEntity.isFirstLogin()) {
            userEntity.setFirstLogin(false);
            userRepository.save(userEntity);
        }
    }

    private void updateUserInfo(UserEntity userEntity, FullData fullData) {
        var userInfo = fullData.getUser();
        if (userInfo != null) {
            Optional.ofNullable(userInfo.getObjetivo()).ifPresent(userEntity::setObjetivo);
            Optional.ofNullable(userInfo.getSuplementos()).ifPresent(userEntity::setSuplementos);
            Optional.ofNullable(userInfo.getEstiloVida()).ifPresent(userEntity::setEstiloVida);
            if (userInfo.getPesoInicial() != 0) userEntity.setPesoInicial(userInfo.getPesoInicial());
            if (userInfo.getPesoActualizado() != 0) userEntity.setPesoActualizado(userInfo.getPesoActualizado());
            if (userInfo.getAltura() != 0) userEntity.setAltura(userInfo.getAltura());
            if (userInfo.getEdad() != 0) userEntity.setEdad(userInfo.getEdad());
            Optional.ofNullable(userInfo.getSexo()).ifPresent(userEntity::setSexo);
        }
        userRepository.save(userEntity);
    }

    private void updateDietInfo(UserEntity userEntity, Diet dietData) {
        Diet diet = Optional.ofNullable(dietRepository.findByUser(userEntity)).orElse(new Diet());
        diet.setUser(userEntity);


        Optional.ofNullable(dietData).ifPresent(d -> {
            diet.setAlergias(d.getAlergias());
            diet.setIntolerancias(d.getIntolerancias());
            diet.setNumeroComidas(d.getNumeroComidas());
            diet.setAlimentosPreferidos(d.getAlimentosPreferidos());
            diet.setAlimentosEvitar(d.getAlimentosEvitar());
            diet.setPreferenciaAlimentaria(d.getPreferenciaAlimentaria());
            diet.setDistribucionMacronutrientes(d.getDistribucionMacronutrientes());
            diet.setDisponibilidadCocinar(d.getDisponibilidadCocinar());
            diet.setPreferenciasCoccion(d.getPreferenciasCoccion());
        });

        dietRepository.save(diet);
    }

    private void updateTrainingInfo(UserEntity userEntity, Training trainingData) {
        Training training = Optional.ofNullable(trainingRepository.findByUser(userEntity)).orElse(new Training());
        training.setUser(userEntity);

        Optional.ofNullable(trainingData).ifPresent(t -> {
            training.setEjercicioDiario(t.getEjercicioDiario());
            training.setCantidadEjerciciosPorDia(t.getCantidadEjerciciosPorDia());
            training.setFrecuenciaEntrenamientoSemanal(t.getFrecuenciaEntrenamientoSemanal());
            training.setTiempoDisponiblePorDia(t.getTiempoDisponiblePorDia());
            training.setNivelExperiencia(t.getNivelExperiencia());
            training.setPreferenciaEjercicios(t.getPreferenciaEjercicios());
            training.setEquipamientoDisponible(t.getEquipamientoDisponible());
            training.setAreasMejorar(t.getAreasMejorar());
            training.setLesionesLimitaciones(t.getLesionesLimitaciones());
            training.setTipoCuerpo(t.getTipoCuerpo());
            training.setPreferenciaIndoorOutdoor(t.getPreferenciaIndoorOutdoor());
            training.setCondicionesMedicas(t.getCondicionesMedicas());
            training.setPreferenciaEstiloEntrenamiento(t.getPreferenciaEstiloEntrenamiento());
            training.setHorasSueno(t.getHorasSueno());
        });

        trainingRepository.save(training);
    }

    public Optional<FullDataDTO> getData(String id) {
        return userRepository.findById(id).map(userEntity -> {
            UserDTO userDTO = mapToUserDTO(userEntity);
            DietDTO dietDTO = mapToDietDTO(dietRepository.findByUser(userEntity));
            TrainingDTO trainingDTO = mapToTrainingDTO(trainingRepository.findByUser(userEntity));

            FullDataDTO fullDataDTO = new FullDataDTO();
            fullDataDTO.setUser(userDTO);
            fullDataDTO.setDiet(dietDTO);
            fullDataDTO.setTraining(trainingDTO);
            return fullDataDTO;
        });
    }

    private UserDTO mapToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setAltura(userEntity.getAltura());
        userDTO.setEdad(userEntity.getEdad());
        userDTO.setSexo(userEntity.getSexo());
        userDTO.setPesoActualizado(userEntity.getPesoActualizado());
        userDTO.setPesoInicial(userEntity.getPesoInicial());
        userDTO.setEstiloVida(userEntity.getEstiloVida());
        userDTO.setSuplementos(userEntity.getSuplementos());
        userDTO.setObjetivo(userEntity.getObjetivo());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        return userDTO;
    }

    private DietDTO mapToDietDTO(Diet diet) {
        DietDTO dietDTO = new DietDTO();
        if (diet != null) {
            dietDTO.setAlergias(diet.getAlergias());
            dietDTO.setIntolerancias(diet.getIntolerancias());
            dietDTO.setNumeroComidas(diet.getNumeroComidas());
            dietDTO.setAlimentosPreferidos(diet.getAlimentosPreferidos());
            dietDTO.setAlimentosEvitar(diet.getAlimentosEvitar());
            dietDTO.setPreferenciaAlimentaria(diet.getPreferenciaAlimentaria());
            dietDTO.setDistribucionMacronutrientes(diet.getDistribucionMacronutrientes());
            dietDTO.setDisponibilidadCocinar(diet.getDisponibilidadCocinar());
            dietDTO.setPreferenciasCoccion(diet.getPreferenciasCoccion());
        }
        return dietDTO;
    }

    private TrainingDTO mapToTrainingDTO(Training training) {
        TrainingDTO trainingDTO = new TrainingDTO();
        if (training != null) {
            trainingDTO.setEjercicioDiario(training.getEjercicioDiario());
            trainingDTO.setCantidadEjerciciosPorDia(training.getCantidadEjerciciosPorDia());
            trainingDTO.setFrecuenciaEntrenamientoSemanal(training.getFrecuenciaEntrenamientoSemanal());
            trainingDTO.setTiempoDisponiblePorDia(training.getTiempoDisponiblePorDia());
            trainingDTO.setNivelExperiencia(training.getNivelExperiencia());
            trainingDTO.setPreferenciaEjercicios(training.getPreferenciaEjercicios());
            trainingDTO.setEquipamientoDisponible(training.getEquipamientoDisponible());
            trainingDTO.setAreasMejorar(training.getAreasMejorar());
            trainingDTO.setLesionesLimitaciones(training.getLesionesLimitaciones());
            trainingDTO.setTipoCuerpo(training.getTipoCuerpo());
            trainingDTO.setPreferenciaIndoorOutdoor(training.getPreferenciaIndoorOutdoor());
            trainingDTO.setCondicionesMedicas(training.getCondicionesMedicas());
            trainingDTO.setPreferenciaEstiloEntrenamiento(training.getPreferenciaEstiloEntrenamiento());
            trainingDTO.setHorasSueno(training.getHorasSueno());
        }
        return trainingDTO;
    }
}
