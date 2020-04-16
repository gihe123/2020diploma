namespace EnglishStudy
{
    partial class AddWord
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.button2 = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.translate = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.wordtype = new System.Windows.Forms.ComboBox();
            this.label3 = new System.Windows.Forms.Label();
            this.yinbiao = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.word = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.fenzu = new System.Windows.Forms.ComboBox();
            this.label4 = new System.Windows.Forms.Label();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.textBox2 = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.button3 = new System.Windows.Forms.Button();
            this.button4 = new System.Windows.Forms.Button();
            this.txtSuoXie = new System.Windows.Forms.TextBox();
            this.label8 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(339, 408);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 49;
            this.button2.Text = "关  闭";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(220, 408);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 48;
            this.button1.Text = "添  加";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // translate
            // 
            this.translate.AcceptsTab = true;
            this.translate.Location = new System.Drawing.Point(158, 186);
            this.translate.Multiline = true;
            this.translate.Name = "translate";
            this.translate.Size = new System.Drawing.Size(285, 72);
            this.translate.TabIndex = 47;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(80, 215);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(65, 12);
            this.label5.TabIndex = 46;
            this.label5.Text = "中文翻译：";
            // 
            // wordtype
            // 
            this.wordtype.FormattingEnabled = true;
            this.wordtype.Items.AddRange(new object[] {
            "动词",
            "形容词",
            "副词",
            "名词",
            "短语"});
            this.wordtype.Location = new System.Drawing.Point(156, 98);
            this.wordtype.Name = "wordtype";
            this.wordtype.Size = new System.Drawing.Size(121, 20);
            this.wordtype.TabIndex = 45;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(78, 101);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(53, 12);
            this.label3.TabIndex = 44;
            this.label3.Text = "类  别：";
            // 
            // yinbiao
            // 
            this.yinbiao.Location = new System.Drawing.Point(440, 50);
            this.yinbiao.Name = "yinbiao";
            this.yinbiao.Size = new System.Drawing.Size(160, 21);
            this.yinbiao.TabIndex = 43;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(361, 53);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(53, 12);
            this.label2.TabIndex = 42;
            this.label2.Text = "音  标：";
            // 
            // word
            // 
            this.word.Location = new System.Drawing.Point(156, 50);
            this.word.Name = "word";
            this.word.Size = new System.Drawing.Size(160, 21);
            this.word.TabIndex = 41;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(78, 53);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(53, 12);
            this.label1.TabIndex = 40;
            this.label1.Text = "单  词：";
            // 
            // fenzu
            // 
            this.fenzu.FormattingEnabled = true;
            this.fenzu.Items.AddRange(new object[] {
            "人物",
            "动物",
            "风景",
            "汽车",
            "摄影",
            "食品",
            "交易",
            "书籍",
            "学习",
            "饰品",
            "电器",
            "金融",
            "设计",
            "房产",
            "工作",
            "旅游",
            "短语"});
            this.fenzu.Location = new System.Drawing.Point(440, 96);
            this.fenzu.Name = "fenzu";
            this.fenzu.Size = new System.Drawing.Size(121, 20);
            this.fenzu.TabIndex = 51;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(361, 101);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(53, 12);
            this.label4.TabIndex = 50;
            this.label4.Text = "分  组：";
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(158, 290);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(160, 21);
            this.textBox1.TabIndex = 53;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(80, 293);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(65, 12);
            this.label6.TabIndex = 52;
            this.label6.Text = "中文语音：";
            // 
            // textBox2
            // 
            this.textBox2.Location = new System.Drawing.Point(158, 347);
            this.textBox2.Name = "textBox2";
            this.textBox2.Size = new System.Drawing.Size(160, 21);
            this.textBox2.TabIndex = 55;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(80, 351);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(65, 12);
            this.label7.TabIndex = 54;
            this.label7.Text = "英文语音：";
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(339, 291);
            this.button3.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(56, 19);
            this.button3.TabIndex = 56;
            this.button3.Text = "选 择";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // button4
            // 
            this.button4.Location = new System.Drawing.Point(339, 347);
            this.button4.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.button4.Name = "button4";
            this.button4.Size = new System.Drawing.Size(56, 20);
            this.button4.TabIndex = 57;
            this.button4.Text = "选 择";
            this.button4.UseVisualStyleBackColor = true;
            this.button4.Click += new System.EventHandler(this.button4_Click);
            // 
            // txtSuoXie
            // 
            this.txtSuoXie.Location = new System.Drawing.Point(156, 142);
            this.txtSuoXie.Name = "txtSuoXie";
            this.txtSuoXie.Size = new System.Drawing.Size(160, 21);
            this.txtSuoXie.TabIndex = 59;
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(78, 145);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(53, 12);
            this.label8.TabIndex = 58;
            this.label8.Text = "缩  写：";
            // 
            // AddWord
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(614, 478);
            this.Controls.Add(this.txtSuoXie);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.button4);
            this.Controls.Add(this.button3);
            this.Controls.Add(this.textBox2);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.fenzu);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.translate);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.wordtype);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.yinbiao);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.word);
            this.Controls.Add(this.label1);
            this.Name = "AddWord";
            this.Text = "添加单词";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox translate;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.ComboBox wordtype;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox yinbiao;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox word;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ComboBox fenzu;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox textBox2;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.Button button4;
        private System.Windows.Forms.TextBox txtSuoXie;
        private System.Windows.Forms.Label label8;
    }
}