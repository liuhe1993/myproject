package com.eric.base.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface InfoFlowDao {
    @android.support.annotation.NonNull
    @Query("SELECT * FROM info_flow_record")
    List<InfoFlowRecord> getAll();

    @android.support.annotation.NonNull
    @Query("SELECT * FROM info_flow_record WHERE doc_id = :docId")
    List<InfoFlowRecord> findByDocId(String docId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(InfoFlowRecord... infoFlowRecords);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(InfoFlowRecord infoFlowRecord);

    @Delete
    void deleteAll(InfoFlowRecord... infoFlowRecords);

    @Delete
    void delete(InfoFlowRecord infoFlowRecord);
}
