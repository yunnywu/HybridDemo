package wu.cy.com.inspect;

import wu.cy.com.inspect.FileInfo;

/**
 * Created by wcy on 17/2/23.
 */

public interface Action {
    void doAction(final FileInfo fileInfo, ActionCallBack callBack);

    interface ActionCallBack{
        void callBack(boolean needReload);
    }
}
