package br.com.renan.trabalho_semestral;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.sql.SQLException;
import java.util.List;

import br.com.renan.trabalho_semestral.controller.IController;

/**
 * @author: renan santos carvalho
 */
public abstract class BaseCRUDFragment<T> extends Fragment {

    protected View view;

    protected BaseCRUDFragment() {
        super();
    }

    public abstract void initialize();

    public abstract int getFragmentId();

    public abstract void clearFields();

    public abstract TextView getResultTextview();

    public abstract IController<T> getController();

    public abstract T viewToObject();

    public abstract void objectToView(T t);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(getFragmentId(), container, false);
        initialize();
        getResultTextview().setMovementMethod(new ScrollingMovementMethod());
        return view;
    }

    public void insert() {
        IController<T> ctrl = getController();
        try {
            ctrl.insert(viewToObject());
            clearFields();
            Toast.makeText(view.getContext(), "Item Inserido com Sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void update() {
        try {
            getController().update(viewToObject());
            clearFields();
            Toast.makeText(view.getContext(), "Item Atualizado com Sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void delete() {
        try {
            getController().delete(viewToObject());
            clearFields();
            Toast.makeText(view.getContext(), "Item Deletado com Sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void findOne() {
        try {
            T one = getController().search(viewToObject());
            if(one != null) {
                objectToView(one);
            } else {
                Toast.makeText(view.getContext(), "Item não encontrado", Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            getResultTextview().setText(R.string.algo_deu_errado);
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void findAll() {
        try {
            List<T> all = getController().list();
            StringBuilder sb = new StringBuilder();
            for (T t : all) {
                sb.append(t);
                sb.append("\n");
            }
            sb.append("FIM");
            getResultTextview().setText(sb.toString());
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}