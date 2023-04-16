package com.beta.gestion_panier_commande.Repository;

import com.beta.gestion_panier_commande.Model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
