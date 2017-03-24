package com.example.ydeepak.BusWala;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ydeepak.BusWala.GeneralInfo.GeoLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.example.ydeepak.BusWala.GeneralInfo.busCurrentInfo;
import com.example.ydeepak.BusWala.Adapters.busInfoAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements com.google.android.gms.location.LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "LocationActivity";
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN = 1;

    Button btnFusedLocation;
    TextView tvLocation;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    String mLastUpdateTime;
    private String mUsername;
    long time;
    private busCurrentInfo mbus;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference userPrefDatabaseReference;
    private DatabaseReference requestsDatabaseReference;
    private DatabaseReference responseDataReference;




    private ChildEventListener childEventListener;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    private ArrayList<busCurrentInfo> listArr;


    private ArrayList<String> busName = new ArrayList<>();

    //Dummy id for testing
    private String userId = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = ANONYMOUS;

        //********GeoLocation Service Init**********//

        if (!isGooglePlayServicesAvailable()) {
            finish();
        }


        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        //******************************************//


        //*********************** FireBase DataBase Init

        firebaseDatabase = FirebaseDatabase.getInstance();
        userPrefDatabaseReference = firebaseDatabase.getReference().child("userPref").child(userId);
        requestsDatabaseReference = firebaseDatabase.getReference().child("requests");
        
        //*********************FireBase DataBase Ends **************//
        mGoogleApiClient.connect();
        busInfoAdapter mbusInfoAdapter = new busInfoAdapter(MainActivity.this, listArr);
        //  sendRequest();
        // ListView listView = (ListView)  findViewById(R.id.list);
        //listView.setAdapter(mbusInfoAdapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_id:
                Log.i(TAG, "add");
                updatePreference();
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, Settings.class);
                startActivity (intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updatePreference () {
        Intent intent = new Intent(this, AddBus.class);
        intent.putExtra("id", userId);
        startActivity(intent);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (busName != null)
            busName.clear();
        Log.d(TAG, "onStart fired ..............");
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop fired ..............");
        if (mGoogleApiClient != null)
            mGoogleApiClient.disconnect();
        if (mGoogleApiClient != null)
            Log.d(TAG, "isConnected ...............: " + mGoogleApiClient.isConnected());
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
        startLocationUpdates();
    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
        Log.d(TAG, "Location update started ..............: ");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed: " + connectionResult.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Firing onLocationChanged..............................................");
        mCurrentLocation = location;
        Log.i(TAG, location.getLatitude() + "----" + location.getLongitude() + "----" + location.getAccuracy());
        stopLocationUpdates();
        mGoogleApiClient.disconnect();
        attachDatabaseReadListener();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (firebaseAuth != null)
            firebaseAuth.removeAuthStateListener(authStateListener);
        removeListener();
    }


    private void removeListener() {
        if (childEventListener != null) {
            userPrefDatabaseReference.removeEventListener(childEventListener);
            childEventListener = null;
        }
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
        Log.d(TAG, "Location update stopped .......................");
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
            Log.d(TAG, "Location update resumed .....................");
        }
    }


    private void attachDatabaseReadListener() {
        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Log.i(TAG, dataSnapshot.getKey() + "--" + dataSnapshot.getValue());
                    busName.add(dataSnapshot.getValue().toString());
                    mbus = new busCurrentInfo(dataSnapshot.getValue().toString(), Long.toString(System.currentTimeMillis()), mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude(), userId);
                    requestsDatabaseReference.push().setValue(mbus);
                    Log.i(TAG, "" + busName.size());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            userPrefDatabaseReference.addChildEventListener(childEventListener);
        }
    }

}
