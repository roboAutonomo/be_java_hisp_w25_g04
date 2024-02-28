package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.repository.SellerRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTests {
    @Mock
    private SellerRepositoryImpl sellerRepository;
    @InjectMocks
    private SellerServiceImpl sellerService;

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
}
