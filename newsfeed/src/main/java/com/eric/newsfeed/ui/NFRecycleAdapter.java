package com.eric.newsfeed.ui;

import android.content.Context;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.eric.base.database.InfoFlowRecord;
import com.eric.newsfeed.R;
import com.eric.newsfeed.bean.GankBean;

import java.util.List;

public class NFRecycleAdapter extends RecyclerView.Adapter<NFViewHolder>  implements View.OnTouchListener {

    public NFRecycleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    private List<GankBean.ResultData> list;

    @androidx.annotation.Nullable
    private CursorAdapter cursorAdapter;

    private Context mContext;

    public void setCursorAdapter(Cursor cursor) {
        cursorAdapter = new CursorAdapter(mContext, cursor) {
            @androidx.annotation.Nullable
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return null;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {

            }
        };

    }

    public void updateData(List<GankBean.ResultData> list) {
        this.list = list;
    }

    @androidx.annotation.Nullable
    public CursorAdapter getCursorAdapter() {
        return cursorAdapter;
    }

    public void changeCursor(Cursor cursor){
        cursorAdapter.changeCursor(cursor);
    }

    @NonNull
    @Override
    public NFViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case InfoFlowRecord.NO_PIC_ITEM:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.no_pic_card_item,viewGroup);
                return new NFViewHolder(view);
            case InfoFlowRecord.THREE_PIC_ITEM:
                View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.three_pic_card_item, viewGroup);
                return new NFViewHolder(view1);
            case InfoFlowRecord.SINGLE_PIC_ITEM:
                View view2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_card_item, viewGroup);
                return new NFViewHolder(view2);
             default:
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.three_pic_card_item, viewGroup);
        return new NFViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NFViewHolder nfViewHolder, int position) {
//        Cursor cursor = getCursorAdapter().getCursor();
//        if (!cursor.moveToPosition(position)){
//            return;
//        }
//        nfViewHolder.bindCursor(cursor);
        if (list != null && !list.isEmpty()) {
            GankBean.ResultData resultData = list.get(position);
            nfViewHolder.textView.setText(resultData.get_id());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
//        Cursor cursor = getCursorAdapter().getCursor();
//        if (!cursor.moveToPosition(position)) {
//            return 0;
//        }
//        InfoFlowRecord record = new InfoFlowRecord();
//        record.inflateEntity(cursor);
        return InfoFlowRecord.NO_PIC_ITEM;


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
