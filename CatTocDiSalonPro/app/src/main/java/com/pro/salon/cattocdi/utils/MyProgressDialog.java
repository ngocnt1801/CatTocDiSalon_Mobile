package com.pro.salon.cattocdi.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.pro.salon.cattocdi.WorkingHoursActivity;

public class MyProgressDialog {
    private static ProgressDialog dialog;

    public static void openDialog(Context context) {
        if (dialog == null) {
            dialog = new ProgressDialog(context);
        }
        dialog.setTitle("Loading");
        dialog.setMessage("Đang xử lý...");
        dialog.setCancelable(false); // disable dismiss by tapping outside of the dialog
        dialog.show();
    }

    public static void closeDialog(){
        if(dialog != null){
            dialog.dismiss();
        }
    }
}
