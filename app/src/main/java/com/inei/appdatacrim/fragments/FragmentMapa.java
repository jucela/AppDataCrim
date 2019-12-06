package com.inei.appdatacrim.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inei.appdatacrim.R;

public class FragmentMapa extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    GoogleMap mgoogleMap;
    MapView mapView;
    View view;
    private FloatingActionButton fab1;
    private LocationManager mLocationManager;
    private Location location;

    private OnFragmentInteractionListener mListener;

    public FragmentMapa() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mapa, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.map);
        fab1 =  (FloatingActionButton) view.findViewById(R.id.fab1);
       if(mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity(),R.style.ThemeOverlay_MaterialComponents_Dialog);
                final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.layout_mensaje_delito, null);
                alert.setTitle("Comparte un Hecho que Afectó tu Seguridad");
                alert.setIcon(R.drawable.ic_mail_outline_black);
                alert.setView(dialogView);
                alert.setPositiveButton("OK",null);
                alert.setNegativeButton("Cancelar",null);

                final AlertDialog alertDialog = alert.create();

                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                    }
                });
                alertDialog.show();
            }
        });


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mgoogleMap = googleMap;
        mgoogleMap.getUiSettings().setCompassEnabled(true);//Brujula
        mgoogleMap.getUiSettings().setZoomControlsEnabled(false);//Zoom
        mgoogleMap.getUiSettings().setMyLocationButtonEnabled(true);//GPS
        mgoogleMap.setOnMapClickListener(this);
        LatLng peru = new LatLng(-77.04459735678859, -12.066755110780761);
        LatLng comisaria = new LatLng(-12.0630177,-77.0283291);



        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(comisaria).title("Comisaria de La Victoria").snippet("Av. 28 de Julio 1620,La Victoria 15033"));
        //CameraPosition Liberty = CameraPosition.builder().target(comisaria).zoom(16).bearing(0).tilt(45).build();
        //googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            mgoogleMap.setMyLocationEnabled(true);

            location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location!=null){
                LatLng gps=new LatLng(location.getLatitude(),location.getLongitude());
                mgoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gps,12));
            }
            else{
                mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(comisaria,5));
            }
        }
        else {
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION)){
            }

            else{
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION} ,1);
            }


        }

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
