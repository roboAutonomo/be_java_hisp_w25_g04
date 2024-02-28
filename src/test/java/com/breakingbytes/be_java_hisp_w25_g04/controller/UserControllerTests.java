package com.breakingbytes.be_java_hisp_w25_g04.controller;

import com.breakingbytes.be_java_hisp_w25_g04.service.ISellerService;
import com.breakingbytes.be_java_hisp_w25_g04.service.IUserService;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.repository.SellerRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @Mock
    UserServiceImpl userService;
    @Mock
    ISellerService sellerService;
    @InjectMocks
    UserController userController;

    @Test
    @DisplayName("T-0002 - Validar que se llamen las funciones del controller - OK")
    void unfollowUserTest(){
        //Arrange
        Integer userToUnfollowID = 1;
        Integer userID = 2;

        //Act
        userController.unfollowUser(userID, userToUnfollowID);

        //Assert
        verify(sellerService, atLeastOnce()).quitFollower(userToUnfollowID, userID);
        verify(userService, atLeastOnce()).unfollowUser(userID, userToUnfollowID);
    }
  
    @Test
    @DisplayName("T-0001 - followUserTestOk")
    public void followUserTestOk() {
        // arrange
        Integer userId = 1;
        Integer userIdToFollow = 2;

        // act
        ResponseEntity<?> response = userController.followUser(userId, userIdToFollow);

        // assert
        verify(userService, atLeastOnce()).follow(userId, userIdToFollow);
        assertEquals(response.getStatusCode(), HttpStatus.OK);


    }
}
