package nc.project.jpa.repository;

import nc.project.jpa.entity.ApartmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<ApartmentType, Long> {
}
