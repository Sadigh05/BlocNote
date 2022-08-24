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

/*
Cette page et pour cree un nouveau note avec sa date et l'heure de creation ,et le sauvegarder ou suprimmer
 on peut pas enrgister un note vide ou l'une de deux contenu est vide (titre,countenu)

 */
public class Addnote extends AppCompatActivity {

    Toolbar toolbar;
    EditText noteTitre,noteContenu;
    Calendar c;

    String todaydates, time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));//pour coloreure le bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//??

      // toolbar.setTitleColor(get)


        noteTitre = findViewById(R.id.noteTitre);
        noteContenu = findViewById(R.id.noteContenu);

        noteTitre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // le date et time
        c = Calendar.getInstance();
        todaydates = c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+(c.get(Calendar.YEAR));
        time = pad( c.get(Calendar.HOUR_OF_DAY))+":"+pad(c.get(Calendar.MINUTE));

        Log.d("caDate","caDate " + todaydates );
        Log.d("caTime","caTime "+ time);

    }

    //cette fonction si l'une de elts <10 on ajout 0 a chaque fois exp: 01,05.
    private String pad(int i) {
        if(i<10)
            return "0"+i;
        return String.valueOf(i);
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
            Toast.makeText(this,"Note is not save", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }

        if(item.getItemId() == R.id.save){
            String titre = noteTitre.getText().toString();
            String contenu = noteContenu.getText().toString();

            if(titre.equals("")||contenu.equals(""))//si ona titre vide ou countenu vide n'est pas enregister!
            {
                Toast.makeText(this,"Note vide ne se crée pas ",Toast.LENGTH_LONG).show();
                goToMain();
            }
            else {
                Note note = new Note(noteTitre.getText().toString(), noteContenu.getText().toString(), todaydates, time); //pour appelle class note ici avec le contenu qui exist ici
                NoteDatabase db = new NoteDatabase(this); //appele de db
                db.addNote(note);  //appele de method qui exist dans
                Toast.makeText(this, "Save ", Toast.LENGTH_SHORT).show();
                goToMain();
            }
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
