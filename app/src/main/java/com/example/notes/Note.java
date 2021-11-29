package com.example.notes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note {
    @ColumnInfo(name = "text")
    String text;
    @PrimaryKey(autoGenerate = true)
    int id;
    Note(String text){
        this.text=text;
        id=0;
    }
}
