package com.elyneker.screenapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int ADD = 1;
    private static final int EDITAR = 2;
    private int id = 1;

    ArrayList<Livro> listaLivros;
    ListView listViewLivros;
    ListaLivrosAdapter adapter;
    EditText editTextCodigo;
    Livro livro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCodigo = findViewById(R.id.editTextCodigo);

        listaLivros = new ArrayList<>();

        adapter = new ListaLivrosAdapter(this, R.layout.adapter_view_layout, listaLivros);

        listViewLivros = (ListView) findViewById(R.id.listView);
        listViewLivros.setAdapter(adapter);
        listViewLivros.setSelector(android.R.color.holo_blue_light);

    }

    //Método que será executado quando uma ação que espera um resultado de outra tela
    //tiver sido concluído
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD && resultCode == CadastroActivity.RESULT_ADD) {

            if(data.getExtras() != null) {
                int codigo = (int) data.getExtras().get("codigo");
                String materia = (String) data.getExtras().get("materia");
                String titulo = (String) data.getExtras().get("titulo");

                Livro novoLivro = new Livro(codigo, materia, titulo);
                listaLivros.add(novoLivro);
                adapter.notifyDataSetChanged();

                //Atualizando o valor do identificador
                this.id++;
            }
        } else if (requestCode == EDITAR && resultCode == CadastroActivity.RESULT_ADD) {

            int codigo = (int) data.getExtras().get("codigo");
            String materia = (String) data.getExtras().get("materia");
            String titulo = (String) data.getExtras().get("titulo");

            for (int i=0; i < listaLivros.size(); i++) {

                if (listaLivros.get(i).getId() == codigo) {

                    listaLivros.get(i).setMateria(materia);
                    listaLivros.get(i).setTitulo(titulo);

                    adapter.notifyDataSetChanged();
                    break;
                }
            }
            Toast.makeText(this, "Alteração Realizada", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "cancelado", Toast.LENGTH_SHORT).show();
        }

    }

    public void clicarAdicionar(View view) {

        Intent intent = new Intent(this, CadastroActivity.class);
        intent.putExtra("op", "add");
        intent.putExtra("codigo", getId());
        startActivityForResult(intent, ADD);
    }

    public void clicarEditar(View view) {

        boolean item = false;

        if (!listaLivros.isEmpty()) {

            int temp = Integer.parseInt(editTextCodigo.getText().toString());

            for (int i=0; i < listaLivros.size(); i++) {

                if (listaLivros.get(i).getId() == temp) {
                    item = true;
                    livro = listaLivros.get(i);

                    Intent intent = new Intent(this, CadastroActivity.class);
                    intent.putExtra("op", "edit");

                    intent.putExtra("codigo", livro.getId());
                    intent.putExtra("materia", livro.getMateria());
                    intent.putExtra("titulo", livro.getTitulo());
                    startActivityForResult(intent, EDITAR);
                    break;
                }
            }
            if (!item) {
                Toast.makeText(this, "Não existe esse id", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Insira um id para editar", Toast.LENGTH_SHORT).show();
        }
    }

    public int getId() { return id; }

}