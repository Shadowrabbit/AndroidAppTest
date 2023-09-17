package com.example.myapplication.DataCenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * 数据库管理器
 */
public class DbManager {
    private final DbHelper _dbHelper; //数据库创建接口
    private SQLiteDatabase db;

    public DbManager(Context context, int version) {
        _dbHelper = new DbHelper(context, "Rescuer.db", null, 1);
    }

    /**
     * 添加救援者数据
     */
    public long addRescuerData(String name, int age, String desc) {
        long row = 0;
        try {
            db = _dbHelper.getReadableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("age", age);
            contentValues.put("desc", desc);
            row = db.insert(SqlDef.RescuerTableName, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return row;
    }

    /**
     * 移除救援者数据
     */
    public int deleteRescuerData(int sid) {

        int num = 0;
        try {
            db = _dbHelper.getReadableDatabase();
            num = db.delete(SqlDef.RescuerTableName, "sid = ?", new String[]{Integer.toString(sid)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return num;
    }

    /**
     * 修改救援者数据
     */
    public int updateRescuerData(RescuerData rescuerData) {
        int num = 0;
        try {
            db = _dbHelper.getReadableDatabase();
            int sid = rescuerData.getSid();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", rescuerData.getName());
            contentValues.put("age", rescuerData.getAge());
            contentValues.put("desc", rescuerData.getDesc());
            num = db.update(SqlDef.RescuerTableName, contentValues, "sid = ?", new String[]{Integer.toString(sid)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return num;
    }

    /**
     * 查询救援者列表
     */
    public ArrayList<RescuerData> queryRescuers() {
        ArrayList<RescuerData> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            db = _dbHelper.getReadableDatabase();
            cursor = db.rawQuery(SqlDef.QueryAllRescuer, null);
            while (cursor.moveToNext()) {
                RescuerData rescuerData = new RescuerData();
                rescuerData.setSid(cursor.getInt(cursor.getColumnIndexOrThrow("sid")));
                rescuerData.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                rescuerData.setAge(cursor.getInt(cursor.getColumnIndexOrThrow("age")));
                rescuerData.setDesc(cursor.getString(cursor.getColumnIndexOrThrow("desc")));
                arrayList.add(rescuerData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
            if (cursor != null) {
                cursor.close();
            }
        }
        return arrayList;
    }
}