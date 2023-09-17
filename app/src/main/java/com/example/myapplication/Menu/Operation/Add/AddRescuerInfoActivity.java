package com.example.myapplication.Menu.Operation.Add;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DataCenter.DbManager;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * 添加救援者信息页面
 */
public class AddRescuerInfoActivity extends AppCompatActivity implements View.OnClickListener {
    protected DbManager dbManager; //数据库管理器
    protected TextInputEditText inputEditTextName; //姓名编辑框
    protected EditText editTextAge; //年龄编辑框
    protected TextInputEditText inputEditTextDesc; //详情编辑框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DbManager(this, 1);
        setContentView(R.layout.activity_add_rescuer_info);
        //初始化页面控件与配置
        onViewInit();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id._BtnConfirm) {
            String name = String.valueOf(inputEditTextName.getText());
            String strAge = String.valueOf(editTextAge.getText());
            String desc = String.valueOf(inputEditTextDesc.getText());
            if (name.isEmpty()) {
                Toast.makeText(this, R.string.add_fail, Toast.LENGTH_SHORT).show();
                return;
            }
            if (strAge.isEmpty()) {
                Toast.makeText(this, R.string.add_fail, Toast.LENGTH_SHORT).show();
                return;
            }
            if (desc.length() > 200) {
                Toast.makeText(this, R.string.add_fail_too_long_desc, Toast.LENGTH_SHORT).show();
            }
            int age = Integer.parseInt(strAge);
            dbManager.addRescuerData(name, age, desc);
            Toast.makeText(this, R.string.add_success, Toast.LENGTH_SHORT).show();
            this.finish();
            return;
        }
        if (viewId == R.id._BtnCancel) {
            this.finish();
            return;
        }
    }

    /**
     * 初始化页面回调
     */
    private void onViewInit() {
        //绑定按钮 注册点击事件
        inputEditTextName = findViewById(R.id._InputEditTextName);
        editTextAge = findViewById(R.id._EditTextAge);
        inputEditTextDesc = findViewById(R.id._InputEditTextDesc);
        Button btnConfirm = findViewById(R.id._BtnConfirm);
        Button btnCancel = findViewById(R.id._BtnCancel);
        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }
}
