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
            .useremail(userdto.getUserEmail())
            .userpw(userdto.getUserPw())
            .username(userdto.getUserName())
            .usergender(userdto.getUserGender())
            .userbirth(userdto.getUserBirth())
            .build();
        return user_info;
    }

    default UserDTO user_build_dto(UserInfo userInfo) {
        UserDTO userDTO = UserDTO.builder()
            .userEmail(userInfo.getUseremail())
            .userPw(userInfo.getUserpw())
            .userName(userInfo.getUsername())
            .userGender(userInfo.getUsergender())
            .userBirth(userInfo.getUserbirth())
            .joinDate(userInfo.getJoindate())
            .build();
        return userDTO;
    }
}
