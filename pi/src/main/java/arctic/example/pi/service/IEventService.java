package arctic.example.pi.service;




import arctic.example.pi.entity.Evenement;

import java.util.List;

public interface IEventService {

    List<Evenement> retrieveAllEvent();

    Evenement addOrUpdateEvent(Evenement event);

    void removeEvenement (Evenement event);

    Evenement retrieveEvent (Long numEvent);

    void removeReservation (Long numEvent, Long numUser);
}
