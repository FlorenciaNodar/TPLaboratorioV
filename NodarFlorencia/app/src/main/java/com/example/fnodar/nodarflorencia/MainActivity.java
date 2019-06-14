package com.example.fnodar.nodarflorencia;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fnodar.nodarflorencia.Helpers.ETipoDato;
import com.example.fnodar.nodarflorencia.Helpers.PaserXML;
import com.example.fnodar.nodarflorencia.Models.Noticia;
import com.example.fnodar.nodarflorencia.Models.NotifConfig;
import com.example.fnodar.nodarflorencia.MyAdapter;
import com.example.fnodar.nodarflorencia.MyThread;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback, SearchView.OnQueryTextListener{
    public MyAdapter myAdapter ;
    private List<Noticia> noticias = new ArrayList<>();
    private static MainActivity instance;
    private RecyclerView rvNoticias;
    Handler handler;
    private String url;

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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Noticias");


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu ,menu);
        MenuItem mi = menu.findItem(R.id.opcion1);
        SearchView sv = (android.support.v7.widget.SearchView) mi.getActionView();
        sv.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.opcion2) {

            DialogConfig dc = new DialogConfig();
            dc.show(getSupportFragmentManager(), "");
        } else if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(query.length() > 3 || query.equals("")) {

            this.myAdapter.filter(query);
        }


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.length() > 3 || newText.equals("")){

            this.myAdapter.filter(newText);

        }

        return false;
    }



    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == 1) {

            noticias = PaserXML.parserXml(msg.obj.toString());
            myAdapter = new MyAdapter(noticias, handler);
            rvNoticias.setAdapter(myAdapter);
            myAdapter.setNoticias(noticias);
            myAdapter.notifyDataSetChanged();
    } else if(msg.arg1 == 2) {
        this.myAdapter.notifyItemChanged((Integer)msg.obj);
    }

        return false;

    }

    public void buscarNoticias(List<String> notiConfig) {
        for (int i = 0; i < notiConfig.size(); i++) {
            this.url = notiConfig.get(i);
            MyThread hd1 = new MyThread(this.handler,this.url, ETipoDato.TEXTO);
            Thread t1 = new Thread(hd1);
            t1.start();
        }

    }
}
