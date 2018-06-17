package jkvillavo.co.com.paymentapp.ui.process.fragments.paymentmethod;

import java.util.List;

import jkvillavo.co.com.paymentapp.data.model.PaymentMethod;

public interface IContractPaymentMethod {

    interface View {

        void hideProgress();

        void onFailure(Throwable t);

        void showMessage(String msg);

        void showPaymentMethods(List<PaymentMethod> methodList);

        void showProgress();
    }

    interface Presenter {

        void loadPaymentMethods();
    }

    interface Model {

        interface OnFinishedListener {

            void onFinished(List<PaymentMethod> methodList);

            void onMessage(String msg);

            void onFailure(Throwable t);
        }

        void loadPaymentMethods(OnFinishedListener onFinishedListener);
    }

}
