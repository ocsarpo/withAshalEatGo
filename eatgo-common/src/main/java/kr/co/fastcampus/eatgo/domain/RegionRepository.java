package kr.co.fastcampus.eatgo.domain;

import kr.co.fastcampus.eatgo.domain.Region;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region, Long> {

    List<Region> findAll();

    Region save(Region region);  //CrudRepo에 기본으로 있음. 써서 명확히 관리할 수 있음
}
