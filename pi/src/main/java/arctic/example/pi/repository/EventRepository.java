package arctic.example.pi.repository;

import arctic.example.pi.entity.Evenement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends CrudRepository<Evenement,Long> {
}
