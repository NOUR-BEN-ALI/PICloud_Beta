package arctic.example.pi.entity;

import java.util.List;

public class KonnectPaymentRequest {

    private String receiverWalletId;
    private String token;
    private double amount;
    private String type;
    private String description;
    private int lifespan;
    private boolean feesIncluded;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String orderId;
    private String webhook;
    private boolean silentWebhook;
    private String successUrl;
    private String failUrl;
    private boolean checkoutForm;
    private List<String> acceptedPaymentMethods;

    public KonnectPaymentRequest() {
    }

    public KonnectPaymentRequest(String receiverWalletId, String token, double amount, String type, String description, int lifespan, boolean feesIncluded, String firstName, String lastName, String phoneNumber, String email, String orderId, String webhook, boolean silentWebhook, String successUrl, String failUrl, boolean checkoutForm, List<String> acceptedPaymentMethods) {
        this.receiverWalletId = receiverWalletId;
        this.token = token;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.lifespan = lifespan;
        this.feesIncluded = feesIncluded;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.orderId = orderId;
        this.webhook = webhook;
        this.silentWebhook = silentWebhook;
        this.successUrl = successUrl;
        this.failUrl = failUrl;
        this.checkoutForm = checkoutForm;
        this.acceptedPaymentMethods = acceptedPaymentMethods;
    }


    // getters and setters


    public String getReceiverWalletId() {
        return receiverWalletId;
    }

    public void setReceiverWalletId(String receiverWalletId) {
        this.receiverWalletId = receiverWalletId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLifespan() {
        return lifespan;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    public boolean isFeesIncluded() {
        return feesIncluded;
    }

    public void setFeesIncluded(boolean feesIncluded) {
        this.feesIncluded = feesIncluded;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public boolean isSilentWebhook() {
        return silentWebhook;
    }

    public void setSilentWebhook(boolean silentWebhook) {
        this.silentWebhook = silentWebhook;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getFailUrl() {
        return failUrl;
    }

    public void setFailUrl(String failUrl) {
        this.failUrl = failUrl;
    }

    public boolean isCheckoutForm() {
        return checkoutForm;
    }

    public void setCheckoutForm(boolean checkoutForm) {
        this.checkoutForm = checkoutForm;
    }

    public List<String> getAcceptedPaymentMethods() {
        return acceptedPaymentMethods;
    }

    public void setAcceptedPaymentMethods(List<String> acceptedPaymentMethods) {
        this.acceptedPaymentMethods = acceptedPaymentMethods;
    }

    @Override
    public String toString() {
        return "KonnectPaymentRequest{" +
                "receiverWalletId='" + receiverWalletId + '\'' +
                ", token='" + token + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", lifespan=" + lifespan +
                ", feesIncluded=" + feesIncluded +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", orderId='" + orderId + '\'' +
                ", webhook='" + webhook + '\'' +
                ", silentWebhook=" + silentWebhook +
                ", successUrl='" + successUrl + '\'' +
                ", failUrl='" + failUrl + '\'' +
                ", checkoutForm=" + checkoutForm +
                ", acceptedPaymentMethods=" + acceptedPaymentMethods +
                '}';
    }


}
