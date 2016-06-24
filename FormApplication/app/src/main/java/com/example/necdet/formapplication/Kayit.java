package com.example.necdet.formapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Kayit extends AppCompatActivity {

    private EditText Adi;
    private EditText Soyadi;
    private EditText Mail;
    private EditText Sifre;
    private Spinner Meslek;
    private WebServiceLogin _WebServiceLogin;
    private Validation _Validation;
    private String Job;

    SoapPrimitive resultString;
    String TAG = "response";

    private String[] rol={"Personel","Öğrenci"};
    private ArrayAdapter<String> dataAdapterForRol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        Adi = (EditText) findViewById(R.id.editTextAdi);
        Soyadi =(EditText)findViewById(R.id.editTextSoyadi);
        Mail = (EditText) findViewById(R.id.editTextMail);
        Sifre = (EditText) findViewById(R.id.editTextSifre);
        Meslek = (Spinner) findViewById(R.id.spinnerJob);

        dataAdapterForRol = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rol);

        dataAdapterForRol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Meslek.setAdapter(dataAdapterForRol);

        Meslek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                if (parent.getSelectedItem().toString().equals(rol[0]))
                    Job="0";
                else if (parent.getSelectedItem().toString().equals(rol[1]))
                    Job="1";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void KayitEkle(String name, String surname, String email, String password, String job){

        try {
            _WebServiceLogin=new WebServiceLogin();
            SoapObject Request = new SoapObject(_WebServiceLogin.NAMESPACE(), _WebServiceLogin.AddUser());
            Request.addProperty("name", name);
            Request.addProperty("surname", surname);
            Request.addProperty("email", email);
            Request.addProperty("password", password);
            Request.addProperty("job", job);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(_WebServiceLogin.MAIN_REQUEST_URL());

            transport.call(_WebServiceLogin.SOAP_ACTION(_WebServiceLogin.AddUser()), soapEnvelope);
            resultString = (SoapPrimitive) soapEnvelope.getResponse();

            Log.i(TAG, "Result Celsius: " + resultString);
        } catch (Exception ex) {
                Toast.makeText(this, "Email adresiniz farklı kullanıcıya aittir.", Toast.LENGTH_SHORT).show();
        }
    }

    public void KayitYapOnClick(View view){

        _Validation=new Validation();

        String adi = Adi.getText().toString();
        String soyadi = Soyadi.getText().toString();
        String mail= Mail.getText().toString();
        String pass=Sifre.getText().toString();


        if(!_Validation.isValidNull(adi))
            Adi.setError("Lütfen Alanı Boş Bırakmayınız!");
        else if(!_Validation.isValidNull(soyadi))
            Soyadi.setError("Lütfen Alanı Boş Bırakmayınız!");
        else if(!_Validation.isValidEmail(mail))
            Mail.setError("E-Mailinizi Kontrol Ediniz!");
        else if(!_Validation.isValidPassword(pass))
            Sifre.setError("En Az 6 Karakter Girmelisiniz!");
        else{
            KayitEkle(adi, soyadi, mail, pass,Job);

            Adi.setText("");
            Soyadi.setText("");
            Mail.setText("");
            Sifre.setText("");

           Toast.makeText(this, "Kayıt Başarıyla Oluşturuldu.", Toast.LENGTH_SHORT).show();
        }
        }
    }
