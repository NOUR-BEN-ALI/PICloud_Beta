package arctic.example.pi.service;

import arctic.example.pi.entity.Evenement;
import arctic.example.pi.entity.User;
import arctic.example.pi.repository.EventRepository;
import arctic.example.pi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements IEventService {
    @Autowired
    EventRepository eventRepo;

    @Autowired
    UserRepository userRepo;

    public List<Evenement> retrieveAllEvent() {
        return (List<Evenement>) eventRepo.findAll();
    }

    @Override
    public List<Evenement> retrieveActiveEvents() {
        return (List<Evenement>) eventRepo.getActiveEvents();
    }


    public void addEvent(Evenement event) {
        List<Evenement> events = eventRepo.getEventCondition(event.getDateDebut(),event.getDateFin());
        if (events.isEmpty())
            eventRepo.save(event);
    }

    public void removeEvenement(Evenement event) {
            eventRepo.delete(event);
    }

    public Optional<Evenement> retrieveEvent(Long numEvent) {
        return eventRepo.findById(numEvent);
    }

    public void removeReservation(Long numEvent, Long numUser) {
        User u = userRepo.findById(numUser).get();
        Evenement e = eventRepo.findById(numEvent).get();
        u.getEvent().remove(e);
        e.getUsers().remove(u);

        eventRepo.save(e);

    }

    @Override
    public void Reserver(Long numEvent, Long numUser) {
        User u = userRepo.findById(numUser).get();
        Evenement e = eventRepo.findById(numEvent).get();
        u.getEvent().add(e);
        e.getUsers().add(u);
        eventRepo.save(e);
    }

    @Override
    public List<Evenement> retrieveReservationsByUser(Long numUser) {
        return eventRepo.getEventsByUser(numUser);
    }

    @Override
    public List<User> retrieveUsersByEvent(Long numEvent) {
        return eventRepo.getUsersByEvent(numEvent);
    }
}
