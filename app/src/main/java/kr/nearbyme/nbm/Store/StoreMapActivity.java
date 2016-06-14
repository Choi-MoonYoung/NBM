package kr.nearbyme.nbm.Store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import kr.nearbyme.nbm.R;

public class StoreMapActivity extends FragmentActivity implements OnMapReadyCallback {
    public static final String EXTRA_SHOP_LOCX = "shop_locX";
    public static final String EXTRA_SHOP_LOCY = "shop_locY";

    double shop_locX;
    double shop_locY;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        shop_locX = intent.getDoubleExtra(EXTRA_SHOP_LOCX, -1);
        shop_locY = intent.getDoubleExtra(EXTRA_SHOP_LOCY, -1);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.d("shop locationX", shop_locX + "");
        LatLng shop = new LatLng(shop_locY, shop_locX);
        mMap.addMarker(new MarkerOptions().position(shop));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(shop));

        CameraPosition position = new CameraPosition.Builder()
                .target(shop)
                .zoom(15f)
                .build();

        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);

        if (mMap != null) {
            mMap.moveCamera(update);
        }


    }

}
