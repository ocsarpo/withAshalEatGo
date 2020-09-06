package kr.co.fastcampus.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    private Long restaurantId;

// 프로그램 내부에서 넣어주는 값이기 때문에 validation 하지않음
    private Long userId;

// 프로그램 내부에서 넣어주는 값이기 때문에 validation 하지않음
    private String name;

    @NotEmpty
    @Setter
    private String date;

    @NotEmpty
    @Setter
    private String time;

    @NotNull
    @Setter
    @Min(1)     // -> 이거 테스트코드 만들어보기
    private Integer partySize;

}
