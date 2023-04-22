package arctic.example.pi.controller;

import arctic.example.pi.entity.Evenement;
import arctic.example.pi.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    public Evenement getEventById(@PathVariable Long id){
        return eventService.retrieveEvent(id);
    }

    @GetMapping("/getActivEvents")
    public List<Evenement> getActiveEvents() {return eventService.retrieveActiveEvents();}
}
