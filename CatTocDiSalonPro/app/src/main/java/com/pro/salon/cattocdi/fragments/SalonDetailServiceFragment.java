package com.pro.salon.cattocdi.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
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
import com.pro.salon.cattocdi.models.Category;
import com.pro.salon.cattocdi.models.Service;
import com.pro.salon.cattocdi.service.ApiClient;
import com.pro.salon.cattocdi.service.SalonClient;
import com.pro.salon.cattocdi.utils.MyContants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class SalonDetailServiceFragment extends Fragment {

    private Button btManagePromotion, btManagerService, btManageWorkingHour;
    private boolean isPreview = false;
    private ServiceRecycleViewAdapter serviceAdapter;
    private RecyclerView serviceRecycleView;
    private List<Service> services;

    @SuppressLint("ValidFragment")
    public SalonDetailServiceFragment(boolean isPreview) {
        // Required empty public constructor
        this.isPreview = isPreview;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_salon_detail_service, container, false);
        // Inflate the layout for this fragment
        services = new ArrayList<Service>();
       // serviceAdapter = new ServiceRecycleViewAdapter(getContext(), MyContants.PROFILE_PAGE, categoryList);
        serviceRecycleView = view.findViewById(R.id.salon_service_recycle_view);
        serviceRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        serviceRecycleView.setAdapter(new ServiceRecycleViewAdapter(getContext(), MyContants.PROFILE_PAGE, services));
        if(!isPreview){
            ApiClient.getInstance()
                    .create(SalonClient.class)
                    .getAllInSalon("Bearer " + MyContants.TOKEN)
                    .enqueue(new Callback<List<Service>>() {
                        @Override
                        public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                            List<Service> serviceList = response.body();
                            serviceAdapter = new ServiceRecycleViewAdapter(getContext(),MyContants.PROFILE_PAGE, serviceList);
                            serviceRecycleView.setAdapter(serviceAdapter);

                        }

                        @Override
                        public void onFailure(Call<List<Service>> call, Throwable t) {

                        }
                    });

            serviceRecycleView.setAdapter(new ServiceRecycleViewAdapter(getActivity(), MyContants.PROFILE_PAGE));
        }else{
            serviceRecycleView.setAdapter(new ServiceRecycleViewAdapter(getActivity(), MyContants.PREVIEW_PAGE));
        }

        RecyclerView promotionRecycleView = view.findViewById(R.id.salon_promotion_recycle_view);
        promotionRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        promotionRecycleView.setAdapter(new SalonDetailPromotionRecycleView());

        ViewCompat.setNestedScrollingEnabled(serviceRecycleView, false);
        ViewCompat.setNestedScrollingEnabled(promotionRecycleView, false);


        btManagePromotion = view.findViewById(R.id.salon_promotion_manage_btn);
        btManagerService = view.findViewById(R.id.salon_service_manage_btn);
        btManageWorkingHour = view.findViewById(R.id.salon_working_hours_manage_btn);

        if(!isPreview){
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

        }else{
            btManagePromotion.setVisibility(View.GONE);
            btManagerService.setVisibility(View.GONE);
            btManageWorkingHour.setVisibility(View.GONE);
        }
        return view;
    }

}
