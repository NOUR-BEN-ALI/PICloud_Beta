package arctic.example.pi.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import arctic.example.pi.entity.Evenement;
import arctic.example.pi.entity.User;
import arctic.example.pi.repository.EventRepository;
import arctic.example.pi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    EventRepository eventRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    private JavaMailSender mailSender;

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
    public void Reserver(Long numEvent, Long numUser) throws IOException, WriterException, MessagingException {
        User u = userRepo.findById(numUser).get();
        Evenement e = eventRepo.findById(numEvent).get();
        u.getEvent().add(e);
        e.getUsers().add(u);
        eventRepo.save(e);

        Map<String, Object> qrcodeData = new HashMap<>();
        qrcodeData.put("User name", u.getNom());
        qrcodeData.put("Email address", u.getEmail());
        qrcodeData.put("Event name", e.getNomEvent());
        qrcodeData.put("Event price", e.getPrix());


        ObjectMapper objectMapper = new ObjectMapper();
        String qrcodeText = objectMapper.writeValueAsString(qrcodeData);
        byte[] qrCode = QRCodeGenerator.generateQRCodeImage(qrcodeText, 350, 350);

        sendConfirmationEmail(u.getEmail(),qrCode);
    }

    @Override
    public void sendConfirmationEmail(String recipient, byte[] qrCode) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        helper = new MimeMessageHelper(message, true); // true indicates
        // multipart message
        helper.setTo(recipient);
        helper.setSubject("QR Code for Event");

        helper.setText("Attached is the QR code for your event.");

        helper.addAttachment("event_qr_code.png", new ByteArrayResource(qrCode), "image/png");

        mailSender.send(message);
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
