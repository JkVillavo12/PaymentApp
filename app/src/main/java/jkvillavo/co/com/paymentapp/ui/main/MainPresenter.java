package jkvillavo.co.com.paymentapp.ui.main;

import android.content.Context;

import jkvillavo.co.com.paymentapp.util.NetworkHelper;

public class MainPresenter implements IContractMain.Presenter {

    private IContractMain.View view;
    private IContractMain.Model model;
    private Context mContext;

    public MainPresenter ( IContractMain.View view, Context context ) {

        this.view = view;
        this.model = new MainModel( this, context );
        mContext = context;
    }


    @Override
    public void pay () {

        if ( view != null ) {
            if ( NetworkHelper.getInstance().isNetworkAvailable( mContext ) ) {
                view.next();
            } else {
                view.showSnackBarNetwork();
            }

        }
    }
}
