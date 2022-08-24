package com.example.myblocnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class ModifierNotes extends AppCompatActivity {

    Toolbar toolbar;
    EditText noteContenu;
    Calendar c;
    NoteDatabase db;
    Note note1;
    String todaydates, time,t;
    Long nid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_notes);

        Intent j = getIntent();
         nid = j.getLongExtra("ID",0);
         db = new NoteDatabase(this);
         note1 = db.getNote(nid);

        //Toast.makeText(this,"date is -> "+note.getDate(), Toast.LENGTH_SHORT).show();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));//pour coloreure le bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(note1.getTitre());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                noteContenu = findViewById(R.id.noteContenu);

                noteContenu.setText(note1.getContenu());



        // le date et l'heure
        c = Calendar.getInstance();
        todaydates = c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+(c.get(Calendar.YEAR));
        time = pad(c.get(Calendar.HOUR_OF_DAY))+":"+pad(c.get(Calendar.MINUTE));

        Log.d("caDate","caDate1 " + todaydates );
        Log.d("caTime","caTime2 "+ time);

    }

    //cette fonction si l'une d'elts <10 on ajout 0 a chaque fois exp: 01,05.
    private String pad(int time) {
        if(time <10)
            return "0"+time;
        return String.valueOf(time);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
        //super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete){
            Toast.makeText(this,"Note no modifier", Toast.LENGTH_SHORT).show();
            //no modification meme temps et meme date
            //no modification meme temps et meme date
          goToMain();
        }


        if(item.getItemId() == R.id.save){
                Note note = new Note(nid,note1.getTitre(),noteContenu.getText().toString(),todaydates,time);
                Log.d("EDIT", "edit befor saving id " + note.getID());
                NoteDatabase dbnt = new NoteDatabase(getApplicationContext());
                long id = dbnt.modifierNote(note);
                Log.d("Edit", "Editt" + id);
                Toast.makeText(this, "Note est modifier", Toast.LENGTH_SHORT).show();
                //a chaque tape sur save on oura un modification au cours de temps et date et message de notification
                goToMain();

        }
        return super.onOptionsItemSelected(item);
    }


    private void goToMain() {
        Intent i = new Intent(this,mainpage.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}



