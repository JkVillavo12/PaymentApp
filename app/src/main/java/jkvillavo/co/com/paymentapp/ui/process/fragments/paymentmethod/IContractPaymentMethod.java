package jkvillavo.co.com.paymentapp.ui.process.fragments.paymentmethod;

import java.util.List;

import jkvillavo.co.com.paymentapp.data.model.PaymentMethod;

public interface IContractPaymentMethod {

    interface View {

        /**
         * Solicita ocultar el progressBar
         */
        void hideProgress ();

        /**
         * metodo para informar que el consumo del WS ha fallado
         *
         * @param t
         *         throwable a mostrar
         */
        void onFailure ( Throwable t );

        /**
         * Solicita mostrar un mensaje al usuario
         *
         * @param msg
         *         mensaje a mostrar
         */
        void showMessage ( String msg );

        /**
         * Muestra los medios de pago disponibles
         *
         * @param methodList
         *         lista de metodos de pago
         */
        void showPaymentMethods ( List<PaymentMethod> methodList );

        /**
         * solicita mostrar el progressBar
         */
        void showProgress ();

        /**
         * Solicita mostrar el snackBar de no conexion
         */
        void showSnackBarNetwork ();
    }

    interface Presenter {

        /**
         * Se encarga de la logica para cargar los medios de pago
         */
        void loadPaymentMethods ();
    }

    interface Model {

        interface OnFinishedListener {

            /**
             * Metodo que se lanza al finalizar el llamado de un ws
             *
             * @param methodList
             *         lista de medios de pago
             */
            void onFinished ( List<PaymentMethod> methodList );

            /**
             * Metodo que se lanza cuando querremos informar por medio de un mensaje una situacion en el ws
             *
             * @param msg
             *         mensaje a mostrar
             */
            void onMessage ( String msg );

            /**
             * Metodo que se lanza cuando el ws falla
             *
             * @param t
             *         throwable a manejar
             */
            void onFailure ( Throwable t );
        }

        /**
         * Se encarga de la consultar via ws de los metodos de pago
         *
         * @param onFinishedListener
         *         listener para controlar los eventos
         */
        void loadPaymentMethods ( OnFinishedListener onFinishedListener );
    }

}
