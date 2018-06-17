package jkvillavo.co.com.paymentapp.ui.main;

import android.content.Context;

public class MainModel implements IContractMain.Model {

    private IContractMain.Presenter presenter;
    private Context mContext;

    public MainModel(IContractMain.Presenter presenter, Context contex) {

        this.presenter = presenter;
        this.mContext = contex;

    }

}
