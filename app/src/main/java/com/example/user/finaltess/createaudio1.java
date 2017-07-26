package com.example.user.finaltess;

/**
 * Created by R!K on 18-02-2017.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.Log;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

import static android.R.attr.delay;
import static java.util.stream.IntStream.of;

public class createaudio1 {

    private static final String TAG = "create audio";


    public static StringBuilder sortgeneral() {
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
        int temp2;
        String[] fileArray2;
        File dir2 = new File("/sdcard/Any Reader/Points/");
        File[] files2 = dir2.listFiles();
        fileArray2 = new String[files2.length];
        int intarrayp[] = new int[files2.length];
        for (int i = 0; i < files2.length; ++i) {

            fileArray2[i] = files2[i].getName().substring(1, files2[i].getName().lastIndexOf("."));
            intarrayp[i] = Integer.parseInt(fileArray2[i]);

        }

        for (int j = 0; j < intarrayp.length; j++) {
            for (int k = 0; k < (intarrayp.length) - 1; k++)
                if (intarrayp[k] > intarrayp[k + 1]) {
                    temp2 = intarrayp[j];
                    intarrayp[j] = intarrayp[k];
                    intarrayp[k] = temp2;

                }

        }
        Log.d(TAG,"arrayp :"+intarrayp.toString());
        int temp3;
        String[] fileArray3;
//        String recognized = null;
        File dir3 = new File("/sdcard/Any Reader/Para/");
        File[] files3 = dir3.listFiles();
        fileArray3 = new String[files3.length];
        int intarrayb[] = new int[files3.length];
        for (int i = 0; i < files3.length; ++i) {

            fileArray3[i] = files3[i].getName().substring(1, files3[i].getName().lastIndexOf("."));
            intarrayb[i] = Integer.parseInt(fileArray3[i]);

        }

        for (int j = 0; j < intarrayb.length; j++) {
            for (int k = 0; k < (intarrayb.length) - 1; k++)
                if (intarrayb[k] > intarrayb[k + 1]) {
                    temp3 = intarrayb[j];
                    intarrayb[j] = intarrayb[k];
                    intarrayb[k] = temp3;

                }
        }
//        int[] ff={0,75,316,538,713};
        for(int a=0;a<intarrayb.length;a++) {
            if (Arrays.asList(intarrayb).contains(80)) {
                Log.d(TAG, "Contains 80");
            } else if (Arrays.asList(intarrayb).contains(75)) {
                Log.d(TAG, "Contains 75");
            } else if (Arrays.asList(intarrayb).contains(0)) {
                Log.d(TAG, "Contains 0");
            }
        }
        int[] ff=new int[intarrayb.length+intarrayh.length];
        System.arraycopy(intarrayb,0,ff,0,intarrayb.length);
        System.arraycopy(intarrayh,0,ff,intarrayb.length,intarrayh.length);


        int[] ff1=new int[ff.length+intarrayp.length];
        System.arraycopy(ff,0,ff1,0,ff.length);
        System.arraycopy(intarrayp,0,ff1,ff.length,intarrayp.length);
        for (int a=0;a<ff1.length;a++){
            Log.d(TAG,"length of ff1 : "+ff1[a]);}

        Arrays.sort(ff1);
        for (int a=0;a<ff1.length;a++){
            Log.d(TAG,"length of ff1 sorted: "+ff1[a]);}
        for (int a=0;a<intarrayb.length;a++){
            Log.d(TAG,"length of paragraph sorted: "+ff1[a]);}



        ArrayList<String> storetext = new ArrayList<>();
        TessBaseAPI baseApi = new TessBaseAPI();
        String path = "/sdcard/Any Reader/FinalTess";
        baseApi.setDebug(true);
        baseApi.init(path, "eng");
        Log.d(TAG, "tess running create audio");
        baseApi.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST,
                ".,';:[]{}!@#$%^&*()_+=-?/0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");


        for (int m = 0; m < ff1.length; m++){
            Log.d(TAG, "loop 0");
            int finalM = m;
            if(m!=0) {storetext.add(" "+" "+" Next "+" "+" ");
                if (IntStream.of(intarrayh).anyMatch(x -> x == (ff1[finalM])) == true) {
                    if (IntStream.of(intarrayh).anyMatch(x -> x == (ff1[finalM - 1])) == true) {
                        Log.d(TAG, "loop 1");
//                        baseApi.setImage(BitmapFactory.decodeFile("/sdcard/Any Reader/Headings/h" + ff1[m] + ".jpg"));
//                        storetext.add(" Sub Heading " +" "+" " + baseApi.getUTF8Text());

                    } else {
                        Log.d(TAG, "loop 2");
//                        baseApi.setImage(BitmapFactory.decodeFile("/sdcard/Any Reader/Headings/h" + ff1[m] + ".jpg"));
//                        storetext.add(" Heading "  +" "+" "+ baseApi.getUTF8Text());
                    }
                } else if (IntStream.of(intarrayp).anyMatch(x -> x == (ff1[finalM])) == true) {
                    Log.d(TAG, "loop 3");
//                    baseApi.setImage(BitmapFactory.decodeFile("/sdcard/Any Reader/Points/p" + ff1[m] + ".jpg"));
//                    storetext.add(" Point "  +" "+" "+ baseApi.getUTF8Text());
                } else if (IntStream.of(intarrayb).anyMatch(x -> x == (ff1[finalM])) == true) {
                    Log.d(TAG, "loop 4");
                    baseApi.setImage(BitmapFactory.decodeFile("/sdcard/Any Reader/Para/b" + ff1[m] + ".jpg"));
                    storetext.add(" Paragraph "  +" "+" "+ baseApi.getUTF8Text());
                }
//            else    {storetext.add("Oops I did not encounter any text to read");}


            }
            else {
                if(IntStream.of(intarrayh).anyMatch(x -> x == (ff1[finalM])) == true){
                    Log.d(TAG, "loop a");
//                    baseApi.setImage(BitmapFactory.decodeFile("/sdcard/Any Reader/Headings/h" + ff1[m] + ".jpg"));
//                    storetext.add(" Heading "  +" "+" "+ baseApi.getUTF8Text());
                }
                else if (IntStream.of(intarrayp).anyMatch(x -> x == (ff1[finalM])) == true) {
                    Log.d(TAG, "loop b");
//                    baseApi.setImage(BitmapFactory.decodeFile("/sdcard/Any Reader/Points/p" + ff1[m] + ".jpg"));
//                    storetext.add(" Point "  +" "+" "+ baseApi.getUTF8Text());
                } else if (IntStream.of(intarrayb).anyMatch(x -> x == (ff1[finalM])) == true) {
                    Log.d(TAG, "loop c");
                    baseApi.setImage(BitmapFactory.decodeFile("/sdcard/Any Reader/Para/b" + ff1[m] + ".jpg"));
                    storetext.add(" Paragraph " +" "+" " + baseApi.getUTF8Text());
                }
            }
        }



        StringBuilder Bigstring = new StringBuilder();
        for (int j = 0; j < storetext.size(); j++) {
            Bigstring.append(storetext.get(j).toString());
        }

        return Bigstring;
    }
}
