package com.andre.larfinancas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.andre.larfinancas.databinding.ActivityMainBinding;
import com.andre.larfinancas.fragments.LoginFragment;
import com.andre.larfinancas.fragments.ProductGridFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";

    //CONFIG EMULATOR FIREBASE
    private static final int PORT_EMULATOR_AUTH = 9099;
    private static final String HOST_EMULATOR_AUTH = "10.0.2.2";
    private static final int PORT_EMULATOR_DATABASE = 9000;
    private static final String HOST_EMULATOR_DATABASE = "10.0.2.2";

    private SharedPreferences mSharedPreferences;
    private GoogleSignInClient mSignInClient;

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Google SingIn
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mSignInClient = GoogleSignIn.getClient(this, gso);


        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //Instace firebase
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        if (savedInstanceState == null && mFirebaseAuth.getCurrentUser() == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new ProductGridFragment())
                .commit();

    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    public void signOut() {
        mFirebaseAuth.signOut();
        mSignInClient.signOut();
        navigateTo(new LoginFragment(), false);
    }

    @Nullable
    private String getUserPhotoUrl() {
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user != null && user.getPhotoUrl() != null) {
            return user.getPhotoUrl().toString();
        }

        return null;
    }

    private String getUserName() {
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user != null) {
            return user.getDisplayName();
        }

        return ANONYMOUS;
    }
}