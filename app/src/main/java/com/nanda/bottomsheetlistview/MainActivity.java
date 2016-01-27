package com.nanda.bottomsheetlistview;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nanda.bottomsheetlistview.bottomsheet.BottomSheetLayout;
import com.nanda.bottomsheetlistview.fragment.RecyclerViewTest;

/**
 * Created by Nandagopal on 28-Jan-16.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBSRecyclerview;
    protected BottomSheetLayout bottomSheetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        findViewById(R.id.bottomSheetListview).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bottomSheetListview) {
            new RecyclerViewTest().show(getSupportFragmentManager(), R.id.bottomsheet);
        }
    }
}
