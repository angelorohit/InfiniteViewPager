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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angelo.demo.R;

/**
 * A Template Fragment that will be used to populate the
 * {@link com.angelo.demo.widget.InfiniteViewPager}
 * @author angelo
 */
public class TemplateFragment extends Fragment {

    // The title to be displayed in the center of this fragment.
    private String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_template, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txtView = (TextView) view.findViewById(R.id.txtView);
        txtView.setText(title);
    }

    public void setTitle(final String title) {
        this.title = title;
    }
}
