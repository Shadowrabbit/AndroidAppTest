package com.example.myapplication.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Menu.MenuActivity;
import com.example.myapplication.R;

/**
 * 主页面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEnter = findViewById(R.id._buttonEnter);
        btnEnter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id._buttonEnter) {
            onClickEnter();
        }
    }

    /**
     * 进入按钮点击回调
     */
    private void onClickEnter() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}