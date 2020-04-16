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
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (txtName.Text == string.Empty || txtPass.Text == string.Empty)
            {
                MessageBox.Show("请输入用户名和密码！");
                return;
            }
            DataSet ds = MainConfig.GetRecord("select * from [user] where UserName='"+txtName.Text+"' and PassWord='"+txtPass.Text+"'");
            if (ds != null)
            {
                if (ds.Tables[0].Rows.Count > 0)
                {
                    MainConfig.Userds = ds;
                    MainWindow mainwindow = new MainWindow();
                    mainwindow.Show();
                    this.Hide();
                }
                else
                {
                    MessageBox.Show("登录失败！");
                    return;
                }
            }
            else
            {
                MessageBox.Show("登录失败，数据库操作失败！");
                return;
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
        
        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            Register regi = new Register();
            regi.Show();
        }
    }
}
