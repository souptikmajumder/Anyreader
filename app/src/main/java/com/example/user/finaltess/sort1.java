package com.example.user.finaltess;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.opencv.core.Point;
import org.opencv.core.Rect;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;

/**
 * Created by R!K on 17-02-2017.
 */

public class sort1 {

    final static ArrayList <Integer>deletearrayx=new ArrayList<Integer>(50);
    final static ArrayList<Integer>deletearrayy=new ArrayList<Integer>(50);
    private static final String TAG =null ;
    static int count1s=0;
    static int count2s=0;
    static int count3s=0;

    public static int parasorty(int z){
        int temp1;
        int b;
        String[] fileArraya;
        File dira = new File("/sdcard/Any Reader/Param/");
        File[] filesa = dira.listFiles();
        fileArraya = new String[filesa.length];
        int intarrayh[] = new int[filesa.length];
        for (int i = 0; i < filesa.length; ++i) {

//        fileArraya[0] = filesa[0].getName().substring(1, filesa[0].getName().lastIndexOf("."));
//        String abc = filesa[0].getName().substring(filesa[0].getName().indexOf("b"), filesa[0].getName().lastIndexOf("."));
            fileArraya[i] = filesa[i].getName().substring((filesa[i].getName().indexOf("b"))+1, filesa[i].getName().lastIndexOf("-"));
            intarrayh[i] = Integer.parseInt(fileArraya[i]);
        }
        for (int j = 0; j < intarrayh.length; j++) {
            for (int k = 0; k < (intarrayh.length) - 1; k++)
                if (intarrayh[k] > intarrayh[k + 1]) {
                    temp1 = intarrayh[j];
                    intarrayh[j] = intarrayh[k];
                    intarrayh[k] = temp1;

                }

        }


        int h=intarrayh[z];



        return  h;}

    public static int[] paraback(){
    int temp1;
    int b;
    String[] fileArraya;
    File dira = new File("/sdcard/Any Reader/Param/");
    File[] filesa = dira.listFiles();
    fileArraya = new String[filesa.length];
    int intarrayh[] = new int[filesa.length];
    for (int i = 0; i < filesa.length; ++i) {

//        fileArraya[0] = filesa[0].getName().substring(1, filesa[0].getName().lastIndexOf("."));
//        String abc = filesa[0].getName().substring(filesa[0].getName().indexOf("b"), filesa[0].getName().lastIndexOf("."));
        fileArraya[i] = filesa[i].getName().substring(1, filesa[i].getName().lastIndexOf("-"));
        intarrayh[i] = Integer.parseInt(fileArraya[i]);
    }
    for (int j = 0; j < intarrayh.length; j++) {
        for (int k = 0; k < (intarrayh.length) - 1; k++)
            if (intarrayh[k] > intarrayh[k + 1]) {
                temp1 = intarrayh[j];
                intarrayh[j] = intarrayh[k];
                intarrayh[k] = temp1;

            }

    }

return intarrayh;
    }

    public static int parabackx(int z){
        int temp1;
        String[] fileArray1;
        File dir1 = new File("/sdcard/Any Reader/Param/");
        File[] files1 = dir1.listFiles();
        fileArray1 = new String[files1.length];
        int intarrayh[] = new int[files1.length];
        for (int i = 0; i < files1.length; ++i) {

            fileArray1[i] = files1[i].getName().substring(1, files1[i].getName().lastIndexOf("-"));
            intarrayh[i] = Integer.parseInt(fileArray1[i]);

        }

        for (int j = 0; j < intarrayh.length; j++) {
            for (int k = 0; k < (intarrayh.length) - 1; k++)
                if (intarrayh[k] > intarrayh[k + 1]) {
                    temp1 = intarrayh[j];
                    intarrayh[j] = intarrayh[k];
                    intarrayh[k] = temp1;

                }

        }
        return intarrayh[z];
    }

    public static int parabacky(int z){
        int temp1;
        String[] fileArray1;
        File dir1 = new File("/sdcard/Any Reader/Param/");
        File[] files1 = dir1.listFiles();
        fileArray1 = new String[files1.length];
        int intarrayh[] = new int[files1.length];
        for (int i = 0; i < files1.length; ++i) {

            fileArray1[i] = files1[i].getName().substring((files1[i].getName().indexOf("-"))+1, files1[i].getName().lastIndexOf(">"));
            intarrayh[i] = Integer.parseInt(fileArray1[i]);

        }

        for (int j = 0; j < intarrayh.length; j++) {
            for (int k = 0; k < (intarrayh.length) - 1; k++)
                if (intarrayh[k] > intarrayh[k + 1]) {
                    temp1 = intarrayh[j];
                    intarrayh[j] = intarrayh[k];
                    intarrayh[k] = temp1;

                }

        }
        return intarrayh[z];
    }


