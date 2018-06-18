package jkvillavo.co.com.paymentapp.ui.process.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import jkvillavo.co.com.paymentapp.R;
import jkvillavo.co.com.paymentapp.data.model.PaymentMethod;

/**
 * Adaptador encargado de mostrar los metodos de pago
 */
public class AdapterPaymentMethod extends RecyclerView.Adapter<AdapterPaymentMethod.PaymentMethodViewHolder> {

    private List<PaymentMethod> listPaymentMethods;
    private AdapterPaymentMethodListener mListener;
    private Fragment fragment;

    public AdapterPaymentMethod ( List<PaymentMethod> listPaymentMethods, AdapterPaymentMethodListener methodListener,
                                  Fragment fragment ) {

        this.listPaymentMethods = listPaymentMethods;
        this.mListener = methodListener;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {

        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_paymentmethod, parent, false );

        return new PaymentMethodViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder ( @NonNull PaymentMethodViewHolder holder, int position ) {

        final PaymentMethod paymentMethod = listPaymentMethods.get( position );
        holder.tvName.setText( paymentMethod.getName() );
        Glide.with( fragment ).load( paymentMethod.getSecureThumbnail() ).into( holder.imageView );

        if ( paymentMethod.isSelected() ) {
            holder.relativeLayout.setBackgroundResource( R.drawable.shape_back_blueligth );
        } else {
            holder.relativeLayout.setBackgroundResource( 0 );
        }

        holder.cardView.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick ( View v ) {

                for ( PaymentMethod payment : listPaymentMethods ) {
                    if ( ! payment.getId().equalsIgnoreCase( paymentMethod.getId() ) ) {
                        payment.setSelected( false );
                    }
                }
                paymentMethod.setSelected( ! paymentMethod.isSelected() );
                mListener.onPaymentSelected( paymentMethod );

            }
        } );

    }

    @Override
    public int getItemCount () {

        return listPaymentMethods.size();
    }

    public class PaymentMethodViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        private CardView cardView;
        private RelativeLayout relativeLayout;
        private ImageView imageView;

        public PaymentMethodViewHolder ( View view ) {

            super( view );
            tvName = ( TextView ) view.findViewById( R.id.itemPaymentMethod_textViewName );
            cardView = ( CardView ) view.findViewById( R.id.itemPaymentMethod_card );
            relativeLayout = ( RelativeLayout ) view.findViewById( R.id.itemPaymentMethod_rl );
            imageView = ( ImageView ) view.findViewById( R.id.itemPaymentMethod_image );

        }
    }

    public interface AdapterPaymentMethodListener {

        void onPaymentSelected ( PaymentMethod paymentMethod );

    }

}
