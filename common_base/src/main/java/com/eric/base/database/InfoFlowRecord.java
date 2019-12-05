package com.eric.base.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.database.Cursor;

@Entity(tableName = "info_flow_record")
public class InfoFlowRecord {

    public static final int SINGLE_PIC_ITEM = 1;

    public static final int THREE_PIC_ITEM = 2;

    public InfoFlowRecord() {
    }


    public interface Columns {
        String ID = "id";
        String DOCID = "doc_id";
        String TITLE = "title";
        String IMAGE_URLs  = "image_urls";
        String LINK_URL = "link_url";
    }
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    private int id;

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    @ColumnInfo(name = Columns.DOCID)
    private String doc_id;

    @ColumnInfo(name = Columns.TITLE)
    private String title;

    @ColumnInfo(name = Columns.IMAGE_URLs)
    private String image_urls;

    @ColumnInfo(name = Columns.LINK_URL)
    private String link_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_urls() {
        return image_urls;
    }

    public void setImage_urls(String image_urls) {
        this.image_urls = image_urls;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }
    public void inflateEntity(Cursor cursor) {
        // TODO: 2019/5/18 从cursor中获取数据
    }
}
