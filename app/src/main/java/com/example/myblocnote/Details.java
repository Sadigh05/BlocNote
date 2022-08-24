package com.example.myblocnote;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {
    NoteDatabase db;
    Note note;
    TextView notedetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notedetails  =  findViewById(R.id.detaildeNote);
        notedetails.setMovementMethod(new ScrollingMovementMethod());

        Intent i = getIntent();
        Long id = i.getLongExtra("ID",0);
        //recuper data ->id de notre adapter ??
        //Toast.makeText(this,"titre  -> "+id, Toast.LENGTH_SHORT).show();


        //recuper les donnees  de db apartire de son id n'import qll donne:
       db = new NoteDatabase(this);
       note = db.getNote(id);

        //affiche le nome de note
        getSupportActionBar().setTitle(note.getTitre());
        notedetails.setText(note.getContenu());
       //Toast.makeText(this,"titre "+note.getTitre(),Toast.LENGTH_SHORT).show();


        //Button pour supprimer le note
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.SuprimerNote(note.getID());
                Toast.makeText(Details.this,"Note est suprimer ",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(),mainpage.class)); //retourne

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.modifier,menu); //menu bar
        return true;
        //super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.modifieritm)
        {//envoyer l'utilisateur vers modifiernotes activity

            //Toast.makeText(this,"modifier",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,ModifierNotes.class);
            i.putExtra("ID",note.getID());
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
