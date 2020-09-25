package com.elanilsondejesus.instagram.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.adapter.AdapterFeed;
import com.elanilsondejesus.instagram.helper.ConfiguracaoFirebase;
import com.elanilsondejesus.instagram.helper.UsuarioFirebase;
import com.elanilsondejesus.instagram.model.Feed;
import com.elanilsondejesus.instagram.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FreedFragment extends Fragment {
    private RecyclerView recyclerViewFeed;
    private AdapterFeed adapterFeed;
    private List<Feed> feedList = new ArrayList<>();
    private ValueEventListener valueEventListenerFeed;
    private DatabaseReference feedREf;
    private String idUsuarioLogado;




    public FreedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_freed, container, false);

        //inicializar componentes
        adapterFeed = new AdapterFeed(feedList,getActivity());
        recyclerViewFeed = view.findViewById(R.id.recycleviewFeed);
        //configuraçoes iniciais
        idUsuarioLogado = UsuarioFirebase.getIdentificadorUsuario();
        feedREf = ConfiguracaoFirebase.getFirebase().child("feed")
                .child(idUsuarioLogado);
        //configurar recyclevieww
        recyclerViewFeed.setHasFixedSize(true);
        recyclerViewFeed.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewFeed.setAdapter(adapterFeed);


        return view;
    }
    private void listarFeed(){

        valueEventListenerFeed = feedREf.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                feedList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    feedList.add(ds.getValue(Feed.class));

                }
                Collections.reverse(feedList);
                adapterFeed.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        listarFeed();
    }

    @Override
    public void onStop() {
        super.onStop();
        feedREf.removeEventListener(valueEventListenerFeed);

    }
}
