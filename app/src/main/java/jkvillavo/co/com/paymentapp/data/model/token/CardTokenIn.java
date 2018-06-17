package jkvillavo.co.com.paymentapp.data.model.token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardTokenIn {

    @SerializedName("cardNumber")
    @Expose
    private String cardNumber;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("cardholder")
    @Expose
    private Cardholder cardholder;

    @SerializedName("expirationYear")
    @Expose
    private String expirationYear;

    @SerializedName("expirationMonth")
    @Expose
    private String expirationMonth;

    @SerializedName("securityCode")
    @Expose
    private String securityCode;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cardholder getCardholder() {
        return cardholder;
    }

    public void setCardholder(Cardholder cardholder) {
        this.cardholder = cardholder;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

}
