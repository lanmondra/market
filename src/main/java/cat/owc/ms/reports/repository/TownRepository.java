package cat.owc.ms.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.owc.ms.reports.entity.Town;

@Repository
public interface TownRepository  extends JpaRepository<Town, Integer> , TownRepositoryNative{

}
