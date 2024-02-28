package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.LastPostsDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.BadRequestException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.ISellerRepository;
import com.breakingbytes.be_java_hisp_w25_g04.repository.SellerRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.repository.UserRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTests {

    @Mock
    UserRepositoryImpl userRepository;

    @InjectMocks
    SellerServiceImpl sellerService;

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
}
