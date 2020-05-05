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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.Date;

@Controller
public class WritePostController {
    private PostDTO postDTO;
    private PostService postService;
    private UserService userService;

    @Autowired
    public void setUserService(@Qualifier("userService") UserService us) { this.userService = us; }

    @Autowired
    public void setPostService(@Qualifier("postService") PostService ps) { this.postService = ps; }

    @RequestMapping(value = "/writepost", method = RequestMethod.GET)
    public String writePostPage(Model model) {
        model.addAttribute("postDTO", new PostDTO());
        return "thymeleaf/writePost";
    }

    @RequestMapping(value = "/writepost", method = RequestMethod.POST)
    public String writeNewPost(@ModelAttribute final PostDTO postDTO, final BindingResult bindingResult, final ModelMap model) {
        this.postDTO = postDTO;
        Integer userid = getUserIdByName(postDTO.getUsername(), postDTO.getPasswords());

        if(userid == null) {
            userid = createNewUsers(postDTO.getUsername(), postDTO.getPasswords());
        }

        if (bindingResult.hasErrors() || postDTO.getErrMsg() != null) {
            model.addAttribute("errorMsg", postDTO.getErrMsg());
            return "thymeleaf/writePost";
        }

        createNewPost(userid);

        model.clear();
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
        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setPasswords(passwords);
        newUser.setCreatedate(Calendar.getInstance().getTime());
        this.userService.addUser(newUser);

        return newUser.getUserid();
    }

    private Integer getUserIdByName(String username, String password) {
        Users existUser = this.userService.getUserByName(username);
        Integer userId = null;

        if (existUser != null) {
            if (!existUser.getPasswords().equals(password)){
                postDTO.setErrMsg("Password not match with stored data");
            }
        }

        return userId;
    }

}
