package com.cardiosense.cardiosense.DTO.UserInfoDTO;

import com.cardiosense.cardiosense.DTO.UserDTO;
import lombok.Data;

@Data
public class FullDataDTO {
    private UserDTO user;
    private DietDTO diet;
    private TrainingDTO training;
}
