// edited by Victoria Latta
package com.team8.memesplaining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.EditText;
import android.widget.Button;
import java.util.Locale;
import android.widget.Toast;
import android.view.View;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

public class TtsActivity extends AppCompatActivity
{
    private TextToSpeech mTTS;
    private EditText mEditText;
    private SeekBar mSeekBarPitch;
    private SeekBar mSeekBarSpeed;
    private Button mButtonSpeak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // initialize TTS engine
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        //mEditText = findViewById(R.id.edit_text);
        //mSeekBarPitch = findViewById(R.id.seek_bar_pitch);
        //mSeekBarSpeed = findViewById(R.id.seek_bar_speed);

        //mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //speak();
            //}
        //});
    }

    // speak the content of the meme to the user
    // text from OCR fed to String text
    private void speak() {
        String text = mEditText.getText().toString();
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
    }
}