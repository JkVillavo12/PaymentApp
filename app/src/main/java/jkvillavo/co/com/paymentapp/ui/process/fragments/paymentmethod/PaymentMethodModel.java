package jkvillavo.co.com.paymentapp.ui.process.fragments.paymentmethod;

import android.content.Context;

import java.util.List;

import jkvillavo.co.com.paymentapp.R;
import jkvillavo.co.com.paymentapp.data.Config;
import jkvillavo.co.com.paymentapp.data.model.PaymentMethod;
import jkvillavo.co.com.paymentapp.data.network.MercadoPagoApi;
import jkvillavo.co.com.paymentapp.data.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentMethodModel implements IContractPaymentMethod.Model {

    private IContractPaymentMethod.Presenter presenter;
    private Context mContext;

    public PaymentMethodModel(IContractPaymentMethod.Presenter presenter, Context context) {

        this.presenter = presenter;
        mContext = context;

    }

    @Override
    public void loadPaymentMethods(final OnFinishedListener onFinishedListener) {

        Call<List<PaymentMethod>> call = RetrofitInstance.getRetrofitInstance().create(
                MercadoPagoApi.class).getPaymentMethods(Config.MercadoPagoApi.PUBLIC_KEY);

        call.enqueue(new Callback<List<PaymentMethod>>() {

            public void onResponse(Call<List<PaymentMethod>> call, Response<List<PaymentMethod>> response) {

                if (response.body() != null) {
                    List<PaymentMethod> methodList = response.body();
                    onFinishedListener.onFinished(methodList);
                } else {
                    onFinishedListener.onMessage(mContext.getResources().getString(R.string.msg_errorBackend));
                }
            }

            @Override
            public void onFailure(Call<List<PaymentMethod>> call, Throwable t) {

                onFinishedListener.onFailure(t);
            }

        });

    }
}
