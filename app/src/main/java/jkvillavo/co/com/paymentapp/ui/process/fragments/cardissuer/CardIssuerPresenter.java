package jkvillavo.co.com.paymentapp.ui.process.fragments.cardissuer;

import android.content.Context;

import java.util.List;

import jkvillavo.co.com.paymentapp.data.model.CardIssuer;
import jkvillavo.co.com.paymentapp.util.NetworkHelper;

public class CardIssuerPresenter implements IContractCardIssuer.Presenter,
        IContractCardIssuer.Model.OnFinishedListener {

    private IContractCardIssuer.View view;
    private IContractCardIssuer.Model model;
    private Context mContext;

    public CardIssuerPresenter ( IContractCardIssuer.View view, Context context ) {

        this.view = view;
        this.model = new CardIssuerModel( this, context );
        this.mContext = context;
    }

    @Override
    public void loadCardIssuers ( String paymentMethodId ) {

        if ( view != null ) {
            view.showProgress();
            if ( NetworkHelper.getInstance().isNetworkAvailable( ( Context ) view ) ) {
                model.loadCardIssuers( this, paymentMethodId );
            } else {
                view.hideProgress();
                view.showSnackBar();
            }
        }

    }

    @Override
    public void onFinished ( List<CardIssuer> cardIssuerList ) {

        if ( view != null ) {
            view.hideProgress();
            if ( cardIssuerList.isEmpty() ) {
                view.showCardIssuers( cardIssuerList );
                view.showEmptyResult();
            } else {
                view.showCardIssuers( cardIssuerList );
                view.hideEmptyResult();
            }
        }


    }

    @Override
    public void onMessage ( String msg ) {

        if ( view != null ) {
            view.hideProgress();
            view.showMessage( msg );
        }

    }

    @Override
    public void onFailure ( Throwable t ) {

        if ( view != null ) {
            view.hideProgress();
            view.onFailure( t );
        }

    }
}
