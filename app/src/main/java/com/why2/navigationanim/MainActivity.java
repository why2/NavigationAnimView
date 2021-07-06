package com.why2.navigationanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.why2.navigationanim.view.NavigationAnimLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationAnimLayout nal = findViewById(R.id.nal);
        nal.setClickListener(position -> {
            Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        });
    }
}