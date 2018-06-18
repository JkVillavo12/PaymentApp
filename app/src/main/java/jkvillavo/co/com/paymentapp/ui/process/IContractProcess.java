package jkvillavo.co.com.paymentapp.ui.process;

import android.support.v4.app.Fragment;

import jkvillavo.co.com.paymentapp.data.model.CardIssuer;
import jkvillavo.co.com.paymentapp.data.model.PayerCost;
import jkvillavo.co.com.paymentapp.data.model.PaymentMethod;

public interface IContractProcess {

    interface View {

        /**
         * Finaliza el proceso de seleccion de medio de pago
         */
        void finishProcess ();

        /**
         * Selecciona una pagina en el viewPager
         *
         * @param pageIssuer
         *         pagina a seleccionar
         */
        void selectPage ( int pageIssuer );

        /**
         * Setea el método de pago seleccionado en la actividad, para despues ser enviado a la vista principal
         *
         * @param paymentMethod
         *         medio de pago seleccionado
         */
        void setPaymentMethod ( PaymentMethod paymentMethod );

        /**
         * Setea el banco o entidad seleccionada, para despues ser enviada a la vista principal
         *
         * @param cardIssuer
         *         banco seleccionado
         */
        void setIssuer ( CardIssuer cardIssuer );

        /**
         * Setea el modo de pago (cuotas) seleccionada para el pago
         *
         * @param payerCost
         *         informacion de las cuotas seleccionada
         */
        void setPayerCost ( PayerCost payerCost );

        /**
         * Muestra en la actividad el monto de la compra
         *
         * @param textToShow
         *         monto formateado a decimales
         */
        void showAmount ( String textToShow );

        /**
         * Muestra un texto explicativo según la información que se requiera pedir al usuario
         *
         * @param string
         *         texto a mostrar
         */
        void showTextExplain ( String string );

        /**
         * Indica si debemos mostrar el boton atras del proceso
         *
         * @param show
         *         boolean que indica si mostrar u ocultar
         */
        void showBack ( boolean show );


    }

    interface Presenter {

        /**
         * Contiene la logica necesaria para el boton back
         *
         * @param currentPage
         *         pagina actual
         */
        void back ( int currentPage );

        /**
         * Cambia el texto explicativo segun la pagina seleccionada
         *
         * @param position
         */
        void changeTextExplain ( int position );

        /**
         * Carga el monto de la compra
         */
        void loadAmount ();

        /**
         * Continua el proceso realizando el cambio de pagina
         *
         * @param mFragment
         *         fragmento actual
         * @param currentPage
         *         pagina actual
         */
        void next ( Fragment mFragment, int currentPage );
    }

    interface Model {


    }
}
