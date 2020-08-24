package kr.co.fastcampus.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter                 // Lombok 게터 만들어줌
@Builder                // Lombok에서 제공하는 빌더패턴
@NoArgsConstructor      // Lombok 기본생성자 만들어줌
@AllArgsConstructor     // Lombok
public class Restaurant {
    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    
    @Transient // 임시로 통과하는 것이다~ DB에 대한 처리 하지않음
    private List<MenuItem> menuItems;

    public String getInformation() {
        return name + " in " + address;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
//        컬렉션 복사
        this.menuItems = new ArrayList<>(menuItems);
    }

    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
