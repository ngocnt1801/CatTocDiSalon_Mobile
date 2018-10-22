package com.pro.salon.cattocdi.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.ReviewProfileActivity;
import com.pro.salon.cattocdi.adapter.ProfileTabAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Context context;
    private TextView tvPreview;
    private boolean isAtEditPage = true;
    private TextView tvTitle;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ProfileFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.detail_pager);
        ProfileTabAdapter adapter = new ProfileTabAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.detail_tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        tvTitle = view.findViewById(R.id.fg_profile_title_tv);
        tvPreview = view.findViewById(R.id.fg_profile_preview_tv);
        tvPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReviewProfileActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void changeToEdit(){
        isAtEditPage = true;
        tvPreview.setText("Preview");
        tvTitle.setText("Chỉnh sửa thông tin");

    }
    private void changeToPreiview(){
        isAtEditPage = false;
        tvPreview.setText("Sửa");
        tvTitle.setText("Xem trước thông tin");

    }
}
