package com.example.fnodar.nodarflorencia;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
        this.notiConfig.setPrefiero(o.toString());
    }

    public NotifConfig getNotiConfig() {
        return notiConfig;
    }
}
