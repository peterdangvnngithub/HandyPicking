
namespace Handy_Picking_Winform
{
    partial class Form_Menu
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form_Menu));
            this.panelTop = new System.Windows.Forms.Panel();
            this.labelControl1 = new DevExpress.XtraEditors.LabelControl();
            this.flowLayoutPanel1 = new System.Windows.Forms.FlowLayoutPanel();
            this.panel_ImportProductMaster = new System.Windows.Forms.Panel();
            this.picBox_ProductMaster = new System.Windows.Forms.PictureBox();
            this.panel3 = new System.Windows.Forms.Panel();
            this.lbl_ImportProductMaster = new System.Windows.Forms.Label();
            this.panel_HandyPicking = new System.Windows.Forms.Panel();
            this.picBox_HandyPicking = new System.Windows.Forms.PictureBox();
            this.panel5 = new System.Windows.Forms.Panel();
            this.lbl_HandyPicking = new System.Windows.Forms.Label();
            this.lbl_X = new DevExpress.XtraEditors.LabelControl();
            this.panelTop.SuspendLayout();
            this.flowLayoutPanel1.SuspendLayout();
            this.panel_ImportProductMaster.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picBox_ProductMaster)).BeginInit();
            this.panel_HandyPicking.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picBox_HandyPicking)).BeginInit();
            this.SuspendLayout();
            // 
            // panelTop
            // 
            this.panelTop.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(13)))), ((int)(((byte)(89)))), ((int)(((byte)(1)))));
            this.panelTop.Controls.Add(this.lbl_X);
            this.panelTop.Controls.Add(this.labelControl1);
            this.panelTop.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelTop.Location = new System.Drawing.Point(0, 0);
            this.panelTop.Name = "panelTop";
            this.panelTop.Size = new System.Drawing.Size(996, 50);
            this.panelTop.TabIndex = 0;
            this.panelTop.MouseDown += new System.Windows.Forms.MouseEventHandler(this.panelTop_MouseDown);
            this.panelTop.MouseMove += new System.Windows.Forms.MouseEventHandler(this.panelTop_MouseMove);
            this.panelTop.MouseUp += new System.Windows.Forms.MouseEventHandler(this.panelTop_MouseUp);
            // 
            // labelControl1
            // 
            this.labelControl1.Appearance.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelControl1.Appearance.ForeColor = System.Drawing.Color.White;
            this.labelControl1.Appearance.Options.UseFont = true;
            this.labelControl1.Appearance.Options.UseForeColor = true;
            this.labelControl1.Location = new System.Drawing.Point(28, 7);
            this.labelControl1.Name = "labelControl1";
            this.labelControl1.Size = new System.Drawing.Size(66, 32);
            this.labelControl1.TabIndex = 0;
            this.labelControl1.Text = "Menu";
            // 
            // flowLayoutPanel1
            // 
            this.flowLayoutPanel1.Controls.Add(this.panel_ImportProductMaster);
            this.flowLayoutPanel1.Controls.Add(this.panel_HandyPicking);
            this.flowLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flowLayoutPanel1.Location = new System.Drawing.Point(0, 50);
            this.flowLayoutPanel1.Name = "flowLayoutPanel1";
            this.flowLayoutPanel1.Size = new System.Drawing.Size(996, 425);
            this.flowLayoutPanel1.TabIndex = 1;
            // 
            // panel_ImportProductMaster
            // 
            this.panel_ImportProductMaster.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel_ImportProductMaster.Controls.Add(this.picBox_ProductMaster);
            this.panel_ImportProductMaster.Controls.Add(this.panel3);
            this.panel_ImportProductMaster.Controls.Add(this.lbl_ImportProductMaster);
            this.panel_ImportProductMaster.Location = new System.Drawing.Point(3, 3);
            this.panel_ImportProductMaster.Name = "panel_ImportProductMaster";
            this.panel_ImportProductMaster.Size = new System.Drawing.Size(200, 171);
            this.panel_ImportProductMaster.TabIndex = 0;
            // 
            // picBox_ProductMaster
            // 
            this.picBox_ProductMaster.Dock = System.Windows.Forms.DockStyle.Top;
            this.picBox_ProductMaster.Image = ((System.Drawing.Image)(resources.GetObject("picBox_ProductMaster.Image")));
            this.picBox_ProductMaster.Location = new System.Drawing.Point(0, 0);
            this.picBox_ProductMaster.Name = "picBox_ProductMaster";
            this.picBox_ProductMaster.Size = new System.Drawing.Size(198, 128);
            this.picBox_ProductMaster.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage;
            this.picBox_ProductMaster.TabIndex = 2;
            this.picBox_ProductMaster.TabStop = false;
            this.picBox_ProductMaster.Click += new System.EventHandler(this.picBox_ProductMaster_Click);
            // 
            // panel3
            // 
            this.panel3.Location = new System.Drawing.Point(0, 177);
            this.panel3.Name = "panel3";
            this.panel3.Size = new System.Drawing.Size(200, 171);
            this.panel3.TabIndex = 1;
            // 
            // lbl_ImportProductMaster
            // 
            this.lbl_ImportProductMaster.AutoSize = true;
            this.lbl_ImportProductMaster.Font = new System.Drawing.Font("Segoe UI Semibold", 12.25F, System.Drawing.FontStyle.Bold);
            this.lbl_ImportProductMaster.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(13)))), ((int)(((byte)(89)))), ((int)(((byte)(1)))));
            this.lbl_ImportProductMaster.Location = new System.Drawing.Point(9, 134);
            this.lbl_ImportProductMaster.Name = "lbl_ImportProductMaster";
            this.lbl_ImportProductMaster.Size = new System.Drawing.Size(185, 23);
            this.lbl_ImportProductMaster.TabIndex = 1;
            this.lbl_ImportProductMaster.Text = "Import Product Master";
            this.lbl_ImportProductMaster.Click += new System.EventHandler(this.lbl_ImportProductMaster_Click);
            // 
            // panel_HandyPicking
            // 
            this.panel_HandyPicking.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel_HandyPicking.Controls.Add(this.picBox_HandyPicking);
            this.panel_HandyPicking.Controls.Add(this.panel5);
            this.panel_HandyPicking.Controls.Add(this.lbl_HandyPicking);
            this.panel_HandyPicking.Location = new System.Drawing.Point(209, 3);
            this.panel_HandyPicking.Name = "panel_HandyPicking";
            this.panel_HandyPicking.Size = new System.Drawing.Size(200, 171);
            this.panel_HandyPicking.TabIndex = 2;
            // 
            // picBox_HandyPicking
            // 
            this.picBox_HandyPicking.Dock = System.Windows.Forms.DockStyle.Top;
            this.picBox_HandyPicking.Image = ((System.Drawing.Image)(resources.GetObject("picBox_HandyPicking.Image")));
            this.picBox_HandyPicking.Location = new System.Drawing.Point(0, 0);
            this.picBox_HandyPicking.Name = "picBox_HandyPicking";
            this.picBox_HandyPicking.Size = new System.Drawing.Size(198, 128);
            this.picBox_HandyPicking.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage;
            this.picBox_HandyPicking.TabIndex = 3;
            this.picBox_HandyPicking.TabStop = false;
            this.picBox_HandyPicking.Click += new System.EventHandler(this.picBox_HandyPicking_Click);
            // 
            // panel5
            // 
            this.panel5.Location = new System.Drawing.Point(0, 177);
            this.panel5.Name = "panel5";
            this.panel5.Size = new System.Drawing.Size(200, 171);
            this.panel5.TabIndex = 1;
            // 
            // lbl_HandyPicking
            // 
            this.lbl_HandyPicking.AutoSize = true;
            this.lbl_HandyPicking.Font = new System.Drawing.Font("Segoe UI Semibold", 12.25F, System.Drawing.FontStyle.Bold);
            this.lbl_HandyPicking.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(13)))), ((int)(((byte)(89)))), ((int)(((byte)(1)))));
            this.lbl_HandyPicking.Location = new System.Drawing.Point(38, 134);
            this.lbl_HandyPicking.Name = "lbl_HandyPicking";
            this.lbl_HandyPicking.Size = new System.Drawing.Size(121, 23);
            this.lbl_HandyPicking.TabIndex = 1;
            this.lbl_HandyPicking.Text = "Handy Picking";
            this.lbl_HandyPicking.Click += new System.EventHandler(this.lbl_HandyPicking_Click);
            // 
            // lbl_X
            // 
            this.lbl_X.Appearance.Font = new System.Drawing.Font("Segoe UI", 16F, System.Drawing.FontStyle.Bold);
            this.lbl_X.Appearance.ForeColor = System.Drawing.Color.White;
            this.lbl_X.Appearance.Options.UseFont = true;
            this.lbl_X.Appearance.Options.UseForeColor = true;
            this.lbl_X.Location = new System.Drawing.Point(968, 7);
            this.lbl_X.Name = "lbl_X";
            this.lbl_X.Size = new System.Drawing.Size(14, 30);
            this.lbl_X.TabIndex = 1;
            this.lbl_X.Text = "X";
            this.lbl_X.Click += new System.EventHandler(this.lbl_X_Click);
            // 
            // Form_Menu
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(996, 475);
            this.Controls.Add(this.flowLayoutPanel1);
            this.Controls.Add(this.panelTop);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "Form_Menu";
            this.Text = "Form_Menu";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form_Menu_FormClosing);
            this.panelTop.ResumeLayout(false);
            this.panelTop.PerformLayout();
            this.flowLayoutPanel1.ResumeLayout(false);
            this.panel_ImportProductMaster.ResumeLayout(false);
            this.panel_ImportProductMaster.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picBox_ProductMaster)).EndInit();
            this.panel_HandyPicking.ResumeLayout(false);
            this.panel_HandyPicking.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picBox_HandyPicking)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panelTop;
        private System.Windows.Forms.FlowLayoutPanel flowLayoutPanel1;
        private DevExpress.XtraEditors.LabelControl labelControl1;
        private System.Windows.Forms.Panel panel_ImportProductMaster;
        private System.Windows.Forms.Panel panel3;
        private System.Windows.Forms.Label lbl_ImportProductMaster;
        private System.Windows.Forms.Panel panel_HandyPicking;
        private System.Windows.Forms.Panel panel5;
        private System.Windows.Forms.Label lbl_HandyPicking;
        private System.Windows.Forms.PictureBox picBox_ProductMaster;
        private System.Windows.Forms.PictureBox picBox_HandyPicking;
        private DevExpress.XtraEditors.LabelControl lbl_X;
    }
}