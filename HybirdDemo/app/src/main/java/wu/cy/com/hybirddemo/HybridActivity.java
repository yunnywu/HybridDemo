package wu.cy.com.hybirddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HybridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hybrid);

        HybridFragment hybridFragment = HybridFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.root_content,
                hybridFragment).commit();
    }
}
