package com.example.notesapp.simplenotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.widget.CompoundButtonCompat;
import androidx.fragment.app.DialogFragment;

import com.example.notesapp.Activity_Login;
import com.example.notesapp.MainScreenActivity;
import com.example.notesapp.R;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private int fontSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
        getSettings(preferences);
        applySettings();
    }
    public void logout(View view) {
        // Clear session data in the database
//

        logoutUser();
        startActivity(new Intent(SettingsActivity.this, Activity_Login.class));

    }
    // Method to clear user registration status (logout)
    private void logoutUser() {
        // Get the SharedPreferences editor
        SharedPreferences.Editor editor = getSharedPreferences("user_prefs", Context.MODE_PRIVATE).edit();
        // Remove the "registered" key
        editor.remove("registered");
        // Apply the changes
        editor.apply();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getSettings(SharedPreferences preferences) {
        fontSize = preferences.getInt(HelperUtils.PREFERENCE_FONT_SIZE, 16); // Default font size is 16dp
    }

    private void applySettings() {
        // Apply font size to UI elements
        TextView textView = findViewById(R.id.tv_accent);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fontSize);

        // Initialize and set font size for buttons
        Button btnLogout = findViewById(R.id.btn_logout);
        Button btnHome = findViewById(R.id.btn_home);
        Button btnApply = findViewById(R.id.btn_apply);

        btnLogout.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fontSize);
        btnHome.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fontSize);
        btnApply.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fontSize);
    }

    public void saveSettings(View view) {
        // Save font size to preferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(HelperUtils.PREFERENCE_FONT_SIZE, fontSize);
        editor.apply();

        startActivity(new Intent(SettingsActivity.this, NotesListActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
    public void home(View view) {
        // Save font size to preferences


        startActivity(new Intent(SettingsActivity.this, MainScreenActivity.class));
        finish();
    }

    public void changeFontSize(View view) {
        // Show a dialog to select font size
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wählen Sie Schriftgröße");

        // Add a list of font size options
        String[] fontSizes = {"Klein", "Mittel", "Groß"};
        builder.setItems(fontSizes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        fontSize = 12; // Small
                        break;
                    case 1:
                        fontSize = 16; // Medium
                        break;
                    case 2:
                        fontSize = 20; // Large
                        break;
                }
                applySettings(); // Update UI
            }
        });

        builder.show();
    }
}
