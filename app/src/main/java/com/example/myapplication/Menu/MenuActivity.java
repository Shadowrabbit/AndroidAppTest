package com.example.myapplication.Menu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Menu.About.AboutFragment;
import com.example.myapplication.Menu.Operation.OperationFragment;
import com.example.myapplication.Menu.Query.QueryFragment;
import com.example.myapplication.R;

import java.util.HashMap;

/**
 * 菜单页面
 */
public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private final FragmentManager _fragmentManager = getFragmentManager(); //用于管理fragment分页切换的管理器
    private final OperationFragment _fragmentOperation = new OperationFragment(); //操作分页实例
    private final QueryFragment _fragmentQuery = new QueryFragment(); //查询分页实例
    private final AboutFragment _fragmentAbout = new AboutFragment(); //关于分页实例
    private final HashMap<Integer, Fragment> _buttonId2Fragment = new HashMap<Integer, Fragment>(); //按钮与fragment分页映射表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //初始化页面控件与配置
        onViewInit();
        //设置默认页签
        changeFragment(_buttonId2Fragment.get(R.id._BtnOperation));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Fragment fragment = _buttonId2Fragment.get(id);
        changeFragment(fragment);
    }

    /**
     * 初始化页面回调
     */
    private void onViewInit() {
        //绑定按钮 注册点击事件
        Button btnOperation = findViewById(R.id._BtnOperation);
        Button btnQuery = findViewById(R.id._BtnQuery);
        Button btnAbout = findViewById(R.id._BtnAbout);
        btnOperation.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        //设置按钮id与fragment分页的映射关系
        _buttonId2Fragment.put(R.id._BtnOperation, _fragmentOperation);
        _buttonId2Fragment.put(R.id._BtnQuery, _fragmentQuery);
        _buttonId2Fragment.put(R.id._BtnAbout, _fragmentAbout);
    }

    /**
     * 切换展示页签
     * @param fragment 展示页签实例
     */
    private void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = _fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id._FrameLayoutContent, fragment);
        fragmentTransaction.commit();
    }
}
