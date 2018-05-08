package hsk3.jane.cn.hsk3.utils;

import android.database.Cursor;

import hsk3.jane.cn.hsk3.base.MyApplication;
import hsk3.jane.cn.hsk3.db.WordDBAdapter;

/**
 * Created by Jane on 2018/3/21.
 */

public class Utils {
    public static String getTitle(int type){
        String title = "";
        switch (type){
            case 1:
                title = "连词成句";
                break;
            case 2:
                title = "句子练习";
                break;
            case 3:
                title = "造句练习";
                break;
            case 4:
                title = "声调练习";
                break;
        }
        return title;
    }

    /**
     * 造句练习，句子评价
     * @return
     * @param myAnswer
     * @param syntax
     */
    public static String getEvaluate(String myAnswer, String[] syntax){
        int indexStart = 0;
        int indexEnd = 1;
        myAnswer.substring(indexStart, indexEnd);

        return getEvalute(myAnswer, indexStart, indexEnd, syntax[0], 0);
//        return "暂无评价。";
    }

    public static WordDBAdapter dbAdapter;
    public static String getEvalute(String myAnswer, int indexStart, int indexEnd, String syntax, int index){
        String [] types = syntax.split("\\+");
        String word = myAnswer.substring(indexStart, indexEnd);//单词
        dbAdapter = MyApplication.getDbAdapter();
        if (dbAdapter.isExist(word)) {
            Cursor cursor = dbAdapter.searchExact(word);
            cursor.moveToFirst();
            String wordType = cursor.getString(cursor.getColumnIndex(WordDBAdapter.KEY_TYPE));//
            if (checkType(types[index], wordType, word)){
                return "匹配";
            }else{
                return "语法不匹配";
            }
        }else{
            return "包含超纲词语";
        }

    }

    /**
     * 检查词性是否匹配
     * @param type
     * @param wordType
     * @return
     */
    private static boolean checkType(String type, String wordType, String word) {
        if (wordType.equals("n.")){//名词
            if (type.equals("n") || type.equals("n.") || type.equals("名") || type.equals("名词")){
                return true;
            }
        }else if (wordType.equals("v.")){//动词
            if (type.equals("v") || type.equals("v.") || type.equals("动") || type.equals("动词")){
                return true;
            }
        }else if (wordType.equals("adj.")){//形容词
            if (type.equals("adj") || type.equals("adj.") || type.equals("形") || type.equals("形容") || type.equals("形容词")){
                return true;
            }
        }else if (wordType.equals("adv.")){//副词
            if (type.equals("adv") || type.equals("adv.") || type.equals("副") || type.equals("副词")){
                return true;
            }
        }else if (wordType.equals("pron.")){//代词
            if (type.equals("pron") || type.equals("pron.") || type.equals("代") || type.equals("代词")){
                return true;
            }
        }else if (wordType.equals("prep.")){//介词
            if (type.equals("prep") || type.equals("prep.") || type.equals("介") || type.equals("介词")){
                return true;
            }
        }else if (wordType.equals("num.")){//数词
            if (type.equals("num") || type.equals("num.") || type.equals("数") || type.equals("数词")){
                return true;
            }
            if (type.equals("m") || type.equals("m.") || type.equals("量") || type.equals("量词")){
                return true;
            }
            if (type.equals("数量") || type.equals("数量词")){
                return true;
            }
        }else if (wordType.equals("conj.")){//连词
            if (type.equals("conj") || type.equals("conj.") || type.equals("连") || type.equals("连词")){
                return true;
            }
        }else if (wordType.equals("part.")){//助词
            if (type.equals("part") || type.equals("part.") || type.equals("助") || type.equals("助词")){
                return true;
            }
        }else if (wordType.equals("int.")){//叹词
            if (type.equals("num") || type.equals("num.") || type.equals("数") || type.equals("数词")){
                return true;
            }
        }else if (wordType.equals("onom.")){//拟声词
            if (type.equals("onom") || type.equals("onom.") || type.equals("声") || type.equals("拟") || type.equals("拟声") || type.equals("拟声词")){
                return true;
            }
        }else if (wordType.equals("pref.")){//前缀
            if (type.equals("pref") || type.equals("pref.") || type.equals("前缀") || type.equals("前缀词")){
                return true;
            }
        }else if (wordType.equals("suf.")){//后缀
            if (type.equals("suf") || type.equals("suf.") || type.equals("后缀") || type.equals("后缀词")){
                return true;
            }
        }else if (wordType.equals("m.")){//量词
            if (type.equals("num") || type.equals("num.") || type.equals("数") || type.equals("数词")){
                return true;
            }
            if (type.equals("m") || type.equals("m.") || type.equals("量") || type.equals("量词")){
                return true;
            }
            if (type.equals("数量") || type.equals("数量词")){
                return true;
            }
        }else if (wordType.equals("num.-m.")){//数量词
            if (type.equals("num") || type.equals("num.") || type.equals("数") || type.equals("数词")){
                return true;
            }
            if (type.equals("m") || type.equals("m.") || type.equals("量") || type.equals("量词")){
                return true;
            }
            if (type.equals("数量") || type.equals("数量词")){
                return true;
            }
        }else if (wordType.equals("mod.")){//能愿动词
            if (type.equals("mod") || type.equals("mod.") || type.equals("能愿") || type.equals("能愿动词")){
                return true;
            }
        }else{
            if (type.equals(word)){
                return true;
            }
        }
        return false;
    }
}
