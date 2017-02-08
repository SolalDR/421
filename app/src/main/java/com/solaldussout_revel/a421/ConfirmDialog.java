package com.solaldussout_revel.a421;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ConfirmDialog extends Dialog {
    private Context mContext;
    private String message;

    public interface DialogListener {
        public void ready();

        public void cancelled();
    }

    private DialogListener mReadyListener;

    public ConfirmDialog(Context context, String lib, DialogListener readyListener) {
        super(context);
        mReadyListener = readyListener;
        mContext = context;
        message = lib;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.confirm_dialog);

        Button buttonOK = (Button) findViewById(R.id.dialogOK);
        Button buttonCancel = (Button) findViewById(R.id.dialogCancel);
        TextView text = (TextView) findViewById(R.id.dialogLabel);
        text.setText(message.toString());

        buttonOK.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                mReadyListener.ready();
                ConfirmDialog.this.dismiss();
            }
        });
        buttonCancel.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                mReadyListener.cancelled();
                ConfirmDialog.this.dismiss();
            }
        });
    }
}
