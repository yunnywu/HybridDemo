package wu.cy.com.hybirddemo.hybrid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import wu.cy.com.hybirddemo.Global;
import wu.cy.com.hybirddemo.R;
import wu.cy.com.hybirddemo.hybrid.HybridFragment;

public class HybridActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hybrid);

        HybridFragment hybridFragment = HybridFragment.newInstance(Global.TEST_INDEX_URL);
        getSupportFragmentManager().beginTransaction().replace(R.id.root_content,
                hybridFragment).commit();
    }
}
