package com.example.user.finaltess;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, android.hardware.Camera.PictureCallback {
    private static final String TAG =null ;
    TextToSpeech tts;
    private android.hardware.Camera mCamera = null;
    private Cameraview mCameraView = null;
    FrameLayout camera_view;
    ImageButton imgClose;
    Context context;
    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();
    Bitmap bp1;
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status)
        {
            switch(status){
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG,"oPENcv LOADED SUCCESSFULLY");
                    }
                break;
                default:
                {super.onManagerConnected(status);}
                break;
            }
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this,
                mLoaderCallback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts=new TextToSpeech(this,this);
        try{

            mCamera = android.hardware.Camera.open();


            //you can use open(int) to use different cameras

        } catch (Exception e){
            Log.d("ERROR", "Failed to get camera: " + e.getMessage());
        }

        if(mCamera != null) {
            mCameraView = new Cameraview(this, mCamera);//create a SurfaceView to show camera data
            camera_view = (FrameLayout)findViewById(R.id.camera_view1);
            camera_view.addView(mCameraView);//add the SurfaceView to the layout
        }

        //btn to close the application
         imgClose = (ImageButton)findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCamera.autoFocus(myAutoFocusCallback);
                File file= new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Any Reader/patna2.jpg");
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                bp1 = BitmapFactory.decodeFile( "/sdcard/Any Reader/patna2.jpg", options);
                if (file.exists()){
                    Toast.makeText(MainActivity.this,"Image captured",Toast.LENGTH_SHORT).show();
                    camera_view.removeAllViews();
                    camera_view.addView(mCameraView);
//                    if(skewdenoise.giveangle(bp1)>5.0){
//                            boolean d0=file.delete();
//
//                            camera_view.removeAllViews();
//                             camera_view.addView(mCameraView);
//                             tts.speak("slightly rotate clockwise",TextToSpeech.QUEUE_FLUSH,null);
//                         Toast.makeText(MainActivity.this,"rotate clockwise",Toast.LENGTH_LONG).show();
//
//                }
//                    else if(skewdenoise.giveangle(bp1)<-5.0){
//                         camera_view.removeAllViews();
//                        boolean d0=file.delete();
//                        camera_view.addView(mCameraView);
//                         tts.speak("Slightly rotate anti-clockwise",TextToSpeech.QUEUE_FLUSH,null);
//                         Toast.makeText(MainActivity.this,"Rotate anticlockwise",Toast.LENGTH_LONG).show();
//
//                     }
//                else  {
//                        tts.speak("Ok,Text is Ready", TextToSpeech.QUEUE_FLUSH, null);
//                                Intent i = new Intent(MainActivity.this,Zoominout.class);
//                                startActivity(i);


                                if(Processing.distance(bp1)==1){
                                boolean deleted = file.delete();
                                camera_view.removeAllViews();
                                camera_view.addView(mCameraView);
                                tts.speak("Camera open Zoom out", TextToSpeech.QUEUE_FLUSH, null);
                                    Toast.makeText(MainActivity.this, "zoom out", Toast.LENGTH_LONG).show();
                                }
                            if(Processing.distance(bp1)==2){
                                camera_view.removeAllViews();
                                boolean deleted1 = file.delete();
                                camera_view.addView(mCameraView);
                                tts.speak("Retake,zoom in", TextToSpeech.QUEUE_FLUSH, null);
                                Toast.makeText(MainActivity.this, "zoom in", Toast.LENGTH_LONG).show();
                                }
                        if(Processing.distance(bp1)==3){
                                Toast.makeText(MainActivity.this, "Its ok!", Toast.LENGTH_SHORT).show();
                                tts.speak("Ok,Text is Ready", TextToSpeech.QUEUE_FLUSH, null);
                            Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(MainActivity.this,Zoominout.class);
                                startActivity(i);


                    }}}});}



    public void  savefile(Intent i, String filename) {

        File imagesFolder = new File(Environment.getExternalStorageDirectory(), filename);
        imagesFolder.mkdirs();
        File image = new File(imagesFolder, "image.jpg");
        Uri uriSavedImage = Uri.fromFile(image);
        i.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        Toast.makeText(this, "image saved", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                tts.speak("Camera is open.Take picture!",TextToSpeech.QUEUE_FLUSH,null);
                // speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }
     android.hardware.Camera.PictureCallback mPicture=new android.hardware.Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
            File pictureFile = new File("/sdcard/Any Reader/patna2.jpg");
            if (pictureFile == null) {
                Log.d(TAG, "error saving file");

            }
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();

            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not there");
            } catch (IOException e) {
                Log.d(TAG, "data not written ");
            }
        }
    };

    @Override
    public void onPictureTaken(byte[] data, android.hardware.Camera camera) {

    }
    android.hardware.Camera.AutoFocusCallback myAutoFocusCallback = new android.hardware.Camera.AutoFocusCallback() {

        @Override
        public void onAutoFocus(boolean success, android.hardware.Camera camera) {
            mCamera.takePicture(null,null,mPicture);
        }


    };}