    public static int parabackw(int z){
        int temp1;
        String[] fileArray1;
        File dir1 = new File("/sdcard/Any Reader/Param/");
        File[] files1 = dir1.listFiles();
        fileArray1 = new String[files1.length];
        int intarrayh[] = new int[files1.length];
        for (int i = 0; i < files1.length; ++i) {

            fileArray1[i] = files1[i].getName().substring((files1[i].getName().indexOf(">"))+1, files1[i].getName().lastIndexOf("<"));
            intarrayh[i] = Integer.parseInt(fileArray1[i]);

        }

        for (int j = 0; j < intarrayh.length; j++) {
            for (int k = 0; k < (intarrayh.length) - 1; k++)
                if (intarrayh[k] > intarrayh[k + 1]) {
                    temp1 = intarrayh[j];
                    intarrayh[j] = intarrayh[k];
                    intarrayh[k] = temp1;

                }

        }
        return intarrayh[z];
    }

    public static int parabackh(int z){
        int temp1;
        String[] fileArray1;
        File dir1 = new File("/sdcard/Any Reader/Param/");
        File[] files1 = dir1.listFiles();
        fileArray1 = new String[files1.length];
        int intarrayh[] = new int[files1.length];
        for (int i = 0; i < files1.length; ++i) {

            fileArray1[i] = files1[i].getName().substring((files1[i].getName().indexOf("<"))+1, files1[i].getName().lastIndexOf("."));
            intarrayh[i] = Integer.parseInt(fileArray1[i]);

        }

        for (int j = 0; j < intarrayh.length; j++) {
            for (int k = 0; k < (intarrayh.length) - 1; k++)
                if (intarrayh[k] > intarrayh[k + 1]) {
                    temp1 = intarrayh[j];
                    intarrayh[j] = intarrayh[k];
                    intarrayh[k] = temp1;

                }

        }
        return intarrayh[z];
    }

//public static int[] headingbackx(){
//    int temp1;
//    String[] fileArray1;
//    File dir1 = new File("/sdcard/Any Reader/Headings/");
//    File[] files1 = dir1.listFiles();
//    fileArray1 = new String[files1.length];
//    int intarrayh[] = new int[files1.length];
//    for (int i = 0; i < files1.length; ++i) {
//
//        fileArray1[i] = files1[i].getName().substring(1, files1[i].getName().lastIndexOf("-"));
//        intarrayh[i] = Integer.parseInt(fileArray1[i]);
//
//    }
//
//    for (int j = 0; j < intarrayh.length; j++) {
//        for (int k = 0; k < (intarrayh.length) - 1; k++)
//            if (intarrayh[k] > intarrayh[k + 1]) {
//                temp1 = intarrayh[j];
//                intarrayh[j] = intarrayh[k];
//                intarrayh[k] = temp1;
//
//            }
//
//    }
//
//
//    return intarrayh;
//}

    public static int[] headingbacky(){
        int temp1;
        String[] fileArray1;
        File dir1 = new File("/sdcard/Any Reader/Headings/");
        File[] files1 = dir1.listFiles();
        fileArray1 = new String[files1.length];
        int intarrayh[] = new int[files1.length];
        for (int i = 0; i < files1.length; ++i) {

            fileArray1[i] = files1[i].getName().substring(1, files1[i].getName().lastIndexOf("."));
            intarrayh[i] = Integer.parseInt(fileArray1[i]);

        }

        for (int j = 0; j < intarrayh.length; j++) {
            for (int k = 0; k < (intarrayh.length) - 1; k++)
                if (intarrayh[k] > intarrayh[k + 1]) {
                    temp1 = intarrayh[j];
                    intarrayh[j] = intarrayh[k];
                    intarrayh[k] = temp1;

                }

        }
        return intarrayh;
    }

