package jkvillavo.co.com.paymentapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jkvillavo.co.com.paymentapp.R;
import jkvillavo.co.com.paymentapp.ui.main.MainActivity;
import jkvillavo.co.com.paymentapp.util.Constants;

public class SplashActivity extends AppCompatActivity implements IContractSplash.View {

    @BindView(R.id.splash_image)
    ImageView splashImage;

    private IContractSplash.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        presenter = new SplashPresenter(this);
    }

    @Override
    protected void onStart() {

        super.onStart();
        presenter.next(Constants.Time.SEGS_1);

    }

    @Override
    public void nextMain() {

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, (View) splashImage, getResources().getString(R.string.transition_mercadoLibre));
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent, options.toBundle());

    }
}
