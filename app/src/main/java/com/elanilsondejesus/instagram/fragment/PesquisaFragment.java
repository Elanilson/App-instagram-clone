package com.elanilsondejesus.instagram.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;

import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.activity.PerfilAmigoActivity;
import com.elanilsondejesus.instagram.adapter.AdapterPesquisa;
import com.elanilsondejesus.instagram.helper.ConfiguracaoFirebase;
import com.elanilsondejesus.instagram.helper.RecyclerItemClickListener;
import com.elanilsondejesus.instagram.helper.UsuarioFirebase;
import com.elanilsondejesus.instagram.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PesquisaFragment extends Fragment {
    private SearchView searchViewPesquisa;
    private RecyclerView recyclerViewPesquisa;
    private List<Usuario>listaUsuario;
    private DatabaseReference usuarioRef;
    private AdapterPesquisa adapterPesquisa;
    private String idUsuarioLogado;


    public PesquisaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pesquisa, container, false);

        recyclerViewPesquisa = view.findViewById(R.id.recycleviewPesquisa);
        searchViewPesquisa = view.findViewById(R.id.seaviewPesquisa);

        //configurações iniciais
        listaUsuario = new ArrayList<>();
        adapterPesquisa = new AdapterPesquisa(listaUsuario,getActivity());
        usuarioRef = ConfiguracaoFirebase.getFirebase().child("usuarios");
        idUsuarioLogado = UsuarioFirebase.getIdentificadorUsuario();

        //configurar Recyclewview
        recyclerViewPesquisa.setHasFixedSize(true);
        recyclerViewPesquisa.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewPesquisa.setAdapter(adapterPesquisa);


        //configurar    evendo clique
        recyclerViewPesquisa.addOnItemTouchListener( new RecyclerItemClickListener(
                getActivity(), recyclerViewPesquisa, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Usuario usuarioSelelcionado =listaUsuario.get(position);
                Intent intent = new Intent(getActivity(), PerfilAmigoActivity.class);
                intent.putExtra("usuarioSelecionado",usuarioSelelcionado);
                startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }
        ));


        //configurar Searchaview
        searchViewPesquisa.setQueryHint("Buscar usuários");
        searchViewPesquisa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String textoDigitado =newText.toUpperCase();
                pesquisarUsuario(textoDigitado);


                return true;
            }
        });

    return view;
    }
    public void pesquisarUsuario(String texto){

        //limpar lista
        listaUsuario.clear();
        //pesquisar usuario caso tenha texto na pesquisa
        if(texto.length()>=2){
            Query query =usuarioRef.orderByChild("nome").startAt(texto).endAt(texto+"\uf8ff");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //limpar lista
                    listaUsuario.clear();
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                       //verificar se é  usuario logado e remover da lista
                        Usuario usuario = ds.getValue(Usuario.class);
                        if(idUsuarioLogado.equalsIgnoreCase(usuario.getId())){
                            continue;
                        }
                        //adicionar usuario na lista
                        listaUsuario.add(usuario);


                    }
                    adapterPesquisa.notifyDataSetChanged();

//                    int total= listaUsuario.size();
//
//                    Log.i("TotalUsuarios","Total: "+total);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }


    }

}
