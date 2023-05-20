package com.example.handypicking.activity.picking.pickingDetail;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handypicking.R;
import com.example.handypicking.model.handy_detail;

import java.text.DecimalFormat;
import java.util.List;

public class PickingDetailAdapter extends RecyclerView.Adapter<PickingDetailAdapter.PickingHolder> {
    private final Context mContext;
    private final List<handy_detail> list_handyDetail;
    ItemClickListener itemClickListener;
    public PickingDetailAdapter(Context mContext,
                                List<handy_detail> list_handyDetail,
                                ItemClickListener itemClickListener)
    {
        this.mContext           = mContext;
        this.list_handyDetail   = list_handyDetail;
        this.itemClickListener  = itemClickListener;
    }

    @NonNull
    @Override
    public PickingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.picking_item, parent, false);
        return new PickingHolder(view, itemClickListener );
    }

    @Override
    public void onBindViewHolder(@NonNull PickingHolder holder, int position)
    {
        handy_detail handyDetail = list_handyDetail.get(position);
        holder.SetDetails(handyDetail);
    }

    @Override
    public int getItemCount()
    {
        return list_handyDetail.size();
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    //2. Holder
    public class PickingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtItemCode, txtSeries, txtQuantity;
        private final Button btnDelete;

        ItemClickListener itemClickListener;

        public PickingHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            txtItemCode = itemView.findViewById(R.id.txtItemCode);
            txtSeries   = itemView.findViewById(R.id.txtSeries);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            btnDelete   = itemView.findViewById(R.id.btnDelete);

            this.itemClickListener = itemClickListener;
            btnDelete.setOnClickListener(this);
        }

        void SetDetails(handy_detail handyDetail)
        {
            txtItemCode.setText(handyDetail.getCUS_ITEM_CODE());
            txtSeries.setText(handyDetail.getSERIES());
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            txtQuantity.setText(formatter.format(handyDetail.getQTY_TOTAL()));
        }

        @Override
        public void onClick(View v)
        {
            itemClickListener.onItemClick( v, getAdapterPosition() );
        }
    }
}
