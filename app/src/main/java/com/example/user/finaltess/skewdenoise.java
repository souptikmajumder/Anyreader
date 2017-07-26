package com.example.user.finaltess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgproc.Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY_INV;
import static org.opencv.imgproc.Imgproc.adaptiveThreshold;

/**
 * Created by User on 04-02-2017.
 */

public class skewdenoise {

      static Bitmap bmpOut1;

    public static double  giveangle(Bitmap bp1){
        Mat myimg=new Mat();
        Mat format=new Mat();
        Mat format1=new Mat();
        Size s;
        Utils.bitmapToMat(bp1,myimg);
        Utils.bitmapToMat(bp1,format);
        s=new Size(myimg.width()*0.34,myimg.height()*0.34);
        Imgproc.resize(myimg,format,s);
        Imgproc.cvtColor(format,format,Imgproc.COLOR_RGB2GRAY);
        Imgproc.medianBlur(format,format,3);
        adaptiveThreshold(format,format,255, ADAPTIVE_THRESH_GAUSSIAN_C, THRESH_BINARY,11,2);
        final Bitmap bmpOut = Bitmap.createBitmap(format.cols(),format.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(format,bmpOut);
        Size size = format.size();
        Core.bitwise_not(format, format);
        Mat lines = new Mat();
        Imgproc.HoughLinesP(format, lines, 1, Math.PI / 180, 100, size.width / 2.f, 20);
        double angle = 0.;
        for(int i = 0; i<lines.height(); i++){
            for(int j = 0; j<lines.width();j++){
                angle += Math.atan2(lines.get(i, j)[3] - lines.get(i, j)[1], lines.get(i, j)[2] - lines.get(i, j)[0]);
            }
        }
        angle /= lines.size().area();
        angle = angle * 180 / Math.PI;
        Point center = new Point(format.width()/2, format.height()/2);
        Mat rotImage = Imgproc.getRotationMatrix2D(center, angle, 1.0);
        Size size1 = new Size(format.width(), format.height());
        Imgproc.warpAffine(format, format1, rotImage, size1, Imgproc.INTER_LINEAR + Imgproc.CV_WARP_FILL_OUTLIERS);
        Imgproc.threshold(format1,format1,0,255,THRESH_BINARY_INV);
        Imgproc.medianBlur(format1,format1,5);
        adaptiveThreshold(format1,format1,255, ADAPTIVE_THRESH_GAUSSIAN_C, THRESH_BINARY,11,2);
        final Bitmap bmpOut1 = Bitmap.createBitmap(format1.cols(),format1.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(format1,bmpOut1);
        return  angle;

    }

    public static Bitmap cleanbip(Bitmap bp){
        Mat myimg=new Mat();
        Mat format=new Mat();
        Mat format1=new Mat();
        Size s;
        Utils.bitmapToMat(bp,myimg);
        Utils.bitmapToMat(bp,format);
//        s=new Size(myimg.width()*1.0,myimg.height()*1.0);
        Imgproc.cvtColor(format,format,Imgproc.COLOR_RGB2GRAY);
        Imgproc.medianBlur(format,format,3);
        adaptiveThreshold(format,format,255, ADAPTIVE_THRESH_GAUSSIAN_C, THRESH_BINARY,11,2);
        final Bitmap bmpOut = Bitmap.createBitmap(format.cols(),format.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(format,bmpOut);
        Size size = format.size();
        Core.bitwise_not(format, format);
        Mat lines = new Mat();
        Imgproc.HoughLinesP(format, lines, 1, Math.PI / 180, 100, size.width / 2.f, 20);
        double angle = 0.;
        for(int i = 0; i<lines.height(); i++){
            for(int j = 0; j<lines.width();j++){
                angle += Math.atan2(lines.get(i, j)[3] - lines.get(i, j)[1], lines.get(i, j)[2] - lines.get(i, j)[0]);
            }
        }
        angle /= lines.size().area();
        angle = angle * 180 / Math.PI;
        Point center = new Point(format.width()/2, format.height()/2);
        Mat rotImage = Imgproc.getRotationMatrix2D(center, angle, 1.0);
        Size size1 = new Size(format.width(), format.height());
        Imgproc.warpAffine(format, format1, rotImage, size1, Imgproc.INTER_LINEAR + Imgproc.CV_WARP_FILL_OUTLIERS);
        Imgproc.threshold(format1,format1,0,255,THRESH_BINARY_INV);
        Imgproc.medianBlur(format1,format1,5);
        adaptiveThreshold(format1,format1,255, ADAPTIVE_THRESH_GAUSSIAN_C, THRESH_BINARY,11,2);
        final Bitmap bmpOut1 = Bitmap.createBitmap(format1.cols(),format1.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(format1,bmpOut1);
        return  bmpOut1;

    }











}
