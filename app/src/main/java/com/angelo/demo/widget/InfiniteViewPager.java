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

    /**
     * An {@link android.support.v4.view.ViewPager.OnPageChangeListener} that may be set externally.
     * Internally, this ViewPager always uses
     * {@link com.angelo.demo.widget.InfiniteViewPager.PageChangeListener}
     * while ensuring that the external listener is called appropriately.
     */
    private OnPageChangeListener externalOnPageChangeListener = null;

    public InfiniteViewPager(Context context) {
        super(context);
        setActualOnPageChangeListener(new PageChangeListener(this));
    }

    public InfiniteViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setActualOnPageChangeListener(new PageChangeListener(this));
    }

    private void setActualOnPageChangeListener(OnPageChangeListener listener) {
        super.setOnPageChangeListener(listener);
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.externalOnPageChangeListener = listener;
    }

    private OnPageChangeListener getExternalOnPageChangeListener() {
        return this.externalOnPageChangeListener;
    }

    /**
     * An {@link android.support.v4.view.ViewPager.OnPageChangeListener} that the
     * {@link com.angelo.demo.widget.InfiniteViewPager} internally uses to cycle its fragments.
     */
    private static class PageChangeListener implements OnPageChangeListener {
        /**
         * The {@link com.angelo.demo.widget.InfiniteViewPager} for which
         * this listener has been set.
         */
        private final InfiniteViewPager viewPager;

        public PageChangeListener(final InfiniteViewPager viewPager) {
            this.viewPager = viewPager;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            final OnPageChangeListener externalOnPageChangeListener =
                    viewPager.getExternalOnPageChangeListener();
            if (externalOnPageChangeListener != null) {
                externalOnPageChangeListener.onPageScrolled(
                        position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            final OnPageChangeListener externalOnPageChangeListener =
                    viewPager.getExternalOnPageChangeListener();
            if (externalOnPageChangeListener != null) {
                externalOnPageChangeListener.onPageSelected(position);
            }

            InfiniteViewPagerAdapter adapter = (InfiniteViewPagerAdapter) viewPager.getAdapter();
            List<Fragment> pagerFragments = adapter.getPagerFragments();
            // Ensure that cycling only occurs if there are 3 or more fragments.
            if (pagerFragments.size() > 2) {
                final int cycleResult = cyclePagerFragments(pagerFragments, position);
                if (cycleResult != 0) {
                    adapter.setPagerFragments(pagerFragments);
                    adapter.notifyDataSetChanged();

                    // Turn off the actual and external OnPageChangeListeners, so that
                    // this function does not unnecessarily get called again when
                    // setting the current item.
                    viewPager.setOnPageChangeListener(null);
                    viewPager.setActualOnPageChangeListener(null);
                    viewPager.setCurrentItem(position + cycleResult, false);
                    viewPager.setOnPageChangeListener(externalOnPageChangeListener);
                    viewPager.setActualOnPageChangeListener(this);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            final OnPageChangeListener externalOnPageChangeListener =
                    viewPager.getExternalOnPageChangeListener();
            if (externalOnPageChangeListener != null) {
                externalOnPageChangeListener.onPageScrollStateChanged(state);
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
