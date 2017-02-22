package wu.cy.com.hybirddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.cy.com.hybirddemo.activity.PathDiffActivity;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_patch)
    Button mPatchDiff;

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

    }
}
