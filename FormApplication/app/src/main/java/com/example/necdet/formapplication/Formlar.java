package com.example.necdet.formapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;


public class Formlar extends AppCompatActivity {

    private ListView listForms;

    private WebServiceLogin _WebServiceLogin;

    private List<String> listTitle;
    private List<String> listID;

    public static int _FormunIdsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formlar);

        listForms = (ListView) findViewById(R.id.listViewForms);
        MyFormsName();
        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, listTitle);

        listForms.setAdapter(veriAdaptoru);

        listForms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                _FormunIdsi=Integer.parseInt(listID.get(position));
                Intent FormAc = new Intent(Formlar.this, FormSon.class);
                startActivity(FormAc);
            }
        });
    }



    public void MyFormsName()
    {
        listTitle = new ArrayList<String>();
        listID = new ArrayList<String>();

        try {
            _WebServiceLogin=new WebServiceLogin();
            SoapObject Request = new SoapObject(_WebServiceLogin.NAMESPACE(), _WebServiceLogin.Forms());

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(_WebServiceLogin.MAIN_REQUEST_URL());

            transport.call(_WebServiceLogin.SOAP_ACTION(_WebServiceLogin.Forms()), soapEnvelope);

            String resultSOAP =  ((SoapObject) soapEnvelope.bodyIn).toString();
            resultSOAP = resultSOAP.replace("FormsResponse","");
            resultSOAP = resultSOAP.replace(";","");
            JSONObject j = new JSONObject(resultSOAP);

            for(int i=0;i<j.getJSONArray("FormsResult").length();i++) {
                listTitle.add(new JSONObject(j.getJSONArray("FormsResult").getString(i)).getString("title"));
                listID.add(new JSONObject(j.getJSONArray("FormsResult").getString(i)).getString("ID"));
            }


        } catch (Exception ex) {
            listTitle.add(ex.getMessage());
            listID.add(ex.getMessage());
        }
    }


}


