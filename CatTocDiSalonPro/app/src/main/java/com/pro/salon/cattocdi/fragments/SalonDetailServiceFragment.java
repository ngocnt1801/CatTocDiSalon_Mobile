package com.pro.salon.cattocdi.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pro.salon.cattocdi.MainActivity;
import com.pro.salon.cattocdi.PromotionActivity;
import com.pro.salon.cattocdi.ServiceActivity;
import com.pro.salon.cattocdi.WorkingHoursActivity;
import com.pro.salon.cattocdi.adapter.SalonDetailPromotionRecycleView;
import com.pro.salon.cattocdi.adapter.ServiceRecycleViewAdapter;
import com.pro.salon.cattocdi.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SalonDetailServiceFragment extends Fragment {

    private Button btManagePromotion, btManagerService, btManageWorkingHour;

    public SalonDetailServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_salon_detail_service, container, false);
        // Inflate the layout for this fragment
        RecyclerView serviceRecycleView = view.findViewById(R.id.salon_service_recycle_view);
        serviceRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        serviceRecycleView.setAdapter(new ServiceRecycleViewAdapter(getActivity()));

        RecyclerView promotionRecycleView = view.findViewById(R.id.salon_promotion_recycle_view);
        promotionRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        promotionRecycleView.setAdapter(new SalonDetailPromotionRecycleView());

        btManagePromotion = view.findViewById(R.id.salon_promotion_manage_btn);
        btManagerService = view.findViewById(R.id.salon_service_manage_btn);
        btManageWorkingHour = view.findViewById(R.id.salon_working_hours_manage_btn);

        btManagePromotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PromotionActivity.class);
                getActivity().startActivity(intent);
            }
        });

        btManagerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ServiceActivity.class);
                getActivity().startActivity(intent);
            }
        });

        btManageWorkingHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WorkingHoursActivity.class);
                getActivity().startActivity(intent);
            }
        });


        return view;
    }

}
