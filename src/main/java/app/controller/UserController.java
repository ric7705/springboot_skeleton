package app.controller;

import app.service.UserService;
import app.vo.response.UserRes;
import app.vo.request.UserReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<UserRes> getUsers(){

        LOG.info("get users");

        return userService.getUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean addUser(@RequestBody @Valid UserReq user){

        LOG.info("add user");
        return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public boolean deleteUser(@RequestBody String id){

        return userService.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public boolean updaeUser(@RequestBody @Valid UserReq user){

        return userService.updateUser(user);
    }


}
