package com.example.ml;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageHelper;

import com.example.ml.helpers.imageHelperActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void GoToImageActivity(View view){
        Intent intent = new Intent(this, imageHelperActivity.class);
        startActivity(intent);
    }


}



