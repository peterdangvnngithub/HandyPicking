package com.example.handypicking.activity.dataLocal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handypicking.R;
import com.example.handypicking.model.handy_ms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DataLocalAdapter extends RecyclerView.Adapter<DataLocalAdapter.DataLocalHolder> implements Filterable {
    private final Context mContext;
    private List<handy_ms> list_handyMS;
    private List<handy_ms> list_handyMS_Old;
    ItemClickListener itemClickListener;
    String TAG = "DataLocalAdapter";

    public DataLocalAdapter(Context mContext,
                            List<handy_ms> list_handyMS,
                            ItemClickListener itemClickListener) {
        this.mContext           = mContext;
        this.list_handyMS       = list_handyMS;
        this.list_handyMS_Old   = list_handyMS;
        this.itemClickListener  = itemClickListener;
    }

    @NonNull
    @Override
    public DataLocalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_data_local, parent, false);
        return new DataLocalHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DataLocalHolder holder, int position) {
        handy_ms handyMS = list_handyMS.get(position);
        holder.SetDetail(handyMS);
    }

    @Override
    public int getItemCount() {
        return list_handyMS.size();
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class DataLocalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView dataLocal_txtDateCreate, dataLocal_txtPickingListNo;
        private final ImageView dataLocal_imgStatusPicking;
        public DataLocalHolder(@NonNull View itemView, ItemClickListener itemClickListener)
        {
            super(itemView);

            dataLocal_txtDateCreate       = itemView.findViewById(R.id.data_txtDateCreate);
            dataLocal_txtPickingListNo    = itemView.findViewById(R.id.data_txtPickingListNo);
            dataLocal_imgStatusPicking    = itemView.findViewById(R.id.data_imgStatusPicking);

            itemView.setOnClickListener(this);
        }

        public void SetDetail(handy_ms handyMS) {
            String createDateStr = handyMS.getCREATE_DATE();
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
            try {
                Date createDate = inputFormat.parse(createDateStr);

                dataLocal_txtDateCreate.setText(outputFormat.format(createDate));
                dataLocal_txtPickingListNo.setText(handyMS.getPICKING_LIST_NO());
                if(handyMS.getSTATUS() == 0) {
                    dataLocal_imgStatusPicking.setImageResource(R.drawable.ic_check);
                } else {
                    dataLocal_imgStatusPicking.setImageResource(R.drawable.ic_lock);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null)
            {
                itemClickListener.onItemClick(v, this.getAdapterPosition());
            }
        }
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
