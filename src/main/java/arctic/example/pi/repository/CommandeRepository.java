package arctic.example.pi.repository;

import arctic.example.pi.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long>  {

    // Services avancées

    @Query("SELECT c FROM Commande c WHERE c.mode_paiment = :mode_paiment")
    List<Commande> findByModePaiment(@Param("mode_paiment") String mode_paiment);

    List<Commande> findByAdressmail(String adressmail);

    List<Commande> findByNom(String nom);

    List<Commande> findBySociete(String societe);




}
