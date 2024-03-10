package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.notesapp.DataBase.DatabaseHelper;
import com.example.notesapp.simplenotes.NoteActivity;
import com.example.notesapp.simplenotes.NotesListActivity;
import com.example.notesapp.simplenotes.SettingsActivity;

public class MainScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }
    public void show_notes(View view) {
        startActivity(new Intent(MainScreenActivity.this, NotesListActivity.class));
    }

    public void setting(View view) {
        startActivity(new Intent(MainScreenActivity.this, SettingsActivity.class));

    }

    public void create_notes(View view) {
        Intent intent = new Intent(this, NoteActivity.class);
startActivity(intent);
    }

    public void delete_notes(View view) {
        startActivity(new Intent(MainScreenActivity.this, NotesListActivity.class));

    }

    public void logout(View view) {
        // Clear session data in the database
//

        logoutUser();
        startActivity(new Intent(MainScreenActivity.this, Activity_Login.class));

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

}