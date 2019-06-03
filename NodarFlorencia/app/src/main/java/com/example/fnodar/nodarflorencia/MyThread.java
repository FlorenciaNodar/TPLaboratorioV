package com.example.fnodar.nodarflorencia;

import android.icu.util.EthiopicCalendar;
import android.os.Handler;
import android.os.Message;

import com.example.fnodar.nodarflorencia.Helpers.ETipoDato;
import com.example.fnodar.nodarflorencia.Helpers.HttpConection;
import com.example.fnodar.nodarflorencia.Helpers.PaserXML;
import com.example.fnodar.nodarflorencia.Models.Noticia;

import java.io.UnsupportedEncodingException;

public class MyThread  extends Thread {
    private Handler handler;
    private String url;
    private ETipoDato queBusco;
    private Noticia noticia;
    private Integer posicion;
    public MyThread(Handler handler, String url, ETipoDato queBusco) {
        this.handler = handler;
        this.url = url;
        this.queBusco = queBusco;
    }

    public MyThread(String url, ETipoDato queBusco, Handler handler, Noticia noticia, Integer posicion) {
        this.url = url;
        this.queBusco = queBusco;
        this.noticia = noticia;
        this.handler = handler;
        this.posicion = posicion;
    }
    @Override
    public void run() {

        if(Thread.interrupted()) {
            return;
        }
        HttpConection chttp = new HttpConection();
        byte[] retorno = chttp.devolverDatos(this.url);
        Message msg = new Message();
        if(this.queBusco == ETipoDato.TEXTO) {
            msg.arg1 = 1;
            try {
                msg.obj = new String(retorno, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            this.handler.sendMessage(msg);
        } else if(this.queBusco == ETipoDato.IMAGEN) {
            if(this.noticia != null) {
                this.noticia.setImagenByte(retorno);
                msg.arg1 = 2;
                msg.obj = this.posicion;
                this.handler.sendMessage(msg);
            } else {

            }

        }
    }
}
