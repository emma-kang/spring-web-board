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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class MainController {
    private UserService userService;
    private PostService postService;

    @Autowired(required = true)
    public void setUserService(@Qualifier("userService") UserService us){
        this.userService = us;
    }

    @Autowired
    public void setPostService(@Qualifier("postService") PostService ps) { this.postService = ps; }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(Model model) {
        model.addAttribute("listPost", this.postService.listPosts());
        return "thymeleaf/main";
    }

}
