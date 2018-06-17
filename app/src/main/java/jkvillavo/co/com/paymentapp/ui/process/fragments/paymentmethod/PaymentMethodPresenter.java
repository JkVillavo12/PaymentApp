package jkvillavo.co.com.paymentapp.ui.process.fragments.paymentmethod;

import android.content.Context;

import java.util.List;

import jkvillavo.co.com.paymentapp.data.model.PaymentMethod;

public class PaymentMethodPresenter implements IContractPaymentMethod.Presenter,
        IContractPaymentMethod.Model.OnFinishedListener {

    private IContractPaymentMethod.View view;
    private IContractPaymentMethod.Model model;

    public PaymentMethodPresenter(IContractPaymentMethod.View view, Context context) {

        this.view = view;
        this.model = new PaymentMethodModel(this, context);
    }

    @Override
    public void loadPaymentMethods() {

        if (view != null) {
            view.showProgress();
            model.loadPaymentMethods(this);
        }
    }

    @Override
    public void onFinished(List<PaymentMethod> methodList) {

        if (view != null) {
            view.hideProgress();
            view.showPaymentMethods(methodList);
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
