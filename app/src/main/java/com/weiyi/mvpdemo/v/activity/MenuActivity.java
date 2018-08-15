package com.weiyi.mvpdemo.v.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.weiyi.mvpdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.bottom_menu)
    Button mBottomMenu;
    @BindView(R.id.center_menu)
    Button mCenterMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bottom_menu, R.id.center_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottom_menu:
                break;
            case R.id.center_menu:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, 1, "MENU_1");
        menu.add(Menu.NONE, 2, 2, "MENU_2");
        menu.add(Menu.NONE, 3, 3, "MENU_3");
        menu.add(Menu.NONE, 4, 4, "MENU_4");
        menu.add(Menu.NONE, 5, 5, "MENU_5");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Toast.makeText(this, "Menu_click_1", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "Menu_click_2", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "Menu_click_3", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "Menu_click_4", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this, "Menu_click_5", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        Toast.makeText(this, "选项菜单关闭了", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Toast.makeText(this,
                "选项菜单显示之前onPrepareOptionsMenu方法会被调用，你可以用此方法来根据打当时的情况调整菜单",
                Toast.LENGTH_LONG).show();
        // 如果返回false，此方法就把用户点击menu的动作给消费了，onCreateOptionsMenu方法将不会被调用
        return true;
    }
}
