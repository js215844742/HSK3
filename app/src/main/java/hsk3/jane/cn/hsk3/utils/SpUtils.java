package hsk3.jane.cn.hsk3.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import hsk3.jane.cn.hsk3.base.AppConfig;
import hsk3.jane.cn.hsk3.base.MyApplication;

/**
 * Created by Jane on 2018/3/9.
 */

public class SpUtils {
    public static SharedPreferences getAppPreferences() {
        return MyApplication.getContext().getSharedPreferences(AppConfig.BASE_PACKAGE, Context.MODE_PRIVATE);
    }
    /**
     * 存储string类型数据
     * @param path
     * @param data
     */
    public static void saveStringPreference(String path, String data) {
        getAppPreferences().edit().putString(path, data).apply();
    }

    /**
     * 存储int数据类型
     * @param path
     * @param data
     */
    public static void saveIntPreference(String path, int data) {
        getAppPreferences().edit().putInt(path, data).apply();
    }

    /**
     * 存储long数据类型
     * @param path
     * @param data
     */
    public static void saveLongPreference(String path, long data) {
        getAppPreferences().edit().putLong(path, data).apply();
    }

    /**
     * 存储boolean数据类型
     * @param path
     * @param flag
     */
    public static void saveBooleanPreference(String path, boolean flag) {
        getAppPreferences().edit().putBoolean(path, flag).apply();
    }

    /**
     * 存储实体类
     * @param key
     * @param obj
     */
    public static void putBean(String key, Object obj) {
        if (obj instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                String string64 = new String(Base64.encode(baos.toByteArray(),
                        0));
                getAppPreferences().edit().putString(key, string64).apply();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException(
                    "the obj must implement Serializble");
        }
    }

    /**
     * 获取sp中的String类型数据
     * @param path
     * @return
     */
    public static String getStringPreference(String path){
        return getAppPreferences().getString(path, "");
    }
    public static String getStringPreference(String path, String defaultValue){
        return getAppPreferences().getString(path, defaultValue);
    }

    /**
     * 获取sp中的int类型数据
     * @param path
     * @return
     */
    public static int getIntPreference(String path){
        return getAppPreferences().getInt(path, 0);
    }
    public static int getIntPreference(String path, int defaultValue){
        return getAppPreferences().getInt(path, defaultValue);
    }

    /**
     * 获取sp中的long类型数据
     * @param path
     * @return
     */
    public static long getLongPreference(String path){
        return getAppPreferences().getLong(path, 0);
    }
    public static long getLongPreference(String path, long defaultValue){
        return getAppPreferences().getLong(path, defaultValue);
    }

    /**
     * 获取sp中的boolean类型数据
     * @param path
     * @return
     */
    public static boolean getBooleanPreference(String path){
        return getAppPreferences().getBoolean(path, false);
    }
    public static boolean getBooleanPreference(String path, boolean defaultValue){
        return getAppPreferences().getBoolean(path, defaultValue);
    }

    /**
     * 获取sp中的实体类数据
     * @return
     */
    public static Object getBean(String key) {
        Object obj = null;
        try {
            String base64 = getAppPreferences().getString(key,"");
            if (base64.equals("")) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 清除所有sp
     */
    public static void cleanAll(){
        getAppPreferences().edit().clear().apply();
    }
}
