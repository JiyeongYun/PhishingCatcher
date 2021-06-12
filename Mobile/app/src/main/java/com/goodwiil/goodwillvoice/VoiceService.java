package com.goodwiil.goodwillvoice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.goodwiil.goodwillvoice.util.ScreenManager;

import java.util.Locale;

public class VoiceService extends Service implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        tts = new TextToSpeech(this, this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.KOREAN);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }
            ScreenManager.printToast(this, "TTS GOOD");
            tts.speak("전화", TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

}