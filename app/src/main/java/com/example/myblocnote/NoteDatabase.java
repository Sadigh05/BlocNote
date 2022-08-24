package com.example.myblocnote;

 import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 import android.database.sqlite.SQLiteStatement;
 import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


//cette page concerne la base de donne pour bloc note et utilisateur avec qeul que methode tres important
public class NoteDatabase extends SQLiteOpenHelper {

// la version et nom de base de donnee
    private static final int DATABASE_VERSION =4;
    private static final String DATABASE_NAME = "notedbs";
    private static final String Bloc_TABLE = "notestables";
    private static final String users_TABLE = "utilisateur";

    private SQLiteStatement insertStmt;

    public NoteDatabase(Context context) //constructer de notre clsse avec les elts
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    //Les nom de colones de table de notes
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_Content = "content";
    private static final String KEY_DATE = "dates";
    private static final String KEY_TIME = "time";

    //Les decelaration de table login et insecrire que on doit l'appele utilisateur
    private static final String ID = "id";
    private static final String user = "user";
    private static final String pass = "pass";
    private static final String comfpass = "comfpass ";
    private static final String user_id = "user_id";



//creation de table utilisateur
    String createuser = "CREATE TABLE "+users_TABLE +" (" + ID+ " INTEGER PRIMARY KEY,"+
        user+" TEXT,"+
        pass+" TEXT,"+
        comfpass+" TEXT "+")";


    //creation de table de note
    String createtablebloc = "CREATE TABLE "+Bloc_TABLE +" (" + KEY_ID+ " INTEGER PRIMARY KEY,"+
            KEY_TITLE+" TEXT,"+
            KEY_Content+" TEXT,"+
            KEY_DATE+" TEXT,"+
            KEY_TIME+" TEXT,"+
            user_id+" INTEGER," + " FOREIGN KEY(user_id) REFERENCES TABLE_users(ID) "+")";






    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createtablebloc);
        db.execSQL(createuser);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS "+Bloc_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+users_TABLE);
        onCreate(db);
    }

//insertion dans user table:
    public long insertusers(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(user,users.getUser());
        v.put(pass,users.getPass());
        v.put(comfpass,users.getCmfpass());
        long d = db.insert(users_TABLE, null, v);
        return d;

    }


    public long addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();//on doit insert notre database donc ona obliger de getwritabledb.
        ContentValues c = new ContentValues();//cette fonction pour collecte entre les variable de database avec classe Note.java
        c.put(KEY_TITLE,note.getTitre());
        c.put(KEY_Content,note.getContenu());
        c.put(KEY_DATE,note.getDate());
        c.put(KEY_TIME,note.getTime());
        long jj = db.insert(Bloc_TABLE,null,c);
       // Log.d("bien inserez ","id -> " +jj);
       return jj;
    }

    //getnote return un seul note
    //getnotes return liste de note

    public Note getNote(long id){
//select * from table where id=nbr.
        SQLiteDatabase db   =   this.getWritableDatabase();
        Cursor cursor       =   db.query(Bloc_TABLE,new String[]{KEY_ID,KEY_TITLE, KEY_Content, KEY_DATE, KEY_TIME}, KEY_ID +"=?",
                new String[]{String.valueOf(id)},null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        return new Note(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),
                cursor.getString(3),cursor.getString(4));
    }

    //cette method permet de return les notes apartir de notre databases:

    public List<Note> getNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> allNotes = new ArrayList<>();
        //select * from databasename.

        String query = "SELECT * FROM " + Bloc_TABLE +" JOIN "  +users_TABLE  + " WHERE "+ Bloc_TABLE +"."+KEY_ID +"="+ users_TABLE+"."+ID + " ORDER BY "+KEY_ID+" DESC";

        // String query = "SELECT * FROM " + Bloc_TABLE + " ORDER BY "+KEY_ID+" DESC";
        // user_id+" INTEGER," + " FOREIGN KEY(user_id) REFERENCES TABLE_users(ID) "+")";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Note note = new Note();
                note.setID(Long.parseLong(cursor.getString(0)));
                note.setTitre(cursor.getString(1));
                note.setContenu((cursor.getString(2)));
                note.setDate(cursor.getString(3));
                note.setTime(cursor.getString(4));
                note.setId_user(Long.parseLong(cursor.getString(5)));

                allNotes.add(note);//toutes doit etre ajoute dans notre list
            }while(cursor.moveToNext());
        }

        return allNotes;
    }


//Methode modifier note et mis ajour le table
    public int modifierNote(Note note){
        SQLiteDatabase db  =  this.getWritableDatabase();
        ContentValues  v   =  new ContentValues();
        Log.d("edit","titre edite "+note.getTitre() + "\n timemmm "+note.getTime());
        v.put(KEY_TITLE,note.getTitre());
        //toolbar.setTitleTextColor(getResources().getColor(R.color.white))
        v.put(KEY_Content,note.getContenu());
        v.put(KEY_DATE,note.getDate());
        v.put(KEY_TIME,note.getTime());

        //Log.d("edit","titre edite "+db.modifierNote(note.getTitre()) + "\n timemmm "+db.note.getTime());
        return db.update(Bloc_TABLE,v,KEY_ID+"=?",new String[]{String.valueOf(note.getID())});

    }

    //Methode permet de suprimer un note de la db apartir de sont ID
    void SuprimerNote(long id){
        SQLiteDatabase  db =  this.getWritableDatabase();
        db.delete(Bloc_TABLE,KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();


    }


}