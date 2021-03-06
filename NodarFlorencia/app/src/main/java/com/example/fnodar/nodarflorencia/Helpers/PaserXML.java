package com.example.fnodar.nodarflorencia.Helpers;

import android.util.Xml;

import com.example.fnodar.nodarflorencia.Models.Noticia;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaserXML {

    private static List<Noticia> noticias;
    public static final String TELAM = "http://www.telam.com.ar";

    public static List<Noticia> parserXml(String strXml) {
        XmlPullParser xml = Xml.newPullParser();
        String titulo = "";
        String link = "";
        String imagen = "";
        String descripcion = "";
        String fecha = "";
        try {
            xml.setInput(new StringReader(strXml));
            int event = xml.getEventType();
            while(event !=  XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        noticias = new ArrayList<>();
                        break;

                    case XmlPullParser.START_TAG:
                        if(xml.getName().equalsIgnoreCase("title"))
                            titulo = xml.nextText();
                        if(xml.getName().equalsIgnoreCase("link"))
                            link =  TELAM + xml.nextText();
                        if(xml.getName().equalsIgnoreCase("description"))
                            descripcion = xml.nextText();
                        if(xml.getName().equalsIgnoreCase("pubDate"))
                            fecha = xml.nextText();
                        if(xml.getName().equalsIgnoreCase("enclosure"))
                            imagen = xml.getAttributeValue(null, "url");
                        break;

                    case XmlPullParser.END_TAG:
                        if(xml.getName().equalsIgnoreCase("item")) {
                            Noticia noticia = new Noticia();
                            noticia.setTitulo(titulo);
                            noticia.setDescripcion(descripcion);
                            noticia.setImagen(imagen);
                            noticia.setLink(link);
                            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
                            String subFecha = fecha.split(":")[0];
                            String fechaFinal = subFecha.substring(5, subFecha.length() - 3);
                            Date date = formatter.parse(fechaFinal);
                            noticia.setFecha(date);
                            noticias.add(noticia);
                            titulo = "";
                            link = "";
                            imagen = "";
                            descripcion = "";
                            fecha = "";
                        }
                        break;
                }
                event = xml.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return noticias;
    }
}
