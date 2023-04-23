package arctic.example.pi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numSponsor;

    private String nomSponsor;

    private String Description;

    private Date debutContract;
    private Date finContract;

    private String fileName;

    @ManyToMany
    private Set<Evenement> event;


}
