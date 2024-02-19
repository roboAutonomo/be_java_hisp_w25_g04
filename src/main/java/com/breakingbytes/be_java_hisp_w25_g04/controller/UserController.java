package com.breakingbytes.be_java_hisp_w25_g04.controller;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowedDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowersDTO;
import com.breakingbytes.be_java_hisp_w25_g04.service.IUserService;
import com.breakingbytes.be_java_hisp_w25_g04.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followUser(@PathVariable("userId") int userId, @PathVariable("userIdToFollow") int userIdToFollow) {
        userService.follow(userId, userIdToFollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
