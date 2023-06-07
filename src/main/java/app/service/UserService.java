package app.service;


import app.vo.response.UserRes;
import app.vo.request.UserReq;

import java.util.List;

public interface UserService {

    List<UserRes> getUsers();

    boolean addUser(UserReq user);
    boolean deleteUser(String id);
    boolean updateUser(UserReq userReq);
    void testLock();
}
