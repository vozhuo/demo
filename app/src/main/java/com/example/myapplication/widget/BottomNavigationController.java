package com.example.myapplication.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

/**
 *  A controller used to associate ViewPager with TabLayout.This class could
 *  manage the tabs to achieve the feature of bottom navigation submenu.
 *
 * @author Chen Yu
 * @since  2019.10.30
 * @version 1.0.0
 */

public class BottomNavigationController {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private ClickTraceRecorder mRecorder;

    private int mBottomMenuResId;
    private MenuItem[] mFirstMenu;                   //一级菜单
    private SparseArray<SubMenu> mSubMenuMap;       //K:一级菜单索引；V:二级菜单实例
    private SparseArray<PopupWindow> mSubMenuWindow;//K:一级菜单索引；V:二级菜单窗口
    private List<String> mFragmentTitleList;        //各个fragment标题集合，用于toolbar标题的设置
    private boolean isPopupWindowShowing = false;
    private PopupWindow mShowingPopupWindow = null;

    private int mBottomTabResId;
    private int mFirstSelectedTabIndex;

    private BottomNavigationController(TabLayout tabLayout){
        this.mRecorder = new ClickTraceRecorder();
        this.mTabLayout = tabLayout;
        this.mBottomTabResId = R.layout.bottom_navigation_tab_item;
        this.mFirstSelectedTabIndex = 0;
        this.mBottomMenuResId = R.menu.main_activity_bottom_menu;
    }

    public static BottomNavigationController use(TabLayout tabLayout){
        if (tabLayout == null)
            throw new NullPointerException("TabLayout can not be null");

        BottomNavigationController controller = new BottomNavigationController(tabLayout);
        tabLayout.setMotionEventSplittingEnabled(false);
        return controller;
    }

    public BottomNavigationController menuResourceId(int menuResId){
        this.mBottomMenuResId = menuResId;
        return this;
    }

    public BottomNavigationController bottomTabItemLayout(int resId){
        this.mBottomTabResId = resId;
        return this;
    }

    @Deprecated
    public BottomNavigationController firstSelectedItem(int index){
        this.mFirstSelectedTabIndex = index;
        return this;
    }

    public BottomNavigationController toolbar(Toolbar toolbar){
        this.mToolbar = toolbar;
        return this;
    }

    public void associateWith(ViewPager viewPager){
        if (viewPager.getAdapter() == null)
            throw new NullPointerException("the ViewPager should have an adapter.");
        this.mViewPager = viewPager;
        parseMenu();
        setupTabView();
    }

    private void setupTabView(){
        for (int i = 0;i < mFirstMenu.length;i++){
            TabLayout.Tab tab = mTabLayout.newTab();
            View view = ((Activity)mTabLayout.getContext()).getLayoutInflater().inflate(mBottomTabResId,null);
            ImageView iv = view.findViewById(R.id.tab_item_imageview);
            TextView tv = view.findViewById(R.id.tab_item_textview);
            tv.setText(mFirstMenu[i].getTitle());
            iv.setImageDrawable(mFirstMenu[i].getIcon());

            tab.setCustomView(view);
            if (tab.getCustomView() != null){
                View tabView = (View) tab.getCustomView().getParent();
                tabView.setTag(i);
                int finalI = i;
                tabView.setOnClickListener(v -> {
                    //if (mFirstMenu[finalI].hasSubMenu()) {
                        mRecorder.setClickEvent(ClickTraceRecorder.SUBMENU_ACTIVED_UNSELECTED);
                    //}
                });
            }
            mTabLayout.addTab(tab);
        }

        mTabLayout.addOnTabSelectedListener(mOnTabSelectedListener);

        mViewPager.setCurrentItem(mFirstSelectedTabIndex);
        mTabLayout.getTabAt(mFirstSelectedTabIndex).select();
        applyMenuItemSelected(mTabLayout.getTabAt(mFirstSelectedTabIndex));
        applyToolbarChanged(mFirstSelectedTabIndex);
//        View view = mTabLayout.getTabAt(mFirstSelectedTabIndex).getCustomView();
//        TextView tv = view.findViewById(R.id.tab_item_textview);
//        tv.setTextColor(mTabLayout.getResources().getColor(R.color.bottom_navigation_menu_text_color));

        mSubMenuWindow = new SparseArray<>();
        for (int i = 0;i < mSubMenuMap.size();i++){
            int keyIndex = mSubMenuMap.keyAt(i);
            //calculate menu offset
            int offset = 0;
            for (int j = 0;j < keyIndex;j++){
                if (mFirstMenu[j].hasSubMenu()){
                    offset += mFirstMenu[j].getSubMenu().size();
                }else{
                    offset++;
                }
            }

            mSubMenuWindow.append(keyIndex,buildPopupWindow(mSubMenuMap.get(keyIndex),keyIndex,offset));
        }
    }

