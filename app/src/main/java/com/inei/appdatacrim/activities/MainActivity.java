package com.inei.appdatacrim.activities;

import android.animation.ArgbEvaluator;
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
import android.widget.TextView;
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
import com.inei.appdatacrim.dialogs.DialogComisarias;
import com.inei.appdatacrim.dialogs.DialogDelitos;
import com.inei.appdatacrim.dialogs.DialogResidencias;
import com.inei.appdatacrim.fragments.FragmentMapa;
import com.inei.appdatacrim.fragments.FragmentMapa2;
import com.inei.appdatacrim.modelo.SQLConstantes;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogDelitos.SendDialogListener,DialogComisarias.SendDialogComisariasListener, DialogResidencias.SendDialogResidenciasListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Button boton;

    private MapView mapView;
    //private ArcGISMap map;
    private TextView texto;


    private ArrayList<String> listDataHeader;
    private ExpandableListView expListView;
    private HashMap<String, List<String>> listDataChild;
    private ExpandListAdapter listAdapter;
    private MenuItem menuItems;
    private CheckBox checkBox1;


    private ArcGISMap map ;

    private FeatureLayer layerFromTableComisarias;
    private FeatureLayer layerFromTableResidencias;

    private FeatureLayer layerFromTable16_0;
    private FeatureLayer layerFromTable16_1;
    private FeatureLayer layerFromTable16_2;
    private FeatureLayer layerFromTable16_3;
    private FeatureLayer layerFromTable16_4;
    private FeatureLayer layerFromTable16_5;
    private FeatureLayer layerFromTable16_6;
    private FeatureLayer layerFromTable16_7;
    private FeatureLayer layerFromTable16_8;
    private FeatureLayer layerFromTable16_9;
    private FeatureLayer layerFromTable16_10;
    private FeatureLayer layerFromTable16_11;
    private FeatureLayer layerFromTable16_12;
    private FeatureLayer layerFromTable16_13;
    private FeatureLayer layerFromTable16_14;
    private FeatureLayer layerFromTable16_15;
    private FeatureLayer layerFromTable16_16;

    private FeatureLayer layerFromTable17_0;
    private FeatureLayer layerFromTable17_1;
    private FeatureLayer layerFromTable17_2;
    private FeatureLayer layerFromTable17_3;
    private FeatureLayer layerFromTable17_4;
    private FeatureLayer layerFromTable17_5;
    private FeatureLayer layerFromTable17_6;
    private FeatureLayer layerFromTable17_7;
    private FeatureLayer layerFromTable17_8;
    private FeatureLayer layerFromTable17_9;
    private FeatureLayer layerFromTable17_10;
    private FeatureLayer layerFromTable17_11;
    private FeatureLayer layerFromTable17_12;
    private FeatureLayer layerFromTable17_13;
    private FeatureLayer layerFromTable17_14;
    private FeatureLayer layerFromTable17_15;
    private FeatureLayer layerFromTable17_16;

    private FeatureLayer layerFromTable18_0;
    private FeatureLayer layerFromTable18_1;
    private FeatureLayer layerFromTable18_2;
    private FeatureLayer layerFromTable18_3;
    private FeatureLayer layerFromTable18_4;
    private FeatureLayer layerFromTable18_5;
    private FeatureLayer layerFromTable18_6;
    private FeatureLayer layerFromTable18_7;
    private FeatureLayer layerFromTable18_8;
    private FeatureLayer layerFromTable18_9;
    private FeatureLayer layerFromTable18_10;
    private FeatureLayer layerFromTable18_11;
    private FeatureLayer layerFromTable18_12;
    private FeatureLayer layerFromTable18_13;
    private FeatureLayer layerFromTable18_14;
    private FeatureLayer layerFromTable18_15;
    private FeatureLayer layerFromTable18_16;

    private FeatureLayer layerFromTable19_0;
    private FeatureLayer layerFromTable19_1;
    private FeatureLayer layerFromTable19_2;
    private FeatureLayer layerFromTable19_3;
    private FeatureLayer layerFromTable19_4;
    private FeatureLayer layerFromTable19_5;
    private FeatureLayer layerFromTable19_6;
    private FeatureLayer layerFromTable19_7;
    private FeatureLayer layerFromTable19_8;
    private FeatureLayer layerFromTable19_9;
    private FeatureLayer layerFromTable19_10;
    private FeatureLayer layerFromTable19_11;
    private FeatureLayer layerFromTable19_12;
    private FeatureLayer layerFromTable19_13;
    private FeatureLayer layerFromTable19_14;
    private FeatureLayer layerFromTable19_15;
    private FeatureLayer layerFromTable19_16;


    private ArrayList<Boolean> estadosComisaria  = new ArrayList<>();
    private ArrayList<Boolean> estadosResidencia = new ArrayList<>();

    private ArrayList<Boolean> estados16 = new ArrayList<>();
    private ArrayList<Boolean> estados17 = new ArrayList<>();
    private ArrayList<Boolean> estados18 = new ArrayList<>();
    private ArrayList<Boolean> estados19 = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        texto = findViewById(R.id.idtexto);
        //checkBox1 = findViewById(R.id.id_check1);
        drawerLayout =(DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_drawer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(navigationView);



        /*Lista Expandible en DRAWER*/
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        expListView = (ExpandableListView) findViewById(R.id.expandable_principal1);
        listAdapter = new ExpandListAdapter(this,obtenerListDataHeader(),obtenerListDataChild(),expListView);
        expListView.setAdapter(listAdapter);
        enableExpandableList();

        setCheckEstados();

        Basemap.Type basemapType = Basemap.Type.STREETS_VECTOR;
        double latitude  = -12.060457;
        double longitude = -77.041531;
        int levelOfDetail = 16;
        map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
        mapView.setMap(map);

        ServiceFeatureTable tableComisarias = new ServiceFeatureTable(SQLConstantes.servicioComisarias);
        ServiceFeatureTable tableResidencias = new ServiceFeatureTable(SQLConstantes.servicioPuntoResidencia);

        ServiceFeatureTable table16_1 = new ServiceFeatureTable(SQLConstantes.servicio16_1);
        ServiceFeatureTable table16_2 = new ServiceFeatureTable(SQLConstantes.servicio16_2);
        ServiceFeatureTable table16_3 = new ServiceFeatureTable(SQLConstantes.servicio16_3);
        ServiceFeatureTable table16_4 = new ServiceFeatureTable(SQLConstantes.servicio16_4);
        ServiceFeatureTable table16_5 = new ServiceFeatureTable(SQLConstantes.servicio16_5);
        ServiceFeatureTable table16_6 = new ServiceFeatureTable(SQLConstantes.servicio16_6);
        ServiceFeatureTable table16_7 = new ServiceFeatureTable(SQLConstantes.servicio16_7);
        ServiceFeatureTable table16_8 = new ServiceFeatureTable(SQLConstantes.servicio16_8);
        ServiceFeatureTable table16_9 = new ServiceFeatureTable(SQLConstantes.servicio16_9);
        ServiceFeatureTable table16_10 = new ServiceFeatureTable(SQLConstantes.servicio16_10);
        ServiceFeatureTable table16_11 = new ServiceFeatureTable(SQLConstantes.servicio16_11);
        ServiceFeatureTable table16_12 = new ServiceFeatureTable(SQLConstantes.servicio16_12);
        ServiceFeatureTable table16_13 = new ServiceFeatureTable(SQLConstantes.servicio16_13);
        ServiceFeatureTable table16_14 = new ServiceFeatureTable(SQLConstantes.servicio16_14);
        ServiceFeatureTable table16_15 = new ServiceFeatureTable(SQLConstantes.servicio16_15);
        ServiceFeatureTable table16_16 = new ServiceFeatureTable(SQLConstantes.servicio16_16);

        ServiceFeatureTable table17_1 = new ServiceFeatureTable(SQLConstantes.servicio17_1);
        ServiceFeatureTable table17_2 = new ServiceFeatureTable(SQLConstantes.servicio17_2);
        ServiceFeatureTable table17_3 = new ServiceFeatureTable(SQLConstantes.servicio17_3);
        ServiceFeatureTable table17_4 = new ServiceFeatureTable(SQLConstantes.servicio17_4);
        ServiceFeatureTable table17_5 = new ServiceFeatureTable(SQLConstantes.servicio17_5);
        ServiceFeatureTable table17_6 = new ServiceFeatureTable(SQLConstantes.servicio17_6);
        ServiceFeatureTable table17_7 = new ServiceFeatureTable(SQLConstantes.servicio17_7);
        ServiceFeatureTable table17_8 = new ServiceFeatureTable(SQLConstantes.servicio17_8);
        ServiceFeatureTable table17_9 = new ServiceFeatureTable(SQLConstantes.servicio17_9);
        ServiceFeatureTable table17_10 = new ServiceFeatureTable(SQLConstantes.servicio17_10);
        ServiceFeatureTable table17_11 = new ServiceFeatureTable(SQLConstantes.servicio17_11);
        ServiceFeatureTable table17_12 = new ServiceFeatureTable(SQLConstantes.servicio17_12);
        ServiceFeatureTable table17_13 = new ServiceFeatureTable(SQLConstantes.servicio17_13);
        ServiceFeatureTable table17_14 = new ServiceFeatureTable(SQLConstantes.servicio17_14);
        ServiceFeatureTable table17_15 = new ServiceFeatureTable(SQLConstantes.servicio17_15);
        ServiceFeatureTable table17_16 = new ServiceFeatureTable(SQLConstantes.servicio17_16);

        ServiceFeatureTable table18_1 = new ServiceFeatureTable(SQLConstantes.servicio18_1);
        ServiceFeatureTable table18_2 = new ServiceFeatureTable(SQLConstantes.servicio18_2);
        ServiceFeatureTable table18_3 = new ServiceFeatureTable(SQLConstantes.servicio18_3);
        ServiceFeatureTable table18_4 = new ServiceFeatureTable(SQLConstantes.servicio18_4);
        ServiceFeatureTable table18_5 = new ServiceFeatureTable(SQLConstantes.servicio18_5);
        ServiceFeatureTable table18_6 = new ServiceFeatureTable(SQLConstantes.servicio18_6);
        ServiceFeatureTable table18_7 = new ServiceFeatureTable(SQLConstantes.servicio18_7);
        ServiceFeatureTable table18_8 = new ServiceFeatureTable(SQLConstantes.servicio18_8);
        ServiceFeatureTable table18_9 = new ServiceFeatureTable(SQLConstantes.servicio18_9);
        ServiceFeatureTable table18_10 = new ServiceFeatureTable(SQLConstantes.servicio18_10);
        ServiceFeatureTable table18_11 = new ServiceFeatureTable(SQLConstantes.servicio18_11);
        ServiceFeatureTable table18_12 = new ServiceFeatureTable(SQLConstantes.servicio18_12);
        ServiceFeatureTable table18_13 = new ServiceFeatureTable(SQLConstantes.servicio18_13);
        ServiceFeatureTable table18_14 = new ServiceFeatureTable(SQLConstantes.servicio18_14);
        ServiceFeatureTable table18_15 = new ServiceFeatureTable(SQLConstantes.servicio18_15);
        ServiceFeatureTable table18_16 = new ServiceFeatureTable(SQLConstantes.servicio18_16);

        ServiceFeatureTable table19_1 = new ServiceFeatureTable(SQLConstantes.servicio19_1);
        ServiceFeatureTable table19_2 = new ServiceFeatureTable(SQLConstantes.servicio19_2);
        ServiceFeatureTable table19_3 = new ServiceFeatureTable(SQLConstantes.servicio19_3);
        ServiceFeatureTable table19_4 = new ServiceFeatureTable(SQLConstantes.servicio19_4);
        ServiceFeatureTable table19_5 = new ServiceFeatureTable(SQLConstantes.servicio19_5);
        ServiceFeatureTable table19_6 = new ServiceFeatureTable(SQLConstantes.servicio19_6);
        ServiceFeatureTable table19_7 = new ServiceFeatureTable(SQLConstantes.servicio19_7);
        ServiceFeatureTable table19_8 = new ServiceFeatureTable(SQLConstantes.servicio19_8);
        ServiceFeatureTable table19_9 = new ServiceFeatureTable(SQLConstantes.servicio19_9);
        ServiceFeatureTable table19_10 = new ServiceFeatureTable(SQLConstantes.servicio19_10);
        ServiceFeatureTable table19_11 = new ServiceFeatureTable(SQLConstantes.servicio19_11);
        ServiceFeatureTable table19_12 = new ServiceFeatureTable(SQLConstantes.servicio19_12);
        ServiceFeatureTable table19_13 = new ServiceFeatureTable(SQLConstantes.servicio19_13);
        ServiceFeatureTable table19_14 = new ServiceFeatureTable(SQLConstantes.servicio19_14);
        ServiceFeatureTable table19_15 = new ServiceFeatureTable(SQLConstantes.servicio19_15);
        ServiceFeatureTable table19_16 = new ServiceFeatureTable(SQLConstantes.servicio19_16);

        layerFromTable16_1 = new FeatureLayer(table16_1);
        layerFromTable16_2 = new FeatureLayer(table16_2);
        layerFromTable16_3 = new FeatureLayer(table16_3);
        layerFromTable16_4 = new FeatureLayer(table16_4);
        layerFromTable16_5 = new FeatureLayer(table16_5);
        layerFromTable16_6 = new FeatureLayer(table16_6);
        layerFromTable16_7 = new FeatureLayer(table16_7);
        layerFromTable16_8 = new FeatureLayer(table16_8);
        layerFromTable16_9 = new FeatureLayer(table16_9);
        layerFromTable16_10 = new FeatureLayer(table16_10);
        layerFromTable16_11 = new FeatureLayer(table16_11);
        layerFromTable16_12 = new FeatureLayer(table16_12);
        layerFromTable16_13 = new FeatureLayer(table16_13);
        layerFromTable16_14 = new FeatureLayer(table16_14);
        layerFromTable16_15 = new FeatureLayer(table16_15);
        layerFromTable16_16 = new FeatureLayer(table16_16);

        layerFromTable17_1 = new FeatureLayer(table17_1);
        layerFromTable17_2 = new FeatureLayer(table17_2);
        layerFromTable17_3 = new FeatureLayer(table17_3);
        layerFromTable17_4 = new FeatureLayer(table17_4);
        layerFromTable17_5 = new FeatureLayer(table17_5);
        layerFromTable17_6 = new FeatureLayer(table17_6);
        layerFromTable17_7 = new FeatureLayer(table17_7);
        layerFromTable17_8 = new FeatureLayer(table17_8);
        layerFromTable17_9 = new FeatureLayer(table17_9);
        layerFromTable17_10 = new FeatureLayer(table17_10);
        layerFromTable17_11 = new FeatureLayer(table17_11);
        layerFromTable17_12 = new FeatureLayer(table17_12);
        layerFromTable17_13 = new FeatureLayer(table17_13);
        layerFromTable17_14 = new FeatureLayer(table17_14);
        layerFromTable17_15 = new FeatureLayer(table17_15);
        layerFromTable17_16 = new FeatureLayer(table17_16);

        layerFromTable18_1 = new FeatureLayer(table18_1);
        layerFromTable18_2 = new FeatureLayer(table18_2);
        layerFromTable18_3 = new FeatureLayer(table18_3);
        layerFromTable18_4 = new FeatureLayer(table18_4);
        layerFromTable18_5 = new FeatureLayer(table18_5);
        layerFromTable18_6 = new FeatureLayer(table18_6);
        layerFromTable18_7 = new FeatureLayer(table18_7);
        layerFromTable18_8 = new FeatureLayer(table18_8);
        layerFromTable18_9 = new FeatureLayer(table18_9);
        layerFromTable18_10 = new FeatureLayer(table18_10);
        layerFromTable18_11 = new FeatureLayer(table18_11);
        layerFromTable18_12 = new FeatureLayer(table18_12);
        layerFromTable18_13 = new FeatureLayer(table18_13);
        layerFromTable18_14 = new FeatureLayer(table18_14);
        layerFromTable18_15 = new FeatureLayer(table18_15);
        layerFromTable18_16 = new FeatureLayer(table18_16);

        layerFromTable19_1 = new FeatureLayer(table19_1);
        layerFromTable19_2 = new FeatureLayer(table19_2);
        layerFromTable19_3 = new FeatureLayer(table19_3);
        layerFromTable19_4 = new FeatureLayer(table19_4);
        layerFromTable19_5 = new FeatureLayer(table19_5);
        layerFromTable19_6 = new FeatureLayer(table19_6);
        layerFromTable19_7 = new FeatureLayer(table19_7);
        layerFromTable19_8 = new FeatureLayer(table19_8);
        layerFromTable19_9 = new FeatureLayer(table19_9);
        layerFromTable19_10 = new FeatureLayer(table19_10);
        layerFromTable19_11 = new FeatureLayer(table19_11);
        layerFromTable19_12 = new FeatureLayer(table19_12);
        layerFromTable19_13 = new FeatureLayer(table19_13);
        layerFromTable19_14 = new FeatureLayer(table19_14);
        layerFromTable19_15 = new FeatureLayer(table19_15);
        layerFromTable19_16 = new FeatureLayer(table19_16);

        layerFromTableComisarias = new FeatureLayer(tableComisarias);
        layerFromTableResidencias = new FeatureLayer(tableResidencias);

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

    /*METODOS DE ANULACION*/
    @Override
    protected void onPause() {
        if (mapView != null) {
            mapView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.resume();
        }
    }

    @Override

    protected void onDestroy() {
        if (mapView != null) {
            mapView.dispose();
        }
        super.onDestroy();
    }


    /*METODO DE INTERACCION CON EL ADAPTADOR*/
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
                                sendComisaria(estadosComisaria);
                                break;
                        }
                        break;
                    case 1:
                        switch (childPosition) {
                            case 0:
                                sendDelito("2016",estados16);
                                break;
                            case 1:
                                sendDelito("2017",estados17);
                                break;
                            case 2:
                                sendDelito("2018",estados18);
                                break;
                            case 3:
                                sendDelito("2019",estados19);
                                break;

                        }
                        break;
                    case 2:
                        switch (childPosition) {
                            case 0:
                                sendResidencia(estadosResidencia);
                                break;
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

                if(groupPosition>=0 && childPosition>=0)
                {

                    //FragmentMapa newFragment = new FragmentMapa();
                    //newFragment.setArguments(args);
                    //FragmentManager fragmentManager = getSupportFragmentManager();
                    //fragmentManager.beginTransaction().replace(R.id.contedor_fragments,newFragment).commit();
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
        grupo1.add(0,"Ubicación Comisarías");

        List<String> grupo2 = new ArrayList<String>();
        grupo2.add(0,"2016");
        grupo2.add(1,"2017");
        grupo2.add(2,"2018");
        grupo2.add(3,"2019");


        List<String> grupo3 = new ArrayList<String>();
        grupo3.add(0,"Ver Puntos de Residencia");

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

    /*SETEAR CAPAS*/
     private void addLayers(boolean estado,FeatureLayer capa) {
        if(estado==true)
        {
            //map.getOperationalLayers().clear();
            map.getOperationalLayers().add(capa);}
        else
        { map.getOperationalLayers().remove(capa);}
    }

    /*SETEAR ESTADOS AL ARRAYLIST*/
    private void setCheckEstados(){
        if(estadosComisaria.size()==0){
            for(int i=0;i<1;i++)
            {estadosComisaria.add(i,false);}
        }
        if(estadosResidencia.size()==0){
            for(int i=0;i<1;i++)
            {estadosResidencia.add(i,false);}
        }
        if(estados16.size()==0){
            for(int i=0;i<17;i++)
            {estados16.add(i,false);}
        }
        if(estados17.size()==0){
            for(int i=0;i<17;i++)
            {estados17.add(i,false);}
        }
        if(estados18.size()==0){
            for(int i=0;i<17;i++)
            {estados18.add(i,false);}
        }
        if(estados19.size()==0){
            for(int i=0;i<17;i++)
            {estados19.add(i,false);}
        }
    }

    /*METODOS DE INTERACCION CON DIALOGOS*/

    //Metodo que recibe datos de Dialogcomisarias//
    @Override
    public void receiveComisaria(ArrayList<Boolean> estados) {
        estadosComisaria.add(0,estados.get(0));
        addLayers(estadosComisaria.get(0),layerFromTableComisarias);
    }

    //Metodo que recibe datos de DialogDelitos//
    @Override
    public void receiveDelito(ArrayList<Boolean> stateLayers,String anio) {

        if(anio=="2016"){
        estados16.add(0,stateLayers.get(0));
        estados16.add(1,stateLayers.get(1));
        estados16.add(2,stateLayers.get(2));
        estados16.add(3,stateLayers.get(3));
        estados16.add(4,stateLayers.get(4));
        estados16.add(5,stateLayers.get(5));
        estados16.add(6,stateLayers.get(6));
        estados16.add(7,stateLayers.get(7));
        estados16.add(8,stateLayers.get(8));
        estados16.add(9,stateLayers.get(9));
        estados16.add(10,stateLayers.get(10));
        estados16.add(11,stateLayers.get(11));
        estados16.add(12,stateLayers.get(12));
        estados16.add(13,stateLayers.get(13));
        estados16.add(14,stateLayers.get(14));
        estados16.add(15,stateLayers.get(15));
        estados16.add(16,stateLayers.get(16));
        addLayers(estados16.get(1),layerFromTable16_1);
        addLayers(estados16.get(2),layerFromTable16_2);
        addLayers(estados16.get(3),layerFromTable16_3);
        addLayers(estados16.get(4),layerFromTable16_4);
        addLayers(estados16.get(5),layerFromTable16_5);
        addLayers(estados16.get(6),layerFromTable16_6);
        addLayers(estados16.get(7),layerFromTable16_7);
        addLayers(estados16.get(8),layerFromTable16_8);
        addLayers(estados16.get(9),layerFromTable16_9);
        addLayers(estados16.get(10),layerFromTable16_10);
        addLayers(estados16.get(11),layerFromTable16_11);
        addLayers(estados16.get(12),layerFromTable16_12);
        addLayers(estados16.get(13),layerFromTable16_13);
        addLayers(estados16.get(14),layerFromTable16_14);
        addLayers(estados16.get(15),layerFromTable16_15);
        addLayers(estados16.get(16),layerFromTable16_16);}
        else
        if(anio=="2017"){
        estados17.add(0,stateLayers.get(0));
        estados17.add(1,stateLayers.get(1));
        estados17.add(2,stateLayers.get(2));
        estados17.add(3,stateLayers.get(3));
        estados17.add(4,stateLayers.get(4));
        estados17.add(5,stateLayers.get(5));
        estados17.add(6,stateLayers.get(6));
        estados17.add(7,stateLayers.get(7));
        estados17.add(8,stateLayers.get(8));
        estados17.add(9,stateLayers.get(9));
        estados17.add(10,stateLayers.get(10));
        estados17.add(11,stateLayers.get(11));
        estados17.add(12,stateLayers.get(12));
        estados17.add(13,stateLayers.get(13));
        estados17.add(14,stateLayers.get(14));
        estados17.add(15,stateLayers.get(15));
        estados17.add(16,stateLayers.get(16));
        addLayers(estados17.get(1),layerFromTable17_1);
        addLayers(estados17.get(2),layerFromTable17_2);
        addLayers(estados17.get(3),layerFromTable17_3);
        addLayers(estados17.get(4),layerFromTable17_4);
        addLayers(estados17.get(5),layerFromTable17_5);
        addLayers(estados17.get(6),layerFromTable17_6);
        addLayers(estados17.get(7),layerFromTable17_7);
        addLayers(estados17.get(8),layerFromTable17_8);
        addLayers(estados17.get(9),layerFromTable17_9);
        addLayers(estados17.get(10),layerFromTable17_10);
        addLayers(estados17.get(11),layerFromTable17_11);
        addLayers(estados17.get(12),layerFromTable17_12);
        addLayers(estados17.get(13),layerFromTable17_13);
        addLayers(estados17.get(14),layerFromTable17_14);
        addLayers(estados17.get(15),layerFromTable17_15);
        addLayers(estados17.get(16),layerFromTable17_16);}
        else
        if(anio=="2018"){
        estados18.add(0,stateLayers.get(0));
        estados18.add(1,stateLayers.get(1));
        estados18.add(2,stateLayers.get(2));
        estados18.add(3,stateLayers.get(3));
        estados18.add(4,stateLayers.get(4));
        estados18.add(5,stateLayers.get(5));
        estados18.add(6,stateLayers.get(6));
        estados18.add(7,stateLayers.get(7));
        estados18.add(8,stateLayers.get(8));
        estados18.add(9,stateLayers.get(9));
        estados18.add(10,stateLayers.get(10));
        estados18.add(11,stateLayers.get(11));
        estados18.add(12,stateLayers.get(12));
        estados18.add(13,stateLayers.get(13));
        estados18.add(14,stateLayers.get(14));
        estados18.add(15,stateLayers.get(15));
        estados18.add(16,stateLayers.get(16));
        addLayers(estados18.get(1),layerFromTable18_1);
        addLayers(estados18.get(2),layerFromTable18_2);
        addLayers(estados18.get(3),layerFromTable18_3);
        addLayers(estados18.get(4),layerFromTable18_4);
        addLayers(estados18.get(5),layerFromTable18_5);
        addLayers(estados18.get(6),layerFromTable18_6);
        addLayers(estados18.get(7),layerFromTable18_7);
        addLayers(estados18.get(8),layerFromTable18_8);
        addLayers(estados18.get(9),layerFromTable18_9);
        addLayers(estados18.get(10),layerFromTable18_10);
        addLayers(estados18.get(11),layerFromTable18_11);
        addLayers(estados18.get(12),layerFromTable18_12);
        addLayers(estados18.get(13),layerFromTable18_13);
        addLayers(estados18.get(14),layerFromTable18_14);
        addLayers(estados18.get(15),layerFromTable18_15);
        addLayers(estados18.get(16),layerFromTable18_16);}
        else
        if(anio=="2019"){
        estados19.add(0,stateLayers.get(0));
        estados19.add(1,stateLayers.get(1));
        estados19.add(2,stateLayers.get(2));
        estados19.add(3,stateLayers.get(3));
        estados19.add(4,stateLayers.get(4));
        estados19.add(5,stateLayers.get(5));
        estados19.add(6,stateLayers.get(6));
        estados19.add(7,stateLayers.get(7));
        estados19.add(8,stateLayers.get(8));
        estados19.add(9,stateLayers.get(9));
        estados19.add(10,stateLayers.get(10));
        estados19.add(11,stateLayers.get(11));
        estados19.add(12,stateLayers.get(12));
        estados19.add(13,stateLayers.get(13));
        estados19.add(14,stateLayers.get(14));
        estados19.add(15,stateLayers.get(15));
        estados19.add(16,stateLayers.get(16));
        addLayers(estados19.get(1),layerFromTable19_1);
        addLayers(estados19.get(2),layerFromTable19_2);
        addLayers(estados19.get(3),layerFromTable19_3);
        addLayers(estados19.get(4),layerFromTable19_4);
        addLayers(estados19.get(5),layerFromTable19_5);
        addLayers(estados19.get(6),layerFromTable19_6);
        addLayers(estados19.get(7),layerFromTable19_7);
        addLayers(estados19.get(8),layerFromTable19_8);
        addLayers(estados19.get(9),layerFromTable19_9);
        addLayers(estados19.get(10),layerFromTable19_10);
        addLayers(estados19.get(11),layerFromTable19_11);
        addLayers(estados19.get(12),layerFromTable19_12);
        addLayers(estados19.get(13),layerFromTable19_13);
        addLayers(estados19.get(14),layerFromTable19_14);
        addLayers(estados19.get(15),layerFromTable19_15);
        addLayers(estados19.get(16),layerFromTable19_16);}
    }

    //Metodo que recibe datos de DialogResidencias//
    @Override
    public void receiveResidencia(ArrayList<Boolean> estados) {
        estadosResidencia.add(0,estados.get(0));
        addLayers(estadosResidencia.get(0),layerFromTableResidencias);
    }

    /*Metodo que envia datos DialogComisarias*/
    public void sendComisaria(ArrayList<Boolean> estados) {
        DialogComisarias form = DialogComisarias.newInstance(estados);
        form.show(getSupportFragmentManager(), DialogComisarias.TAG);

    }

    /*Metodo que envia datos DialogDelitos*/
    public void sendDelito(String anio,ArrayList<Boolean> estados) {
        DialogDelitos form = DialogDelitos.newInstance(anio,estados);
        form.show(getSupportFragmentManager(), DialogDelitos.TAG);
    }

    /*Metodo que envia datos DialogResidencias*/
    public void sendResidencia(ArrayList<Boolean> estados) {
        DialogResidencias form = DialogResidencias.newInstance(estados);
        form.show(getSupportFragmentManager(), DialogResidencias.TAG);

    }


}
