package hsk3.jane.cn.hsk3.utils;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * 语音播放工具
 * Created by Jane on 2018/3/14.
 */

public class AudioUtils {
    private static AudioUtils audioUtils;
    private SpeechSynthesizer mySynthesizer;

    public AudioUtils() {
    }

    /**
     * 单例
     * @return
     */
    public static AudioUtils getInstance() {
        if (audioUtils == null) {
            synchronized (AudioUtils.class) {
                if (audioUtils == null) {
                    audioUtils = new AudioUtils();
                }
            }
        }
        return audioUtils;
    }
    private InitListener myInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d("mySynthesiezer:", "InitListener init() code = " + code);
        }
    };

    /**
     * 初始化语音配置
     * @param context
     */
    public void init(Context context) {
        //处理语音合成关键类
        mySynthesizer = SpeechSynthesizer.createSynthesizer(context, myInitListener);
        //设置发音人
        mySynthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        //设置音调
        mySynthesizer.setParameter(SpeechConstant.PITCH, "50");
        //设置音量
        mySynthesizer.setParameter(SpeechConstant.VOLUME, "50");
        //设置语速
        mySynthesizer.setParameter(SpeechConstant.SPEED, "50");
    }

    /**
     * 初始化语音配置
     * @param context
     */
    public void init(Context context, int speed) {
        //处理语音合成关键类
        mySynthesizer = SpeechSynthesizer.createSynthesizer(context, myInitListener);
        //设置发音人
        mySynthesizer.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        //设置音调
        mySynthesizer.setParameter(SpeechConstant.PITCH, "50");
        //设置音量
        mySynthesizer.setParameter(SpeechConstant.VOLUME, "50");
        //设置语速
        mySynthesizer.setParameter(SpeechConstant.SPEED, speed+"");
    }

    /**
     * 根据传入的文本转换音频并播放
     * @param string
     */
    public void speakText(String string) {
        int code = mySynthesizer.startSpeaking(string, new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {}
            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {}
            @Override
            public void onSpeakPaused() {}
            @Override
            public void onSpeakResumed() {}
            @Override
            public void onSpeakProgress(int i, int i1, int i2) {}
            @Override
            public void onCompleted(SpeechError speechError) {}
            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {}
        });
    }
    public boolean isSpeaking(){
        return mySynthesizer.isSpeaking();
    }
    public void resume(){
        mySynthesizer.resumeSpeaking();
    }
    public void pause(){
        mySynthesizer.pauseSpeaking();
    }
    public void stopSpeak(){
        mySynthesizer.stopSpeaking();
    }
}
