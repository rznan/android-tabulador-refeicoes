package br.com.renan.trabalho_semestral;

import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.renan.trabalho_semestral.controller.AlimentoController;
import br.com.renan.trabalho_semestral.controller.IController;
import br.com.renan.trabalho_semestral.model.Alimento;
import br.com.renan.trabalho_semestral.model.Refeicao;
import br.com.renan.trabalho_semestral.support.SafeParser;

/**
 *@author: renan santos carvalho
 */
public class AlimentoFragment extends BaseCRUDFragment<Alimento> {

    IController<Alimento> controller;

    private EditText etIdA;
    private EditText etCaloriasA;
    private EditText etCarboidratosA;
    private EditText etGorduraA;
    private EditText etNomeA;
    private EditText etProteinasA;
    private Button btnPesquisarA;
    private Button btnAtualizarA;
    private Button btnSalvarA;
    private Button btnListarA;
    private Button btnDeletarA;
    private TextView tvResultA;


    @Override
    public void initialize() {
        etIdA = view.findViewById(R.id.etIdA);
        etCaloriasA = view.findViewById(R.id.etCaloriasA);
        etCarboidratosA = view.findViewById(R.id.etCarboidratosA);
        etGorduraA = view.findViewById(R.id.etGorduraA);
        etNomeA = view.findViewById(R.id.etNomeA);
        etProteinasA = view.findViewById(R.id.etProteinasA);

        btnPesquisarA = view.findViewById(R.id.btnPesquisarA);
        btnPesquisarA.setOnClickListener(e -> super.findOne());
        btnAtualizarA = view.findViewById(R.id.btnAtualizarA);
        btnAtualizarA.setOnClickListener(e -> super.update());
        btnSalvarA = view.findViewById(R.id.btnSalvarA);
        btnSalvarA.setOnClickListener(e -> super.insert());
        btnListarA = view.findViewById(R.id.btnListarA);
        btnListarA.setOnClickListener(e -> super.findAll());
        btnDeletarA = view.findViewById(R.id.btnDeletarA);
        btnDeletarA.setOnClickListener(e -> super.delete());

        tvResultA = view.findViewById(R.id.tvResultA);

        controller = new AlimentoController(null);
    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_alimento;
    }

    @Override
    public void clearFields() {
        this.etIdA.setText("");
        this.etCaloriasA.setText("");
        this.etCarboidratosA.setText("");
        this.etGorduraA.setText("");
        this.etProteinasA.setText("");
        this.etNomeA.setText("");
    }

    @Override
    public TextView getResultTextview() {
        return tvResultA;
    }

    @Override
    public IController<Alimento> getController() {
        return controller;
    }

    @Override
    public Alimento viewToObject() {
        int id = SafeParser.safeParseInt(this.etIdA.getText().toString(), -1);
        String nome = this.etNomeA.getText().toString();
        int prot = SafeParser.safeParseInt(this.etProteinasA.getText().toString(), -1);
        int cal = SafeParser.safeParseInt(this.etCaloriasA.getText().toString(), -1);
        int cab = SafeParser.safeParseInt(this.etCarboidratosA.getText().toString(), -1);
        int fat = SafeParser.safeParseInt(this.etGorduraA.getText().toString(), -1);
        return new Alimento(id, cal, nome, prot, cab, fat);
    }

    @Override
    public void objectToView(Alimento alimento) {
        this.etIdA.setText(String.valueOf(alimento.getId()));
        this.etNomeA.setText(alimento.getNome());
        this.etCaloriasA.setText(String.valueOf(alimento.getCalorias()));
        this.etProteinasA.setText( String.valueOf(alimento.getProteinas()) );
        this.etCarboidratosA.setText( String.valueOf(alimento.getCarboidratos()));
        this.etGorduraA.setText(String.valueOf(alimento.getGorduras()));
    }
}