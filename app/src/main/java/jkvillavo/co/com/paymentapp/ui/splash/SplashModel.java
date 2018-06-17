package jkvillavo.co.com.paymentapp.ui.splash;

import android.content.Context;

public class SplashModel implements IContractSplash.Model {

    private IContractSplash.Presenter presenter;
    private Context mContext;

    public SplashModel(IContractSplash.Presenter presenter, Context contex) {

        this.presenter = presenter;
        this.mContext = contex;

    }

    @Override
    public void next() {

        presenter.nextMain();

    }
}
