package hsk3.jane.cn.hsk3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import hsk3.jane.cn.hsk3.data.WordData;

/**
 * 词库数据库
 * Created by Jane on 2018/3/30.
 */

public class WordDBAdapter {
    static final int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "words.db";
    private static String DATABASE_TABLE = "";

    private static String KEY_ID = "key_id";//ID
    private static String KEY_HANZI = "key_hanzi";//汉字
    private static String KEY_PINYIN = "key_pinyin";//拼音
    private static String KEY_INDEX = "key_index";//序号
    private static String KEY_FIRST = "key_first";//首字母
    private static String KEY_LIANXIANG = "key_lianxiang";//联想功能

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    public WordDBAdapter(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("CREATE TABLE " + DATABASE_TABLE +" (");
            stringBuffer.append(KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,");
            stringBuffer.append(KEY_HANZI + "TEXT,");
            stringBuffer.append(KEY_PINYIN + "TEXT,");
            stringBuffer.append(KEY_INDEX + "TEXT,");
            stringBuffer.append(KEY_FIRST + "TEXT,");
            stringBuffer.append(KEY_LIANXIANG + "TEXT,");
            sqLiteDatabase.execSQL(stringBuffer.toString());
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    public WordDBAdapter open(){
        db = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public long insert(int index){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, WordData.WORDS[index][0]);
        contentValues.put(KEY_HANZI, WordData.WORDS[index][1]);
        contentValues.put(KEY_PINYIN, WordData.WORDS[index][2]);
        contentValues.put(KEY_FIRST, WordData.WORDS[index][3]);
        contentValues.put(KEY_INDEX, WordData.WORDS[index][4]);
        contentValues.put(KEY_LIANXIANG, WordData.WORDS[index][5]);
        return db.insert(DATABASE_TABLE, null, contentValues);
    }

    public boolean isExist(int index){
        Cursor cursor = db.rawQuery("SELECT " + KEY_ID + " FROM " + DATABASE_TABLE +" WHERE "+ KEY_ID +" = \'" + index + "\'", null);
        if (cursor.getCount()>0){
            cursor.close();
            return true;
        }
        return false;
    }

    public Cursor getAll(){
        return db.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);
    }

    public Cursor getAllByLetter(){
        return db.query(DATABASE_TABLE, new String[]{
                KEY_ID, KEY_HANZI, KEY_PINYIN, KEY_FIRST, KEY_INDEX, KEY_LIANXIANG},
                null, null, null, null, KEY_FIRST);
    }

    public void deleteAll(){
        db.delete(DATABASE_TABLE, null ,null);
    }

    public int getPosition(String letter){
        Cursor cursor = db.rawQuery("SELECT " + KEY_ID + " FROM " + DATABASE_TABLE + " WHERE " + KEY_FIRST + " = \'" + letter +"\'", null);
        int position = 0;
        int i = cursor.getPosition();
        if (i>0){
            position = i;
        }
        if (!cursor.isClosed())
            cursor.close();
        return position;
    }

    /**
     * 模糊查询
     * @param string
     * @return
     */
    public Cursor search(String string){
        return db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE (" +
                KEY_HANZI + " LIKE \'"+string+"\') OR (" +
                KEY_PINYIN + " LIKE \'"+string+"\')", null);
    }

    public int updataItem(int index){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, WordData.WORDS[index][0]);
        contentValues.put(KEY_HANZI, WordData.WORDS[index][1]);
        contentValues.put(KEY_PINYIN, WordData.WORDS[index][2]);
        contentValues.put(KEY_FIRST, WordData.WORDS[index][3]);
        contentValues.put(KEY_INDEX, WordData.WORDS[index][4]);
        contentValues.put(KEY_LIANXIANG, WordData.WORDS[index][5]);
        return db.update(DATABASE_TABLE, contentValues, KEY_ID+"=?", new String[]{KEY_ID});
    }
}
