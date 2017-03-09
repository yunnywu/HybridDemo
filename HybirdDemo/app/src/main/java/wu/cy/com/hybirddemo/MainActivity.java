package wu.cy.com.hybirddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import butterknife.Bind;
import butterknife.ButterKnife;
import wu.cy.com.hybirddemo.hybrid.HybridActivity;
import wu.cy.com.hybirddemo.activity.PathDiffActivity;
import wu.cy.com.hybirddemo.service.ResUpdateIntentService;
import wu.cy.com.hybirddemo.util.PackageUtil;
import wu.cy.com.hybirddemo.util.SPUtil;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_patch)
    Button mPatchDiff;

    @Bind(R.id.btn_file)
    Button mBtnFileCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mPatchDiff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PathDiffActivity.class);
                startActivity(intent);
            }
        });

        mBtnFileCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HybridActivity.class);
                startActivity(intent);
            }
        });

        if(SPUtil.getBoolean("ResUpdate" + PackageUtil.getVersionName() , false)) {
            //检查是否需要更新资源包
            ResUpdateIntentService.startActionResUpdate(this);
        }else {
            //每个版本首次进入时，解压缩assets 下面的zip 文件
            ResUpdateIntentService.startActionUnzipAssets(this);
        }
    }
}