    public static int[] headingback(){
        int temp1;
        String[] fileArray1;
        File dir1 = new File("/sdcard/Any Reader/Headings/");
        File[] files1 = dir1.listFiles();
        fileArray1 = new String[files1.length];
        int intarrayh[] = new int[files1.length];
        for (int i = 0; i < files1.length; ++i) {

            fileArray1[i] = files1[i].getName().substring(1, files1[i].getName().lastIndexOf("-"));
            intarrayh[i] = Integer.parseInt(fileArray1[i]);

        }

        for (int j = 0; j < intarrayh.length; j++) {
            for (int k = 0; k < (intarrayh.length) - 1; k++)
                if (intarrayh[k] > intarrayh[k + 1]) {
                    temp1 = intarrayh[j];
                    intarrayh[j] = intarrayh[k];
                    intarrayh[k] = temp1;

                }

        }
        return intarrayh;
    }

//    public static int headingbacklength(){
//        int temp1;
//        String[] fileArray1;
//        File dir1 = new File("/sdcard/Any Reader/Headings/");
//        File[] files1 = dir1.listFiles();
//        fileArray1 = new String[files1.length];
//        int intarrayh[] = new int[files1.length];
//        for (int i = 0; i < files1.length; ++i) {
//
//            fileArray1[i] = files1[i].getName().substring(1, files1[i].getName().lastIndexOf("."));
//            intarrayh[i] = Integer.parseInt(fileArray1[i]);
//
//        }
//
//        for (int j = 0; j < intarrayh.length; j++) {
//            for (int k = 0; k < (intarrayh.length) - 1; k++)
//                if (intarrayh[k] > intarrayh[k + 1]) {
//                    temp1 = intarrayh[j];
//                    intarrayh[j] = intarrayh[k];
//                    intarrayh[k] = temp1;
//
//                }
//
//        }
//        int z=intarrayh.length;
//        return z;
//    }

//    public static void faulty(){
//
//        Rect r1=new Rect(sort1.parabackx(0), sort1.parabacky(0), sort1.parabackw(0), sort1.parabackh(0));
//        Rect r2=new Rect(sort1.parabackx(1), sort1.parabacky(1), sort1.parabackw(1), sort1.parabackh(1));
//
//        int mi= sort1.headingbackx().length;
//
//        Log.d(TAG,"length of this :"+mi);
//        for (int i=0;i<mi;i++)
//
//        {   Log.d(TAG,"loop runs:"+mi);
//            int a= sort1.headingbackx()[i];
//            int b= sort1.headingbacky()[i];
//            Point p= new Point (a,b);
//
//            Log.d(TAG,"loop a");
//            if (r1.contains(p))
//            {Log.d(TAG,"loop b");
//                deletearrayx.add(a);
//                deletearrayy.add(b);
//                File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Any Reader/Headings/h"+String.valueOf(a)+"-"+String.valueOf(b)+".jpg");
////                 boolean f0=file.delete();
//                count1s++;
//                Log.d(TAG,"loop c");
//            }
////            else if (sort1.headingback()[i]>=paray1 && sort1.headingback()[i]<=heightadd1)
//            else if (r2.contains(p))
//            {
//                Log.d(TAG,"loop d");
//                deletearrayx.add(a);
//                deletearrayy.add(b);
//                File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Any Reader/Headings/h"+String.valueOf(a)+"-"+String.valueOf(b)+".jpg");
////                boolean f0=file.delete();
//                count2s++;
//                Log.d(TAG,"loop e");
//            }
//            else {
//                count3s++;
//                Log.d(TAG,"next loop");
//            }
//        }
//
//
//        Log.d(TAG,"Count bad a, bad b, good:"+count1s+" , "+count2s+" , "+count3s);
//
//
//
//    }

//    public static void faulty1(){
//
//        int mi= sort1.headingbacky().length;
//        Log.d(TAG,"length of this :"+mi);
//        for (int i=0;i<mi;i++)
//        {
//            Log.d(TAG,"loop runs:"+mi);
//            int za=parabacky(0)+parabackh(0);
//            int zb=parabacky(1)+parabackh(1);
//            int a=headingbacky()[i];
//            Log.d(TAG,"loop a");
//            if (a>=parabacky(0) && a<=za)
//                {   Log.d(TAG,"loop b");
//                    deletearrayx.add(a);
//                    count1s++;
//                    Log.d(TAG,"loop c");
//                }
//
//            else if (a>=parabacky(1) && a<=zb)
//                {
//                    Log.d(TAG,"loop d");
//                    deletearrayx.add(a);
//                    count2s++;
//                    Log.d(TAG,"loop e");
//                }
//            else
//                {
//                    count3s++;
//                    Log.d(TAG,"next loop");
//                }
//        }
//        Log.d(TAG,"Count bad a, bad b, good:"+count1s+" , "+count2s+" , "+count3s);
//    }

    public static void faulty2(){

        int mi= sort1.headingbacky().length;
        Log.d(TAG,"length of this :"+mi);
        for (int i=0;i<mi;i++) {
            for (int j = 0; j < paraback().length; j++)
            {
                Log.d(TAG, "loop runs:" + mi);
                int za = parabacky(j) + parabackh(j);
                int a = headingbacky()[i];
                Log.d(TAG, "loop a");
                if (a >= parabacky(j) && a <= za) {
                    Log.d(TAG, "loop b");
                    deletearrayx.add(a);
                    count1s++;
                    Log.d(TAG, "loop c");
                } else {
                    count3s++;
                    Log.d(TAG, "next loop");
                }
            }
        }
        Log.d(TAG,"Count bad a, bad b, good:"+count1s+" , "+count2s+" , "+count3s);
    }

    public static void faultydelete(){



        for(int x=0;x<deletearrayx.size();x++) {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Any Reader/Headings/h"+String.valueOf(deletearrayx.get(x))+".jpg");
            boolean f0=file.delete();
        }
    }
    public static int checkarrayx(){
        return deletearrayx.size();
    }
    public static int checkarrayY(){
        return deletearrayy.size();
    }

}
