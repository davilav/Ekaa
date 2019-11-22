package com.pma.ekaa.utils;

import android.location.LocationListener;
import android.os.Bundle;

import com.pma.ekaa.data.models.Geolocation;

public class Location implements LocationListener {

    @Override
    public void onLocationChanged(android.location.Location loc) {
        // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
        // debido a la deteccion de un cambio de ubicacion
        double latitude = loc.getLatitude();
        double longitude = loc.getLongitude();

        double scale = Math.pow(10,7);
        latitude = Math.round(latitude * scale) / scale;
        longitude = Math.round(longitude * scale) / scale;

        Geolocation obj = new Geolocation();
        obj.setLatitude(latitude);
        obj.setLongitude(longitude);
        Utils.getInstance().setGeolocation(obj);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
