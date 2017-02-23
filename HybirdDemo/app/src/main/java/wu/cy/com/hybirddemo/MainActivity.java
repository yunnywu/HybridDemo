package wu.cy.com.hybirddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.cy.com.hybirddemo.activity.PathDiffActivity;
import wu.cy.com.hybirddemo.service.ResUpdateIntentService;
import wu.cy.com.inspect.FileListActivity;

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
                Intent intent = new Intent(FileListActivity.ACTION_FILE_MANAGER);
                startActivity(intent);
            }
        });

        ResUpdateIntentService.startActionResUpdate(this, "no");
    }
}
