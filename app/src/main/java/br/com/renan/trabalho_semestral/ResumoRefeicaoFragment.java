package br.com.renan.trabalho_semestral;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;

import br.com.renan.trabalho_semestral.controller.IController;
import br.com.renan.trabalho_semestral.controller.RefeicaoController;
import br.com.renan.trabalho_semestral.model.Refeicao;
import br.com.renan.trabalho_semestral.persistence.RefeicaoDao;
import br.com.renan.trabalho_semestral.support.SafeParser;

/**
 *@author: renan santos carvalho
 */
public class ResumoRefeicaoFragment extends BaseCRUDFragment<Refeicao> {

    IController<Refeicao> refeicaoIController;

    private EditText etIdRs;
    private TextView tvResultRs;

    public ResumoRefeicaoFragment() {
        super();
    }

    @Override
    public void initialize() {
        etIdRs = view.findViewById(R.id.etIdRs);

        Button btnPesquisarRs = view.findViewById(R.id.btnPesquisarRs);
        btnPesquisarRs.setOnClickListener(e -> super.findOne());
        Button btnListarRs = view.findViewById(R.id.btnListarRs);
        btnListarRs.setOnClickListener(e -> super.findAll());
        Button btnDeletarRs = view.findViewById(R.id.btnDeletarRs);
        btnDeletarRs.setOnClickListener(e -> super.delete());

        tvResultRs = view.findViewById(R.id.tvResultRs);

        refeicaoIController = new RefeicaoController(new RefeicaoDao(view.getContext()));
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