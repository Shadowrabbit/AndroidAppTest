package com.example.myapplication.Menu.Operation;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.DataCenter.DbManager;
import com.example.myapplication.DataCenter.RescuerData;
import com.example.myapplication.Menu.MenuUtils;
import com.example.myapplication.Menu.Operation.Add.AddRescuerInfoActivity;
import com.example.myapplication.Menu.Operation.Update.UpdateRescuerInfoActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * 操作分页
 */
public class OperationFragment extends Fragment implements View.OnClickListener {
    private AlertDialog _alertDialog; //列表弹窗
    private DbManager _dbManager; //数据库管理器

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation, container, false);
        onInitView(view);
        _dbManager = new DbManager(getActivity(), 1);
        return view;
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        //添加救援者信息
        if (viewId == R.id._BtnAddInfo) {
            Intent intent = new Intent(getActivity(), AddRescuerInfoActivity.class);
            startActivity(intent);
            return;
        }
        //移除信息
        if (viewId == R.id._BtnRemoveInfo) {
            //显示救援者列表的弹窗
            _alertDialog = MenuUtils.ShowRescuerListDialog(getActivity(), (adapterView, view1, i, l) -> {
                showRemoveConfirmDialog(i);
            }, R.string.remove_info);
            return;
        }
        //更新信息
        if (viewId == R.id._BtnUpdateInfo) {
            _alertDialog = MenuUtils.ShowRescuerListDialog(getActivity(), (adapterView, view1, i, l) -> {
                Intent intent = new Intent(getActivity(), UpdateRescuerInfoActivity.class);
                ArrayList<RescuerData> sourceDataList = _dbManager.queryRescuers();
                RescuerData rescuerData = sourceDataList.get(i);
                intent.putExtra("rescuerData", rescuerData);
                _alertDialog.dismiss();
                startActivity(intent);
            }, R.string.update_info);
            return;
        }
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void onInitView(View view) {
        Button btnAddInfo = view.findViewById(R.id._BtnAddInfo);
        Button btnRemoveInfo = view.findViewById(R.id._BtnRemoveInfo);
        Button btnUpdateInfo = view.findViewById(R.id._BtnUpdateInfo);
        btnAddInfo.setOnClickListener(this);
        btnRemoveInfo.setOnClickListener(this);
        btnUpdateInfo.setOnClickListener(this);
    }

    /**
     * 显示移除确认弹窗
     *
     * @param i
     */
    private void showRemoveConfirmDialog(int i) {
        //确认删除的弹窗
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.remove_confirm);
        builder.setPositiveButton(R.string.confirm, (dialog, which) -> {
            ArrayList<RescuerData> sourceDataList = _dbManager.queryRescuers();
            RescuerData rescuerData = sourceDataList.get(i);
            _dbManager.deleteRescuerData(rescuerData.getSid());
            Toast.makeText(getActivity(), R.string.remove_success, Toast.LENGTH_SHORT).show();
            if (_alertDialog != null) {
                _alertDialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}
