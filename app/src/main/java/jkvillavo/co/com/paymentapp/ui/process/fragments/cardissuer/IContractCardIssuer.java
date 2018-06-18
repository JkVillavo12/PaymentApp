package jkvillavo.co.com.paymentapp.ui.process.fragments.cardissuer;

import java.util.List;

import jkvillavo.co.com.paymentapp.data.model.CardIssuer;
import jkvillavo.co.com.paymentapp.data.model.Installment;

public interface IContractCardIssuer {

    interface View {

        /**
         * Oculta la vista de Empty results
         */
        void hideEmptyResult ();

        /**
         * Solicita ocultar el progress bar
         */
        void hideProgress ();

        /**
         * SOlicita mostrar al usuario un mensaje de fallo en el ws
         *
         * @param t
         *         throwable a controlar
         */
        void onFailure ( Throwable t );

        /**
         * Muestra la lista de issuers o bancos disponibles para la creditCard seleccionada
         *
         * @param cardIssuerList
         *         lista de issuers
         */
        void showCardIssuers ( List<CardIssuer> cardIssuerList );

        /**
         * SOlicita mostrar la vista de empty results
         */
        void showEmptyResult ();

        /**
         * Solicita mostrar un mensaje al usuario
         *
         * @param msg
         *         mensaje a mostrar
         */
        void showMessage ( String msg );

        /**
         * Solicita mostrar el progress bar
         */
        void showProgress ();

        /**
         * Solicita mostrar el sanckBar de no conexion
         */
        void showSnackBar ();
    }

    interface Presenter {

        /**
         * Se encarga de la logica para cargar la lista de issuers disponibles para la tarjeta de credito seleccionada
         *
         * @param paymentMethodId
         *         id del metodo de pago seleccionado
         */
        void loadCardIssuers ( String paymentMethodId );
    }

    interface Model {

        interface OnFinishedListener {

            /**
             * Metodo que se lanza al finalizar el llamado de un ws
             *
             * @param cardIssuerList
             *         Informacion de los bancos disponibles
             */
            void onFinished ( List<CardIssuer> cardIssuerList );

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
         * Método encargado de consultar via ws los bancos disponibles para una tarjeta de credito seleccionada
         *
         * @param onFinishedListener
         *         listener para controlar eventos
         * @param paymentMethodId
         *         identificador del metodo de pago seleccionado
         */
        void loadCardIssuers ( OnFinishedListener onFinishedListener, String paymentMethodId );
    }
}
