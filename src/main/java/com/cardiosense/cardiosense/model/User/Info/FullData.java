package com.cardiosense.cardiosense.model.User.Info;

import com.cardiosense.cardiosense.model.User.UserEntity;
import lombok.Data;

@Data
public class FullData {
    private UserEntity user;
    private Diet diet;
    private Training training;
}
