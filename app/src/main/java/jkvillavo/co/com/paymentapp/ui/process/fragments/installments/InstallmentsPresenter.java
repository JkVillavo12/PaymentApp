package jkvillavo.co.com.paymentapp.ui.process.fragments.installments;

import android.content.Context;

import jkvillavo.co.com.paymentapp.data.model.Installment;

public class InstallmentsPresenter implements IContractInstallments.Presenter, IContractInstallments.Model.OnFinishedListener {

    private IContractInstallments.View view;
    private IContractInstallments.Model model;

    public InstallmentsPresenter(IContractInstallments.View view, Context context) {

        this.view = view;
        this.model = new InstallmentsModel(this, context);
    }

    @Override
    public void loadPayerCosts(String cardIssuerId) {

        if (view != null) {
            view.showProgress();
            model.loadPayerCosts(this, cardIssuerId);
        }

    }

    @Override
    public void onFinished(Installment installment) {

        if (view != null) {
            view.hideProgress();
            view.showPayerCosts(installment.getPayerCosts());
        }

    }

    @Override
    public void onMessage(String msg) {

        if (view != null) {
            view.hideProgress();
            view.showMessage(msg);
        }

    }

    @Override
    public void onFailure(Throwable t) {

        if (view != null) {
            view.hideProgress();
            view.onFailure(t);
        }

    }
}
