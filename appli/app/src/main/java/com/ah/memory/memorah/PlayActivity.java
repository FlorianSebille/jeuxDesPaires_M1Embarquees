package com.ah.memory.memorah;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.LinkagePager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ah.memory.memorah.view.DataDemoView;

import java.util.ArrayList;

import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.LinkagePagerContainer;
import me.crosswall.lib.coverflow.core.PageItemClickListener;

/**
 * Created by yuweichen on 16/5/3.
 */
public class PlayActivity extends AppCompatActivity{
    private LinkagePagerContainer customPagerContainer;
    private LinkagePager pager;
    private int parallaxHeight;
    private View tab;
    private FloatingActionButton floatingActionButton;
    private SeekBar seekBar;

    private static int[] imgs = { R.raw.level1, R.raw.level2, R.raw.level3 };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_collapsing);

        parallaxHeight = getResources().getDimensionPixelSize(R.dimen.cover_pager_height) - getResources().getDimensionPixelSize(R.dimen.tab_height);

        customPagerContainer = (LinkagePagerContainer) findViewById(R.id.pager_container);

        customPagerContainer.setPageItemClickListener(new PageItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                pager.setCurrentItem(position);
            }
        });

        tab = findViewById(R.id.tab);

        final LinkagePager cover = customPagerContainer.getViewPager();

        customPagerContainer.setOverlapEnabled(true);

        PagerAdapter coverAdapter = new MyPagerAdapter();
        cover.setAdapter(coverAdapter);
        cover.setOffscreenPageLimit(5);

        new CoverFlow.Builder()
                .withLinkage(cover)
                .pagerMargin(0f)
                .scale(0.3f)
                .spaceSize(0f)
                .build();


        pager = (LinkagePager) findViewById(R.id.pager);

        final MyListPagerAdapter adapter = new MyListPagerAdapter();

        pager.setOffscreenPageLimit(5);
        pager.setAdapter(adapter);

        cover.setLinkagePager(pager);
        pager.setLinkagePager(cover);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                System.err.println("Current difficulty:" + ((SeekBar)pager.getChildAt(pager.getCurrentItem()).findViewById(R.id.seekBarDifficulty)).getProgress());
                Intent intent = new Intent(PlayActivity.this, GameActivity.class);
                System.err.println(adapter.getDifficulty());
                //intent.putExtra("difficulty",seekBar.getProgress());
                //startActivity(intent);
            }
        });
    }

    class MyListPagerAdapter extends PagerAdapter{
        private DataDemoView view;

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            view = new DataDemoView(PlayActivity.this, position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public int getDifficulty(){ return view.getDifficulty();}

    }

    private class MyPagerAdapter extends PagerAdapter {
        private ArrayList<MyListPagerAdapter> listPagerAdapterArrayList;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView view = new TextView(PlayActivity.this);
            view.setGravity(Gravity.CENTER);
            view.setBackgroundResource(imgs[position]);
            //listPagerAdapterArrayList.add(ne)
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

    }


}