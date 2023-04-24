package arctic.example.pi.service;
import arctic.example.pi.entity.Evenement;
import arctic.example.pi.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.zxing.WriterException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IEventService {

    List<Evenement> retrieveAllEvent();

    List <Evenement> retrieveActiveEvents();

    List <Evenement> retrieveReservationsByUser(Long numUser);

    List<User> retrieveUsersByEvent(Long numEvent);

    void addEvent(Evenement event);

    void removeEvenement (Evenement event);

    Optional< Evenement> retrieveEvent (Long numEvent);

    void removeReservation (Long numEvent, Long numUser);

    void Reserver(Long numEvent, Long numUser) throws JsonProcessingException, IOException, WriterException, MessagingException;




}
