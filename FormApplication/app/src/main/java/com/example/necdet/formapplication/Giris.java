package com.example.necdet.formapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class Giris extends AppCompatActivity {

    private EditText EMail;
    private EditText Password;

    private WebServiceLogin _WebServiceLogin;
    private Validation _Validation;

    public static String _Kullanici;

    SoapPrimitive resultString;
    String TAG = "response";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        EMail =(EditText) findViewById(R.id.editEMail);
        Password = (EditText) findViewById(R.id.editPassword);

    }


    public String GirisYap(String email, String password){
        try {
            _WebServiceLogin=new WebServiceLogin();
            SoapObject Request = new SoapObject(_WebServiceLogin.NAMESPACE(), _WebServiceLogin.Login());
            Request.addProperty("email", email);
            Request.addProperty("password", password);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(_WebServiceLogin.MAIN_REQUEST_URL());

            transport.call(_WebServiceLogin.SOAP_ACTION(_WebServiceLogin.Login()), soapEnvelope);
            resultString = (SoapPrimitive) soapEnvelope.getResponse();

            Log.i(TAG, "Result Celsius: " + resultString);
            return resultString.toString();
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
            return  "Error";
        }
    }

    public void FormOnClick(View view) {

        _Validation = new Validation();
        final String email = EMail.getText().toString();
        final String pass = Password.getText().toString();

        if (!_Validation.isValidEmail(email)) {
            EMail.setError("E-Mailinizi Kontrol Ediniz!");
        }else if (!_Validation.isValidPassword(pass)) {
            Password.setError("En Az 6 Karakter Girmelisiniz!");
        }else{
            if(!GirisYap(EMail.getText().toString(), Password.getText().toString()).equals("Error"))
            {
                if(GirisYap(EMail.getText().toString(), Password.getText().toString()).equals("0")) {
                    Intent FormAc = new Intent(this, FormOlustur.class);
                    startActivity(FormAc);
                    _Kullanici= EMail.getText().toString();
                }else
                {
                    Intent FormAc = new Intent(this, Formlar.class);
                    startActivity(FormAc);
                }
            }else{
                Toast.makeText(this, "Hatalı Kullanıcı Adı veya Şifre.", Toast.LENGTH_SHORT).show();
            }

        }
    }
}