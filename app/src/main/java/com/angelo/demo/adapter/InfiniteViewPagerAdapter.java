//  InfiniteViewPager - An infinitely scrolling view pager.
//  Copyright (C) 2014  Angelo Rohit Joseph Pulikotil
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package com.angelo.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * The adapter for {@link com.angelo.demo.widget.InfiniteViewPager}
 * @author angelo
 */
public class InfiniteViewPagerAdapter extends FragmentStatePagerAdapter {

    /**
     *  A list of {@link android.support.v4.app.Fragment} which serves as the data for this
     *  adapter.
     */
    private List<Fragment> pagerFragments;

    public InfiniteViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public List<Fragment> getPagerFragments() {
        return pagerFragments;
    }

    public void setPagerFragments(final List<Fragment> pagerFragments) {
        this.pagerFragments = pagerFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return (pagerFragments != null && pagerFragments.size() > position) ?
                pagerFragments.get(position) : null;
    }

    @Override
    public int getCount() {
        return (pagerFragments != null) ? pagerFragments.size() : 0;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
