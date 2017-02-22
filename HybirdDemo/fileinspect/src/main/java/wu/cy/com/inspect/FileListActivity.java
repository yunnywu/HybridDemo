package wu.cy.com.inspect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FileListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);


        getFilesDir().getAbsolutePath();
    }
}
