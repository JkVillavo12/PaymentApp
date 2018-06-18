package jkvillavo.co.com.paymentapp.ui.process.fragments.installments;

import android.content.Context;

import jkvillavo.co.com.paymentapp.data.model.Installment;
import jkvillavo.co.com.paymentapp.util.NetworkHelper;

public class InstallmentsPresenter implements IContractInstallments.Presenter, IContractInstallments.Model.OnFinishedListener {

    private IContractInstallments.View view;
    private IContractInstallments.Model model;
    private Context mContext;

    public InstallmentsPresenter ( IContractInstallments.View view, Context context ) {

        this.view = view;
        this.model = new InstallmentsModel( this, context );
        this.mContext = context;

    }

    @Override
    public void loadPayerCosts ( String cardIssuerId ) {

        if ( view != null ) {
            view.showProgress();
            if ( NetworkHelper.getInstance().isNetworkAvailable( mContext ) ) {
                model.loadPayerCosts( this, cardIssuerId );
            } else {
                view.hideProgress();
                view.showSnackBar();
            }

        }

    }

    @Override
    public void onFinished ( Installment installment ) {

        if ( view != null ) {
            view.hideProgress();
            view.showPayerCosts( installment.getPayerCosts() );
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
