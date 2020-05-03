package org.ekang.webboard.controller;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.ekang.webboard.dto.PostDTO;
import org.ekang.webboard.models.Posts;
import org.ekang.webboard.models.Users;
import org.ekang.webboard.service.PostService;
import org.ekang.webboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class WritePostController {
    private PostDTO postDTO;
    private PostService postService;
    private UserService userService;
    private String errorMsg = null;

    @Autowired
    public void setUserService(@Qualifier("userService") UserService us) { this.userService = us; }

    @Autowired
    public void setPostService(@Qualifier("postService") PostService ps) { this.postService = ps; }

    @RequestMapping(value = "/writepost", method = RequestMethod.POST)
    public String writeNewPost(@ModelAttribute final PostDTO postDTO, Model model) {
        this.postDTO = postDTO;
        Integer userid = getUserIdByName(postDTO.getUsername(), postDTO.getPasswords());

        if(userid == null){
            userid = createNewUsers(postDTO.getUsername(), postDTO.getPasswords());
        }

        if (errorMsg != null) {
            model.addAttribute("errorMsg", errorMsg);
            return "thymeleaf/writePost";
        }

        createNewPost(userid);

        return "redirect:/";
    }

    private void createNewPost(Integer userid) {
        Posts post = new Posts();

        post.setUserid(userid);
        post.setTitle(postDTO.getTitle());
        post.setBody(postDTO.getPostbody());
        post.setPostingDate(Calendar.getInstance().getTime());

        this.postService.addPost(post);

    }

    private Integer createNewUsers(String username, String passwords) {
        Date today = Calendar.getInstance().getTime();
        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setPasswords(passwords);
        newUser.setCreatedate(today);
        this.userService.addUser(newUser);

        return newUser.getUserid();
    }

    private Integer getUserIdByName(String username, String password) {
        Users existUser = this.userService.getUserByName(username);
        Integer userId = null;

        if(existUser != null){
            userId = existUser.getUserid();
            if(!existUser.getPasswords().equals(password)) {
                errorMsg = "Passwords not match";
            }
        }

        return userId;
    }

}
