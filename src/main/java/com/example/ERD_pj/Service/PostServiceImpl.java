package com.example.ERD_pj.Service;

import com.example.ERD_pj.DTO.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
  @Override
  public List<PostDTO> getAllPosts() {
    return null;
  }

  @Override
  public PostDTO getPostById(Long id) {
    return null;
  }

  @Override
  public PostDTO createPost(PostDTO postDTO) {
    return null;
  }

  @Override
  public PostDTO updatePost(Long id, PostDTO postDTO) {
    return null;
  }

  @Override
  public void deletePost(Long id) {

  }
}
