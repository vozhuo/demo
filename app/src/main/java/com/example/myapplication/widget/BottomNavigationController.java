package com.example.myapplication.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;

/**
 *  A controller used to associate ViewPager with TabLayout.This class could
 *  manage the tabs to achieve the function of bottom navigation submenu.
 *
 * @author Chen Yu
 * @since  2019.10.30
 * @version 1.0.0
 */
public class BottomNavigationController {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] dataItems = {"监测","设备"};
    private String[] analysisItems = {"安全","预警","预测"};
    private PopupWindow mDataPopupWindow;
    private PopupWindow mAnalysisPopupWindow;
    private ClickTraceRecorder mRecorder;

    private int mTabCount;

    private BottomNavigationController(TabLayout tabLayout){
        this.mTabLayout = tabLayout;
        this.mTabCount = 4;
        this.mRecorder = new ClickTraceRecorder();
    }

    public static BottomNavigationController use(TabLayout tabLayout){
        BottomNavigationController controller = new BottomNavigationController(tabLayout);
        return controller;
    }

    public BottomNavigationController tabCount(int count){
        this.mTabCount = count;
        return this;
    }

    public void associateWith(ViewPager viewPager){
        if (viewPager.getAdapter() == null)
            throw new NullPointerException("the ViewPager should have an adapter.");
        this.mViewPager = viewPager;
        setupTabView();
    }

    private void setupTabView(){
        for (int i = 0;i < mTabCount;i++){
            TabLayout.Tab tab = mTabLayout.newTab();
            View view = ((Activity)mTabLayout.getContext()).getLayoutInflater().inflate(R.layout.bottom_navigation_tab_item,null);
            tab.setCustomView(view);
            if (tab.getCustomView() != null){
                View tabView = (View) tab.getCustomView().getParent();
                tabView.setTag(i);
                tabView.setOnClickListener(v -> mRecorder.setClickEvent(ClickTraceRecorder.SUBMENU_ACTIVED_UNSELECTED));
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

        mTabLayout.addOnTabSelectedListener(mOnTabSelectedListener);
        mViewPager.setCurrentItem(0);
        mTabLayout.getTabAt(0).select();

        View view = mTabLayout.getTabAt(0).getCustomView();
        TextView tv = view.findViewById(R.id.tab_item_textview);
        tv.setTextColor(mTabLayout.getResources().getColor(R.color.bottom_navigation_menu_text_color));

        mDataPopupWindow = buildPopupWindow(dataItems,1);
        mAnalysisPopupWindow = buildPopupWindow(analysisItems,3);
    }

    private PopupWindow buildPopupWindow(String[] item,int offset){
        LayoutInflater inflater = ((Activity)mTabLayout.getContext()).getLayoutInflater();
        PopupWindow popupWindow = new PopupWindow(mTabLayout.getContext());
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.bottom_navigation_popup_window_main,null);
        View[] views = new View[item.length];

        for (int i = 0;i < views.length;i++){
            views[i] = inflater.inflate(R.layout.bottom_navigation_popup_window_item,rootView,false);
            ImageView icon = views[i].findViewById(R.id.bottom_navigation_popup_window_item_iv);
            TextView text = views[i].findViewById(R.id.bottom_navigation_popup_window_item_tv);
            text.setText(item[i]);

            int finalI = i;
            views[i].setOnClickListener(v -> {
                mRecorder.setClickEvent(ClickTraceRecorder.SUBMENU_ACTIVED_SELECTED);
                mRecorder.setLastActivedTabIndex(offset == 1 ? 1 : 2);
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
            if (mRecorder.getClickEvent() == ClickTraceRecorder.SUBMENU_ACTIVED_UNSELECTED){
                mRecorder.setClickEvent(ClickTraceRecorder.SUBMENU_IDLE);
                mTabLayout.getTabAt(mRecorder.getLastActivedTabIndex()).select();
            }
        });
        return popupWindow;
    }

    private void showPopupWindow(PopupWindow popupWindow,View anchor){
        popupWindow.showAsDropDown(anchor,0,
                -(popupWindow.getContentView().getMeasuredHeight() + anchor.getHeight() + 10), Gravity.LEFT);
    }

    private TabLayout.OnTabSelectedListener mOnTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            View view = tab.getCustomView();
            TextView tv = view.findViewById(R.id.tab_item_textview);
            ImageView iv = view.findViewById(R.id.tab_item_imageview);
            tv.setTextColor(mTabLayout.getResources().getColor(R.color.bottom_navigation_menu_text_color));

            if (mRecorder.getClickEvent() == ClickTraceRecorder.SUBMENU_IDLE)
                return;

            int index = tab.getPosition();
            if (index == 0){
                mRecorder.setClickEvent(ClickTraceRecorder.SUBMENU_IDLE);
                mRecorder.setLastActivedTabIndex(0);
                mViewPager.setCurrentItem(0,false);
            }else if (index == 1){
                showPopupWindow(mDataPopupWindow,tab.getCustomView());
            }else if (index == 2){
                showPopupWindow(mAnalysisPopupWindow,tab.getCustomView());
            }else{
                mRecorder.setClickEvent(ClickTraceRecorder.SUBMENU_IDLE);
                mRecorder.setLastActivedTabIndex(3);
                mViewPager.setCurrentItem(6,false);
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            View view = tab.getCustomView();
            TextView tv = view.findViewById(R.id.tab_item_textview);
            ImageView iv = view.findViewById(R.id.tab_item_imageview);

            tv.setTextColor(mTabLayout.getResources().getColor(R.color.bottom_navigation_menu_unselected_text_color));
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
    };

    /**
     *  A recorder used to record the user's click event.
     */
    class ClickTraceRecorder{
        static final int SUBMENU_IDLE = 0;
        static final int SUBMENU_ACTIVED_UNSELECTED = 1;
        static final int SUBMENU_ACTIVED_SELECTED = 2;

        int clickEvent = SUBMENU_IDLE;
        int lastActivedTabIndex = 0;

        public int getClickEvent() {
            return clickEvent;
        }

        public void setClickEvent(int clickEvent) {
            this.clickEvent = clickEvent;
        }

        public int getLastActivedTabIndex() {
            return lastActivedTabIndex;
        }

        public void setLastActivedTabIndex(int lastActivedTabIndex) {
            this.lastActivedTabIndex = lastActivedTabIndex;
        }
    }
}
