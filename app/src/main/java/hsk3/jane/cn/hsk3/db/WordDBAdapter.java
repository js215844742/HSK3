package hsk3.jane.cn.hsk3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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
    private static String DATABASE_TABLE = "word_table";

    public static String KEY_ID = "key_id";//ID
    public static String KEY_INDEX = "key_index";//序号
    public static String KEY_WORD = "key_word";//词语
    public static String KEY_PINYIN = "key_pinyin";//拼音
    public static String KEY_FIRST_LETTER = "key_first_letter";//首字母
    public static String KEY_TONE = "key_tone";//声调
    public static String KEY_TYPE = "key_type";//词性
    public static String KEY_CLASS_NUMBER = "key_class_number";//课号
    public static String KEY_EXPLANE = "key_explane";//词义
    public static String KEY_LIANXIANG = "key_lianxiang";//联想功能

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
            stringBuffer.append(KEY_INDEX+ " INTEGER,");
            stringBuffer.append(KEY_WORD + " TEXT,");
            stringBuffer.append(KEY_PINYIN + " TEXT,");
            stringBuffer.append(KEY_FIRST_LETTER + " TEXT,");
            stringBuffer.append(KEY_TONE + " TEXT,");
            stringBuffer.append(KEY_TYPE + " TEXT,");
            stringBuffer.append(KEY_CLASS_NUMBER + " INTEGER,");
            stringBuffer.append(KEY_EXPLANE + " TEXT,");
            stringBuffer.append(KEY_LIANXIANG + " TEXT);");
            try {
                sqLiteDatabase.execSQL(stringBuffer.toString());
            }catch (SQLException e){
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        }
    }

    public WordDBAdapter open() throws SQLException{
        db = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public long insert(int index){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, Integer.valueOf(WordData.WORDS[index][0]));
        contentValues.put(KEY_INDEX, Integer.valueOf(WordData.WORDS[index][1]));
        contentValues.put(KEY_WORD, WordData.WORDS[index][2]);
        contentValues.put(KEY_PINYIN, WordData.WORDS[index][3]);
        contentValues.put(KEY_FIRST_LETTER, WordData.WORDS[index][4]);
        contentValues.put(KEY_TONE, WordData.WORDS[index][5]);
        contentValues.put(KEY_TYPE, WordData.WORDS[index][6]);
        contentValues.put(KEY_CLASS_NUMBER, Integer.valueOf(WordData.WORDS[index][7]));
        contentValues.put(KEY_EXPLANE, WordData.WORDS[index][8]);
        contentValues.put(KEY_LIANXIANG, "");
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

    public boolean isExist(String word){
        Cursor cursor = db.rawQuery("SELECT " + KEY_ID + " FROM " + DATABASE_TABLE +" WHERE "+ KEY_WORD +" = \'" + word + "\'", null);
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
                KEY_ID, KEY_INDEX, KEY_WORD, KEY_PINYIN, KEY_FIRST_LETTER, KEY_TONE,KEY_TYPE, KEY_CLASS_NUMBER, KEY_EXPLANE, KEY_LIANXIANG},
                null, null, null, null, KEY_FIRST_LETTER);
    }

    public void deleteAll(){
        db.delete(DATABASE_TABLE, null ,null);
    }

    public int getPosition(String letter){
        Cursor cursor = db.rawQuery("SELECT " + KEY_ID + " FROM " + DATABASE_TABLE + " WHERE " + KEY_FIRST_LETTER + " = \'" + letter +"\'", null);
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
     * 精确查找
     * @param string
     * @return
     */
    public Cursor searchExact(String string){
        return db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE (" +
        KEY_WORD + " = \'" + string + "\')", null);
    }

    /**
     * 模糊查询
     * @param string
     * @return
     */
    public Cursor searchFuzzy(String string){
        return db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE (" +
                KEY_WORD + " LIKE \'"+string+"\') OR (" +
                KEY_PINYIN + " LIKE \'"+string+"\')", null);
    }

    public int updataItem(int index){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, Integer.valueOf(WordData.WORDS[index][0]));
        contentValues.put(KEY_INDEX, Integer.valueOf(WordData.WORDS[index][1]));
        contentValues.put(KEY_WORD, WordData.WORDS[index][2]);
        contentValues.put(KEY_PINYIN, WordData.WORDS[index][3]);
        contentValues.put(KEY_FIRST_LETTER, WordData.WORDS[index][4]);
        contentValues.put(KEY_TONE, WordData.WORDS[index][5]);
        contentValues.put(KEY_TYPE, WordData.WORDS[index][6]);
        contentValues.put(KEY_CLASS_NUMBER, Integer.valueOf(WordData.WORDS[index][7]));
        contentValues.put(KEY_EXPLANE, WordData.WORDS[index][8]);
        contentValues.put(KEY_LIANXIANG, "");
        return db.update(DATABASE_TABLE, contentValues, KEY_ID+"=?", new String[]{index+""});
    }
}
