package com.chooloo.www.callmanager.activity;

import android.content.Context;
import android.os.Bundle;
import android.telecom.Call;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chooloo.www.callmanager.CallManager;
import com.chooloo.www.callmanager.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OngoingCallActivity extends AppCompatActivity {
    @BindView(R.id.answer_btn)
    Button mAnswerButton;
    @BindView(R.id.deny_btn)
    Button mDenyButton;
    @BindView(R.id.text_status)
    TextView mStatusText;
    @BindView(R.id.caller_text)
    TextView mCallerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_call);
        ButterKnife.bind(this);

        if (CallManager.sCallState == "RINGING") {
            mStatusText.setText(getResources().getString(R.string.status_incoming_call));
//            mAnswerButton.setVisibility(View.VISIBLE);
//            mDenyButton.setVisibility(View.VISIBLE);
        } else {
            mStatusText.setText("Dialing");
//            mAnswerButton.setVisibility(View.INVISIBLE);
//            mDenyButton.setVisibility(View.INVISIBLE);
        }

        if (CallManager.sPhoneNumber == "") {
            mCallerText.setText("Unknown Number");
        } else if (CallManager.getContactName(this) == "") {
            mCallerText.setText(CallManager.getPhoneNumber());
        } else {
            mCallerText.setText(CallManager.getContactName(this));
        }
    }

    @OnClick(R.id.answer_btn)
    public void answer(View view) {
        CallManager.sAnswer();
        mStatusText.setText(getResources().getString(R.string.status_ongoing_call));
    }

    @OnClick(R.id.deny_btn)
    public void deny(View view) {
        mStatusText.setText(getResources().getString(R.string.status_call_ended));
        CallManager.sReject();
        finish();
    }

    //TODO Change the UI depending on the state (active/calling/hold...)
    private void updateUI() {
        switch (CallManager.sCall.getState()) {

        }
    }
}
