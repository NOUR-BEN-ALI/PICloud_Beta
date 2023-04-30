package arctic.example.pi.DTO;

public class AssignToEventRequest {

    private Long eventId;
    private Long[] sponsorId;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long[] getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Long[] sponsorId) {
        this.sponsorId = sponsorId;
    }
}
