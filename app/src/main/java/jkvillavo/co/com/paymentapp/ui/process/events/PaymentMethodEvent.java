package jkvillavo.co.com.paymentapp.ui.process.events;

public class PaymentMethodEvent {

    private String paymentMethodId;

    public PaymentMethodEvent(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}
