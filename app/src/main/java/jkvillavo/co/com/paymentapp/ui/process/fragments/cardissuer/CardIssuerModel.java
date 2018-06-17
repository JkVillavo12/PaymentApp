package jkvillavo.co.com.paymentapp.ui.process.fragments.cardissuer;

import android.content.Context;

import java.util.List;

import jkvillavo.co.com.paymentapp.R;
import jkvillavo.co.com.paymentapp.data.Config;
import jkvillavo.co.com.paymentapp.data.model.CardIssuer;
import jkvillavo.co.com.paymentapp.data.network.MercadoPagoApi;
import jkvillavo.co.com.paymentapp.data.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardIssuerModel implements IContractCardIssuer.Model {

    private IContractCardIssuer.Presenter presenter;
    private Context mContext;

    public CardIssuerModel(IContractCardIssuer.Presenter presenter, Context context) {

        this.presenter = presenter;
        this.mContext = context;

    }

    @Override
    public void loadCardIssuers(final OnFinishedListener onFinishedListener, String paymentMethodId) {

        Call<List<CardIssuer>> call = RetrofitInstance.getRetrofitInstance().create(
                MercadoPagoApi.class).getCardIssuers(Config.MercadoPagoApi.PUBLIC_KEY, paymentMethodId
        );

        call.enqueue(new Callback<List<CardIssuer>>() {

            public void onResponse(Call<List<CardIssuer>> call, Response<List<CardIssuer>> response) {

                if (response.body() != null) {
                    List<CardIssuer> cardIssuerList = response.body();
                    onFinishedListener.onFinished(cardIssuerList);
                } else {
                    onFinishedListener.onMessage(mContext.getResources().getString(R.string.msg_errorBackend));
                }
            }

            @Override
            public void onFailure(Call<List<CardIssuer>> call, Throwable t) {

                onFinishedListener.onFailure(t);
            }

        });

    }

}
