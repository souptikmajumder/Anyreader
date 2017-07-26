package com.example.user.finaltess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class distance extends AppCompatActivity implements TextToSpeech.OnInitListener {
    ImageView v;
    TextToSpeech tts;
    Rect rect,rect1=null;
    float knownwidth= (float) 8.26,focalength=0,perwidth=0,knowndistance=(float) 24.0;
    float pixelwidth=(float)111;
    private static final String TAG = "Activity5::";
    List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this,
                mLoaderCallback);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.distance);
        v=(ImageView)findViewById(R.id.imageView5);
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        final Bitmap bp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/soup.jpg", options);
        final Bitmap bp1 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/soup.jpg", options);
        Mat mymat= new Mat();
        Mat mymat2= new Mat();
        Utils.bitmapToMat(bp,mymat);
        Utils.bitmapToMat(bp1,mymat2);
        Imgproc.cvtColor(mymat,mymat, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(mymat,mymat,new Size(5,5),0);
        Mat canny= new Mat();
        Imgproc.Canny(mymat,canny,35,125);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(canny, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
        double maxval1=0;
        int maxval2=0;
        MatOfPoint2f points= new MatOfPoint2f();
        for (int contouridx=0;contouridx<contours.size();contouridx++){
            double contourarea=Imgproc.contourArea(contours.get(contouridx));
            if(maxval1<contourarea){
                maxval1=contourarea;
                maxval2=contouridx;
                MatOfPoint2f contour2f = new MatOfPoint2f(contours.get(maxval2).toArray());
                MatOfPoint mypoints= new MatOfPoint(contour2f.toArray());
                rect=Imgproc.boundingRect(mypoints);
            }

        }
        Imgproc.rectangle(mymat2, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(255,255,0), 2);

        Imgproc.drawContours(mymat2,contours,maxval2,new Scalar(0,255,0),2);




        //knowndistance= (float) 24.0,knownwidth= (float) 8.26,pixelwidth= (float) 177.0;
        float focalpoint=  ((pixelwidth*knowndistance)/knownwidth);
        final Bitmap bmpOut0 = Bitmap.createBitmap(mymat2.cols(),mymat2.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mymat2,bmpOut0);
        v.setImageBitmap(bmpOut0);
        float z=distance(knownwidth,focalpoint,rect.height);
        Toast.makeText(distance.this,"distance ::"+z+" "+"foot",Toast.LENGTH_LONG).show();


    }
   protected float  distance(float knownwidth,float focalength,int perwidth ){
        return (float) (((knownwidth*focalength)/perwidth)*0.0833333);

    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);
            tts.setSpeechRate((float)0.8);


            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not till supported");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }
}
