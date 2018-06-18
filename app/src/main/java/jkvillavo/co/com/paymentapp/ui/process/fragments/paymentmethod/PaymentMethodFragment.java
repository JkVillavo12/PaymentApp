package jkvillavo.co.com.paymentapp.ui.process.fragments.paymentmethod;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jkvillavo.co.com.paymentapp.R;
import jkvillavo.co.com.paymentapp.data.model.PaymentMethod;
import jkvillavo.co.com.paymentapp.ui.process.adapter.AdapterPaymentMethod;

public class PaymentMethodFragment extends Fragment implements IContractPaymentMethod.View,
        AdapterPaymentMethod.AdapterPaymentMethodListener {

    Unbinder unbinder;
    @BindView(R.id.paymentMethod_recycler)
    RecyclerView paymentMethodRecycler;
    @BindView(R.id.paymentMethod_empty)
    TextView paymentMethodEmpty;
    @BindView(R.id.paymentMethod_textViewMandatory)
    TextView paymentMethodTextViewMandatory;

    private IContractPaymentMethod.Presenter presenter;

    private AdapterPaymentMethod adapterPaymentMethod;
    private List<PaymentMethod> listPaymentMethods = new ArrayList<>();
    private PaymentMethod currentPaymentMethod;
    private IOnCreditCardFragment mListener;

    public PaymentMethodFragment() {
    }

    public interface IOnCreditCardFragment {

        void onFailure(Throwable t);

        void showMessage(String msg);

        void showProgress();

        void hideProgress();

        void showSnackBarNetwork ();
    }

    public static PaymentMethodFragment newInstance() {
        PaymentMethodFragment fragment = new PaymentMethodFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOnCreditCardFragment) {
            mListener = (IOnCreditCardFragment) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement IOnCreditCardFragment");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PaymentMethodPresenter(this, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_paymentmethod, container, false);
        unbinder = ButterKnife.bind(this, view);

        adapterPaymentMethod = new AdapterPaymentMethod(listPaymentMethods, this, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        paymentMethodRecycler.setLayoutManager(mLayoutManager);
        paymentMethodRecycler.setItemAnimator(new DefaultItemAnimator());
        paymentMethodRecycler.setAdapter(adapterPaymentMethod);

        paymentMethodRecycler.setVisibility(View.GONE);
        paymentMethodEmpty.setVisibility(View.VISIBLE);
        presenter.loadPaymentMethods();

        return view;

    }

    @Override
    public void onStart() {

        super.onStart();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void hideProgress() {
        mListener.hideProgress();
    }

    @Override
    public void onFailure(Throwable t) {

        mListener.onFailure(t);
    }

    @Override
    public void showMessage(String msg) {

        mListener.showMessage(msg);
    }

    @Override
    public void showPaymentMethods(List<PaymentMethod> methodList) {

        paymentMethodRecycler.setVisibility(View.VISIBLE);
        paymentMethodEmpty.setVisibility(View.GONE);
        this.listPaymentMethods.addAll(methodList);
        adapterPaymentMethod.notifyDataSetChanged();

    }

    @Override
    public void showProgress() {

        mListener.showProgress();
    }

    @Override
    public void showSnackBarNetwork () {

        mListener.showSnackBarNetwork();
    }

    @Override
    public void onPaymentSelected(PaymentMethod paymentMethod) {

        adapterPaymentMethod.notifyDataSetChanged();
        if (paymentMethod.isSelected()) {
            currentPaymentMethod = paymentMethod;
        } else {
            currentPaymentMethod = null;
        }

    }

    public Object validateInfo() {

        if (currentPaymentMethod != null) {
            paymentMethodTextViewMandatory.setVisibility(View.GONE);
            return currentPaymentMethod;
        } else {
            paymentMethodTextViewMandatory.setVisibility(View.VISIBLE);
            return null;
        }

    }

}
