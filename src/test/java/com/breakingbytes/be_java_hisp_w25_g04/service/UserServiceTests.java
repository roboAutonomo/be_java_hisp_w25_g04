package com.breakingbytes.be_java_hisp_w25_g04.service;

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
