package com.example.stepper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.stepper.common.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.viewPagerIndicator)
    ViewPagerIndicator viewPagerIndicator;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_next)
    Button btnNext;

    List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        getData();

        List<Fragment> listaFragment = new ArrayList<>();

        int count = 1;

        for (String value : data) {
            switch (count) {
                case 2:
                    SliderCardFragment2 cardFragment2 = new SliderCardFragment2();
                    cardFragment2.setTitle(value);
                    listaFragment.add(cardFragment2);
                    break;
                default:
                    SliderCardFragment cardFragment = new SliderCardFragment();
                    cardFragment.setTitle(value);
                    listaFragment.add(cardFragment);
                    break;
            }

            count++;
        }

        SliderAdapter adapterSlider = new SliderAdapter(getSupportFragmentManager(), listaFragment);
        viewPager.setAdapter(adapterSlider);
        viewPagerIndicator.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // EMPTY
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        btnBack.setVisibility(View.INVISIBLE);
                        btnNext.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        btnBack.setVisibility(View.VISIBLE);
                        btnNext.setVisibility(View.VISIBLE);
                        break;
                    default:

                        if (position == data.size() - 1) {
                            btnBack.setVisibility(View.VISIBLE);
                            btnNext.setVisibility(View.INVISIBLE);
                        } else {
                            btnBack.setVisibility(View.VISIBLE);
                            btnNext.setVisibility(View.VISIBLE);
                        }

                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // EMPTY
            }
        });

        btnBack.setVisibility(View.INVISIBLE);
    }

    private void getData() {
        data = new ArrayList<>();
        data.add("Tutorial 1");
        data.add("Tutorial 2");
        data.add("Tutorial 3");
        data.add("Tutorial 4");
        data.add("Tutorial 5");
    }

    @OnClick(R.id.btn_back)
    public void backPage() {
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
        }
    }

    @OnClick(R.id.btn_next)
    public void nextPage() {
        if (viewPager.getCurrentItem() != data.size()) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        }
    }
}