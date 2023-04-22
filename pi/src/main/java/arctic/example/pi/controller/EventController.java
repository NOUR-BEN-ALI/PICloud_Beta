package arctic.example.pi.controller;

import arctic.example.pi.entity.Evenement;
import arctic.example.pi.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private IEventService eventService;

    @PostMapping("/addEvent")
    public void addEvent(@RequestBody Evenement event){
         eventService.addEvent(event);
    }

  /*  @PutMapping("/updateEvent")
    public  Evenement updateEvent(@RequestBody Evenement event){
        return eventService.addOrUpdateEvent(event);
    }
   */

    @DeleteMapping("/deleteEvent")
    public void deleteEvent(@RequestBody Evenement event){
        eventService.removeEvenement(event);
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
    public void assign(@PathVariable Long ide,@PathVariable Long idu){ eventService.Reserver(ide, idu);}

    @GetMapping("/getActivEvents")
    public List<Evenement> getActiveEvents() {return eventService.retrieveActiveEvents();}

    @GetMapping("/getReservationByUser/{id}")
    public List<Evenement> getReservationByUser(@PathVariable Long id){
        return eventService.retrieveReservationsByUser(id);
    }

    @DeleteMapping("/deleteReservation/{ide}/{idu}")
    public void deleteReservation(@PathVariable Long idu,@PathVariable Long ide){
        eventService.removeReservation(ide,idu);
    }

}
