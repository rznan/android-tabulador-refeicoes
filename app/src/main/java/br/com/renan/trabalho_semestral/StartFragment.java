package br.com.renan.trabalho_semestral;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * @author: renan santos carvalho
 */
public class StartFragment extends Fragment {

    private View VIEW;

    public StartFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.VIEW = inflater.inflate(R.layout.fragment_start, container, false);
        return VIEW;
    }
}