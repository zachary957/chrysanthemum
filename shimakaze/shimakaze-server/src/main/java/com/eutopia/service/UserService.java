package com.eutopia.service;

import com.eutopia.domain.UserInfoDTO;

public interface UserService {

    Integer register(UserInfoDTO userInfoDTO);

    String login(UserInfoDTO userInfoDTO);
}
