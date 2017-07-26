package com.example.user.finaltess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.path;
import static android.R.id.input;
import static org.opencv.imgproc.Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C;
import static org.opencv.imgproc.Imgproc.ADAPTIVE_THRESH_MEAN_C;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY_INV;
import static org.opencv.imgproc.Imgproc.THRESH_OTSU;
import static org.opencv.imgproc.Imgproc.adaptiveThreshold;

public class Main3Activity extends AppCompatActivity {
    Bitmap bp;
    Bitmap bmpOut1;
    String recognizedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ImageView c1=(ImageView)findViewById(R.id.abc1);
        TextView t1=(TextView)findViewById(R.id.trytext1);
        ScrollView s1=(ScrollView)findViewById(R.id.scroll1);
        Mat myimg=new Mat();
        Mat format=new Mat();
        Mat format1=new Mat();
        Size s;
        bp= BitmapFactory.decodeFile("/sdcard/Any Reader/patna2.jpg");
        Utils.bitmapToMat(bp,myimg);
        Utils.bitmapToMat(bp,format);
        s=new Size(myimg.width()*0.34,myimg.height()*0.34);
        Imgproc.resize(myimg,format,s);
        Imgproc.cvtColor(format,format,Imgproc.COLOR_RGB2GRAY);
        Imgproc.medianBlur(format,format,5);
        adaptiveThreshold(format,format,255, ADAPTIVE_THRESH_GAUSSIAN_C, THRESH_BINARY,11,2);
        final Bitmap bmpOut = Bitmap.createBitmap(format.cols(),format.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(format,bmpOut);
        Size size = format.size();
        Core.bitwise_not(format, format);
        Mat lines = new Mat();
        Imgproc.HoughLinesP(format, lines, 1, Math.PI / 180, 100, size.width /3.f, 20);
        double angle = 0.;
        for(int i = 0; i<lines.height(); i++){
            for(int j = 0; j<lines.width();j++){
                angle += Math.atan2(lines.get(i, j)[3] - lines.get(i, j)[1], lines.get(i, j)[2] - lines.get(i, j)[0]);
            }
        }
        angle /= lines.size().area();
        angle = angle * 180 / Math.PI;
        Toast.makeText(Main3Activity.this,"angle is "+ angle,Toast.LENGTH_LONG).show();
        Point center = new Point(format.width()/2, format.height()/2);
        Mat rotImage = Imgproc.getRotationMatrix2D(center, angle, 1.0);
        Size size1 = new Size(format.width(), format.height());
        Imgproc.warpAffine(format, format1, rotImage, size1, Imgproc.INTER_LINEAR + Imgproc.CV_WARP_FILL_OUTLIERS);
        Imgproc.threshold(format1,format1,0,255,THRESH_BINARY_INV);
        Imgproc.medianBlur(format1,format1,5);
        adaptiveThreshold(format1,format1,255, ADAPTIVE_THRESH_GAUSSIAN_C, THRESH_BINARY,11,2);
        bmpOut1 = Bitmap.createBitmap(format1.cols(),format1.rows(), Bitmap.Config.ARGB_8888);

        Utils.matToBitmap(format1,bmpOut1);

        c1.setImageBitmap(bmpOut1);

        //scanImage();
        //t1.setText(recognizedText);
//        c3.setImageBitmap(bmpOut1);


    }

    private String scanImage() {
        TessBaseAPI baseApi = new TessBaseAPI();
        String path = "/sdcard/Finaltess";
        baseApi.setDebug(true);
        baseApi.init(path,"eng");
        baseApi.setImage(bmpOut1);
        baseApi.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST,
                ".,';:[]{}!@#$%^&*()_+=-?/0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        recognizedText = baseApi.getUTF8Text();
        baseApi.end();

    return  recognizedText;}


}

