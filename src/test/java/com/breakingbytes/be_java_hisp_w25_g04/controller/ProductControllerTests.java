package com.breakingbytes.be_java_hisp_w25_g04.controller;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.LastPostsDTO;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.ResponsePostDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Product;
import com.breakingbytes.be_java_hisp_w25_g04.service.SellerServiceImpl;
import org.junit.jupiter.api.Disabled;

import com.breakingbytes.be_java_hisp_w25_g04.exception.BadRequestException;
import com.breakingbytes.be_java_hisp_w25_g04.service.ISellerService;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {

    @Mock
    ISellerService sellerService;

    @InjectMocks
    ProductController productController;

    @Test
    @DisplayName("T-0005: Correcto ordenamiento ascendente (date_asc)")
    public void orderByDateAscOkTest() {
        Integer userId = 1;
        String order = "date_asc";
        LastPostsDTO lastPostsDTO = FactoryUsers.getInstance().generateLastPostDto();

        ResponseEntity<LastPostsDTO> expectedResponse = new ResponseEntity<>(lastPostsDTO, HttpStatus.OK);

        when(sellerService.getPostOfVendorsFollowedByUser(userId, order)).thenReturn(lastPostsDTO);

        ResponseEntity<LastPostsDTO> currentResponse = productController.getPostOfVendorsFollowedByUser(userId, order);

        verify(sellerService, atLeast(1)).getPostOfVendorsFollowedByUser(userId, order);

        assertThat(expectedResponse).isEqualTo(currentResponse);
    }

    @Test
    @DisplayName("T-0005: Excepcion ordenamiento incorrecto controller")
    public void orderByDateAscExceptionTest() {
        Integer userId = 1;
        String  order = "orden_malo",
                expectedMsg = "El tipo de ordenamiento por fecha es incorrecto";

        when(sellerService.getPostOfVendorsFollowedByUser(userId, order))
                            .thenThrow(new BadRequestException(expectedMsg));

        Exception currentException = assertThrows(BadRequestException.class,
                                                    () -> productController.getPostOfVendorsFollowedByUser(userId, order));

        String currentMsg = currentException.getMessage();

        assertEquals(expectedMsg, currentMsg);
    }

    @Test
    @DisplayName("T-0005: Correcto ordenamiento descendente (date_desc)")
    public void orderByDateDescOkTest() {
        Integer userId = 1;
        String order = "date_desc";
        LastPostsDTO lastPostsDTO = FactoryUsers.getInstance().generateLastPostDto();

        ResponseEntity<LastPostsDTO> expectedResponse = new ResponseEntity<>(lastPostsDTO, HttpStatus.OK);

        when(sellerService.getPostOfVendorsFollowedByUser(userId, order)).thenReturn(lastPostsDTO);

        ResponseEntity<LastPostsDTO> currentResponse = productController.getPostOfVendorsFollowedByUser(userId, order);

        verify(sellerService, atLeast(1)).getPostOfVendorsFollowedByUser(userId, order);

        assertThat(expectedResponse).isEqualTo(currentResponse);
    }
}
