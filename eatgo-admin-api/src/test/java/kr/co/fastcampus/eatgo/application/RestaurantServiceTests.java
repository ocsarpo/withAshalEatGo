package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTests {

    private RestaurantService restaurantService;
    @Mock
    private RestaurantRepository restaurantRepository;

    //    이 테스트는 스프링의 테스트가 아니라서 의존성 주입을 받을 수 없다!!
//    그래서 직접 Service에서 사용할 Repository를 직접 넣어줌
    @Before
    public void setUp() {
//        테스트 객체에 있는 @Mock 어노테이션이 붙은 것들 초기화
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();
        restaurantService = new RestaurantService(restaurantRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .categoryId(1L)
                .name("Bob zip")
                .address( "Seoul")
                .build();

        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);

//        Optional이 리턴되야하니 수정
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId(), is(1004L));
    }

    //        요청만해도 에러가 발생하면 좋겠다
    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNotExisted() {
        restaurantService.getRestaurant(404L);
    }

    @Test
    public void addRestaurant() {
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address( "Busan")
                .build();

        Restaurant created = restaurantService.addRestaurant(restaurant);
        // 여기까지 했을 때, 새로운 레스토랑이 만들어진 것을 확인했음 좋겠고, ID가 임의로 넣지 않아도 만들어졌으면 좋겠다.

        assertThat(created.getId(), is(1234L));
    }

    @Test
    public void updateRestaurant() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address( "Seoul")
                .build();
//        찾아서 얻어졌고
        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));
//      바꾸랬더니
        restaurantService.updateRestaurant(1004L, "Sool zip", "Busan");
//      바뀌었다!
        assertThat(restaurant.getName(), is("Sool zip"));
        assertThat(restaurant.getAddress(), is("Busan"));
    }
}