package com.example.handypicking.activity.dataServer.dataServerDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handypicking.R;
import com.example.handypicking.model.handy_detail;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DataServerDetailAdapter extends RecyclerView.Adapter<DataServerDetailAdapter.DataServerHolder>{
    private final Context mContext;
    private final List<handy_detail> list_handyDetail;
    public DataServerDetailAdapter(Context mContext,
                                List<handy_detail> list_handyDetail)
    {
        this.mContext           = mContext;
        this.list_handyDetail   = list_handyDetail;
    }

    @NonNull
    @Override
    public DataServerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_data_server_detail, parent, false);
        return new DataServerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataServerHolder holder, int position)
    {
        handy_detail handyDetail = list_handyDetail.get(position);
        holder.SetDetails(handyDetail);
    }

    @Override
    public int getItemCount()
    {
        return list_handyDetail.size();
    }

    public class DataServerHolder extends RecyclerView.ViewHolder {
        private final TextView dataServerDetail_txtPalletNo, dataServerDetail_txtItemCode, dataServerDetail_txtSeries, dataServerDetail_txtQuantity, dataServerDetail_dateCreate;
        public DataServerHolder(@NonNull View itemView) {
            super(itemView);
            dataServerDetail_txtPalletNo = itemView.findViewById(R.id.row_dataServerDetail_txtPalletNo);
            dataServerDetail_txtItemCode = itemView.findViewById(R.id.row_dataServerDetail_txtItemCode);
            dataServerDetail_txtSeries   = itemView.findViewById(R.id.row_dataServerDetail_txtSeries);
            dataServerDetail_txtQuantity = itemView.findViewById(R.id.row_dataServerDetail_txtQuantity);
            dataServerDetail_dateCreate  = itemView.findViewById(R.id.row_dataServerDetail_dateCreate);
        }

        void SetDetails(handy_detail handyDetail)
        {
            dataServerDetail_txtPalletNo.setText(handyDetail.getPALLET_NO().trim());
            dataServerDetail_txtItemCode.setText(handyDetail.getCUS_ITEM_CODE());
            dataServerDetail_txtSeries.setText(handyDetail.getSERIES());
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            dataServerDetail_txtQuantity.setText(formatter.format(handyDetail.getQTY_TOTAL()));

            String createDateStr            = handyDetail.getCREATE_DATE();
            SimpleDateFormat inputFormat    = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            SimpleDateFormat outputFormat   = new SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.US);
            try {
                Date createDate = inputFormat.parse(createDateStr);
                dataServerDetail_dateCreate.setText(outputFormat.format(createDate));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
