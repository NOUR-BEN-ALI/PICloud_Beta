package com.beta.gestion_panier_commande.Repository;


import com.beta.gestion_panier_commande.Model.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierRepository  extends JpaRepository<Panier, Long>{

}
