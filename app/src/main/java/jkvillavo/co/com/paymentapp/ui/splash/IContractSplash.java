package jkvillavo.co.com.paymentapp.ui.splash;

public class IContractSplash {

    interface View {

        void nextMain();
    }

    interface Presenter {

        void next(int i);

        void nextMain();
    }

    interface Model {

        void next();

    }
}
