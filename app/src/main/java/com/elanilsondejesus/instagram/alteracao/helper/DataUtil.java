package com.elanilsondejesus.instagram.alteracao.helper;

import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DataUtil {
        TextView diaatual;
    public static String dataAtual(String diaEscolhido){

       long date= System.currentTimeMillis();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy ");
        String dataString =simpleDateFormat.format(date);

        String vv="";
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            Date d1 = df.parse(diaEscolhido);
            System.out.println(d1);
            Date d2 = df.parse(dataString);
            System.out.println(d2);
            long dt = (d1.getTime() - d2.getTime()) + 3600000; // 1 hora para compensar horário de verão
            long r = dt / 86400000L; // passaram-se 67111 dias
            r +=-1;
            vv = String.valueOf(r);

        } catch (ParseException ex) {

        }
        return vv;



    }
    public static String mesAnoDataescolhida(String data){
        String retornaData [] =data.split("/");
        String dia=retornaData[0];
        String mes=retornaData[1];
        String ano=retornaData[2];

        String mesAno = mes+ano;

        return mesAno;
    }

    public static String Testt () {
        String vv="";
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            Date d1 = df.parse("15/08/2020");
            System.out.println(d1);
            Date d2 = df.parse("15/05/2020");
            System.out.println(d2);
            long dt = (d1.getTime() - d2.getTime()) + 3600000; // 1 hora para compensar horário de verão
            long r = dt / 86400000L; // passaram-se 67111 dias
            r +=-1;
             vv = String.valueOf(r);

        } catch (ParseException ex) {

        }
        return vv;

    }

}
