package com.example.fnodar.nodarflorencia.Models;

public class NotifConfig {
    private String prefiero;
    private String url;

    public NotifConfig() {
        this.prefiero = "deportes";
        this.url = "http://www.telam.com.ar/rss2/deportes.xml";
    }

    public NotifConfig(String prefiero, String url) {
        this.prefiero = prefiero;
        this.url = url;
    }

    public String getPrefiero() {
        return prefiero;
    }

    public void setPrefiero(String quePrefiero) {
        if(quePrefiero.equalsIgnoreCase("Deportes")) {
            this.url = "http://www.telam.com.ar/rss2/deportes.xml";
            this.prefiero = "deportes";
        }
        if(quePrefiero.equalsIgnoreCase("Economia")) {
            this.url = "http://www.telam.com.ar/rss2/economia.xml";
            this.prefiero = "economia";
        }
        if(quePrefiero.equalsIgnoreCase("Politica")) {
            this.url = "http://www.telam.com.ar/rss2/politica.xml";
            this.prefiero = "politica";
        }
        if(quePrefiero.equalsIgnoreCase("Internacional")) {
            this.url = "http://www.telam.com.ar/rss2/internacional.xml";
            this.prefiero = "internacional";
        }
    }

    public String getUrl() {
        return url;
    }
}
