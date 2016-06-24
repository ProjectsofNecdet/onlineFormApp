package com.example.necdet.formapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void KayitOnclick(View view){
        Intent KayitAc = new Intent(this, Kayit.class);
        startActivity(KayitAc);
    }

    public void GirisOnClick(View view){
        Intent GirisAc = new Intent(this, Giris.class);
        startActivity(GirisAc);
    }
}
