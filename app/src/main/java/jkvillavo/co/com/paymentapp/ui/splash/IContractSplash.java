package jkvillavo.co.com.paymentapp.ui.splash;

public class IContractSplash {

    interface View {

        /**
         * Dirije la app a la pantalla main
         */
        void nextMain ();
    }

    interface Presenter {

        /**
         * Logica para esperar un poco e ir a la siguiente pantalla
         *
         * @param i
         *         milliseconds a esperar
         */
        void next ( int i );

        /**
         * Da la orden de continuar con la siguiente pantalla
         */
        void nextMain ();
    }

    interface Model {

        /**
         * Modelo encargado de tomar la decision de a donde dirijir la app
         */
        void next ();

    }
}
