package com.inei.appdatacrim.activities;

import android.Manifest;
import android.animation.ArgbEvaluator;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.symbology.TextSymbol;
import com.esri.arcgisruntime.tasks.geocode.GeocodeParameters;
import com.esri.arcgisruntime.tasks.geocode.GeocodeResult;
import com.esri.arcgisruntime.tasks.geocode.LocatorTask;
import com.esri.arcgisruntime.util.ListenableList;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.inei.appdatacrim.R;
import com.inei.appdatacrim.adapters.ExpandListAdapter;
import com.inei.appdatacrim.dialogs.DialogComisarias;
import com.inei.appdatacrim.dialogs.DialogDelitos;
import com.inei.appdatacrim.dialogs.DialogDelitosGraficos;
import com.inei.appdatacrim.dialogs.DialogMensajes;
import com.inei.appdatacrim.dialogs.DialogResidencias;
import com.inei.appdatacrim.fragments.FragmentMapa;
import com.inei.appdatacrim.fragments.FragmentMapa2;
import com.inei.appdatacrim.modelo.SQLConstantes;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements DialogDelitos.SendDialogListener,DialogComisarias.SendDialogComisariasListener, DialogResidencias.SendDialogResidenciasListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FloatingActionButton btn_message;
    private FloatingActionButton btn_gps;
    private SearchView mSearchView = null;

    private MapView mapView;
    private ArcGISMap map ;
    private LocationDisplay mLocationDisplay;
    private GraphicsOverlay mGraphicsOverlay;
    private LocatorTask mLocatorTask = null;
    private GeocodeParameters mGeocodeParameters = null;
    public static final  LocationDisplay.AutoPanMode NAVEGATION = null;

    private ArrayList<String> listDataHeader;
    private ExpandableListView expListView;
    private HashMap<String, List<String>> listDataChild;
    private ExpandListAdapter listAdapter;
    private MenuItem menuItems;
    private CheckBox checkBox1;

    private FeatureLayer layerFromTableComisarias;
    private FeatureLayer layerFromTableResidencias;

    private ArrayList<Boolean> estadosComisaria  = new ArrayList<>();
    private ArrayList<Boolean> estadosResidencia = new ArrayList<>();

    private ArrayList<Boolean> estados16 = new ArrayList<>();
    private ArrayList<Boolean> estados17 = new ArrayList<>();
    private ArrayList<Boolean> estados18 = new ArrayList<>();
    private ArrayList<Boolean> estados19 = new ArrayList<>();

    private ArrayList<FeatureLayer> featureLayers16 = new ArrayList<>();
    private ArrayList<FeatureLayer> featureLayers17 = new ArrayList<>();
    private ArrayList<FeatureLayer> featureLayers18 = new ArrayList<>();
    private ArrayList<FeatureLayer> featureLayers19 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        drawerLayout =(DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        btn_message = (FloatingActionButton)findViewById(R.id.btn_message);
        btn_gps = (FloatingActionButton)findViewById(R.id.btn_gps);

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
        setupLocationDisplay();
        //setupLocator();

        ServiceFeatureTable tableComisarias = new ServiceFeatureTable(SQLConstantes.servicioComisarias);
        ServiceFeatureTable tableResidencias = new ServiceFeatureTable(SQLConstantes.servicioPuntoResidencia);

        ServiceFeatureTable table16_0 = new ServiceFeatureTable(SQLConstantes.servicio16_0);
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

        ServiceFeatureTable table17_0 = new ServiceFeatureTable(SQLConstantes.servicio17_0);
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

        ServiceFeatureTable table18_0 = new ServiceFeatureTable(SQLConstantes.servicio18_0);
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

        ServiceFeatureTable table19_0 = new ServiceFeatureTable(SQLConstantes.servicio19_0);
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

        featureLayers16.add(new FeatureLayer(table16_0));
        featureLayers16.add(new FeatureLayer(table16_1));
        featureLayers16.add(new FeatureLayer(table16_2));
        featureLayers16.add(new FeatureLayer(table16_3));
        featureLayers16.add(new FeatureLayer(table16_4));
        featureLayers16.add(new FeatureLayer(table16_5));
        featureLayers16.add(new FeatureLayer(table16_6));
        featureLayers16.add(new FeatureLayer(table16_7));
        featureLayers16.add(new FeatureLayer(table16_8));
        featureLayers16.add(new FeatureLayer(table16_9));
        featureLayers16.add(new FeatureLayer(table16_10));
        featureLayers16.add(new FeatureLayer(table16_11));
        featureLayers16.add(new FeatureLayer(table16_12));
        featureLayers16.add(new FeatureLayer(table16_13));
        featureLayers16.add(new FeatureLayer(table16_14));
        featureLayers16.add(new FeatureLayer(table16_15));

        featureLayers17.add(new FeatureLayer(table17_0));
        featureLayers17.add(new FeatureLayer(table17_1));
        featureLayers17.add(new FeatureLayer(table17_2));
        featureLayers17.add(new FeatureLayer(table17_3));
        featureLayers17.add(new FeatureLayer(table17_4));
        featureLayers17.add(new FeatureLayer(table17_5));
        featureLayers17.add(new FeatureLayer(table17_6));
        featureLayers17.add(new FeatureLayer(table17_7));
        featureLayers17.add(new FeatureLayer(table17_8));
        featureLayers17.add(new FeatureLayer(table17_9));
        featureLayers17.add(new FeatureLayer(table17_10));
        featureLayers17.add(new FeatureLayer(table17_11));
        featureLayers17.add(new FeatureLayer(table17_12));
        featureLayers17.add(new FeatureLayer(table17_13));
        featureLayers17.add(new FeatureLayer(table17_14));
        featureLayers17.add(new FeatureLayer(table17_15));

        featureLayers18.add(new FeatureLayer(table18_0));
        featureLayers18.add(new FeatureLayer(table18_1));
        featureLayers18.add(new FeatureLayer(table18_2));
        featureLayers18.add(new FeatureLayer(table18_3));
        featureLayers18.add(new FeatureLayer(table18_4));
        featureLayers18.add(new FeatureLayer(table18_5));
        featureLayers18.add(new FeatureLayer(table18_6));
        featureLayers18.add(new FeatureLayer(table18_7));
        featureLayers18.add(new FeatureLayer(table18_8));
        featureLayers18.add(new FeatureLayer(table18_9));
        featureLayers18.add(new FeatureLayer(table18_10));
        featureLayers18.add(new FeatureLayer(table18_11));
        featureLayers18.add(new FeatureLayer(table18_12));
        featureLayers18.add(new FeatureLayer(table18_13));
        featureLayers18.add(new FeatureLayer(table18_14));
        featureLayers18.add(new FeatureLayer(table18_15));

        featureLayers19.add(new FeatureLayer(table19_0));
        featureLayers19.add(new FeatureLayer(table19_1));
        featureLayers19.add(new FeatureLayer(table19_2));
        featureLayers19.add(new FeatureLayer(table19_3));
        featureLayers19.add(new FeatureLayer(table19_4));
        featureLayers19.add(new FeatureLayer(table19_5));
        featureLayers19.add(new FeatureLayer(table19_6));
        featureLayers19.add(new FeatureLayer(table19_7));
        featureLayers19.add(new FeatureLayer(table19_8));
        featureLayers19.add(new FeatureLayer(table19_9));
        featureLayers19.add(new FeatureLayer(table19_10));
        featureLayers19.add(new FeatureLayer(table19_11));
        featureLayers19.add(new FeatureLayer(table19_12));
        featureLayers19.add(new FeatureLayer(table19_13));
        featureLayers19.add(new FeatureLayer(table19_14));
        featureLayers19.add(new FeatureLayer(table19_15));

        layerFromTableComisarias = new FeatureLayer(tableComisarias);
        layerFromTableResidencias = new FeatureLayer(tableResidencias);

        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              openMensaje();
            }
        });
        btn_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupLocationCurrent();
            }
        });

    }

   /*METODOS UBICACION*/
    private void setupLocationDisplay() {
        mLocationDisplay = mapView.getLocationDisplay();
        mLocationDisplay.addDataSourceStatusChangedListener(dataSourceStatusChangedEvent -> {
            if (dataSourceStatusChangedEvent.isStarted() || dataSourceStatusChangedEvent.getError() == null) {
                return;
            }

            int requestPermissionsCode = 2;
            String[] requestPermissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

            if (!(ContextCompat.checkSelfPermission(MainActivity.this, requestPermissions[0]) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(MainActivity.this, requestPermissions[1]) == PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(MainActivity.this, requestPermissions, requestPermissionsCode);
            } else {
                String message = String.format("Error in DataSourceStatusChangedListener: %s",
                        dataSourceStatusChangedEvent.getSource().getLocationDataSource().getError().getMessage());
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
        mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.COMPASS_NAVIGATION);
        mLocationDisplay.startAsync();
    }

    private void setupLocationCurrent() {
        mLocationDisplay = mapView.getLocationDisplay();
        mLocationDisplay.addDataSourceStatusChangedListener(dataSourceStatusChangedEvent -> {
            if (dataSourceStatusChangedEvent.isStarted() || dataSourceStatusChangedEvent.getError() == null) {
                return;
            }

            int requestPermissionsCode = 2;
            String[] requestPermissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

            if (!(ContextCompat.checkSelfPermission(MainActivity.this, requestPermissions[0]) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(MainActivity.this, requestPermissions[1]) == PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(MainActivity.this, requestPermissions, requestPermissionsCode);
            } else {
                String message = String.format("Error in DataSourceStatusChangedListener: %s",
                        dataSourceStatusChangedEvent.getSource().getLocationDataSource().getError().getMessage());
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
        mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.NAVIGATION);
        mLocationDisplay.startAsync();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mLocationDisplay.startAsync();
        } else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.location_permission_denied), Toast.LENGTH_SHORT).show();
        }
    }

    /*METODOS DE BUSQUEDA DE DIRECCION*/

//    private void queryLocator(final String query) {
//        if (query != null && query.length() > 0) {
//            mLocatorTask.cancelLoad();
//            final ListenableFuture<List<GeocodeResult>> geocodeFuture = mLocatorTask.geocodeAsync(query, mGeocodeParameters);
//            geocodeFuture.addDoneListener(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        List<GeocodeResult> geocodeResults = geocodeFuture.get();
//                        if (geocodeResults.size() > 0) {
//                            displaySearchResult(geocodeResults.get(0));
//                        } else {
//                            showError(getString(R.string.nothing_found) + " " + query);
//                        }
//                    } catch (InterruptedException | ExecutionException e) {
//                        showError(e.getMessage());
//                    }
//                    geocodeFuture.removeDoneListener(this); // Done searching, remove the listener.
//                }
//            });
//        }
//    }

//    private void displaySearchResult(GeocodeResult geocodedLocation) {
//        String displayLabel = geocodedLocation.getLabel();
//        TextSymbol textLabel = new TextSymbol(18, displayLabel, Color.rgb(192, 32, 32), TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.BOTTOM);
//        Graphic textGraphic = new Graphic(geocodedLocation.getDisplayLocation(), textLabel);
//        Graphic mapMarker = new Graphic(geocodedLocation.getDisplayLocation(), geocodedLocation.getAttributes(),
//                new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, Color.rgb(255, 0, 0), 12.0f));
//        ListenableList allGraphics = mGraphicsOverlay.getGraphics();
//        allGraphics.clear();
//        allGraphics.add(mapMarker);
//        allGraphics.add(textGraphic);
//        mapView.setViewpointCenterAsync(geocodedLocation.getDisplayLocation());
//    }
//
//    private void setupLocator() {
//        String locatorService = "http://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer";
//        mLocatorTask = new LocatorTask(locatorService);
//        mLocatorTask.addDoneLoadingListener(() -> {
//            if (mLocatorTask.getLoadStatus() == LoadStatus.LOADED) {
//                mGeocodeParameters = new GeocodeParameters();
//                mGeocodeParameters.getResultAttributeNames().add("*");
//                mGeocodeParameters.setMaxResults(1);
//                mGraphicsOverlay = new GraphicsOverlay();
//                mapView.getGraphicsOverlays().add(mGraphicsOverlay);
//            } else if (mSearchView != null) {
//                mSearchView.setEnabled(false);
//            }
//        });
//        mLocatorTask.loadAsync();
//    }
//
//    private void showError(String message) {
//        Log.d("Search", message);
//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//    }


//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            queryLocator(intent.getStringExtra(SearchManager.QUERY));
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.action_buscar);
        if (searchMenuItem != null) {
            mSearchView = (SearchView) searchMenuItem.getActionView();
            if (mSearchView != null) {
                SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                mSearchView.setIconifiedByDefault(false);
            }
        }
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
                                sendDelitoGrafico("2016");
                                break;
                            case 1:
                                sendDelitoGrafico("2017");
                                break;
                            case 2:
                                sendDelitoGrafico("2018");
                                break;
                            case 3:
                                sendDelitoGrafico("2019");

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
            if(!map.getOperationalLayers().contains(capa))
            {map.getOperationalLayers().add(capa);}
        }
        else
        { map.getOperationalLayers().remove(capa);}
    }

    /*SETEAR ESTADOS A LOS ARRAYLIST AL INICIAR*/
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
            for(int i=0;i<16;i++)
            {estados16.add(i,false);}
        }
        if(estados17.size()==0){
            for(int i=0;i<16;i++)
            {estados17.add(i,false);}
        }
        if(estados18.size()==0){
            for(int i=0;i<16;i++)
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
            for(int i=0;i<16;i++){
                //Log.i("estado"+i,""+estados16.get(i)+"-"+stateLayers.get(i));
                estados16.add(i, stateLayers.get(i));
                addLayers(estados16.get(i), featureLayers16.get(i));
            }
        }
        else
        if(anio=="2017"){
            for(int i=0;i<16;i++){
                estados17.add(i,stateLayers.get(i));
                addLayers(estados17.get(i),featureLayers17.get(i));
            }
        }
        else
        if(anio=="2018"){
            for(int i=0;i<16;i++){
                estados18.add(i,stateLayers.get(i));
                addLayers(estados18.get(i),featureLayers18.get(i));
            }
        }
        else
        if(anio=="2019"){
            for(int i=0;i<16;i++){
                estados19.add(i,stateLayers.get(i));
                addLayers(estados19.get(i),featureLayers19.get(i));
            }
        }
    }

    //Metodo que recibe datos de DialogResidencias//
    @Override
    public void receiveResidencia(ArrayList<Boolean> estados) {
        estadosResidencia.add(0,estados.get(0));
        addLayers(estadosResidencia.get(0),layerFromTableResidencias);
    }


    //Metodo que envia datos DialogComisarias//
    public void sendComisaria(ArrayList<Boolean> estados) {
        DialogComisarias form = DialogComisarias.newInstance(estados);
        form.show(getSupportFragmentManager(), DialogComisarias.TAG);

    }

    //Metodo que envia datos DialogDelitos//
    public void sendDelito(String anio,ArrayList<Boolean> estados) {
        DialogDelitos form = DialogDelitos.newInstance(anio,estados);
        form.show(getSupportFragmentManager(), DialogDelitos.TAG);
    }

    //Metodo que envia datos DialogResidencias//
    public void sendResidencia(ArrayList<Boolean> estados) {
        DialogResidencias form = DialogResidencias.newInstance(estados);
        form.show(getSupportFragmentManager(), DialogResidencias.TAG);

    }

    //Metodo que envia datos DialogDelitosGraficos//
    public void sendDelitoGrafico(String anio) {
        DialogDelitosGraficos form = DialogDelitosGraficos.newInstance(anio);
        form.show(getSupportFragmentManager(), DialogDelitosGraficos.TAG);
    }

    //Metodo que envia datos DialogMensajes//
    public void openMensaje() {
        DialogMensajes form = new DialogMensajes();
        form.show(getSupportFragmentManager(), DialogResidencias.TAG);

    }




}
