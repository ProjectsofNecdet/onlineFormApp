package com.example.necdet.formapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormOlustur extends AppCompatActivity {

    private Button buttonBaslik;
    private Button butonButton;
    private Button buttonMetin;
    private Button radioButon;
    private Button checkBox;
    private Button butonAltbaslik;
    private Button buttonMenu;
    private Button clear;
    private Button kaydet;
    private String m_Text = "";
    private TableLayout queryTableLayout;


    private String strKullaniciEmail;

    private ArrayList<String> dropdown = new ArrayList<String>();
    private ArrayList<Integer> siraArray = new ArrayList<Integer>();
    private ArrayList<Integer> typeArray = new ArrayList<Integer>();
    private ArrayList<String> titleArray = new ArrayList<String>();
    private ArrayAdapter<String> dataAdapterForMenu;
    private Spinner spinnermenu;

    private int count=0;

    private WebServiceLogin _WebServiceLogin;

    SoapPrimitive resultString;
    String TAG = "response";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_olustur);

        queryTableLayout = (TableLayout) findViewById(R.id.queryTableLayout);
        buttonBaslik = (Button) findViewById(R.id.buttonBaslik);
        buttonBaslik.setOnClickListener(buttonBaslikListener);

        butonButton = (Button) findViewById(R.id.button);
        butonButton.setOnClickListener(buttonButtonListener);

        buttonMetin=(Button) findViewById(R.id.buttonMetin);
        buttonMetin.setOnClickListener(buttonMetinListener);

        radioButon = (Button) findViewById(R.id.buttonRadio);
        radioButon.setOnClickListener(buttonRadioListener);

        checkBox=(Button) findViewById(R.id.buttonOnayKutusu);
        checkBox.setOnClickListener(buttonCheckBoxListener);

        butonAltbaslik=(Button) findViewById(R.id.buttonAltBaslik);
        butonAltbaslik.setOnClickListener(buttonAltbaslikListener);

        //buttonMenu=(Button) findViewById(R.id.buttonAcilirMenu);
        //buttonMenu.setOnClickListener(buttonMenuListener);

        clear = (Button) findViewById(R.id.buttonAlanlariSil);
        clear.setOnClickListener(clearButtonListener);

        kaydet=(Button) findViewById(R.id.buttonKaydet);
        kaydet.setOnClickListener(kaydetListener);

        strKullaniciEmail = Giris._Kullanici;


    }

    public View.OnClickListener buttonBaslikListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
            builder.setTitle("Konu Başlığını Giriniz");
            final EditText input = new EditText(FormOlustur.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText().length() > 0) {
                        m_Text = input.getText().toString();
                        addTagGUI(m_Text,R.layout.activity_button_baslik, R.id.labelBaslik,R.id.newImageButtonBaslik);

                        siraArray.add(count);
                        typeArray.add(0);
                        titleArray.add(m_Text);
                        count++;
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
                        builder.setTitle("Eksik Konu Başlığı");
                        builder.setMessage("Lütfen en az bir karakter içeren konu başlığı giriniz");

                        builder.setPositiveButton("Tamam", null);

                        AlertDialog errorDialog = builder.create();
                        errorDialog.show();
                    }
                }
            });

            AlertDialog errorDialog = builder.create();
            errorDialog.show();

        }
    };

    public View.OnClickListener buttonButtonListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
            builder.setTitle("Button Adı Giriniz");
            final EditText input = new EditText(FormOlustur.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText().length() > 0) {
                        m_Text = input.getText().toString();
                        addTagGUI(m_Text,R.layout.activity_button_button, R.id.buttonButton,R.id.newImageButtonButton);

                        siraArray.add(count);
                        typeArray.add(1);
                        titleArray.add(m_Text);
                        count++;
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
                        builder.setTitle("Eksik Button Adı");
                        builder.setMessage("Lütfen en az bir karakter içeren button adını giriniz");

                        builder.setPositiveButton("Tamam", null);

                        AlertDialog errorDialog = builder.create();
                        errorDialog.show();
                    }
                }
            });

            AlertDialog errorDialog = builder.create();
            errorDialog.show();

        }
    };

    public View.OnClickListener buttonMetinListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
            builder.setTitle("Metin Giriniz");
            final EditText input = new EditText(FormOlustur.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText().length() > 0) {
                        m_Text = input.getText().toString();
                        addTagGUI(m_Text,R.layout.activity_button_metin,R.id.labelMetin,R.id.newImageButtonMetin);

                        siraArray.add(count);
                        typeArray.add(2);
                        titleArray.add(m_Text);
                        count++;
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
                        builder.setTitle("Eksik Konu Metni");
                        builder.setMessage("Lütfen en az bir karakter içeren metin giriniz");

                        builder.setPositiveButton("Tamam", null);

                        AlertDialog errorDialog = builder.create();
                        errorDialog.show();
                    }
                }
            });

            AlertDialog errorDialog = builder.create();
            errorDialog.show();
        }
    };

    public View.OnClickListener buttonRadioListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
            builder.setTitle("Tercihinizi Yazınız");
            final EditText input = new EditText(FormOlustur.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText().length() > 0) {
                        m_Text = input.getText().toString();
                        addTagGUI(m_Text,R.layout.activity_button_radio, R.id.radioButon, R.id.newImageButtonRadio);

                        siraArray.add(count);
                        typeArray.add(4);
                        titleArray.add(m_Text);
                        count++;
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
                        builder.setTitle("Eksik Yazı");
                        builder.setMessage("Lütfen en az bir karakter içeren metin giriniz");

                        builder.setPositiveButton("Tamam", null);

                        AlertDialog errorDialog = builder.create();
                        errorDialog.show();
                    }
                }
            });

            AlertDialog errorDialog = builder.create();
            errorDialog.show();

        }
    };

    public View.OnClickListener buttonCheckBoxListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
            builder.setTitle("Tercihinizi Yazınız");
            final EditText input = new EditText(FormOlustur.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText().length() > 0) {
                        m_Text = input.getText().toString();
                        addTagGUI(m_Text,R.layout.activity_button_checkbox, R.id.checkBoxButon, R.id.newImageButtonCheckBox);

                        siraArray.add(count);
                        typeArray.add(5);
                        titleArray.add(m_Text);
                        count++;
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
                        builder.setTitle("Eksik Yazı");
                        builder.setMessage("Lütfen en az bir karakter içeren metin giriniz");

                        builder.setPositiveButton("Tamam", null);

                        AlertDialog errorDialog = builder.create();
                        errorDialog.show();
                    }
                }
            });

            AlertDialog errorDialog = builder.create();
            errorDialog.show();

        }
    };

    public View.OnClickListener buttonAltbaslikListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
            builder.setTitle("Konu Alt Başlığını Giriniz");
            final EditText input = new EditText(FormOlustur.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText().length() > 0) {
                        m_Text = input.getText().toString();
                        addTagGUI(m_Text,R.layout.activity_button_altbaslik, R.id.labelaltBaslik, R.id.newImageButtonAltBaslik);

                        siraArray.add(count);
                        typeArray.add(3);
                        titleArray.add(m_Text);
                        count++;
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
                        builder.setTitle("Eksik Yazı");
                        builder.setMessage("Lütfen en az bir karakter içeren alt başlık giriniz");

                        builder.setPositiveButton("Tamam", null);

                        AlertDialog errorDialog = builder.create();
                        errorDialog.show();
                    }
                }
            });

            AlertDialog errorDialog = builder.create();
            errorDialog.show();

        }
    };

    public View.OnClickListener buttonMenuListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
            builder.setTitle("Açılır Menü Başlığı Giriniz");
            final EditText input = new EditText(FormOlustur.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText().length() > 0) {
                        m_Text = input.getText().toString();
                        addTagGUI(m_Text,R.layout.activity_button_menu, R.id.labelMenu, R.id.newImageButtonMenu);

                        siraArray.add(count);
                        typeArray.add(6);
                        titleArray.add(m_Text);
                        dropdown.clear();
                        count++;
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
                        builder.setTitle("Eksik Yazı");
                        builder.setMessage("Lütfen en az bir karakter içeren menü başlık giriniz");

                        builder.setPositiveButton("Tamam", null);

                        AlertDialog errorDialog = builder.create();
                        errorDialog.show();
                    }
                }
            });

            AlertDialog errorDialog = builder.create();
            errorDialog.show();

        }
    };

    private void addTagGUI(String tag, int xmlAdi, int xmlButtonAdi, int xmlDeleteButton)
    {

        // obtain a layout inflator object/service using the Context.
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // inflate the new tag-query button.
        View newTagView = layoutInflater.inflate(xmlAdi, null);

        if(xmlAdi==R.layout.activity_button_menu) {
            // ontain references to tag and edit buttons and set their event listeners
            TextView newTextView = (TextView) newTagView.findViewById(xmlButtonAdi);
            newTextView.setText(tag);

            ImageButton newImageButton = (ImageButton) newTagView.findViewById(xmlDeleteButton);
            newImageButton.setOnClickListener(deleteButtonListener);

            Spinner spinnermenu = (Spinner) newTagView.findViewById(R.id.spinnerMenu);

            ImageButton newImageButtonAdd = (ImageButton) newTagView.findViewById(R.id.imageButtonAdd);
            newImageButtonAdd.setOnClickListener(addButtonListener);

            ImageButton newImageButtonDelete = (ImageButton) newTagView.findViewById(R.id.imageButtonDelete);
            newImageButtonDelete.setOnClickListener(deleteButtonListener);

            // finally, add tag-edit buttons to the corresponding index in table layout.
            queryTableLayout.addView(newTagView);
        }else
        {
            // ontain references to tag and edit buttons and set their event listeners
            TextView newTextView = (TextView) newTagView.findViewById(xmlButtonAdi);
            newTextView.setText(tag);

            ImageButton newImageButton = (ImageButton) newTagView.findViewById(xmlDeleteButton);
            newImageButton.setOnClickListener(deleteButtonListener);

            // finally, add tag-edit buttons to the corresponding index in table layout.
            queryTableLayout.addView(newTagView);
        }
    }

    public View.OnClickListener clearButtonListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
            builder.setTitle("Emin Misiniz ?");
            builder.setMessage("Form Elemanlarınız Silinecektir");

            // This will add an OK button in order to get the confirmation from the user
            builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    // TODO Auto-generated method stub
                    queryTableLayout.removeAllViews();
                    count=0;
                    siraArray.clear();
                    typeArray.clear();
                    titleArray.clear();
                }
            });

            builder.setCancelable(true);
            builder.setNegativeButton("Hayır", null); // no action if the user cancels the delete all request.

            AlertDialog confirmDialog = builder.create();
            confirmDialog.show();

        }
    };

    public View.OnClickListener deleteButtonListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TableRow textTableRow = (TableRow)v.getParent();

            queryTableLayout.removeView(textTableRow);

        }
    };

    public View.OnClickListener addButtonListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
            builder.setTitle("Açılır Menü Elemanlarınızı Giriniz");
            final EditText input = new EditText(FormOlustur.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText().length() > 0) {

                        spinnermenu = (Spinner) findViewById(R.id.spinnerMenu);

                        dropdown.add(input.getText().toString());
                        dataAdapterForMenu = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, dropdown);
                        dataAdapterForMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnermenu.setAdapter(dataAdapterForMenu);

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FormOlustur.this);
                        builder.setTitle("Eksik Metin");
                        builder.setMessage("Lütfen en az bir karakter içeren menü elemanı adını giriniz");

                        builder.setPositiveButton("Tamam", null);

                        AlertDialog errorDialog = builder.create();
                        errorDialog.show();
                    }
                }
            });

            AlertDialog errorDialog = builder.create();
            errorDialog.show();
        }
    };

    public View.OnClickListener kaydetListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int userID = Integer.parseInt(ActiveUserId(strKullaniciEmail));
            int formID = Integer.parseInt(formKaydet(titleArray.get(0),userID,siraArray.get(0), typeArray.get(0),titleArray.get(0)));

            for(int j=1; j < count; j++)
            {
                FormItems(formID, siraArray.get(j), typeArray.get(j), titleArray.get(j));
            }
            queryTableLayout.removeAllViews();
        }
    };

    public void FormsOnClick(View view) {
        Intent FormAc = new Intent(this, Formlar.class);
        startActivity(FormAc);

    }

    public String formKaydet(String formtitle, int usersID, int sira, int typess, String title)
    {
        try {
            _WebServiceLogin=new WebServiceLogin();
            SoapObject Request = new SoapObject(_WebServiceLogin.NAMESPACE(), _WebServiceLogin.CreatedForm());
            Request.addProperty("formtitle", formtitle);
            Request.addProperty("usersID", usersID);
            Request.addProperty("sira", sira);
            Request.addProperty("typess", typess);
            Request.addProperty("title", title);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(_WebServiceLogin.MAIN_REQUEST_URL());

            transport.call(_WebServiceLogin.SOAP_ACTION(_WebServiceLogin.CreatedForm()), soapEnvelope);
            resultString = (SoapPrimitive) soapEnvelope.getResponse();

            Log.i(TAG, "Result Celsius: " + resultString);
            return resultString.toString();
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
            return  "Error";
        }
    }

    private void FormItems(int formID, int sira, int typess, String title){

        try {
            _WebServiceLogin=new WebServiceLogin();
            SoapObject Request = new SoapObject(_WebServiceLogin.NAMESPACE(), _WebServiceLogin.FormItems());
            Request.addProperty("formID", formID);
            Request.addProperty("sira", sira);
            Request.addProperty("typess", typess);
            Request.addProperty("title", title);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(_WebServiceLogin.MAIN_REQUEST_URL());

            transport.call(_WebServiceLogin.SOAP_ACTION(_WebServiceLogin.FormItems()), soapEnvelope);
            resultString = (SoapPrimitive) soapEnvelope.getResponse();

            Log.i(TAG, "Result Celsius: " + resultString);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }

    private String ActiveUserId(String email){

        try {
            _WebServiceLogin=new WebServiceLogin();
            SoapObject Request = new SoapObject(_WebServiceLogin.NAMESPACE(), _WebServiceLogin.UserNumber());
            Request.addProperty("email", email);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(_WebServiceLogin.MAIN_REQUEST_URL());

            transport.call(_WebServiceLogin.SOAP_ACTION(_WebServiceLogin.UserNumber()), soapEnvelope);
            resultString = (SoapPrimitive) soapEnvelope.getResponse();

            Log.i(TAG, "Result Celsius: " + resultString);
            return resultString.toString();
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
            return  "Error";
        }
    }
}
