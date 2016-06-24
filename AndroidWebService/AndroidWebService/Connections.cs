using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Web;

namespace AndroidWebService
{
    public class Connections
    {
        #region MTS DataTable
        private static SqlParameter _SqlParameter;
        private static SqlParameter[] _SqlParameters;
        private static string _SqlCommandText;

        public static SqlParameter MtsSqlParameter
        {

            get { return _SqlParameter; }
            set { _SqlParameter = value; }

        }

        public static SqlParameter[] MtsSqlParameters
        {

            get { return _SqlParameters; }
            set { _SqlParameters = value; }

        }

        public static string MtsSqlCommandText
        {

            get { return _SqlCommandText; }
            set { _SqlCommandText = value; }
        }

        public static string ConnectionString
        {
            get {

                return System.Configuration.ConfigurationManager.
    ConnectionStrings["androidConnection"].ConnectionString;
            }
        }

        public static DataTable returnDataTable
        {
            get
            {
                if (sqlConnection.State == ConnectionState.Closed)
                    sqlConnection.Open();

                sqlCmd = new SqlCommand();
                sqlCmd.Connection = sqlConnection;
                sqlCmd.CommandText = _SqlCommandText;
                if (_SqlParameter != null)
                    sqlCmd.Parameters.Add(_SqlParameter);
                if (_SqlParameters != null)
                    sqlCmd.Parameters.AddRange(_SqlParameters);

                sqlCmd.CommandType = CommandType.Text;
                sqlCmd.ExecuteNonQuery();

                sqlAdapter = new SqlDataAdapter();
                dtTable = new DataTable();
                sqlAdapter.SelectCommand = sqlCmd;
                sqlAdapter.Fill(dtTable);
                sqlCmd.Parameters.Clear();

                return dtTable;
            }
        }

        #endregion



        public static SqlConnection sqlConnection;
        private static SqlCommand _sqlCmd;
        private static SqlDataAdapter _sqlAdapter;
        private static DataTable _dtTable;



        public static SqlCommand sqlCmd
        {
            get { return _sqlCmd; }
            set { _sqlCmd = value; }

        }

        public static SqlDataAdapter sqlAdapter
        {
            get { return _sqlAdapter; }
            set { _sqlAdapter = value; }

        }

        public static DataTable dtTable
        {
            get { return _dtTable; }
            set { _dtTable = value; }

        }

        public static string DataTableToJSON(DataTable Dt)
        {
            string[] StrDc = new string[Dt.Columns.Count];

            string HeadStr = string.Empty;
            for (int i = 0; i < Dt.Columns.Count; i++)
            {

                StrDc[i] = Dt.Columns[i].Caption;
                HeadStr += "\"" + StrDc[i] + "\":\"" + StrDc[i] + i.ToString() + "¾" + "\",";

            }

            HeadStr = HeadStr.Substring(0, HeadStr.Length - 1);

            StringBuilder Sb = new StringBuilder();

            Sb.Append("[");

            for (int i = 0; i < Dt.Rows.Count; i++)
            {

                string TempStr = HeadStr;

                for (int j = 0; j < Dt.Columns.Count; j++)
                {

                    TempStr = TempStr.Replace(Dt.Columns[j] + j.ToString() + "¾", Dt.Rows[i][j].ToString().Trim());
                }
                //Sb.AppendFormat("{{{0}}},",TempStr);

                Sb.Append("{" + TempStr + "},");
            }

            Sb = new StringBuilder(Sb.ToString().Substring(0, Sb.ToString().Length - 1));

            if (Sb.ToString().Length > 0)
                Sb.Append("]");

            return StripControlChars(Sb.ToString());

        }
        //To strip control characters:

        //A character that does not represent a printable character but //serves to initiate a particular action.

        public static string StripControlChars(string s)
        {
            return Regex.Replace(s, @"[^\x20-\x7F]", "");
        }
    }
}