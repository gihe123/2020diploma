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
    public partial class Register : Form
    {
        public Register()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string exist = "select * from [User] where username='" + UserName.Text + "'";
            DataSet ds = MainConfig.GetRecord(exist);
            if (ds.Tables[0].Rows.Count > 0)
            {
                MessageBox.Show("该用户名已注册！");
                return;
            }
            if (pass.Text == string.Empty || pass.Text != confirm.Text)
            {
                MessageBox.Show("密码输入不正确！");
            }
            string sql = "insert into [User](UserName,PassWord,IdCard,Birthday,Sex,RealName,Email,Telephone,Remark,UserType) values('" + UserName.Text + "','" + pass.Text + "','" + IdCard.Text + "','" + this.Birthday.Text + "','" + Sex.Text + "','" + realName.Text + "','" + Email.Text + "','" + Telephone.Text + "','" + remark.Text + "',2)";
            //string sql = "update [User] set IdCard='" + IdCard.Text + "',Birthday='" + this.Birthday.Text + "',Sex='" + Sex.Text + "',RealName='" + realName.Text + "',Email='" + Email.Text + "',Telephone='" + Telephone.Text + "',Remark='" + remark.Text + "',UserType='" + type + "' where id='" + MainWindow.userID + "'";
            MainConfig.Excute(sql);
            MessageBox.Show("注册成功！");
        }
    }
}
