namespace EnglishStudy
{
    partial class Register
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
            this.remark = new System.Windows.Forms.TextBox();
            this.label9 = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.IdCard = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.Telephone = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.Email = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.Birthday = new System.Windows.Forms.DateTimePicker();
            this.label4 = new System.Windows.Forms.Label();
            this.Sex = new System.Windows.Forms.ComboBox();
            this.label3 = new System.Windows.Forms.Label();
            this.realName = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.UserName = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.confirm = new System.Windows.Forms.TextBox();
            this.label10 = new System.Windows.Forms.Label();
            this.pass = new System.Windows.Forms.TextBox();
            this.label11 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // remark
            // 
            this.remark.Location = new System.Drawing.Point(141, 333);
            this.remark.Multiline = true;
            this.remark.Name = "remark";
            this.remark.Size = new System.Drawing.Size(443, 109);
            this.remark.TabIndex = 41;
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(62, 386);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(59, 12);
            this.label9.TabIndex = 40;
            this.label9.Text = "备   注：";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(303, 473);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 38;
            this.button1.Text = "注  册";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // IdCard
            // 
            this.IdCard.Location = new System.Drawing.Point(140, 272);
            this.IdCard.Name = "IdCard";
            this.IdCard.Size = new System.Drawing.Size(160, 21);
            this.IdCard.TabIndex = 35;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(62, 275);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(65, 12);
            this.label7.TabIndex = 34;
            this.label7.Text = "身份证号：";
            // 
            // Telephone
            // 
            this.Telephone.Location = new System.Drawing.Point(424, 211);
            this.Telephone.Name = "Telephone";
            this.Telephone.Size = new System.Drawing.Size(160, 21);
            this.Telephone.TabIndex = 33;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(345, 211);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(65, 12);
            this.label6.TabIndex = 32;
            this.label6.Text = "电话号码：";
            // 
            // Email
            // 
            this.Email.AcceptsTab = true;
            this.Email.Location = new System.Drawing.Point(140, 208);
            this.Email.Name = "Email";
            this.Email.Size = new System.Drawing.Size(160, 21);
            this.Email.TabIndex = 31;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(62, 211);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(65, 12);
            this.label5.TabIndex = 30;
            this.label5.Text = "电子邮件：";
            // 
            // Birthday
            // 
            this.Birthday.CustomFormat = "";
            this.Birthday.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.Birthday.Location = new System.Drawing.Point(424, 153);
            this.Birthday.Name = "Birthday";
            this.Birthday.Size = new System.Drawing.Size(160, 21);
            this.Birthday.TabIndex = 29;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(345, 153);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(53, 12);
            this.label4.TabIndex = 28;
            this.label4.Text = "生  日：";
            // 
            // Sex
            // 
            this.Sex.FormattingEnabled = true;
            this.Sex.Items.AddRange(new object[] {
            "男",
            "女"});
            this.Sex.Location = new System.Drawing.Point(140, 150);
            this.Sex.Name = "Sex";
            this.Sex.Size = new System.Drawing.Size(121, 20);
            this.Sex.TabIndex = 27;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(62, 153);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(53, 12);
            this.label3.TabIndex = 26;
            this.label3.Text = "性  别：";
            // 
            // realName
            // 
            this.realName.Location = new System.Drawing.Point(424, 33);
            this.realName.Name = "realName";
            this.realName.Size = new System.Drawing.Size(160, 21);
            this.realName.TabIndex = 25;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(345, 36);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(53, 12);
            this.label2.TabIndex = 24;
            this.label2.Text = "姓  名：";
            // 
            // UserName
            // 
            this.UserName.Location = new System.Drawing.Point(140, 33);
            this.UserName.Name = "UserName";
            this.UserName.Size = new System.Drawing.Size(160, 21);
            this.UserName.TabIndex = 23;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(62, 36);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(53, 12);
            this.label1.TabIndex = 22;
            this.label1.Text = "用户名：";
            // 
            // confirm
            // 
            this.confirm.Location = new System.Drawing.Point(424, 92);
            this.confirm.Name = "confirm";
            this.confirm.Size = new System.Drawing.Size(160, 21);
            this.confirm.TabIndex = 45;
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(345, 95);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(65, 12);
            this.label10.TabIndex = 44;
            this.label10.Text = "确认密码：";
            // 
            // pass
            // 
            this.pass.Location = new System.Drawing.Point(140, 92);
            this.pass.Name = "pass";
            this.pass.Size = new System.Drawing.Size(160, 21);
            this.pass.TabIndex = 43;
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(62, 95);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(53, 12);
            this.label11.TabIndex = 42;
            this.label11.Text = "密  码：";
            // 
            // Register
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(724, 524);
            this.Controls.Add(this.confirm);
            this.Controls.Add(this.label10);
            this.Controls.Add(this.pass);
            this.Controls.Add(this.label11);
            this.Controls.Add(this.remark);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.button1);
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
            this.Name = "Register";
            this.Text = "用户注册";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox remark;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox IdCard;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox Telephone;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox Email;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.DateTimePicker Birthday;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.ComboBox Sex;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox realName;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox UserName;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox confirm;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.TextBox pass;
        private System.Windows.Forms.Label label11;

    }
}