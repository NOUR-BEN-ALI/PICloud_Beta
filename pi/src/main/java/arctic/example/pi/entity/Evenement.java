package arctic.example.pi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evenement  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numEvent;
    private String nomEvent;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFin ;
    private int nbrPlace ;

    private Double prix;

    private String fileName;
    @ManyToMany(mappedBy = "event")
    private Set<User> users;

    @ManyToMany(mappedBy = "event")
    private List<Sponsor> sponsors;



}
