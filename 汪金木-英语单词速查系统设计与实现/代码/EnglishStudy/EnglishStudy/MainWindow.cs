using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Media;
using System.Text;
using System.Windows.Forms;

namespace EnglishStudy
{
    public partial class MainWindow : Form
    {
        public static string userID = "0";
        public static string wordID = "0";
        public static string tempchar;
        public static string tempDC;
        public static int CSSuccess = 0;
        public static int CSFail = 0;
        public static int GameSuccess = 0;
        public static int GameFail = 0;
        public MainWindow()
        {
            InitializeComponent();
        }

        private void MainWindow_Load(object sender, EventArgs e)
        {
            if (MainConfig.Userds != null)
            {
                DataRow dr = MainConfig.Userds.Tables[0].Rows[0];
                this.lblUserName.Text = dr["UserName"].ToString();
                lblRealName.Text = dr["RealName"].ToString();
                lblSex.Text = dr["Sex"].ToString();
                lblType.Text = dr["usertype"].ToString() == "1" ? "管理员" : "普通用户";
                if (dr["UserType"].ToString() == "1")
                {
                    tabControl1.TabPages.Remove(tabUser);
                    tabControl1.TabPages.Remove(tabPage2);
                }
                else
                {
                    InitUser();
                    InitWord();
                }
            }

            
           
            InitZiYou();
            InitFenZu();
            InitSC();
            InitFX();
        }

        /// <summary>
        /// 初始化用户
        /// </summary>
        private void InitWord()
        {
            DataSet ds = MainConfig.GetRecord("select * from word");
            dataWord.AutoGenerateColumns = false;
            dataWord.AllowUserToAddRows = false;
            dataWord.DataSource = ds.Tables[0].DefaultView;
        }

        private void InitUser()
        {
            DataSet ds = MainConfig.GetRecord("select * from [User]");
            dataUser.AutoGenerateColumns = false;
            dataUser.AllowUserToAddRows = false;
            dataUser.DataSource = ds.Tables[0].DefaultView;
        }

        private void dataUser_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            string action = dataUser.Columns[e.ColumnIndex].Name;//操作类型
            if (action == "caozuo")
            {
                string id = dataUser.Rows[e.RowIndex].Cells[9].Value.ToString();
                MainWindow.userID = id;
                UserDetail detail = new UserDetail();
                detail.Show();
            }
            if (action == "shanchu")
            {
                if (MessageBox.Show("确认删除？") == DialogResult.OK)
                {
                    string id = dataUser.Rows[e.RowIndex].Cells[9].Value.ToString();
                    string sql = "delete [user] where id='"+id+"'";
                    MainConfig.Excute(sql);
                    DataSet ds = MainConfig.GetRecord("select * from [User]");
                    dataUser.AutoGenerateColumns = false;
                    dataUser.AllowUserToAddRows = false;
                    dataUser.DataSource = ds.Tables[0].DefaultView;
                }
               
            }
        }

        private void btn_Search_Click(object sender, EventArgs e)
        {
            DataSet ds = MainConfig.GetRecord("select * from [User] where username like '%"+textBox1.Text+"%'");
            dataUser.AutoGenerateColumns = false;
            dataUser.AllowUserToAddRows = false;
            dataUser.DataSource = ds.Tables[0].DefaultView;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            DataSet ds = MainConfig.GetRecord("select * from [Word] where word like '%" + textBox2.Text + "%' or SuoXie Like '%" + textBox2.Text + "%'");
            dataWord.AutoGenerateColumns = false;
            dataWord.AllowUserToAddRows = false;
            dataWord.DataSource = ds.Tables[0].DefaultView;
        }

