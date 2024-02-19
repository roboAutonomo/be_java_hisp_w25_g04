package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.request.PostDTO;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.DbMock;
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

    @Override
    public void addPost(PostDTO postDTO) {
        Post post = mapper.modelMapper().map(postDTO, Post.class);
        Optional<Seller> seller = sellerRepository.findById(postDTO.getUserId());
        if (seller.isPresent()) sellerRepository.addPost(post, seller.get());
        else throw new NotFoundException("No se ha encontrado un vendedor con ese ID");
    }

    @Override
    public List<PostDTO> findAllPosts() {
        return DbMock.getInstance().getListOfPost().stream().map(p -> mapper.modelMapper().map(p, PostDTO.class)).toList();
    }
}