package jkvillavo.co.com.paymentapp.ui.process;

import android.support.v4.app.Fragment;

import jkvillavo.co.com.paymentapp.data.model.CardIssuer;
import jkvillavo.co.com.paymentapp.data.model.PayerCost;
import jkvillavo.co.com.paymentapp.data.model.PaymentMethod;

public interface IContractProcess {

    interface View {

        void finishProcess();

        void selectPage(int pageIssuer);

        void setPaymentMethod(PaymentMethod paymentMethod);

        void setIssuer(CardIssuer cardIssuer);

        void setPayerCost(PayerCost payerCost);

        void showAmount(String textToShow);

        void showTextExplain(String string);

        void showBack(boolean show);


    }

    interface Presenter {

        void back(int currentPage);

        void changeTextExplain(int position);

        void loadAmount();

        void next(Fragment mFragment, int currentPage);
    }

    interface Model {


    }
}
