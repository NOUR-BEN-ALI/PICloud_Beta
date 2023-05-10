package arctic.example.pi.service;

import arctic.example.pi.entity.Commande;
import arctic.example.pi.entity.Panier;
import arctic.example.pi.repository.CommandeRepository;
import arctic.example.pi.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanierService {

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    public List<Panier> getAllPaniers() {
        return panierRepository.findAll();
    }

    public Optional<Panier> getPanierById(long id) {
        return panierRepository.findById(id);
    }

    public void addPanier(Panier panier) {
        panierRepository.save(panier);
    }

    public void updatePanier(Panier panier) {
        panierRepository.save(panier);
    }

    public void deletePanier(long id) {
        panierRepository.deleteById(id);
    }


    public List<Panier> getAllOrderedByTotalPanierASC() {
        return panierRepository.getAllOrderedByTotalPanierASC();
    }

    public Long findTotalPanierByProductRef(int ref_product) {
        return panierRepository.findByRefProduitOrderByTotalPanier(ref_product);
    }


    public void assignCommandeToPanier(Long id_panier, Long idC) {
        Panier panier = panierRepository.findById(id_panier).orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        Commande commande = commandeRepository.findById(idC).orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        panier.setCommande(commande);
        panierRepository.save(panier);
    }


}

