using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace EnglishStudy
{
    public partial class UserDetail : Form
    {
        public UserDetail()
        {
            InitializeComponent();
        }

        private void UserDetail_Load(object sender, EventArgs e)
        {
            if (MainWindow.userID != "0")
            {
                DataSet ds = MainConfig.GetRecord("select * from [User] where Id='"+MainWindow.userID+"'");
                if (ds != null)
                {
                    if (ds.Tables[0].Rows.Count > 0)
                    {
                        DataRow dr = ds.Tables[0].Rows[0];
                        this.UserName.Text = dr["UserName"].ToString();
                        UserName.Enabled = false;
                        this.realName.Text = dr["realName"].ToString();
                        this.IdCard.Text = dr["idCard"].ToString();
                        this.Birthday.Text = dr["Birthday"].ToString();
                        this.Email.Text = dr["Email"].ToString();
                        this.Telephone.Text = dr["Telephone"].ToString();
                        this.Sex.Text = dr["Sex"].ToString();
                        this.UserType.Text = dr["UserType"].ToString() == "1" ? "管理员" : "普通用户";
                        this.remark.Text = dr["remark"].ToString();
                    }
                    else
                    {
                        MessageBox.Show("找不到用户！");
                    }
                }
            }
        }

        private void dateTimePicker1_ValueChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            string type = UserType.Text == "管理员" ? "1" : "2";
            string sql = "update [User] set IdCard='" + IdCard.Text + "',Birthday='" + this.Birthday.Text + "',Sex='" + Sex.Text + "',RealName='" + realName.Text + "',Email='" + Email.Text + "',Telephone='" + Telephone.Text + "',Remark='" + remark.Text + "',UserType='" +type + "' where id='"+MainWindow.userID+"'";
            MainConfig.Excute(sql);
            MessageBox.Show("保存成功！");
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Hide();
        }
    }
}
