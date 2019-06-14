package com.example.fnodar.nodarflorencia;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.fnodar.nodarflorencia.Models.NotifConfig;

public class ListenerListDialog implements AdapterView.OnItemClickListener {
    private ListView listView;
    private NotifConfig notiConfig;

    public ListenerListDialog(ListView listView) {
        this.listView = listView;
        this.notiConfig = new NotifConfig();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object o = this.listView.getItemAtPosition(position);
        SharedPreferences sharedPreferences = listView.getContext().getSharedPreferences("noticias_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sharedPreferences.edit();

        e.putBoolean(o.toString(),((AppCompatCheckedTextView)view).isChecked());
        e.commit();
    }

    public NotifConfig getNotiConfig() {
        return notiConfig;
    }
}
