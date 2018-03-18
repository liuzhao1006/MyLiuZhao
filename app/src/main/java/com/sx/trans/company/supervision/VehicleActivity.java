package com.sx.trans.company.supervision;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.sx.trans.R;
import com.sx.trans.supervision.app.BaseActivity;
import com.sx.trans.supervision.fragment.FragmentVehicle;

/**
 * 此类只有一个目的,就是将原本监控模块中的定位页面放在这里,展示,并保证功能完整
 */
public class VehicleActivity extends BaseActivity {

    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        FrameLayout fl_vehicle = findViewById(R.id.fl_vehicle);
        if (transaction == null) {
            transaction = getSupportFragmentManager().beginTransaction();
        }
        FragmentVehicle vehicle = new FragmentVehicle();
        transaction.add(R.id.fl_vehicle, vehicle).commit();

    }


}
