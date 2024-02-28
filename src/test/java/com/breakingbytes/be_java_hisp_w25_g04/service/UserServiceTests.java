package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.ResponseDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.IUserRepository;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;
import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.BadRequestException;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.SellerRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.repository.UserRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;
import com.breakingbytes.be_java_hisp_w25_g04.utils.Mapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    UserRepositoryImpl userRepository;
    @Mock
    SellerRepositoryImpl sellerRepository;
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

    @Test
    @DisplayName("T-0001: Permite continuar con normalidad.")
    public void followTestOk() {
        // arrange
        Integer userId = 1;
        User user = FactoryUsers.getInstance().getUserById(userId);
        Optional<User> expectedUser = Optional.of(user);

        Integer userIdToFollow = 4;
        Seller seller = (Seller) FactoryUsers.getInstance().getSellerById(userIdToFollow);
        Optional<Seller> expectedSeller = Optional.of(seller);

        when(userRepository.findById(userId)).thenReturn(expectedUser);
        when(sellerRepository.findById(userIdToFollow)).thenReturn(expectedSeller);


        // act
        userService.follow(userId, userIdToFollow);

        // assert
        verify(userRepository, atLeastOnce()).findById(userId);
        verify(sellerRepository, atLeastOnce()).findById(userIdToFollow);
        verify(userRepository, atLeastOnce()).addFollowing(expectedUser.get(), expectedSeller.get());
        verify(sellerRepository, atLeastOnce()).addFollower(expectedSeller.get(), expectedUser.get());
    }

    @Test
    @DisplayName("T-0001: Notifica la no existencia del usuario mediante una excepción.")
    public void followTestUserNotFound() {
        // arrange
        Integer userId = 1;
        User user = FactoryUsers.getInstance().getUserById(userId);
        Optional<User> expectedUser = Optional.of(user);

        when(userRepository.findById(userId)).thenReturn(expectedUser);

        Integer userIdToFollow = 4;
        // act and assert
        Exception exception = assertThrows(NotFoundException.class, () ->
                userService.follow(userId, userIdToFollow));

        verify(userRepository, atLeastOnce()).findById(userId);
        verify(sellerRepository, atLeastOnce()).findById(userIdToFollow);
        assertEquals("Vendedor no encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("T-0001: Notifica la no existencia del vendedor mediante una excepción.")
    public void followTestSellerNotFound() {
        // arrange
        Integer userId = 1;
        Integer userIdToFollow = 3;

        // act and assert
        Exception exception = assertThrows(NotFoundException.class, () ->
                userService.follow(userId, userIdToFollow));

        verify(userRepository, atLeastOnce()).findById(userId);
        verify(sellerRepository, atLeastOnce()).findById(userIdToFollow);
        assertEquals("Ususario no encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("T-0001: Notifica que ya sigue a ese vendedor mediante una excepción.")
    public void followTestSellerBAdRequest() {
        // arrange
        Integer userId = 1;
        User user = FactoryUsers.getInstance().getUserById(userId);
        Optional<User> expectedUser = Optional.of(user);

        Integer userIdToFollow = 3;
        Seller seller = (Seller) FactoryUsers.getInstance().getSellerById(userIdToFollow);
        Optional<Seller> expectedSeller = Optional.of(seller);

        when(userRepository.findById(userId)).thenReturn(expectedUser);
        when(sellerRepository.findById(userIdToFollow)).thenReturn(expectedSeller);


        // act
        Exception exception = assertThrows(BadRequestException.class, () ->
                userService.follow(userId, userIdToFollow));

        // assert
        verify(userRepository, atLeastOnce()).findById(userId);
        verify(sellerRepository, atLeastOnce()).findById(userIdToFollow);
    }

}
