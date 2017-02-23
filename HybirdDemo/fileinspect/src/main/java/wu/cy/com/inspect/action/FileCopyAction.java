package wu.cy.com.inspect.action;

import android.content.Context;

import wu.cy.com.inspect.FileInfo;

/**
 * Created by wcy on 17/2/23.
 */

public class FileCopyAction implements Action{

    private Context mContext;
    private String mActionName;

    public FileCopyAction(Context context, String name) {
        this.mContext = context;
        this.mActionName = name;
    }

    @Override
    public void doAction(final FileInfo fileInfo) {

    }

    @Override
    public String toString() {
        return mActionName;
    }
}
