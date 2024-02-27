package com.breakingbytes.be_java_hisp_w25_g04.controller;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.LastPostsDTO;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.ResponsePostDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Product;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.service.ISellerService;
import com.breakingbytes.be_java_hisp_w25_g04.utils.Mapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        LastPostsDTO lastPostsDTO = generateLastPostDto();

        ResponseEntity<LastPostsDTO> expectedResponse = new ResponseEntity<>(lastPostsDTO, HttpStatus.OK);

        when(sellerService.getPostOfVendorsFollowedByUser(userId, order)).thenReturn(lastPostsDTO);

        ResponseEntity<LastPostsDTO> currentResponse = productController.getPostOfVendorsFollowedByUser(1, "date_asc");

        verify(sellerService, atLeast(1)).getPostOfVendorsFollowedByUser(userId, order);

        assertThat(expectedResponse).isEqualTo(currentResponse);
    }

    @Test
    @DisplayName("T-0005: Excepcion ordenamiento ascendente (date_asc)")
    public void orderByDateAscErrorTest() {

    }

    @Test
    @DisplayName("T-0005: Correcto ordenamiento descendente (date_desc)")
    public void orderByDateDescOkTest() {

    }

    @Test
    @DisplayName("T-0005: Excepcion ordenamiento descendente (date_asc)")
    public void orderByDateDescErrorTest() {

    }

    private LastPostsDTO generateLastPostDto() {
        User pepe = new User();
        pepe.setName("Pepe");

        Seller juan = new Seller(); // ID: 3
        juan.setName("Juan");
        pepe.addFollowing(juan);
        juan.addFollower(pepe);

        Product p2 = new Product(2, "AAAAAAAA", "Electronics", "Samsung", "Black", "5G capable");
        Product p3 = new Product(3, "Running Shoes", "Apparel", "Nike", "Blue", "Breathable mesh upper");
        Product p4 = new Product(4, "Headphones", "Electronics", "Sony", "Red", "Noise-canceling feature");
        Product p5 = new Product(5, "Backpack", "Accessories", "JanSport", "Gray", "Multiple compartments");

        Post post1 = new Post(3, LocalDate.of(2024,2,20), p2, 100, 1500.0);
        Post post2 = new Post(3, LocalDate.of(2023, 2,20), p3, 100, 1000.0);
        Post post3 = new Post(3, LocalDate.of(2019,2,21), p4, 100, 20.0);
        Post post4 = new Post(3, LocalDate.of(2019,2,26), p5, 300, 30.0);

        juan.getPosts().add(post1);
        juan.getPosts().add(post2);
        juan.getPosts().add(post3);
        juan.getPosts().add(post4);

        List<ResponsePostDTO> postsDto = new ArrayList<>();
        for (Seller s : pepe.getFollowing()) {
            for (Post p : s.getPosts()) {
                if (!p.getDate().isBefore(LocalDate.now().minusWeeks(2))) {
                    ResponsePostDTO responsePostDTO = convertPostToDto(p);
                    responsePostDTO.setUserId(s.getId());
                    postsDto.add(responsePostDTO);
                }
            }
        }

        return new LastPostsDTO(pepe.getId(), postsDto);
    }

    private ResponsePostDTO convertPostToDto(Post post) {
        return new ResponsePostDTO(post.getUserId(), post.getPostId(), post.getDate(), post.getProduct(),
                                    post.getCategory(), post.getPrice());
    }
}
