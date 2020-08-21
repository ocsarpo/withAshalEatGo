package kr.co.fastcampus.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

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
