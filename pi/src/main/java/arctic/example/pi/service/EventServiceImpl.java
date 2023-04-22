package arctic.example.pi.service;

import arctic.example.pi.entity.Evenement;
import arctic.example.pi.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class EventServiceImpl implements IEventService {
    @Autowired
    EventRepository eventRepo;

    public List<Evenement> retrieveAllEvent() {
        return (List<Evenement>) eventRepo.findAll();
    }

    public Evenement addOrUpdateEvent(Evenement event) {
        return eventRepo.save(event);
    }

    public void removeEvenement(Evenement event) {
            eventRepo.delete(event);
    }

    public Evenement retrieveEvent(Long numEvent) {
        return eventRepo.findById(numEvent).get();
    }

    public void removeReservation(Long numEvent, Long numUser) {



    }
}
