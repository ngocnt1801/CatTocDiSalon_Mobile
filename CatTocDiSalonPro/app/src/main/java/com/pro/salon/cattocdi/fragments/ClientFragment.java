package com.pro.salon.cattocdi.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.adapter.ContactAdapter;
import com.pro.salon.cattocdi.utils.MyContants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientFragment extends Fragment {

    private RecyclerView rvContactList;

    public ClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_client, container, false);
        rvContactList = view.findViewById(R.id.fg_client_contact_rv);
        rvContactList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
       //rvContactList.setAdapter(new ContactAdapter(getActivity(), MyContants.CUSTOMERS));
        return view;
    }


}
