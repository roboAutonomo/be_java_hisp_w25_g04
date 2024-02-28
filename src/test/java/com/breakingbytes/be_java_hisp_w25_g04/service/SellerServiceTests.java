package com.breakingbytes.be_java_hisp_w25_g04.service;


import com.breakingbytes.be_java_hisp_w25_g04.dto.response.LastPostsDTO;

import com.breakingbytes.be_java_hisp_w25_g04.entity.Product;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.repository.UserRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;


import com.breakingbytes.be_java_hisp_w25_g04.dto.response.LastPostsDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.repository.UserRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.LastPostsDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.BadRequestException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.ISellerRepository;
import com.breakingbytes.be_java_hisp_w25_g04.repository.SellerRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.repository.UserRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;

import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;

import com.breakingbytes.be_java_hisp_w25_g04.repository.SellerRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;
import org.junit.jupiter.api.Assertions;
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

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Optional;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTests {
  
  @Mock
    private SellerRepositoryImpl sellerRepository;
    @Mock
    UserRepositoryImpl userRepository;
    @InjectMocks
    private SellerServiceImpl sellerService;

    @Test
    @DisplayName("T-0008 Posts fecha valida Ordenados Por Fecha Ascendente")
    void postOrderedByAscDateTestOk() {
        //ARRANGE
        User user = FactoryUsers.getInstance().getListOfUsers().get(0); //Primer usuario con Id 1
        user.getFollowing().get(0).setPosts(FactoryUsers.getInstance().getPostsWithoutOrder());

        //Se agrega Post con fecha vieja mayor a 2 semanas
        Post p1 = new Post(3, LocalDate.now().minusWeeks(6), new Product(), 100, 2000.0);
        p1.setPostId(5);
        user.getFollowing().get(0).getPosts().add(p1);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        LastPostsDTO expected = new LastPostsDTO(user.getId(),
                FactoryUsers.getInstance().convertPostToResponsePostDTO(
                        FactoryUsers.getInstance().getPostsWithoutOrder()));
        //ACT

        LastPostsDTO result = sellerService.getPostOfVendorsFollowedByUser(user.getId(),"");
  //ASSERT

        assertEquals(expected, result);
    }      
    
    @Test
    @DisplayName("T-0006 Posts Ordenados Por Fecha Ascendente")
    void postOrderedByAscDateTestOk() {
        //ARRANGE
        String orderParam = "date_asc";
        User user = new User();
        user.setId(1);
        user.setFollowing(List.of(new Seller()));
        user.getFollowing().get(0).setId(3);
        user.getFollowing().get(0).setPosts(FactoryUsers.getInstance().getPostsWithoutOrder());

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        LastPostsDTO expected = new LastPostsDTO(user.getId(),
                FactoryUsers.getInstance().convertPostToResponsePostDTO(FactoryUsers.getInstance().getPostsDateAsc()));
        //ACT

        LastPostsDTO result = sellerService.getPostOfVendorsFollowedByUser(user.getId(), orderParam);
        //ASSERT

        assertEquals(expected, result);
    }


    @Test
    @DisplayName("T-0006 Posts Ordenados Por Fecha Descendente")
    void postOrderedByDescDateTestOk(){
        //ARRANGE
        String orderParam = "date_desc";
        User user = new User();
        user.setId(1);
        user.setFollowing(List.of(new Seller()));
        user.getFollowing().get(0).setId(3);
        user.getFollowing().get(0).setPosts(FactoryUsers.getInstance().getPostsWithoutOrder());

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        LastPostsDTO expected = new LastPostsDTO(user.getId(),
                FactoryUsers.getInstance().convertPostToResponsePostDTO(FactoryUsers.getInstance().getPostsDateDesc()));
        //ACT

        LastPostsDTO result = sellerService.getPostOfVendorsFollowedByUser(user.getId(), orderParam);
  //ASSERT

        assertEquals(expected, result);
    }      


    @Test
    @DisplayName("T-0007 Verifica que la cantidad de seguidores de un determinado usuario sea correcta.")
    public void testGetCountFollowersOfSeller(){
        Integer idUser = 3;
        Seller seller = FactoryUsers.getSellerThree();
        Integer expectedCount= 3;

        when(sellerRepository.findById(idUser)).thenReturn(Optional.of(seller));
        Integer countFollowersResult = sellerService.getCountFollowersOfSeller(idUser).getFollowers_count();

        Assertions.assertEquals(expectedCount, countFollowersResult);
    }

    @Test
    @DisplayName("T-0005: Excepcion ordenamiento incorrecto service")
    public void orderByDateAscExceptionTest() {
        Integer userId = 1;
        String  order = "date_";
        User userExpected = FactoryUsers.getInstance().getUserById(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userExpected));

        assertThrows(BadRequestException.class, () -> sellerService.getPostOfVendorsFollowedByUser(userId, order));
    }

    @Test
    @DisplayName("T-0005: Correcto ordenamiento descendente (date_desc)")
    public void orderByDateDescOkTest() {
        Integer userId = 1;
        String order = "date_desc";
        User userExpected = FactoryUsers.getInstance().getUserById(userId);

        LastPostsDTO expectedLastPostsDTO = FactoryUsers.getInstance().generateLastPostDto();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userExpected));

        LastPostsDTO currentLastPostDto = sellerService.getPostOfVendorsFollowedByUser(userId, order);

        verify(userRepository, atLeast(1)).findById(userId);

        assertThat(expectedLastPostsDTO).isEqualTo(currentLastPostDto);
    }

    @Test
    @DisplayName("T-0005: Correcto ordenamiento descendente (date_desc)")
    public void orderByDateAScOkTest() {
        Integer userId = 1;
        String order = "date_asc";
        User userExpected = FactoryUsers.getInstance().getUserById(userId);

        LastPostsDTO expectedLastPostsDTO = FactoryUsers.getInstance().generateLastPostDto();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userExpected));

        LastPostsDTO currentLastPostDto = sellerService.getPostOfVendorsFollowedByUser(userId, order);

        verify(userRepository, atLeast(1)).findById(userId);

        assertThat(expectedLastPostsDTO).isEqualTo(currentLastPostDto);
    }  
  
  
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
