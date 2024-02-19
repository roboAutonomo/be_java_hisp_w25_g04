package com.breakingbytes.be_java_hisp_w25_g04.controller;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    IUserService userService;

    public List<UserDTO> getUsersFollowersOf(UserDTO userDTO) {
        return this.userService.getUsersFollowersOf(userDTO);
    };
}
