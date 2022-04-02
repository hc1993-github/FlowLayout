package com.hc.flowlayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SevenActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    File file;
    Uri newuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+".jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK){
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(Uri.fromFile(file),"image/*");
            intent.putExtra("crop","true");
            intent.putExtra("scale",true);
            if(Build.MODEL.contains("HUAWEI")){
                intent.putExtra("aspectX",9998);
                intent.putExtra("aspectY",9999);
            }else {
                intent.putExtra("aspectX",1);
                intent.putExtra("aspectY",1);
            }
            intent.putExtra("outputX",300);
            intent.putExtra("outputY",300);
            intent.putExtra("return-data",false);
            newuri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + "new.jpg"));
            intent.putExtra(MediaStore.EXTRA_OUTPUT,newuri);
            intent.putExtra("outputFormat",Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection",true);
            //intent.putExtra("circleCrop",false);
            startActivityForResult(intent,200);
        }else if(requestCode==200 && resultCode==RESULT_OK){
            try {
                file.delete();
                imageView.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(newuri)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}