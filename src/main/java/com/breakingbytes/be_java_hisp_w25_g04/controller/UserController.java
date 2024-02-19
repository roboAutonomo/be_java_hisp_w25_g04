package com.breakingbytes.be_java_hisp_w25_g04.controller;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowedDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowersDTO;
import com.breakingbytes.be_java_hisp_w25_g04.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/users/{userId}/followers/list")
    public UserFollowersDTO getUsersFollowersOf(@PathVariable int userId) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        return this.userService.getUsersFollowersOf(userDTO);
    };

    @GetMapping("/users/{userId}/followed/list")
    public UserFollowedDTO getUsersFollowed(@PathVariable int userId) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        return this.userService.getUsersFollowed(userDTO);
    };

}
