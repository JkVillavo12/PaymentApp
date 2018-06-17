package jkvillavo.co.com.paymentapp.ui.splash;

import android.content.Context;
import android.os.Handler;

public class SplashPresenter implements IContractSplash.Presenter {

    private IContractSplash.View view;
    private IContractSplash.Model model;

    public SplashPresenter(IContractSplash.View view) {

        this.view = view;
        this.model = new SplashModel(this, (Context) view);
    }

    @Override
    public void next(int millis) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            public void run() {

                model.next();

            }
        }, millis);

    }

    @Override
    public void nextMain() {

        if (view != null) {
            view.nextMain();
        }
    }
}
