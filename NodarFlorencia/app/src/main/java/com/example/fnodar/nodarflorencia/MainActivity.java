package com.example.fnodar.nodarflorencia;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.fnodar.nodarflorencia.Helpers.ETipoDato;
import com.example.fnodar.nodarflorencia.Helpers.PaserXML;
import com.example.fnodar.nodarflorencia.Models.Noticia;
import com.example.fnodar.nodarflorencia.MyAdapter;
import com.example.fnodar.nodarflorencia.MyThread;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback {
    private MyAdapter myAdapter;
//    private Handler handler;
  //  private String url;
    private List<Noticia> noticias = new ArrayList<>();
    private static MainActivity instance;
    private RecyclerView rvNoticias;
    Handler handler;
    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNoticias = super.findViewById(R.id.rv);

        handler = new Handler(this);

        MyThread m = new MyThread(handler, "http://www.telam.com.ar/rss2/deportes.xml", ETipoDato.TEXTO);
        m.start();

        Log.d("LISTA", noticias.toString());


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        rvNoticias.setLayoutManager(layoutManager);

        instance = this;
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == 1) {
            noticias = PaserXML.parserXml(msg.obj.toString());
        myAdapter = new MyAdapter(noticias, handler);
        rvNoticias.setAdapter(myAdapter);
    } else if(msg.arg1 == 2) {
        this.myAdapter.notifyItemChanged((Integer)msg.obj);
    }

        return false;

    }

}
