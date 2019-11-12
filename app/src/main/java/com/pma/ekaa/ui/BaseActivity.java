package com.pma.ekaa.ui;

import android.Manifest;
import android.bluetooth.BluetoothAssignedNumbers;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Geolocation;
import com.pma.ekaa.ui.welcome.WelcomeActivity;
import com.pma.ekaa.utils.Utils;

import org.jetbrains.annotations.NotNull;

abstract  public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



}



