package com.example.handypicking.activity.syncData;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handypicking.R;
import com.example.handypicking.model.handy_ms;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.SyncDataHolder>  {
    private final Context mContext;
    private final List<handy_ms> list_handyMS;
    private ItemClickListener itemClickListener;
    private ButtonClickListener buttonClickListener;
    String TAG = "SyncDataAdapter";

    public DataAdapter(Context mContext,
                       List<handy_ms> list_handyMS,
                       ItemClickListener itemClickListener,
                       ButtonClickListener buttonClickListener) {
        this.mContext = mContext;
        this.list_handyMS = list_handyMS;
        this.itemClickListener = itemClickListener;
        this.buttonClickListener = buttonClickListener;
    }

    @NonNull
    @Override
    public SyncDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_sync_data, parent, false);
        return new SyncDataHolder(view, itemClickListener );
    }

    @Override
    public void onBindViewHolder(@NonNull SyncDataHolder holder, int position) {
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

    public interface ButtonClickListener {
        void onButtonClick(View view, int position);
    }

    public class SyncDataHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtDateCreate, txtPickingListNo;
        private final Button btnDeleteData;
        public SyncDataHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            txtDateCreate       = itemView.findViewById(R.id.txtDateCreate);
            txtPickingListNo    = itemView.findViewById(R.id.txtPickingListNo);
            btnDeleteData       = (Button) itemView.findViewById(R.id.imgDeleteData);

            btnDeleteData.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        void SetDetails(handy_ms handyMS)
        {
            String createDateStr            = handyMS.getCREATE_DATE();
            SimpleDateFormat inputFormat    = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.US);
            SimpleDateFormat outputFormat   = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
            try {
                Date createDate = inputFormat.parse(createDateStr);
                txtDateCreate.setText(outputFormat.format(createDate));
                txtPickingListNo.setText(handyMS.getPICKING_LIST_NO());
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                if (v.getId() == R.id.imgDeleteData) {
                    if (buttonClickListener != null) {
                        buttonClickListener.onButtonClick(v, position);
                    }
                } else {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, position);
                    }
                }
            }
        }
    }
}
