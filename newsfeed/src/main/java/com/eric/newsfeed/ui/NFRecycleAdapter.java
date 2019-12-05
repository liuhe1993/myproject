package com.eric.newsfeed.ui;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.eric.base.database.InfoFlowRecord;
import com.eric.newsfeed.R;

public class NFRecycleAdapter extends RecyclerView.Adapter<NFViewHolder>  implements View.OnTouchListener {

    public NFRecycleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @android.support.annotation.Nullable
    private CursorAdapter cursorAdapter;

    private Context mContext;

    public void setCursorAdapter(Cursor cursor) {
        cursorAdapter = new CursorAdapter(mContext, cursor) {
            @android.support.annotation.Nullable
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return null;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {

            }
        };

    }

    @android.support.annotation.Nullable
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
            case InfoFlowRecord.THREE_PIC_ITEM:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.three_pic_card_item, viewGroup);
                return new NFViewHolder(view);
            case InfoFlowRecord.SINGLE_PIC_ITEM:
                View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_card_item, viewGroup);
                return new NFViewHolder(view1);
             default:
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.three_pic_card_item, viewGroup);
        return new NFViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NFViewHolder nfViewHolder, int position) {
        Cursor cursor = getCursorAdapter().getCursor();
        if (!cursor.moveToPosition(position)){
            return;
        }
        nfViewHolder.bindCursor(cursor);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        Cursor cursor = getCursorAdapter().getCursor();
        if (!cursor.moveToPosition(position)) {
            return 0;
        }
        InfoFlowRecord record = new InfoFlowRecord();
        record.inflateEntity(cursor);

        int id = record.getId();
        if (id == InfoFlowRecord.SINGLE_PIC_ITEM){
            return InfoFlowRecord.SINGLE_PIC_ITEM;
        } else {
            return InfoFlowRecord.THREE_PIC_ITEM;
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
