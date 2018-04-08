package hsk3.jane.cn.hsk3.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 句子练习的数据，句法和例句
 * Created by Jane on 2018/3/6.
 */

public class SentenceData {
    /**
     * 25种句型的公式
     */
    public static String[][] SYNTAX = {
            {"N+（N）+一+M+N+也/都+Negator（不/没）+V"},//句法1
            {"N+一点儿+N+也/都+Negator (不/没)+V"},
            {"N+Adv+(Negator)+V+好+（V）+N"},
            {"N+N+（Negator没）V+着+Num+M+N"},
            {"N+V+N（place）+来/去+（V）","N+V+N+来/去 Or N+V+来/去+N"},
            {"N+V+了+N（+N）就+V（+V+N）"},
            {"N+想/要+V+N+还是+N？"},
            {"N+V+N+或者+N"},
            {"N（〔+M〕+N）+Adv（不）+Adj","N(〔+M〕+N)+又+Adj+又+Adj"},
            {"N+（Adv）+V1+着（+Prep.+N）+V+N"},
            {"N+Adv+V+过+N+了","N+Negator(没有)+V+过+N","N+V+过+N+没有？"},
            {"N+V+过+Num+次/遍+N（destination）","N+（N）+V+过+N(destination)+Num+次/遍","N+V+过+Pron+Num+次"},
            {"N+比+N+Adj（+Num-M）","N+Negator（没有）+N+Adj。"},
            {"N+V+得+Adj。"},
            {"N+V+N+V+得+Adj","N+V+N+V+得+Adj。"},
            {"越来越+Adj","越来越+Mental Verb（想、喜欢、爱、担心、害怕、习惯）"},
            {"“了”在句尾，“了”at the end of a sentence indicating a change of condition"},
            {"V+不+Adj/V+N"},
            {"N+V+了+Num+M+（N）"},
            {"N+Prep（跟、从、离、给、对）+N+V/Adj(+N)"},
            {"（N1+V）+哪儿/什么/谁/什么时候（+VP/Adj）+N2+就+V+哪儿/什么/谁/什么时候"},
            {"又+V indicating the recurrence has already happened","再+V indicating the recurrence has not happened yet."},
            {"越A越B the structure the more A, the more B means B changes with A."},
            {"以前"},
            {"以后"}
    };
        /**
         * 25种句型的例句
         */
        public static String[][] EXAMPLE = {
                {"我一天也没休息。"},
                {"今天一点儿雨都没下。"},
                {"我们要去旅游，已经买好火车票了。"},
                {"我家的桌子上放着一张报纸。"},
                {"她不在房间，她下楼去了。"},
                {"我下了课就吃饭。"},
                {"你想喝咖啡还是茶？"},
                {"今天晚上吃米饭或者面条都可以。"},
                {"他的女儿非常聪明。"},
                {"他总是站着吃饭。"},
                {"我们已经学过这个汉字了。"},
                {"我们看过三次电影。","我们去过北京三次。"},
                {"哥哥比姐姐高（一点儿）。","哥哥没有姐姐高"},
                {"他说得很流利。","他说汉语说得很流利。"},
                {"她比我学得好。","她学得比我好。"},
                {"你越来越漂亮。","孩子还没有回来，妈妈越来越担心。"},
                {"上个月天气很冷，现在天气不那么冷了。","我前几天有点儿发烧，现在好多了。"},
                {"我看不清楚那个汉字。"},
                {"我们唱了两个小时歌。"},
                {"我跟阿里一起去商店。","他家离学校特别近。","我从学校出发。","我给他介绍情况。","他对国外的生活不习惯。"},
                {"什么东西又好又便宜我们就买什么东西。","你去哪儿我就去哪儿。","你哪天有时间就哪天来我家吧。","谁喜欢他他就喜欢谁。"},
                {"上个星期我买了一条裤子，昨天又买了一条。","你只吃了一点儿饭，再吃一点儿吧。"},
                {"雨越下越大。","越往南，天气越热。","山很高，我们越往上爬，路越难走。我们也越爬越冷。"},
                {"以前我在我的国家学习中文；现在我在杭州学习中文。（previously）","我工作以前，我在北京学习计算机。（before）","这都是以前的事了，别提了。（previous）"},
                {"感冒以后要多喝水，多休息。（after）","以后我的中文会说得越来越好。（later）","以后的事情以后再说，现在不要担心 （future）"}
        };
        /**
         * 25种句型的例句的翻译
         */
        public static String[] EXAMPLE_TRANSLATION = {
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "She is doing with her study better than me.",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        };



}
