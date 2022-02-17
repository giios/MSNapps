package com.cursoandroid.msnapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cursoandroid.msnapp.R;
import com.cursoandroid.msnapp.helper.Preferencias;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

   // private EditText nome;
   // private EditText telefone;
    //private String ultimoCaracterDigitado = " ";
    //private EditText codPais;
    //private EditText codValidacao;
    private Button cadastrar;

    //private DatabaseReference referenciaFirebase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        /*telefone     = (EditText) findViewById(R.id.edit_telefone);
        nome         = (EditText) findViewById(R.id.edit_nome);
        codPais      = (EditText) findViewById(R.id.edit_cod_pais);
        codValidacao = (EditText) findViewById(R.id.edit_cod_validacao);*/
        cadastrar    = (Button) findViewById(R.id.bt_cadastrar) ;



        /*telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_FONE));
        codPais.addTextChangedListener(MaskEditUtil.mask(codPais, MaskEditUtil.FORMAT_COD_PAIS));*/


        /*Criando ação de clique no botão cadastrar ao mesmo tempo que formata o numero de telefone, retirando alguns caracteres*/
        /*cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeUsuario = nome.getText().toString();
                String telefoneCompleto = codPais.getText().toString() +
                        telefone.getText().toString();

                String telefoneSemFormatacao = telefoneCompleto.replace("+", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("(", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace(")", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace(" ", "");

                //Gerar token
                Random randomico = new Random();
                int numeroRandomico = randomico.nextInt(999999 - 100000) + 100000;
                String token = String.valueOf( numeroRandomico );

                //Salvar os dados para validação
                Preferencias preferencias = new Preferencias(MainActivity.this);
                preferencias.salvarUsuarioPreferencias(nomeUsuario, telefoneSemFormatacao, token);


                HashMap<String, String> usuario = preferencias.getDadosUsuario();

                Log.i("TOKEN", "T: " + usuario.get("token"));

            }
        });*/

    }
}