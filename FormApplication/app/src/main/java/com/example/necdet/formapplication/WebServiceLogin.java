package com.example.necdet.formapplication;

import java.util.List;

/**
 * Created by Necdet on 27.4.2016.
 */
public class WebServiceLogin {

    public String NAMESPACE() {
        return "http://192.168.68.156/";
    }

    public String MAIN_REQUEST_URL() {
        return NAMESPACE() + "User.asmx";
    }

    public String SOAP_ACTION(String methodname) {
        return NAMESPACE() + methodname;
    }

    public String AddUser() {
        return "AddUser";
    }

    public String UserInformation() {
        return "UserInformation";
    }

    public String Login() {
        return "Login";
    }

    public String CreatedForm()
    {
        return "CreatedForm";
    }

    public String FormItems()
    {
        return "FormItems";
    }

    public String Forms()
    {
        return "Forms";
    }

    public String UserNumber(){return "UserNumber";}

    public String FormInformations()
    {
        return "FormInformations";
    }

    public String FormDataNumber()
    {
        return "FormDataNumber";
    }
}
