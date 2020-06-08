package com.project.expert11demo.Ui.CommonAndroid;

import android.app.Activity;
import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.project.expert11demo.R;

public class CommonFunction extends AppCompatActivity {
    static ProgressDialog progressDialog;

    public static void showProgressDialog(Activity activity){
        if(progressDialog !=  null && progressDialog.isShowing()){
            return;
        }
        progressDialog = new ProgressDialog(activity);
        progressDialog.setProgressStyle(R.style.Widget_AppCompat_ProgressBar);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void hideProgressDialog(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
