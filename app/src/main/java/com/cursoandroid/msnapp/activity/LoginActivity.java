package com.cursoandroid.msnapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cursoandroid.msnapp.R;
import com.cursoandroid.msnapp.helper.Permissao;
import com.cursoandroid.msnapp.helper.Preferencias;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private EditText telefone;
    private EditText nome;
    private EditText codPais;
    private EditText codValidacao;
    private Button cadastrar;
    private String[] permissoesNecessarias = new String[]{Manifest.permission.SEND_SMS,
        Manifest.permission.INTERNET};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Permissao.validaPermissoes(1,this, permissoesNecessarias);

        telefone     = (EditText) findViewById(R.id.edit_telefone);
        nome         = (EditText) findViewById(R.id.edit_nome);
        codPais      = (EditText) findViewById(R.id.edit_cod_pais);
        codValidacao = (EditText) findViewById(R.id.edit_cod_validacao);
        cadastrar    = (Button) findViewById(R.id.bt_cadastrar) ;

        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_FONE));
        codPais.addTextChangedListener(MaskEditUtil.mask(codPais, MaskEditUtil.FORMAT_COD_PAIS));

        /*Criando ação de clique no botão cadastrar ao mesmo tempo que formata o numero de telefone, retirando alguns caracteres*/
        cadastrar.setOnClickListener(view -> {
            String nomeUsuario = nome.getText().toString();
            String telefoneCompleto = codPais.getText().toString() +
                    telefone.getText().toString();

            String telefoneSemFormatacao = telefoneCompleto.replace("+", "");
            telefoneSemFormatacao = telefoneSemFormatacao.replace("-", "");
            telefoneSemFormatacao = telefoneSemFormatacao.replace("(", "");
            telefoneSemFormatacao = telefoneSemFormatacao.replace(")", "");
            telefoneSemFormatacao = telefoneSemFormatacao.replace(" ", "");

            //Gerar Token
            Random randomico = new Random();
            int numeroRandomico = randomico.nextInt(999999 - 100000) + 100000;
            String token = String.valueOf( numeroRandomico );
            String mensagemToken = "MSNapp: Código de confirmação: " + token;

            //Salvar os dados para validação
            Preferencias preferencias = new Preferencias(getApplicationContext());
            preferencias.salvarUsuarioPreferencias(nomeUsuario, telefoneSemFormatacao, token);

            //Envio do SMS
            telefoneSemFormatacao = "5554";
            Boolean enviadoSMS = enviaSMS("+" + telefoneSemFormatacao, mensagemToken);

            if (enviadoSMS){

                Intent intent = new Intent(LoginActivity.this, ValidadorActivity.class);
                startActivity( intent );
                finish();

            }else{

                Toast.makeText(LoginActivity.this, "Problema ao enviar SMS, tente novamente!!", Toast.LENGTH_LONG).show();

            }



//            HashMap<String, String> usuario = preferencias.getDadosUsuario();
//            Log.i("TOKEN", "NOME: " + usuario.get("Nome") + " FONE: " + usuario.get("Telefone") + " TOKEN: " + usuario.get("Token"));

        });
    }

    /*Envio do SMS*/
    private boolean enviaSMS(String telefone, String mensagem){

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone, null, mensagem, null, null);

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;

        }

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int resultado : grantResults){
            if (resultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões negadas");
        builder.setMessage("Para utilização do app, é necessário aceitar as permissões solicitadas!");

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
