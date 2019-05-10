package app.service.Impl;


import app.repository.User;
import app.repository.UserDao;
import app.service.UserService;
import app.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<UserVo> getUsers() {
        List<UserVo> users = new ArrayList<>();

        for(User u: userDao.findAll()){
            UserVo user = new UserVo(u.getUsername(), u.getEmail());
            users.add(user);
        }
        return users;
    }
}
