package dianliju.hanzhentinkerdemo;

import android.Manifest;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static  final String HaHa = "修复终极成功";
    private TextView tv_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        initpper();
        tv_text = (TextView) findViewById(R.id.tv_test);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_text.setText(HaHa);
            }
        });
        findViewById(R.id.btn_startbug).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //修复包路径
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/tencent/QQfile_recv/patch_signed_7zip.apk";
                File file = new File(path);
                if (file.exists()){
                    Log.e("HANZHEN","文件存在");
                }else {
                    Log.e("HANZHEN","文件不存在");
                }
                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
                        path);//等下要push到SD卡里面去apk，以达到更新的目的
            }
        });


        ListView lv = (ListView) findViewById(R.id.lv_test);
        lv.setAdapter(new MyAdapter());

    }


    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = View.inflate(MainActivity.this,R.layout.item,null);
            return view;
        }
    }

    /**
     * requestpermission
     */
    private void initpper() {
        // 在Activity：
        AndPermission.with(MainActivity.this)
                .requestCode(100)
                .permission(Manifest.permission.WRITE_CONTACTS)
        .callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                Log.e("HANZHEN","成功");
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

            }
        })
        .start();
    }
}
