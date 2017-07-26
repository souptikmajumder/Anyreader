package com.example.user.finaltess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by User on 07-02-2017.
 */

public class scanpage {
    private static final String TAG ="msg::" ;
    public static ArrayList<Bitmap> largepara(Bitmap bp) {
        int c1, c2, c3, c4 = 0;
        Rect rect = null;
        int set1 = 0;
        Mat mymat = new Mat();
        Utils.bitmapToMat(skewdenoise.cleanbip(bp), mymat);
        Mat rgb = new Mat();
        Mat crop = rgb.clone();
        Imgproc.pyrDown(mymat, rgb);
        Mat small = new Mat();
        Imgproc.cvtColor(rgb, small, Imgproc.COLOR_RGB2GRAY);
        Mat grad = new Mat();
        Mat morphkernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(7, 7));
        Imgproc.morphologyEx(small, grad, Imgproc.MORPH_GRADIENT, morphkernel);
        Mat bw = new Mat();
        Imgproc.threshold(grad, bw, 0.0, 255.0, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        Mat connected = new Mat();
        morphkernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(30, 10));
        Imgproc.morphologyEx(bw, connected, Imgproc.MORPH_CLOSE, morphkernel);
        Mat mask = Mat.zeros(bw.size(), CvType.CV_8UC1);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(connected, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
        Bitmap bmpOut2 = null;
        ArrayList<Bitmap> paraarray = null;
        for (int idx = 0; idx < contours.size(); idx++) {
            rect = Imgproc.boundingRect(contours.get(idx));
            Mat maskROI = new Mat(mask, rect);
            maskROI.setTo(new Scalar(0, 0, 0));
            Imgproc.drawContours(mask, contours, idx, new Scalar(255, 255, 255), Core.FILLED);
            double r = (double) Core.countNonZero(maskROI) / (rect.width * rect.height);
            if ((rect.height >= 80 && rect.width > 150)) {
                Imgproc.rectangle(rgb, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 1);
                c1 = rect.x;
                c2 = rect.y;
                c3 = rect.width;
                c4 = rect.height;
                paraarray = new ArrayList<Bitmap>();
                Bitmap bmpOut0 = Bitmap.createBitmap(rgb.cols(), rgb.rows(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(rgb, bmpOut0);
                bmpOut2 = Bitmap.createBitmap(bmpOut0, c1, c2, c3, c4);
                paraarray.add(bmpOut2);
                File pictureFile = new File("/sdcard/Any Reader/Big para/img" + rect.y + ".jpg");
                if (pictureFile == null) {

                }

                Bitmap image = paraarray.get(set1);
                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    //fos.write(data);
                    image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.close();

                } catch (FileNotFoundException e) {

                } catch (IOException e) {


                }

            }
        }


        return paraarray;
    }


    public static void smallpara(Bitmap bp){
        int c1, c2, c3, c4 = 0;
        Rect rect = null;
        int set1 = 0;
        Bitmap bmpOut0;
        Mat mymat = new Mat();
        Utils.bitmapToMat(skewdenoise.cleanbip(bp), mymat);
        Mat rgb = new Mat();
        Mat crop = rgb.clone();
        Imgproc.pyrDown(mymat, rgb);
        Mat small = new Mat();
        Imgproc.cvtColor(rgb, small, Imgproc.COLOR_RGB2GRAY);
        Mat grad = new Mat();
        Mat morphkernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(7, 7));
        Imgproc.morphologyEx(small, grad, Imgproc.MORPH_GRADIENT, morphkernel);
        Mat bw = new Mat();
        Imgproc.threshold(grad, bw, 0.0, 255.0, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        Mat connected = new Mat();
        morphkernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(30, 10));
        Imgproc.morphologyEx(bw, connected, Imgproc.MORPH_CLOSE, morphkernel);
        Mat mask = Mat.zeros(bw.size(), CvType.CV_8UC1);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(connected, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
        Bitmap bmpOut2 = null;
        for (int idx = 0; idx < contours.size(); idx++) {
            rect = Imgproc.boundingRect(contours.get(idx));
            Mat maskROI = new Mat(mask, rect);
            maskROI.setTo(new Scalar(0, 0, 0));
            Imgproc.drawContours(mask, contours, idx, new Scalar(255, 255, 255), Core.FILLED);
            double r = (double) Core.countNonZero(maskROI) / (rect.width * rect.height);

             if((rect.height<80&&rect.height>=50&& rect.width>200)){
            Imgproc.rectangle(rgb,new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(0,0,255),1);
        c1 = rect.x;
        c2 = rect.y;
        c3 = rect.width;
        c4 = rect.height;

        ArrayList<Bitmap> paraarray = new ArrayList<Bitmap>();
        bmpOut0 = Bitmap.createBitmap(rgb.cols(), rgb.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(rgb, bmpOut0);
         bmpOut2 = Bitmap.createBitmap(bmpOut0, c1, c2, c3, c4);
        paraarray.add(bmpOut2);

        File pictureFile = new File("/sdcard/Any Reader/Big para/img" + rect.y + ".jpg");
        if (pictureFile == null) {
            Log.d(TAG,"pic file not found,scanpage");

        }

        Bitmap image = paraarray.get(set1);
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            //fos.write(data);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();

        } catch (FileNotFoundException e) {
            Log.d(TAG,"pic file not found,scanpage");
        } catch (IOException e) {
            Log.d(TAG,"IO Exception,scanpage");

        }



    }}}


    public static void points(Bitmap bp){
        int a1, a2, a3, a4 = 0;
        Rect rect = null;
        int set1 = 0;
        Bitmap bmpOut0;
        Mat mymat = new Mat();
        Utils.bitmapToMat(skewdenoise.cleanbip(bp), mymat);
        Mat rgb = new Mat();
        Mat crop = rgb.clone();
        Imgproc.pyrDown(mymat, rgb);
        Mat small = new Mat();
        Imgproc.cvtColor(rgb, small, Imgproc.COLOR_RGB2GRAY);
        Mat grad = new Mat();
        Mat morphkernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(7, 7));
        Imgproc.morphologyEx(small, grad, Imgproc.MORPH_GRADIENT, morphkernel);
        Mat bw = new Mat();
        Imgproc.threshold(grad, bw, 0.0, 255.0, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        Mat connected = new Mat();
        morphkernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(30, 10));
        Imgproc.morphologyEx(bw, connected, Imgproc.MORPH_CLOSE, morphkernel);
        Mat mask = Mat.zeros(bw.size(), CvType.CV_8UC1);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(connected, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
        Bitmap bmpOut2 = null;
        for (int idx = 0; idx < contours.size(); idx++) {
            rect = Imgproc.boundingRect(contours.get(idx));
            Mat maskROI = new Mat(mask, rect);
            maskROI.setTo(new Scalar(0, 0, 0));
            Imgproc.drawContours(mask, contours, idx, new Scalar(255, 255, 255), Core.FILLED);
            double r = (double) Core.countNonZero(maskROI) / (rect.width * rect.height);

            if((rect.height < 50 && rect.width > 200)){
                Imgproc.rectangle(rgb,new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(0,0,255),1);
                a1 = rect.x;
                a2 = rect.y;
                a3 = rect.width;
                a4 = rect.height;
                ArrayList<Bitmap> paraarray = new ArrayList<Bitmap>();
                bmpOut0 = Bitmap.createBitmap(rgb.cols(), rgb.rows(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(rgb, bmpOut0);
                bmpOut2 = Bitmap.createBitmap(bmpOut0, a1, a2, a3, a4);
                paraarray.add(bmpOut2);

                File pictureFile = new File("/sdcard/Any Reader/Big para/img" + rect.y + ".jpg");
                if (pictureFile == null) {
                    Log.d(TAG,"pic file not found,scanpage");

                }

                Bitmap image = paraarray.get(set1);
                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    //fos.write(data);
                    image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.close();

                } catch (FileNotFoundException e) {
                    Log.d(TAG,"pic file not found,scanpage");
                } catch (IOException e) {
                    Log.d(TAG,"IO Exception,scanpage");

                }



            }}

    }


}
