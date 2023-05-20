
namespace Time_Keeping
{
    partial class Form_Main
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form_Main));
            this.ribbon = new DevExpress.XtraBars.Ribbon.RibbonControl();
            this.barBtn_Transfer_Data = new DevExpress.XtraBars.BarButtonItem();
            this.barBtn_Compare_Data_Inv_PL = new DevExpress.XtraBars.BarButtonItem();
            this.barBtn_Import_Invoice = new DevExpress.XtraBars.BarButtonItem();
            this.barBtn_ExportPL = new DevExpress.XtraBars.BarButtonItem();
            this.barBtn_Inv_Clear_Data = new DevExpress.XtraBars.BarButtonItem();
            this.barBtn_PL_Clear_Data = new DevExpress.XtraBars.BarButtonItem();
            this.ribbonPage_Action = new DevExpress.XtraBars.Ribbon.RibbonPage();
            this.ribbonPageGroup1 = new DevExpress.XtraBars.Ribbon.RibbonPageGroup();
            this.ribbonPageGroup2 = new DevExpress.XtraBars.Ribbon.RibbonPageGroup();
            this.ribbonStatusBar = new DevExpress.XtraBars.Ribbon.RibbonStatusBar();
            this.panelControl1 = new DevExpress.XtraEditors.PanelControl();
            this.lbl_PickingList = new DevExpress.XtraEditors.LabelControl();
            this.sLookUp_PickingList = new DevExpress.XtraEditors.SearchLookUpEdit();
            this.gridView_sLookUp_PL_No = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.panelControl2 = new DevExpress.XtraEditors.PanelControl();
            this.xtraTabControl1 = new DevExpress.XtraTab.XtraTabControl();
            this.xtraTabPage1 = new DevExpress.XtraTab.XtraTabPage();
            this.gridControl_Picking_List = new DevExpress.XtraGrid.GridControl();
            this.gridView_Picking_List = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.xtraTabPage2 = new DevExpress.XtraTab.XtraTabPage();
            this.gridControl_Invoice = new DevExpress.XtraGrid.GridControl();
            this.gridView_Invoice = new DevExpress.XtraGrid.Views.Grid.GridView();
            ((System.ComponentModel.ISupportInitialize)(this.ribbon)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl1)).BeginInit();
            this.panelControl1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sLookUp_PickingList.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_sLookUp_PL_No)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl2)).BeginInit();
            this.panelControl2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.xtraTabControl1)).BeginInit();
            this.xtraTabControl1.SuspendLayout();
            this.xtraTabPage1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_Picking_List)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Picking_List)).BeginInit();
            this.xtraTabPage2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_Invoice)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Invoice)).BeginInit();
            this.SuspendLayout();
            // 
            // ribbon
            // 
            this.ribbon.ExpandCollapseItem.Id = 0;
            this.ribbon.Items.AddRange(new DevExpress.XtraBars.BarItem[] {
            this.ribbon.ExpandCollapseItem,
            this.ribbon.SearchEditItem,
            this.barBtn_Transfer_Data,
            this.barBtn_Compare_Data_Inv_PL,
            this.barBtn_Import_Invoice,
            this.barBtn_ExportPL,
            this.barBtn_Inv_Clear_Data,
            this.barBtn_PL_Clear_Data});
            this.ribbon.Location = new System.Drawing.Point(0, 0);
            this.ribbon.MaxItemId = 7;
            this.ribbon.Name = "ribbon";
            this.ribbon.Pages.AddRange(new DevExpress.XtraBars.Ribbon.RibbonPage[] {
            this.ribbonPage_Action});
            this.ribbon.Size = new System.Drawing.Size(1064, 158);
            this.ribbon.StatusBar = this.ribbonStatusBar;
            // 
            // barBtn_Transfer_Data
            // 
            this.barBtn_Transfer_Data.Caption = "&Transfer data";
            this.barBtn_Transfer_Data.Id = 1;
            this.barBtn_Transfer_Data.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("barBtn_Transfer_Data.ImageOptions.SvgImage")));
            this.barBtn_Transfer_Data.Name = "barBtn_Transfer_Data";
            this.barBtn_Transfer_Data.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtn_Search_ItemClick);
            // 
            // barBtn_Compare_Data_Inv_PL
            // 
            this.barBtn_Compare_Data_Inv_PL.Caption = "&Compare Invoice And Picking List";
            this.barBtn_Compare_Data_Inv_PL.Id = 2;
            this.barBtn_Compare_Data_Inv_PL.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("barBtn_Compare_Data_Inv_PL.ImageOptions.SvgImage")));
            this.barBtn_Compare_Data_Inv_PL.Name = "barBtn_Compare_Data_Inv_PL";
            this.barBtn_Compare_Data_Inv_PL.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtn_Compare_Data_Inv_PL_ItemClick);
            // 
            // barBtn_Import_Invoice
            // 
            this.barBtn_Import_Invoice.Caption = "Import Invoice";
            this.barBtn_Import_Invoice.Id = 3;
            this.barBtn_Import_Invoice.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("barBtn_Import_Invoice.ImageOptions.SvgImage")));
            this.barBtn_Import_Invoice.Name = "barBtn_Import_Invoice";
            this.barBtn_Import_Invoice.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtn_Import_Invoice_ItemClick);
            // 
            // barBtn_ExportPL
            // 
            this.barBtn_ExportPL.Caption = "Export Picking List";
            this.barBtn_ExportPL.Id = 4;
            this.barBtn_ExportPL.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("barBtn_ExportPL.ImageOptions.Image")));
            this.barBtn_ExportPL.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("barBtn_ExportPL.ImageOptions.LargeImage")));
            this.barBtn_ExportPL.Name = "barBtn_ExportPL";
            // 
            // barBtn_Inv_Clear_Data
            // 
            this.barBtn_Inv_Clear_Data.Caption = "Clear Invoice Data";
            this.barBtn_Inv_Clear_Data.Id = 5;
            this.barBtn_Inv_Clear_Data.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("barBtn_Inv_Clear_Data.ImageOptions.SvgImage")));
            this.barBtn_Inv_Clear_Data.Name = "barBtn_Inv_Clear_Data";
            // 
            // barBtn_PL_Clear_Data
            // 
            this.barBtn_PL_Clear_Data.Caption = "Clear Picking List Data";
            this.barBtn_PL_Clear_Data.Id = 6;
            this.barBtn_PL_Clear_Data.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("barBtn_PL_Clear_Data.ImageOptions.SvgImage")));
            this.barBtn_PL_Clear_Data.Name = "barBtn_PL_Clear_Data";
            // 
            // ribbonPage_Action
            // 
            this.ribbonPage_Action.Groups.AddRange(new DevExpress.XtraBars.Ribbon.RibbonPageGroup[] {
            this.ribbonPageGroup1,
            this.ribbonPageGroup2});
            this.ribbonPage_Action.Name = "ribbonPage_Action";
            this.ribbonPage_Action.Text = "Action";
            // 
            // ribbonPageGroup1
            // 
            this.ribbonPageGroup1.ItemLinks.Add(this.barBtn_Compare_Data_Inv_PL);
            this.ribbonPageGroup1.ItemLinks.Add(this.barBtn_ExportPL);
            this.ribbonPageGroup1.ItemLinks.Add(this.barBtn_PL_Clear_Data);
            this.ribbonPageGroup1.Name = "ribbonPageGroup1";
            this.ribbonPageGroup1.Text = "Packing List";
            // 
            // ribbonPageGroup2
            // 
            this.ribbonPageGroup2.ItemLinks.Add(this.barBtn_Import_Invoice);
            this.ribbonPageGroup2.ItemLinks.Add(this.barBtn_Inv_Clear_Data);
            this.ribbonPageGroup2.Name = "ribbonPageGroup2";
            this.ribbonPageGroup2.Text = "Invoice";
            // 
            // ribbonStatusBar
            // 
            this.ribbonStatusBar.Location = new System.Drawing.Point(0, 674);
            this.ribbonStatusBar.Name = "ribbonStatusBar";
            this.ribbonStatusBar.Ribbon = this.ribbon;
            this.ribbonStatusBar.Size = new System.Drawing.Size(1064, 24);
            // 
            // panelControl1
            // 
            this.panelControl1.Controls.Add(this.lbl_PickingList);
            this.panelControl1.Controls.Add(this.sLookUp_PickingList);
            this.panelControl1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelControl1.Location = new System.Drawing.Point(2, 2);
            this.panelControl1.Name = "panelControl1";
            this.panelControl1.Size = new System.Drawing.Size(1060, 36);
            this.panelControl1.TabIndex = 2;
            // 
            // lbl_PickingList
            // 
            this.lbl_PickingList.Appearance.Font = new System.Drawing.Font("Segoe UI", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbl_PickingList.Appearance.Options.UseFont = true;
            this.lbl_PickingList.Appearance.Options.UseTextOptions = true;
            this.lbl_PickingList.Appearance.TextOptions.HAlignment = DevExpress.Utils.HorzAlignment.Center;
            this.lbl_PickingList.Appearance.TextOptions.VAlignment = DevExpress.Utils.VertAlignment.Center;
            this.lbl_PickingList.AutoSizeMode = DevExpress.XtraEditors.LabelAutoSizeMode.None;
            this.lbl_PickingList.BorderStyle = DevExpress.XtraEditors.Controls.BorderStyles.Simple;
            this.lbl_PickingList.Location = new System.Drawing.Point(4, 4);
            this.lbl_PickingList.Name = "lbl_PickingList";
            this.lbl_PickingList.Size = new System.Drawing.Size(113, 20);
            this.lbl_PickingList.TabIndex = 6;
            this.lbl_PickingList.Text = "PICKING LIST NO";
            // 
            // sLookUp_PickingList
            // 
            this.sLookUp_PickingList.Location = new System.Drawing.Point(118, 4);
            this.sLookUp_PickingList.MenuManager = this.ribbon;
            this.sLookUp_PickingList.Name = "sLookUp_PickingList";
            this.sLookUp_PickingList.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.sLookUp_PickingList.Properties.PopupView = this.gridView_sLookUp_PL_No;
            this.sLookUp_PickingList.Size = new System.Drawing.Size(137, 20);
            this.sLookUp_PickingList.TabIndex = 5;
            this.sLookUp_PickingList.Closed += new DevExpress.XtraEditors.Controls.ClosedEventHandler(this.sLookUp_PickingList_Closed);
            // 
            // gridView_sLookUp_PL_No
            // 
            this.gridView_sLookUp_PL_No.FocusRectStyle = DevExpress.XtraGrid.Views.Grid.DrawFocusRectStyle.RowFocus;
            this.gridView_sLookUp_PL_No.Name = "gridView_sLookUp_PL_No";
            this.gridView_sLookUp_PL_No.OptionsSelection.EnableAppearanceFocusedCell = false;
            this.gridView_sLookUp_PL_No.OptionsView.ShowGroupPanel = false;
            // 
            // panelControl2
            // 
            this.panelControl2.Controls.Add(this.xtraTabControl1);
            this.panelControl2.Controls.Add(this.panelControl1);
            this.panelControl2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelControl2.Location = new System.Drawing.Point(0, 158);
            this.panelControl2.Name = "panelControl2";
            this.panelControl2.Size = new System.Drawing.Size(1064, 516);
            this.panelControl2.TabIndex = 4;
            // 
            // xtraTabControl1
            // 
            this.xtraTabControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.xtraTabControl1.Location = new System.Drawing.Point(2, 38);
            this.xtraTabControl1.Name = "xtraTabControl1";
            this.xtraTabControl1.SelectedTabPage = this.xtraTabPage1;
            this.xtraTabControl1.Size = new System.Drawing.Size(1060, 476);
            this.xtraTabControl1.TabIndex = 3;
            this.xtraTabControl1.TabPages.AddRange(new DevExpress.XtraTab.XtraTabPage[] {
            this.xtraTabPage1,
            this.xtraTabPage2});
            // 
            // xtraTabPage1
            // 
            this.xtraTabPage1.Controls.Add(this.gridControl_Picking_List);
            this.xtraTabPage1.Name = "xtraTabPage1";
            this.xtraTabPage1.Size = new System.Drawing.Size(1058, 451);
            this.xtraTabPage1.Text = "Picking List";
            // 
            // gridControl_Picking_List
            // 
            this.gridControl_Picking_List.Dock = System.Windows.Forms.DockStyle.Fill;
            this.gridControl_Picking_List.Location = new System.Drawing.Point(0, 0);
            this.gridControl_Picking_List.MainView = this.gridView_Picking_List;
            this.gridControl_Picking_List.MenuManager = this.ribbon;
            this.gridControl_Picking_List.Name = "gridControl_Picking_List";
            this.gridControl_Picking_List.Size = new System.Drawing.Size(1058, 451);
            this.gridControl_Picking_List.TabIndex = 0;
            this.gridControl_Picking_List.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView_Picking_List});
            // 
            // gridView_Picking_List
            // 
            this.gridView_Picking_List.GridControl = this.gridControl_Picking_List;
            this.gridView_Picking_List.Name = "gridView_Picking_List";
            // 
            // xtraTabPage2
            // 
            this.xtraTabPage2.Controls.Add(this.gridControl_Invoice);
            this.xtraTabPage2.Name = "xtraTabPage2";
            this.xtraTabPage2.Size = new System.Drawing.Size(1058, 451);
            this.xtraTabPage2.Text = "Invoice";
            // 
            // gridControl_Invoice
            // 
            this.gridControl_Invoice.Dock = System.Windows.Forms.DockStyle.Fill;
            this.gridControl_Invoice.Location = new System.Drawing.Point(0, 0);
            this.gridControl_Invoice.MainView = this.gridView_Invoice;
            this.gridControl_Invoice.MenuManager = this.ribbon;
            this.gridControl_Invoice.Name = "gridControl_Invoice";
            this.gridControl_Invoice.Size = new System.Drawing.Size(1058, 451);
            this.gridControl_Invoice.TabIndex = 0;
            this.gridControl_Invoice.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView_Invoice});
            // 
            // gridView_Invoice
            // 
            this.gridView_Invoice.GridControl = this.gridControl_Invoice;
            this.gridView_Invoice.Name = "gridView_Invoice";
            this.gridView_Invoice.OptionsBehavior.Editable = false;
            // 
            // Form_Main
            // 
            this.Appearance.BackColor = System.Drawing.Color.Black;
            this.Appearance.Options.UseBackColor = true;
            this.Appearance.Options.UseFont = true;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1064, 698);
            this.Controls.Add(this.panelControl2);
            this.Controls.Add(this.ribbonStatusBar);
            this.Controls.Add(this.ribbon);
            this.Font = new System.Drawing.Font("Segoe UI", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.IconOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("Form_Main.IconOptions.SvgImage")));
            this.Name = "Form_Main";
            this.Ribbon = this.ribbon;
            this.StatusBar = this.ribbonStatusBar;
            this.Text = "COMPARE INVOICE AND PICKING LIST";
            this.Load += new System.EventHandler(this.Form_Main_Load);
            ((System.ComponentModel.ISupportInitialize)(this.ribbon)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl1)).EndInit();
            this.panelControl1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.sLookUp_PickingList.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_sLookUp_PL_No)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl2)).EndInit();
            this.panelControl2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.xtraTabControl1)).EndInit();
            this.xtraTabControl1.ResumeLayout(false);
            this.xtraTabPage1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_Picking_List)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Picking_List)).EndInit();
            this.xtraTabPage2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_Invoice)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Invoice)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DevExpress.XtraBars.Ribbon.RibbonControl ribbon;
        private DevExpress.XtraBars.Ribbon.RibbonPage ribbonPage_Action;
        private DevExpress.XtraBars.Ribbon.RibbonPageGroup ribbonPageGroup1;
        private DevExpress.XtraBars.Ribbon.RibbonStatusBar ribbonStatusBar;
        private DevExpress.XtraEditors.PanelControl panelControl1;
        private DevExpress.XtraEditors.PanelControl panelControl2;
        private DevExpress.XtraGrid.GridControl gridControl_Invoice;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView_Invoice;
        private DevExpress.XtraBars.BarButtonItem barBtn_Transfer_Data;
        private DevExpress.XtraBars.BarButtonItem barBtn_Compare_Data_Inv_PL;
        private DevExpress.XtraBars.BarButtonItem barBtn_Import_Invoice;
        private DevExpress.XtraBars.Ribbon.RibbonPageGroup ribbonPageGroup2;
        private DevExpress.XtraTab.XtraTabControl xtraTabControl1;
        private DevExpress.XtraTab.XtraTabPage xtraTabPage1;
        private DevExpress.XtraGrid.GridControl gridControl_Picking_List;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView_Picking_List;
        private DevExpress.XtraTab.XtraTabPage xtraTabPage2;
        private DevExpress.XtraEditors.LabelControl lbl_PickingList;
        private DevExpress.XtraEditors.SearchLookUpEdit sLookUp_PickingList;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView_sLookUp_PL_No;
        private DevExpress.XtraBars.BarButtonItem barBtn_ExportPL;
        private DevExpress.XtraBars.BarButtonItem barBtn_Inv_Clear_Data;
        private DevExpress.XtraBars.BarButtonItem barBtn_PL_Clear_Data;
    }
}