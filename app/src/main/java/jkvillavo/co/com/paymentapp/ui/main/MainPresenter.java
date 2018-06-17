package jkvillavo.co.com.paymentapp.ui.main;

import android.content.Context;

public class MainPresenter implements IContractMain.Presenter {

    private IContractMain.View view;
    private IContractMain.Model model;

    public MainPresenter(IContractMain.View view) {

        this.view = view;
        this.model = new MainModel(this, (Context) view);
    }


}
