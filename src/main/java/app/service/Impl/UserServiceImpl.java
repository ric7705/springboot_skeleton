package app.service.Impl;


import app.repository.User;
import app.repository.UserDao;
import app.service.BookService;
import app.service.UserService;
import app.vo.response.UserRes;
import app.vo.request.UserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    BookService bookService;

    @Autowired
    EntityManager entityManager;

    @Override
    public List<UserRes> getUsers() {
        bookService.getBook();
        List<UserRes> users = new ArrayList<>();

        for(User u: userDao.findAll()){
            UserRes user = new UserRes(u.getId(), u.getUsername(), u.getEmail());
            users.add(user);
        }
        userDao.save(new User());

        return users;
    }

    @Override
    public boolean addUser(UserReq userReq) {
        Optional<User> userOpt = userDao.findById(userReq.getId());

        if(userOpt.isPresent()){
            return false;
        }else{
            User user = new User(userReq.getUsername(), userReq.getEmail(), userReq.getPassword());
            userDao.save(user);
            return true;
        }
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

    @Override
    public void testLock() {
        User user = userDao.findById(1L).orElse(null);
        user.setEmail("X");
        userDao.save(user);

        userDao.findAll().stream().forEach(el -> {
            System.out.println(el.getEmail());
        });
        user = userDao.findById(1L).orElse(null);
        System.out.println(user.getEmail());
    }

    private <A,B,C,D,T> T get(A a, B b, C c, D d){
        return null;
    }
}
