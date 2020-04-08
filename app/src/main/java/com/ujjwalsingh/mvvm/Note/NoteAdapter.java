package com.ujjwalsingh.mvvm.Note;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ujjwalsingh.mvvm.EditorActivity;
import com.ujjwalsingh.mvvm.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    private Context context;
    private List<NotesEntity> dataList;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final NotesEntity notesEntity =dataList.get(position);
        holder.textView.setText(notesEntity.text);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, EditorActivity.class);
                intent.putExtra("node",notesEntity.getId());
                context.startActivity(intent);
               // Bundle b =intent.getExtras();
               // int k = b.getInt("node")
                Log.i("Hard",String.valueOf(notesEntity.getId()));
            }
        });
    }

    public NoteAdapter(Context context, List<NotesEntity> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.text);
        }

    }

}
