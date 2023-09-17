package com.example.myapplication.Menu;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.myapplication.DataCenter.DbManager;
import com.example.myapplication.DataCenter.RescuerData;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuUtils {
    /**
     * 显示救援者列表弹窗
     */
    public static AlertDialog ShowRescuerListDialog(Context context, AdapterView.OnItemClickListener onClickListener, int titleId) {
        //弹窗构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        View viewDialog = createDialogView(context, onClickListener);
        builder.setView(viewDialog);
        builder.setTitle(titleId);
        builder.setPositiveButton(R.string.confirm, (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    /**
     * 构建弹窗的视图实例
     *
     * @return
     */
    private static View createDialogView(Context context, AdapterView.OnItemClickListener onClickListener) {
        //弹窗的视图
        View viewDialog = View.inflate(context, R.layout.fragment_query_dialog, null);
        //视图中的列表控件
        ListView lvRescuer = viewDialog.findViewById(R.id._LvRescuer);
        //获取简易适配器
        SimpleAdapter simpleAdapter = getSimpleAdapter(context);
        //为列表视图listview提供适配数据
        lvRescuer.setAdapter(simpleAdapter);
        //设置item点击回调
        lvRescuer.setOnItemClickListener(onClickListener);
        return viewDialog;
    }

    /**
     * 获取简易适配器
     */
    private static SimpleAdapter getSimpleAdapter(Context context) {
        //数据库管理器实例
        DbManager dbManager = new DbManager(context, 1);
        //源数据列表 从数据库拿到的结构
        ArrayList<RescuerData> sourceDataList = dbManager.queryRescuers();
        //列表控件上的每个Item承载的数据的列表 适配器数据
        ArrayList<Map<String, Object>> adapterDataList = new ArrayList<>();
        //从数据源设置数据到适配数据
        for (int i = 0; i < sourceDataList.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("sid", sourceDataList.get(i).getSid());
            map.put("name", sourceDataList.get(i).getName());
            map.put("age", sourceDataList.get(i).getAge());
            adapterDataList.add(map);
        }
        //创建简易适配器 用于供listview列表视图显示
        return new SimpleAdapter(context, adapterDataList,
                R.layout.fragment_query_dialog_item,
                new String[]{"sid", "name", "age"},
                new int[]{R.id._TxtSid, R.id._TxtName, R.id._TxtAge});
    }
}
