package arctic.example.pi.entity;

public class KonnectPaymentResponse {
    private String payUrl;
    private String paymentRef;
    private KonnectPaymentStatus status;


    public KonnectPaymentResponse() {
    }

    public KonnectPaymentResponse(String payUrl, String paymentRef, KonnectPaymentStatus status) {
        this.payUrl = payUrl;
        this.paymentRef = paymentRef;
        this.status = status;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    public KonnectPaymentStatus getStatus() {
        return status;
    }

    public void setStatus(KonnectPaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "KonnectPaymentResponse{" +
                "payUrl='" + payUrl + '\'' +
                ", paymentRef='" + paymentRef + '\'' +
                ", status=" + status +
                '}';
    }
}
