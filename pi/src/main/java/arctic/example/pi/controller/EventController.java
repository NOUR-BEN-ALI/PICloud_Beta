package arctic.example.pi.controller;

import arctic.example.pi.DTO.AssignToEventRequest;
import arctic.example.pi.DTO.RemoveSponsorFromEventRequest;
import arctic.example.pi.entity.Evenement;
import arctic.example.pi.entity.Sponsor;
import arctic.example.pi.entity.User;
import arctic.example.pi.service.IEventService;
import arctic.example.pi.service.ParticipantsPDFService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private IEventService eventService;

    @PostMapping("/addEvent")
    public void addEvent(@RequestBody Evenement event){
         eventService.addEvent(event);
    }

    @PutMapping("/updateEvent")
    public  void updateEvent(@RequestBody Evenement event){
         eventService.updateEvenement(event);
    }


    @DeleteMapping("/deleteEvent/{id}")
    public void deleteEvent(@PathVariable Long id ){
        eventService.removeEvenement(id);
    }

    @GetMapping("/events")
    public List<Evenement> getAllEvents(){
        return eventService.retrieveAllEvent();
    }

    @GetMapping("/getEvent/{id}")
    public Optional<Evenement> getEventById(@PathVariable Long id){
        return eventService.retrieveEvent(id);
    }

    @PostMapping ("/resever/{idu}/{ide}")
    public void assign(@PathVariable Long ide,@PathVariable Long idu) throws IOException, WriterException, MessagingException

    { eventService.Reserver(ide, idu);}

    @GetMapping("/getActivEvents")
    public List<Evenement> getActiveEvents() {return eventService.retrieveActiveEvents();}

    @GetMapping("/getReservationByUser/{id}")
    public List<Evenement> getReservationByUser(@PathVariable Long id){
        return eventService.retrieveReservationsByUser(id);
    }

    @GetMapping("/getParticipants/{numEvent}")
    public List<User> getUsersByEvent(@PathVariable Long numEvent){
        return eventService.retrieveUsersByEvent(numEvent);
    }


    @DeleteMapping("/deleteReservation/{ide}/{idu}")
    public void deleteReservation(@PathVariable Long idu,@PathVariable Long ide){
        eventService.removeReservation(ide,idu);
    }

    @GetMapping("/sponsorsNonDuEvent/{id}")
    public List<Sponsor> getSponsorNonDuEvent(@PathVariable Long id) {
        return eventService.getSponsorNonDuEvent(id);
    }

    @GetMapping("/SponsorDuEvent/{id}")
    public List<Sponsor> getSponsorsDuEvent(@PathVariable Long id) {
        return eventService.getSponsorsDuEvent(id);
    }

    @PostMapping("/assignSponsor")
    public void assignSponsorToEvent(@RequestBody AssignToEventRequest req) {
        eventService.addSponsorFromEvent(req);
    }

    @PostMapping("/removeSponsor")
    public void removeSponsorDuEvent(@RequestBody RemoveSponsorFromEventRequest req) {
        eventService.removeSponsorFromEvent(req);
    }



    @GetMapping(value = "/openpdf/participants/{ide}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> employeeReport(@PathVariable Long ide)  throws IOException {
        List<User> participants = (List<User>) eventService.retrieveUsersByEvent(ide);

        ByteArrayInputStream bis = ParticipantsPDFService.participantPDFReport(participants);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=participants.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }




}
