package com.eric.newsfeed.ui;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eric.base.database.InfoFlowRecord;

public class NFViewHolder extends RecyclerView.ViewHolder {
    public NFViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bindCursor(Cursor cursor) {
        if (itemView instanceof InfoFlowView){
            InfoFlowRecord record = new InfoFlowRecord();
            record.inflateEntity(cursor);
            ((InfoFlowView) itemView).setRecord(record);
        }
    }

}
