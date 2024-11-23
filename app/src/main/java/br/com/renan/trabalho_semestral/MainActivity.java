package br.com.renan.trabalho_semestral;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 *@author: renan santos carvalho
 */
public class MainActivity extends AppCompatActivity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle b = getIntent().getExtras();
        if(b != null) {
            loadFragment(b);
        }
        else {
            fragment = new StartFragment();
            loadFragment();
        }

    }

    private void loadFragment(Bundle b) {
        String tipo = b.getString("Tipo");
        if(tipo.equalsIgnoreCase("Alimento")) {
            fragment = new AlimentoFragment();
        }
        else if(tipo.equalsIgnoreCase("Bebida")) {
            fragment = new BebidaFragment();
        }
        else if(tipo.equalsIgnoreCase("Refeição")) {
            fragment = new ResumoRefeicaoFragment();
        }
        else if(tipo.equalsIgnoreCase("AddRefeição")) {
            fragment = new RefeicaoFragment();
        }
        else if(tipo.equalsIgnoreCase("Null")) {
            fragment = new StartFragment();
        }
        loadFragment();
    }

    private void loadFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Bundle b = new Bundle();
        Intent i = new Intent(this, MainActivity.class);

        if(id == R.id.itemAlimentos) {
            b.putString("Tipo", "Alimento");
        }
        else if(id == R.id.itemBebidas) {
            b.putString("Tipo", "Bebida");
        }
        else if(id == R.id.itemResumo) {
            b.putString("Tipo", "Refeição");
        }
        else if(id == R.id.itemRefeicao) {
            b.putString("Tipo", "AddRefeição");
        }
        else {
            b.putString("Tipo", "Null");
        }

        i.putExtras(b);
        this.startActivity(i);
        this.finish();
        return true;
    }
}