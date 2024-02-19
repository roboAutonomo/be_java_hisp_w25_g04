package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.PostDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Product;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.exception.BadRequestException;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.DbMock;
import com.breakingbytes.be_java_hisp_w25_g04.repository.IPostRepository;
import com.breakingbytes.be_java_hisp_w25_g04.repository.IProductRepository;
import com.breakingbytes.be_java_hisp_w25_g04.repository.ISellerRepository;

import com.breakingbytes.be_java_hisp_w25_g04.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements ISellerService{

    @Autowired
    ISellerRepository sellerRepository;

    @Autowired
    Mapper mapper;
    @Autowired
    IPostRepository postRepository;
    @Autowired
    IProductRepository productRepository;

    @Override
    public void addPost(PostDTO postDTO) {
        Post post = mapper.modelMapper().map(postDTO, Post.class);
        Optional<Seller> seller = sellerRepository.findById(postDTO.getUserId());
        Optional<Product> product = productRepository.findAll().stream()
                .filter(p -> p.getId() == postDTO.getProduct().getId())
                .findFirst();
        if (product.isPresent()) throw new BadRequestException("Ya existe un producto con ese ID");
        if (seller.isPresent()) sellerRepository.addPost(post, seller.get());
        else throw new NotFoundException("No se ha encontrado un vendedor con ese ID");
    }

    @Override
    public List<PostDTO> findAllPosts() {
        return postRepository.findAll()
                .stream().map(p -> mapper.modelMapper().map(p, PostDTO.class)).toList();
    }

}
