package com.eric.newsfeed.ui;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eric.base.database.InfoFlowRecord;
import com.eric.newsfeed.R;
import com.eric.newsfeed.ui.view.InfoFlowView;

public class NFViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public NFViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.text);
    }


    public void bindCursor(Cursor cursor) {
        if (itemView instanceof InfoFlowView){
            InfoFlowRecord record = new InfoFlowRecord();
            record.inflateEntity(cursor);
            ((InfoFlowView) itemView).setRecord(record);
        }
    }

}
