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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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

    @RequestMapping(value = "/writepost", params = {"newpost"})
    public String writeNewPost(final PostDTO postDTO, final BindingResult bindingResult, final ModelMap model) {
        this.postDTO = postDTO;
        createNewPost();

        return "redirect:/";
    }

    private void createNewPost() {
        String username = postDTO.getUsername();
        String passwords = postDTO.getPasswords();
        String title = postDTO.getTitle();
        String postbody = postDTO.getPostbody();

        System.out.println(username + " " + passwords + " " + title + " " + postbody);

        Posts post = new Posts();

        Integer userid = getUserIdByName(username);
        // if there's no same username in the database, create new user
        if(userid == null){
            userid = createNewUsers(username, passwords);
        }

        Date today = Calendar.getInstance().getTime();
        System.out.println(today);

        post.setUserid(userid);
        post.setTitle(title);
        post.setBody(postbody);
        post.setPostingDate(today);

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

    private Integer getUserIdByName(String username) {
        return this.userService.getUserIdByName(username);
    }

}
