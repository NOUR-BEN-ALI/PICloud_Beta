package arctic.example.pi.repository;

import arctic.example.pi.entity.Articles;
import arctic.example.pi.entity.Blog;
import arctic.example.pi.entity.Reclamation;
import arctic.example.pi.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BlogRepository extends CrudRepository<Blog,Long> {
    Set<Blog> findByTitre(String titre);
    @Query("SELECT e FROM Blog e ORDER BY e.titre asc ")
    Set<Blog> triblog () ;

    List<Blog> findAllByUser(User user);
}
