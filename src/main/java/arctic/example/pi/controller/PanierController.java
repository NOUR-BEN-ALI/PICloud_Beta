package arctic.example.pi.controller;

import arctic.example.pi.entity.Panier;
import arctic.example.pi.repository.CommandeRepository;
import arctic.example.pi.repository.PanierRepository;
import arctic.example.pi.service.PanierPdfService;
import arctic.example.pi.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController

@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")



@RequestMapping("/paniers")
public class PanierController {

    @Autowired

    private PanierService panierService;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private PanierRepository panierRepository;


    @GetMapping("/allPaniers")
    public ResponseEntity<List<Panier>> getAllPaniers() {
        return ResponseEntity.ok(panierService.getAllPaniers());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Panier> getPanierById(@PathVariable("id") long id) {
        Optional<Panier> panier = panierService.getPanierById(id);
        return panier.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addPanier")
    public ResponseEntity<Void> addPanier(@RequestBody Panier panier) {
        panierService.addPanier(panier);

        // Schedule the deletion of the added panier after 5 minutes
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> panierService.deletePanier(panier.getId_panier()), 5, TimeUnit.MINUTES);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }




    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updatePanier(@PathVariable("id") long id, @RequestBody Panier panier) {
        Optional<Panier> existingPanier = panierService.getPanierById(id);
        if (existingPanier.isPresent()) {
            panier.setId_panier(id);
            panierService.updatePanier(panier);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/deletePanier/{id}")
    public ResponseEntity<Void> deletePanier(@PathVariable("id") long id) {
        Optional<Panier> existingPanier = panierService.getPanierById(id);
        if (existingPanier.isPresent()) {
            panierService.deletePanier(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    // http://localhost:8090/paniers/getAllOrderedByTotalPanierASC


    @GetMapping("/getAllOrderedByTotalPanierASC")
    List<Panier> getAllOrderedByTotalPanierASC(){
        return panierService.getAllOrderedByTotalPanierASC();
    }


    @GetMapping(params = "ref_product")
    public ResponseEntity<Long> getTotalPanierByProductRef(@RequestParam int ref_product) {
        Long totalPanier = panierService.findTotalPanierByProductRef(ref_product);
        if (totalPanier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(totalPanier, HttpStatus.OK);
        }
    }





    @GetMapping(value = "/pdfreport", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePdfReport() {
        List<Panier> participants = panierService.getAllPaniers();

        ByteArrayInputStream bis = PanierPdfService.PanierPDFReport(participants);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=paniers.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


    // Avancéee


    @PostMapping("/assign/{id_panier}/{idC}")
    public void assignCommandeToPanier(@PathVariable Long id_panier, @PathVariable Long idC) {
        panierService.assignCommandeToPanier(id_panier, idC);
    }

//



}
