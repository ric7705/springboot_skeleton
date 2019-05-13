package app.controller;

import app.service.Impl.UserServiceImpl;
import app.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    UserService userService = new UserServiceImpl();

    @InjectMocks
    UserController userController;

    @Test
    public void testGetUsers() throws Exception {
        userController.getUsers();
        verify(userService).getUsers();
    }
}
