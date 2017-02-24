package wu.cy.com.inspect.action;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.io.File;

import wu.cy.com.inspect.FileInfo;
import wu.cy.com.inspect.FileUtil;

/**
 * Created by wcy on 17/2/23.
 */

public class DeleteAction implements Action {

    private Context mContext;
    private String mActionName;

    public DeleteAction(Context context, String name) {
        this.mContext = context;
        this.mActionName = name;
    }

    @Override
    public void doAction(final FileInfo fileInfo) {
        AlertDialog dialog = new AlertDialog.Builder(mContext).setTitle("Delete File")
                .setMessage("delete this file " + fileInfo.fileName + "?")
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        File file = new File(fileInfo.filePath);
                        FileUtil.deleteDir(file);
                    }
                })
                .create();
        dialog.show();
    }

    @Override
    public String toString() {
        return mActionName;
    }
}
