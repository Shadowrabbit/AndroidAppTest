package com.example.myapplication.Menu.Query;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.myapplication.DataCenter.DbManager;
import com.example.myapplication.DataCenter.RescuerData;
import com.example.myapplication.Menu.MenuUtils;
import com.example.myapplication.Menu.Query.RescuerInfo.RescuerInfoActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

/**
 * 查询分页
 */
public class QueryFragment extends Fragment implements View.OnClickListener {
    private AlertDialog _alertDialog; //列表弹窗
    private DbManager _dbManager; //数据库管理器

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        _dbManager = new DbManager(getActivity(), 1);
        onInitView(view);
        return view;
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id._BtnQuery) {
            _alertDialog = MenuUtils.ShowRescuerListDialog(getActivity(), (adapterView, view1, i, l) -> {
                Intent intent = new Intent(getActivity(), RescuerInfoActivity.class);
                ArrayList<RescuerData> sourceDataList = _dbManager.queryRescuers();
                RescuerData rescuerData = sourceDataList.get(i);
                intent.putExtra("rescuerData", rescuerData);
                _alertDialog.dismiss();
                startActivity(intent);
            }, R.string.update_info);
        }
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void onInitView(View view) {
        Button btnQuery = view.findViewById(R.id._BtnQuery);
        btnQuery.setOnClickListener(this);
    }
}
