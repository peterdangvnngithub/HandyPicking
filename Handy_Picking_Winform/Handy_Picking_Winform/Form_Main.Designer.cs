
namespace Handy_Picking_Winform
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
            this.barBtn_Compare_Data_Inv_PL = new DevExpress.XtraBars.BarButtonItem();
            this.barBtn_Import_Data_Compare = new DevExpress.XtraBars.BarButtonItem();
            this.barBtn_ExportPL_Merge = new DevExpress.XtraBars.BarButtonItem();
            this.barBtn_Inv_Clear_Data = new DevExpress.XtraBars.BarButtonItem();
            this.barBtn_Clear_PL_Data = new DevExpress.XtraBars.BarButtonItem();
            this.barBtnLoadPackagingStandards = new DevExpress.XtraBars.BarButtonItem();
            this.barBtn_Add_PickingList = new DevExpress.XtraBars.BarButtonItem();
            this.barBtnLockPickingList = new DevExpress.XtraBars.BarButtonItem();
            this.btnSavePicking = new DevExpress.XtraBars.BarButtonItem();
            this.barStaticUser = new DevExpress.XtraBars.BarStaticItem();
            this.btn_InputPacking = new DevExpress.XtraBars.BarButtonItem();
            this.barBtn_Merge_Picking_List = new DevExpress.XtraBars.BarButtonItem();
            this.ribbonPage_Action = new DevExpress.XtraBars.Ribbon.RibbonPage();
            this.ribbonPageGroup1 = new DevExpress.XtraBars.Ribbon.RibbonPageGroup();
            this.ribbonPageGroup2 = new DevExpress.XtraBars.Ribbon.RibbonPageGroup();
            this.ribbonPageGroup3 = new DevExpress.XtraBars.Ribbon.RibbonPageGroup();
            this.ribbonPageGroup4 = new DevExpress.XtraBars.Ribbon.RibbonPageGroup();
            this.ribbonStatusBar = new DevExpress.XtraBars.Ribbon.RibbonStatusBar();
            this.panelControl1 = new DevExpress.XtraEditors.PanelControl();
            this.lbl_PickingList = new DevExpress.XtraEditors.LabelControl();
            this.sLookUp_PickingList = new DevExpress.XtraEditors.SearchLookUpEdit();
            this.gridView_sLookUp_PL_No = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.xMainTab = new DevExpress.XtraTab.XtraTabControl();
            this.xtraTabPage1 = new DevExpress.XtraTab.XtraTabPage();
            this.gridControl_Picking_List = new DevExpress.XtraGrid.GridControl();
            this.gridView_Picking_List = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.xtraTabPage2 = new DevExpress.XtraTab.XtraTabPage();
            this.gridControl_Data_Compare = new DevExpress.XtraGrid.GridControl();
            this.gridView_Data_Compare = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.xtraTabPage3 = new DevExpress.XtraTab.XtraTabPage();
            this.panel1 = new System.Windows.Forms.Panel();
            this.gridControl_CompareWithInvoice = new DevExpress.XtraGrid.GridControl();
            this.gridView_Compare_With_Invoice = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.xtraTabPage4 = new DevExpress.XtraTab.XtraTabPage();
            this.gridControl_Picking_List_Merge = new DevExpress.XtraGrid.GridControl();
            this.gridView_Picking_List_Merge = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.panelControl2 = new DevExpress.XtraEditors.PanelControl();
            this.gridControl1 = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            ((System.ComponentModel.ISupportInitialize)(this.ribbon)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl1)).BeginInit();
            this.panelControl1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sLookUp_PickingList.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_sLookUp_PL_No)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.xMainTab)).BeginInit();
            this.xMainTab.SuspendLayout();
            this.xtraTabPage1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_Picking_List)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Picking_List)).BeginInit();
            this.xtraTabPage2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_Data_Compare)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Data_Compare)).BeginInit();
            this.xtraTabPage3.SuspendLayout();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_CompareWithInvoice)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Compare_With_Invoice)).BeginInit();
            this.xtraTabPage4.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_Picking_List_Merge)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Picking_List_Merge)).BeginInit();
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
            this.barBtn_Compare_Data_Inv_PL,
            this.barBtn_Import_Data_Compare,
            this.barBtn_ExportPL_Merge,
            this.barBtn_Inv_Clear_Data,
            this.barBtn_Clear_PL_Data,
            this.barBtnLoadPackagingStandards,
            this.barBtn_Add_PickingList,
            this.barBtnLockPickingList,
            this.btnSavePicking,
            this.barStaticUser,
            this.btn_InputPacking,
            this.barBtn_Merge_Picking_List});
            this.ribbon.Location = new System.Drawing.Point(0, 0);
            this.ribbon.MaxItemId = 17;
            this.ribbon.Name = "ribbon";
            this.ribbon.Pages.AddRange(new DevExpress.XtraBars.Ribbon.RibbonPage[] {
            this.ribbonPage_Action});
            this.ribbon.Size = new System.Drawing.Size(997, 158);
            this.ribbon.StatusBar = this.ribbonStatusBar;
            // 
            // barBtn_Compare_Data_Inv_PL
            // 
            this.barBtn_Compare_Data_Inv_PL.Caption = "&So sánh Data Compare và Picking List";
            this.barBtn_Compare_Data_Inv_PL.Id = 2;
            this.barBtn_Compare_Data_Inv_PL.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("barBtn_Compare_Data_Inv_PL.ImageOptions.SvgImage")));
            this.barBtn_Compare_Data_Inv_PL.Name = "barBtn_Compare_Data_Inv_PL";
            this.barBtn_Compare_Data_Inv_PL.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtn_Compare_Data_Inv_PL_ItemClick);
            // 
            // barBtn_Import_Data_Compare
            // 
            this.barBtn_Import_Data_Compare.Caption = "Nhập dữ liệu so sánh";
            this.barBtn_Import_Data_Compare.Id = 3;
            this.barBtn_Import_Data_Compare.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("barBtn_Import_Data_Compare.ImageOptions.SvgImage")));
            this.barBtn_Import_Data_Compare.Name = "barBtn_Import_Data_Compare";
            this.barBtn_Import_Data_Compare.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtn_Import_Invoice_ItemClick);
            // 
            // barBtn_ExportPL_Merge
            // 
            this.barBtn_ExportPL_Merge.Caption = "Xuất Picking List Merge";
            this.barBtn_ExportPL_Merge.Id = 4;
            this.barBtn_ExportPL_Merge.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("barBtn_ExportPL_Merge.ImageOptions.Image")));
            this.barBtn_ExportPL_Merge.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("barBtn_ExportPL_Merge.ImageOptions.LargeImage")));
            this.barBtn_ExportPL_Merge.Name = "barBtn_ExportPL_Merge";
            this.barBtn_ExportPL_Merge.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtn_ExportPL_ItemClick);
            // 
            // barBtn_Inv_Clear_Data
            // 
            this.barBtn_Inv_Clear_Data.Caption = "Xóa Dữ Liệu So Sánh";
            this.barBtn_Inv_Clear_Data.Id = 5;
            this.barBtn_Inv_Clear_Data.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("barBtn_Inv_Clear_Data.ImageOptions.SvgImage")));
            this.barBtn_Inv_Clear_Data.Name = "barBtn_Inv_Clear_Data";
            this.barBtn_Inv_Clear_Data.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtn_Inv_Clear_Data_ItemClick);
            // 
            // barBtn_Clear_PL_Data
            // 
            this.barBtn_Clear_PL_Data.Caption = "Clear Picking List";
            this.barBtn_Clear_PL_Data.Id = 6;
            this.barBtn_Clear_PL_Data.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("barBtn_Clear_PL_Data.ImageOptions.SvgImage")));
            this.barBtn_Clear_PL_Data.Name = "barBtn_Clear_PL_Data";
            this.barBtn_Clear_PL_Data.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtn_Clear_PL_Data_ItemClick);
            // 
            // barBtnLoadPackagingStandards
            // 
            this.barBtnLoadPackagingStandards.Caption = "Tải quy cách đóng gói";
            this.barBtnLoadPackagingStandards.Id = 7;
            this.barBtnLoadPackagingStandards.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("barBtnLoadPackagingStandards.ImageOptions.SvgImage")));
            this.barBtnLoadPackagingStandards.Name = "barBtnLoadPackagingStandards";
            this.barBtnLoadPackagingStandards.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtnLoadPackagingStandards_ItemClick);
            // 
            // barBtn_Add_PickingList
            // 
            this.barBtn_Add_PickingList.Caption = "Thêm Picking List";
            this.barBtn_Add_PickingList.Id = 8;
            this.barBtn_Add_PickingList.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("barBtn_Add_PickingList.ImageOptions.SvgImage")));
            this.barBtn_Add_PickingList.Name = "barBtn_Add_PickingList";
            this.barBtn_Add_PickingList.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtn_Add_PickingList_ItemClick);
            // 
            // barBtnLockPickingList
            // 
            this.barBtnLockPickingList.Caption = "Khóa/ Mở Khóa PickingList";
            this.barBtnLockPickingList.Id = 9;
            this.barBtnLockPickingList.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("barBtnLockPickingList.ImageOptions.SvgImage")));
            this.barBtnLockPickingList.Name = "barBtnLockPickingList";
            this.barBtnLockPickingList.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtnLockPickingList_ItemClick);
            // 
            // btnSavePicking
            // 
            this.btnSavePicking.Caption = "Lưu Picking List";
            this.btnSavePicking.Id = 10;
            this.btnSavePicking.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("btnSavePicking.ImageOptions.SvgImage")));
            this.btnSavePicking.Name = "btnSavePicking";
            this.btnSavePicking.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnSavePicking_ItemClick);
            // 
            // barStaticUser
            // 
            this.barStaticUser.Alignment = DevExpress.XtraBars.BarItemLinkAlignment.Right;
            this.barStaticUser.Caption = "User1";
            this.barStaticUser.Id = 11;
            this.barStaticUser.Name = "barStaticUser";
            // 
            // btn_InputPacking
            // 
            this.btn_InputPacking.Caption = "Nhập quy cách đóng gói";
            this.btn_InputPacking.Id = 13;
            this.btn_InputPacking.ImageOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("btn_InputPacking.ImageOptions.SvgImage")));
            this.btn_InputPacking.Name = "btn_InputPacking";
            this.btn_InputPacking.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btn_InputPacking_ItemClick);
            // 
            // barBtn_Merge_Picking_List
            // 
            this.barBtn_Merge_Picking_List.Caption = "Gom Picking List";
            this.barBtn_Merge_Picking_List.Id = 16;
            this.barBtn_Merge_Picking_List.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("barBtn_Merge_Picking_List.ImageOptions.Image")));
            this.barBtn_Merge_Picking_List.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("barBtn_Merge_Picking_List.ImageOptions.LargeImage")));
            this.barBtn_Merge_Picking_List.Name = "barBtn_Merge_Picking_List";
            this.barBtn_Merge_Picking_List.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barBtn_Merge_Picking_List_ItemClick);
            // 
            // ribbonPage_Action
            // 
            this.ribbonPage_Action.Groups.AddRange(new DevExpress.XtraBars.Ribbon.RibbonPageGroup[] {
            this.ribbonPageGroup1,
            this.ribbonPageGroup2,
            this.ribbonPageGroup3,
            this.ribbonPageGroup4});
            this.ribbonPage_Action.Name = "ribbonPage_Action";
            this.ribbonPage_Action.Text = "Action";
            // 
            // ribbonPageGroup1
            // 
            this.ribbonPageGroup1.ItemLinks.Add(this.barBtn_Add_PickingList);
            this.ribbonPageGroup1.ItemLinks.Add(this.barBtn_Clear_PL_Data);
            this.ribbonPageGroup1.ItemLinks.Add(this.barBtnLoadPackagingStandards);
            this.ribbonPageGroup1.ItemLinks.Add(this.btnSavePicking);
            this.ribbonPageGroup1.ItemLinks.Add(this.barBtn_ExportPL_Merge);
            this.ribbonPageGroup1.ItemLinks.Add(this.barBtnLockPickingList);
            this.ribbonPageGroup1.ItemLinks.Add(this.barBtn_Merge_Picking_List);
            this.ribbonPageGroup1.Name = "ribbonPageGroup1";
            this.ribbonPageGroup1.Text = "Picking List";
            // 
            // ribbonPageGroup2
            // 
            this.ribbonPageGroup2.ItemLinks.Add(this.barBtn_Import_Data_Compare);
            this.ribbonPageGroup2.ItemLinks.Add(this.barBtn_Inv_Clear_Data);
            this.ribbonPageGroup2.Name = "ribbonPageGroup2";
            this.ribbonPageGroup2.Text = "Data Compare";
            // 
            // ribbonPageGroup3
            // 
            this.ribbonPageGroup3.ItemLinks.Add(this.barBtn_Compare_Data_Inv_PL);
            this.ribbonPageGroup3.Name = "ribbonPageGroup3";
            this.ribbonPageGroup3.Text = "Compare";
            // 
            // ribbonPageGroup4
            // 
            this.ribbonPageGroup4.ItemLinks.Add(this.btn_InputPacking);
            this.ribbonPageGroup4.Name = "ribbonPageGroup4";
            this.ribbonPageGroup4.Text = "Picking";
            // 
            // ribbonStatusBar
            // 
            this.ribbonStatusBar.ItemLinks.Add(this.barStaticUser);
            this.ribbonStatusBar.Location = new System.Drawing.Point(0, 682);
            this.ribbonStatusBar.Name = "ribbonStatusBar";
            this.ribbonStatusBar.Ribbon = this.ribbon;
            this.ribbonStatusBar.Size = new System.Drawing.Size(997, 24);
            // 
            // panelControl1
            // 
            this.panelControl1.Controls.Add(this.lbl_PickingList);
            this.panelControl1.Controls.Add(this.sLookUp_PickingList);
            this.panelControl1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelControl1.Location = new System.Drawing.Point(2, 2);
            this.panelControl1.Name = "panelControl1";
            this.panelControl1.Size = new System.Drawing.Size(993, 36);
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
            this.sLookUp_PickingList.Properties.EditValueChanged += new System.EventHandler(this.sLookUp_PickingList_Properties_EditValueChanged);
            this.sLookUp_PickingList.Size = new System.Drawing.Size(137, 20);
            this.sLookUp_PickingList.TabIndex = 5;
            // 
            // gridView_sLookUp_PL_No
            // 
            this.gridView_sLookUp_PL_No.FocusRectStyle = DevExpress.XtraGrid.Views.Grid.DrawFocusRectStyle.RowFocus;
            this.gridView_sLookUp_PL_No.Name = "gridView_sLookUp_PL_No";
            this.gridView_sLookUp_PL_No.OptionsSelection.EnableAppearanceFocusedCell = false;
            this.gridView_sLookUp_PL_No.OptionsView.ShowGroupPanel = false;
            // 
            // xMainTab
            // 
            this.xMainTab.Dock = System.Windows.Forms.DockStyle.Fill;
            this.xMainTab.Location = new System.Drawing.Point(2, 38);
            this.xMainTab.Name = "xMainTab";
            this.xMainTab.SelectedTabPage = this.xtraTabPage1;
            this.xMainTab.Size = new System.Drawing.Size(993, 484);
            this.xMainTab.TabIndex = 1;
            this.xMainTab.TabPages.AddRange(new DevExpress.XtraTab.XtraTabPage[] {
            this.xtraTabPage1,
            this.xtraTabPage2,
            this.xtraTabPage3,
            this.xtraTabPage4});
            // 
            // xtraTabPage1
            // 
            this.xtraTabPage1.Controls.Add(this.gridControl_Picking_List);
            this.xtraTabPage1.Name = "xtraTabPage1";
            this.xtraTabPage1.Size = new System.Drawing.Size(991, 459);
            this.xtraTabPage1.Text = "Picking List";
            // 
            // gridControl_Picking_List
            // 
            this.gridControl_Picking_List.Dock = System.Windows.Forms.DockStyle.Fill;
            this.gridControl_Picking_List.Location = new System.Drawing.Point(0, 0);
            this.gridControl_Picking_List.MainView = this.gridView_Picking_List;
            this.gridControl_Picking_List.MenuManager = this.ribbon;
            this.gridControl_Picking_List.Name = "gridControl_Picking_List";
            this.gridControl_Picking_List.Size = new System.Drawing.Size(991, 459);
            this.gridControl_Picking_List.TabIndex = 0;
            this.gridControl_Picking_List.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView_Picking_List});
            // 
            // gridView_Picking_List
            // 
            this.gridView_Picking_List.GridControl = this.gridControl_Picking_List;
            this.gridView_Picking_List.IndicatorWidth = 50;
            this.gridView_Picking_List.Name = "gridView_Picking_List";
            this.gridView_Picking_List.CellMerge += new DevExpress.XtraGrid.Views.Grid.CellMergeEventHandler(this.gridView_Picking_List_CellMerge);
            // 
            // xtraTabPage2
            // 
            this.xtraTabPage2.Controls.Add(this.gridControl_Data_Compare);
            this.xtraTabPage2.Name = "xtraTabPage2";
            this.xtraTabPage2.Size = new System.Drawing.Size(991, 459);
            this.xtraTabPage2.Text = "Data Compare";
            // 
            // gridControl_Data_Compare
            // 
            this.gridControl_Data_Compare.Dock = System.Windows.Forms.DockStyle.Fill;
            this.gridControl_Data_Compare.Location = new System.Drawing.Point(0, 0);
            this.gridControl_Data_Compare.MainView = this.gridView_Data_Compare;
            this.gridControl_Data_Compare.MenuManager = this.ribbon;
            this.gridControl_Data_Compare.Name = "gridControl_Data_Compare";
            this.gridControl_Data_Compare.Size = new System.Drawing.Size(991, 459);
            this.gridControl_Data_Compare.TabIndex = 0;
            this.gridControl_Data_Compare.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView_Data_Compare});
            // 
            // gridView_Data_Compare
            // 
            this.gridView_Data_Compare.GridControl = this.gridControl_Data_Compare;
            this.gridView_Data_Compare.IndicatorWidth = 50;
            this.gridView_Data_Compare.Name = "gridView_Data_Compare";
            this.gridView_Data_Compare.OptionsBehavior.Editable = false;
            // 
            // xtraTabPage3
            // 
            this.xtraTabPage3.Controls.Add(this.panel1);
            this.xtraTabPage3.Name = "xtraTabPage3";
            this.xtraTabPage3.Size = new System.Drawing.Size(991, 459);
            this.xtraTabPage3.Text = "Compare";
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.gridControl_CompareWithInvoice);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(991, 459);
            this.panel1.TabIndex = 2;
            // 
            // gridControl_CompareWithInvoice
            // 
            this.gridControl_CompareWithInvoice.Dock = System.Windows.Forms.DockStyle.Fill;
            this.gridControl_CompareWithInvoice.Location = new System.Drawing.Point(0, 0);
            this.gridControl_CompareWithInvoice.MainView = this.gridView_Compare_With_Invoice;
            this.gridControl_CompareWithInvoice.MenuManager = this.ribbon;
            this.gridControl_CompareWithInvoice.Name = "gridControl_CompareWithInvoice";
            this.gridControl_CompareWithInvoice.Size = new System.Drawing.Size(991, 459);
            this.gridControl_CompareWithInvoice.TabIndex = 3;
            this.gridControl_CompareWithInvoice.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView_Compare_With_Invoice});
            // 
            // gridView_Compare_With_Invoice
            // 
            this.gridView_Compare_With_Invoice.GridControl = this.gridControl_CompareWithInvoice;
            this.gridView_Compare_With_Invoice.Name = "gridView_Compare_With_Invoice";
            // 
            // xtraTabPage4
            // 
            this.xtraTabPage4.Controls.Add(this.gridControl_Picking_List_Merge);
            this.xtraTabPage4.Name = "xtraTabPage4";
            this.xtraTabPage4.Size = new System.Drawing.Size(991, 459);
            this.xtraTabPage4.Text = "Picking List Merge";
            // 
            // gridControl_Picking_List_Merge
            // 
            this.gridControl_Picking_List_Merge.Dock = System.Windows.Forms.DockStyle.Fill;
            this.gridControl_Picking_List_Merge.Location = new System.Drawing.Point(0, 0);
            this.gridControl_Picking_List_Merge.MainView = this.gridView_Picking_List_Merge;
            this.gridControl_Picking_List_Merge.MenuManager = this.ribbon;
            this.gridControl_Picking_List_Merge.Name = "gridControl_Picking_List_Merge";
            this.gridControl_Picking_List_Merge.Size = new System.Drawing.Size(991, 459);
            this.gridControl_Picking_List_Merge.TabIndex = 0;
            this.gridControl_Picking_List_Merge.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView_Picking_List_Merge});
            // 
            // gridView_Picking_List_Merge
            // 
            this.gridView_Picking_List_Merge.GridControl = this.gridControl_Picking_List_Merge;
            this.gridView_Picking_List_Merge.Name = "gridView_Picking_List_Merge";
            // 
            // panelControl2
            // 
            this.panelControl2.Controls.Add(this.xMainTab);
            this.panelControl2.Controls.Add(this.panelControl1);
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
            // Form_Main
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
            this.IconOptions.SvgImage = ((DevExpress.Utils.Svg.SvgImage)(resources.GetObject("Form_Main.IconOptions.SvgImage")));
            this.Name = "Form_Main";
            this.Ribbon = this.ribbon;
            this.StatusBar = this.ribbonStatusBar;
            this.Text = "COMPARE INVOICE AND PICKING LIST";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form_Main_FormClosing);
            this.Load += new System.EventHandler(this.Form_Main_Load);
            ((System.ComponentModel.ISupportInitialize)(this.ribbon)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl1)).EndInit();
            this.panelControl1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.sLookUp_PickingList.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_sLookUp_PL_No)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.xMainTab)).EndInit();
            this.xMainTab.ResumeLayout(false);
            this.xtraTabPage1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_Picking_List)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Picking_List)).EndInit();
            this.xtraTabPage2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_Data_Compare)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Data_Compare)).EndInit();
            this.xtraTabPage3.ResumeLayout(false);
            this.panel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_CompareWithInvoice)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Compare_With_Invoice)).EndInit();
            this.xtraTabPage4.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.gridControl_Picking_List_Merge)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView_Picking_List_Merge)).EndInit();
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
        private DevExpress.XtraBars.BarButtonItem barBtn_Compare_Data_Inv_PL;
        private DevExpress.XtraBars.BarButtonItem barBtn_Import_Data_Compare;
        private DevExpress.XtraBars.Ribbon.RibbonPageGroup ribbonPageGroup2;
        private DevExpress.XtraBars.BarButtonItem barBtn_ExportPL_Merge;
        private DevExpress.XtraBars.BarButtonItem barBtn_Inv_Clear_Data;
        private DevExpress.XtraBars.BarButtonItem barBtn_Clear_PL_Data;
        private DevExpress.XtraBars.BarButtonItem barBtnLoadPackagingStandards;
        private DevExpress.XtraBars.BarButtonItem barBtn_Add_PickingList;
        private DevExpress.XtraBars.BarButtonItem barBtnLockPickingList;
        private DevExpress.XtraBars.BarButtonItem btnSavePicking;
        private DevExpress.XtraEditors.PanelControl panelControl1;
        private DevExpress.XtraEditors.LabelControl lbl_PickingList;
        private DevExpress.XtraEditors.SearchLookUpEdit sLookUp_PickingList;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView_sLookUp_PL_No;
        private DevExpress.XtraTab.XtraTabControl xMainTab;
        private DevExpress.XtraTab.XtraTabPage xtraTabPage1;
        private DevExpress.XtraGrid.GridControl gridControl_Picking_List;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView_Picking_List;
        private DevExpress.XtraTab.XtraTabPage xtraTabPage2;
        private DevExpress.XtraGrid.GridControl gridControl_Data_Compare;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView_Data_Compare;
        private DevExpress.XtraTab.XtraTabPage xtraTabPage3;
        private DevExpress.XtraEditors.PanelControl panelControl2;
        private System.Windows.Forms.Panel panel1;
        private DevExpress.XtraBars.Ribbon.RibbonPageGroup ribbonPageGroup3;
        private DevExpress.XtraBars.BarStaticItem barStaticUser;
        private DevExpress.XtraGrid.GridControl gridControl_CompareWithInvoice;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView_Compare_With_Invoice;
        private DevExpress.XtraBars.Ribbon.RibbonPageGroup ribbonPageGroup4;
        private DevExpress.XtraBars.BarButtonItem btn_InputPacking;
        private DevExpress.XtraGrid.GridControl gridControl1;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private DevExpress.XtraBars.BarButtonItem barBtn_Merge_Picking_List;
        private DevExpress.XtraTab.XtraTabPage xtraTabPage4;
        private DevExpress.XtraGrid.GridControl gridControl_Picking_List_Merge;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView_Picking_List_Merge;
    }
}