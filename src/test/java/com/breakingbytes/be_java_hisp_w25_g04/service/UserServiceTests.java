package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowedDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowersDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.BadRequestException;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.SellerRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.repository.UserRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;
import com.breakingbytes.be_java_hisp_w25_g04.utils.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    UserRepositoryImpl userRepository;
    @Mock
    SellerRepositoryImpl sellerRepository;
    @InjectMocks
    UserServiceImpl userService;

    // ------------- Tests para getUsersFollowersOf

    @Test
    @DisplayName("T-0003/getUsersFollowersOf(): Permite continuar con normalidad con el ordenamiento: name_asc")
    void getUsersFollowersOfOrderAscTestOk(){
        // Arrange
        String orderParam = "name_asc";
        Integer idUserParam = 4;
        Seller userExpected = FactoryUsers.getInstance().getSellerByName("Robert");
        // Act
        when(sellerRepository.findById(idUserParam)).thenReturn(Optional.of(userExpected));
        UserFollowersDTO response = userService.getUsersFollowersOf(idUserParam, orderParam);
        // Assert
        Assertions.assertDoesNotThrow(() -> response);
    }

    @Test
    @DisplayName("T-0003/getUsersFollowersOf(): Permite continuar con normalidad con el ordenamiento: name_desc")
    void getUsersFollowersOfOrderDescTestOk(){
        // Arrange
        String orderParam = "name_desc";
        Integer idUserParam = 4;
        Seller userExpected = FactoryUsers.getInstance().getSellerByName("Robert");
        // Act
        when(sellerRepository.findById(idUserParam)).thenReturn(Optional.of(userExpected));
        UserFollowersDTO response = userService.getUsersFollowersOf(idUserParam, orderParam);
        // Assert
        Assertions.assertDoesNotThrow(() -> response);
    }

    @Test
    @DisplayName("T-0003/getUsersFollowersOf(): Permite continuar con normalidad sin ordenamiento")
    void getUsersFollowersOfDisorderlyTestOk(){
        // Arrange
        String orderParam = "";
        Integer idUserParam = 4;
        Seller userExpected = FactoryUsers.getInstance().getSellerByName("Robert");
        // Act
        when(sellerRepository.findById(idUserParam)).thenReturn(Optional.of(userExpected));
        UserFollowersDTO response = userService.getUsersFollowersOf(idUserParam, orderParam);
        // Assert
        Assertions.assertDoesNotThrow(() -> response);
    }

    @Test
    @DisplayName("T-0003/getUsersFollowersOf(): Notifica la no existencia mediante una excepcion")
    void getUsersFollowersOfOrderTestSad(){
        // Arrange
        String orderParam = "ascendente";
        Integer idUserParam = 4;
        Seller userExpected = FactoryUsers.getInstance().getSellerByName("Robert");
        // Act
        when(sellerRepository.findById(idUserParam)).thenReturn(Optional.of(userExpected));
        // Assert
        Assertions.assertThrows(BadRequestException.class, () -> userService.getUsersFollowersOf(idUserParam, orderParam));
    }

    // ------------ Test para getUsersFollowed

    @Test
    @DisplayName("T-0003/getUsersFollowed(): Permite continuar con normalidad con el ordenamiento: name_asc")
    void getUsersFollowedOrderAscTestOk(){
        // Arrange
        String orderParam = "name_asc";
        Integer idUserParam = 1;
        User userExpected = FactoryUsers.getInstance().getUserByName("Pepe");
        // Act
        when(userRepository.findById(idUserParam)).thenReturn(Optional.of(userExpected));
        when(sellerRepository.findById(idUserParam)).thenReturn(Optional.empty());
        UserFollowedDTO response = userService.getUsersFollowed(idUserParam, orderParam);
        // Assert
        Assertions.assertDoesNotThrow(() -> response);
    }

    @Test
    @DisplayName("T-0003/getUsersFollowed(): Permite continuar con normalidad con el ordenamiento: name_desc")
    void getUsersFollowedOrderDescTestOk(){
        // Arrange
        String orderParam = "name_desc";
        Integer idUserParam = 1;
        User userExpected = FactoryUsers.getInstance().getUserByName("Pepe");
        // Act
        when(userRepository.findById(idUserParam)).thenReturn(Optional.of(userExpected));
        when(sellerRepository.findById(idUserParam)).thenReturn(Optional.empty());
        UserFollowedDTO response = userService.getUsersFollowed(idUserParam, orderParam);
        // Assert
        Assertions.assertDoesNotThrow(() -> response);
    }

    @Test
    @DisplayName("T-0003/getUsersFollowed(): Permite continuar con normalidad sin ordenamiento")
    void getUsersFollowedDisorderlyTestOk(){
        // Arrange
        String orderParam = "";
        Integer idUserParam = 1;
        User userExpected = FactoryUsers.getInstance().getUserByName("Pepe");
        // Act
        when(userRepository.findById(idUserParam)).thenReturn(Optional.of(userExpected));
        when(sellerRepository.findById(idUserParam)).thenReturn(Optional.empty());
        UserFollowedDTO response = userService.getUsersFollowed(idUserParam, orderParam);
        // Assert
        Assertions.assertDoesNotThrow(() -> response);
    }

    @Test
    @DisplayName("T-0003/getUsersFollowed(): Notifica la no existencia mediante una excepcion")
    void getUsersFollowedOrderTestSad(){
        // Arrange
        String orderParam = "cualquier_cosa";
        Integer idUserParam = 1;
        User userExpected = FactoryUsers.getInstance().getUserByName("Pepe");
        // Act
        when(userRepository.findById(idUserParam)).thenReturn(Optional.of(userExpected));
        when(sellerRepository.findById(idUserParam)).thenReturn(Optional.empty());
        // Assert
        Assertions.assertThrows(BadRequestException.class, () -> userService.getUsersFollowed(idUserParam, orderParam));
    }



}
