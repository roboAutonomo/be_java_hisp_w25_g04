package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowersDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.repository.UserRepositoryImpl;
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
    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("T-0003: Permite continuar con normalidad con el ordenamiento: name_asc")
    void getUsersFollowersOfOrderAscTestOk(){
        // Arrange
        String orderParam = "name_asc";
        Integer idUserParam = 2;
        User userExpected = new User(); // agregar lo que devuelve
        // Act
        when(userRepository.findById(idUserParam)).thenReturn(Optional.of(userExpected));
        UserFollowersDTO response = userService.getUsersFollowersOf(idUserParam, orderParam);
        // Assert
        Assertions.assertFalse(response.getFollowers().isEmpty());
    }

    @Test
    @DisplayName("T-0003: Permite continuar con normalidad con el ordenamiento: name_desc")
    void getUsersFollowersOfOrderDescTestOk(){
        // Arrange
        // Act
        // Assert
    }

    @Test
    @DisplayName("T-0003: Permite continuar con normalidad sin ordenamiento")
    void getUsersFollowersOfTestOk(){
        // Arrange
        // Act
        // Assert
    }

    @Test
    @DisplayName("T-0003: Notifica la no existencia mediante una excepcion")
    void getUsersFollowersOfTestSad(){
        // Arrange
        // Act
        // Assert
    }



}
