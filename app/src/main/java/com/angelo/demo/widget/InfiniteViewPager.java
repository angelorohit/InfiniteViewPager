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

package com.angelo.demo.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.angelo.demo.adapter.InfiniteViewPagerAdapter;

import java.util.List;

/**
 * A {@link android.support.v4.view.ViewPager} that supports infinite scrolling of fragments.
 * The pager cycles through its fragments in a circular fashion.
 * Cycling of fragments will only occur if the view pager contains more than two fragments.
 *
 * @author angelo
 */
public class InfiniteViewPager extends ViewPager {
    public InfiniteViewPager(Context context) {
        super(context);
        setOnPageChangeListener(new PageChangeListener(this));
    }

    public InfiniteViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnPageChangeListener(new PageChangeListener(this));
    }

    private static class PageChangeListener extends SimpleOnPageChangeListener {
        // The {@link ViewPager} for which this listener has been set.
        private final ViewPager viewPager;

        public PageChangeListener(final ViewPager viewPager) {
            this.viewPager = viewPager;
        }

        @Override
        public void onPageSelected(int position) {
            InfiniteViewPagerAdapter adapter = (InfiniteViewPagerAdapter) viewPager.getAdapter();
            List<Fragment> pagerFragments = adapter.getPagerFragments();
            if (pagerFragments.size() > 2) {
                final int cycleResult = cyclePagerFragments(pagerFragments, position);
                if (cycleResult != 0) {
                    adapter.setPagerFragments(pagerFragments);
                    adapter.notifyDataSetChanged();
                    viewPager.setOnPageChangeListener(null);
                    viewPager.setCurrentItem(position + cycleResult, false);
                    viewPager.setOnPageChangeListener(this);
                }
            }
        }
    }

    /**
     * Cycles through the fragments in the given pagerFragments list,
     * depending on the current position that is passed in.
     *
     * @param pagerFragments A {@link java.util.List} containing
     *                      {@link android.support.v4.app.Fragment} to be cycled.
     * @param position       The position of the currently selected item in the pager fragments.
     * @return -1 if the viewpager items were cycled to the right,
     * 1 if the viewpager items were cycled to the left,
     * 0 if the viewpager items were not cycled.
     */
    private static int cyclePagerFragments(List<Fragment> pagerFragments, final int position) {
        final int lastPosition = pagerFragments.size() - 1;
        if (position == lastPosition) {
            pagerFragments.add(pagerFragments.remove(0));
            return -1;
        } else if (position == 0) {
            pagerFragments.add(0, pagerFragments.remove(lastPosition));
            return 1;
        }

        return 0;
    }
}
