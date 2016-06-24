package com.example.necdet.formapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

public class FormSon extends AppCompatActivity {

    private TableLayout tabLeSon;

    private WebServiceLogin _WebServiceLogin;

    private List<String> listTitle;
    //private List<String> listSira;
    private List<String> listType;

    private int FormsID=Formlar._FormunIdsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_son);

        tabLeSon = (TableLayout) findViewById(R.id.tabLeSon);

        FormElemanlari(FormsID);
        Listele();
    }

    private void addTagGUI(String tag, int xmlAdi, int xmlTypeTitle)
    {

        // obtain a layout inflator object/service using the Context.
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // inflate the new tag-query button.
        View newTagView = layoutInflater.inflate(xmlAdi, null);


        // ontain references to tag and edit buttons and set their event listeners
        TextView newTextView = (TextView) newTagView.findViewById(xmlTypeTitle);
        newTextView.setText(tag);

        // finally, add tag-edit buttons to the corresponding index in table layout.
        tabLeSon.addView(newTagView);

    }

    private void Listele()
    {
        for(int i=0;i<listType.size();i++)
        {
            int typess = Integer.parseInt(listType.get(i));
            String title=listTitle.get(i).toString();
            switch (typess)
            {
                case 0:
                    addTagGUI(title,R.layout.activity_baslik, R.id.labelBaslikSon);
                    break;
                case 1:
                    addTagGUI(title,R.layout.activity_button, R.id.buttonSon);
                    break;
                case 2:
                    addTagGUI(title,R.layout.activity_metin,R.id.labelMetinSon);
                    break;
                case 3:
                    addTagGUI(title,R.layout.activity_altbaslik, R.id.labelaltBaslikSon);
                    break;
                case 4:
                    addTagGUI(title,R.layout.activity_radio, R.id.radioButtonSon);
                    break;
                case 5:
                    addTagGUI(title,R.layout.activity_checkbox, R.id.checkBoxSon);
                    break;
            }
        }
    }

    private void FormElemanlari(int formID)
    {
        listTitle = new ArrayList<String>();
        listType = new ArrayList<String>();

        try {
            _WebServiceLogin=new WebServiceLogin();
            SoapObject Request = new SoapObject(_WebServiceLogin.NAMESPACE(), _WebServiceLogin.FormInformations());
            Request.addProperty("formID", formID);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(_WebServiceLogin.MAIN_REQUEST_URL());

            transport.call(_WebServiceLogin.SOAP_ACTION(_WebServiceLogin.FormInformations()), soapEnvelope);

            String resultSOAP =  ((SoapObject) soapEnvelope.bodyIn).toString();
            resultSOAP = resultSOAP.replace("FormInformationsResponse","");
            resultSOAP = resultSOAP.replace(";","");
            JSONObject j = new JSONObject(resultSOAP);

            for(int i=0;i<j.getJSONArray("FormInformationsResult").length();i++) {
                listTitle.add(new JSONObject(j.getJSONArray("FormInformationsResult").getString(i)).getString("title"));
                //listSira.add(new JSONObject(j.getJSONArray("FormsResult").getString(i)).getString("sira"));
                listType.add(new JSONObject(j.getJSONArray("FormInformationsResult").getString(i)).getString("typess"));
            }


        } catch (Exception ex) {
            listTitle.add(ex.getMessage());
           // listSira.add(ex.getMessage());
            listType.add(ex.getMessage());
        }
    }
}
