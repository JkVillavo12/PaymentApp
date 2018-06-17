package jkvillavo.co.com.paymentapp.ui.process.events;

public class CardIssuerEvent {

    private String issuerId;

    public CardIssuerEvent(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }
}
