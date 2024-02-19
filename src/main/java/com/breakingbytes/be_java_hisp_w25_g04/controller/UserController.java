package com.breakingbytes.be_java_hisp_w25_g04.controller;

import com.breakingbytes.be_java_hisp_w25_g04.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<?> getCountFollowers(@PathVariable int userId){
        return new ResponseEntity<>( userService.getCountFollowersOfSeller(userId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<?> getFollowersSorted(@PathVariable int userId, @RequestParam String order){
        return new ResponseEntity<>( userService.getFollowersSortedByOrder(userId, order), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<?> getFollowedsSorted(@PathVariable int userId, @RequestParam String order){
        return new ResponseEntity<>( userService.getFollowedsSortedByOrder(userId, order), HttpStatus.OK);
    }
}
