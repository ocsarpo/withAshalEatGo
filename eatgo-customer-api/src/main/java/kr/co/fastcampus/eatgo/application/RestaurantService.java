package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {
    private RestaurantRepository restaurantRepository;
    private MenuItemRepository menuItemRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuItemRepository menuItemRepository,
                             ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Restaurant> getRestaurants(String region, long categoryId) {
        List<Restaurant> restaurants = restaurantRepository.
                findByAddressContainingAndCategoryId(region, categoryId);
        return restaurants;
    }

    public Restaurant getRestaurant(Long id) {
//        Optional이 값이 없을 때 어떻게 처리하느냐?
//        실무에서는 restaurant가 null일 때의 처리(예외) 를 해주어야함
        Restaurant restaurant  = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
//        orElseThrow 진입시에만 예외 생성

        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
        restaurant.setReviews(reviews);

        return restaurant;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(Long id, String name, String address) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
//        객체만 처리.. -> @Transactional 로 인해서 db에 적용됨
        restaurant.updateInformation(name, address);
        return restaurant;
    }
}
