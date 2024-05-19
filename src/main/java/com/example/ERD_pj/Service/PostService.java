package com.example.ERD_pj.Service;

import com.example.ERD_pj.DTO.PostDTO;

import java.util.List;


public interface PostService {

  List<PostDTO> getAllPosts();
  PostDTO getPostById(Long id);
  PostDTO createPost(PostDTO postDTO);
  PostDTO updatePost(Long id, PostDTO postDTO);
  void deletePost(Long id);

}
