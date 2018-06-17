package jkvillavo.co.com.paymentapp.ui.process.fragments.installments;

import java.util.List;

import jkvillavo.co.com.paymentapp.data.model.Installment;
import jkvillavo.co.com.paymentapp.data.model.PayerCost;

public interface IContractInstallments {

    interface View {

        void hideProgress();

        void onFailure(Throwable t);

        void showPayerCosts(List<PayerCost> payerCostList);

        void showMessage(String msg);

        void showProgress();
    }

    interface Presenter {

        void loadPayerCosts(String cardIssuerId);
    }

    interface Model {

        interface OnFinishedListener {

            void onFinished(Installment installment);

            void onMessage(String msg);

            void onFailure(Throwable t);
        }

        void loadPayerCosts(IContractInstallments.Model.OnFinishedListener onFinishedListener, String cardIssuerId);
    }
}
