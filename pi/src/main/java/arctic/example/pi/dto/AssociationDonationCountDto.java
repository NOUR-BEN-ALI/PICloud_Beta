package arctic.example.pi.dto;

public class AssociationDonationCountDto {
    private int month;
    private long count;

    public AssociationDonationCountDto(int month, long count) {
        this.month = month;
        this.count = count;
    }
}
