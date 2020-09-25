package com.elanilsondejesus.instagram.helper;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConfiguracaoFirebase {
    private static DatabaseReference referenciaDatabase;
    private static FirebaseAuth refenciaAutenticacao;
    private static StorageReference storage;

    // retornar a instancia  do FirebaseAuth
    public static  FirebaseAuth getRefenciaAutenticacao(){
        if(refenciaAutenticacao==null){
            refenciaAutenticacao= FirebaseAuth.getInstance();
        }
        return  refenciaAutenticacao;
    }
    //retorna referencia do database
    public  static  DatabaseReference getFirebase(){
        if(referenciaDatabase==null){
            referenciaDatabase=FirebaseDatabase.getInstance().getReference();
        }
        return referenciaDatabase;
    }
    //retorna instacia do FirebaseStorage
    public static StorageReference getstorageReference (){
        if(storage ==null){
            storage= FirebaseStorage.getInstance().getReference();
        }
        return storage;

    }
}
