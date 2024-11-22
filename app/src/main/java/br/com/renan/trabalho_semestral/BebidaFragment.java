package br.com.renan.trabalho_semestral;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.renan.trabalho_semestral.controller.IController;
import br.com.renan.trabalho_semestral.model.Alimento;
import br.com.renan.trabalho_semestral.model.Bebida;
import br.com.renan.trabalho_semestral.support.SafeParser;

public class BebidaFragment extends BaseCRUDFragment<Bebida> {

    private EditText etIdB;
    private EditText etCaloriasA;
    private EditText etAcucarB;
    private EditText etNomeB;
    private EditText etVolumeB;

    private Button btnPesquisarB;
    private Button btnAtualizarB;
    private Button btnSalvarB;
    private Button btnListarB;
    private Button btnDeletarB;

    private TextView tvResultB;

    @Override
    public void initialize() {
        etIdB = view.findViewById(R.id.etIdB);
        etCaloriasA = view.findViewById(R.id.etCaloriasA);
        etAcucarB = view.findViewById(R.id.etAcucarB);
        etNomeB = view.findViewById(R.id.etNomeB);
        etVolumeB = view.findViewById(R.id.etVolumeB);

        btnPesquisarB = view.findViewById(R.id.btnPesquisarB);
        btnPesquisarB.setOnClickListener(e -> super.findOne());
        btnListarB = view.findViewById(R.id.btnListarB);
        btnListarB.setOnClickListener(e -> super.findAll());
        btnAtualizarB = view.findViewById(R.id.btnAtualizarB);
        btnAtualizarB.setOnClickListener(e -> super.update());
        btnSalvarB = view.findViewById(R.id.btnSalvarB);
        btnSalvarB.setOnClickListener(e -> super.insert());
        btnDeletarB = view.findViewById(R.id.btnDeletarB);
        btnDeletarB.setOnClickListener(e -> super.delete());

        tvResultB = view.findViewById(R.id.tvResultB);
    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_bebida;
    }

    @Override
    public void clearFields() {
        this.etIdB.setText("");
        this.etCaloriasA.setText("");
        this.etAcucarB.setText("");
        this.etNomeB.setText("");
        this.etVolumeB.setText("");
    }

    @Override
    public TextView getResultTextview() {
        return tvResultB;
    }

    @Override
    public IController<Bebida> getController() {
        return null;
    }

    @Override
    public Bebida viewToObject() {
        int id = SafeParser.safeParseInt(this.etIdB.getText().toString(), -1);
        String nme = this.etNomeB.getText().toString();
        int cal = SafeParser.safeParseInt(this.etCaloriasA.getText().toString(), -1);
        int vol = SafeParser.safeParseInt(this.etVolumeB.getText().toString(), -1);
        int sug = SafeParser.safeParseInt(this.etAcucarB.getText().toString(), -1);

        Bebida b = new Bebida(id, cal, nme, vol, sug);
        return b;
    }

    @Override
    public void objectToView(Bebida bebida) {
        this.etIdB.setText(String.valueOf(bebida.getId()));
        this.etCaloriasA.setText(String.valueOf(bebida.getCalorias()));
        this.etAcucarB.setText(String.valueOf(bebida.getAcucares()));
        this.etNomeB.setText(String.valueOf(bebida.getNome()));
        this.etVolumeB.setText(String.valueOf(bebida.getVolume()));
    }
}