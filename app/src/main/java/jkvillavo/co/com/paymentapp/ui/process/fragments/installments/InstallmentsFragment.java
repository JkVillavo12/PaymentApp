package jkvillavo.co.com.paymentapp.ui.process.fragments.installments;

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
import jkvillavo.co.com.paymentapp.data.model.PayerCost;
import jkvillavo.co.com.paymentapp.ui.process.adapter.AdapterPayerCost;
import jkvillavo.co.com.paymentapp.ui.process.events.CardIssuerEvent;

public class InstallmentsFragment extends Fragment implements
        IContractInstallments.View, AdapterPayerCost.AdapterPayerCostListener {

    @BindView ( R.id.installments_textViewMandatory )
    TextView installmentsTextViewMandatory;
    @BindView ( R.id.installments_recycler )
    RecyclerView installmentsRecycler;
    @BindView ( R.id.installments_empty )
    TextView installmentsEmpty;
    Unbinder unbinder;
    private IContractInstallments.Presenter presenter;

    private AdapterPayerCost adapterPayerCost;
    private List<PayerCost> listPayerCosts = new ArrayList<>();
    private PayerCost currentPayerCost;
    private IOnInstallmentsListener mListener;

    public InstallmentsFragment () {

    }

    public static InstallmentsFragment newInstance () {

        InstallmentsFragment fragment = new InstallmentsFragment();
        Bundle args = new Bundle();
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onAttach ( Context context ) {

        super.onAttach( context );
        if ( context instanceof IOnInstallmentsListener ) {
            mListener = ( IOnInstallmentsListener ) context;
        } else {
            throw new RuntimeException( context.toString() + " must implement IOnInstallmentsListener" );
        }
        EventBus.getDefault().register( InstallmentsFragment.this );
    }

    @Override
    public void onCreate ( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        //if (getArguments() != null) {
        //}
        presenter = new InstallmentsPresenter( this, getContext() );
    }

    @Override
    public void onStart () {

        super.onStart();

        adapterPayerCost = new AdapterPayerCost( listPayerCosts, this, this );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getContext() );
        installmentsRecycler.setLayoutManager( mLayoutManager );
        installmentsRecycler.setItemAnimator( new DefaultItemAnimator() );
        installmentsRecycler.setAdapter( adapterPayerCost );

        installmentsRecycler.setVisibility( View.GONE );
        installmentsEmpty.setVisibility( View.VISIBLE );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_installments, container, false );
        unbinder = ButterKnife.bind( this, view );
        return view;
    }

    @Override
    public void onDetach () {

        super.onDetach();
        mListener = null;
        EventBus.getDefault().unregister( InstallmentsFragment.this );
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
    public void showPayerCosts ( List<PayerCost> payerCostList ) {

        installmentsTextViewMandatory.setVisibility( View.GONE );
        for ( int i = listPayerCosts.size() - 1; i >= 0; i-- ) {
            this.listPayerCosts.remove( i );
            adapterPayerCost.notifyItemRemoved( i );
        }

        installmentsRecycler.setVisibility( View.VISIBLE );
        installmentsEmpty.setVisibility( View.GONE );
        this.listPayerCosts.addAll( payerCostList );
        adapterPayerCost.notifyDataSetChanged();

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

    @Subscribe
    public void onEvent ( CardIssuerEvent event ) {

        presenter.loadPayerCosts( event.getIssuerId() );
    }

    @Override
    public void onPayerCostSelected ( PayerCost payerCost ) {

        adapterPayerCost.notifyDataSetChanged();
        if ( payerCost.isSelected() ) {
            currentPayerCost = payerCost;
        } else {
            currentPayerCost = null;
        }

    }

    public Object validateInfo () {

        if ( currentPayerCost != null ) {
            installmentsTextViewMandatory.setVisibility( View.GONE );
            return currentPayerCost;
        } else {
            installmentsTextViewMandatory.setVisibility( View.VISIBLE );
            return null;
        }

    }

    public interface IOnInstallmentsListener {

        void onFailure ( Throwable t );

        void showMessage ( String msg );

        void showProgress ();

        void hideProgress ();

        void showSnackBarNetwork ();
    }
}
