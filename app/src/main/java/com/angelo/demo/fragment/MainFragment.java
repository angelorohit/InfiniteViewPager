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

package com.angelo.demo.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angelo.demo.R;
import com.angelo.demo.adapter.InfiniteViewPagerAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * The main fragment that contains the {@link com.angelo.demo.widget.InfiniteViewPager}.
 * @author angelo
 */
public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        List<Fragment> pagerFragments = createPagerFragments();
        InfiniteViewPagerAdapter adapter = new InfiniteViewPagerAdapter(
                                    getActivity().getSupportFragmentManager());
        adapter.setPagerFragments(pagerFragments);
        viewPager.setAdapter(adapter);
    }

    private List<Fragment> createPagerFragments() {
        List<Fragment> dataList = new LinkedList<Fragment>();
        final Resources res = getActivity().getResources();
        final String[] dataArr = res.getStringArray(R.array.view_pager_data);

        for (final String dataStr : dataArr) {
            TemplateFragment fragment = new TemplateFragment();
            fragment.setTitle(dataStr);
            dataList.add(fragment);
        }

        return dataList;
    }
}
