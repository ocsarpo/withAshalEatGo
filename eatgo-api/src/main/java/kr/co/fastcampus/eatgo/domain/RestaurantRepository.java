package kr.co.fastcampus.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// RestaurantRepository를 스프링이 직접 관리하도록 하는 어노테이션
// 이 객체를 사용할 곳에는 멤버변수에 직접 객체를 할당하지 않고 @Autowired를 사용하면 됨
@Component
public class RestaurantRepository {
    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepository(){
        restaurants.add(new Restaurant(1004L,"Bob zip", "Seoul"));
        restaurants.add(new Restaurant(2020L,"Cyber food", "Seoul"));
    }
    public List<Restaurant> findAll() {
        return restaurants;
    }

    public Restaurant findById(Long id) {
        //         java 8 부터 추가된 stream API
//        지금은 컨트롤러에서 검색을 해서 돌려주는 데. 이것은 컨트롤러 역할이 아님.
//        그래서 역할을 repository로 위임해서 컨트롤러는 그저 값을 바로 돌려주는 역할만 하게 함
        return restaurants.stream()
        .filter(r -> r.getId().equals(id))
        .findFirst()
        .orElse(null);
//                .get();
    }
}
