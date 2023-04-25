package com.ateam.lionbuy.service;

import java.util.List;

import com.ateam.lionbuy.dto.UserDTO;
import com.ateam.lionbuy.entity.UserInfo;

public interface UserService {
    
    String join(UserDTO userdto);

    String login(String user_email);

    List<UserDTO> user_all();

    default UserInfo user_build_entity(UserDTO userdto) {
        UserInfo user_info = UserInfo.builder()
            .userEmail(userdto.getUserEmail())
            .userPw(userdto.getUserPw())
            .userName(userdto.getUserName())
            .userGender(userdto.getUserGender())
            .userBirth(userdto.getUserBirth())
            .build();
        return user_info;
    }

    default UserDTO user_build_dto(UserInfo userInfo) {
        UserDTO userDTO = UserDTO.builder()
            .userNum(userInfo.getUserNum())
            .userEmail(userInfo.getUserEmail())
            .userPw(userInfo.getUserPw())
            .userName(userInfo.getUserName())
            .userGender(userInfo.getUserGender())
            .userBirth(userInfo.getUserBirth())
            .joinDate(userInfo.getJoinDate())
            .build();
        return userDTO;
    }
}
