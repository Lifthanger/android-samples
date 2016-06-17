/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mapdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.LinkedList;
import java.util.List;

/**
 * This shows how to create a simple activity with multiple maps on screen.
 */
public class MultiMapDemoActivity extends AppCompatActivity {
    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);
    private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng ADELAIDE = new LatLng(-34.92873, 138.59995);
    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);

    public static final LatLng LONDON = new LatLng(51.5074, 0.1278);
    public static final LatLng LIVERPOOL = new LatLng(53.4084, -2.9916);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimap_demo);

        final SupportMapFragment firstMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1);

        firstMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                addMarkers(googleMap, multiplyMarkerPosition(BRISBANE));
                addMarkers(googleMap, multiplyMarkerPosition(MELBOURNE));
                addMarkers(googleMap, multiplyMarkerPosition(SYDNEY));
                addMarkers(googleMap, multiplyMarkerPosition(ADELAIDE));
                addMarkers(googleMap, multiplyMarkerPosition(PERTH));

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ADELAIDE, 4f));
            }
        });

        final SupportMapFragment secondMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);

        secondMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                addMarkersBlue(googleMap, multiplyMarkerPosition(LONDON));
                addMarkersBlue(googleMap, multiplyMarkerPosition(LIVERPOOL));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LONDON, 4f));
            }
        });
    }

    public List<LatLng> multiplyMarkerPosition(LatLng position) {
        final List<LatLng> positions = new LinkedList<>();

        final double latitude = position.latitude;
        final double longitude = position.longitude;
        final double delta = 1;

        positions.add(new LatLng(latitude, longitude));
        positions.add(new LatLng(latitude, longitude + delta));
        positions.add(new LatLng(latitude, longitude - delta));

        positions.add(new LatLng(latitude - delta, longitude));
        positions.add(new LatLng(latitude - delta, longitude + delta));
        positions.add(new LatLng(latitude - delta, longitude - delta));
        
        positions.add(new LatLng(latitude + delta, longitude));
        positions.add(new LatLng(latitude + delta, longitude + delta));
        positions.add(new LatLng(latitude + delta, longitude - delta));

        return positions;
    }

    public void addMarkers(GoogleMap map, List<LatLng> positions) {
        for (LatLng latLng : positions) {
            map.addMarker(new MarkerOptions().position(latLng));
        }
    }

    public void addMarkersBlue(GoogleMap map, List<LatLng> positions) {
        for (LatLng latLng : positions) {
            map.addMarker(
                    new MarkerOptions()
                            .position(latLng)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }
    }
}
