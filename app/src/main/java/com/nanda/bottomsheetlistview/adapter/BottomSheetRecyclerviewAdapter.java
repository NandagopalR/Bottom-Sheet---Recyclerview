package com.nanda.bottomsheetlistview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanda.bottomsheetlistview.R;

import java.util.List;

/**
 * Created by Nandagopal on 28-Jan-16.
 */
public class BottomSheetRecyclerviewAdapter extends RecyclerView.Adapter<BottomSheetRecyclerviewAdapter.BottomSheetRecyclerViewHolder> {

    private Context context;
    private List<String> mList;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    public BottomSheetRecyclerviewAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public BottomSheetRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_bottomsheet_test, parent, false);
        return new BottomSheetRecyclerViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(BottomSheetRecyclerViewHolder holder, int position) {
        holder.mText.setText(mList.get(position).toString());
    }


    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private void onItemHolderClick(BottomSheetRecyclerViewHolder itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class BottomSheetRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mText;
        ImageView mImg;

        BottomSheetRecyclerviewAdapter mAdapter;

        public BottomSheetRecyclerViewHolder(View itemView, BottomSheetRecyclerviewAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            itemView.setOnClickListener(this);
            mText = (TextView) itemView.findViewById(R.id.text_bottom);
            mImg = (ImageView) itemView.findViewById(R.id.img_bottom);
        }

        @Override
        public void onClick(View v) {
            mAdapter.onItemHolderClick(this);
        }
    }
}
