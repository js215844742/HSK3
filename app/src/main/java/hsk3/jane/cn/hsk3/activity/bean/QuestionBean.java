package hsk3.jane.cn.hsk3.activity.bean;

import java.util.ArrayList;

/**
 * Created by Jane on 2018/3/5.
 */

public class QuestionBean {
    String[] pinyin;
    String[] hanzi;
    String answer;
    String rightAnswer;

    public QuestionBean(String[] pinyin, String[] hanzi, String answer){
        setPinyin(pinyin);
        setHanzi(hanzi);
        setAnswer(answer);
    }

    public String[] getPinyin() {
        return pinyin;
    }

    public void setPinyin(String[] pinyin) {
        this.pinyin = pinyin;
    }

    public String[] getHanzi() {
        return hanzi;
    }

    public void setHanzi(String[] hanzi) {
        this.hanzi = hanzi;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
