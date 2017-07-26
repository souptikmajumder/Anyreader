package com.example.user.finaltess;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static android.R.attr.path;

public class Result extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private static final String TAG = "result activity::";
    Button replay,play,add;
    ImageButton imgClose;
    ScrollView sv;
    TextView t1;
    String path;
    Bitmap bt;
    int length;
    int count=0;
    int a=0;int b=0;int c=0;
    String s1,s2,s3;
    int tapcount=0;
    int mycount=0;
    String recognizedText;
    MediaPlayer myplayer;
    TextToSpeech tts;
    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tts=new TextToSpeech(this,this);
        bt = BitmapFactory.decodeFile("/sdcard/Any Reader/patna2.jpg");
        replay=(Button)findViewById(R.id.button1);
        play=(Button)findViewById(R.id.button2);
        add=(Button)findViewById(R.id.button3);
        imgClose = (ImageButton)findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(" Please Wait till I respond",TextToSpeech.QUEUE_FLUSH,null);
                HashMap<String, String> myHashRender = new HashMap<String, String>();
                String destinationFileName = "/sdcard/Any Reader/mypatnasong2.mp3";
                myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,scanImage());
                tts.synthesizeToFile(scanImage(), myHashRender, destinationFileName);
            }
        });
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Result.this,"REplay",Toast.LENGTH_LONG).show();
                myplayer.stop();
                loadmusic();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tapcount%2!=0){
                    mycount++;
                    if(mycount==1){
                        loadmusic();
                    }else {
                        resumemusic();
                    }

                    play.setText("PAUSE");
                    play.setTextColor(Color.WHITE);
                    play.setTextSize(20);
                    Toast.makeText(Result.this,"count::"+tapcount,Toast.LENGTH_LONG).show();

                }
                else {
                    pausemusic();
                    play.setText("PLAY");
                    play.setTextColor(Color.WHITE);
                    play.setTextSize(20);
                    play.setTextColor(Color.rgb(255,255,255));
                    Toast.makeText(Result.this,"count::"+tapcount,Toast.LENGTH_LONG).show();
                }
                tapcount++;
            }


        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausemusic();
                searchgoogle();
            }
        });
        sv=(ScrollView)findViewById(R.id.scroll1);
        t1=(TextView)findViewById(R.id.text1);
        scanImage();
        t1.setText(recognizedText);

    }
    private String scanImage(){
        TessBaseAPI baseApi = new TessBaseAPI();
        path = "/sdcard/Finaltess";
        baseApi.setDebug(true);
        baseApi.init(path,"eng");
        baseApi.setImage(bt);
        baseApi.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST,
                ".,';:[]{}!@#$%^&*()_+=-?/0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        recognizedText = baseApi.getUTF8Text();
        baseApi.end();
        count++;
        if(count==1){
            tts.speak("Text is getting ready.Please wait for a moment",TextToSpeech.QUEUE_FLUSH,null);}
        else if(count>2){
            play.setText("PLAY");
            play.setTextColor(Color.WHITE);
            play.setTextSize(20);
            play.setTextColor(Color.rgb(255,255,255));
            replay.setText("REPLAY");
            replay.setTextColor(Color.WHITE);
            replay.setTextSize(20);
            add.setText("ASK");
            add.setTextColor(Color.WHITE);
            add.setTextSize(20);
            add.setTextColor(Color.rgb(255,255,255));
            Runnable task = new Runnable() {
                public void run() {
                    setready();
                }
            };
            worker.schedule(task,50, TimeUnit.SECONDS);
        }

        return recognizedText;}
      void setready(){


        tts.speak("Well,I see"+ Processing.pointcount()+" short points"+Processing.smallpara()+"small paragraphs and only"
                +Processing.largepara()+"big paragraphs to read"+"\n "+"Double click on play to start reading.",TextToSpeech.QUEUE_FLUSH,null);

      }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }
            else {

                Log.d(TAG,"step2");
                tts.speak("Click on the top right corner of the screen ",TextToSpeech.QUEUE_ADD,null);
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        pausemusic();
        searchgoogle();
        return true;
    }

    private void searchgoogle() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.google.android.googlequicksearchbox",
                "com.google.android.googlequicksearchbox.VoiceSearchActivity");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException anfe) {
            Log.d(TAG, "Google Voice Search is not found");
        }
    }


    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            File file = new File("/sdcard/Any Reader/mypatnasong2.mp3");
            Boolean delete=file.delete();

        }
        try{ if(myplayer.isPlaying()){
            myplayer.stop();
            myplayer.release();
            File file = new File("/sdcard/Any Reader/mypatnasong2.mp3");
            Boolean delete=file.delete();
        }}
        catch (Exception e){
            Log.d(TAG,"error on destroy method");
        }
        super.onDestroy();
    }
    void loadmusic() {
        String completePath1 = "/sdcard/mypatnasong2.mp3";
        File file = new File(completePath1);
        Uri myUri2 = Uri.fromFile(file);
        myplayer = new MediaPlayer();
        myplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            myplayer.setDataSource(getApplicationContext(), myUri2);
        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), "1.You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            myplayer.prepare();
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(),"2.You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        }
        catch (IOException e){
            Toast.makeText(getApplicationContext(), "3.You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        }
        myplayer.start();


    }
    void pausemusic(){
        try{  myplayer.pause();
            length=myplayer.getCurrentPosition();
        }catch (Exception e){
            Log.d(TAG,"no pause now!");}
    }
    void resumemusic() {
        myplayer.seekTo(length);
        myplayer.start();
    }
}
