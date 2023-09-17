package com.example.myapplication.Menu.Operation.Update;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.DataCenter.RescuerData;
import com.example.myapplication.Menu.Operation.Add.AddRescuerInfoActivity;
import com.example.myapplication.R;

public class UpdateRescuerInfoActivity extends AddRescuerInfoActivity {
    private RescuerData _rescuerData; //当前修改中救援者的数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _rescuerData = (RescuerData) getIntent().getSerializableExtra("rescuerData");
        setData();
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
            _rescuerData.setName(name);
            _rescuerData.setAge(age);
            _rescuerData.setDesc(desc);
            dbManager.updateRescuerData(_rescuerData);
            Toast.makeText(this, R.string.update_success, Toast.LENGTH_SHORT).show();
            this.finish();
            return;
        }
        if (viewId == R.id._BtnCancel) {
            this.finish();
            return;
        }
    }

    /**
     * 设置数据
     */
    private void setData() {
        inputEditTextName.setText(_rescuerData.getName());
        editTextAge.setText(String.valueOf(_rescuerData.getAge()));
        inputEditTextDesc.setText(_rescuerData.getDesc());
    }
}