        private void dataWord_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            string action = dataWord.Columns[e.ColumnIndex].Name;//操作类型
            if (action == "wordcaozuo")
            {
                string id = dataWord.Rows[e.RowIndex].Cells[7].Value.ToString();
                MainWindow.wordID = id;
                WordDetail detail = new WordDetail();
                detail.Show();
            }
            if (action == "wordshanchu")
            {
                if (MessageBox.Show("确认删除？") == DialogResult.OK)
                {
                    string id = dataWord.Rows[e.RowIndex].Cells[7].Value.ToString();
                    string sql = "delete [word] where id='" + id + "'";
                    MainConfig.Excute(sql);
                    DataSet ds = MainConfig.GetRecord("select * from [User]");
                    dataWord.AutoGenerateColumns = false;
                    dataWord.AllowUserToAddRows = false;
                    dataWord.DataSource = ds.Tables[0].DefaultView;
                }

            }
        }

        /// <summary>
        /// 初始化自由记忆单词
        /// </summary>
        private void InitZiYou()
        {
            string sql = "select top 20 * from word";
            DataSet ds = MainConfig.GetRecord(sql);
            dataziyou.AutoGenerateColumns = false;
            dataziyou.AllowUserToAddRows = false;
            dataziyou.DataSource = ds.Tables[0].DefaultView;

        }

        /// <summary>
        /// 初始化自由记忆单词
        /// </summary>
        private void InitFenZu()
        {
            string sql = "select top 20 * from word where fenzu='" + fenzuType.Text + "'";
            DataSet ds = MainConfig.GetRecord(sql);
            dataFenzu.AutoGenerateColumns = false;
            dataFenzu.AllowUserToAddRows = false;
            dataFenzu.DataSource = ds.Tables[0].DefaultView;

        }

        private void button2_Click(object sender, EventArgs e)
        {
            AddWord add = new AddWord();
            add.Show();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            string sql = "select top 20 * from word order by newid() desc";
            DataSet ds = MainConfig.GetRecord(sql);
            dataziyou.AutoGenerateColumns = false;
            dataziyou.AllowUserToAddRows = false;
            dataziyou.DataSource = ds.Tables[0].DefaultView;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            string sql = "select top 20 * from word where fenzu='" + fenzuType.Text + "' order by newid() desc";
            DataSet ds = MainConfig.GetRecord(sql);
            dataFenzu.AutoGenerateColumns = false;
            dataFenzu.AllowUserToAddRows = false;
            dataFenzu.DataSource = ds.Tables[0].DefaultView;
        }

        private void dataziyou_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            string action = dataziyou.Columns[e.ColumnIndex].Name;//操作类型
            if (action == "StorageZY")
            {
                string id = dataziyou.Rows[e.RowIndex].Cells[4].Value.ToString();
                string sql = "select * from Storage where UserId='"+MainConfig.Userds.Tables[0].Rows[0]["ID"].ToString()+"' and wordID='"+id+"'";
                DataSet ds = MainConfig.GetRecord(sql);
                if (ds.Tables[0].Rows.Count > 0)
                {
                    MessageBox.Show("您已收藏过该单词！");
                }
                else
                {
                    string sql1 = "insert into Storage(UserID,UserName,WordID,WordName) values('" + MainConfig.Userds.Tables[0].Rows[0]["ID"].ToString() + "','" + MainConfig.Userds.Tables[0].Rows[0]["UserName"].ToString() + "','" + id + "','" + dataziyou.Rows[e.RowIndex].Cells[0].Value.ToString() + "')";
                    MainConfig.Excute(sql1);
                    MessageBox.Show("收藏成功！");
                }
            }
        }

        private void dataFenzu_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            string action = dataFenzu.Columns[e.ColumnIndex].Name;//操作类型
            if (action == "StorageBtn")
            {
                string id = dataFenzu.Rows[e.RowIndex].Cells[4].Value.ToString();
                string sql = "select * from Storage where UserId='" + MainConfig.Userds.Tables[0].Rows[0]["ID"].ToString() + "' and wordID='" + id + "'";
                DataSet ds = MainConfig.GetRecord(sql);
                if (ds.Tables[0].Rows.Count > 0)
                {
                    MessageBox.Show("您已收藏过该单词！");
                }
                else
                {
                    string sql1 = "insert into Storage(UserID,UserName,WordID,WordName) values('" + MainConfig.Userds.Tables[0].Rows[0]["ID"].ToString() + "','" + MainConfig.Userds.Tables[0].Rows[0]["UserName"].ToString() + "','" + id + "','" + dataFenzu.Rows[e.RowIndex].Cells[0].Value.ToString() + "')";
                    MainConfig.Excute(sql1);
                    MessageBox.Show("收藏成功！");
                }
            }
        }

        /// <summary>
        /// 初始化用户
        /// </summary>
        private void InitSC()
        {
            DataSet ds = MainConfig.GetRecord("select Storage.ID as aid,word.* from Storage inner join Word on word.ID=Storage.wordID where Storage.UserID='" + MainConfig.Userds.Tables[0].Rows[0]["ID"].ToString() + "'");
            dataSC.AutoGenerateColumns = false;
            dataSC.AllowUserToAddRows = false;
            dataSC.DataSource = ds.Tables[0].DefaultView;
        }
        /// <summary>
        /// 初始化用户
        /// </summary>
        private void InitFX()
        {
            DataSet ds = MainConfig.GetRecord("select Storage.ID as aid,word.* from Storage inner join Word on word.ID=Storage.wordID where Storage.UserID='" + MainConfig.Userds.Tables[0].Rows[0]["ID"].ToString() + "'");
            dataFX.AutoGenerateColumns = false;
            dataFX.AllowUserToAddRows = false;
            dataFX.DataSource = ds.Tables[0].DefaultView;
        }


        private void button6_Click(object sender, EventArgs e)
        {
            DataSet ds = MainConfig.GetRecord("select Storage.ID as aid,word.* from Storage inner join Word on word.ID=Storage.wordID where Storage.UserID='" + MainConfig.Userds.Tables[0].Rows[0]["ID"].ToString() + "' and word.word like '%" + SCTXT.Text + "%'");
            dataSC.AutoGenerateColumns = false;
            dataSC.AllowUserToAddRows = false;
            dataSC.DataSource = ds.Tables[0].DefaultView;
        }

        private void dataSC_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
                 string action = dataSC.Columns[e.ColumnIndex].Name;//操作类型

                 if (action == "deleteStrage")
                 {
                     if (MessageBox.Show("确认删除？") == DialogResult.OK)
                     {
                         string id = dataSC.Rows[e.RowIndex].Cells[5].Value.ToString();
                         string sql = "delete Storage where id='" + id + "'";
                         MainConfig.Excute(sql);
                         DataSet ds = MainConfig.GetRecord("select Storage.ID as aid,word.* from Storage inner join Word on word.ID=Storage.wordID where Storage.UserID='" + MainConfig.Userds.Tables[0].Rows[0]["ID"].ToString() + "' and word.word like '%" + SCTXT.Text + "%'");
                         dataSC.AutoGenerateColumns = false;
                         dataSC.AllowUserToAddRows = false;
                         dataSC.DataSource = ds.Tables[0].DefaultView;
                     }

                 }
        }

        private void button5_Click(object sender, EventArgs e)
        {
            DataSet ds = MainConfig.GetRecord("select top 20 Storage.ID as aid,word.* from Storage inner join Word on word.ID=Storage.wordID where Storage.UserID='" + MainConfig.Userds.Tables[0].Rows[0]["ID"].ToString() + "' order by newid() desc");
            dataFX.AutoGenerateColumns = false;
            dataFX.AllowUserToAddRows = false;
            dataFX.DataSource = ds.Tables[0].DefaultView;
        }

        private void button7_Click(object sender, EventArgs e)
        {
            this.lblResult.Text = string.Empty;
            this.lblYinbiao.Text = string.Empty;
            this.lblFY.Text = string.Empty;
            if (txtFY.Text == string.Empty)
            {
                MessageBox.Show("请输入单词");
            }
            else
            {
                DataSet ds = MainConfig.GetRecord("select * from word where word='"+txtFY.Text+ "' or SuoXie='" + txtFY.Text + "'");
                if (ds.Tables[0].Rows.Count > 0)
                {
                    DataRow dr = ds.Tables[0].Rows[0];
                    this.lblResult.Text = "单词："+dr["word"].ToString();
               
                    this.lblYinbiao.Text = "音标：" + dr["yinbiao"].ToString();
                    this.lblFY.Text = "释义：" + dr["Translate"].ToString();
                }
            }
        }

        private EventHandler LblResult_Click(object sender, EventArgs e)
        {
            System.Media.SoundPlayer sp = new SoundPlayer();
            sp.SoundLocation = sender.ToString();
            sp.PlayLooping();
            return null;
        }

        private void button8_Click(object sender, EventArgs e)
        {
            //this.lblResult = new Label();
            this.lblResult.Text = string.Empty;
            this.lblYinbiao.Text = string.Empty;
            this.lblFY.Text = string.Empty;
            if (txtFY.Text == string.Empty)
            {
                MessageBox.Show("请输入单词");
            }
            else
            {
                DataSet ds = MainConfig.GetRecord("select * from word where Translate like'%" + txtFY.Text + "%'");
                if (ds.Tables[0].Rows.Count > 0)
                {
                    DataRow dr = ds.Tables[0].Rows[0];
                    this.lblResult.Text = "单词：" + dr["word"].ToString();
                 
                    this.lblYinbiao.Text = "音标：" + dr["yinbiao"].ToString();
                    this.lblFY.Text = "释义：" + dr["Translate"].ToString();
                }
            }
        }

        //private void beginGame_Click(object sender, EventArgs e)
        //{
        //    beginGame.Text = "开始游戏....";
        //    beginGame.Enabled= false;

        //    DataSet ds = MainConfig.GetRecord("select top 1 * from word order by newid() desc");
        //    if (ds != null)
        //    {
        //        if (ds.Tables[0].Rows.Count > 0)
        //        {
        //            string DC = ds.Tables[0].Rows[0]["word"].ToString();
        //            this.lblSY.Text = ds.Tables[0].Rows[0]["translate"].ToString();
        //            Random rad = new Random();
        //           int temp=rad.Next(0, DC.Length - 1);
        //            int i = 0;
        //            foreach (char c in DC)
        //            {
        //                if (temp == i)
        //                {
        //                    this.lblDC.Text += "_";
        //                    tempchar=c.ToString();
        //                }
        //                else
        //                {
        //                    this.lblDC.Text += c.ToString();
        //                }
        //                i++;
        //            }
        //            this.lblSY.Text = ds.Tables[0].Rows[0]["translate"].ToString();
        //        }
        //    }
        //    timer1.Start();
        //}

        //private void timer1_Tick(object sender, EventArgs e)
        //{
        //    this.lblDJ.Text = (Convert.ToInt32(lblDJ.Text)-1).ToString();
        //    if (this.lblDJ.Text == "0")
        //    {
        //        GameFail++;
        //        this.lblDJ.Text = "5";
        //        this.lblDC.Text = string.Empty;
        //        beginGame_Click(null, null);
        //    }
        //    if (GameSuccess + GameFail == 10)
        //    {
        //        timer1.Stop();
        //        string mess = string.Empty;
        //        if (GameSuccess >= 8)
        //        {
        //            mess = "评级：英语牛人！";
        //        }
        //        if (GameSuccess < 8 && GameSuccess >= 5)
        //        {
        //            mess = "评级：英语达人";
        //        }
        //        if (GameSuccess < 5)
        //        {
        //            mess = "评级：英语菜鸟";
        //        }
        //        MessageBox.Show("游戏结束，当前得分" + GameSuccess * 10 + "分;" + mess);
        //        GameSuccess = 0;
        //        GameFail = 0;
        //        tempchar = string.Empty;
        //        beginGame.Enabled = true;
        //        beginGame.Text = "开始游戏";
        //    }
        //}

        //private void button9_Click(object sender, EventArgs e)
        //{
        //    if (tempchar == txtZM.Text)
        //    {
        //        GameSuccess++;
        //    }
        //    else
        //    {
        //        GameFail++;
        //    }
        //    this.lblDJ.Text = "5";
        //    this.lblDC.Text = string.Empty;
        //    beginGame_Click(null, null);
        //    if (GameSuccess + GameFail == 10)
        //    {
        //        timer1.Stop();
        //        string mess = string.Empty;
        //        if (GameSuccess >= 8)
        //        {
        //            mess = "评级：英语牛人！";
        //        }
        //        if (GameSuccess < 8 && GameSuccess >= 5)
        //        {
        //            mess = "评级：英语达人";
        //        }
        //        if (GameSuccess < 5)
        //        {
        //            mess = "评级：英语菜鸟";
        //        }
        //        MessageBox.Show("游戏结束，当前得分"+GameSuccess*10+"分;"+mess);
        //        GameSuccess = 0;
        //        GameFail = 0;
        //        tempchar = string.Empty;
               
        //        beginGame.Enabled = true;
        //        beginGame.Text = "开始游戏";
        //    }
        //    this.lblDJ.Text = "5";
        //}

        private void button10_Click(object sender, EventArgs e)
        {
            button10.Text = "开始测试....";
            button10.Enabled = false;

            DataSet ds = MainConfig.GetRecord("select top 1 * from word order by newid() desc");
            if (ds != null)
            {
                if (ds.Tables[0].Rows.Count > 0)
                {
                    tempDC = ds.Tables[0].Rows[0]["word"].ToString();
                    this.lblCs.Text = ds.Tables[0].Rows[0]["translate"].ToString();
                }
            }
        }

        private void button11_Click(object sender, EventArgs e)
        {
            if (tempDC == txtCs.Text)
            {
                CSSuccess++;
            }
            else
            {
                CSFail++;
            }
            button10_Click(null, null);
            if (CSSuccess + CSFail == 10)
            {
                string mess = string.Empty;
               
                MessageBox.Show("测试结束，当前得分" + CSSuccess * 10 + "分");
                CSSuccess = 0;
                CSFail = 0;
                tempDC = string.Empty;

                button10.Enabled = true;
                button10.Text = "开始测试";
            }
        }

        private void button9_Click(object sender, EventArgs e)
        {
            DataSet ds = MainConfig.GetRecord("select * from word where word='" + txtFY.Text + "'");
            if (ds.Tables[0].Rows.Count > 0)
            {
                DataRow dr = ds.Tables[0].Rows[0];
                LblResult_Click(dr["YingWenYuYan"].ToString(), e);
            }
          
        }

        private void button12_Click(object sender, EventArgs e)
        {
            DataSet ds = MainConfig.GetRecord("select * from word where word='" + txtFY.Text + "'");
            if (ds.Tables[0].Rows.Count > 0)
            {
                DataRow dr = ds.Tables[0].Rows[0];
                LblResult_Click(dr["zhongwenyuyin"].ToString(), e);
            }
        }

        private void button13_Click(object sender, EventArgs e)
        {
            OpenFileDialog fileDialog = new OpenFileDialog();
            fileDialog.Multiselect = true;
            fileDialog.Title = "请选择文件";
            fileDialog.Filter = "所有文件(*csv*)|*.csv*"; //设置要选择的文件的类型
            if (fileDialog.ShowDialog() == DialogResult.OK)
            {
                string file = fileDialog.FileName;//返回文件的完整路径   
                this.textBox3.Text = file;
            }
        }

        private void button14_Click(object sender, EventArgs e)
        {
            DataTable dt = ConvertCSVToTable(this.textBox3.Text);
            foreach (DataRow dr in dt.Rows)
            {
                try
                {
                   DataSet dsds= MainConfig.GetRecord("select * from Word where Word='" + dr[0].ToString() + "'");
                    if(dsds!=null&&dsds.Tables[0].Rows.Count>0)
                    {
                        
                    }
                    else
                    {
                        string sql = "insert into word(word,translate,type) values('" + dr[0].ToString() + "','" + dr[2].ToString() + "','" + dr[1].ToString() + "')";
                        MainConfig.Excute(sql);
                    }

                }
                catch
                {

                }
            }
            MessageBox.Show("数据导入完毕");
        }

        public DataTable ConvertCSVToTable(string fileName)
        {
            DataTable dt = new DataTable();
            FileStream fs = new FileStream(fileName, FileMode.Open, FileAccess.Read);
            StreamReader sr = new StreamReader(fs, Encoding.UTF8);
            //记录每次读取的一行记录
            string strLine = "";
            //记录每行记录中的各字段内容
            string[] aryLine;
            //标示列数
            int columnCount = 0;
            //标示是否是读取的第一行
            bool IsFirst = true;
            bool IsTotalResult = false;
            //逐行读取CSV中的数据
            while ((strLine = sr.ReadLine()) != null)
            {
                if (strLine == "" || strLine.Replace(",", "") == "")
                {
                    continue;
                }
                aryLine = strLine.Replace(" ", "").Split(',');

                if (IsTotalResult)
                {
                    if (IsFirst == true)
                    {
                        IsFirst = false;
                        columnCount = aryLine.Length;
                        //创建列
                        for (int i = 0; i < columnCount; i++)
                        {
                            DataColumn dc = new DataColumn(aryLine[i].Trim());
                            dt.Columns.Add(dc);
                        }
                    }
                    else
                    {
                        DataRow dr = dt.NewRow();
                        for (int j = 0; j < columnCount; j++)
                        {
                            dr[j] = aryLine[j].Trim();
                        }
                        dt.Rows.Add(dr);
                    }
                    continue;
                }
                if (IsFirst == true)
                {
                    IsFirst = false;
                    columnCount = aryLine.Length;
                    //创建列
                    for (int i = 0; i < columnCount; i++)
                    {
                        DataColumn dc = new DataColumn(aryLine[i].Trim());
                        dt.Columns.Add(dc);
                    }
                }
                else
                {
                    DataRow dr = dt.NewRow();
                    try
                    {


                        for (int j = 0; j < columnCount; j++)
                        {
                            dr[j] = aryLine[j].Trim();
                        }
                        dt.Rows.Add(dr);
                    }
                    catch { }
                }
            }
            sr.Close();
            fs.Close();
            return dt;
        }
    }
}
