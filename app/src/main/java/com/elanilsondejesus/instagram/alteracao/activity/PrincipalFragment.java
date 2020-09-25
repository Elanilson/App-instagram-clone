package com.elanilsondejesus.instagram.alteracao.activity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elanilsondejesus.instagram.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipalFragment extends Fragment {
    SmartTabLayout smartTabLayout;
    ViewPager viewPager;



    public PrincipalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_principal, container, false);

        smartTabLayout = view.findViewById(R.id.viewpagertab);
        viewPager = view.findViewById(R.id.viewpager);


        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getFragmentManager(),
                FragmentPagerItems.with(getActivity())
                        .add("imc", Tela3Fragment.class)

                        .create()

        );
        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);
//        startActivity( new Intent(getActivity(), Tela.class));


        return view;
    }

}
