package br.com.renan.trabalho_semestral;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.renan.trabalho_semestral.controller.IController;
import br.com.renan.trabalho_semestral.model.Consumivel;
import br.com.renan.trabalho_semestral.model.Consumo;
import br.com.renan.trabalho_semestral.model.Refeicao;
import br.com.renan.trabalho_semestral.support.SafeParser;

public class RefeicaoFragment extends BaseCRUDFragment<Refeicao> {

    List<Consumivel> consumivelFullList;
    Refeicao targetRefeicao;

    private EditText etIdR;
    private EditText etQuantItemR;
    private EditText etDataR;
    private Button btnPesquisarR;
    private Button btnRemoveItemR;
    private Button btnAddItemR;
    private Button btnUpdateR;
    private Button btnSalvarR;
    private TextView tvResultR;
    private RadioGroup rgConsumivelR;
    private RadioButton rbAlimentoR;
    private RadioButton rbBebidaR;
    private Spinner spinner;

    @Override
    public void initialize() {
        etIdR = view.findViewById(R.id.etIdR);
        etQuantItemR = view.findViewById(R.id.etQuantItemR);
        etDataR = view.findViewById(R.id.etDataR);

        btnPesquisarR = view.findViewById(R.id.btnPesquisarR);
        btnPesquisarR.setOnClickListener(e -> super.findOne());

        btnRemoveItemR = view.findViewById(R.id.btnRemoveItemR);
        btnRemoveItemR.setOnClickListener(e -> removeItem());
        btnAddItemR = view.findViewById(R.id.btnAddItemR);
        btnAddItemR.setOnClickListener(e -> addItem());

        btnUpdateR = view.findViewById(R.id.btnUpdateR);
        btnUpdateR.setOnClickListener(e -> super.update());
        btnSalvarR = view.findViewById(R.id.btnSalvarR);
        btnSalvarR.setOnClickListener(e -> super.insert());

        tvResultR = view.findViewById(R.id.tvResultR);

        rgConsumivelR = view.findViewById(R.id.rgConsumivelR);
        rbAlimentoR = view.findViewById(R.id.rbAlimentoR);
        rbBebidaR = view.findViewById(R.id.rbBebidaR);
        rbAlimentoR.setChecked(true);

        spinner = view.findViewById(R.id.spinner);

        populateSpinner();
    }

    private void populateSpinner() {

    }

    private void addItem() {
        // select item from spinner
        // consumivelList.add(c);
        getResultTextview().setText(targetRefeicao.detalharItens());
    }

    private void removeItem() {
        // select item from spinner
        // consumivelList.remove(c);
        getResultTextview().setText(targetRefeicao.detalharItens());
    }

    @Override
    public int getFragmentId() {
        return R.layout.fragment_refeicao;
    }

    @Override
    public void clearFields() {
        this.etIdR.setText("");
        this.etQuantItemR.setText("");
        this.etDataR.setText("");
    }

    @Override
    public TextView getResultTextview() {
        return tvResultR;
    }

    @Override
    public IController<Refeicao> getController() {
        return null;
    }

    @Override
    public Refeicao viewToObject() {
        int i = SafeParser.safeParseInt(this.etIdR.getText().toString(), -1);
        LocalDate dt = SafeParser.safeParseLocalDate(this.etDataR.getText().toString(), LocalDate.now());

        targetRefeicao.setId(i);
        targetRefeicao.setData(dt);

        return targetRefeicao;
    }

    @Override
    public void objectToView(Refeicao refeicao) {
        this.etIdR.setText(String.valueOf(refeicao.getId()));
        this.etDataR.setText(
                refeicao.getData().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
        );
        this.etQuantItemR.setText(String.valueOf(0));
        targetRefeicao = refeicao;
        getResultTextview().setText(targetRefeicao.detalharItens());
    }
}