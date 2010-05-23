package com.rumpl.monkey;

import android.app.Activity;
import android.os.Bundle;


public class Monkey extends Activity  {
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MonkeyView monkey = new MonkeyView(this);
        setContentView(monkey);
        monkey.requestFocus();
    }
}