using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace AndroidWebService
{
    /// <summary>
    /// Summary description for User
    /// </summary>
    [WebService(Namespace = "http://127.0.0.1/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class User : System.Web.Services.WebService
    {

        [WebMethod]
        public string AddUser(string name, string surname, string email, string password, string job)
        {
            try
            {
                using (Connections.sqlConnection = new System.Data.SqlClient.SqlConnection(Connections.ConnectionString))
                {
                    using (Connections.sqlCmd = new System.Data.SqlClient.SqlCommand()) {

                        Connections.sqlCmd.Connection = Connections.sqlConnection;

                        Connections.sqlCmd.CommandText = "Insert Into users (name, surname, email, password, job) values (@name, @surname, @email, @password, @job)";
                        Connections.sqlCmd.Parameters.AddWithValue("@name", name);
                        Connections.sqlCmd.Parameters.AddWithValue("@surname", surname);
                        Connections.sqlCmd.Parameters.AddWithValue("@email", email);
                        Connections.sqlCmd.Parameters.AddWithValue("@password", password);
                        Connections.sqlCmd.Parameters.AddWithValue("@job", job);
                        Connections.sqlConnection.Open(); 
                        Connections.sqlCmd.ExecuteNonQuery();
                        Connections.sqlConnection.Close();
                        return "Ok";

                    }                  
                } 
            }
            catch (Exception ex)
            {

                return "Error";
            }

        }

        [WebMethod]
        public string UserInformation()
        {
            try
            {
                using (Connections.sqlConnection = new System.Data.SqlClient.SqlConnection(Connections.ConnectionString))
                {
                    using (Connections.sqlCmd = new System.Data.SqlClient.SqlCommand())
                    {

                        Connections.sqlCmd.Connection = Connections.sqlConnection;

                        Connections.sqlCmd.CommandText = "Select * From users";
                        Connections.sqlConnection.Open();
                        Connections.sqlCmd.ExecuteNonQuery();
                        Connections.sqlConnection.Close();
                        return "Ok";

                    }
                }
            }
            catch (Exception ex)
            {

                return "Error";
            }

        }
    }


}
