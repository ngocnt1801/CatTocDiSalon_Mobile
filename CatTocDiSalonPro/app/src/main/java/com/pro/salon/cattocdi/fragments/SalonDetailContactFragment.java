package com.pro.salon.cattocdi.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pro.salon.cattocdi.InformationActivity;
import com.pro.salon.cattocdi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalonDetailContactFragment extends Fragment implements OnMapReadyCallback{
    private GoogleMapOptions options = new GoogleMapOptions().liteMode(true);
    private GoogleMap map;
    private LatLng latLng;
    private SupportMapFragment supportMapFragment;
    private Button btManageInfo;

    public SalonDetailContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_salon_detail_contact, container, false);
        latLng = new LatLng(10.8466881,106.6329596);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.salon_detail_map);
        supportMapFragment.getMapAsync(this);

        btManageInfo = view.findViewById(R.id.salon_info_manage_btn);
        btManageInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InformationActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
        map.addMarker(markerOptions);
    }
}
