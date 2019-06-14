package com.example.fnodar.nodarflorencia;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.fnodar.nodarflorencia.Models.NotifConfig;

import java.util.ArrayList;
import java.util.List;

public class ListenerDialogConfig implements DialogInterface.OnClickListener {

    ListenerListDialog lld;
    Activity activity;
    List<String> listNotifConfig = new ArrayList<>();
    NotifConfig notifConfig = new NotifConfig();

    public ListenerDialogConfig(ListenerListDialog lld, Activity activity) {
        this.lld = lld;
        this.activity = activity;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == AlertDialog.BUTTON_POSITIVE) {

            SharedPreferences prefs = this.activity.getSharedPreferences("noticias_config", Context.MODE_PRIVATE);
            ((MainActivity)this.activity).myAdapter.deleteNoticias();
            if(prefs.getBoolean("Deportes",false))
                listNotifConfig.add("http://www.telam.com.ar/rss2/deportes.xml");
            if(prefs.getBoolean("Economia",false))
                listNotifConfig.add("http://www.telam.com.ar/rss2/economia.xml");
            if(prefs.getBoolean("Politica",false))
                listNotifConfig.add("http://www.telam.com.ar/rss2/politica.xml");
            if(prefs.getBoolean("Internacional",false))
                listNotifConfig.add("http://www.telam.com.ar/rss2/internacional.xml");

            ((MainActivity)this.activity).buscarNoticias(listNotifConfig);



        }

        if(which == AlertDialog.BUTTON_NEGATIVE) {
            Log.d("CANCELAR", "Cancel");
        }
    }
}