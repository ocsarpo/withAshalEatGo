package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        //        1. 이렇게하면 0번째가 없으니 테스트가 실패함.
//        List<User> users = new ArrayList<>();

        //        2. 임시로 하나 넣어줘서 테스트 성공시킴 -> 테스트로 다시 가서 유저목록을 얻는 로직을 레포지토리를 통해 얻도록 변경
//        List<User> users = new ArrayList<>();
//        users.add(User.builder()
//                .email("tester@example.com")
//                .name("tester")
//                .level(1L)
//                .build());

//         3 최종
        List<User> users = userRepository.findAll();

        return users;
    }

    public User addUser(String email, String name) {
        User newUser = User.builder()
                .email(email)
                .name(name)
                .level(1L)
                .build();
        return userRepository.save(newUser);
    }

    public User updateUser(Long id, String email, String name, Long level) {
//        TODO: restaurantService의 예외처리 참고
        User user = userRepository.findById(id).orElse(null);

        user.setName(name);
        user.setEmail(email);
        user.setLevel(level);
        return user;
    }

    public User deactiveUser(Long id) {
//        TODO: restaurantService의 예외처리 참고
        User user = userRepository.findById(id).orElse(null);

        user.deactivate();

        return user;
    }
}
