package br.com.renan.trabalho_semestral;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

import br.com.renan.trabalho_semestral.controller.AlimentoController;
import br.com.renan.trabalho_semestral.controller.BebidaController;
import br.com.renan.trabalho_semestral.controller.IController;
import br.com.renan.trabalho_semestral.controller.RefeicaoController;
import br.com.renan.trabalho_semestral.model.Alimento;
import br.com.renan.trabalho_semestral.model.Bebida;
import br.com.renan.trabalho_semestral.model.Consumivel;
import br.com.renan.trabalho_semestral.model.Refeicao;
import br.com.renan.trabalho_semestral.persistence.ConsumivelDao;
import br.com.renan.trabalho_semestral.persistence.RefeicaoDao;
import br.com.renan.trabalho_semestral.support.SafeParser;

/**
 *@author: renan santos carvalho
 */
public class RefeicaoFragment extends BaseCRUDFragment<Refeicao> {

    IController<Refeicao> refeicaoIController;
    IController<Alimento> alimentoIController;
    IController<Bebida> bebidaIController;

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

    public RefeicaoFragment() {
        super();
    }

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
        btnSalvarR.setOnClickListener(e -> {
            if(SafeParser.safeParseInt(etIdR.getText().toString(), 0) <= 0) {
                Toast.makeText(view.getContext(), "0 não é um id válido", Toast.LENGTH_LONG).show();
            } else {
                super.insert();
            }
        });

        tvResultR = view.findViewById(R.id.tvResultR);

        rgConsumivelR = view.findViewById(R.id.rgConsumivelR);
        rgConsumivelR.setOnCheckedChangeListener((a, b) -> populateSpinner());
        rbAlimentoR = view.findViewById(R.id.rbAlimentoR);
        rbBebidaR = view.findViewById(R.id.rbBebidaR);
        rbAlimentoR.setSelected(true);

        spinner = view.findViewById(R.id.spinner);

        refeicaoIController = new RefeicaoController(new RefeicaoDao(view.getContext()));
        alimentoIController = new AlimentoController(new ConsumivelDao(view.getContext()));
        bebidaIController = new BebidaController(new ConsumivelDao(view.getContext()));

        targetRefeicao = new Refeicao();

        populateSpinner();

    }

    private void populateSpinner() {

        try {
            if(rbBebidaR.isChecked()) {
                List<Bebida> list = bebidaIController.list();
                Bebida b0 = new Bebida(0, 0, "Selecione um item", 0, 0);
                list.add(0, b0);
                ArrayAdapter<Bebida> arrayAdapter = new ArrayAdapter<Bebida>(view.getContext(),
                        android.R.layout.simple_spinner_item,
                        list
                );
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
            } else {
                List<Alimento> list = alimentoIController.list();
                Alimento b0 = new Alimento(0, 0, "Selecione um item", 0, 0 ,0);
                list.add(0, b0);
                ArrayAdapter<Alimento> arrayAdapter = new ArrayAdapter<Alimento>(view.getContext(),
                        android.R.layout.simple_spinner_item,
                        list
                );
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void addItem() {
        int quant = SafeParser.safeParseInt(this.etQuantItemR.getText().toString(), 1);

        if(spinner.getSelectedItemPosition() == 0) {
            Toast.makeText(view.getContext(), "Selecione um item", Toast.LENGTH_LONG).show();
        } else {
                targetRefeicao.addItem((Consumivel) spinner.getSelectedItem(), quant);
                getResultTextview().setText(targetRefeicao.detalharItens());
        }

    }

    private void removeItem() {
        Consumivel c = (Consumivel) spinner.getSelectedItem();
        targetRefeicao.removeItem(c.getId());
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
        return refeicaoIController;
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
        spinner.setSelection(0);
        getResultTextview().setText(targetRefeicao.detalharItens());
    }
}