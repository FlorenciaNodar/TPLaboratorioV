package com.example.fnodar.nodarflorencia;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fnodar.nodarflorencia.Helpers.ETipoDato;
import com.example.fnodar.nodarflorencia.Models.Noticia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter  extends RecyclerView.Adapter<MyViewHolder> {
    private List<Noticia> noticias;
    private List<Noticia> noticiasCopy;
    private Thread t1;
    private Handler handler;

    public MyAdapter(List<Noticia> noticias,Handler handler) {
        this.noticias = noticias;
        this.handler = handler;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Noticia n = this.noticias.get(position);


        if(n.getImagenByte() == null && !n.getImagen().equalsIgnoreCase("")) {
            MyThread hd = new MyThread(n.getImagen(), ETipoDato.IMAGEN, this.handler, n, position);
            this.t1 = new Thread(hd);
            t1.start();
        } else if(n.getImagen().equalsIgnoreCase("")) {
            holder.imgNoticia.setImageResource(R.drawable.ic_launcher_background);
        } else {
            holder.imgNoticia.setImageBitmap(BitmapFactory.decodeByteArray(n.getImagenByte(), 0, n.getImagenByte().length));
        }
        holder.setLink(n.getLink());
        holder.tituloNoticia.setText(n.getTitulo());
        StringBuilder sb = new StringBuilder("");
        if(n.getDescripcion().length() < 170) {
            sb.append(n.getDescripcion());
        } else {
            sb.append(n.getDescripcion().substring(0, 170).concat("..."));
        }
        holder.descripcionNoticia.setText(sb.toString());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MMM-yyyy");
        holder.fechaNoticia.setText(dt1.format(n.getFecha()));
    }

    @Override
    public int getItemCount() {
        return this.noticias.size();
    }

    public void setNoticias(List<Noticia> n) {
        this.noticias = n;
    }


}
