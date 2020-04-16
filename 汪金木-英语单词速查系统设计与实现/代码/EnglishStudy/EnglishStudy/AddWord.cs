using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace EnglishStudy
{
    public partial class AddWord : Form
    {
        public AddWord()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string sql = "insert into word(word,yinbiao,translate,type,fenzu,zhongwenyuyin,YingWenYuYan,SuoXie) values('" + word.Text + "','" + yinbiao.Text + "','" + translate.Text + "','" + wordtype.Text + "','" + fenzu.Text + "','" + textBox1.Text + "','" + textBox2.Text + "','" + txtSuoXie.Text + "')";
            MainConfig.Excute(sql);
            MessageBox.Show("添加成功！");

        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            OpenFileDialog fileDialog = new OpenFileDialog();

            if (fileDialog.ShowDialog() == DialogResult.OK)
            {
                string extension = Path.GetExtension(fileDialog.FileName);
                //声明允许的后缀名
                string[] str = new string[] { ".wav" };
                if (!str.Contains(extension))
                {
                    MessageBox.Show("wav格式的音频！");
                }
                else
                {
                    string str2 = Path.Combine(System.Environment.CurrentDirectory, Guid.NewGuid().ToString() + "." + extension);
                    File.Copy(fileDialog.FileName, str2);
                    textBox1.Text = str2;
                }
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            OpenFileDialog fileDialog = new OpenFileDialog();

            if (fileDialog.ShowDialog() == DialogResult.OK)
            {
                string extension = Path.GetExtension(fileDialog.FileName);
                //声明允许的后缀名
                string[] str = new string[] { ".wav" };
                if (!str.Contains(extension))
                {
                    MessageBox.Show("wav格式的音频！");
                }
                else
                {
                    string str2 = Path.Combine(System.Environment.CurrentDirectory, Guid.NewGuid().ToString() + "." + extension);
                    File.Copy(fileDialog.FileName, str2);
                    textBox2.Text = str2;
                }
            }
        }
    }
}
