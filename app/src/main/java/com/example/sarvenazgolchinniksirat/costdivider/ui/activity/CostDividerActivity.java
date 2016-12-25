package com.example.sarvenazgolchinniksirat.costdivider.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.sarvenazgolchinniksirat.costdivider.R;
import com.example.sarvenazgolchinniksirat.costdivider.ui.fragment.WelcomeFragment;
import com.example.sarvenazgolchinniksirat.costdivider.util.FragmentNavigationUtil;

public class CostDividerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_divider);

        FragmentNavigationUtil.showFragment(WelcomeFragment.newInstance(), getSupportFragmentManager(), R.id.content_pane);
    }
}
