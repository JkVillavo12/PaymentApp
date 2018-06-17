
package jkvillavo.co.com.paymentapp.data.model.token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardTokenOut {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("public_key")
    @Expose
    private String publicKey;
    @SerializedName("cardholder")
    @Expose
    private Cardholder cardholder;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_last_updated")
    @Expose
    private String dateLastUpdated;
    @SerializedName("date_due")
    @Expose
    private String dateDue;
    @SerializedName("luhn_validation")
    @Expose
    private Boolean luhnValidation;
    @SerializedName("live_mode")
    @Expose
    private Boolean liveMode;
    @SerializedName("require_esc")
    @Expose
    private Boolean requireEsc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public Cardholder getCardholder() {
        return cardholder;
    }

    public void setCardholder(Cardholder cardholder) {
        this.cardholder = cardholder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateLastUpdated() {
        return dateLastUpdated;
    }

    public void setDateLastUpdated(String dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }

    public String getDateDue() {
        return dateDue;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    public Boolean getLuhnValidation() {
        return luhnValidation;
    }

    public void setLuhnValidation(Boolean luhnValidation) {
        this.luhnValidation = luhnValidation;
    }

    public Boolean getLiveMode() {
        return liveMode;
    }

    public void setLiveMode(Boolean liveMode) {
        this.liveMode = liveMode;
    }

    public Boolean getRequireEsc() {
        return requireEsc;
    }

    public void setRequireEsc(Boolean requireEsc) {
        this.requireEsc = requireEsc;
    }

}
