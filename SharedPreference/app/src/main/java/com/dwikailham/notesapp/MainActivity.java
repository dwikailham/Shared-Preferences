package com.dwikailham.notesapp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.dwikailham.notesapp.fragments.LoginFragment;
import com.dwikailham.notesapp.fragments.NoteFragment;
import com.dwikailham.notesapp.fragments.SettingsFragment;
import com.dwikailham.notesapp.models.Session;
import com.dwikailham.notesapp.models.Settings;
import com.dwikailham.notesapp.models.User;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFragmentListener,NoteFragment.OnNoteFragmentListener {
    public Settings settings;
    public Session session;

    private void addFragment() {
        Fragment fragment = null;
        if (session.isLogin()) {
            fragment = new NoteFragment();
            ((NoteFragment) fragment).setListener(this);
        } else {
            fragment = new LoginFragment();
            ((LoginFragment) fragment).setListener(this);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_settings){
            createSettingsFragment();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        settings = new Settings(this);
        session = new Session(settings);


//        getSupportActionBar().setBackgroundDrawable(
//                new ColorDrawable(Color.parseColor("#5e9c00")));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorAccent));
//        }

        addFragment();
    }

    @Override
    public void onLoginClicked(View view, String username, String password) {
        User user = session.doLogin(username, password);
        String message = "Authentication failed";
        if (user != null) {
            message = "Welcome " + username;
            session.setUser(username);
        }
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
        addFragment();
    }

    @Override
    public void onLogoutClick() {
        session.doLogout();
        addFragment();
    }

    private void createSettingsFragment(){
        Fragment settingsFragment = new SettingsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, settingsFragment)
                .addToBackStack(null)
                .commit();
    }
}
