package com.elyneker.screenapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroActivity extends AppCompatActivity {

    public static final int RESULT_ADD = 1;
    public static final int RESULT_CANCELAR = 2;
    private int id;

    EditText edtMateria;
    EditText edtTitulo;
    EditText edtId;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtMateria = findViewById(R.id.editextMateria);
        edtTitulo = findViewById(R.id.editTextTitulo);
        edtId = findViewById(R.id.editTextCod);

        //receber o número do id vindo da tela inicial
        this.id = (int) getIntent().getExtras().get("codigo");

        edtId.setText(Integer.toString(id));

        if (getIntent().getExtras().get("op").equals("edit")) {
            edtMateria.setText((String) getIntent().getExtras().get("materia"));
            edtTitulo.setText((String) getIntent().getExtras().get("titulo"));
        }

    }

    public void cancelar(View view) {
        setResult(RESULT_CANCELAR);
        finish();
    }

    public void adicionar(View view) {

        Intent intent = new Intent();

        String materia = edtMateria.getText().toString();
        String titulo = edtTitulo.getText().toString();

        intent.putExtra("codigo", getId());
        intent.putExtra("materia", materia);
        intent.putExtra("titulo", titulo);

        setResult(RESULT_ADD, intent);
        finish();

    }

    public int getId() {
        return id;
    }

}