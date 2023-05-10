package arctic.example.pi.controller;

import arctic.example.pi.entity.Commande;
import arctic.example.pi.repository.CommandeRepository;
import arctic.example.pi.service.CommandeService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;





@RestController

@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")


@RequestMapping("/commandes")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;
    @Autowired
    private CommandeRepository commandeRepository;


    @GetMapping("/allCommandes")
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandes = commandeService.getAllCommandes();
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        Optional<Commande> optionalCommande = commandeService.getCommandeById(id);
        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addCommande")
    public ResponseEntity<Void> addCommande(@RequestBody Commande commande) {
        commandeService.addCommande(commande);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande commande) {
        Commande updatedCommande = commandeService.updateCommande(id, commande);
        if (updatedCommande != null) {
            return new ResponseEntity<>(updatedCommande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteCommande/{id}")
    public ResponseEntity<HttpStatus> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Services avancées

    // Search Commande By Email
    //  Test Example : http://localhost:8090/commandes?email=johndoe@example.com
    @GetMapping(params = "email")
    public ResponseEntity<List<Commande>> getCommandesByAdressmail(@RequestParam String email) {
        List<Commande> commandes = commandeService.getCommandesByAdressmail(email);
        if (commandes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(commandes, HttpStatus.OK);
        }
    }

    // Search By Payement Methode Function
    // Test Link : http://localhost:8090/commandes?mode_paiment=credit card
    @GetMapping(params = "mode_paiment")
    public ResponseEntity<List<Commande>> getCommandesByModePaiment(@RequestParam String mode_paiment) {
        List<Commande> commandes = commandeService.getCommandesByModePaiment(mode_paiment);
        if (commandes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(commandes, HttpStatus.OK);
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<Commande>> searchCommandes(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String societe,
            @RequestParam(required = false) String adressmail) {

        List<Commande> commandes;

        if (nom != null) {
            commandes = commandeService.findCommandesByNom(nom);
        } else if (societe != null) {
            commandes = commandeService.findCommandesBySociete(societe);
        } else if (adressmail != null) {
            commandes = commandeService.findCommandesByAdressmail(adressmail);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(commandes);
    }

    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=commandes.xlsx");

        List<Commande> commandes = commandeRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Commandes");

        // create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nom");
        headerRow.createCell(1).setCellValue("Prenom");
        headerRow.createCell(2).setCellValue("Societe");
        headerRow.createCell(3).setCellValue("Num Tel");
        headerRow.createCell(4).setCellValue("ZIP Code");
        headerRow.createCell(5).setCellValue("Etat");
        headerRow.createCell(6).setCellValue("Adresse");
        headerRow.createCell(7).setCellValue("Adresse mail");
        headerRow.createCell(8).setCellValue("Mode de paiement");

        // create data rows
        int rowNum = 1;
        for (Commande commande : commandes) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(commande.getNom());
            row.createCell(1).setCellValue(commande.getPrenom());
            row.createCell(2).setCellValue(commande.getSociete());
            row.createCell(3).setCellValue(commande.getNum_tel());
            row.createCell(4).setCellValue(commande.getCodepostal());
            row.createCell(5).setCellValue(commande.getEtat());
            row.createCell(6).setCellValue(commande.getAdresse());
            row.createCell(7).setCellValue(commande.getAdressmail());
            row.createCell(8).setCellValue(commande.getMode_paiment());

        }

        // write the workbook to the response stream
        workbook.write(response.getOutputStream());
        workbook.close();
    }


    @GetMapping("/societe/{societe}")
    public List<Commande> getCommandesBySociete(@PathVariable String societe) {
        return commandeRepository.findBySociete(societe);
    }


}