    private void parseMenu(){
        Activity activity = (Activity) mTabLayout.getContext();
        MenuInflater inflater = activity.getMenuInflater();

        Menu menu = new MenuBuilder(activity.getApplicationContext());
        inflater.inflate(mBottomMenuResId,menu);

        int totalValidItemCount = 0;
        mFirstMenu = new MenuItem[menu.size()];
        mSubMenuMap = new SparseArray<>();
        mFragmentTitleList = new ArrayList<>();
        for (int i = 0;i < menu.size();i++){
            mFirstMenu[i] = menu.getItem(i);
            if (mFirstMenu[i].hasSubMenu()){
                SubMenu subMenu = mFirstMenu[i].getSubMenu();
                int subMenuSize = subMenu.size();
                int subIndex = 0;
                while (subIndex < subMenuSize){
                    mFragmentTitleList.add(subMenu.getItem(subIndex).getTitle().toString());
                    subIndex++;
                }

                mSubMenuMap.append(i,subMenu);
                totalValidItemCount += subMenuSize;
            }else{
                mFragmentTitleList.add(mFirstMenu[i].getTitle().toString());
                totalValidItemCount++;
            }
        }

        if (totalValidItemCount != Objects.requireNonNull(mViewPager.getAdapter()).getCount()){
            throw new IllegalStateException("The menu items does not matched the number of Fragment");
        }
    }

    private PopupWindow buildPopupWindow(SubMenu subMenu,int firstMenuIndex,int menuOffset){
        LayoutInflater inflater = ((Activity)mTabLayout.getContext()).getLayoutInflater();
        PopupWindow popupWindow = new PopupWindow(mTabLayout.getContext());
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.bottom_navigation_popup_window_main,null);
        View[] views = new View[subMenu.size()];

