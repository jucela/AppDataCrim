package com.inei.appdatacrim.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

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


    private ArrayList<String> listDataHeader;
    private ExpandableListView expListView;
    private HashMap<String, List<String>> listDataChild;
    private ExpandListAdapter listAdapter;
    private MenuItem menuItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,R.style.ThemeOverlay_MaterialComponents_Dialog);
                                final View dialogView = MainActivity.this.getLayoutInflater().inflate(R.layout.layout_denuncias_2016, null);

                                alert.setTitle("Denuncias Delitos 2016");
                                alert.setView(dialogView);
                                alert.setPositiveButton("Aceptar",null);
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
                                Log.i("posicion","1-2");
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


}
