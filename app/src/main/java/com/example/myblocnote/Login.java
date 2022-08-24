package com.example.myblocnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




// cette page est permet de l'utilisateur de conncete si il y'a un compte:
public class Login extends AppCompatActivity {
//cette page perment d'utilisteur de login dans la bloc note
    EditText ed1,ed2;
    Button b1;
    TextView b2;
    Users user;
    Note note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed1 = findViewById(R.id.username);
        ed2 = findViewById(R.id.password);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                Intent intent = new Intent(Login.this,MainActivity.class);
                Login.this.startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login()
    {
        String nom = ed1.getText().toString();
        String pass = ed2.getText().toString();


        if(nom.equals("") || pass.equals(""))
        {
            Toast.makeText(this,"Nom ou password est vide",Toast.LENGTH_LONG).show();
        }
        else if(null!=ChekUser(nom,pass)) {
            String nomDB = ChekUser(nom, pass);


            Intent i = new Intent(Login.this, mainpage.class);
            i.putExtra("uname", nomDB);
            //Log.d("id user","userrs" + u.getIDuser());
            startActivity(i);

            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();

        }
        else
        {
            Toast.makeText(this,"Cette utilisateur n'exist pas ",Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();

        }

    }


    public String ChekUser(String nom,String pass)
    {
        SQLiteDatabase db = this.openOrCreateDatabase("notedbs", Context.MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("SELECT  id ,user,pass FROM utilisateur WHERE user = ? AND pass = ?",new String[]{nom,pass});//recuper data from db

        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            String nomutilisateur = cursor.getString(1);
            String passwordd = cursor.getString(2);
            SharedPreferences.Editor sp = getSharedPreferences("nomutilisateur",MODE_PRIVATE).edit();
            sp.putString("uname",nomutilisateur);
            sp.apply();
            cursor.close();


            return nomutilisateur;
        }return null;

    }

}