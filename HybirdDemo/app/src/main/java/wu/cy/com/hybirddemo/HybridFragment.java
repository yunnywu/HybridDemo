package wu.cy.com.hybirddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by wcy8038 on 2017/2/21.
 */

public class HybridFragment extends Fragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static HybridFragment newInstance() {
        return new HybridFragment();
    }
}
