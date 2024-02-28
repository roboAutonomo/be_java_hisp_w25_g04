package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.UserFollowersDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
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
    @Mock
    Mapper mapper;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("T-0003: Permite continuar con normalidad con el ordenamiento: name_asc")
    void getUsersFollowersOfOrderAscTestOk(){
        // Arrange
        String orderParam = "name_asc";
        Integer idUserParam = 2;
        Seller userExpected = FactoryUsers.getInstance().getSellerByName("Robert");
        // Act
        when(sellerRepository.findById(idUserParam)).thenReturn(Optional.of(userExpected));
        when(mapper.modelMapper().map(u1, UserDTO.class)).thenReturn();
        when(mapper.modelMapper().map(u2, UserDTO.class)).thenReturn();
        UserFollowersDTO response = userService.getUsersFollowersOf(idUserParam, orderParam);
        // Assert
        Assertions.assertDoesNotThrow(() -> response);
    }

    @Test
    @DisplayName("T-0003: Permite continuar con normalidad con el ordenamiento: name_desc")
    void getUsersFollowersOfOrderDescTestOk(){
        // Arrange
        String orderParam = "name_desc";
        Integer idUserParam = 2;
        Seller userExpected = FactoryUsers.getInstance().getSellerByName("Robert");
        // Act
        when(sellerRepository.findById(idUserParam)).thenReturn(Optional.of(userExpected));
        when(mapper.modelMapper().map(u1, UserDTO.class)).thenReturn();
        when(mapper.modelMapper().map(u2, UserDTO.class)).thenReturn();
        UserFollowersDTO response = userService.getUsersFollowersOf(idUserParam, orderParam);
        // Assert
        Assertions.assertDoesNotThrow(() -> response);
    }

    @Test
    @DisplayName("T-0003: Permite continuar con normalidad sin ordenamiento")
    void getUsersFollowersOfTestOk(){
        // Arrange
        String orderParam = "";
        Integer idUserParam = 2;
        Seller userExpected = FactoryUsers.getInstance().getSellerByName("Robert");
        // Act
        when(sellerRepository.findById(idUserParam)).thenReturn(Optional.of(userExpected));
        when(mapper.modelMapper().map(u1, UserDTO.class)).thenReturn();
        when(mapper.modelMapper().map(u2, UserDTO.class)).thenReturn();
        UserFollowersDTO response = userService.getUsersFollowersOf(idUserParam, orderParam);
        // Assert
        Assertions.assertDoesNotThrow(() -> response);
    }

    @Test
    @DisplayName("T-0003: Notifica la no existencia mediante una excepcion")
    void getUsersFollowersOfTestSad(){
        // Arrange
        String orderParam = "";
        Integer idUserParam = 2;
        Seller userExpected = FactoryUsers.getInstance().getSellerByName("Robert");
        // Act
        when(sellerRepository.findById(idUserParam)).thenReturn(Optional.of(userExpected));
        when(mapper.modelMapper().map(u1, UserDTO.class)).thenReturn();
        when(mapper.modelMapper().map(u2, UserDTO.class)).thenReturn();
        UserFollowersDTO response = userService.getUsersFollowersOf(idUserParam, orderParam);
        // Assert
        Assertions.assertDoesNotThrow(() -> response);
    }



}
