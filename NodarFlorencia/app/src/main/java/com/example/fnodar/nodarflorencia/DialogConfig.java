package com.example.fnodar.nodarflorencia;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DialogConfig extends DialogFragment {

    private ListView listViewConfig;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder buil = new AlertDialog.Builder(getActivity());
        View v = LayoutInflater.from(getActivity().getBaseContext()).inflate(R.layout.dialog_config_layout, null);
        this.listViewConfig = (ListView) v.findViewById(R.id.listViewConfig);
        List<String> listTipoNoticias = new ArrayList<>();
        listTipoNoticias.add("Deportes");
        listTipoNoticias.add("Economia");
        listTipoNoticias.add("Politica");
        listTipoNoticias.add("Internacional");
        SharedPreferences prefs = this.getContext().getSharedPreferences("noticias_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = prefs.edit();
        e.putBoolean("Deportes",false);
        e.putBoolean("Economia",false);
        e.putBoolean("Internacional",false);
        e.putBoolean("Politica",false);
        e.commit();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, listTipoNoticias);
        this.listViewConfig.setAdapter(arrayAdapter);
        ListenerListDialog lld = new ListenerListDialog(this.listViewConfig);
        ListenerDialogConfig ldc = new ListenerDialogConfig(lld, getActivity());
        this.listViewConfig.setOnItemClickListener(lld);
        buil.setTitle("Elija las noticias de su preferencia");
        buil.setPositiveButton("CONFIRMAR", ldc);
        buil.setNegativeButton("CANCELAR", ldc);
        buil.setView(v);

        return buil.create();
    }
}
