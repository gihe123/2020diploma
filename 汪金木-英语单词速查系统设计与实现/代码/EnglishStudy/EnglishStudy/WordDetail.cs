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
    public partial class WordDetail : Form
    {
        public WordDetail()
        {
            InitializeComponent();
        }

        private void WordDetail_Load(object sender, EventArgs e)
        {
            if (MainWindow.wordID != "0")
            {
                DataSet ds = MainConfig.GetRecord("select * from [word] where Id='" + MainWindow.wordID + "'");
                if (ds != null)
                {
                    if (ds.Tables[0].Rows.Count > 0)
                    {
                        DataRow dr = ds.Tables[0].Rows[0];
                        this.word.Text = dr["word"].ToString();
                        this.yinbiao.Text = dr["yinbiao"].ToString();
                        this.wordtype.Text = dr["type"].ToString();
                        this.translate.Text = dr["Translate"].ToString();
                        this.fenzu.Text = dr["fenzu"].ToString();
                    }
                    else
                    {
                        MessageBox.Show("找不到用户！");
                    }
                }
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string sql = "update [word] set word='" + word.Text + "',yinbiao='" + this.yinbiao.Text + "',Type='" + wordtype.Text + "',Translate='" + translate.Text + "',fenzu='" + fenzu.Text + "' where id='" + MainWindow.wordID + "'";
            MainConfig.Excute(sql);
            MessageBox.Show("保存成功！");
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
