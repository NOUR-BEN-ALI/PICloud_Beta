package arctic.example.pi.repository;


import arctic.example.pi.entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierRepository  extends JpaRepository<Panier, Long>{

    @Query("select p from Panier p order by p.total_panier ASC ")
    List<Panier> getAllOrderedByTotalPanierASC();

    @Query("SELECT SUM(total_panier) FROM Panier WHERE ref_product = :ref_product")
    Long findByRefProduitOrderByTotalPanier(@Param("ref_product") int ref_product);



}
