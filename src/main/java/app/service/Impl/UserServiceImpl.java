package app.service.Impl;


import app.repository.User;
import app.repository.UserDao;
import app.service.UserService;
import app.vo.response.UserRes;
import app.vo.request.UserReq;
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
    public List<UserRes> getUsers() {
        List<UserRes> users = new ArrayList<>();

        for(User u: userDao.findAll()){
            UserRes user = new UserRes(u.getId(), u.getUsername(), u.getEmail());
            users.add(user);
        }
        return users;
    }

    @Override
    public boolean addUser(UserReq userReq) {
        User user = new User(userReq.getUsername(), userReq.getEmail(), userReq.getPassword());
        userDao.save(user);
        return true;
    }

    @Override
    public boolean deleteUser(String id) {
        userDao.deleteById(new Long(id));
        return true;
    }

    @Override
    public boolean updateUser(UserReq userReq) {
        User user = userDao.findById(userReq.getId()).orElse(null);
        user.setEmail(userReq.getEmail());
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        userDao.save(user);
        return true;
    }
}
