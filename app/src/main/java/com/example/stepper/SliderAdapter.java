package com.example.stepper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class SliderAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> lista;

    SliderAdapter(FragmentManager fm, List<Fragment> lista) {
        super(fm);
        this.lista = lista;
    }

    @Override
    public Fragment getItem(int position) {
        return lista.get(position);
    }

    @Override
    public int getCount() {
        return lista.size();
    }
}
