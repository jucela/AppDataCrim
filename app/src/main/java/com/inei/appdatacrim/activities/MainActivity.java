package com.inei.appdatacrim.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.navigation.NavigationView;
import com.inei.appdatacrim.R;
import com.inei.appdatacrim.adapters.ExpandListAdapter;
import com.inei.appdatacrim.fragments.FragmentMapa;
import com.inei.appdatacrim.fragments.FragmentMapa2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Button boton;

    private MapView mapView;
    private ArcGISMap map;


    private ArrayList<String> listDataHeader;
    private ExpandableListView expListView;
    private HashMap<String, List<String>> listDataChild;
    private ExpandListAdapter listAdapter;
    private MenuItem menuItems;
    CheckBox checkBox1_1;


    private ArrayList<Boolean> estados = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        drawerLayout =(DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_drawer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(navigationView);

        checkBox1_1 = (CheckBox) findViewById(R.id.id_check1);

        /*Lista Expandible en DRAWER*/
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        expListView = (ExpandableListView) findViewById(R.id.expandable_principal1);
        listAdapter = new ExpandListAdapter(this,obtenerListDataHeader(),obtenerListDataChild(),expListView);
        expListView.setAdapter(listAdapter);
        enableExpandableList();

        /*METODO DE ARCGIS*/
        Basemap.Type basemapType = Basemap.Type.NAVIGATION_VECTOR;
        double latitude = -12.060457;
        double longitude = -77.041531;
        int levelOfDetail = 16;


        map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
        mapView.setMap(map);
        /**/
        //addLayer1_1(map);
        estados.add(0,false);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuItem){

    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectItemDrawer(menuItem);
                return true;
            }
        });
    }


    private void enableExpandableList() {

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, final int groupPosition, final int childPosition, long id) {
                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 0:
                                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,R.style.ThemeOverlay_MaterialComponents_Dialog);
                                final View dialogView = MainActivity.this.getLayoutInflater().inflate(R.layout.layout_comisaria_area, null);

                                alert.setTitle("Area de Vista");
                                alert.setView(dialogView);
                                alert.setNegativeButton("Cancelar",null);

                                final AlertDialog alertDialog = alert.create();

                                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                                    @Override
                                    public void onShow(DialogInterface dialogInterface) {
                                        Button b = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                                        b.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                alertDialog.dismiss();
                                            }
                                        });
                                    }
                                });
                                alertDialog.show();
                                Log.i("posicion","0-0");
                                break;
                            case 1:
                                AlertDialog.Builder alert2 = new AlertDialog.Builder(MainActivity.this,R.style.ThemeOverlay_MaterialComponents_Dialog);
                                final View dialogView2 = MainActivity.this.getLayoutInflater().inflate(R.layout.layout_comisaria_distrito, null);

                                alert2.setTitle("En el Distrito");
                                alert2.setView(dialogView2);
                                alert2.setNegativeButton("Cancelar",null);

                                final AlertDialog alertDialog2 = alert2.create();

                                alertDialog2.setOnShowListener(new DialogInterface.OnShowListener() {
                                    @Override
                                    public void onShow(DialogInterface dialogInterface) {
                                        Button b = alertDialog2.getButton(AlertDialog.BUTTON_NEGATIVE);
                                        b.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                alertDialog2.dismiss();
                                            }
                                        });
                                    }
                                });
                                alertDialog2.show();
                                Log.i("posicion","0-1");
                                break;
                        }
                        break;
                    case 1:
                        switch (childPosition) {
                            case 0:
                                Log.i("posicion","1-0");
                                break;
                            case 1:
                                Log.i("posicion","1-1");
                                break;
                            case 2:
                                formDelitos2016();
                                break;
                            case 3:
                                Log.i("posicion","1-3");
                                break;
                            case 4:
                                Log.i("posicion","1-4");
                                break;
                            case 5:
                                Log.i("posicion","1-5");

                        }
                        break;
                    case 2:
                        switch (childPosition) {
                            case 0:
                                Log.i("posicion","2-0");
                                break;
                            case 1:
                                Log.i("posicion","2-1");

                        }
                        break;
                    case 3:
                        switch (childPosition) {
                            case 0:
                                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,R.style.ThemeOverlay_MaterialComponents_Dialog);
                                final View dialogView = MainActivity.this.getLayoutInflater().inflate(R.layout.layout_total_delitos_2016, null);

                                alert.setTitle("Delitos Agrupados 2016");
                                alert.setView(dialogView);
                                alert.setNegativeButton("Salir",null);

                                final AlertDialog alertDialog = alert.create();

                                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                                    @Override
                                    public void onShow(DialogInterface dialogInterface) {
                                        Button b = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                                        b.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                alertDialog.dismiss();
                                            }
                                        });
                                    }
                                });
                                alertDialog.show();
                                Log.i("posicion","3-0");
                                break;
                            case 1:
                                FragmentMapa newFragment = new FragmentMapa();
                                //newFragment.setArguments(args);
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.contedor_fragments,newFragment).commit();
                                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                                drawer.closeDrawer(GravityCompat.START);
                                break;
                            case 2:
                                FragmentMapa2 newFragment2 = new FragmentMapa2();
                                //newFragment.setArguments(args);
                                FragmentManager fragmentManager2 = getSupportFragmentManager();
                                fragmentManager2.beginTransaction().replace(R.id.contedor_fragments,newFragment2).commit();
                                DrawerLayout drawer2 = (DrawerLayout) findViewById(R.id.drawer);
                                drawer2.closeDrawer(GravityCompat.START);
                                break;
                            case 3:
                                Log.i("posicion","3-3");

                        }
                        break;
                }

                if(groupPosition>=0 && childPosition==0)
                {

                    FragmentMapa newFragment = new FragmentMapa();
                    //newFragment.setArguments(args);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.contedor_fragments,newFragment).commit();
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                    drawer.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
    }


    /*SETEAR DATOS EN MENU LATERAL O DRAWER*/
    //Llenar Cabezera de Lista
    public ArrayList<String> obtenerListDataHeader() {
       ArrayList<String> header = new ArrayList<>();
        header.add(0,"Comisarías");
        header.add(1,"Denuncias de Delitos");
        header.add(2,"Residencia de Privados de Libertad");
        header.add(3,"Total Delitos");
        return header;
    }
    //Llenar Detalle de Lista
    public HashMap<String, List<String>> obtenerListDataChild(){
        HashMap<String, List<String>> child = new HashMap<String, List<String>>();
        List<String> grupo1 = new ArrayList<String>();
        grupo1.add(0,"Area de Vista");
        grupo1.add(1,"En el Distrito");

        List<String> grupo2 = new ArrayList<String>();
        grupo2.add(0,"Ver Mapa de Calor del 2016 al 2019");
        grupo2.add(1,"Ver Puntos de Delitos del 2016 al 2019");
        grupo2.add(2,"2016");
        grupo2.add(3,"2017");
        grupo2.add(4,"2018");
        grupo2.add(5,"2019");


        List<String> grupo3 = new ArrayList<String>();
        grupo3.add(0,"Ver Mapa de Calor");
        grupo3.add(1,"Ver Puntos de Residencia");

        List<String> grupo4 = new ArrayList<String>();
        grupo4.add(0,"2016");
        grupo4.add(1,"2017");
        grupo4.add(2,"2018");
        grupo4.add(3,"2019");


        child.put(obtenerListDataHeader().get(0),grupo1);// Header, Child data
        child.put(obtenerListDataHeader().get(1),grupo2);
        child.put(obtenerListDataHeader().get(2),grupo3);
        child.put(obtenerListDataHeader().get(3),grupo4);

        return child;
    }

    /*SETEAR Y OBTENER VALORES DE CAMPO*/

    public  void formDelitos2016(){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,R.style.ThemeOverlay_MaterialComponents_Dialog);
        final View dialogView = MainActivity.this.getLayoutInflater().inflate(R.layout.layout_denuncias, null);
        ServiceFeatureTable table = new ServiceFeatureTable("http://arcgis1.inei.gob.pe:6080/arcgis/rest/services/CRIMINALIDAD/DATACRIM005_AGS_PUNTOSDELITOS_CIUDADANO/MapServer/50");
        FeatureLayer layerFromTable = new FeatureLayer(table);
        final CheckBox check1 = (CheckBox) dialogView.findViewById(R.id.id_check1);
        final CheckBox check2 = (CheckBox) dialogView.findViewById(R.id.id_check2);
        final CheckBox check3 = (CheckBox) dialogView.findViewById(R.id.id_check3);
        final CheckBox check4 = (CheckBox) dialogView.findViewById(R.id.id_check4);
        final CheckBox check5 = (CheckBox) dialogView.findViewById(R.id.id_check5);
        final CheckBox check6 = (CheckBox) dialogView.findViewById(R.id.id_check6);
        final CheckBox check7 = (CheckBox) dialogView.findViewById(R.id.id_check7);
        final CheckBox check8 = (CheckBox) dialogView.findViewById(R.id.id_check8);
        final CheckBox check9 = (CheckBox) dialogView.findViewById(R.id.id_check9);
        final CheckBox check10 = (CheckBox) dialogView.findViewById(R.id.id_check10);
        final CheckBox check11 = (CheckBox) dialogView.findViewById(R.id.id_check11);
        final CheckBox check12 = (CheckBox) dialogView.findViewById(R.id.id_check12);
        final CheckBox check13 = (CheckBox) dialogView.findViewById(R.id.id_check13);
        final CheckBox check14 = (CheckBox) dialogView.findViewById(R.id.id_check14);
        final CheckBox check15 = (CheckBox) dialogView.findViewById(R.id.id_check15);
        final CheckBox check16 = (CheckBox) dialogView.findViewById(R.id.id_check16);
        final CheckBox check17 = (CheckBox) dialogView.findViewById(R.id.id_check17);
        check1.setChecked(estados.get(0));


        alert.setTitle("Denuncias Delitos");
        alert.setIcon(R.drawable.ic_place);
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
                        boolean estado1 = check1.isChecked();
                        boolean estado2 = check2.isChecked();
                        boolean estado3 = check3.isChecked();
                        boolean estado4 = check4.isChecked();
                        boolean estado5 = check5.isChecked();
                        boolean estado6 = check6.isChecked();
                        boolean estado7 = check7.isChecked();
                        boolean estado8 = check8.isChecked();
                        boolean estado9 = check9.isChecked();
                        boolean estado10 = check10.isChecked();
                        boolean estado11 = check11.isChecked();
                        boolean estado12 = check12.isChecked();
                        boolean estado13 = check13.isChecked();
                        boolean estado14 = check14.isChecked();
                        boolean estado15 = check15.isChecked();
                        boolean estado16 = check16.isChecked();
                        boolean estado17 = check17.isChecked();
                        estados.add(estado1);
                        estados.add(estado2);
                        estados.add(estado3);
                        estados.add(estado4);
                        estados.add(estado5);
                        estados.add(estado6);
                        estados.add(estado7);
                        estados.add(estado8);
                        estados.add(estado9);
                        estados.add(estado10);
                        estados.add(estado11);
                        estados.add(estado12);
                        estados.add(estado13);
                        estados.add(estado14);
                        estados.add(estado15);
                        estados.add(estado16);
                        estados.add(estado17);
                        addCapa(map,estado1);
//                        if(estados.get(0)==true){
//                        map.getOperationalLayers().add(layerFromTable);
//                       }if(estados.get(0)==false){
//                        map.getOperationalLayers().remove(layerFromTable);
//                       }
                        Toast.makeText(MainActivity.this,"check1"+estado1,Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();

                    }
                });
            }
        });
        alertDialog.show();
    }

    /*SETEAR CAPAS*/
    private void addCapa(final ArcGISMap map,boolean estado) {
        ServiceFeatureTable table = new ServiceFeatureTable("http://arcgis1.inei.gob.pe:6080/arcgis/rest/services/CRIMINALIDAD/DATACRIM005_AGS_PUNTOSDELITOS_CIUDADANO/MapServer/50");
        FeatureLayer layerFromTable = new FeatureLayer(table);
        if(estado==true){
            map.getOperationalLayers().add(layerFromTable);
            Toast.makeText(MainActivity.this,"addCapa1"+estado,Toast.LENGTH_SHORT).show();
        }
        else
         {
             Toast.makeText(MainActivity.this,"addCapa2"+estado,Toast.LENGTH_SHORT).show();
             map.getOperationalLayers().remove(layerFromTable);
         }
    }

}
