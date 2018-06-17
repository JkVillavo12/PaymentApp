package jkvillavo.co.com.paymentapp.ui.process.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import jkvillavo.co.com.paymentapp.ui.process.fragments.cardissuer.CardIssuerFragment;
import jkvillavo.co.com.paymentapp.ui.process.fragments.installments.InstallmentsFragment;
import jkvillavo.co.com.paymentapp.ui.process.fragments.paymentmethod.PaymentMethodFragment;

public class AdapterOnProcess extends FragmentStatePagerAdapter {

    static final SparseArray<Fragment> registeredFragments = new SparseArray();


    public AdapterOnProcess(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = PaymentMethodFragment.newInstance();
                break;
            case 1:
                fragment = CardIssuerFragment.newInstance();
                break;
            case 2:
                fragment = InstallmentsFragment.newInstance();
                break;
            default:
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
