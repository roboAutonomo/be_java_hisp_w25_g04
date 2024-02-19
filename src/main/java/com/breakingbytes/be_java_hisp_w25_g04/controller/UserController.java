package com.breakingbytes.be_java_hisp_w25_g04.controller;
import com.breakingbytes.be_java_hisp_w25_g04.dto.request.PostDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowedDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowersDTO;
import com.breakingbytes.be_java_hisp_w25_g04.service.IUserService;
import com.breakingbytes.be_java_hisp_w25_g04.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    IUserService userService;


    @PostMapping("/products/post")
    public ResponseEntity<?> addPost(@RequestBody PostDTO postDTO){
        userService.addPost(postDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<?> getCountFollowers(@PathVariable int userId){
        return new ResponseEntity<>( userService.getCountFollowersOfSeller(userId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<?> getUsersFollowersOf(@PathVariable int userId, @RequestParam(required = false, defaultValue = "") String order){
        return new ResponseEntity<>( userService.getUsersFollowersOf(userId, order), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<?> getUsersFollowed(@PathVariable int userId, @RequestParam(required = false, defaultValue = "") String order){
        return new ResponseEntity<>( userService.getUsersFollowed(userId, order), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followUser(@PathVariable("userId") int userId, @PathVariable("userIdToFollow") int userIdToFollow) {
        userService.follow(userId, userIdToFollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }
      

}
