package jkvillavo.co.com.paymentapp.ui.process;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import jkvillavo.co.com.paymentapp.R;
import jkvillavo.co.com.paymentapp.data.model.CardIssuer;
import jkvillavo.co.com.paymentapp.data.model.PayerCost;
import jkvillavo.co.com.paymentapp.data.model.PaymentMethod;
import jkvillavo.co.com.paymentapp.ui.process.adapter.AdapterOnProcess;
import jkvillavo.co.com.paymentapp.ui.process.fragments.cardissuer.CardIssuerFragment;
import jkvillavo.co.com.paymentapp.ui.process.fragments.installments.InstallmentsFragment;
import jkvillavo.co.com.paymentapp.ui.process.fragments.paymentmethod.PaymentMethodFragment;
import jkvillavo.co.com.paymentapp.ui.process.viewpager.NoSwipeViewPager;
import jkvillavo.co.com.paymentapp.util.ExtrasKey;
import jkvillavo.co.com.paymentapp.util.UiUtils;

public class ProcessActivity extends AppCompatActivity implements IContractProcess.View,
        PaymentMethodFragment.IOnCreditCardFragment, CardIssuerFragment.IOnIssuerFragment,
        InstallmentsFragment.IOnInstallmentsListener {

    @BindView(R.id.process_viewPager)
    NoSwipeViewPager processViewPager;
    @BindView(R.id.process_progressBar)
    ProgressBar processProgressBar;
    @BindView(R.id.process_textViewExplain)
    TextView processTextViewExplain;
    @BindView(R.id.process_textViewAmount)
    TextView processTextViewAmount;
    @BindView(R.id.process_btnBack)
    ImageButton processBtnBack;
    @BindView(R.id.process_btnNext)
    ImageButton processBtnNext;
    private IContractProcess.Presenter presenter;
    private AdapterOnProcess adapterOnProcess;

    private int currentPage = 0;
    private PaymentMethod currenPayment;
    private CardIssuer currentIssuer;
    private PayerCost currentPayerCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapterOnProcess = new AdapterOnProcess(getSupportFragmentManager());
        processViewPager.setAdapter(adapterOnProcess);

        presenter = new ProcessPresenter(this);
        showBack(false);
    }

    @Override
    protected void onStart() {

        super.onStart();

        setListenerViewPager();
        changeTextExplain(UiUtils.Pages.PAGE_PAYMENTMETHOD);
        loadAmount();

    }

    private void loadAmount() {

        presenter.loadAmount();

    }

    private void setListenerViewPager() {

        processViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                currentPage = position;
                changeTextExplain(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

    }

    private void changeTextExplain(int position) {

        presenter.changeTextExplain(position);

    }

    public void back(View view) {

        presenter.back(currentPage);


    }

    public void next(View view) {

        /*
            Debemos tomar el fragmento que se esta viendo y validar si el usuario hizo el proceso en
            el fragmento, si lo hizo continuamos a la siguiente pantalla
         */
        Fragment mFragment = adapterOnProcess.getRegisteredFragment(processViewPager.getCurrentItem());
        presenter.next(mFragment, currentPage);

    }

    @Override
    public void onFailure(Throwable t) {

        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

        processProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

        processProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void finishProcess() {

        Intent returnIntent = new Intent();
        returnIntent.putExtra(ExtrasKey.Extras.PAYMENTMETHOD_NAME, currenPayment.getName());
        returnIntent.putExtra(ExtrasKey.Extras.PAYMENTMETHOD_IMAGE, currenPayment.getSecureThumbnail());
        returnIntent.putExtra(ExtrasKey.Extras.ISSUER_NAME, currentIssuer.getName());
        returnIntent.putExtra(ExtrasKey.Extras.ISSUER_IMAGE, currentIssuer.getSecureThumbnail());
        returnIntent.putExtra(ExtrasKey.Extras.PAYERCOST, currentPayerCost.getRecommendedMessage());

        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }

    @Override
    public void onBackPressed() {

        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();

    }

    @Override
    public void selectPage(int pagePosition) {
        processViewPager.setCurrentItem(pagePosition);
    }

    @Override
    public void setPaymentMethod(PaymentMethod paymentMethod) {

        currenPayment = paymentMethod;
    }

    @Override
    public void setIssuer(CardIssuer cardIssuer) {

        currentIssuer = cardIssuer;

    }

    @Override
    public void setPayerCost(PayerCost payerCost) {

        currentPayerCost = payerCost;

    }

    @Override
    public void showAmount(String amount) {

        processTextViewAmount.setText(amount);

    }

    @Override
    public void showTextExplain(String string) {

        processTextViewExplain.setText(string);

    }

    @Override
    public void showBack(boolean show) {

        if (show) {
            processBtnBack.setVisibility(View.VISIBLE);
        } else {
            processBtnBack.setVisibility(View.INVISIBLE);
        }

    }
}
