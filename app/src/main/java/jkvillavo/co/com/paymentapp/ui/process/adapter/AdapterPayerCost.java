package jkvillavo.co.com.paymentapp.ui.process.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import jkvillavo.co.com.paymentapp.R;
import jkvillavo.co.com.paymentapp.data.model.PayerCost;

/**
 * Adaptador encargado de mostrar las opciones de pago
 */
public class AdapterPayerCost extends RecyclerView.Adapter<AdapterPayerCost.PayerCostViewHolder> {

    private List<PayerCost> listPayerCosts;
    private AdapterPayerCostListener mListener;
    private Fragment fragment;

    public AdapterPayerCost ( List<PayerCost> listPayerCosts, AdapterPayerCostListener methodListener,
                              Fragment fragment ) {

        this.listPayerCosts = listPayerCosts;
        this.mListener = methodListener;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public PayerCostViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {

        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_payercost, parent, false );

        return new PayerCostViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder ( @NonNull PayerCostViewHolder holder, int position ) {

        final PayerCost payerCost = listPayerCosts.get( position );
        holder.tvName.setText( payerCost.getRecommendedMessage() );
        holder.tvInstallments.setText( String.valueOf( payerCost.getInstallments() ) );
        String installments = fragment.getResources().getQuantityString( R.plurals.numberOfInstallments, payerCost.getInstallments() );
        holder.tvInstallmentsText.setText( installments );

        if ( payerCost.isSelected() ) {
            holder.relativeLayout.setBackgroundResource( R.drawable.shape_back_blueligth );
        } else {
            holder.relativeLayout.setBackgroundResource( 0 );
        }

        holder.cardView.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick ( View v ) {

                for ( PayerCost payment : listPayerCosts ) {
                    if ( payment.getInstallments() != payerCost.getInstallments() ) {
                        payment.setSelected( false );
                    }
                }
                payerCost.setSelected( ! payerCost.isSelected() );
                mListener.onPayerCostSelected( payerCost );

            }
        } );

    }

    @Override
    public int getItemCount () {

        return listPayerCosts.size();
    }

    public class PayerCostViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvInstallments, tvInstallmentsText;
        private CardView cardView;
        private RelativeLayout relativeLayout;

        public PayerCostViewHolder ( View view ) {

            super( view );
            tvName = ( TextView ) view.findViewById( R.id.itemPayerCost_textViewName );
            tvInstallments = ( TextView ) view.findViewById( R.id.itemPayerCost_textInstallments );
            tvInstallmentsText = ( TextView ) view.findViewById( R.id.itemPayerCost_text );
            cardView = ( CardView ) view.findViewById( R.id.itemPayerCost_card );
            relativeLayout = ( RelativeLayout ) view.findViewById( R.id.itemPayerCost_rl );

        }
    }

    public interface AdapterPayerCostListener {

        void onPayerCostSelected ( PayerCost payerCost );

    }

}
