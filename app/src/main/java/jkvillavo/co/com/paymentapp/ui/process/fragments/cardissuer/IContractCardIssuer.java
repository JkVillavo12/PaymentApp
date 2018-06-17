package jkvillavo.co.com.paymentapp.ui.process.fragments.cardissuer;

import java.util.List;

import jkvillavo.co.com.paymentapp.data.model.CardIssuer;

public interface IContractCardIssuer {

    interface View {

        void hideEmptyResult();

        void hideProgress();

        void onFailure(Throwable t);

        void showCardIssuers(List<CardIssuer> cardIssuerList);

        void showEmptyResult();

        void showMessage(String msg);

        void showProgress();
    }

    interface Presenter {

        void loadCardIssuers(String paymentMethodId);
    }

    interface Model {

        interface OnFinishedListener {

            void onFinished(List<CardIssuer> cardIssuerList);

            void onMessage(String msg);

            void onFailure(Throwable t);
        }

        void loadCardIssuers(OnFinishedListener onFinishedListener, String paymentMethodId);
    }
}
