package com.kitatolong.arie.tes004;

import com.kitatolong.arie.tes004.model.navDrawerItem;
import com.kitatolong.arie.tes004.adapter.navDrawerListAdapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    //nav drawer tittle
    private CharSequence mDrawerTittle;

    //used to store app tittle
    private CharSequence mTittle;

    //slide menu item
    private String[] navMenuTitle;
    private TypedArray navMenuIcons;

    private ArrayList<navDrawerItem> navDrawerItems;
    private navDrawerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTittle = mDrawerTittle = getTitle();

        //load slide menu item
        navMenuTitle = getResources().getStringArray(R.array.nav_draw_items);

        //nav drawer item
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_draw_icon);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<navDrawerItem>();

        //home
        navDrawerItems.add(new navDrawerItem(navMenuTitle[0], navMenuIcons.getResourceId(0,-1)));
        //cari warung
        navDrawerItems.add(new navDrawerItem(navMenuTitle[1], navMenuIcons.getResourceId(1,-1)));
        //list warung
        navDrawerItems.add(new navDrawerItem(navMenuTitle[2], navMenuIcons.getResourceId(2,-1)));
        //
        navDrawerItems.add(new navDrawerItem(navMenuTitle[3], navMenuIcons.getResourceId(3,-1), true, "22"));
        //setting
        navDrawerItems.add(new navDrawerItem(navMenuTitle[4], navMenuIcons.getResourceId(4,-1)));
        //logout
        navDrawerItems.add(new navDrawerItem(navMenuTitle[5], navMenuIcons.getResourceId(5, -1), true, "50+"));

        //recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        //setting the nav drawer list adapter
        adapter = new navDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);

        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name){
            public void onDrawerClosed(View view){
                getSupportActionBar().setTitle(mTittle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView){
                getSupportActionBar().setTitle(mDrawerTittle);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState==null){
            displayView(0);
        }
    }

    private class SlideMenuClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);

        return super.onPrepareOptionsMenu(menu);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();

                break;
            case 1:
                fragment = new CariWarungFragment();
                break;
            case 2:
                fragment = new ListWarungFragment();
                break;
            case 3:
                fragment = new KomentarFragment();
                break;
            case 4:
                fragment = new SettingFragment();
                break;
            case 5:
                fragment = new WhatsHotFragment();
                break;

            default:
                break;
        }
        if (fragment!=null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitle[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    public void setTitle(CharSequence title){
        mTittle=title;
        getSupportActionBar().setTitle(mTittle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}