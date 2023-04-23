package arctic.example.pi.controller;


import arctic.example.pi.entity.Sponsor;
import arctic.example.pi.service.ISponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/sponsors")
public class SponsorController {

    @Autowired
    private ISponsorService sponsorService;

    @PostMapping("/addSponsor")
    public Sponsor addSponsor(@RequestBody Sponsor sponsor){
        return sponsorService.addOrUpdateSponsor(sponsor);
    }

    @PutMapping("/updateSponsor")
    public  Sponsor updateSponsort(@RequestBody Sponsor sponsor){
        return sponsorService.addOrUpdateSponsor(sponsor);
    }

    @DeleteMapping("/deleteSponsor")
    public void deleteSponsor(@RequestBody Sponsor sponsor){
        sponsorService.removeSponsor(sponsor);
    }

    @GetMapping("/sponsors")
    public List<Sponsor> getAllSponsors(){
        return sponsorService.retrieveAllSponsors();
    }

    @GetMapping("/getSponsor/{id}")
    public Sponsor getSponsorById(@PathVariable Long id){
        return sponsorService.retrieveSponsor(id);
    }

    @GetMapping("/export-to-excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Sponsors_Information.xlsx";
        response.setHeader(headerKey, headerValue);
        sponsorService.exportSponsorsToExcel(response);

    }

}
