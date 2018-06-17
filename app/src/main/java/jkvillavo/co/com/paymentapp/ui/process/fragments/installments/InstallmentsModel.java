package jkvillavo.co.com.paymentapp.ui.process.fragments.installments;

import android.content.Context;

import java.util.List;

import jkvillavo.co.com.paymentapp.R;
import jkvillavo.co.com.paymentapp.data.Config;
import jkvillavo.co.com.paymentapp.data.model.Installment;
import jkvillavo.co.com.paymentapp.data.network.MercadoPagoApi;
import jkvillavo.co.com.paymentapp.data.network.RetrofitInstance;
import jkvillavo.co.com.paymentapp.util.prefs.Prefs;
import jkvillavo.co.com.paymentapp.util.prefs.PrefsKey;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstallmentsModel implements IContractInstallments.Model {

    private IContractInstallments.Presenter presenter;
    private Context mContext;

    public InstallmentsModel(IContractInstallments.Presenter presenter, Context context) {

        this.presenter = presenter;
        this.mContext = context;

    }

    @Override
    public void loadPayerCosts(final IContractInstallments.Model.OnFinishedListener onFinishedListener, String cardIssuerId) {

        long amount = Prefs.getLongData(PrefsKey.Process.AMOUNT, mContext);
        String paymentMethodId = Prefs.getStringData(PrefsKey.Process.PAYMENT_METHOD_ID, mContext);

        Call<List<Installment>> call = RetrofitInstance.getRetrofitInstance().create(
                MercadoPagoApi.class).getInstallment(
                Config.MercadoPagoApi.PUBLIC_KEY,
                String.valueOf(amount),
                paymentMethodId,
                cardIssuerId
        );

        call.enqueue(new Callback<List<Installment>>() {

            public void onResponse(Call<List<Installment>> call, Response<List<Installment>> response) {

                if (response.body() != null) {
                    List<Installment> installment = response.body();
                    if (!installment.isEmpty()) {
                        onFinishedListener.onFinished(installment.get(0));
                    } else {
                        onFinishedListener.onMessage(mContext.getResources().getString(R.string.msg_noInstallments));
                    }

                } else {
                    onFinishedListener.onMessage(mContext.getResources().getString(R.string.msg_errorBackend));
                }
            }

            @Override
            public void onFailure(Call<List<Installment>> call, Throwable t) {

                onFinishedListener.onFailure(t);
            }

        });

    }
}
