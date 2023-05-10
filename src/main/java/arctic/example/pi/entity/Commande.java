package arctic.example.pi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity

@Table(name = "commande")
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;

    private String nom;
    private String prenom;
    private String societe;

    private int num_tel;
    private int codepostal;

    private String etat;

    private String adresse;

    private String adressmail;

    private String mode_paiment;


    @JsonIgnore
    @OneToMany(mappedBy = "commande" , cascade = CascadeType.ALL)
    private List<Panier> paniers ;

    @ManyToOne
    @JoinColumn(name = "ref_product")
    private Produit produit;


}
