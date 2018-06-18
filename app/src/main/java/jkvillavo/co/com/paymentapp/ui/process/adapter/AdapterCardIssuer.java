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
import jkvillavo.co.com.paymentapp.data.model.CardIssuer;

/**
 * Adaptador encargado de mostrar los bancos disponibles para un medio de pago
 */
public class AdapterCardIssuer extends RecyclerView.Adapter<AdapterCardIssuer.CardIssuerViewHolder> {

    private List<CardIssuer> listCardIssuers;
    private AdapterCardIssuerListener mListener;
    private Fragment fragment;

    public AdapterCardIssuer ( List<CardIssuer> listCardIssuers, AdapterCardIssuerListener methodListener,
                               Fragment fragment ) {

        this.listCardIssuers = listCardIssuers;
        this.mListener = methodListener;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public CardIssuerViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {

        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item_cardissuer, parent, false );

        return new CardIssuerViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder ( @NonNull CardIssuerViewHolder holder, int position ) {

        final CardIssuer CardIssuer = listCardIssuers.get( position );
        holder.tvName.setText( CardIssuer.getName() );
        Glide.with( fragment ).load( CardIssuer.getSecureThumbnail() ).into( holder.imageView );

        if ( CardIssuer.isSelected() ) {
            holder.relativeLayout.setBackgroundResource( R.drawable.shape_back_blueligth );
        } else {
            holder.relativeLayout.setBackgroundResource( 0 );
        }

        holder.cardView.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick ( View v ) {

                for ( CardIssuer payment : listCardIssuers ) {
                    if ( ! payment.getId().equalsIgnoreCase( CardIssuer.getId() ) ) {
                        payment.setSelected( false );
                    }
                }
                CardIssuer.setSelected( ! CardIssuer.isSelected() );
                mListener.onCardIssuerSelected( CardIssuer );

            }
        } );

    }

    @Override
    public int getItemCount () {

        return listCardIssuers.size();
    }

    public class CardIssuerViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        private CardView cardView;
        private RelativeLayout relativeLayout;
        private ImageView imageView;

        public CardIssuerViewHolder ( View view ) {

            super( view );
            tvName = ( TextView ) view.findViewById( R.id.itemCardIssuer_textViewName );
            cardView = ( CardView ) view.findViewById( R.id.itemCardIssuer_card );
            relativeLayout = ( RelativeLayout ) view.findViewById( R.id.itemCardIssuer_rl );
            imageView = ( ImageView ) view.findViewById( R.id.itemCardIssuer_image );

        }
    }

    public interface AdapterCardIssuerListener {

        void onCardIssuerSelected ( CardIssuer CardIssuer );

    }

}
