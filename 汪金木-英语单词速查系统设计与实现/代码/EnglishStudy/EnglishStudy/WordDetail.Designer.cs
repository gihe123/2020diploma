namespace EnglishStudy
{
    partial class WordDetail
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
            this.SuspendLayout();
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(325, 243);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 39;
            this.button2.Text = "关  闭";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(207, 243);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 38;
            this.button1.Text = "保  存";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // translate
            // 
            this.translate.AcceptsTab = true;
            this.translate.Location = new System.Drawing.Point(152, 137);
            this.translate.Multiline = true;
            this.translate.Name = "translate";
            this.translate.Size = new System.Drawing.Size(285, 72);
            this.translate.TabIndex = 31;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(74, 166);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(65, 12);
            this.label5.TabIndex = 30;
            this.label5.Text = "中文翻译：";
            // 
            // wordtype
            // 
            this.wordtype.FormattingEnabled = true;
            this.wordtype.Items.AddRange(new object[] {
            "动词",
            "形容词",
            "副词",
            "名词"});
            this.wordtype.Location = new System.Drawing.Point(152, 79);
            this.wordtype.Name = "wordtype";
            this.wordtype.Size = new System.Drawing.Size(121, 20);
            this.wordtype.TabIndex = 27;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(74, 82);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(53, 12);
            this.label3.TabIndex = 26;
            this.label3.Text = "类  别：";
            // 
            // yinbiao
            // 
            this.yinbiao.Location = new System.Drawing.Point(436, 31);
            this.yinbiao.Name = "yinbiao";
            this.yinbiao.Size = new System.Drawing.Size(160, 21);
            this.yinbiao.TabIndex = 25;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(357, 34);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(53, 12);
            this.label2.TabIndex = 24;
            this.label2.Text = "音  标：";
            // 
            // word
            // 
            this.word.Location = new System.Drawing.Point(152, 31);
            this.word.Name = "word";
            this.word.Size = new System.Drawing.Size(160, 21);
            this.word.TabIndex = 23;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(74, 34);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(53, 12);
            this.label1.TabIndex = 22;
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
            "旅游"});
            this.fenzu.Location = new System.Drawing.Point(436, 79);
            this.fenzu.Name = "fenzu";
            this.fenzu.Size = new System.Drawing.Size(121, 20);
            this.fenzu.TabIndex = 41;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(357, 84);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(53, 12);
            this.label4.TabIndex = 40;
            this.label4.Text = "分  组：";
            // 
            // WordDetail
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(606, 286);
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
            this.Name = "WordDetail";
            this.Text = "单词详情";
            this.Load += new System.EventHandler(this.WordDetail_Load);
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
    }
}