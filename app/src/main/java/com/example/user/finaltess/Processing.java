package com.example.user.finaltess;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static android.support.v4.app.ActivityCompat.startActivity;
import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by User on 25-12-2016.
 */
public  class  Processing  {
    public static int distance(Bitmap bp) {
    Rect rect;
    int count=0,set=0;
        Mat mymat= new Mat();
        Utils.bitmapToMat(bp,mymat);
        Mat rgb= new Mat();
        Mat crop=rgb.clone();
        Imgproc.pyrDown(mymat,rgb);
        Mat small= new Mat();
        Imgproc.cvtColor(rgb,small,Imgproc.COLOR_RGB2GRAY);
        Mat grad= new Mat();
        Mat morphkernel=Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE,new Size(3,3));
        Imgproc.morphologyEx(small,grad,Imgproc.MORPH_GRADIENT,morphkernel);
        Mat bw=new Mat();
        Imgproc.threshold(grad,bw,0.0,255.0,Imgproc.THRESH_BINARY|Imgproc.THRESH_OTSU);
        Mat connected= new Mat();
        morphkernel=Imgproc.getStructuringElement(Imgproc.MORPH_RECT,new Size(30,12));
        Imgproc.morphologyEx(bw,connected,Imgproc.MORPH_CLOSE,morphkernel);
        Mat mask=Mat.zeros(bw.size(), CvType.CV_8UC1);
        List <MatOfPoint> contours= new ArrayList<MatOfPoint>();
        Mat hierarchy= new Mat();
        Imgproc.findContours(connected,contours,hierarchy,Imgproc.RETR_CCOMP,Imgproc.CHAIN_APPROX_SIMPLE,new Point(0,0));
        for (int idx=0;idx<contours.size();idx++){
            rect = Imgproc.boundingRect(contours.get(idx));
            Mat maskROI= new Mat(mask,rect);
            maskROI.setTo(new Scalar(0,0,0));
            Imgproc.drawContours(mask,contours,idx,new Scalar(255,255,255), Core.FILLED);
            double r=(double)Core.countNonZero(maskROI)/(rect.width*rect.height);
            if( (rect.height>10&& rect.width>10)){

                Imgproc.rectangle(rgb,new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(0,255,0));
                count++;


//            if(rect.width>(rgb.width()-50)){
//
//                set=1;
//
//            }
             if(rect.width==rgb.width()){

                set=1;

            }
            else if(rect.width<=(rgb.width()-10) && rect.width>(rgb.width()-100)){

                set=3;

            }
            else if(count==0){

                set=2;
            }

             else if(rect.width>(rgb.width()-30)){

                 set=1;
             }

//             else if(rect.width<(rgb.width()-100)){
//
//                 set=2;
//             }
//               else if(rect.width>=(rgb.width()-30) && rect.height>=(rgb.height()-30)){
//                 set = 2;
//             }



        }

    }
    return set;}

    public  static int pointcount() {
        int pycount1=0;int pycount2=0;int pycount3=0;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        final Bitmap bp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/patna2.jpg", options);
        Rect rect;
        Mat mymat = new Mat();
        Utils.bitmapToMat(bp, mymat);
        Mat rgb = new Mat();
        Imgproc.pyrDown(mymat, rgb);
        Mat small = new Mat();
        Imgproc.cvtColor(rgb, small, Imgproc.COLOR_RGB2GRAY);
        Mat grad = new Mat();
        Mat morphkernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(3, 3));
        Imgproc.morphologyEx(small, grad, Imgproc.MORPH_GRADIENT, morphkernel);
        Mat bw = new Mat();
        Imgproc.threshold(grad, bw, 0.0, 255.0, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        Mat connected = new Mat();
        morphkernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(9, 1));
        Imgproc.morphologyEx(bw, connected, Imgproc.MORPH_CLOSE, morphkernel);
        Mat mask = Mat.zeros(bw.size(), CvType.CV_8UC1);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(connected, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
        for (int idx = 0; idx < contours.size(); idx++) {
            rect = Imgproc.boundingRect(contours.get(idx));
            Mat maskROI = new Mat(mask, rect);
            maskROI.setTo(new Scalar(0, 0, 0));
            Imgproc.drawContours(mask, contours, idx, new Scalar(255, 255, 255), Core.FILLED);
            double r = (double) Core.countNonZero(maskROI) / (rect.width * rect.height);
            if ((rect.height > 4 && rect.width > 4)) {
                Imgproc.rectangle(rgb, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));

                if(rect.height<=10){
                    pycount1++;

                }
                 if(rect.height<=40&&rect.height>10){
                    pycount2++;
                }
                if(rect.height>=50){
                    pycount3++;
                }
            }

        }


        return pycount1;}
    public static int smallpara(){
        int pycount1=0;int pycount2=0;int pycount3=0;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        final Bitmap bp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/patna2.jpg", options);
        Rect rect;
        Mat mymat = new Mat();
        Utils.bitmapToMat(bp, mymat);
        Mat rgb = new Mat();
        Imgproc.pyrDown(mymat, rgb);
        Mat small = new Mat();
        Imgproc.cvtColor(rgb, small, Imgproc.COLOR_RGB2GRAY);
        Mat grad = new Mat();
        Mat morphkernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(3, 3));
        Imgproc.morphologyEx(small, grad, Imgproc.MORPH_GRADIENT, morphkernel);
        Mat bw = new Mat();
        Imgproc.threshold(grad, bw, 0.0, 255.0, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        Mat connected = new Mat();
        morphkernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(9, 1));
        Imgproc.morphologyEx(bw, connected, Imgproc.MORPH_CLOSE, morphkernel);
        Mat mask = Mat.zeros(bw.size(), CvType.CV_8UC1);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(connected, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
        for (int idx = 0; idx < contours.size(); idx++) {
            rect = Imgproc.boundingRect(contours.get(idx));
            Mat maskROI = new Mat(mask, rect);
            maskROI.setTo(new Scalar(0, 0, 0));
            Imgproc.drawContours(mask, contours, idx, new Scalar(255, 255, 255), Core.FILLED);
            double r = (double) Core.countNonZero(maskROI) / (rect.width * rect.height);
            if ((rect.height > 4 && rect.width > 4)) {
                Imgproc.rectangle(rgb, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));


                if(rect.height<=40&&rect.height>10){
                    pycount2++;
                }

            }

        }


