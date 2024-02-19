package com.breakingbytes.be_java_hisp_w25_g04.controller;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UnfollowResponseDTO;
import com.breakingbytes.be_java_hisp_w25_g04.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollowUser(@PathVariable("userId") String userId,
                                                            @PathVariable("userIdToUnfollow") String userIdToUnfollow) {
        return new ResponseEntity<>(userService.unfollowUser(userId, userIdToUnfollow), HttpStatus.OK);
    }
}
