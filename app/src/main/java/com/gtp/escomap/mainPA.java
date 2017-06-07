package com.gtp.escomap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class mainPA extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Crear_evento.OnFragmentInteractionListener,
        Nueva_solicitud.OnFragmentInteractionListener,
        Consultar_solicitudes.OnFragmentInteractionListener,
        Nuevo_usuario.OnFragmentInteractionListener,
        Modificar_usuario.OnFragmentInteractionListener,
        Consultar_eventos.OnFragmentInteractionListener,
        Crear_clase.OnFragmentInteractionListener,
        Consultar_clases.OnFragmentInteractionListener,
        Crear_profesor.OnFragmentInteractionListener,
        Consultar_profesores.OnFragmentInteractionListener,
        Consultar_mapa.OnFragmentInteractionListener {

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        setTitle("Bienvenido " + name);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView txViewName = (TextView) headerView.findViewById(R.id.lblName);
        txViewName.setText(name);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_pa_drawer, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.Nueva_solicitud) {
            fragment = new Nueva_solicitud();
        } else if (id == R.id.Consultar_solicitudes) {
            fragment = new Consultar_solicitudes();
        } else if (id == R.id.Nuevo_usuario) {
            fragment = new Nuevo_usuario();
        } else if (id == R.id.Modificar_usuario) {
            fragment = new Modificar_usuario();
        } else if (id == R.id.Crear_evento) {
            fragment = new Crear_evento();
        } else if (id == R.id.Consultar_eventos) {
            fragment = new Consultar_eventos();
        } else if (id == R.id.Crear_clase) {
            fragment = new Crear_clase();
        } else if (id == R.id.Consultar_clases) {
            fragment = new Consultar_clases();
        } else if (id == R.id.Crear_profesor) {
            fragment = new Crear_profesor();
        } else if (id == R.id.Consultar_profesores) {
            fragment = new Consultar_profesores();
        } else if (id == R.id.Consultar_mapa) {
            fragment = new Consultar_mapa();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_frame_pa, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
