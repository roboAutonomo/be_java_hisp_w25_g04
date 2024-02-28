package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.ISellerRepository;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTests {

    @Mock
    ISellerRepository sellerRepository;
    @InjectMocks
    SellerServiceImpl sellerService;

    @Test
    @DisplayName("T-0002 - Elimina un seguidor de un vendedor - OK")
    void quitFollowerTest(){
        //Arrange
        Integer sellerID = 1;
        Integer userID = 2;
        Seller seller = FactoryUsers.getInstance().createSeller(sellerID);
        User user = FactoryUsers.getInstance().createUser(userID);
        seller.addFollower(user);

        when(sellerRepository.findById(sellerID)).thenReturn(Optional.of(seller));

        //Act
        sellerService.quitFollower(sellerID, userID);

        //Assert
        verify(sellerRepository).findById(sellerID);
        verify(sellerRepository).setSellerFollowers(sellerID, seller.getFollowers());

        assertFalse(seller.getFollowers().contains(user));

    }
    @Test
    @DisplayName("T-0002 - Elimina un seguidor de un vendedor - Vendedor NotFound")
    void quitFollowerNotSellerFoundTest(){
        //Arrange
        Integer sellerID = 1;
        Integer userID = 2;

        when(sellerRepository.findById(sellerID)).thenReturn(Optional.empty());

        //Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> sellerService.quitFollower(sellerID, userID)
        );

        assertEquals("No se ha encontrado vendedor con id: " + sellerID, exception.getMessage());
    }

    @Test
    @DisplayName("T-0002 - Elimina un seguidor de un vendedor - Seguidor NotFound")
    void quitFollowerNotUserFoundTest(){
        //Arrange
        Integer sellerID = 1;
        Integer userID = 2;
        Seller seller = FactoryUsers.getInstance().createSeller(sellerID);

        when(sellerRepository.findById(sellerID)).thenReturn(Optional.of(seller));

        //Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> sellerService.quitFollower(sellerID, userID)
        );

        assertEquals("El usuario no se encuentra entre los seguidores.", exception.getMessage());
    }


}
