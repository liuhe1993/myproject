package com.eric.base.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InfoFlowDao {
    @androidx.annotation.NonNull
    @Query("SELECT * FROM info_flow_record")
    List<InfoFlowRecord> getAll();

    @androidx.annotation.NonNull
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
