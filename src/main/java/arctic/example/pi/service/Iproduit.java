package arctic.example.pi.service;

import arctic.example.pi.entity.Produit;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface Iproduit {
    public Produit addProd(Produit a) ;
    void deleteProd(Long id);

    Produit updateProd(Produit a);

    List<Produit> retrieveAllProduits();

    Produit retrieveProd(Long id);
    Set<Produit> findByName(String name);

    Set<Produit> findByDateExpBetween(Date date1 , Date date2);


    public Produit addblogtoarticles(Produit produit, Long id);
public Produit changerEtat(Produit produit);
    void assignCategorieToProduit(Long id, Long idcat);

    // ajouter prod maaha cat
    Produit ajouterProduitt(Produit produit, Long idcat);

    // Méthode pour générer le code QR pour le produit
    Produit updateArticle(Produit produit);
    //String generateQRCode(Produit produit) throws Exception;


    List<Produit> findByuser(Long id);

}
