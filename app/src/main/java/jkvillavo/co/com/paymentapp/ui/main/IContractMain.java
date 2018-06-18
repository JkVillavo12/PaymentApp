package jkvillavo.co.com.paymentapp.ui.main;

public interface IContractMain {

    interface View {

        /**
         * Método que nos lleva a la siguiente actividad donde haremos el proceso de seleccionar el medio de pago
         */
        void next ();

        /**
         * Muestra un snackBar informando al usuario del estado de la conexion a internet
         */
        void showSnackBarNetwork ();
    }

    interface Presenter {

        /**
         * Contiene la lógica necesaria para iniciar el pago
         */
        void pay ();
    }

    interface Model {


    }

}
