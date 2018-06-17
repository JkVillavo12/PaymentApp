package jkvillavo.co.com.paymentapp.data.network;

import java.util.List;

import jkvillavo.co.com.paymentapp.data.model.CardIssuer;
import jkvillavo.co.com.paymentapp.data.model.Installment;
import jkvillavo.co.com.paymentapp.data.model.PaymentMethod;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MercadoPagoApi {

    @GET("payment_methods")
    Call<List<PaymentMethod>> getPaymentMethods(
            @Query("public_key") String publicKey);

    @GET("payment_methods/card_issuers")
    Call<List<CardIssuer>> getCardIssuers(
            @Query("public_key") String publicKey,
            @Query("payment_method_id") String paymentMethodId);

    @GET("payment_methods/installments")
    Call<List<Installment>> getInstallment(
            @Query("public_key") String publicKey,
            @Query("amount") String amount,
            @Query("payment_method_id") String paymentMethodId,
            @Query("issuer.id") String issuerId);


}
