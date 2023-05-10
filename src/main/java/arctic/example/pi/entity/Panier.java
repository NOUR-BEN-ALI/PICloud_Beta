package arctic.example.pi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity

@Table(name = "panier")

public class Panier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_panier;

    private int quantity;

    private float total_panier;

    private int prix_product;

    private int ref_product;


    // Add a creationDate property
    @Column(name = "creationDate")
    private Date creationDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idC")
    private Commande commande ;


}
