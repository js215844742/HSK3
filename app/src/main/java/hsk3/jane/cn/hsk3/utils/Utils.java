package hsk3.jane.cn.hsk3.utils;

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
        int index = 1;
        myAnswer.substring(0, index);

        return "暂无评价。";
    }
}
