using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using System.Data.SqlClient;
namespace EnglishStudy
{
    public class MainConfig
    {
        public static string GetConnection()
        {
            return @"Data Source=.\SQL2008R;Initial Catalog=EnglishStudy;uid=sa;pwd=sa";
        }

        public static void Excute(string sql)
        {
            SqlConnection conn = new SqlConnection(GetConnection());
            SqlCommand com = new SqlCommand(sql, conn);
            conn.Open();
            com.ExecuteNonQuery();
            conn.Close();
        }

        public static DataSet GetRecord(string sql)
        {
            SqlConnection conn = new SqlConnection(GetConnection());
            SqlDataAdapter da = new SqlDataAdapter(sql, conn);
            DataSet ds = new DataSet();
            da.Fill(ds);
            return ds;
        }

        public static DataSet Userds;
    }
}
