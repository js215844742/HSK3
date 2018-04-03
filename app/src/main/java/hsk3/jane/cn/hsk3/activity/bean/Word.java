package hsk3.jane.cn.hsk3.activity.bean;

/**
 * Created by Jane on 2018/3/30.
 */

public class Word {
    private int id;
    private int index;
    private String word;
    private String pinyin;
    private String firstLetter;
    private String tone;
    private String type;
    private int classNumber;
    private String explane;
    private String lianxiang;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public String getExplane() {
        return explane;
    }

    public void setExplane(String explane) {
        this.explane = explane;
    }

    public String getLianxiang() {
        return lianxiang;
    }

    public void setLianxiang(String lianxiang) {
        this.lianxiang = lianxiang;
    }
}
