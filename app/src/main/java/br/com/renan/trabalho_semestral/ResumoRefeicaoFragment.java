package br.com.renan.trabalho_semestral;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.time.LocalDate;

import br.com.renan.trabalho_semestral.controller.IController;
import br.com.renan.trabalho_semestral.controller.RefeicaoController;
import br.com.renan.trabalho_semestral.model.Refeicao;
import br.com.renan.trabalho_semestral.support.SafeParser;

/**
 *@author: renan santos carvalho
 */
public class ResumoRefeicaoFragment extends BaseCRUDFragment<Refeicao> {

    IController<Refeicao> refeicaoIController;

    private EditText etIdRs;
    private Button btnPesquisarRs;
    private Button btnListarRs;
    private Button btnDeletarRs;
    private TextView tvResultRs;

    public ResumoRefeicaoFragment() {
        super();
    }

    @Override
    public void initialize() {
        etIdRs = view.findViewById(R.id.etIdRs);

        btnPesquisarRs = view.findViewById(R.id.btnPesquisarRs);
        btnPesquisarRs.setOnClickListener(e -> super.findOne());
        btnListarRs = view.findViewById(R.id.btnListarRs);
        btnListarRs.setOnClickListener(e -> super.findAll());
        btnDeletarRs = view.findViewById(R.id.btnDeletarRs);
        btnDeletarRs.setOnClickListener(e -> super.delete());

        tvResultRs = view.findViewById(R.id.tvResultRs);

        refeicaoIController = new RefeicaoController(null);
    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_resumo_refeicao;
    }

    @Override
    public void clearFields() {
        this.etIdRs.setText("");
    }

    @Override
    public TextView getResultTextview() {
        return tvResultRs;
    }

    @Override
    public IController<Refeicao> getController() {
        return refeicaoIController;
    }

    @Override
    public Refeicao viewToObject() {
        int id = SafeParser.safeParseInt(this.etIdRs.getText().toString(), -1);
        return new Refeicao(id, LocalDate.now());
    }

    @Override
    public void objectToView(Refeicao refeicao) {
        tvResultRs.setText(refeicao.detalharItens());
    }

    @Override
    public void insert() {
    }

    @Override
    public void update() {
    }
}