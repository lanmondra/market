package cat.owc.ms.reports.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.owc.ms.reports.entity.Province;

@Repository
public interface ProvinceRepository  extends JpaRepository<Province, Integer>{
	
	Optional< Province> findByName(String name);

}
