package kr.co.fastcampus.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();

//    java 8 부터 추가된 타입. -> Restaurant 가 있냐 없냐? 직접구분하는 타입
    // null로 접근했을 때 발생하는 문제 해결
    Optional<Restaurant> findById(Long id);

    Restaurant save(Restaurant restaurant);
}
