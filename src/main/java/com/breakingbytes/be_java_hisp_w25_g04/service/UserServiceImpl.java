package com.breakingbytes.be_java_hisp_w25_g04.service;

import com.breakingbytes.be_java_hisp_w25_g04.dto.response.LastPostsDto;
import com.breakingbytes.be_java_hisp_w25_g04.dto.response.PostDto;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Post;
import com.breakingbytes.be_java_hisp_w25_g04.entity.Seller;
import com.breakingbytes.be_java_hisp_w25_g04.entity.User;
import com.breakingbytes.be_java_hisp_w25_g04.exception.NotFoundException;
import com.breakingbytes.be_java_hisp_w25_g04.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    IUserRepository userRepository;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public LastPostsDto getPostOfVendorsFollowedByUser(int id, String order) {
        Optional<User> opt = this.userRepository.findById(id);
        if (opt.isEmpty()) throw new NotFoundException("No se encuentra el id buscado");
        User user = opt.get();

        List<PostDto> posts = new ArrayList<>();


        for (Seller s : user.getFollowing()){
            for (Post p : s.getPosts()){
                if(!p.getDate().isBefore(LocalDate.now().minusWeeks(2))){
                    posts.add( new PostDto(s.getId(),
                            p.getId(),
                            p.getDate(),
                            p.getProduct(),
                            p.getCategory(),
                            p.getPrice()));
                }
            }
        }
        if(posts.isEmpty()) throw new NotFoundException("No hay publicaciones que cumplan con el requisito");

        switch (order){
           case "date_asc" -> posts.sort(Comparator.comparing(PostDto::getDate));
           case "date_desc" -> posts.sort(Comparator.comparing(PostDto::getDate).reversed());
           //default case is already satisfied
       };

        return new LastPostsDto(user.getId(), posts);


    }

}
