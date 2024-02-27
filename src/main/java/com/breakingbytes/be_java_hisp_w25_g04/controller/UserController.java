package com.breakingbytes.be_java_hisp_w25_g04.controller;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.LastPostsDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.request.RequestPostDTO;
import com.breakingbytes.be_java_hisp_w25_g04.service.ISellerService;
import com.breakingbytes.be_java_hisp_w25_g04.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    IUserService userService;
    ISellerService sellerService;
    public UserController(IUserService userService, ISellerService sellerService) {
        this.userService = userService;
        this.sellerService = sellerService;
    }

    @PostMapping("/{user_id}/unfollow/{user_id_to_unfollow}")
    public ResponseEntity<?> unfollowUser(@PathVariable("user_id") String user_id,
                                          @PathVariable("user_id_to_unfollow") String user_id_to_unfollow) {
        sellerService.quitFollower(user_id_to_unfollow, user_id);
        return ResponseEntity.ok()
                .body(userService.unfollowUser(user_id, user_id_to_unfollow));
    }

    @GetMapping("/{user_id}/followers/count")
    public ResponseEntity<?> getCountFollowers(@PathVariable int user_id){
        return ResponseEntity.ok()
                .body(sellerService.getCountFollowersOfSeller(user_id));
    }

    @GetMapping("/{user_id}/followers/list")
    public ResponseEntity<?> getUsersFollowersOf(@PathVariable int user_id, @RequestParam(required = false, defaultValue = "") String order){
        return ResponseEntity.ok()
                .body(userService.getUsersFollowersOf(user_id, order));
    }

    @GetMapping("/{user_id}/followed/list")
    public ResponseEntity<?> getUsersFollowed(@PathVariable int user_id, @RequestParam(required = false, defaultValue = "") String order){
        return ResponseEntity.ok()
                .body(userService.getUsersFollowed(user_id, order));
    }

    @PostMapping("/{user_id}/follow/{user_id_to_follow}")
    public ResponseEntity<?> followUser(@PathVariable("user_id") int user_id, @PathVariable("user_id_to_follow") int user_id_to_follow) {
        userService.follow(user_id, user_id_to_follow);
        return ResponseEntity
                .ok().build();
    }

}
