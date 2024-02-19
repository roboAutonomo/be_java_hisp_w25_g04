package com.breakingbytes.be_java_hisp_w25_g04.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Seller extends User{

    @JsonBackReference
    List<User> followers;
    List<Post> posts;

    public Seller(int id, String name, List<Seller> following, List<User> followers, List<Post> posts) {
        super(id, name, following);
        this.followers = followers;
        this.posts = posts;
    }

    public Seller(List<User> followers, List<Post> posts) {
        this.followers = followers;
        this.posts = posts;
    }
}
