package com.breakingbytes.be_java_hisp_w25_g04.controller;

import com.breakingbytes.be_java_hisp_w25_g04.service.ISellerService;
import com.breakingbytes.be_java_hisp_w25_g04.service.IUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    @Mock
    IUserService userService;
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
}
