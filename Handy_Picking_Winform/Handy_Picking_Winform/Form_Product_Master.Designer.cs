
namespace Handy_Picking_Winform
{
    partial class Form_Product_Master
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form_Product_Master));
            this.ribbon = new DevExpress.XtraBars.Ribbon.RibbonControl();
            this.barBtn_Export_Production_Master = new DevExpress.XtraBars.BarButtonItem();
            this.barBtn_Import_Production_Master = new DevExpress.XtraBars.BarButtonItem();
            this.barStatic_User = new DevExpress.XtraBars.BarStaticItem();
            this.barStatic_Version = new DevExpress.XtraBars.BarStaticItem();
            this.barBtn_Refresh = new DevExpress.XtraBars.BarButtonItem();
            this.ribbonPage_Action = new DevExpress.XtraBars.Ribbon.RibbonPage();
            this.ribbonPageGroup1 = new DevExpress.XtraBars.Ribbon.RibbonPageGroup();
            this.ribbonStatusBar = new DevExpress.XtraBars.Ribbon.RibbonStatusBar();
            this.btn_InputPacking = new DevExpress.XtraBars.BarButtonItem();
            this.barBtn_Merge_Picking_List = new DevExpress.XtraBars.BarButtonItem();
            this.xMainTab = new DevExpress.XtraTab.XtraTabControl();
            this.xtraTabPage1 = new DevExpress.XtraTab.XtraTabPage();
            this.gridControl_Product_Master = new DevExpress.XtraGrid.GridControl();
            this.gridView_Product_Master = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.panelControl2 = new DevExpress.XtraEditors.PanelControl();
            this.gridControl1 = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            ((System.ComponentModel.ISupportInitialize)(this.ribbon)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.xMainTab)).BeginInit();
            this.xMainTab.SuspendLayout();
            this.xtraTabPage1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_Product_Master)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Product_Master)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl2)).BeginInit();
            this.panelControl2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // ribbon
            // 
            this.ribbon.ExpandCollapseItem.Id = 0;
            this.ribbon.Items.AddRange(new DevExpress.XtraBars.BarItem[] {
            this.ribbon.ExpandCollapseItem,
            this.ribbon.SearchEditItem,
            this.barBtn_Export_Production_Master,
            this.barBtn_Import_Production_Master,
            this.barStatic_User,
            this.barStatic_Version,
            this.barBtn_Refresh});
            this.ribbon.Location = new System.Drawing.Point(0, 0);
            this.ribbon.MaxItemId = 35;
            this.ribbon.Name = "ribbon";
            this.ribbon.Pages.AddRange(new DevExpress.XtraBars.Ribbon.RibbonPage[] {
            this.ribbonPage_Action});
            this.ribbon.Size = new System.Drawing.Size(997, 158);
            this.ribbon.StatusBar = this.ribbonStatusBar;
            // 
            // barBtn_Export_Production_Master
            // 
            this.barBtn_Export_Production_Master.Caption = "Xuất Product Master";
            this.barBtn_Export_Production_Master.Id = 4;
            this.barBtn_Export_Production_Master.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("barBtn_Export_Production_Master.ImageOptions.Image")));
            this.barBtn_Export_Production_Master.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("barBtn_Export_Production_Master.ImageOptions.LargeImage")));
            this.barBtn_Export_Production_Master.Name = "barBtn_Export_Production_Master";
            this.barBtn_Export_Production_Master.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtn_ExportPL_ItemClick);
            // 
            // barBtn_Import_Production_Master
            // 
            this.barBtn_Import_Production_Master.Caption = "Nhập Product Master";
            this.barBtn_Import_Production_Master.Id = 10;
            this.barBtn_Import_Production_Master.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("barBtn_Import_Production_Master.ImageOptions.SvgImage")));
            this.barBtn_Import_Production_Master.Name = "barBtn_Import_Production_Master";
            this.barBtn_Import_Production_Master.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtn_Import_Production_Master_ItemClick);
            // 
            // barStatic_User
            // 
            this.barStatic_User.Alignment = DevExpress.XtraBars.BarItemLinkAlignment.Right;
            this.barStatic_User.Caption = "User1";
            this.barStatic_User.Id = 11;
            this.barStatic_User.Name = "barStatic_User";
            // 
            // barStatic_Version
            // 
            this.barStatic_Version.Caption = "barStaticItem1";
            this.barStatic_Version.Id = 17;
            this.barStatic_Version.Name = "barStatic_Version";
            // 
            // barBtn_Refresh
            // 
            this.barBtn_Refresh.Caption = "Refresh";
            this.barBtn_Refresh.Id = 34;
            this.barBtn_Refresh.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("barBtn_Refresh.ImageOptions.Image")));
            this.barBtn_Refresh.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("barBtn_Refresh.ImageOptions.LargeImage")));
            this.barBtn_Refresh.Name = "barBtn_Refresh";
            this.barBtn_Refresh.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtn_Refresh_ItemClick);
            // 
            // ribbonPage_Action
            // 
            this.ribbonPage_Action.Groups.AddRange(new DevExpress.XtraBars.Ribbon.RibbonPageGroup[] {
            this.ribbonPageGroup1});
            this.ribbonPage_Action.Name = "ribbonPage_Action";
            this.ribbonPage_Action.Text = "Action";
            // 
            // ribbonPageGroup1
            // 
            this.ribbonPageGroup1.ItemLinks.Add(this.barBtn_Refresh);
            this.ribbonPageGroup1.ItemLinks.Add(this.barBtn_Import_Production_Master);
            this.ribbonPageGroup1.ItemLinks.Add(this.barBtn_Export_Production_Master);
            this.ribbonPageGroup1.Name = "ribbonPageGroup1";
            this.ribbonPageGroup1.Text = "Action";
            // 
            // ribbonStatusBar
            // 
            this.ribbonStatusBar.ItemLinks.Add(this.barStatic_User);
            this.ribbonStatusBar.ItemLinks.Add(this.barStatic_Version);
            this.ribbonStatusBar.Location = new System.Drawing.Point(0, 682);
            this.ribbonStatusBar.Name = "ribbonStatusBar";
            this.ribbonStatusBar.Ribbon = this.ribbon;
            this.ribbonStatusBar.Size = new System.Drawing.Size(997, 24);
            // 
            // btn_InputPacking
            // 
            this.btn_InputPacking.Id = 32;
            this.btn_InputPacking.Name = "btn_InputPacking";
            // 
            // barBtn_Merge_Picking_List
            // 
            this.barBtn_Merge_Picking_List.Id = 33;
            this.barBtn_Merge_Picking_List.Name = "barBtn_Merge_Picking_List";
            // 
            // xMainTab
            // 
            this.xMainTab.Dock = System.Windows.Forms.DockStyle.Fill;
            this.xMainTab.Location = new System.Drawing.Point(2, 2);
            this.xMainTab.Name = "xMainTab";
            this.xMainTab.SelectedTabPage = this.xtraTabPage1;
            this.xMainTab.Size = new System.Drawing.Size(993, 520);
            this.xMainTab.TabIndex = 1;
            this.xMainTab.TabPages.AddRange(new DevExpress.XtraTab.XtraTabPage[] {
            this.xtraTabPage1});
            // 
            // xtraTabPage1
            // 
            this.xtraTabPage1.Controls.Add(this.gridControl_Product_Master);
            this.xtraTabPage1.Name = "xtraTabPage1";
            this.xtraTabPage1.Size = new System.Drawing.Size(991, 495);
            this.xtraTabPage1.Text = "Product Master";
            // 
            // gridControl_Product_Master
            // 
            this.gridControl_Product_Master.Dock = System.Windows.Forms.DockStyle.Fill;
            this.gridControl_Product_Master.Location = new System.Drawing.Point(0, 0);
            this.gridControl_Product_Master.MainView = this.gridView_Product_Master;
            this.gridControl_Product_Master.MenuManager = this.ribbon;
            this.gridControl_Product_Master.Name = "gridControl_Product_Master";
            this.gridControl_Product_Master.Size = new System.Drawing.Size(991, 495);
            this.gridControl_Product_Master.TabIndex = 0;
            this.gridControl_Product_Master.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView_Product_Master});
            // 
            // gridView_Product_Master
            // 
            this.gridView_Product_Master.GridControl = this.gridControl_Product_Master;
            this.gridView_Product_Master.IndicatorWidth = 50;
            this.gridView_Product_Master.Name = "gridView_Product_Master";
            // 
            // panelControl2
            // 
            this.panelControl2.Controls.Add(this.xMainTab);
            this.panelControl2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelControl2.Location = new System.Drawing.Point(0, 158);
            this.panelControl2.Name = "panelControl2";
            this.panelControl2.Size = new System.Drawing.Size(997, 524);
            this.panelControl2.TabIndex = 4;
            // 
            // gridControl1
            // 
            this.gridControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.gridControl1.Location = new System.Drawing.Point(1, 24);
            this.gridControl1.MainView = this.gridView1;
            this.gridControl1.MenuManager = this.ribbon;
            this.gridControl1.Name = "gridControl1";
            this.gridControl1.Size = new System.Drawing.Size(991, 459);
            this.gridControl1.TabIndex = 4;
            this.gridControl1.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // gridView1
            // 
            this.gridView1.GridControl = this.gridControl1;
            this.gridView1.Name = "gridView1";
            // 
            // Form_ProductionMaster
            // 
            this.Appearance.BackColor = System.Drawing.Color.Black;
            this.Appearance.Options.UseBackColor = true;
            this.Appearance.Options.UseFont = true;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(997, 706);
            this.Controls.Add(this.panelControl2);
            this.Controls.Add(this.ribbonStatusBar);
            this.Controls.Add(this.ribbon);
            this.Font = new System.Drawing.Font("Segoe UI", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.IconOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("Form_ProductionMaster.IconOptions.SvgImage")));
            this.Name = "Form_ProductionMaster";
            this.Ribbon = this.ribbon;
            this.StatusBar = this.ribbonStatusBar;
            this.Text = "PRODUCT MASTER";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form_Main_FormClosing);
            this.Load += new System.EventHandler(this.Form_Main_Load);
            ((System.ComponentModel.ISupportInitialize)(this.ribbon)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.xMainTab)).EndInit();
            this.xMainTab.ResumeLayout(false);
            this.xtraTabPage1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_Product_Master)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Product_Master)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl2)).EndInit();
            this.panelControl2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.gridControl1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DevExpress.XtraBars.Ribbon.RibbonControl ribbon;
        private DevExpress.XtraBars.Ribbon.RibbonPage ribbonPage_Action;
        private DevExpress.XtraBars.Ribbon.RibbonPageGroup ribbonPageGroup1;
        private DevExpress.XtraBars.Ribbon.RibbonStatusBar ribbonStatusBar;
        private DevExpress.XtraBars.BarButtonItem barBtn_Export_Production_Master;
        private DevExpress.XtraBars.BarButtonItem barBtn_Import_Production_Master;
        private DevExpress.XtraTab.XtraTabControl xMainTab;
        private DevExpress.XtraTab.XtraTabPage xtraTabPage1;
        private DevExpress.XtraGrid.GridControl gridControl_Product_Master;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView_Product_Master;
        private DevExpress.XtraEditors.PanelControl panelControl2;
        private DevExpress.XtraBars.BarStaticItem barStatic_User;
        private DevExpress.XtraBars.BarButtonItem btn_InputPacking;
        private DevExpress.XtraGrid.GridControl gridControl1;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private DevExpress.XtraBars.BarButtonItem barBtn_Merge_Picking_List;
        private DevExpress.XtraBars.BarStaticItem barStatic_Version;
        private DevExpress.XtraBars.BarButtonItem barBtn_Refresh;
    }
}