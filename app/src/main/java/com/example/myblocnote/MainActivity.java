package com.example.myblocnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {


    EditText ed1,ed2,ed3;
    TextView id;
    Button b1,b2;
    Toolbar toolbar;
    Users users;

//cette page perment d'utilisateur d'inscrire dans la bloc note avec une base de donne
    //cette page perment d'inscrire un user dans le data base:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        id  = findViewById(R.id.iduser);
        ed1 = findViewById(R.id.username);
        ed2 = findViewById(R.id.password);
        ed3 = findViewById(R.id.confirmpassword);
        b1 = findViewById(R.id.btn1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();

            }
        });

    }

    public void insert (){
        try {
            String username = ed1.getText().toString();
            String password = ed2.getText().toString();
            String confirmpassword = ed3.getText().toString();

            NoteDatabase n = new NoteDatabase(this);

            //verifier si la mot de pass est bien confirmer.
            if(username.equals("") || password.equals("") || confirmpassword.equals(""))
            {
                Toast.makeText(this,"Nom ou password ou confirmpsw est vide",Toast.LENGTH_LONG).show();

            }

            else if(!password.equals(confirmpassword))
            {
                Toast.makeText(MainActivity.this,"password non confirmer",Toast.LENGTH_LONG).show();
                ed2.setText("");
                ed3.setText("");
                ed2.requestFocus();

            }
            else
            {
                users   =  new Users(username,password,confirmpassword);
                n.insertusers(users); //appelle de la methode insert qui perment d'inserez un user dans la table.
                Toast.makeText(this,"Tu est bien inscrire",Toast.LENGTH_LONG).show();


                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed1.requestFocus();
                goTologin();

            }

        }
        catch (Exception ex)
        {

        }

    }

    private void goTologin() {

                Intent i = new Intent(this,Login.class);//from getcontext to details activitie new activity details

                startActivity(i);

    }


}
