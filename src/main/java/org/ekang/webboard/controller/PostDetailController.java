package org.ekang.webboard.controller;

import org.ekang.webboard.dto.PostDTO;
import org.ekang.webboard.models.Posts;
import org.ekang.webboard.models.Users;
import org.ekang.webboard.service.PostService;
import org.ekang.webboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostDetailController {
    private PostService postService;
    private UserService userService;

    @Autowired
    public void setUserService(@Qualifier("userService") UserService us) { this.userService = us; }

    @Autowired
    public void setPostService(@Qualifier("postService") PostService ps) { this.postService = ps; }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String postDetail(@PathVariable("id") int id, Model model) {
        PostDTO postDTO = findPostDetails(id);
        model.addAttribute("postDTO", postDTO);
        return "thymeleaf/postDetail";
    }

    @RequestMapping(value = "/details/{id}/edit", method = RequestMethod.PUT)
    public String editPost(@PathVariable("id") int id, Model model) {
        return "hi";
    }

    @RequestMapping(value = "/details/{id}/delete", method = RequestMethod.DELETE)
    public String deletePost(@PathVariable("id") int id, Model model) {
        return "hi";
    }

    private PostDTO findPostDetails(int id) {
        PostDTO postDTO = new PostDTO();
        Posts post = this.postService.getPostById(id);
        Users owner = this.userService.getUserById(post.getUserid());
        postDTO.setTitle(post.getTitle());
        postDTO.setPostbody(post.getBody());
        postDTO.setUsername(owner.getUsername());
        postDTO.setCreateDate(post.getPostingDate().toString());

        return postDTO;
    }
}
