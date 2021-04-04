package com.example.project.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static com.example.project.data.QuizContract.MovieEntry.KEY_ANSWER;
import static com.example.project.data.QuizContract.MovieEntry.KEY_ID;
import static com.example.project.data.QuizContract.MovieEntry.KEY_OPTA;
import static com.example.project.data.QuizContract.MovieEntry.KEY_OPTB;
import static com.example.project.data.QuizContract.MovieEntry.KEY_OPTC;
import static com.example.project.data.QuizContract.MovieEntry.KEY_QUES;
import static com.example.project.data.QuizContract.MovieEntry.TABLE_QUEST;

public class DbHelper extends SQLiteOpenHelper {
    private static final String ENCODING = "UTF-8";
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "triviaQuiz";
    // tasks table name

    private SQLiteDatabase dbase;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }
    private void addQuestions()
    {
        Question q1=new Question("ฉันยิ้มให้กับเพื่อนร่วมงาน แม้ว่าฉันจะเพิ่งทะเลาะกับน้องหรือพี่ของฉัน","ใช่", "ไม่ ", "Security Exception", "ไม่");
        this.addQuestion(q1);
        Question q2=new Question("ฉันสามารถพูดคุยได้อย่างปกติกับคนที่ฉันไม่ชอบ", "SQLite", "BackupHelper", "NetworkInfo", "SQLite");
        this.addQuestion(q2);
        Question q3=new Question("แม้จะมีเรื่องไม่สบายใจ แต่ฉันก็ยังมีสมาธิในการทำงาน","Wi-Fi radio", "Service Content Provider","Ducking", "Service Content Provider" );
        this.addQuestion(q3);
        Question q4=new Question("ฉันสามารถยิ้มให้กับคนที่ฉันรู้ว่าเขาเกลียดฉัน", "LocationManager", "AttributeSet", "SQLiteOpenHelper","LocationManager");
        this.addQuestion(q4);
        Question q5=new Question("เวลาฉันโกรธ ฉันจะพยายามควบคุมอารมณ์โกรธไว้","NetworkInfo","GooglePlay","Linux Based","Linux Based");
        this.addQuestion(q5);
        Question q6=new Question("1ันรู้สาเหตุในการเกิดอารมณ์ต่าง ๆ ของฉัน","ใช่","ไม่","ไม่ใช่","ไม่");
        this.addQuestion(q6);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST + " ORDER BY "+ "RANDOM()";
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
            Collections.shuffle(quesList);
        }
        // return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}
