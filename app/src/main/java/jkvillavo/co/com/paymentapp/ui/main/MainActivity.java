package jkvillavo.co.com.paymentapp.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import jkvillavo.co.com.paymentapp.R;
import jkvillavo.co.com.paymentapp.ui.process.ProcessActivity;
import jkvillavo.co.com.paymentapp.util.ExtrasKey;
import jkvillavo.co.com.paymentapp.util.UiUtils;
import jkvillavo.co.com.paymentapp.util.prefs.Prefs;
import jkvillavo.co.com.paymentapp.util.prefs.PrefsKey;

public class MainActivity extends AppCompatActivity implements IContractMain.View {

    @BindView(R.id.main_editTextAmount)
    TextInputEditText mainEditTextAmount;
    @BindView(R.id.main_textInputAmount)
    TextInputLayout mainTextInputAmount;
    @BindView(R.id.main_image)
    ImageView mainImage;
    @BindView(R.id.main_textPresentation)
    TextView mainTextPresentation;
    @BindView(R.id.main_imageAmount)
    ImageView mainImageAmount;
    @BindView(R.id.main_btnPay)
    Button mainBtnPay;
    @BindView(R.id.main_circle1)
    View mainCircle1;
    @BindView(R.id.main_paymentText)
    TextView mainPaymentText;
    @BindView(R.id.main_paymentTextValue)
    TextView mainPaymentTextValue;
    @BindView(R.id.main_imagePayment)
    ImageView mainImagePayment;
    @BindView(R.id.main_circle2)
    View mainCircle2;
    @BindView(R.id.main_issuerText)
    TextView mainIssuerText;
    @BindView(R.id.main_issuerTextValue)
    TextView mainIssuerTextValue;
    @BindView(R.id.main_imageIssuer)
    ImageView mainImageIssuer;
    @BindView(R.id.main_circle3)
    View mainCircle3;
    @BindView(R.id.main_payerCostText)
    TextView mainPayerCostText;
    @BindView(R.id.main_payerCostTextValue)
    TextView mainPayerCostTextValue;
    @BindView(R.id.main_layoutSummary)
    LinearLayout mainLayoutSummary;

    private IContractMain.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);
        mainLayoutSummary.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
    }

    public void pay(View view) {

        int errores = 0;
        errores += UiUtils.validadEditTextWithTextInput(mainEditTextAmount, mainTextInputAmount, getApplicationContext());

        if (errores > 0) {
            long amount = Long.parseLong(mainEditTextAmount.getText().toString());
            Prefs.save(MainActivity.this, PrefsKey.Process.AMOUNT, amount);
            Intent intent = new Intent(MainActivity.this, ProcessActivity.class);
            startActivityForResult(intent, ExtrasKey.RequestCode.PAY);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ExtrasKey.RequestCode.PAY) {

            if (resultCode == Activity.RESULT_OK) {

                mainLayoutSummary.setVisibility(View.VISIBLE);
                loadDataPay(data);

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                mainLayoutSummary.setVisibility(View.GONE);
            }

        }

    }

    private void loadDataPay(Intent data) {

        mainPaymentTextValue.setText(data.getStringExtra(ExtrasKey.Extras.PAYMENTMETHOD_NAME));
        mainIssuerTextValue.setText(data.getStringExtra(ExtrasKey.Extras.ISSUER_NAME));
        mainPayerCostTextValue.setText(data.getStringExtra(ExtrasKey.Extras.PAYERCOST));

        Glide.with(MainActivity.this).load(data.getStringExtra(ExtrasKey.Extras.PAYMENTMETHOD_IMAGE)).into(mainImagePayment);
        Glide.with(MainActivity.this).load(data.getStringExtra(ExtrasKey.Extras.ISSUER_IMAGE)).into(mainImageIssuer);

    }
}
