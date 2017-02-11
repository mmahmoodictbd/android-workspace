package com.chumbok.dailypoetry;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.chumbok.dailypoetry.adapter.FeedListAdapter;
import com.chumbok.dailypoetry.adapter.ManuAdapterClass;
import com.chumbok.dailypoetry.listener.EndlessScrollListener;
import com.chumbok.dailypoetry.service.FeedService;
import com.chumbok.dailypoetry.task.InitDataBackgroundTask;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final Logger logger = Logger.getLogger();

    @ViewById(R.id.listView)
    ListView listView;

    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @ViewById(R.id.left_drawer)
    RelativeLayout mDrawerRelativeLayout;

    @ViewById(R.id.drawer_list)
    ListView mDrawerList;

    @Bean
    FeedService feedService;

    @Bean
    FeedItemList feedItemList;

    @Bean
    FeedListAdapter listAdapter;

    @Bean
    EndlessScrollListener endlessScrollListener;

    @Bean
    InitDataBackgroundTask initDataBackgroundTask;

    private Toolbar mToolbar;

    ActionBarDrawerToggle mDrawerToggle;
    String mTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @AfterViews
    void updateListView() {
        listView.setAdapter(listAdapter);
        listView.setOnScrollListener(endlessScrollListener);
        feedService.displayFeedItemsFromDB(1, 5);
        initMenu();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
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

    /**
     * Handling the touch event of app icon
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.help:
                showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Called whenever we call invalidateOptionsMenu()
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerRelativeLayout);

        if (drawerOpen) {
            return true;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    private void showHelp() {

    }

    protected void initMenu() {

        mTitle = (String) getTitle();

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close) {
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });


        mDrawerLayout.setDrawerListener(mDrawerToggle);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this.getBaseContext(),
                R.layout.row_of_drawer,
                this.getResources().getStringArray(R.array.menu_item)
        );

        ManuAdapterClass adClass = new ManuAdapterClass(this, this.getResources().getStringArray(R.array.menu_item),
                this.getResources().getStringArray(R.array.menu_drawables_list));
        mDrawerList.setAdapter(adClass);

        ActionBar actionBar = this.getActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Setting item click listener for the listview mDrawerList
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {


                /*String[] rivers = getResources().getStringArray(R.array.rivers);
                mTitle = rivers[position];
                RiverFragment rFragment = new RiverFragment();

                Bundle data = new Bundle();
                data.putInt("position", position);

                rFragment.setArguments(data);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, rFragment);
                ft.commit();*/

                mDrawerLayout.closeDrawer(mDrawerRelativeLayout);
            }
        });

    }

}