        for (int i = 0;i < views.length;i++){
            views[i] = inflater.inflate(R.layout.bottom_navigation_popup_window_item,rootView,false);
            ImageView icon = views[i].findViewById(R.id.bottom_navigation_popup_window_item_iv);
            TextView text = views[i].findViewById(R.id.bottom_navigation_popup_window_item_tv);
            icon.setImageDrawable(subMenu.getItem(i).getIcon());
            text.setText(subMenu.getItem(i).getTitle());

            int finalI = i;
            views[i].setOnClickListener(v -> {
                applyMenuItemUnSelected(mTabLayout.getTabAt(mRecorder.getLastActivedTabIndex()));

                mRecorder.setClickEvent(ClickTraceRecorder.SUBMENU_ACTIVED_SELECTED);
                mRecorder.setLastActivedTabIndex(firstMenuIndex);
                mViewPager.setCurrentItem(finalI + menuOffset,false);
                applyToolbarChanged(finalI + menuOffset);
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
//                View view = mTabLayout.getTabAt(mRecorder.getLastActivedTabIndex()).getCustomView();
//                TextView tv = view.findViewById(R.id.tab_item_textview);
//                ImageView iv = view.findViewById(R.id.tab_item_imageview);
//                tv.setTextColor(mTabLayout.getResources().getColor(R.color.bottom_navigation_menu_text_color));
                mRecorder.setClickEvent(ClickTraceRecorder.SUBMENU_IDLE);
                mTabLayout.getTabAt(mRecorder.getLastActivedTabIndex()).select();
            }
            else if (mRecorder.getClickEvent() == ClickTraceRecorder.SUBMENU_ACTIVED_SELECTED){
                mRecorder.setClickEvent(ClickTraceRecorder.SUBMENU_IDLE);
                int selectedIndex = mRecorder.getLastActivedTabIndex();
                applyMenuItemSelected(mTabLayout.getTabAt(selectedIndex));
            }

            isPopupWindowShowing = false;
        });
        return popupWindow;
    }

    private void showPopupWindow(PopupWindow popupWindow,View anchor){
        isPopupWindowShowing = true;
        mShowingPopupWindow = popupWindow;
        popupWindow.showAsDropDown(anchor,0,
                -(popupWindow.getContentView().getMeasuredHeight() + anchor.getHeight() + 10), Gravity.LEFT);
    }

    private void applyMenuItemSelected(TabLayout.Tab tab){
        View view = tab.getCustomView();
        if (view == null)
            throw new NullPointerException("Tab custom view is null.");

        TextView tv = view.findViewById(R.id.tab_item_textview);
        ImageView iv = view.findViewById(R.id.tab_item_imageview);
        tv.setTextColor(mTabLayout.getResources().getColor(R.color.bottom_navigation_menu_text_color));
        iv.setSelected(true);
    }

    private void applyMenuItemUnSelected(TabLayout.Tab tab){
        View view = tab.getCustomView();
        if (view == null)
            throw new NullPointerException("Tab custom view is null.");

        TextView tv = view.findViewById(R.id.tab_item_textview);
        ImageView iv = view.findViewById(R.id.tab_item_imageview);
        tv.setTextColor(mTabLayout.getResources().getColor(R.color.bottom_navigation_menu_unselected_text_color));
        iv.setSelected(false);
    }

    private void applyToolbarChanged(int fragmentIndex){
        if (mToolbar != null){
            mToolbar.setTitle(mFragmentTitleList.get(fragmentIndex));
        }
    }

    private TabLayout.OnTabSelectedListener mOnTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            applyMenuItemSelected(tab);

            if (mRecorder.getClickEvent() == ClickTraceRecorder.SUBMENU_IDLE)
                return;

            if (isPopupWindowShowing){
                mShowingPopupWindow.dismiss();
            }

            int index = tab.getPosition();
            if (!mFirstMenu[index].hasSubMenu()){
                int offsetIndex = 0;
                for (int i = 0;i < index;i++){
                    if (mFirstMenu[i].hasSubMenu()){
                        offsetIndex += mFirstMenu[i].getSubMenu().size();
                    }else{
                        offsetIndex++;
                    }
                }

                mRecorder.setClickEvent(ClickTraceRecorder.SUBMENU_IDLE);
                mRecorder.setLastActivedTabIndex(index);
                mViewPager.setCurrentItem(offsetIndex,false);
                applyToolbarChanged(offsetIndex);
                //tv.setTextColor(mTabLayout.getResources().getColor(R.color.bottom_navigation_menu_text_color));
            }else {
                showPopupWindow(mSubMenuWindow.get(index),tab.getCustomView());
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            applyMenuItemUnSelected(tab);
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            if (mRecorder.getClickEvent() == ClickTraceRecorder.SUBMENU_IDLE)
                return;

            int index = tab.getPosition();
            if (mFirstMenu[index].hasSubMenu()){
                showPopupWindow(mSubMenuWindow.get(index),tab.getCustomView());
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

        int getClickEvent() {
            return clickEvent;
        }

        void setClickEvent(int clickEvent) {
            this.clickEvent = clickEvent;
        }

        int getLastActivedTabIndex() {
            return lastActivedTabIndex;
        }

        void setLastActivedTabIndex(int lastActivedTabIndex) {
            this.lastActivedTabIndex = lastActivedTabIndex;
        }
    }
}
