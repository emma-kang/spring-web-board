package org.ekang.webboard;
import org.ekang.webboard.models.Users;
import org.ekang.webboard.service.UserService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

    private UserService userService;

    @Autowired
    public void setUserService(@Qualifier("userService") UserService us){
        this.userService = us;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String mainPage(){
        return "index";
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new Users());
        model.addAttribute("listUsers", this.userService.listUsers());
        return "userList";
    }

}
