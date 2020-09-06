package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Reservation;
import kr.co.fastcampus.eatgo.domain.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReservationServiceTests {

    @Mock
    private ReservationService reservationService;
    @Mock
    private ReservationRepository reservationRepository;

    @Before
    public void setUp () {
        MockitoAnnotations.initMocks(this);

        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void addReservation() {
        Long userId = 1004L;
        String name = "John";
        String date = "2020-12-24";
        String time = "20:00";
        Integer partySize = 20;
        Long restaurantId = 369L;

//        세이브 했더니 받은 거 그대로 돌려줄 것이다 (will) 
        given(reservationRepository.save(any())).will(invocation -> {
                    Reservation reservation = invocation.getArgument(0);
                    return reservation;
                });

//        서비스를 이용해서 addReservation을 하고
        Reservation reservation = reservationService.addReservation(
                restaurantId, userId, name, date, time, partySize);
//          그 결과를 검증
        assertThat(reservation.getName(), is(name));
//          save는 무조건 일어날 것이다
        verify(reservationRepository).save(any(Reservation.class));
    }

}