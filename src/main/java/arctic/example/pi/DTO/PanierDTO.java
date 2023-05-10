package arctic.example.pi.DTO;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data

public class PanierDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPanier;

    private int quantity;

    private float total;

    private int prixProduct;

    private Long refProduct;



    public PanierDTO() {
    }

    public PanierDTO(int quantity, float total, int prixProduct, Long refProduct) {
        this.quantity = quantity;
        this.total = total;
        this.prixProduct = prixProduct;
        this.refProduct = refProduct;
    }

}


