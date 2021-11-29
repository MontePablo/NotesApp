package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesRVAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    List<Note> mAllNotes ;
    Context mContext;
    INotesRVAdapter mListener;

    NotesRVAdapter(Context context,INotesRVAdapter listener){
        this.mContext=context;
        this.mListener=listener;
//        mAllNotes=new ArrayList<>();
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteViewHolder noteViewHolder=new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false));
        noteViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClicked(mAllNotes.get(noteViewHolder.getAdapterPosition()));
            }
        });
        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote=mAllNotes.get(position);
        holder.textView.setText(currentNote.text);

    }

    @Override
    public int getItemCount() {
        if (mAllNotes!=null) {
            return mAllNotes.size();
        }
        return 0;
    }
    void updateRecyclerView(List<Note> notes){
        mAllNotes=notes;
        notifyDataSetChanged();
    }
}

class NoteViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ImageView deleteButton;
    NoteViewHolder(View itemView){
        super(itemView);
        textView=itemView.findViewById(R.id.textNote);
        deleteButton=itemView.findViewById(R.id.deleteButton);
    }
}
interface INotesRVAdapter{
    void onItemClicked(Note note);
}

