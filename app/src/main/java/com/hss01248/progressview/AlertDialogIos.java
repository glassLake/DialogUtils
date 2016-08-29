package com.hss01248.progressview;

import android.content.Context;

/**
 * Created by Administrator on 2016/7/24.
 */
public class AlertDialogIos extends android.support.v7.app.AlertDialog {
    protected AlertDialogIos(Context context) {
        super(context);
    }

    protected AlertDialogIos(Context context, int theme) {
        super(context, theme);
    }

    protected AlertDialogIos(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


}
