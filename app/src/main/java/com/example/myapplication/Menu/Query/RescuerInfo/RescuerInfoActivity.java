package com.example.myapplication.Menu.Query.RescuerInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.Menu.Operation.Update.UpdateRescuerInfoActivity;
import com.example.myapplication.R;

public class RescuerInfoActivity extends UpdateRescuerInfoActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disableInput(inputEditTextName);
        disableInput(editTextAge);
        disableInput(inputEditTextDesc);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id._BtnConfirm) {
            this.finish();
            return;
        }
        if (viewId == R.id._BtnCancel) {
            this.finish();
            return;
        }
    }

    /**
     * 设置编辑控件禁止编辑
     *
     * @param editText
     */
    private void disableInput(EditText editText) {
        editText.setKeyListener(null);
        editText.setFocusable(false);
        editText.setClickable(false);
        editText.setLongClickable(false);
    }
}
