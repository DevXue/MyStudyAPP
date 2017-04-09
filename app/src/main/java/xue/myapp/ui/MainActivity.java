package xue.myapp.ui;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import xue.myapp.R;
import xue.myapp.common.CommonActivity;
import xue.myapp.common.PermissionLintener;

public class MainActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                requestRuntimepermission(new String[]{Manifest.permission.CALL_PHONE,
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.CAMERA

                                }, new PermissionLintener() {
                                    @Override
                                    public void onGranted() {
                                        Toast.makeText(MainActivity.this, "同意权限", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onDenied(List<String> deniedPermisson) {
                                        for (String s : deniedPermisson) {
                                            Toast.makeText(MainActivity.this, s+"权限被拒绝", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //存文件时，把缓存文件存在自己APP安装包之下
    private void saveFile(){
       File file =getExternalCacheDir();  //此方法的弊端就是存的文件容易被清理软件清理掉
        file.getPath();
        //此方法是存文件保存在自己APP安装包自己定义的目录下清理软件清理不掉，参数相当于文件名，为空的话默认的文件名为Files
        getExternalFilesDir("myFileCatch");
    }
}
