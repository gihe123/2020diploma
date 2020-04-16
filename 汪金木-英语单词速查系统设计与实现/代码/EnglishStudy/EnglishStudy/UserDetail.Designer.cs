namespace EnglishStudy
{
    partial class UserDetail
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
            this.label1 = new System.Windows.Forms.Label();
            this.UserName = new System.Windows.Forms.TextBox();
            this.realName = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.Sex = new System.Windows.Forms.ComboBox();
            this.label4 = new System.Windows.Forms.Label();
            this.Birthday = new System.Windows.Forms.DateTimePicker();
            this.Email = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.Telephone = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.IdCard = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.UserType = new System.Windows.Forms.ComboBox();
            this.button1 = new System.Windows.Forms.Button();
            this.remark = new System.Windows.Forms.TextBox();
            this.label9 = new System.Windows.Forms.Label();
            this.button2 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(37, 42);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(53, 12);
            this.label1.TabIndex = 0;
            this.label1.Text = "用户名：";
            // 
            // UserName
            // 
            this.UserName.Location = new System.Drawing.Point(115, 39);
            this.UserName.Name = "UserName";
            this.UserName.Size = new System.Drawing.Size(160, 21);
            this.UserName.TabIndex = 1;
            // 
            // realName
            // 
            this.realName.Location = new System.Drawing.Point(399, 39);
            this.realName.Name = "realName";
            this.realName.Size = new System.Drawing.Size(160, 21);
            this.realName.TabIndex = 3;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(320, 42);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(53, 12);
            this.label2.TabIndex = 2;
            this.label2.Text = "姓  名：";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(37, 98);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(53, 12);
            this.label3.TabIndex = 4;
            this.label3.Text = "性  别：";
            // 
            // Sex
            // 
            this.Sex.FormattingEnabled = true;
            this.Sex.Items.AddRange(new object[] {
            "男",
            "女"});
            this.Sex.Location = new System.Drawing.Point(115, 95);
            this.Sex.Name = "Sex";
            this.Sex.Size = new System.Drawing.Size(121, 20);
            this.Sex.TabIndex = 5;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(320, 98);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(53, 12);
            this.label4.TabIndex = 6;
            this.label4.Text = "生  日：";
            // 
            // Birthday
            // 
            this.Birthday.CustomFormat = "";
            this.Birthday.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.Birthday.Location = new System.Drawing.Point(399, 98);
            this.Birthday.Name = "Birthday";
            this.Birthday.Size = new System.Drawing.Size(160, 21);
            this.Birthday.TabIndex = 8;
            this.Birthday.ValueChanged += new System.EventHandler(this.dateTimePicker1_ValueChanged);
            // 
            // Email
            // 
            this.Email.AcceptsTab = true;
            this.Email.Location = new System.Drawing.Point(115, 153);
            this.Email.Name = "Email";
            this.Email.Size = new System.Drawing.Size(160, 21);
            this.Email.TabIndex = 10;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(37, 156);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(65, 12);
            this.label5.TabIndex = 9;
            this.label5.Text = "电子邮件：";
            // 
            // Telephone
            // 
            this.Telephone.Location = new System.Drawing.Point(399, 156);
            this.Telephone.Name = "Telephone";
            this.Telephone.Size = new System.Drawing.Size(160, 21);
            this.Telephone.TabIndex = 12;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(320, 156);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(65, 12);
            this.label6.TabIndex = 11;
            this.label6.Text = "电话号码：";
            // 
            // IdCard
            // 
            this.IdCard.Location = new System.Drawing.Point(115, 217);
            this.IdCard.Name = "IdCard";
            this.IdCard.Size = new System.Drawing.Size(160, 21);
            this.IdCard.TabIndex = 14;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(37, 220);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(65, 12);
            this.label7.TabIndex = 13;
            this.label7.Text = "身份证号：";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(320, 217);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(65, 12);
            this.label8.TabIndex = 15;
            this.label8.Text = "用户类型：";
            // 
            // UserType
            // 
            this.UserType.FormattingEnabled = true;
            this.UserType.Items.AddRange(new object[] {
            "管理员",
            "普通用户"});
            this.UserType.Location = new System.Drawing.Point(399, 217);
            this.UserType.Name = "UserType";
            this.UserType.Size = new System.Drawing.Size(121, 20);
            this.UserType.TabIndex = 17;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(200, 417);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 18;
            this.button1.Text = "保  存";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // remark
            // 
            this.remark.Location = new System.Drawing.Point(116, 278);
            this.remark.Multiline = true;
            this.remark.Name = "remark";
            this.remark.Size = new System.Drawing.Size(443, 109);
            this.remark.TabIndex = 21;
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(37, 331);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(59, 12);
            this.label9.TabIndex = 20;
            this.label9.Text = "备   注：";
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(379, 417);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 19;
            this.button2.Text = "关  闭";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // UserDetail
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(718, 477);
            this.Controls.Add(this.remark);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.UserType);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.IdCard);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.Telephone);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.Email);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.Birthday);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.Sex);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.realName);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.UserName);
            this.Controls.Add(this.label1);
            this.Name = "UserDetail";
            this.Text = "用户详情";
            this.Load += new System.EventHandler(this.UserDetail_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox UserName;
        private System.Windows.Forms.TextBox realName;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.ComboBox Sex;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.DateTimePicker Birthday;
        private System.Windows.Forms.TextBox Email;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox Telephone;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox IdCard;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.ComboBox UserType;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox remark;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Button button2;
    }
}