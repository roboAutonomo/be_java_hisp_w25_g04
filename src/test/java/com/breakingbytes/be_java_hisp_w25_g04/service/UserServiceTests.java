package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.ResponseDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.IUserRepository;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    IUserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("T-0002 - Dejar de seguir a un vendedor - OK")
    void unfollowUserTest(){
        //Arrange
        Integer userID = 1;
        Integer userToUnfollow = 2;
        User user = FactoryUsers.getInstance().createUser(userID);
        Seller sellerToUnfollow = FactoryUsers.getInstance().createSeller(userToUnfollow);
        user.addFollowing(sellerToUnfollow);
        ResponseDTO expected = new ResponseDTO("El usuario " + user.getName() + " ha dejado de seguir a: " + sellerToUnfollow.getName());

        when(userRepository.findById(userID)).thenReturn(Optional.of(user));

        //Act
        ResponseDTO actual = userService.unfollowUser(userID, userToUnfollow);

        //Assert
        verify(userRepository).findById(userID);
        verify(userRepository).setUserFollowings(userID, user.getFollowing());

        assertFalse(sellerToUnfollow.getFollowing().contains(sellerToUnfollow));
        assertEquals(expected, actual);

    }
    @Test
    @DisplayName("T-0002 - Dejar de seguir a un vendedor - User NotFound ")
    void unfollowUserNotUserFoundTests(){
        //Arrange
        Integer userID = 1;
        Integer userToUnfollowID = 2;

        when(userRepository.findById(userID)).thenReturn(Optional.empty());

        //Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> userService.unfollowUser(userID, userToUnfollowID)
        );

        assertEquals("Usuario no encontrado.", exception.getMessage());

    }
    @Test
    @DisplayName("T-0002 - Dejar de seguir a un vendedor - Seller NotFound")
    void unfollowUserNotSellerFoundTests(){
        //Arrange
        Integer userID = 1;
        Integer userToUnfollowID = 2;
        User user = FactoryUsers.getInstance().createUser(userID);

        when(userRepository.findById(userID)).thenReturn(Optional.of(user));

        //Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> userService.unfollowUser(userID, userToUnfollowID)
        );

        assertEquals("El usuario que se quiere dejar de seguir no fue encontrado en los seguidos.", exception.getMessage());
    }


}
