package hsk3.jane.cn.hsk3.utils;

/**
 * Created by Jane on 2018/3/8.
 */

public class MathUtils {
    /**
     * 将阿拉伯数字转换为中文数字
     * @param number
     * @return
     */
    public static String getChineseNumber(int number) {
        String result = "";
//        String[] str = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String[] str = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
//        String ss[] = new String[] { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿" };
        String ss[] = new String[] { "", "十", "百", "千", "万", "十", "百", "千", "亿", "十"};
        String string = String.valueOf(number);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            String index = String.valueOf(string.charAt(i));
            sb = sb.append(str[Integer.parseInt(index)]);
        }
        String sss = String.valueOf(sb);
        String[] a = sss.split("");
        for (int i = 1; i < a.length; i++) {
            if (!a[i].equals("") && !a[i].equals("零")){
                if (a.length==3 && a.length-1-i==1 && a[i].equals("一")){
                    result += ss[a.length-1-i];
                }else {
                    result += a[i] + ss[a.length - 1 - i];
                }
            }else if (a.length-1-i == 4){
                if (!a[i].equals("") && !a[i].equals("零")) {
                    result += a[i] + ss[a.length-1-i];
                }else{
                    result = deleteEnd0(result);
                    result += ss[a.length-1-i];
                }
            }else{
                result += a[i];
            }
        }
        result = delete00(result);
        result = deleteEnd0(result);
        return result;
    }

    /**
     * 多个零在一起的时候去掉一个零
     * @param str
     * @return
     */
    private static String delete00(String str){
        str = str.replace("零零", "零");
        if (str.contains("零零")){
            str = delete00(str);
        }
        return str;
    }

    /**
     * 去掉末尾的零
     * @param str
     * @return
     */
    private static String deleteEnd0(String str){
        if (str.length()>1&&str.endsWith("零")){
            str = str.substring(0, str.length()-1);
            str = deleteEnd0(str);
        }
        return str;
    }
}
