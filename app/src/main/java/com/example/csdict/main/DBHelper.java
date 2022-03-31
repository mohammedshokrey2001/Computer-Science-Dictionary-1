package com.example.csdict.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.csdict.DataModels.DataModelMessage;
import com.example.csdict.DataModels.DataModelUser;
import com.example.csdict.DataModels.DataModelWord;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // table one users
    public static final String USER_TABLE = "USER_TABLE";
    public static final String ID1 = "id";
    public static final String NAME = "NAME";
    public static final String PHONE = "PHONE";
    public static final String ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String EMAIL = "EMAIL";
    public static final String PASS = "PASS";
    public static final String PASS_Q = "PASS_QUESTION";
    public static final String PASS_A = "PASS_ANSWER";
    public static final String FAV_WORD = "FAV_WORDS";


    //table two words  
    public static final String WORD_DATA_TABLE = "WORD_DATA_TABLE";
    public static final String ID = "id";
    public static final String WORD_NAME = "name";
    public static final String WORD_DESCRIPTION = "word_description";
    public static final String WORD_ABBSERVATION = "word_APP";

    // table three messages
    public static final String  MESSAGES_TABLE = "MESSAGES_TABLE";
    public static final String  MESSAGES_SENDER = "MESSAGES_SENDER";
    public static final String  MESSAGES_RECIVER = "MESSAGES_RECIVER";
    public static final String  MESSAGES_TITLE = "MESSAGES_TITLE";
    public static final String  MESSAGES_FLAG = "MESSAGES_FLAG";
    public static final String  MESSAGES_CONTENT = "MESSAGES_CONTENT";


    //DATABASE WORK
    private  final Context context;
    private static final String DATABASE_NAME = "mysqldb.db";
    private static final int DATABASE_VERISON = 2;


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERISON);
        this.context = context;
    }



    @Override
    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase) {

        String query1 = "CREATE TABLE "+ USER_TABLE + " ( "
                 + ID + "  INTEGER PRIMARY KEY AUTOINCREMENT,  "
                +NAME +" VARCHAR (40) ,"
                +PASS +" VARCHAR (40) ,"
                +EMAIL +" VARCHAR (40) ,"
                +PHONE +" VARCHAR (40) ,"
                +PASS_A +" VARCHAR (40) ,"
                +PASS_Q +" VARCHAR (40) ,"
                +ACTIVE_CUSTOMER +" BOOL ,"
                +FAV_WORD +" VARCHAR (10000) " +

                " ); ";


        String query2 ="CREATE TABLE "+ WORD_DATA_TABLE + " ( "
                + ID + "  INTEGER PRIMARY KEY AUTOINCREMENT,  "
                +WORD_NAME +" VARCHAR (40) ,"
                +WORD_DESCRIPTION +" VARCHAR (500) ,"
                +WORD_ABBSERVATION + " VARCHAR(20) "
                +" ); "
                ;



       // String query3 = "CREATE TABLE " + MESSAGES_TABLE +"( ID INTEGER PRIMARY KEY AUTOINCREMENT,  )";
        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
        //sqLiteDatabase.execSQL(query3);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old, int news) {
              if (news >old){

                  String query ="CREATE TABLE "+ MESSAGES_TABLE + " ( "
                          + ID + "  INTEGER PRIMARY KEY AUTOINCREMENT,  "
                          +MESSAGES_TITLE +" VARCHAR (40) ,"
                          +MESSAGES_SENDER +" VARCHAR (40) ,"
                          +MESSAGES_RECIVER + " VARCHAR(40), "
                          +MESSAGES_CONTENT +" VARCHAR (4000) ,"
                          +MESSAGES_FLAG +" VARCHAR (40) "

                          +" ); "
                          ;





                  sqLiteDatabase.execSQL(query);


              }

    }

    boolean addUser(@NonNull DataModelUser dataModelUser){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(NAME ,dataModelUser.getName());
        cv.put(PASS ,  SecyrityOne.encyrptPassUser(dataModelUser.getPass()));
        cv.put(PASS_A ,dataModelUser.getReset_answer());
        cv.put(EMAIL ,dataModelUser.getMail());
        cv.put(PASS_Q,dataModelUser.getReset_qu());
        cv.put(PHONE , dataModelUser.getPhone());
        cv.put(FAV_WORD , dataModelUser.getFav());
        cv.put(ACTIVE_CUSTOMER ,true);

        long insert = database.insert(USER_TABLE ,null,cv);

        if ( insert==-1){

            return  false;
        }else {
            return true;
        }


    }

    public boolean addMessage(@NonNull DataModelMessage dataModelMessage){
        SQLiteDatabase database = this.getWritableDatabase();


        String encrupt_message =  SecyrityOne.encrypt(dataModelMessage.getMessage());
        ContentValues cv = new ContentValues();
        cv.put(MESSAGES_TITLE,dataModelMessage.getTitle());
        cv.put(MESSAGES_SENDER,dataModelMessage.getSender());
        cv.put(MESSAGES_RECIVER,dataModelMessage.getReceiver());
        cv.put(MESSAGES_CONTENT,encrupt_message);

        long insert = database.insert(MESSAGES_TABLE ,null,cv);

        if ( insert==-1){

            return  false;
        }else {
            return true;
        }


    }

    public  ArrayList<DataModelMessage> schMessages(String mail , String flag){
        ArrayList<DataModelMessage> modelMessages  = new ArrayList<DataModelMessage>();
        SQLiteDatabase database = this.getReadableDatabase();



        Cursor cursor = database.rawQuery("select * from " +
                        MESSAGES_TABLE + "  where  " + flag + " = ? ",
                new String[]{mail});

        if (cursor.getCount()==0){
            return  null;


        }
        else {

            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String sender = cursor.getString(2);
                String reciver = cursor.getString(3);
                String message = cursor.getString(4);
                Log.i("message: ", "schMessages1: "+message +"id = "+id);

                String message_plain  = SecyrityOne.decrypt(message);
                Log.i("message: ", "schMessages2: "+message_plain);
                DataModelMessage modelMessage = new DataModelMessage(message_plain,String.valueOf(id),sender,reciver,title);
                modelMessages.add(modelMessage);

            }
            return modelMessages;

        }

    }


    DataModelUser searchUser(String mail , String pass){
        pass  = SecyrityOne.encyrptPassUser(pass);
        DataModelUser modelUser  = null;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery("select * from " +
                        USER_TABLE + "  where  " + EMAIL + " = ? AND " + PASS +
                        " = ?",
                new String[]{mail, pass});


        if (cursor.getCount()==0){
            return  null;


        }
        else {

            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String maile = cursor.getString(3);
                String phone = cursor.getString(4);
                String user_fav = cursor.getString(8);

                modelUser = new DataModelUser(name, String.valueOf(id), maile, "", phone, "", "");
                modelUser.setFav(user_fav);


            }
            return modelUser;

        }

    }


    public ArrayList<DataModelUser> getAllUsers() {
    ArrayList<DataModelUser> modelUsers  = new ArrayList<DataModelUser>();
        String query = "SELECT * FROM "+USER_TABLE +"  ; ";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select *  from  " +
                        USER_TABLE ,
                new String[]{ });
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String pass = cursor.getString(2);
                String mail = cursor.getString(3);
                String phone = cursor.getString(4);
                String pass_a = cursor.getString(5);
                String pass_q = cursor.getString(6);
                boolean active_state = cursor.getInt(7)==1 ? true :false ;



                DataModelUser dataModel1 = new DataModelUser(name,String.valueOf(id),mail,pass,phone,pass_q,pass_a);

                if (modelUsers != null) {
                    modelUsers.add(dataModel1);
                }
            }while (cursor.moveToNext());

        }else {
            /////

        }


    return modelUsers;

    }


    public void deleteUser(String id){
        SQLiteDatabase db = getWritableDatabase();

       int d =  db.delete( USER_TABLE,"id=? ",new String[]{id});

    }


    public ArrayList<DataModelWord>getWords(String model ,String user_fav){

        ArrayList<DataModelWord> modelWord = new ArrayList<DataModelWord>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;

        if (model.equals("admin")){
            cursor = database.rawQuery("select * from "+ WORD_DATA_TABLE , new String[]{});
        }
        else if (model.equals("user")){

            cursor = database.rawQuery("select * from "+WORD_DATA_TABLE +" where id in( "+ user_fav +" )",new String[]{});
        }


        assert cursor != null;
        if (cursor.moveToFirst()) {
            do {


                int id = cursor.getInt(0);
                String word_name = cursor.getString(1);
                String word_desc = cursor.getString(2);
                String word_app = cursor.getString(3);

                DataModelWord dataModelWord = new DataModelWord(word_name,word_desc,String.valueOf(id),word_app);

                modelWord.add(dataModelWord);
            }while (cursor.moveToNext());



        } else {

            Toast.makeText(context.getApplicationContext(), "cant excute query", Toast.LENGTH_SHORT).show();
        }

        return modelWord;
    }



    public  boolean addWord(@NonNull DataModelWord word){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(WORD_NAME,word.getName());
        cv.put(WORD_DESCRIPTION,word.getDescription());
        cv.put(WORD_ABBSERVATION,word.getApservation());

        long insert = database.insert(WORD_DATA_TABLE ,null,cv);

        if ( insert==-1){

            return  false;
        }else {
            return true;
        }


    }


    public ArrayList<DataModelWord> get_search_word(String word){
        ArrayList<DataModelWord> words = new ArrayList<DataModelWord>();


        ArrayList<DataModelWord> modelWord = new ArrayList<DataModelWord>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;


            cursor = database.rawQuery("select * from "+WORD_DATA_TABLE +
                    " where "+ WORD_NAME + " like '%"+word +"%'" +" or "+

                    WORD_ABBSERVATION + " like '%"+ word +"%' ;"  ,new String[]{});



        assert cursor != null;
        if (cursor.moveToFirst()) {
            do {


                int id = cursor.getInt(0);
                String word_name = cursor.getString(1);
                String word_desc = cursor.getString(2);
                String word_app = cursor.getString(3);

                DataModelWord dataModelWord = new DataModelWord(word_name,word_desc,String.valueOf(id),word_app);

                words.add(dataModelWord);
            }while (cursor.moveToNext());



        } else {

            Toast.makeText(context.getApplicationContext(), "cant search or cant find", Toast.LENGTH_SHORT).show();
        }


        return  words;
    }


    void addWordFav(int id_word , int id_user ){
        SQLiteDatabase database = getWritableDatabase();
        String fav = null;
        Cursor cursor1 ;

        cursor1 = database.rawQuery(" select "+FAV_WORD +" from " + USER_TABLE +"  WHERE "+ ID +"="+id_user,new String[]{});
        if (cursor1.moveToFirst()){
            do {
                 fav = cursor1.getString(0);

            }while (cursor1.moveToNext());
        }else {
            Toast.makeText(context.getApplicationContext(), "error in get data from user 1", Toast.LENGTH_SHORT).show();
        }
        //////////
        if (fav!=null) {
            fav = new StringBuilder().append(fav).append(",").append(String.valueOf(id_word)).toString();
        }
        else{

            fav = String.valueOf(id_word);
           // Toast.makeText(context.getApplicationContext(), "the id of user is "+id_user + "id of word is "+fav, Toast.LENGTH_SHORT).show();

        }


            cursor1 = database.rawQuery("update "+USER_TABLE + " set " + FAV_WORD +"='"+fav +"' where id ="+id_user,new String[]{});

            if (cursor1.moveToFirst()){


            }
            else {

            }
        }


        DataModelUser resetPass(String mail) {
            DataModelUser data = null;
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery("select * from " +
                            USER_TABLE + "  where  " + EMAIL + " = ? " + " ; "
                    ,
                    new String[]{mail});

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String pass_a = cursor.getString(5);
                    String pass_q = cursor.getString(6);
                    boolean active_state = cursor.getInt(7) == 1 ? true : false;


                    data = new DataModelUser("",String.valueOf(id),"","","" ,pass_q ,pass_a);
                } while (cursor.moveToNext());

            }


        return  data;

        }




        boolean set_new_pass(String new_pass ,int id_user){

        new_pass = SecyrityOne.encyrptPassUser(new_pass);

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor  cursor1 = database.rawQuery("update "+USER_TABLE + " set " + PASS +"='"+new_pass +"' where id ="+id_user,new String[]{});

            return (cursor1.moveToFirst());

        }




        public void deleteMessage(String id){
            SQLiteDatabase db = getWritableDatabase();

            int d =  db.delete( MESSAGES_TABLE,"id=? ",new String[]{id});


        }
    }

