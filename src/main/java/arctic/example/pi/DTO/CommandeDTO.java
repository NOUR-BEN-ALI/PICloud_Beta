package arctic.example.pi.DTO;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;


@Data

public class CommandeDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;

    @ManyToMany(mappedBy = "commandes")
    private List<PanierDTO> paniers = new ArrayList<>();





    private String nom;
    private String prenom;
    private String societe;
    private int numTel;
    private int codePostal;
    private String etat;
    private String adresse;
    private String adresseMail;
    private String modePaiement;
    private Long refProduct;

    public CommandeDTO(String nom, String prenom, String societe, int numTel, int codePostal, String etat, String adresse, String adresseMail, String modePaiement, Long refProduct) {
        this.nom = nom;
        this.prenom = prenom;
        this.societe = societe;
        this.numTel = numTel;
        this.codePostal = codePostal;
        this.etat = etat;
        this.adresse = adresse;
        this.adresseMail = adresseMail;
        this.modePaiement = modePaiement;
        this.refProduct = refProduct;
    }

    // getters and setters for all fields


    public Long getIdC() {
        return idC;
    }

    public void setIdC(Long idC) {
        this.idC = idC;
    }

    public List<PanierDTO> getPaniers() {
        return paniers;
    }

    public void setPaniers(List<PanierDTO> paniers) {
        this.paniers = paniers;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public Long getRefProduct() {
        return refProduct;
    }

    public void setRefProduct(Long refProduct) {
        this.refProduct = refProduct;
    }
}
