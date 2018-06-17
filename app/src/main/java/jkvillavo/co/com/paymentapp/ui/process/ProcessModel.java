package jkvillavo.co.com.paymentapp.ui.process;

import android.content.Context;

public class ProcessModel implements IContractProcess.Model {

    private IContractProcess.Presenter presenter;
    private Context mContext;

    public ProcessModel(IContractProcess.Presenter presenter, Context contex) {

        this.presenter = presenter;
        this.mContext = contex;

    }
}
