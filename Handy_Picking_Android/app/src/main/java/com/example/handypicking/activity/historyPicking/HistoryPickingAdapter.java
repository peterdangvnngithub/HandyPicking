package com.example.handypicking.activity.historyPicking;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handypicking.R;
import com.example.handypicking.model.handy_ms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;

public class HistoryPickingAdapter extends RecyclerView.Adapter<HistoryPickingAdapter.PickingHolder> implements Filterable {
    private final Context mContext;
    private List<handy_ms> list_handyMS;
    private List<handy_ms> list_handyMS_Old;
    ItemClickListener itemClickListener;
    String TAG = "HistoryPickingAdapter";
    public HistoryPickingAdapter(Context mContext,
                                 List<handy_ms> list_handyMS,
                                 ItemClickListener itemClickListener) {
        this.mContext           = mContext;
        this.list_handyMS       = list_handyMS;
        this.list_handyMS_Old   = list_handyMS;
        this.itemClickListener  = itemClickListener;
    }

    @NonNull
    @Override
    public PickingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_history_picking, parent, false);
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
        private final TextView txtDateCreate, txtPickingListNo;
        private final ImageView imgStatusPicking;
        public PickingHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            txtDateCreate       = itemView.findViewById(R.id.txtDateCreate);
            txtPickingListNo    = itemView.findViewById(R.id.txtPickingListNo);
            imgStatusPicking    = (ImageView) itemView.findViewById(R.id.imgStatusPicking);

            itemView.setOnClickListener(this);
        }

        void SetDetails(handy_ms handyMS)
        {
            String createDateStr = handyMS.getCREATE_DATE();
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
            try {
                Date createDate = inputFormat.parse(createDateStr);
                Log.d(TAG, createDate.toString());

                txtDateCreate.setText(outputFormat.format(createDate));
                txtPickingListNo.setText(handyMS.getPICKING_LIST_NO());
                if(handyMS.getSTATUS() == 0) {
                    imgStatusPicking.setImageResource(R.drawable.ic_check);
                } else {
                    imgStatusPicking.setImageResource(R.drawable.ic_lock);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, this.getAdapterPosition());
            }
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty())
                {
                    list_handyMS = list_handyMS_Old;
                } else {
                    List<handy_ms> result = new ArrayList<>();
                    for(handy_ms item : list_handyMS_Old)
                    {
                        if(item.getPICKING_LIST_NO().toLowerCase().contains(strSearch.toLowerCase()))
                        {
                            result.add(item);
                        }
                    }

                    list_handyMS = result;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list_handyMS;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list_handyMS = (List<handy_ms>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
