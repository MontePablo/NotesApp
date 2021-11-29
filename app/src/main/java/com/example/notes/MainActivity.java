package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements INotesRVAdapter {
    NoteViewModel mNoteViewModel;
    MainActivity mContext;
    NotesRVAdapter mAdapter;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=findViewById(R.id.mainRecyclerView);
        mContext=this;
        mAdapter=new NotesRVAdapter(this,this);

        mNoteViewModel=new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NoteViewModel.class);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mNoteViewModel.getAllWords().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if(notes!=null){
                    mAdapter.updateRecyclerView(notes);

                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                }
            }
        });
    }

    @Override
    public void onItemClicked(Note note) {
        mNoteViewModel.delete(note);
        Toast.makeText(this, note.text+" deleted!", Toast.LENGTH_SHORT).show();
    }

    public void submitData(View view) {
        EditText editText=findViewById(R.id.input);
        String text=editText.getText().toString();
        if(!text.isEmpty()){
            mNoteViewModel.insert(new Note(text));
            Toast.makeText(this, text+" inserted!", Toast.LENGTH_SHORT).show();
        }
    }
}