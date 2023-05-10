package arctic.example.pi.entity;

import java.math.BigInteger;

public class SendMoneyRequest
{

    private String fromAddress;
    private String toAddress;
    private BigInteger amount;

    public SendMoneyRequest() {
    }

    public SendMoneyRequest(String fromAddress, String toAddress, BigInteger amount) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }



}
