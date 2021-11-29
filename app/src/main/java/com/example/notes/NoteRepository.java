package com.example.notes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import org.w3c.dom.Node;

import java.util.List;

public class NoteRepository {
    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNodes;

    NoteRepository(Application application){
        NoteDatabase db=NoteDatabase.getDatabase(application);
        mNoteDao=db.getNoteDao();
        mAllNodes=mNoteDao.getAllNotes();
    }
//    NoteRepository(NoteDao dao){
//        mNoteDao=dao;
//        mAllNodes=mNoteDao.getAllNotes();
//    }
    LiveData<List<Note>> getAllNodes(){
        return mAllNodes;
    }
    void insert(Note note){
        NoteDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.insert(note);
            }
        });
    }
    void delete(Note note){
        NoteDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.delete(note);
            }
        });
    }
}
