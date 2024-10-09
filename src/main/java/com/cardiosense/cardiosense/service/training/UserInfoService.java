package com.cardiosense.cardiosense.service.training;

import com.cardiosense.cardiosense.model.training.UserInfoEntity;
import com.cardiosense.cardiosense.repository.training.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;


    public UserInfoEntity saveUserInfo(UserInfoEntity userInfo) {
        return userInfoRepository.save(userInfo);
    }

    public UserInfoEntity getUserInfo(String userId) {
        return userInfoRepository.findByUser_Id(userId).orElse(null);
    }

}
