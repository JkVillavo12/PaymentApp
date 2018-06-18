package jkvillavo.co.com.paymentapp.ui.process.fragments.cardissuer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jkvillavo.co.com.paymentapp.R;
import jkvillavo.co.com.paymentapp.data.model.CardIssuer;
import jkvillavo.co.com.paymentapp.ui.process.adapter.AdapterCardIssuer;
import jkvillavo.co.com.paymentapp.ui.process.events.PaymentMethodEvent;

public class CardIssuerFragment extends Fragment implements IContractCardIssuer.View,
        AdapterCardIssuer.AdapterCardIssuerListener {

    @BindView ( R.id.cardIssuer_textViewMandatory )
    TextView cardIssuerTextViewMandatory;
    @BindView ( R.id.cardIssuer_recycler )
    RecyclerView cardIssuerRecycler;
    @BindView ( R.id.cardIssuer_empty )
    TextView cardIssuerEmpty;
    Unbinder unbinder;
    @BindView ( R.id.cardIssuer_notResult )
    TextView cardIssuerNotResult;
    private IOnIssuerFragment mListener;
    private IContractCardIssuer.Presenter presenter;

    private AdapterCardIssuer adapterCardIssuer;
    private List<CardIssuer> listIssuers = new ArrayList<>();
    private CardIssuer currentCardIssuer;

    public CardIssuerFragment () {

    }

    public static CardIssuerFragment newInstance () {

        CardIssuerFragment fragment = new CardIssuerFragment();
        Bundle args = new Bundle();
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onAttach ( Context context ) {

        super.onAttach( context );
        if ( context instanceof IOnIssuerFragment ) {
            mListener = ( IOnIssuerFragment ) context;
        } else {
            throw new RuntimeException( context.toString() + " must implement IOnIssuerFragment" );
        }
        EventBus.getDefault().register( CardIssuerFragment.this );
    }

    @Override
    public void onCreate ( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        presenter = new CardIssuerPresenter( this, getContext() );

    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {

        View view = inflater.inflate( R.layout.fragment_card_issuer, container, false );
        unbinder = ButterKnife.bind( this, view );
        return view;

    }

    @Override
    public void onStart () {

        super.onStart();

        adapterCardIssuer = new AdapterCardIssuer( listIssuers, this, this );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getContext() );
        cardIssuerRecycler.setLayoutManager( mLayoutManager );
        cardIssuerRecycler.setItemAnimator( new DefaultItemAnimator() );
        cardIssuerRecycler.setAdapter( adapterCardIssuer );

        cardIssuerRecycler.setVisibility( View.GONE );
        cardIssuerEmpty.setVisibility( View.VISIBLE );

    }

    @Override
    public void onDetach () {

        super.onDetach();
        mListener = null;
        EventBus.getDefault().unregister( CardIssuerFragment.this );
    }

    @Override
    public void hideEmptyResult () {

        cardIssuerNotResult.setVisibility( View.GONE );

    }

    @Override
    public void hideProgress () {

        mListener.hideProgress();

    }

    @Override
    public void onFailure ( Throwable t ) {

        mListener.onFailure( t );
    }

    @Override
    public void showCardIssuers ( List<CardIssuer> cardIssuerList ) {

        cardIssuerTextViewMandatory.setVisibility( View.GONE );
        for ( int i = listIssuers.size() - 1; i >= 0; i-- ) {
            this.listIssuers.remove( i );
            adapterCardIssuer.notifyItemRemoved( i );
        }

        cardIssuerRecycler.setVisibility( View.VISIBLE );
        cardIssuerEmpty.setVisibility( View.GONE );
        this.listIssuers.addAll( cardIssuerList );
        adapterCardIssuer.notifyDataSetChanged();

    }

    @Override
    public void showEmptyResult () {

        cardIssuerNotResult.setVisibility( View.VISIBLE );
        currentCardIssuer = null;

    }

    @Override
    public void showMessage ( String msg ) {

        mListener.showMessage( msg );
    }

    @Override
    public void showProgress () {

        mListener.showProgress();

    }

    @Override
    public void showSnackBar () {

        mListener.showSnackBarNetwork();
    }

    @Override
    public void onDestroyView () {

        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCardIssuerSelected ( CardIssuer cardIssuer ) {

        adapterCardIssuer.notifyDataSetChanged();
        if ( cardIssuer.isSelected() ) {
            currentCardIssuer = cardIssuer;
        } else {
            currentCardIssuer = null;
        }

    }

    @Subscribe
    public void onEvent ( PaymentMethodEvent event ) {

        presenter.loadCardIssuers( event.getPaymentMethodId() );
    }

    public Object validateInfo () {

        if ( currentCardIssuer != null ) {
            cardIssuerTextViewMandatory.setVisibility( View.GONE );
            return currentCardIssuer;
        } else {
            cardIssuerTextViewMandatory.setVisibility( View.VISIBLE );
            return null;
        }

    }

    public interface IOnIssuerFragment {

        void onFailure ( Throwable t );

        void showMessage ( String msg );

        void showProgress ();

        void hideProgress ();

        void showSnackBarNetwork ();
    }
}
