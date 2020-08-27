package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.UserService;
import kr.co.fastcampus.eatgo.domain.User;
import org.apache.catalina.Group;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void list() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .email("tester@example.com")
                .name("tester")
                .level(1L)
                .build());

        given(userService.getUsers()).willReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("tester")));
    }

    @Test
    public void create() throws Exception {
        String email = "admin@example.com";
        String name = "Administrator";
        User user = User.builder().email(email).name(name).build();

        given(userService.addUser(email, name)).willReturn(user);
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"admin@example.com\",\"name\":\"Administrator\"}"))
                .andExpect(status().isCreated());
//        위까지 구현 후 테스트 -> UserController에 create메서드 작성 하여 통과 시킴
//        그리고
//        실제 create에서 일어나야하는 일을 검증함(verify)

        verify(userService).addUser(email, name);
//        위 코드 작성 후, userService에 addUser 메서드 생성 후 return null로 테스트 후
//        컨트롤러에서 실제 userService를 사용하여 addUser 메서드를 사용하도록 구현
    }

    @Test
    public void update() throws Exception {
        mvc.perform(patch("/users/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"admin@example.com\"," +
                        "\"name\":\"Administrator\",\"level\":100}"))
                .andExpect(status().isOk());

        Long id = 1004L;
        String email = "admin@example.com";
        String name = "Administrator";
        Long level = 100L;

//          update
        verify(userService).updateUser(eq(id), eq(email), eq(name), eq(level));
    }

    @Test
    public void deactivate() throws Exception {
        mvc.perform(delete("/users/1004"))
                .andExpect(status().isOk());

//        위에서는 아래와 같은 일이 일어나야 한다. (verify)
        verify(userService).deactiveUser(1004L);
    }

}