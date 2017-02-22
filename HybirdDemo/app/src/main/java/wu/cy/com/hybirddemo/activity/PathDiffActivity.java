package wu.cy.com.hybirddemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wu.cy.com.hybirddemo.R;
import wu.cy.com.hybirddemo.util.Md5Util;
import wu.cy.com.hybirddemo.util.PatchDiffUtil;

public class PathDiffActivity extends AppCompatActivity {

    //低版本的apk文件
    private static final String APK_310 = "/sdcard/update_test/125.zip";

    //高版本的diff文件
    private static final String APK_312 = "/sdcard/update_test/126.zip";

    //生成的diff文件
    private static final String APK_DIFF = "/sdcard/update_test/diff.zip";

    //patch 之后生成的文件
    private static final String APK_NEW = "/sdcard/update_test/new.zip";


    private String newFileMD5 = "e506b7a123f49ce6dae369914d596a03";

    @Bind(R.id.tv_md5)
    TextView mTvMd5;
    @Bind(R.id.btn_make_diff)
    Button mBtnMakeDiff;
    @Bind(R.id.btn_patch_apk)
    Button mBtnPatchApk;
    @Bind(R.id.et_file_name)
    EditText mEtFileName;
    @Bind(R.id.btn_generate_md5)
    Button mBtnGenerateMd5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_diff);
        ButterKnife.bind(this);

        Log.d("wcy", getExternalFilesDir(null).getAbsolutePath());
    }

    /**
     *  查看文件的MD5 在EditText输入文件名
     */
    @OnClick(R.id.btn_generate_md5)
    public void generateTheFileMD5(){
        String fileName = "/sdcard/update_test/" + mEtFileName.getText().toString().trim();
        mTvMd5.setText(Md5Util.fileToMD5(fileName));
    }


    @OnClick(R.id.btn_make_diff)
    public void makeTheDiffApk(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                final int result = PatchDiffUtil.genDiff(APK_310, APK_312, APK_DIFF);
                Log.d("wcy", "genDiff for " + (System.currentTimeMillis() - startTime));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PathDiffActivity.this, result == 0 ? "Diff success" : "Diff Fail", Toast.LENGTH_LONG).show();
                    }
                });

            }
        }).start();
    }


    @OnClick(R.id.btn_patch_apk)
    public void patchTheDiffApk(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                final int result = PatchDiffUtil.patch(APK_310, APK_NEW, APK_DIFF);
                Log.d("wcy", "patch for " + (System.currentTimeMillis() - startTime));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PathDiffActivity.this, result == 0 ? "Patch success" : "Patch Fail", Toast.LENGTH_LONG).show();
                    }
                });

            }
        }).start();

    }
}
