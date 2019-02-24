package com.example.moon.dailyexpences;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {



    public SQLHelper(Context context) {
        super(context, SQL_names.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_FOR_TABLE = "create table if not exists " + SQL_names.TABLE_NAME + " ( " + SQL_names.COL_1 + " text, "
                + SQL_names.COL_2 + " text, "+ SQL_names.COL_3 + " text, "+ SQL_names.COL_4+ " text, " + SQL_names.COL_5 + " number );";
        db.execSQL(SQL_FOR_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertValues(String year,String month,String day,String type, int expence){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQL_names.COL_1,year);
        contentValues.put(SQL_names.COL_2,month);
        contentValues.put(SQL_names.COL_3,day);
        contentValues.put(SQL_names.COL_4,type);
        contentValues.put(SQL_names.COL_5,expence);

        SQLiteDatabase db = getWritableDatabase();
        long insert = db.insert(SQL_names.TABLE_NAME, null, contentValues);
        if(insert>0){
            return true;
        }else
            return false;
    }


    public Cursor readValue(String year,String month,String day,String type){
//        String sql = "select * from " + SQL_names.TABLE_NAME + " where " + SQL_names.COL_1 + " = " + year +", "+ SQL_names.COL_2 + " = " + month + " , "
//                + SQL_names.COL_3 + " = " + day + " , " + SQL_names.COL_4 + " = " + type +" ; ";


//        SQLiteDatabase db = getWritableDatabase();
//        Cursor cursor = db.rawQuery(sql,null);

        SQLiteDatabase db = getWritableDatabase();
        String[] colums = {SQL_names.COL_1,SQL_names.COL_2,SQL_names.COL_3,SQL_names.COL_4,SQL_names.COL_5};
        String selection = SQL_names.COL_1 +"=? and " + SQL_names.COL_2 + "=? and " + SQL_names.COL_3 + "=? and " + SQL_names.COL_4 +"=? "; //and " + SQL_names.COL_2 +"=? and " + SQL_names.COL_3 + "=? and " + SQL_names.COL_4 +"=?";
        String args[] = {year,month,day,type};
        Cursor cursor = db.query(SQL_names.TABLE_NAME, colums, selection, args, null, null, null);

        return cursor;
    }


    public Cursor readValue(String year,String month,String type){
//        String sql = "select * from " + SQL_names.TABLE_NAME + " where " + SQL_names.COL_1 + " = " + year +", "+ SQL_names.COL_2 + " = " + month + " , "
//                + SQL_names.COL_3 + " = " + day + " , " + SQL_names.COL_4 + " = " + type +" ; ";


//        SQLiteDatabase db = getWritableDatabase();
//        Cursor cursor = db.rawQuery(sql,null);

        SQLiteDatabase db = getWritableDatabase();
        String[] colums = {SQL_names.COL_1,SQL_names.COL_2,SQL_names.COL_3,SQL_names.COL_4,SQL_names.COL_5};
        String selection = SQL_names.COL_1 +"=? and " + SQL_names.COL_2 + "=? and " + SQL_names.COL_4 +"=? "; //and " + SQL_names.COL_2 +"=? and " + SQL_names.COL_3 + "=? and " + SQL_names.COL_4 +"=?";
        String args[] = {year,month,type};
        Cursor cursor = db.query(SQL_names.TABLE_NAME, colums, selection, args, null, null, null);

        return cursor;
    }
}
