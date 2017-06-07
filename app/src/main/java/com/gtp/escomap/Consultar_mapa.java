package com.gtp.escomap;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Consultar_mapa.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Consultar_mapa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Consultar_mapa extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    MapView mapView;
    ListView lista;
    GoogleMap map;
    CustomAdapter eventosAdapter;

    private Consultar_mapa.OnFragmentInteractionListener mListener;

    public Consultar_mapa() {
        // Required empty public constructor
    }

    Evento[] eventos = new Evento[]{
            new Evento(19.504571, -99.146764,"Reclutamiento Microsoft","","Sala 14"),
            new Evento(19.504850, -99.146741,"Baile de primavera","","Explanada"),
            new Evento(19.504664, -99.146848,"Votación subdirector","","Trofeos"),
            new Evento(19.503884, -99.147209,"Presentación bandas","","Campo"),
            new Evento(19.504574, -99.145091,"Torneo fútbol","","Campo fútbol")
    };

    public void addEvents(){
        eventosAdapter = new CustomAdapter(eventos,getContext());
        lista.setAdapter(eventosAdapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                map.clear();
                Evento e = eventosAdapter.getItem(position);

                Marker m = map.addMarker(new MarkerOptions().
                        position(new LatLng(e.lat,e.lon)).
                        title(e.nombre)
                );
                m.showInfoWindow();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(e.lat,e.lon), 19);
                map.animateCamera(cameraUpdate);
            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Consultar_mapa.
     */
    // TODO: Rename and change types and number of parameters
    public static Consultar_mapa newInstance(String param1, String param2) {
        Consultar_mapa fragment = new Consultar_mapa();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Gets the MapView from the XML layout and creates it
        View v = inflater.inflate(R.layout.fragment_consultar_mapa, container, false);
        getActivity().setTitle("Eventos de hoy");
        mapView = (MapView) v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.getUiSettings().setMyLocationButtonEnabled(true);
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                            },1);
                    return;
                }
                map.getUiSettings().setMyLocationButtonEnabled(true);
                map.setMyLocationEnabled(true);
                map.setMapType(map.MAP_TYPE_NORMAL);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(19.504553, -99.146909), 17);
                map.animateCamera(cameraUpdate);

            }
        });
        lista = (ListView) v.findViewById(R.id.list_events);
        addEvents();
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
class CustomAdapter extends BaseAdapter{
    Evento[] data;
    Context context;
    public CustomAdapter(Evento[] objects,Context c) {
        data = objects;
        context = c;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Evento getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View res;
        ViewCache cache;
        if(convertView==null){
            LayoutInflater i = LayoutInflater.from(context);
            res = i.inflate(R.layout.item_evento,null);
            cache = new ViewCache();
            cache.text_nombre = (TextView) res.findViewById(R.id.text_nombre);
            cache.text_espacio = (TextView) res.findViewById(R.id.text_espacio);
            cache.text_horario = (TextView) res.findViewById(R.id.text_horario);
            res.setTag(cache);
        }
        else{
            res = convertView;
            cache = (ViewCache) res.getTag();
        }
        Evento e = data[position];
        cache.text_nombre.setText(e.nombre);
        cache.text_espacio.setText(e.espacio);
        cache.text_horario.setText("00:00 - 00:00");
        return res;
    }
}


class ViewCache{
    TextView text_nombre;
    TextView text_espacio;
    TextView text_horario;
}