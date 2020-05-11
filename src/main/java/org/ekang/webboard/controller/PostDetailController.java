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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        model.addAttribute("postid", id);
        return "thymeleaf/postDetail";
    }

    @RequestMapping(value = "/details/edit/{id}", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") int id, Model model) {
        PostDTO targetPost = findPostDetails(id);
        targetPost.setPasswords(null); // make empty
        model.addAttribute("target", id);
        model.addAttribute("postDTO", targetPost);
        return "thymeleaf/editPost";
    }

    @RequestMapping(value= "/details/edit/{id}", method = RequestMethod.POST) // need to fix
    public String submitEditPost(@PathVariable("id") int id, @ModelAttribute final PostDTO postDTO, final BindingResult bindingResult, final ModelMap model) {
        PostDTO targetPost = postDTO;
        if(!isValidPasswords(targetPost.getPasswords(), targetPost.getUsername())){
            targetPost.setErrMsg("Passwords is not match with system");
            model.addAttribute("errorMsg", targetPost.getErrMsg());
            model.addAttribute("postDTO", targetPost);
            return "redirect:/details/edit/" + id;
        }

        updatePost(id, targetPost);
        model.clear();

        return "redirect:/";
    }

    private void updatePost(Integer id, PostDTO targetPost) {
        Posts post = new Posts();
        Users user = this.userService.getUserByName(targetPost.getUsername());
        post.setPostid(id);
        post.setTitle(targetPost.getTitle());
        post.setBody(targetPost.getPostbody());
        post.setUserid(user.getUserid());
        this.postService.updatePost(post);
    }

    private boolean isValidPasswords(String passwords, String username) {
        Users user = this.userService.getUserByName(username);
        boolean retValue = false;

        if (user.getPasswords().equals(passwords))
            retValue = true;

        return retValue;
    }

    @RequestMapping(value = "/details/delete/{id}")
    public String deletePost(@PathVariable("id") int id) {
        this.postService.removePost(id);

        return "redirect:/";
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
