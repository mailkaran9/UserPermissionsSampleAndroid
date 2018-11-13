package userpermissionssampleandroid.example.com.userpermissionssampleandroid;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import customdialog.example.com.customdialogboxandroid.CustomFragment;

public class MainActivity extends AppCompatActivity implements CustomFragment.OnFragmentInteractionListener,DialogInterface.OnDismissListener{

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1 ;
    private String permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {


            //String permissionReason = "Need contacts to let you find friends Need contacts to let you find friendsNeed contacts to let you find friends Need contacts to let you find friends Need contacts to let you find friends Need contacts to let you find friends Need contacts to let you find friends Need contacts to";
            String permissionReason = "App would like to request for access to your contact list to enable you to find friends on the app";
            CustomFragment cf = CustomFragment.newInstance(permissionReason,"","","","","");

            permission = Manifest.permission.READ_CONTACTS;

            cf.show(fm, "fragment_edit_name");

        }
        else{
            Toast.makeText(getApplicationContext(),"Permission is Already Granted",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{permission},
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getApplicationContext(),"Permission Granted",Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_SHORT).show();

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
