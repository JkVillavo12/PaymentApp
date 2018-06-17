package jkvillavo.co.com.paymentapp.data.model.token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cardholder {

    @SerializedName("identification")
    @Expose
    private Identification identification;
    @SerializedName("name")
    @Expose
    private String name;

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