return pycount2; }
    public static int largepara(){
        int pycount1=0;int pycount2=0;int pycount3=0;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        final Bitmap bp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/patna2.jpg", options);
        Rect rect;
        Mat mymat = new Mat();
        Utils.bitmapToMat(bp, mymat);
        Mat rgb = new Mat();
        Imgproc.pyrDown(mymat, rgb);
        Mat small = new Mat();
        Imgproc.cvtColor(rgb, small, Imgproc.COLOR_RGB2GRAY);
        Mat grad = new Mat();
        Mat morphkernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(3, 3));
        Imgproc.morphologyEx(small, grad, Imgproc.MORPH_GRADIENT, morphkernel);
        Mat bw = new Mat();
        Imgproc.threshold(grad, bw, 0.0, 255.0, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        Mat connected = new Mat();
        morphkernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(9, 1));
        Imgproc.morphologyEx(bw, connected, Imgproc.MORPH_CLOSE, morphkernel);
        Mat mask = Mat.zeros(bw.size(), CvType.CV_8UC1);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(connected, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
        for (int idx = 0; idx < contours.size(); idx++) {
            rect = Imgproc.boundingRect(contours.get(idx));
            Mat maskROI = new Mat(mask, rect);
            maskROI.setTo(new Scalar(0, 0, 0));
            Imgproc.drawContours(mask, contours, idx, new Scalar(255, 255, 255), Core.FILLED);
            double r = (double) Core.countNonZero(maskROI) / (rect.width * rect.height);
            if ((rect.height > 4 && rect.width > 4)) {
                Imgproc.rectangle(rgb, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));


                if(rect.height>=50){
                    pycount3++;
                }
            }

        }

return pycount3;    }


}
    



