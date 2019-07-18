package com.trjx.tlibs.uils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class SnackbarUtil {

    private static Snackbar snackbar;
    public static void show(View view,String text){
        show(view,text,Snackbar.LENGTH_SHORT);
    }

    public static void show(View view,String text,int duration){
        if (snackbar == null ) {
            snackbar = Snackbar.make(view, text, duration);
            snackbar.addCallback(new Snackbar.Callback(){
                @Override
                public void onShown(Snackbar sb) {
                    super.onShown(sb);
                }

                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                }
            });
            snackbar.show();
        }else{
            snackbar.setText(text);
            if (!snackbar.isShown()) {
                snackbar.show();
            }
        }
    }


    public static void showAction(View view, String text, View.OnClickListener listener){
        //一直显示
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("action1", listener);
//        snackbar.setAction("action1", new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ToastUtil.showToast(context, "action1");
//            }
//        });
        snackbar.show();
    }


}
