package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.UserDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.repository.SellerRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.repository.UserRepositoryImpl;
import com.breakingbytes.be_java_hisp_w25_g04.utils.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepositoryImpl userRepository;
    @Mock
    private SellerRepositoryImpl sellerRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("T-0004 Verifica el correcto ordenamiento ascendente por nombre.")
    public void testSortedListFollowersAsc(){
        Integer idUser = 3;
        String order = "name_asc";
        Optional<Seller> seller = UserFactory.getSellerById(idUser);
        List<UserDTO> expectedSortedList= UserFactory.getSortedList();

        when(sellerRepository.findById(3)).thenReturn(seller);

        List<UserDTO> usersResult = userService.getUsersFollowersOf(idUser, order).getFollowers();

        Assertions.assertEquals(expectedSortedList.get(0), usersResult.get(0));
        Assertions.assertEquals(expectedSortedList.get(1), usersResult.get(1));
        Assertions.assertEquals(expectedSortedList.get(2), usersResult.get(2));
    }

    @Test
    @DisplayName("T-0004 Verifica el correcto ordenamiento descendente por nombre.")
    public void testSortedListFollowersDesc(){
    }

    @Test
    public void testSortedListFollowedsAsc(){
    }

    @Test
    public void testSortedListFollowedsDesc(){

    }
}
