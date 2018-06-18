package jkvillavo.co.com.paymentapp.ui.process.events;

/**
 * Clase que controla el evento de seleccion de un metodo de pago
 */
public class PaymentMethodEvent {

    private String paymentMethodId;

    public PaymentMethodEvent ( String paymentMethodId ) {

        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentMethodId () {

        return paymentMethodId;
    }

    public void setPaymentMethodId ( String paymentMethodId ) {

        this.paymentMethodId = paymentMethodId;
    }
}
