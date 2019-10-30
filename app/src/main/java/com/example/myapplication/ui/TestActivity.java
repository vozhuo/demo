package com.example.myapplication.ui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.widget.BottomNavigationController;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TestActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Fragment fragment4;
    private Fragment fragment5;
    private Fragment fragment6;
    private Fragment fragment7;
    private List<Fragment> mFragmentList;
    private String[] dataItems = {"监测","设备"};
    private String[] analysisItems = {"安全","预警","预测"};
    private PopupWindow mDataPopupWindow;
    private PopupWindow mAnalysisPopupWindow;

    //trace[0]: 1表示用户选择了某一个子选项；0表示用户没有选择任何子选项
    //trace[1]：表示上一个tab的位置，初始化为0
    private int[] traceInPopupWindowClickEvent = {0,0};
    private static final int TAB_IDLE = -1;
    private static final int TAB_ACTIVED_UNSELECTED = 0;
    private static final int TAB_ACTIVED_SELECTED = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);
        initViews();
        initFragments();
        //setUpCustomView();
    }

    private void initViews(){
        mTabLayout = findViewById(R.id.activity_test_main_tablayout);
        mViewPager = findViewById(R.id.activity_test_main_viewpager);
    }

    private void initFragments(){
        fragment1 = new MyFragment("首页");
        fragment2 = new MyFragment("监测");
        fragment3 = new MyFragment("设备");
        fragment4 = new MyFragment("安全");
        fragment5 = new MyFragment("预警");
        fragment6 = new MyFragment("预测");
        fragment7 = new MyFragment("账号");

        mFragmentList = new ArrayList<>();
        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
        mFragmentList.add(fragment4);
        mFragmentList.add(fragment5);
        mFragmentList.add(fragment6);
        mFragmentList.add(fragment7);

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(adapter);

        BottomNavigationController.use(mTabLayout).associateWith(mViewPager);
    }

    private void setUpCustomView(){
        for (int i = 0;i <4;i++){
            TabLayout.Tab tab = mTabLayout.newTab();
            View view = getLayoutInflater().inflate(R.layout.bottom_navigation_tab_item,null);
            tab.setCustomView(view);
            if (tab.getCustomView() != null){
                View tabView = (View) tab.getCustomView().getParent();
                tabView.setTag(i);
                int finalI = i;
                tabView.setOnClickListener(v->{
                    traceInPopupWindowClickEvent[0] = TAB_ACTIVED_UNSELECTED;
                });
            }

            mTabLayout.addTab(tab);

        }

        TextView tv1 = mTabLayout.getTabAt(0).getCustomView().findViewById(R.id.tab_item_textview);
        TextView tv2 = mTabLayout.getTabAt(1).getCustomView().findViewById(R.id.tab_item_textview);
        TextView tv3 = mTabLayout.getTabAt(2).getCustomView().findViewById(R.id.tab_item_textview);
        TextView tv4 = mTabLayout.getTabAt(3).getCustomView().findViewById(R.id.tab_item_textview);

        tv1.setText("首页");
        tv2.setText("数据");
        tv3.setText("分析");
        tv4.setText("账号");

        mTabLayout.addOnTabSelectedListener(this);
        mViewPager.setCurrentItem(0);
        mTabLayout.getTabAt(0).select();

        View view = mTabLayout.getTabAt(0).getCustomView();
        TextView tv = view.findViewById(R.id.tab_item_textview);
        tv.setTextColor(getResources().getColor(R.color.bottom_navigation_menu_text_color));

        mDataPopupWindow = buildPopupWindow(dataItems,1);
        mAnalysisPopupWindow = buildPopupWindow(analysisItems,3);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        TextView tv = view.findViewById(R.id.tab_item_textview);
        ImageView iv = view.findViewById(R.id.tab_item_imageview);
        tv.setTextColor(getResources().getColor(R.color.bottom_navigation_menu_text_color));

        if (traceInPopupWindowClickEvent[0] == TAB_IDLE)
            return;

        int index = tab.getPosition();
        if (index == 0){
            traceInPopupWindowClickEvent[0] = TAB_IDLE;
            traceInPopupWindowClickEvent[1] = 0;
            mViewPager.setCurrentItem(0,false);
        }else if (index == 1){
            showPopupWindow(mDataPopupWindow,tab.getCustomView());
        }else if (index == 2){
            showPopupWindow(mAnalysisPopupWindow,tab.getCustomView());
        }else{
            traceInPopupWindowClickEvent[0] = TAB_IDLE;
            traceInPopupWindowClickEvent[1] = 3;
            mViewPager.setCurrentItem(6,false);
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        TextView tv = view.findViewById(R.id.tab_item_textview);
        ImageView iv = view.findViewById(R.id.tab_item_imageview);

        tv.setTextColor(getResources().getColor(R.color.bottom_navigation_menu_unselected_text_color));
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        int index = tab.getPosition();
        if (index == 1){
            showPopupWindow(mDataPopupWindow,tab.getCustomView());
        }else if (index == 2){
            showPopupWindow(mAnalysisPopupWindow,tab.getCustomView());
        }
    }

    private PopupWindow buildPopupWindow(String[] item,int offset){
        PopupWindow popupWindow = new PopupWindow(this);

        LinearLayout rootView = (LinearLayout) getLayoutInflater().inflate(R.layout.bottom_navigation_popup_window_main,null);
        View[] views = new View[item.length];

        for (int i = 0;i < views.length;i++){
            views[i] = getLayoutInflater().inflate(R.layout.bottom_navigation_popup_window_item,rootView,false);
            ImageView icon = views[i].findViewById(R.id.bottom_navigation_popup_window_item_iv);
            TextView text = views[i].findViewById(R.id.bottom_navigation_popup_window_item_tv);

            text.setText(item[i]);
            int finalI = i;
            views[i].setOnClickListener(v -> {
                traceInPopupWindowClickEvent[0] = TAB_ACTIVED_SELECTED;    //用户点击了某一选项，记录下来
                traceInPopupWindowClickEvent[1] = offset == 1 ? 1 : 2;
                mViewPager.setCurrentItem(finalI + offset,false);
                popupWindow.dismiss();
            });
            rootView.addView(views[i]);
        }

        popupWindow.setContentView(rootView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        popupWindow.setOnDismissListener(() -> {
            if (traceInPopupWindowClickEvent[0] == TAB_ACTIVED_UNSELECTED){
                //用户打开子菜单后没有选择任何选项就关闭了，那么把ViewPager切换为当前选项卡的第一个子菜单
                traceInPopupWindowClickEvent[0] = TAB_IDLE;
                mTabLayout.getTabAt(traceInPopupWindowClickEvent[1]).select();

            }
        });
        return popupWindow;
    }

    private void showPopupWindow(PopupWindow popupWindow,View anchor){
        popupWindow.showAsDropDown(anchor,0,
                -(popupWindow.getContentView().getMeasuredHeight() + anchor.getHeight() + 10), Gravity.LEFT);
    }

    static class MyFragmentAdapter extends FragmentPagerAdapter{

        private List<Fragment> mFragmentList;

        public MyFragmentAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            mFragmentList = fragmentList;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
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
