package jkvillavo.co.com.paymentapp.ui.process;

import android.content.Context;
import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;

import jkvillavo.co.com.paymentapp.R;
import jkvillavo.co.com.paymentapp.data.model.CardIssuer;
import jkvillavo.co.com.paymentapp.data.model.PayerCost;
import jkvillavo.co.com.paymentapp.data.model.PaymentMethod;
import jkvillavo.co.com.paymentapp.ui.process.events.CardIssuerEvent;
import jkvillavo.co.com.paymentapp.ui.process.events.PaymentMethodEvent;
import jkvillavo.co.com.paymentapp.ui.process.fragments.cardissuer.CardIssuerFragment;
import jkvillavo.co.com.paymentapp.ui.process.fragments.installments.InstallmentsFragment;
import jkvillavo.co.com.paymentapp.ui.process.fragments.paymentmethod.PaymentMethodFragment;
import jkvillavo.co.com.paymentapp.util.UiUtils;
import jkvillavo.co.com.paymentapp.util.prefs.Prefs;
import jkvillavo.co.com.paymentapp.util.prefs.PrefsKey;

public class ProcessPresenter implements IContractProcess.Presenter {

    private IContractProcess.View view;
    private IContractProcess.Model model;

    public ProcessPresenter(IContractProcess.View view) {

        this.view = view;
        this.model = new ProcessModel(this, (Context) view);
    }

    @Override
    public void back(int currentPage) {

        if (view != null) {
            switch (currentPage) {
                case UiUtils.Pages.PAGE_ISSUER:
                    view.selectPage(UiUtils.Pages.PAGE_PAYMENTMETHOD);
                    view.showBack(false);
                    break;
                case UiUtils.Pages.PAGE_INSTALLMENTS:
                    view.selectPage(UiUtils.Pages.PAGE_ISSUER);
                    view.showBack(true);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void changeTextExplain(int position) {

        if (view != null) {
            if (position >= 0) {
                switch (position) {
                    case UiUtils.Pages.PAGE_PAYMENTMETHOD:
                        view.showTextExplain(((Context) view).getResources().getString(R.string.msg_titlePaymentMethod));
                        break;
                    case UiUtils.Pages.PAGE_ISSUER:
                        view.showTextExplain(((Context) view).getResources().getString(R.string.msg_titleBank));
                        break;
                    case UiUtils.Pages.PAGE_INSTALLMENTS:
                        view.showTextExplain(((Context) view).getResources().getString(R.string.msg_titleInstallments));
                        break;
                    default:
                        break;
                }
            }
        }

    }

    @Override
    public void loadAmount() {

        if (view != null) {

            long amount = Prefs.getLongData(PrefsKey.Process.AMOUNT, (Context) view);
            String textToShow = ((Context) view).getResources().getString(R.string.text_amount, UiUtils.formatDecimal(amount, UiUtils.Formats.DECIMAL));
            textToShow = textToShow.replaceAll(",", ".");
            view.showAmount(textToShow);

        }

    }

    @Override
    public void next(Fragment mFragment, int currentPage) {

        if (view != null) {
            switch (currentPage) {
                case UiUtils.Pages.PAGE_PAYMENTMETHOD:
                    PaymentMethod paymentMethod = (PaymentMethod) ((PaymentMethodFragment) mFragment).validateInfo();
                    if (paymentMethod != null) {
                        Prefs.save((Context) view, PrefsKey.Process.PAYMENT_METHOD_ID, paymentMethod.getId());
                        EventBus.getDefault().post(new PaymentMethodEvent(paymentMethod.getId()));
                        view.selectPage(UiUtils.Pages.PAGE_ISSUER);
                        view.setPaymentMethod(paymentMethod);
                        view.showBack(true);
                    }
                    break;
                case UiUtils.Pages.PAGE_ISSUER:
                    CardIssuer cardIssuer = (CardIssuer) ((CardIssuerFragment) mFragment).validateInfo();
                    if (cardIssuer != null) {
                        Prefs.save((Context) view, PrefsKey.Process.ISSUER_ID, cardIssuer.getId());
                        EventBus.getDefault().post(new CardIssuerEvent(cardIssuer.getId()));
                        view.selectPage(UiUtils.Pages.PAGE_INSTALLMENTS);
                        view.setIssuer(cardIssuer);
                        view.showBack(true);
                    }
                    break;
                case UiUtils.Pages.PAGE_INSTALLMENTS:
                    PayerCost payerCost = (PayerCost) ((InstallmentsFragment) mFragment).validateInfo();
                    if (payerCost != null) {
                        view.setPayerCost(payerCost);
                        view.finishProcess();
                    }
                    break;
                default:
                    break;
            }
        }


    }
}
