package jkvillavo.co.com.paymentapp.ui.process.fragments.installments;

import java.util.List;

import jkvillavo.co.com.paymentapp.data.model.Installment;
import jkvillavo.co.com.paymentapp.data.model.PayerCost;
import jkvillavo.co.com.paymentapp.data.model.PaymentMethod;

public interface IContractInstallments {

    interface View {

        /**
         * SOlicita ocultar el progressBar
         */
        void hideProgress ();

        /**
         * Metodo que muestra un mensaje controlado al usuario indicando el fallo de un ws
         *
         * @param t
         */
        void onFailure ( Throwable t );

        /**
         * Muestra las distintas opciones de pago en el recycler
         *
         * @param payerCostList
         *         lista de opciones de pago
         */
        void showPayerCosts ( List<PayerCost> payerCostList );

        /**
         * Muestra un mensaje informativo al usuarui
         *
         * @param msg
         *         mensaje a mostrar
         */
        void showMessage ( String msg );

        /**
         * muestra la barra de progreso
         */
        void showProgress ();

        /**
         * Solicita mostrar el snackBar de no conexion
         */
        void showSnackBar ();
    }

    interface Presenter {

        /**
         * Se encarga de la logica para mostrar las opciones de pago
         *
         * @param cardIssuerId
         *         id del Issuer (banco) seleccionado
         */
        void loadPayerCosts ( String cardIssuerId );
    }

    interface Model {

        interface OnFinishedListener {

            /**
             * Metodo que se lanza al finalizar el llamado de un ws
             *
             * @param installment
             *         Informacion de las cuotas y opciones de pago
             */
            void onFinished ( Installment installment );

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
         * Metodo encargado de consumir el ws de opciones de pago
         *
         * @param onFinishedListener
         *         listener para el control de eventos
         * @param cardIssuerId
         *         id del issuer o banco seleccionado
         */
        void loadPayerCosts ( IContractInstallments.Model.OnFinishedListener onFinishedListener, String cardIssuerId );
    }
}
