package com.cardiosense.cardiosense.DTO.User.UserInfoDTO;

import com.cardiosense.cardiosense.DTO.User.UserDTO;
import lombok.Data;

@Data
public class FullDataDTO {
    private UserDTO user;
    private DietDTO diet;
    private TrainingDTO training;
}
