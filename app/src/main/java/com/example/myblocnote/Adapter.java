package com.example.myblocnote;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


//cette page est permet de faire la laison entre la vue recyclview et database
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Note> notes;

    Adapter(Context context, List<Note> notes) {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup viewGroup, int i) {
       View view = inflater.inflate(R.layout.custom_listview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {
        String title   = notes.get(i).getTitre();
        String date    = notes.get(i).getDate();
        String time    = notes.get(i).getTime();
        long  id       = notes.get(i).getID();
        Long iduser    = notes.get(i).getId_user();

        Log.d("mytime","mytime "+ time);
        Log.d("mydate","mydate "+ date); //rien
        Log.d("mytitre","mytitre "+ title);




            viewHolder.NoteTitle.setText(title);
            viewHolder.ndate.setText(date);
            viewHolder.ntime.setText(time);
            viewHolder.nid.setText(String.valueOf(notes.get(i).getID()));
            viewHolder.iduser.setText(String.valueOf(notes.get(i).getId_user()));





    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//dans cette class on doit return notre note
            TextView NoteTitle, ndate, ntime ,nid,iduser;

            public  ViewHolder(@NonNull View itemView) {
                super(itemView);
                NoteTitle       =  itemView.findViewById(R.id.NoteTitle);
                ndate           =  itemView.findViewById(R.id.ndate);
                ntime           =  itemView.findViewById(R.id.ntime);
                nid             =  itemView.findViewById(R.id.listid);
                iduser          =  itemView.findViewById(R.id.iduser);


                //chaque  itemview est clickable item :
                itemView.setOnClickListener(new View.OnClickListener() {//pour entrez dans item chosir et le modifier
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(),Details.class);// De getcontext a la details new activity (details)
                        i.putExtra("ID",notes.get(getAdapterPosition()).getID());
                        v.getContext().startActivity(i);
                        //
                    }
                });

            }

        }


    }