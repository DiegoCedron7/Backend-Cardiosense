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

            if (fullData.getUser().getObjetivo() != null) {
                userEntity.setObjetivo(fullData.getUser().getObjetivo());
            }
            if (fullData.getUser().getSuplementos() != null) {
                userEntity.setSuplementos(fullData.getUser().getSuplementos());
            }
            if (fullData.getUser().getEstiloVida() != null) {
                userEntity.setEstiloVida(fullData.getUser().getEstiloVida());
            }
            if (fullData.getUser().getPesoInicial() != 0) {
                userEntity.setPesoInicial(fullData.getUser().getPesoInicial());
            }
            if (fullData.getUser().getPesoActualizado() != 0) {
                userEntity.setPesoActualizado(fullData.getUser().getPesoActualizado());
            }
            if (fullData.getUser().getAltura() != 0) {
                userEntity.setAltura(fullData.getUser().getAltura());
            }
            if (fullData.getUser().getEdad() != 0) {
                userEntity.setEdad(fullData.getUser().getEdad());
            }
            if (fullData.getUser().getSexo() != null) {
                userEntity.setSexo(fullData.getUser().getSexo());
            }
        }

        userRepository.save(userEntity);

        // Diet
        Diet diet = dietRepository.findByUser(userEntity);
        if (diet == null) {
            diet = new Diet();
            diet.setUser(userEntity);
        }

        diet.setAlergias(fullData.getDiet().getAlergias());
        diet.setIntolerancias(fullData.getDiet().getIntolerancias());
        diet.setNumeroComidas(fullData.getDiet().getNumeroComidas());
        diet.setAlimentosPreferidos(fullData.getDiet().getAlimentosPreferidos());
        diet.setAlimentosEvitar(fullData.getDiet().getAlimentosEvitar());
        diet.setPreferenciaAlimentaria(fullData.getDiet().getPreferenciaAlimentaria());
        diet.setDistribucionMacronutrientes(fullData.getDiet().getDistribucionMacronutrientes());
        diet.setDisponibilidadCocinar(fullData.getDiet().getDisponibilidadCocinar());
        diet.setPreferenciasCoccion(fullData.getDiet().getPreferenciasCoccion());
        dietRepository.save(diet);

        // Training
        Training training = trainingRepository.findByUser(userEntity);
        if (training == null) {
            training = new Training();
            training.setUser(userEntity);
        }

        training.setEjercicioDiario(fullData.getTraining().getEjercicioDiario());
        training.setCantidadEjerciciosPorDia(fullData.getTraining().getCantidadEjerciciosPorDia());
        training.setFrecuenciaEntrenamientoSemanal(fullData.getTraining().getFrecuenciaEntrenamientoSemanal());
        training.setTiempoDisponiblePorDia(fullData.getTraining().getTiempoDisponiblePorDia());
        training.setNivelExperiencia(fullData.getTraining().getNivelExperiencia());
        training.setPreferenciaEjercicios(fullData.getTraining().getPreferenciaEjercicios());
        training.setEquipamientoDisponible(fullData.getTraining().getEquipamientoDisponible());
        training.setAreasMejorar(fullData.getTraining().getAreasMejorar());
        training.setLesionesLimitaciones(fullData.getTraining().getLesionesLimitaciones());
        training.setTipoCuerpo(fullData.getTraining().getTipoCuerpo());
        training.setPreferenciaIndoorOutdoor(fullData.getTraining().getPreferenciaIndoorOutdoor());
        training.setCondicionesMedicas(fullData.getTraining().getCondicionesMedicas());
        training.setPreferenciaEstiloEntrenamiento(fullData.getTraining().getPreferenciaEstiloEntrenamiento());
        training.setHorasSueno(fullData.getTraining().getHorasSueno());


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

            userDTO.setAltura(userEntity.getAltura());
            userDTO.setEdad(userEntity.getEdad());
            userDTO.setSexo(userEntity.getSexo());
            userDTO.setPesoActualizado(userEntity.getPesoActualizado());
            userDTO.setPesoInicial(userEntity.getPesoInicial());
            userDTO.setEstiloVida(userEntity.getEstiloVida());
            userDTO.setSuplementos(userEntity.getSuplementos());
            userDTO.setObjetivo(userEntity.getObjetivo());
            userDTO.setName(userEntity.getName());
            userDTO.setDocumentType(userEntity.getDocumentType());
            userDTO.setAddress(userEntity.getAddress());
            userDTO.setPhone(userEntity.getPhone());
            userDTO.setDocumentNumber(userEntity.getDocumentNumber());
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setLastname(userEntity.getLastname());


            Diet diet = dietRepository.findByUser(userEntity);
            DietDTO dietDTO = new DietDTO();
            dietDTO.setAlergias(diet.getAlergias());
            dietDTO.setIntolerancias(diet.getIntolerancias());
            dietDTO.setNumeroComidas(diet.getNumeroComidas());
            dietDTO.setAlimentosPreferidos(diet.getAlimentosPreferidos());
            dietDTO.setAlimentosEvitar(diet.getAlimentosEvitar());
            dietDTO.setPreferenciaAlimentaria(diet.getPreferenciaAlimentaria());
            dietDTO.setDistribucionMacronutrientes(diet.getDistribucionMacronutrientes());
            dietDTO.setDisponibilidadCocinar(diet.getDisponibilidadCocinar());
            dietDTO.setPreferenciasCoccion(diet.getPreferenciasCoccion());


            Training training = trainingRepository.findByUser(userEntity);
            TrainingDTO trainingDTO = new TrainingDTO();
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


            FullDataDTO fullDataDTO = new FullDataDTO();
            fullDataDTO.setUser(userDTO);
            fullDataDTO.setDiet(dietDTO);
            fullDataDTO.setTraining(trainingDTO);

            return Optional.of(fullDataDTO);
        }
        return Optional.empty();
    }

}
