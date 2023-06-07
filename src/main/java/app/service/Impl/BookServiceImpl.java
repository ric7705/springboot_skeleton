package app.service.Impl;


import app.repository.User;
import app.repository.UserDao;
import app.service.BookService;
import app.service.UserService;
import app.vo.request.UserReq;
import app.vo.response.UserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Override
    public List<String> getBook() {

        String a = "3";

        return null;
    }
}
