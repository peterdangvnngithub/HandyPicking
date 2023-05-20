package com.example.handypicking.activity.historyPicking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handypicking.R;
import com.example.handypicking.model.handy_ms;

import java.util.List;

public class HistoryPickingAdapter extends RecyclerView.Adapter<HistoryPickingAdapter.PickingHolder> {
    private final Context mContext;
    private final List<handy_ms> list_handyMS;
    ItemClickListener itemClickListener;

    public HistoryPickingAdapter(Context mContext,
                                 List<handy_ms> list_handyMS,
                                 ItemClickListener itemClickListener) {
        this.mContext           = mContext;
        this.list_handyMS       = list_handyMS;
        this.itemClickListener  = itemClickListener;
    }

    @NonNull
    @Override
    public PickingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.history_picking_item, parent, false);
        return new PickingHolder(view, itemClickListener );
    }

    @Override
    public void onBindViewHolder(@NonNull PickingHolder holder, int position) {
        handy_ms handyMS = list_handyMS.get(position);
        holder.SetDetails(handyMS);
    }

    @Override
    public int getItemCount() {
        return list_handyMS.size();
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class PickingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtPickingListNo;
        public PickingHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            txtPickingListNo = itemView.findViewById(R.id.txtPickingListNo);
        }

        void SetDetails(handy_ms handyMS)
        {
            txtPickingListNo.setText(handyMS.getPICKING_LIST_NO());
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick( v, getAdapterPosition() );
        }
    }
}
