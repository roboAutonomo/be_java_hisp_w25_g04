package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.LastPostsDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.repository.UserRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.utils.FactoryUsers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest //Contexto cargado
public class SellerServiceTests {

    @Mock
    private UserRepositoryImpl userRepository;


    @InjectMocks
    private SellerServiceImpl sellerService;


    @Test
    @DisplayName("T-0006 Posts Ordenados Por Fecha Ascendente")
    void postOrderedByAscDateTestOk() {
        //ARRANGE
        String orderParam = "date_asc";
        User user = FactoryUsers.getInstance().getListOfUsers().get(0); //Primer usuario con Id 1
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


}
