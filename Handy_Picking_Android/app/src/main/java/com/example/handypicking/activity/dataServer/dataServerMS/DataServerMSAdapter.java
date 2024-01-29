package com.example.handypicking.activity.dataServer.dataServerMS;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handypicking.R;
import com.example.handypicking.model.handy_ms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class DataServerMSAdapter extends RecyclerView.Adapter<DataServerMSAdapter.DataServerHolder>  implements Filterable {
    private final Context mContext;
    private List<handy_ms> list_handyMS;
    private List<handy_ms> list_handyMS_Old;
    private ItemClickListener itemClickListener;
    String TAG = "DataServerAdapter";
    public DataServerMSAdapter(Context mContext,
                               List<handy_ms> list_handyMS,
                               ItemClickListener itemClickListener) {
        this.mContext               = mContext;
        this.list_handyMS           = list_handyMS;
        this.list_handyMS_Old       = list_handyMS;
        this.itemClickListener      = itemClickListener;
    }

    @NonNull
    @Override
    public DataServerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_data_server_ms, parent, false);
        return new DataServerHolder(view, itemClickListener );
    }

    @Override
    public void onBindViewHolder(@NonNull DataServerHolder holder, int position) {
        handy_ms handyMS = list_handyMS.get(position);
        holder.SetDetails(handyMS);
    }

    @Override
    public int getItemCount() {
        return list_handyMS.size();
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onImageClick(View view, int position);
    }

    public class DataServerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView row_dataServerMS_txtDateCreate, row_dataServerMS_txtPickingListNo;
        private final ImageView row_dataServerMS_imgDownload;
        public DataServerHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            row_dataServerMS_txtDateCreate      = itemView.findViewById(R.id.row_dataServerMS_txtDateCreate);
            row_dataServerMS_txtPickingListNo   = itemView.findViewById(R.id.row_dataServerMS_txtPickingListNo);
            row_dataServerMS_imgDownload        = itemView.findViewById(R.id.row_dataServerMS_imgDownload);

            itemView.setOnClickListener(this);
            row_dataServerMS_imgDownload.setOnClickListener(this);
        }

        void SetDetails(handy_ms handyMS)
        {
            String createDateStr            = handyMS.getCREATE_DATE();
            SimpleDateFormat inputFormat    = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            SimpleDateFormat outputFormat   = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
            try {
                Date createDate = inputFormat.parse(createDateStr);
                row_dataServerMS_txtDateCreate.setText(outputFormat.format(createDate));
                row_dataServerMS_txtPickingListNo.setText(handyMS.getPICKING_LIST_NO());
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.row_dataServerMS_imgDownload) {
                itemClickListener.onImageClick(v, getAdapterPosition());
            } else {
                itemClickListener.onItemClick(v, getAdapterPosition());
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
