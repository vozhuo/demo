package com.example.myapplication.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.AppFragmentPagerAdapter;
import com.example.myapplication.ui.fragment.AccountFragment;
import com.example.myapplication.ui.fragment.AnalysisFragment;
import com.example.myapplication.ui.fragment.DataFragment;
import com.example.myapplication.ui.fragment.HomeFragment;
import com.example.myapplication.ui.fragment.PredictFragment;
import com.example.myapplication.ui.fragment.SecurityFragment;
import com.example.myapplication.ui.fragment.WarningFragment;
import com.example.myapplication.widget.BottomNavigationController;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class AppMainActivity extends AppCompatActivity{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Toolbar mToolbar;

    private HomeFragment mHomeFragment;       //地图模块
    private DataFragment mDataFragment;       //监测模块
    private Fragment fragment3;               //设备模块
    private SecurityFragment mSecurityFragment;//安全模块
    private WarningFragment mWarningFragment;  //预警模块
    private PredictFragment mPredictFragment;//预测模块
    private AccountFragment mAccountFragment;  //账号模块
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);
        initViews();
        initFragments();
    }

    private void initViews(){
        mToolbar = findViewById(R.id.activity_main_toolbar);
        mTabLayout = findViewById(R.id.activity_main_tablayout);
        mViewPager = findViewById(R.id.activity_main_viewpager);
    }

    private void initFragments(){
        mHomeFragment = new HomeFragment();
        mDataFragment = new DataFragment();
        fragment3 = new MyFragment("设备");
        mSecurityFragment = new SecurityFragment();
        mWarningFragment = new WarningFragment();
        mPredictFragment = new PredictFragment();

        mAccountFragment = new AccountFragment();

        mFragmentList = new ArrayList<>();
        mFragmentList.add(mHomeFragment);
        mFragmentList.add(mDataFragment);
        mFragmentList.add(fragment3);
        mFragmentList.add(mSecurityFragment);
        mFragmentList.add(mWarningFragment);
        mFragmentList.add(mPredictFragment);
        mFragmentList.add(mAccountFragment);

        AppFragmentPagerAdapter adapter = new AppFragmentPagerAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(adapter);

        BottomNavigationController.use(mTabLayout).toolbar(mToolbar).associateWith(mViewPager);
    }

    public static class MyFragment extends Fragment{

        String name;

        public MyFragment(String name) {
            this.name = name;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_test_main,container,false);
            TextView tv = view.findViewById(R.id.fragment_test_main_tv);
            tv.setText("Fragment:" + name);
            return view;
        }
    }
}
