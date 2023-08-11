using System;
using System.Data;
using System.Linq;
using System.Drawing;
using System.Diagnostics;
using System.Windows.Forms;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using DevExpress.Data;
using DevExpress.Utils;
using DevExpress.DataAccess.Excel;
using DevExpress.XtraBars;
using DevExpress.XtraEditors;
using DevExpress.XtraGrid.Columns;
using DevExpress.XtraGrid.Views.Grid;
using DevExpress.XtraGrid.Views.Base;
using DevExpress.XtraEditors.Controls;
using DevExpress.XtraEditors.Repository;

namespace Handy_Picking_Winform
{
    public partial class Form_Main : DevExpress.XtraBars.Ribbon.RibbonForm
    {
        #region Define the global variable
        // PICKING LIST
        private readonly GridColumn grid_PL_Col_DELETE_ROW                      = new GridColumn();
        private readonly GridColumn grid_PL_Col_PICKING_LIST_NO                 = new GridColumn();
        private readonly GridColumn grid_PL_Col_PALLET_NO                       = new GridColumn();
        private readonly GridColumn grid_PL_Col_CUS_ITEM_CODE                   = new GridColumn();
        private readonly GridColumn grid_PL_Col_TVC_ITEM_CODE                   = new GridColumn();
        private readonly GridColumn grid_PL_Col_SERIES                          = new GridColumn();
        private readonly GridColumn grid_PL_Col_CUSTOMER_PO                     = new GridColumn();
        private readonly GridColumn grid_PL_Col_QTY_CARTON                      = new GridColumn();
        private readonly GridColumn grid_PL_Col_QTY_PER_CARTON                  = new GridColumn();
        private readonly GridColumn grid_PL_Col_QTY_TOTAL                       = new GridColumn();
        private readonly GridColumn grid_PL_Col_NET_WEIGHT                      = new GridColumn();
        private readonly GridColumn grid_PL_Col_NET_WEIGHT_TOTAL                = new GridColumn();
        private readonly GridColumn grid_PL_Col_GROSS_WEIGHT                    = new GridColumn();
        private readonly GridColumn grid_PL_Col_LOT_NO                          = new GridColumn();
        private readonly RepositoryItemButtonEdit grid_PL_repo_btn_DeleteRow    = new RepositoryItemButtonEdit();

        // PICKING LIST MERGE
        private readonly GridColumn grid_PL_Merge_Col_PICKING_LIST_NO           = new GridColumn();
        private readonly GridColumn grid_PL_Merge_Col_PALLET_NO                 = new GridColumn();
        private readonly GridColumn grid_PL_Merge_Col_CUS_ITEM_CODE             = new GridColumn();
        private readonly GridColumn grid_PL_Merge_Col_TVC_ITEM_CODE             = new GridColumn();
        private readonly GridColumn grid_PL_Merge_Col_QTY_CARTON                = new GridColumn();
        private readonly GridColumn grid_PL_Merge_Col_QTY_PER_CARTON            = new GridColumn();
        private readonly GridColumn grid_PL_Merge_Col_QTY_TOTAL                 = new GridColumn();
        private readonly GridColumn grid_PL_Merge_Col_NET_WEIGHT_TOTAL          = new GridColumn();
        private readonly GridColumn grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL        = new GridColumn();
        private readonly GridColumn grid_PL_Merge_Col_LOT_NO                    = new GridColumn();

        // DATA COMPARE
        private readonly GridColumn grid_Data_Compare_Col_CUSTOMER_ITEM_CODE    = new GridColumn();
        private readonly GridColumn grid_Data_Compare_Col_TVC_ITEM_CODE         = new GridColumn();
        private readonly GridColumn grid_Data_Compare_Col_QUANTITY              = new GridColumn();

        // COMPARE WITH PACKING LIST
        private readonly GridColumn grid_Compare_Col_CUSTOMER_ITEM_CODE         = new GridColumn();
        private readonly GridColumn grid_Compare_Col_TVC_ITEM_CODE              = new GridColumn();
        private readonly GridColumn grid_Compare_Col_QUANTITY                   = new GridColumn();
        private readonly GridColumn grid_Compare_Col_PL_CUSTOMER_ITEM_CODE      = new GridColumn();
        private readonly GridColumn grid_Compare_Col_PL_TVC_ITEM_CODE           = new GridColumn();
        private readonly GridColumn grid_Compare_Col_PL_QUANTITY                = new GridColumn();
        private readonly GridColumn grid_Compare_Col_QUANTITY_DIFFERENCE        = new GridColumn();

        // PACKING LIST VIEW
        private readonly GridColumn grid_sLookUp_PL_CUSTOMER_CODE               = new GridColumn();
        private readonly GridColumn grid_sLookUp_PL_PICKING_LIST_NO             = new GridColumn();
        private readonly GridColumn grid_sLookUp_PL_STATUS                      = new GridColumn();
        private readonly GridColumn grid_sLookUp_PL_DELIVERY_ADDRESS            = new GridColumn();
        private readonly GridColumn grid_sLookUp_PL_EMPLOYEE_CODE               = new GridColumn();
        private readonly GridColumn grid_sLookUp_PL_CREATE_DATE                 = new GridColumn();

        List<HANDY_PICKING_DETAIL> list_Handy_Picking_Detail                    = new List<HANDY_PICKING_DETAIL>();
        List<HANDY_PICKING_DETAIL> list_Handy_Picking_Detail_Delete             = new List<HANDY_PICKING_DETAIL>();
        List<Data_Compare> list_DataCompare                                     = new List<Data_Compare>();
        List<Sum_DataCompare> sum_DataCompares                                  = new List<Sum_DataCompare>();
        List<Sum_PickingList> sum_PickingList                                   = new List<Sum_PickingList>();
        List<Compare_DataImport_With_PickingList> compare_Invoice_PickingList   = new List<Compare_DataImport_With_PickingList>();  
        USER_MS userMS                                                          = new USER_MS();
        #endregion

        public Form_Main(USER_MS _userMS)
        {
            InitializeComponent();
            userMS = _userMS;
        }

        private void Form_Main_Load(object sender, EventArgs e)
        {
            // Define gridview structure
            Define_GridView(gridView_Picking_List);
            Define_GridView(gridView_Picking_List_Merge);
            Define_GridView(gridView_Data_Compare);
            Define_GridView(gridView_Compare_With_Invoice);
            Define_GridView(gridView_sLookUp_PL_No);

            Setting_Data_Item_SLookUp(sLookUp_PickingList);
            SettingInit();
        }

        private void SettingInit()
        {
            barStaticUser.Caption = userMS.USERNAME;
        }

        private void Setting_Data_Item_SLookUp(SearchLookUpEdit item)
        {
            if (item.Name == sLookUp_PickingList.Name)
            {
                sLookUp_PickingList.Properties.DataSource       = Get_Data_PickingList_MS();
                sLookUp_PickingList.Properties.ValueMember      = "PICKING_LIST_NO";
                sLookUp_PickingList.Properties.DisplayMember    = "PICKING_LIST_NO";
            }
        }

        private List<HANDY_PICKING_MS> Get_Data_PickingList_MS()    
        {
            using (HANDY_PICKING_Entities db = new HANDY_PICKING_Entities())
            {   
                return db.HANDY_PICKING_MS.ToList();
            }
        }

        private List<HANDY_PICKING_DETAIL> Get_Data_PickingList_Detail(string PLNo)
        {
            using (HANDY_PICKING_Entities db = new HANDY_PICKING_Entities())
            {
                return db.HANDY_PICKING_DETAIL.Where(x => x.PICKING_LIST_NO.Equals(PLNo)).ToList();
            }
        }

        private void Define_GridView(GridView gridViewControl)
        {
            if (gridViewControl.Name == gridView_Picking_List.Name)
            {
                gridView_Picking_List.OptionsPrint.AutoWidth                        = false;
                gridView_Picking_List.OptionsView.ColumnAutoWidth                   = false;
                gridView_Picking_List.OptionsView.ColumnHeaderAutoHeight            = DefaultBoolean.True;
                gridView_Picking_List.OptionsView.AllowCellMerge                    = true;
                gridView_Picking_List.OptionsView.ShowFooter                        = true;
                gridView_Picking_List.CustomDrawRowIndicator                        += new RowIndicatorCustomDrawEventHandler(this.gridView_Picking_List_CustomDrawRowIndicator);

                // DELETE ROW
                grid_PL_Col_DELETE_ROW.ColumnEdit                                   = grid_PL_repo_btn_DeleteRow;
                grid_PL_Col_DELETE_ROW.Name                                         = "grid_PL_Col_DELETE_ROW";
                grid_PL_Col_DELETE_ROW.Visible                                      = true;
                grid_PL_Col_DELETE_ROW.VisibleIndex                                 = 0;
                grid_PL_Col_DELETE_ROW.Width                                        = 22;
                grid_PL_Col_DELETE_ROW.OptionsColumn.AllowMerge                     = DefaultBoolean.False;

                // REPOSITORY BUTTON DELETE ROW
                grid_PL_repo_btn_DeleteRow.AutoHeight                               = false;
                grid_PL_repo_btn_DeleteRow.Buttons.AddRange(new EditorButton[] {new EditorButton(ButtonPredefines.Delete)});
                grid_PL_repo_btn_DeleteRow.Name                                     = "grid_PL_repo_btn_DeleteRow";
                grid_PL_repo_btn_DeleteRow.TextEditStyle                            = TextEditStyles.HideTextEditor;
                grid_PL_repo_btn_DeleteRow.Click                                    += new EventHandler(grid_PL_repo_btn_DeleteRow_Click);

                // PICKING_LIST_NO
                grid_PL_Col_PICKING_LIST_NO.Name                                    = "grid_PL_Col_PICKING_LIST_NO";
                grid_PL_Col_PICKING_LIST_NO.Caption                                 = "PICKING LIST NO";
                grid_PL_Col_PICKING_LIST_NO.FieldName                               = "PICKING_LIST_NO";
                grid_PL_Col_PICKING_LIST_NO.VisibleIndex                            = 0;
                grid_PL_Col_PICKING_LIST_NO.Width                                   = 120;
                grid_PL_Col_PICKING_LIST_NO.OptionsColumn.AllowEdit                 = false;
                grid_PL_Col_PICKING_LIST_NO.OptionsColumn.AllowMerge                = DefaultBoolean.False;
                grid_PL_Col_PICKING_LIST_NO.AppearanceCell.Options.UseTextOptions   = true;
                grid_PL_Col_PICKING_LIST_NO.AppearanceCell.TextOptions.HAlignment   = HorzAlignment.Center;

                // PALLET_NO
                grid_PL_Col_PALLET_NO.Name                                          = "grid_PL_Col_PALLET_NO";
                grid_PL_Col_PALLET_NO.Caption                                       = "PALLET NO";
                grid_PL_Col_PALLET_NO.FieldName                                     = "PALLET_NO";
                grid_PL_Col_PALLET_NO.VisibleIndex                                  = 1;
                grid_PL_Col_PALLET_NO.Width                                         = 70;
                grid_PL_Col_PALLET_NO.OptionsColumn.AllowEdit                       = false;
                grid_PL_Col_PALLET_NO.OptionsColumn.AllowMerge                      = DefaultBoolean.False;
                grid_PL_Col_PALLET_NO.AppearanceCell.Options.UseTextOptions         = true;
                grid_PL_Col_PALLET_NO.AppearanceCell.TextOptions.HAlignment         = HorzAlignment.Center;

                // CUSTOMER ITEM CODE
                grid_PL_Col_CUS_ITEM_CODE.Name                                      = "grid_PL_Col_CUS_ITEM_CODE";
                grid_PL_Col_CUS_ITEM_CODE.Caption                                   = "CUSTOMER ITEM CODE";
                grid_PL_Col_CUS_ITEM_CODE.FieldName                                 = "CUS_ITEM_CODE";
                grid_PL_Col_CUS_ITEM_CODE.VisibleIndex                              = 2;
                grid_PL_Col_CUS_ITEM_CODE.Width                                     = 130;
                grid_PL_Col_CUS_ITEM_CODE.OptionsColumn.AllowEdit                   = false;
                grid_PL_Col_CUS_ITEM_CODE.OptionsColumn.AllowMerge                  = DefaultBoolean.False;
                grid_PL_Col_CUS_ITEM_CODE.AppearanceCell.Options.UseTextOptions     = true;
                grid_PL_Col_CUS_ITEM_CODE.AppearanceCell.TextOptions.HAlignment     = HorzAlignment.Center;

                // TVC ITEM CODE 
                grid_PL_Col_TVC_ITEM_CODE.Name                                      = "grid_PL_Col_TVC_ITEM_CODE";
                grid_PL_Col_TVC_ITEM_CODE.Caption                                   = "TVC ITEM CODE";
                grid_PL_Col_TVC_ITEM_CODE.FieldName                                 = "TVC_ITEM_CODE";
                grid_PL_Col_TVC_ITEM_CODE.VisibleIndex                              = 3;
                grid_PL_Col_TVC_ITEM_CODE.Width                                     = 130;
                grid_PL_Col_TVC_ITEM_CODE.OptionsColumn.AllowEdit                   = false;
                grid_PL_Col_TVC_ITEM_CODE.OptionsColumn.AllowMerge                  = DefaultBoolean.False;
                grid_PL_Col_TVC_ITEM_CODE.AppearanceCell.Options.UseTextOptions     = true;
                grid_PL_Col_TVC_ITEM_CODE.AppearanceCell.TextOptions.HAlignment     = HorzAlignment.Center;

                // SERIES
                grid_PL_Col_SERIES.Name                                             = "grid_PL_Col_SERIES";
                grid_PL_Col_SERIES.Caption                                          = "SERIES";
                grid_PL_Col_SERIES.FieldName                                        = "SERIES";
                grid_PL_Col_SERIES.VisibleIndex                                     = 4;
                grid_PL_Col_SERIES.Width                                            = 150;
                grid_PL_Col_SERIES.OptionsColumn.AllowEdit                          = false;
                grid_PL_Col_SERIES.AppearanceCell.Options.UseTextOptions            = true;
                grid_PL_Col_SERIES.AppearanceCell.TextOptions.HAlignment            = HorzAlignment.Center;

                // CUSTOMER PO
                grid_PL_Col_CUSTOMER_PO.Name                                        = "grid_PL_Col_CUSTOMER_PO";
                grid_PL_Col_CUSTOMER_PO.Caption                                     = "CUSTOMER PO";
                grid_PL_Col_CUSTOMER_PO.FieldName                                   = "CUSTOMER_PO";
                grid_PL_Col_CUSTOMER_PO.VisibleIndex                                = 5;
                grid_PL_Col_CUSTOMER_PO.Width                                       = 100;
                grid_PL_Col_CUSTOMER_PO.OptionsColumn.AllowEdit                     = false;
                grid_PL_Col_CUSTOMER_PO.OptionsColumn.AllowMerge                    = DefaultBoolean.False;
                grid_PL_Col_CUSTOMER_PO.AppearanceCell.Options.UseTextOptions       = true;
                grid_PL_Col_CUSTOMER_PO.AppearanceCell.TextOptions.HAlignment       = HorzAlignment.Center;

                // QTY CARTON
                grid_PL_Col_QTY_CARTON.Name                                         = "grid_PL_Col_QTY_CARTON";
                grid_PL_Col_QTY_CARTON.Caption                                      = "QTY CARTON";
                grid_PL_Col_QTY_CARTON.FieldName                                    = "QTY_CARTON";
                grid_PL_Col_QTY_CARTON.VisibleIndex                                 = 6;
                grid_PL_Col_QTY_CARTON.Width                                        = 100;
                grid_PL_Col_QTY_CARTON.OptionsColumn.AllowEdit                      = false;
                grid_PL_Col_QTY_CARTON.OptionsColumn.AllowMerge                     = DefaultBoolean.False;
                grid_PL_Col_QTY_CARTON.AppearanceCell.Options.UseTextOptions        = true;
                grid_PL_Col_QTY_CARTON.AppearanceCell.TextOptions.HAlignment        = HorzAlignment.Far;
                grid_PL_Col_QTY_CARTON.DisplayFormat.FormatString                   = "#,##0";
                grid_PL_Col_QTY_CARTON.DisplayFormat.FormatType                     = FormatType.Numeric;
                // QTY CARTON SUM
                grid_PL_Col_QTY_CARTON.SummaryItem.SummaryType                      = SummaryItemType.Sum;
                grid_PL_Col_QTY_CARTON.SummaryItem.DisplayFormat                    = "{0:#,##0.####}";

                // QTY PER CARTON
                grid_PL_Col_QTY_PER_CARTON.Name                                     = "grid_PL_Col_QTY_PER_CARTON";
                grid_PL_Col_QTY_PER_CARTON.Caption                                  = "QTY PER CARTON";
                grid_PL_Col_QTY_PER_CARTON.FieldName                                = "QTY_PER_CARTON";
                grid_PL_Col_QTY_PER_CARTON.VisibleIndex                             = 7;
                grid_PL_Col_QTY_PER_CARTON.Width                                    = 100;
                grid_PL_Col_QTY_PER_CARTON.AppearanceCell.TextOptions.HAlignment    = HorzAlignment.Far;
                grid_PL_Col_QTY_PER_CARTON.AppearanceCell.Options.UseTextOptions    = true;
                grid_PL_Col_QTY_PER_CARTON.AppearanceCell.Options.UseBackColor      = true;
                grid_PL_Col_QTY_PER_CARTON.AppearanceCell.Options.UseForeColor      = true;
                grid_PL_Col_QTY_PER_CARTON.AppearanceCell.ForeColor                 = Color.Black;
                grid_PL_Col_QTY_PER_CARTON.AppearanceCell.BackColor                 = ColorTranslator.FromHtml("#FFFFC0");
                grid_PL_Col_QTY_PER_CARTON.OptionsColumn.AllowMerge                 = DefaultBoolean.False;
                grid_PL_Col_QTY_PER_CARTON.DisplayFormat.FormatString               = "#,##0";
                grid_PL_Col_QTY_PER_CARTON.DisplayFormat.FormatType                 = FormatType.Numeric;

                // QTY TOTAL
                grid_PL_Col_QTY_TOTAL.Name                                          = "grid_PL_Col_QTY_TOTAL";
                grid_PL_Col_QTY_TOTAL.Caption                                       = "QTY TOTAL";
                grid_PL_Col_QTY_TOTAL.FieldName                                     = "QTY_TOTAL";
                grid_PL_Col_QTY_TOTAL.VisibleIndex                                  = 8;
                grid_PL_Col_QTY_TOTAL.Width                                         = 100;
                grid_PL_Col_QTY_TOTAL.OptionsColumn.AllowEdit                       = false;
                grid_PL_Col_QTY_TOTAL.AppearanceCell.Options.UseTextOptions         = true;
                grid_PL_Col_QTY_TOTAL.AppearanceCell.TextOptions.HAlignment         = HorzAlignment.Far;
                grid_PL_Col_QTY_TOTAL.OptionsColumn.AllowMerge                      = DefaultBoolean.False;
                grid_PL_Col_QTY_TOTAL.DisplayFormat.FormatString                    = "#,##0";
                grid_PL_Col_QTY_TOTAL.DisplayFormat.FormatType                      = FormatType.Numeric;
                // QTY TOTAL SUM
                grid_PL_Col_QTY_TOTAL.SummaryItem.SummaryType                       = SummaryItemType.Sum;
                grid_PL_Col_QTY_TOTAL.SummaryItem.DisplayFormat                     = "{0:#,##0.####}";

                // LOT NO
                grid_PL_Col_LOT_NO.Name                                             = "grid_PL_Col_LOT_NO";
                grid_PL_Col_LOT_NO.Caption                                          = "LOT NO";
                grid_PL_Col_LOT_NO.FieldName                                        = "LOT_NO";
                grid_PL_Col_LOT_NO.VisibleIndex                                     = 9;
                grid_PL_Col_LOT_NO.Width                                            = 140;
                grid_PL_Col_LOT_NO.AppearanceCell.Options.UseTextOptions            = true;
                grid_PL_Col_LOT_NO.AppearanceCell.Options.UseBackColor              = true;
                grid_PL_Col_LOT_NO.AppearanceCell.Options.UseForeColor              = true;
                grid_PL_Col_LOT_NO.OptionsColumn.AllowMerge                         = DefaultBoolean.False;
                grid_PL_Col_LOT_NO.AppearanceCell.TextOptions.HAlignment            = HorzAlignment.Center;
                grid_PL_Col_LOT_NO.AppearanceCell.ForeColor                         = Color.Black;
                grid_PL_Col_LOT_NO.AppearanceCell.BackColor                         = ColorTranslator.FromHtml("#FFFFC0");

                // NET WEIGHT
                grid_PL_Col_NET_WEIGHT.Name                                         = "grid_PL_Col_NET_WEIGHT";
                grid_PL_Col_NET_WEIGHT.Caption                                      = "NET WEIGHT";
                grid_PL_Col_NET_WEIGHT.FieldName                                    = "NET_WEIGHT";
                grid_PL_Col_NET_WEIGHT.VisibleIndex                                 = 10;
                grid_PL_Col_NET_WEIGHT.Width                                        = 100;
                grid_PL_Col_NET_WEIGHT.AppearanceCell.Options.UseTextOptions        = true;
                grid_PL_Col_NET_WEIGHT.AppearanceCell.Options.UseBackColor          = true;
                grid_PL_Col_NET_WEIGHT.AppearanceCell.Options.UseForeColor          = true;
                grid_PL_Col_NET_WEIGHT.AppearanceCell.TextOptions.HAlignment        = HorzAlignment.Far;
                grid_PL_Col_NET_WEIGHT.AppearanceCell.ForeColor                     = Color.Black;
                grid_PL_Col_NET_WEIGHT.AppearanceCell.BackColor                     = ColorTranslator.FromHtml("#FFFFC0");
                grid_PL_Col_NET_WEIGHT.OptionsColumn.AllowMerge                     = DefaultBoolean.False;
                grid_PL_Col_NET_WEIGHT.DisplayFormat.FormatString                   = "#,##0.###";
                grid_PL_Col_NET_WEIGHT.DisplayFormat.FormatType                     = FormatType.Numeric;

                // NET WEIGHT TOTAL
                grid_PL_Col_NET_WEIGHT_TOTAL.Name                                   = "grid_PL_Col_NET_WEIGHT_TOTAL";
                grid_PL_Col_NET_WEIGHT_TOTAL.Caption                                = "NET WEIGHT TOTAL";
                grid_PL_Col_NET_WEIGHT_TOTAL.FieldName                              = "NET_WEIGHT_TOTAL";
                grid_PL_Col_NET_WEIGHT_TOTAL.VisibleIndex                           = 11;
                grid_PL_Col_NET_WEIGHT_TOTAL.Width                                  = 100;
                grid_PL_Col_NET_WEIGHT_TOTAL.AppearanceCell.ForeColor               = Color.Black;
                grid_PL_Col_NET_WEIGHT_TOTAL.AppearanceCell.BackColor               = ColorTranslator.FromHtml("#FFFFC0");
                grid_PL_Col_NET_WEIGHT_TOTAL.AppearanceCell.TextOptions.HAlignment  = HorzAlignment.Far;
                grid_PL_Col_NET_WEIGHT_TOTAL.AppearanceCell.Options.UseTextOptions  = true;
                grid_PL_Col_NET_WEIGHT_TOTAL.AppearanceCell.Options.UseBackColor    = true;
                grid_PL_Col_NET_WEIGHT_TOTAL.AppearanceCell.Options.UseForeColor    = true;
                grid_PL_Col_NET_WEIGHT_TOTAL.OptionsColumn.AllowMerge               = DefaultBoolean.False;
                grid_PL_Col_NET_WEIGHT_TOTAL.DisplayFormat.FormatString             = "#,##0.###";
                grid_PL_Col_NET_WEIGHT_TOTAL.DisplayFormat.FormatType               = FormatType.Numeric;
                // NET WEIGHT TOTAL SUM
                grid_PL_Col_NET_WEIGHT_TOTAL.SummaryItem.SummaryType                = SummaryItemType.Sum;
                grid_PL_Col_NET_WEIGHT_TOTAL.SummaryItem.DisplayFormat              = "{0:#,##0.####}";

                // GROSS WEIGHT
                grid_PL_Col_GROSS_WEIGHT.Name                                       = "grid_PL_Col_GROSS_WEIGHT";
                grid_PL_Col_GROSS_WEIGHT.Caption                                    = "GROSS WEIGHT";
                grid_PL_Col_GROSS_WEIGHT.FieldName                                  = "GROSS_WEIGHT";
                grid_PL_Col_GROSS_WEIGHT.VisibleIndex                               = 12;
                grid_PL_Col_GROSS_WEIGHT.Width                                      = 120;
                grid_PL_Col_GROSS_WEIGHT.AppearanceCell.ForeColor                   = Color.Black;
                grid_PL_Col_GROSS_WEIGHT.AppearanceCell.BackColor                   = ColorTranslator.FromHtml("#FFFFC0");
                grid_PL_Col_GROSS_WEIGHT.AppearanceCell.Options.UseTextOptions      = true;
                grid_PL_Col_GROSS_WEIGHT.AppearanceCell.Options.UseBackColor        = true;
                grid_PL_Col_GROSS_WEIGHT.AppearanceCell.Options.UseForeColor        = true;
                grid_PL_Col_GROSS_WEIGHT.AppearanceCell.TextOptions.HAlignment      = HorzAlignment.Far;
                grid_PL_Col_GROSS_WEIGHT.OptionsColumn.AllowMerge                   = DefaultBoolean.False;
                grid_PL_Col_GROSS_WEIGHT.DisplayFormat.FormatString                 = "#,##0.###";
                grid_PL_Col_GROSS_WEIGHT.DisplayFormat.FormatType                   = FormatType.Numeric;
                // GROSS WEIGHT SUM
                grid_PL_Col_GROSS_WEIGHT.SummaryItem.SummaryType                    = SummaryItemType.Sum;
                grid_PL_Col_GROSS_WEIGHT.SummaryItem.DisplayFormat                  = "{0:#,##0.####}";



                // Add column to gridview
                gridView_Picking_List.Columns.Add(grid_PL_Col_DELETE_ROW);
                gridView_Picking_List.Columns.Add(grid_PL_Col_PICKING_LIST_NO);
                gridView_Picking_List.Columns.Add(grid_PL_Col_PALLET_NO);
                gridView_Picking_List.Columns.Add(grid_PL_Col_CUS_ITEM_CODE);
                gridView_Picking_List.Columns.Add(grid_PL_Col_TVC_ITEM_CODE);
                gridView_Picking_List.Columns.Add(grid_PL_Col_SERIES);
                gridView_Picking_List.Columns.Add(grid_PL_Col_CUSTOMER_PO);
                gridView_Picking_List.Columns.Add(grid_PL_Col_QTY_CARTON);
                gridView_Picking_List.Columns.Add(grid_PL_Col_QTY_PER_CARTON);
                gridView_Picking_List.Columns.Add(grid_PL_Col_QTY_TOTAL);
                gridView_Picking_List.Columns.Add(grid_PL_Col_NET_WEIGHT);
                gridView_Picking_List.Columns.Add(grid_PL_Col_NET_WEIGHT_TOTAL);
                gridView_Picking_List.Columns.Add(grid_PL_Col_GROSS_WEIGHT);
                gridView_Picking_List.Columns.Add(grid_PL_Col_LOT_NO);

                // Set common attribute
                foreach (GridColumn c in gridView_Picking_List.Columns)
                {
                    c.AppearanceHeader.Options.UseFont          = true;
                    c.AppearanceHeader.Options.UseForeColor     = true;
                    c.AppearanceHeader.Options.UseTextOptions   = true;
                    c.AppearanceHeader.Font                     = new Font("Segoe UI", 8.25F, FontStyle.Bold, GraphicsUnit.Point, ((byte)(0)));
                    c.AppearanceHeader.ForeColor                = Color.Black;
                    c.AppearanceHeader.TextOptions.HAlignment   = HorzAlignment.Center;
                    c.AppearanceHeader.TextOptions.WordWrap     = WordWrap.Wrap;
                }
            }
            else if (gridViewControl.Name == gridView_Picking_List_Merge.Name)
            {
                gridView_Picking_List_Merge.OptionsPrint.AutoWidth                          = false;
                gridView_Picking_List_Merge.OptionsView.ColumnAutoWidth                     = false;
                gridView_Picking_List_Merge.OptionsView.ColumnHeaderAutoHeight              = DefaultBoolean.True;
                gridView_Picking_List_Merge.OptionsView.AllowCellMerge                      = true;
                gridView_Picking_List_Merge.OptionsView.ShowFooter                          = true;
                gridView_Picking_List_Merge.CustomDrawRowIndicator                          += gridView_Picking_List_Merge_CustomDrawRowIndicator;

                // PICKING_LIST_NO
                grid_PL_Merge_Col_PICKING_LIST_NO.Name                                      = "grid_PL_Merge_Col_PICKING_LIST_NO";
                grid_PL_Merge_Col_PICKING_LIST_NO.Caption                                   = "PICKING LIST NO";
                grid_PL_Merge_Col_PICKING_LIST_NO.FieldName                                 = "PICKING_LIST_NO";
                grid_PL_Merge_Col_PICKING_LIST_NO.VisibleIndex                              = 0;
                grid_PL_Merge_Col_PICKING_LIST_NO.Visible                                   = false;
                grid_PL_Merge_Col_PICKING_LIST_NO.Width                                     = 120;
                grid_PL_Merge_Col_PICKING_LIST_NO.OptionsColumn.AllowEdit                   = false;
                grid_PL_Merge_Col_PICKING_LIST_NO.OptionsColumn.AllowMerge                  = DefaultBoolean.False;
                grid_PL_Merge_Col_PICKING_LIST_NO.AppearanceCell.Options.UseTextOptions     = true;
                grid_PL_Merge_Col_PICKING_LIST_NO.AppearanceCell.TextOptions.HAlignment     = HorzAlignment.Center;

                // PALLET_NO
                grid_PL_Merge_Col_PALLET_NO.Name                                            = "grid_PL_Merge_Col_PALLET_NO";
                grid_PL_Merge_Col_PALLET_NO.Caption                                         = "PALLET NO";
                grid_PL_Merge_Col_PALLET_NO.FieldName                                       = "PALLET_NO";
                grid_PL_Merge_Col_PALLET_NO.VisibleIndex                                    = 1;
                grid_PL_Merge_Col_PALLET_NO.Width                                           = 70;
                grid_PL_Merge_Col_PALLET_NO.OptionsColumn.AllowEdit                         = false;
                grid_PL_Merge_Col_PALLET_NO.OptionsColumn.AllowMerge                        = DefaultBoolean.False;
                grid_PL_Merge_Col_PALLET_NO.AppearanceCell.Options.UseTextOptions           = true;
                grid_PL_Merge_Col_PALLET_NO.AppearanceCell.TextOptions.HAlignment           = HorzAlignment.Center;

                // CUSTOMER ITEM CODE
                grid_PL_Merge_Col_CUS_ITEM_CODE.Name                                        = "grid_PL_Merge_Col_CUS_ITEM_CODE";
                grid_PL_Merge_Col_CUS_ITEM_CODE.Caption                                     = "CUSTOMER ITEM CODE";
                grid_PL_Merge_Col_CUS_ITEM_CODE.FieldName                                   = "CUS_ITEM_CODE";
                grid_PL_Merge_Col_CUS_ITEM_CODE.VisibleIndex                                = 2;
                grid_PL_Merge_Col_CUS_ITEM_CODE.Width                                       = 130;
                grid_PL_Merge_Col_CUS_ITEM_CODE.OptionsColumn.AllowEdit                     = false;
                grid_PL_Merge_Col_CUS_ITEM_CODE.OptionsColumn.AllowMerge                    = DefaultBoolean.False;
                grid_PL_Merge_Col_CUS_ITEM_CODE.AppearanceCell.Options.UseTextOptions       = true;
                grid_PL_Merge_Col_CUS_ITEM_CODE.AppearanceCell.TextOptions.HAlignment       = HorzAlignment.Center;

                // TVC ITEM CODE
                grid_PL_Merge_Col_TVC_ITEM_CODE.Name                                        = "grid_PL_Merge_Col_TVC_ITEM_CODE";
                grid_PL_Merge_Col_TVC_ITEM_CODE.Caption                                     = "TVC ITEM CODE";
                grid_PL_Merge_Col_TVC_ITEM_CODE.FieldName                                   = "TVC_ITEM_CODE";
                grid_PL_Merge_Col_TVC_ITEM_CODE.VisibleIndex                                = 3;
                grid_PL_Merge_Col_TVC_ITEM_CODE.Width                                       = 130;
                grid_PL_Merge_Col_TVC_ITEM_CODE.OptionsColumn.AllowEdit                     = false;
                grid_PL_Merge_Col_TVC_ITEM_CODE.OptionsColumn.AllowMerge                    = DefaultBoolean.False;
                grid_PL_Merge_Col_TVC_ITEM_CODE.AppearanceCell.Options.UseTextOptions       = true;
                grid_PL_Merge_Col_TVC_ITEM_CODE.AppearanceCell.TextOptions.HAlignment       = HorzAlignment.Center;

                // QTY CARTON
                grid_PL_Merge_Col_QTY_CARTON.Name                                           = "grid_PL_Merge_Col_QTY_CARTON";
                grid_PL_Merge_Col_QTY_CARTON.Caption                                        = "Q'TY OF BOX";
                grid_PL_Merge_Col_QTY_CARTON.FieldName                                      = "QTY_CARTON";
                grid_PL_Merge_Col_QTY_CARTON.VisibleIndex                                   = 4;
                grid_PL_Merge_Col_QTY_CARTON.Width                                          = 100;
                grid_PL_Merge_Col_QTY_CARTON.OptionsColumn.AllowEdit                        = false;
                grid_PL_Merge_Col_QTY_CARTON.OptionsColumn.AllowMerge                       = DefaultBoolean.False;
                grid_PL_Merge_Col_QTY_CARTON.AppearanceCell.Options.UseTextOptions          = true;
                grid_PL_Merge_Col_QTY_CARTON.AppearanceCell.TextOptions.HAlignment          = HorzAlignment.Far;
                grid_PL_Merge_Col_QTY_CARTON.DisplayFormat.FormatString                     = "#,##0";
                grid_PL_Merge_Col_QTY_CARTON.DisplayFormat.FormatType                       = FormatType.Numeric;
                // QTY CARTON SUM
                grid_PL_Merge_Col_QTY_CARTON.SummaryItem.SummaryType                        = SummaryItemType.Sum;
                grid_PL_Merge_Col_QTY_CARTON.SummaryItem.DisplayFormat                      = "{0:#,##0.####}";

                // QTY PER CARTONs
                grid_PL_Merge_Col_QTY_PER_CARTON.Name                                       = "grid_PL_Merge_Col_QTY_PER_CARTON";
                grid_PL_Merge_Col_QTY_PER_CARTON.Caption                                    = "QTY/BOX\n(PCS)";
                grid_PL_Merge_Col_QTY_PER_CARTON.FieldName                                  = "QTY_PER_CARTON";
                grid_PL_Merge_Col_QTY_PER_CARTON.VisibleIndex                               = 5;
                grid_PL_Merge_Col_QTY_PER_CARTON.Width                                      = 100;
                grid_PL_Merge_Col_QTY_PER_CARTON.AppearanceCell.TextOptions.HAlignment      = HorzAlignment.Far;
                grid_PL_Merge_Col_QTY_PER_CARTON.AppearanceCell.Options.UseTextOptions      = true;
                grid_PL_Merge_Col_QTY_PER_CARTON.AppearanceCell.Options.UseBackColor        = true;
                grid_PL_Merge_Col_QTY_PER_CARTON.AppearanceCell.Options.UseForeColor        = true;
                grid_PL_Merge_Col_QTY_PER_CARTON.AppearanceCell.ForeColor                   = Color.Black;
                grid_PL_Merge_Col_QTY_PER_CARTON.AppearanceCell.BackColor                   = ColorTranslator.FromHtml("#FFFFC0");
                grid_PL_Merge_Col_QTY_PER_CARTON.OptionsColumn.AllowMerge                   = DefaultBoolean.False;
                grid_PL_Merge_Col_QTY_PER_CARTON.DisplayFormat.FormatString                 = "#,##0";
                grid_PL_Merge_Col_QTY_PER_CARTON.DisplayFormat.FormatType                   = FormatType.Numeric;

                // QTY TOTAL
                grid_PL_Merge_Col_QTY_TOTAL.Name                                            = "grid_PL_Merge_Col_QTY_TOTAL";
                grid_PL_Merge_Col_QTY_TOTAL.Caption                                         = "Q'TY TOTAL\n(PCS)";
                grid_PL_Merge_Col_QTY_TOTAL.FieldName                                       = "QTY_TOTAL";
                grid_PL_Merge_Col_QTY_TOTAL.VisibleIndex                                    = 6;
                grid_PL_Merge_Col_QTY_TOTAL.Width                                           = 100;
                grid_PL_Merge_Col_QTY_TOTAL.OptionsColumn.AllowEdit                         = false;
                grid_PL_Merge_Col_QTY_TOTAL.AppearanceCell.Options.UseTextOptions           = true;
                grid_PL_Merge_Col_QTY_TOTAL.AppearanceCell.TextOptions.HAlignment           = HorzAlignment.Far;
                grid_PL_Merge_Col_QTY_TOTAL.OptionsColumn.AllowMerge                        = DefaultBoolean.False;
                grid_PL_Merge_Col_QTY_TOTAL.DisplayFormat.FormatString                      = "#,##0";
                grid_PL_Merge_Col_QTY_TOTAL.DisplayFormat.FormatType                        = FormatType.Numeric;
                // QTY TOTAL SUM
                grid_PL_Merge_Col_QTY_TOTAL.SummaryItem.SummaryType                         = SummaryItemType.Sum;
                grid_PL_Merge_Col_QTY_TOTAL.SummaryItem.DisplayFormat                       = "{0:#,##0.####}";

                // NET WEIGHT TOTAL
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.Name                                     = "grid_PL_Merge_Col_NET_WEIGHT_TOTAL";
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.Caption                                  = "N/W(KG)";
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.FieldName                                = "NET_WEIGHT_TOTAL";
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.VisibleIndex                             = 7;
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.Width                                    = 100;
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.AppearanceCell.ForeColor                 = Color.Black;
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.AppearanceCell.BackColor                 = ColorTranslator.FromHtml("#FFFFC0");
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.AppearanceCell.TextOptions.HAlignment    = HorzAlignment.Far;
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.AppearanceCell.Options.UseTextOptions    = true;
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.AppearanceCell.Options.UseBackColor      = true;
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.AppearanceCell.Options.UseForeColor      = true;
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.OptionsColumn.AllowMerge                 = DefaultBoolean.False;
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.DisplayFormat.FormatString               = "#,##0.###";
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.DisplayFormat.FormatType                 = FormatType.Numeric;
                // NET WEIGHT TOTAL SUM
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.SummaryItem.SummaryType                  = SummaryItemType.Sum;
                grid_PL_Merge_Col_NET_WEIGHT_TOTAL.SummaryItem.DisplayFormat                = "{0:#,##0.####}";

                // GROSS WEIGHT TOTAL
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.Name                                   = "grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL";
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.Caption                                = "G/W(KG)";
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.FieldName                              = "GROSS_WEIGHT";
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.VisibleIndex                           = 8;
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.Width                                  = 120;
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.AppearanceCell.ForeColor               = Color.Black;
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.AppearanceCell.BackColor               = ColorTranslator.FromHtml("#FFFFC0");
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.AppearanceCell.Options.UseTextOptions  = true;
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.AppearanceCell.Options.UseBackColor    = true;
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.AppearanceCell.Options.UseForeColor    = true;
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.AppearanceCell.TextOptions.HAlignment  = HorzAlignment.Far;
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.OptionsColumn.AllowMerge               = DefaultBoolean.False;
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.DisplayFormat.FormatString             = "#,##0.###";
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.DisplayFormat.FormatType               = FormatType.Numeric;
                // GROSS WEIGHT SUM
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.SummaryItem.SummaryType                = SummaryItemType.Sum;
                grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL.SummaryItem.DisplayFormat              = "{0:#,##0.####}";

                // LOT NO
                grid_PL_Merge_Col_LOT_NO.Name                                               = "grid_PL_Merge_Col_LOT_NO";
                grid_PL_Merge_Col_LOT_NO.Caption                                            = "SERIAL NO・NOTE";
                grid_PL_Merge_Col_LOT_NO.FieldName                                          = "LOT_NO";
                grid_PL_Merge_Col_LOT_NO.VisibleIndex                                       = 9;
                grid_PL_Merge_Col_LOT_NO.Width                                              = 140;
                grid_PL_Merge_Col_LOT_NO.AppearanceCell.Options.UseTextOptions              = true;
                grid_PL_Merge_Col_LOT_NO.AppearanceCell.Options.UseBackColor                = true;
                grid_PL_Merge_Col_LOT_NO.AppearanceCell.Options.UseForeColor                = true;
                grid_PL_Merge_Col_LOT_NO.OptionsColumn.AllowMerge                           = DefaultBoolean.False;
                grid_PL_Merge_Col_LOT_NO.AppearanceCell.TextOptions.HAlignment              = HorzAlignment.Center;
                grid_PL_Merge_Col_LOT_NO.AppearanceCell.ForeColor                           = Color.Black;
                grid_PL_Merge_Col_LOT_NO.AppearanceCell.BackColor                           = ColorTranslator.FromHtml("#FFFFC0");

                // Add column to gridview
                gridView_Picking_List_Merge.Columns.Add(grid_PL_Merge_Col_PICKING_LIST_NO);
                gridView_Picking_List_Merge.Columns.Add(grid_PL_Merge_Col_PALLET_NO);
                gridView_Picking_List_Merge.Columns.Add(grid_PL_Merge_Col_CUS_ITEM_CODE);
                gridView_Picking_List_Merge.Columns.Add(grid_PL_Merge_Col_TVC_ITEM_CODE);
                gridView_Picking_List_Merge.Columns.Add(grid_PL_Merge_Col_QTY_CARTON);
                gridView_Picking_List_Merge.Columns.Add(grid_PL_Merge_Col_QTY_PER_CARTON);
                gridView_Picking_List_Merge.Columns.Add(grid_PL_Merge_Col_QTY_TOTAL);
                gridView_Picking_List_Merge.Columns.Add(grid_PL_Merge_Col_NET_WEIGHT_TOTAL);
                gridView_Picking_List_Merge.Columns.Add(grid_PL_Merge_Col_GROSS_WEIGHT_TOTAL);
                gridView_Picking_List_Merge.Columns.Add(grid_PL_Merge_Col_LOT_NO);

                // Set common attribute
                foreach (GridColumn c in gridView_Picking_List_Merge.Columns)
                {
                    c.AppearanceHeader.Options.UseFont = true;
                    c.AppearanceHeader.Options.UseForeColor = true;
                    c.AppearanceHeader.Options.UseTextOptions = true;
                    c.AppearanceHeader.Font = new Font("Segoe UI", 8.25F, FontStyle.Bold, GraphicsUnit.Point, ((byte)(0)));
                    c.AppearanceHeader.ForeColor = Color.Black;
                    c.AppearanceHeader.TextOptions.HAlignment = HorzAlignment.Center;
                    c.AppearanceHeader.TextOptions.WordWrap = WordWrap.Wrap;
                }
            }
            else if (gridViewControl.Name == gridView_Data_Compare.Name)
            {
                gridView_Data_Compare.OptionsPrint.AutoWidth                                    = false;
                gridView_Data_Compare.OptionsView.ColumnAutoWidth                               = false;
                gridView_Data_Compare.OptionsView.ColumnHeaderAutoHeight                        = DefaultBoolean.True;
                gridView_Data_Compare.OptionsView.ShowFooter                                    = true;
                gridView_Data_Compare.CustomDrawRowIndicator                                    += gridView_Data_Compare_CustomDrawRowIndicator;

                // CUSTOMER ITEM CODE
                grid_Data_Compare_Col_CUSTOMER_ITEM_CODE.Name                                   = "grid_Data_Compare_Col_CUSTOMER_ITEM_CODE";
                grid_Data_Compare_Col_CUSTOMER_ITEM_CODE.Caption                                = "CUSTOMER\nITEM CODE";
                grid_Data_Compare_Col_CUSTOMER_ITEM_CODE.FieldName                              = "CUSTOMER_ITEM_CODE";
                grid_Data_Compare_Col_CUSTOMER_ITEM_CODE.VisibleIndex                           = 1;
                grid_Data_Compare_Col_CUSTOMER_ITEM_CODE.Width                                  = 140;
                grid_Data_Compare_Col_CUSTOMER_ITEM_CODE.AppearanceCell.Options.UseTextOptions  = true;
                grid_Data_Compare_Col_CUSTOMER_ITEM_CODE.AppearanceCell.TextOptions.HAlignment  = HorzAlignment.Center;

                // TVC ITEM CODE
                grid_Data_Compare_Col_TVC_ITEM_CODE.Name                                        = "grid_Data_Compare_Col_TVC_ITEM_CODE";
                grid_Data_Compare_Col_TVC_ITEM_CODE.Caption                                     = "TVC ITEM CODE";
                grid_Data_Compare_Col_TVC_ITEM_CODE.FieldName                                   = "TVC_ITEM_CODE";
                grid_Data_Compare_Col_TVC_ITEM_CODE.VisibleIndex                                = 2;
                grid_Data_Compare_Col_TVC_ITEM_CODE.Width                                       = 140;
                grid_Data_Compare_Col_TVC_ITEM_CODE.AppearanceCell.Options.UseTextOptions       = true;
                grid_Data_Compare_Col_TVC_ITEM_CODE.AppearanceCell.TextOptions.HAlignment       = HorzAlignment.Center;


                // QUANTITY
                grid_Data_Compare_Col_QUANTITY.Name                                             = "grid_Data_Compare_Col_QUANTITY";
                grid_Data_Compare_Col_QUANTITY.Caption                                          = "QUANTITY";
                grid_Data_Compare_Col_QUANTITY.FieldName                                        = "QUANTITY";
                grid_Data_Compare_Col_QUANTITY.VisibleIndex                                     = 5;
                grid_Data_Compare_Col_QUANTITY.Width                                            = 120;
                grid_Data_Compare_Col_QUANTITY.AppearanceCell.Options.UseTextOptions            = true;
                grid_Data_Compare_Col_QUANTITY.AppearanceCell.TextOptions.HAlignment            = HorzAlignment.Far;
                grid_Data_Compare_Col_QUANTITY.DisplayFormat.FormatString                       = "#,##0";
                grid_Data_Compare_Col_QUANTITY.DisplayFormat.FormatType                         = FormatType.Numeric;
                // QUANTITY SUM
                grid_Data_Compare_Col_QUANTITY.SummaryItem.SummaryType                          = SummaryItemType.Sum;
                grid_Data_Compare_Col_QUANTITY.SummaryItem.DisplayFormat                        = "{0:#,##0}";

                // Add column to gridview
                gridView_Data_Compare.Columns.Add(grid_Data_Compare_Col_CUSTOMER_ITEM_CODE);
                gridView_Data_Compare.Columns.Add(grid_Data_Compare_Col_TVC_ITEM_CODE);
                gridView_Data_Compare.Columns.Add(grid_Data_Compare_Col_QUANTITY);

                // Set common attribute
                foreach (GridColumn c in gridView_Data_Compare.Columns)
                {
                    c.AppearanceHeader.Options.UseFont          = true;
                    c.AppearanceHeader.Options.UseForeColor     = true;
                    c.AppearanceHeader.Options.UseTextOptions   = true;
                    c.AppearanceHeader.Font                     = new Font("Segoe UI", 8.25F, FontStyle.Bold, GraphicsUnit.Point, ((byte)(0)));
                    c.AppearanceHeader.ForeColor                = Color.Black;
                    c.AppearanceHeader.TextOptions.HAlignment   = HorzAlignment.Center;
                    c.AppearanceHeader.TextOptions.WordWrap     = WordWrap.Wrap;
                }
            }
            else if (gridViewControl.Name == gridView_Compare_With_Invoice.Name)
            {
                gridView_Compare_With_Invoice.IndicatorWidth                                    = 50;
                gridView_Compare_With_Invoice.OptionsPrint.AutoWidth                            = false;
                gridView_Compare_With_Invoice.OptionsView.ColumnAutoWidth                       = false;
                gridView_Compare_With_Invoice.OptionsView.ColumnHeaderAutoHeight                = DefaultBoolean.True;
                gridView_Compare_With_Invoice.OptionsView.RowAutoHeight                         = true;
                gridView_Compare_With_Invoice.CustomDrawCell                                    += gridView_Compare_CustomDrawCell;
                gridView_Compare_With_Invoice.CustomDrawRowIndicator                            += gridView_Compare_With_Invoice_CustomDrawRowIndicator;

                // CUSTOMER ITEM CODE 
                grid_Compare_Col_CUSTOMER_ITEM_CODE.Name                                        = "grid_Compare_Col_CUSTOMER_ITEM_CODE";
                grid_Compare_Col_CUSTOMER_ITEM_CODE.Caption                                     = "CUSTOMER\nITEM CODE";
                grid_Compare_Col_CUSTOMER_ITEM_CODE.FieldName                                   = "CUSTOMER_ITEM_CODE";
                grid_Compare_Col_CUSTOMER_ITEM_CODE.VisibleIndex                                = 0;
                grid_Compare_Col_CUSTOMER_ITEM_CODE.Width                                       = 130;
                grid_Compare_Col_CUSTOMER_ITEM_CODE.OptionsColumn.AllowEdit                     = false;
                grid_Compare_Col_CUSTOMER_ITEM_CODE.OptionsColumn.AllowMerge                    = DefaultBoolean.False;
                grid_Compare_Col_CUSTOMER_ITEM_CODE.AppearanceCell.Options.UseTextOptions       = true;
                grid_Compare_Col_CUSTOMER_ITEM_CODE.AppearanceCell.TextOptions.HAlignment       = HorzAlignment.Center;

                // TVC ITEM CODE 
                grid_Compare_Col_TVC_ITEM_CODE.Name                                             = "grid_Compare_Col_TVC_ITEM_CODE";
                grid_Compare_Col_TVC_ITEM_CODE.Caption                                          = "TVC\nITEM CODE";
                grid_Compare_Col_TVC_ITEM_CODE.FieldName                                        = "TVC_ITEM_CODE";
                grid_Compare_Col_TVC_ITEM_CODE.VisibleIndex                                     = 1;
                grid_Compare_Col_TVC_ITEM_CODE.Width                                            = 130;
                grid_Compare_Col_TVC_ITEM_CODE.OptionsColumn.AllowEdit                          = false;
                grid_Compare_Col_TVC_ITEM_CODE.OptionsColumn.AllowMerge                         = DefaultBoolean.False;
                grid_Compare_Col_TVC_ITEM_CODE.AppearanceCell.Options.UseTextOptions            = true;
                grid_Compare_Col_TVC_ITEM_CODE.AppearanceCell.TextOptions.HAlignment            = HorzAlignment.Center;

                // QUANTITY
                grid_Compare_Col_QUANTITY.Name                                                  = "grid_Compare_Col_QUANTITY";
                grid_Compare_Col_QUANTITY.Caption                                               = "QUANTITY\n(1)";
                grid_Compare_Col_QUANTITY.FieldName                                             = "QUANTITY";
                grid_Compare_Col_QUANTITY.VisibleIndex                                          = 2;
                grid_Compare_Col_QUANTITY.Width                                                 = 120;
                grid_Compare_Col_QUANTITY.OptionsColumn.AllowEdit                               = false;
                grid_Compare_Col_QUANTITY.AppearanceCell.Options.UseTextOptions                 = true;
                grid_Compare_Col_QUANTITY.AppearanceCell.TextOptions.HAlignment                 = HorzAlignment.Far;
                grid_Compare_Col_QUANTITY.OptionsColumn.AllowMerge                              = DefaultBoolean.False;
                grid_Compare_Col_QUANTITY.DisplayFormat.FormatString                            = "#,##0";
                grid_Compare_Col_QUANTITY.DisplayFormat.FormatType                              = FormatType.Numeric;

                // PL CUSTOMER ITEM CODE 
                grid_Compare_Col_PL_CUSTOMER_ITEM_CODE.Name                                     = "grid_Compare_Col_PL_CUSTOMER_ITEM_CODE";
                grid_Compare_Col_PL_CUSTOMER_ITEM_CODE.Caption                                  = "PICKING LIST\nCUSTOMER ITEM CODE";
                grid_Compare_Col_PL_CUSTOMER_ITEM_CODE.FieldName                                = "PL_CUSTOMER_ITEM_CODE";
                grid_Compare_Col_PL_CUSTOMER_ITEM_CODE.VisibleIndex                             = 3;
                grid_Compare_Col_PL_CUSTOMER_ITEM_CODE.Width                                    = 140;
                grid_Compare_Col_PL_CUSTOMER_ITEM_CODE.OptionsColumn.AllowEdit                  = false;
                grid_Compare_Col_PL_CUSTOMER_ITEM_CODE.OptionsColumn.AllowMerge                 = DefaultBoolean.False;
                grid_Compare_Col_PL_CUSTOMER_ITEM_CODE.AppearanceCell.Options.UseTextOptions    = true;
                grid_Compare_Col_PL_CUSTOMER_ITEM_CODE.AppearanceCell.TextOptions.HAlignment    = HorzAlignment.Center;

                // PL TVC ITEM CODE 
                grid_Compare_Col_PL_TVC_ITEM_CODE.Name                                          = "grid_Compare_Col_PL_TVC_ITEM_CODE";
                grid_Compare_Col_PL_TVC_ITEM_CODE.Caption                                       = "PICKING LIST\nTVC ITEM CODE";
                grid_Compare_Col_PL_TVC_ITEM_CODE.FieldName                                     = "PL_TVC_ITEM_CODE";
                grid_Compare_Col_PL_TVC_ITEM_CODE.VisibleIndex                                  = 4;
                grid_Compare_Col_PL_TVC_ITEM_CODE.Width                                         = 140;
                grid_Compare_Col_PL_TVC_ITEM_CODE.OptionsColumn.AllowEdit                       = false;
                grid_Compare_Col_PL_TVC_ITEM_CODE.OptionsColumn.AllowMerge                      = DefaultBoolean.False;
                grid_Compare_Col_PL_TVC_ITEM_CODE.AppearanceCell.Options.UseTextOptions         = true;
                grid_Compare_Col_PL_TVC_ITEM_CODE.AppearanceCell.TextOptions.HAlignment         = HorzAlignment.Center;

                // PL QTY TOTAL
                grid_Compare_Col_PL_QUANTITY.Name                                               = "grid_Compare_Col_PL_QUANTITY";
                grid_Compare_Col_PL_QUANTITY.Caption                                            = "PICKING LIST QUANTITY\n(2)";
                grid_Compare_Col_PL_QUANTITY.FieldName                                          = "PL_QUANTITY";
                grid_Compare_Col_PL_QUANTITY.VisibleIndex                                       = 5;
                grid_Compare_Col_PL_QUANTITY.Width                                              = 120;
                grid_Compare_Col_PL_QUANTITY.OptionsColumn.AllowEdit                            = false;
                grid_Compare_Col_PL_QUANTITY.AppearanceCell.Options.UseTextOptions              = true;
                grid_Compare_Col_PL_QUANTITY.AppearanceCell.TextOptions.HAlignment              = HorzAlignment.Far;
                grid_Compare_Col_PL_QUANTITY.OptionsColumn.AllowMerge                           = DefaultBoolean.False;
                grid_Compare_Col_PL_QUANTITY.DisplayFormat.FormatString                         = "#,##0";
                grid_Compare_Col_PL_QUANTITY.DisplayFormat.FormatType                           = FormatType.Numeric;

                // QUANTITY DIFFERENCE
                grid_Compare_Col_QUANTITY_DIFFERENCE.Name                                       = "grid_Compare_Col_QUANTITY_DIFFERENCE";
                grid_Compare_Col_QUANTITY_DIFFERENCE.Caption                                    = "QUANTITY DIFFERENCE\n(1) - (2)";
                grid_Compare_Col_QUANTITY_DIFFERENCE.FieldName                                  = "QUANTITY_DIFFERENCE";
                grid_Compare_Col_QUANTITY_DIFFERENCE.VisibleIndex                               = 6;
                grid_Compare_Col_QUANTITY_DIFFERENCE.Width                                      = 120;
                grid_Compare_Col_QUANTITY_DIFFERENCE.OptionsColumn.AllowEdit                    = false;
                grid_Compare_Col_QUANTITY_DIFFERENCE.AppearanceCell.Options.UseTextOptions      = true;
                grid_Compare_Col_QUANTITY_DIFFERENCE.AppearanceCell.TextOptions.HAlignment      = HorzAlignment.Far;
                grid_Compare_Col_QUANTITY_DIFFERENCE.OptionsColumn.AllowMerge                   = DefaultBoolean.False;
                grid_Compare_Col_QUANTITY_DIFFERENCE.DisplayFormat.FormatString                 = "#,##0";
                grid_Compare_Col_QUANTITY_DIFFERENCE.DisplayFormat.FormatType                   = FormatType.Numeric;

                // Add column to gridview
                gridView_Compare_With_Invoice.Columns.Add(grid_Compare_Col_TVC_ITEM_CODE);
                gridView_Compare_With_Invoice.Columns.Add(grid_Compare_Col_CUSTOMER_ITEM_CODE);
                gridView_Compare_With_Invoice.Columns.Add(grid_Compare_Col_QUANTITY);
                gridView_Compare_With_Invoice.Columns.Add(grid_Compare_Col_PL_CUSTOMER_ITEM_CODE);
                gridView_Compare_With_Invoice.Columns.Add(grid_Compare_Col_PL_TVC_ITEM_CODE);
                gridView_Compare_With_Invoice.Columns.Add(grid_Compare_Col_PL_QUANTITY);
                gridView_Compare_With_Invoice.Columns.Add(grid_Compare_Col_QUANTITY_DIFFERENCE);

                // Set common attribute
                foreach (GridColumn c in gridView_Compare_With_Invoice.Columns)
                {
                    c.AppearanceHeader.Options.UseFont          = true;
                    c.AppearanceHeader.Options.UseForeColor     = true;
                    c.AppearanceHeader.Options.UseTextOptions   = true;
                    c.AppearanceHeader.Font                     = new Font("Segoe UI", 8.25F, FontStyle.Bold, GraphicsUnit.Point, ((byte)(0)));
                    c.AppearanceHeader.ForeColor                = Color.Black;
                    c.AppearanceHeader.TextOptions.HAlignment   = HorzAlignment.Center;
                    c.AppearanceHeader.TextOptions.WordWrap     = WordWrap.Wrap;
                }
            }
            else if (gridViewControl.Name == gridView_sLookUp_PL_No.Name)
            {
                gridView_sLookUp_PL_No.OptionsPrint.AutoWidth                           = false;
                gridView_sLookUp_PL_No.OptionsView.ColumnAutoWidth                      = false;
                gridView_sLookUp_PL_No.OptionsView.ColumnHeaderAutoHeight               = DefaultBoolean.True;
                gridView_sLookUp_PL_No.OptionsView.RowAutoHeight                        = true;

                // CUSTOMER CODE
                grid_sLookUp_PL_CUSTOMER_CODE.Name                                      = "grid_sLookUp_PL_CUSTOMER_CODE";
                grid_sLookUp_PL_CUSTOMER_CODE.Caption                                   = "CUSTOMER CODE";
                grid_sLookUp_PL_CUSTOMER_CODE.FieldName                                 = "CUSTOMER_CODE";
                grid_sLookUp_PL_CUSTOMER_CODE.VisibleIndex                              = 0;
                grid_sLookUp_PL_CUSTOMER_CODE.Width                                     = 85;
                grid_sLookUp_PL_CUSTOMER_CODE.AppearanceCell.Options.UseTextOptions     = true;
                grid_sLookUp_PL_CUSTOMER_CODE.AppearanceCell.TextOptions.HAlignment     = HorzAlignment.Center;

                // PICKING LIST NO
                grid_sLookUp_PL_PICKING_LIST_NO.Name                                    = "grid_sLookUp_PL_PICKING_LIST_NO";
                grid_sLookUp_PL_PICKING_LIST_NO.Caption                                 = "PICKING LIST NO";
                grid_sLookUp_PL_PICKING_LIST_NO.FieldName                               = "PICKING_LIST_NO";
                grid_sLookUp_PL_PICKING_LIST_NO.VisibleIndex                            = 1;
                grid_sLookUp_PL_PICKING_LIST_NO.Width                                   = 140;
                grid_sLookUp_PL_PICKING_LIST_NO.AppearanceCell.Options.UseTextOptions   = true;
                grid_sLookUp_PL_PICKING_LIST_NO.AppearanceCell.TextOptions.HAlignment   = HorzAlignment.Center;

                // STATUS
                grid_sLookUp_PL_STATUS.Name                                             = "grid_sLookUp_PL_STATUS";
                grid_sLookUp_PL_STATUS.Caption                                          = "STATUS";
                grid_sLookUp_PL_STATUS.FieldName                                        = "STATUS";
                grid_sLookUp_PL_STATUS.VisibleIndex                                     = 2;
                grid_sLookUp_PL_STATUS.Width                                            = 80;
                grid_sLookUp_PL_STATUS.AppearanceCell.Options.UseTextOptions            = true;
                grid_sLookUp_PL_STATUS.AppearanceCell.TextOptions.HAlignment            = HorzAlignment.Center;

                // DELIVERY ADDRESS
                grid_sLookUp_PL_DELIVERY_ADDRESS.Name                                   = "grid_sLookUp_PL_DELIVERY_ADDRESS";
                grid_sLookUp_PL_DELIVERY_ADDRESS.Caption                                = "DELIVERY ADDRESS NAME";
                grid_sLookUp_PL_DELIVERY_ADDRESS.FieldName                              = "DELIVERY_ADDRESS_NAME";
                grid_sLookUp_PL_DELIVERY_ADDRESS.VisibleIndex                           = 3;
                grid_sLookUp_PL_DELIVERY_ADDRESS.Width                                  = 140;
                grid_sLookUp_PL_DELIVERY_ADDRESS.AppearanceCell.Options.UseTextOptions  = true;
                grid_sLookUp_PL_DELIVERY_ADDRESS.AppearanceCell.TextOptions.HAlignment  = HorzAlignment.Near;
                grid_sLookUp_PL_DELIVERY_ADDRESS.AppearanceCell.TextOptions.WordWrap    = WordWrap.Wrap;

                // EMPLOYEE CODE
                grid_sLookUp_PL_EMPLOYEE_CODE.Name                                      = "grid_sLookUp_PL_EMPLOYEE_CODE";
                grid_sLookUp_PL_EMPLOYEE_CODE.Caption                                   = "EMPLOYEE CODE";
                grid_sLookUp_PL_EMPLOYEE_CODE.FieldName                                 = "EMPLOYEE_CODE";
                grid_sLookUp_PL_EMPLOYEE_CODE.VisibleIndex                              = 4;
                grid_sLookUp_PL_EMPLOYEE_CODE.Width                                     = 100;
                grid_sLookUp_PL_EMPLOYEE_CODE.AppearanceCell.Options.UseTextOptions     = true;
                grid_sLookUp_PL_EMPLOYEE_CODE.AppearanceCell.TextOptions.HAlignment     = HorzAlignment.Center;

                // CREATE DATE
                grid_sLookUp_PL_CREATE_DATE.Name                                        = "grid_sLookUp_PL_CREATE_DATE";
                grid_sLookUp_PL_CREATE_DATE.Caption                                     = "CREATE DATE";
                grid_sLookUp_PL_CREATE_DATE.FieldName                                   = "CREATE_DATE";
                grid_sLookUp_PL_CREATE_DATE.VisibleIndex                                = 5;
                grid_sLookUp_PL_CREATE_DATE.Width                                       = 100;
                grid_sLookUp_PL_CREATE_DATE.DisplayFormat.FormatString                  = "dd/MM/yyyy";
                grid_sLookUp_PL_CREATE_DATE.DisplayFormat.FormatType                    = FormatType.DateTime;
                grid_sLookUp_PL_CREATE_DATE.AppearanceCell.Options.UseTextOptions       = true;
                grid_sLookUp_PL_CREATE_DATE.AppearanceCell.TextOptions.HAlignment       = HorzAlignment.Center;

                gridView_sLookUp_PL_No.Columns.Add(grid_sLookUp_PL_CUSTOMER_CODE);
                gridView_sLookUp_PL_No.Columns.Add(grid_sLookUp_PL_PICKING_LIST_NO);
                gridView_sLookUp_PL_No.Columns.Add(grid_sLookUp_PL_STATUS);
                gridView_sLookUp_PL_No.Columns.Add(grid_sLookUp_PL_DELIVERY_ADDRESS);
                gridView_sLookUp_PL_No.Columns.Add(grid_sLookUp_PL_EMPLOYEE_CODE);
                gridView_sLookUp_PL_No.Columns.Add(grid_sLookUp_PL_CREATE_DATE);

                // Set common attribute
                foreach (GridColumn c in gridView_sLookUp_PL_No.Columns)
                {
                    c.AppearanceHeader.Options.UseFont          = true;
                    c.AppearanceHeader.Options.UseFont          = true;
                    c.AppearanceHeader.Options.UseForeColor     = true;
                    c.AppearanceHeader.Options.UseTextOptions   = true;
                    c.AppearanceHeader.Options.UseTextOptions   = true;
                    c.AppearanceHeader.TextOptions.HAlignment   = HorzAlignment.Center;
                    c.AppearanceHeader.TextOptions.WordWrap     = WordWrap.Wrap;
                    c.AppearanceHeader.ForeColor                = Color.Black;
                    c.AppearanceHeader.Font                     = new Font("Segoe UI", 8.25F, FontStyle.Bold, GraphicsUnit.Point, ((byte)(0)));
                }

                gridView_sLookUp_PL_No.CustomColumnDisplayText += (sender, e) =>
                {
                    if (e.Column == grid_sLookUp_PL_STATUS && e.Value != null)
                    {
                        int value = Convert.ToInt32(e.Value);
                        if (value == 0)
                        {
                            e.DisplayText = "NORMAL";
                        }
                        else if (value == 1)
                        {
                            e.DisplayText = "LOCK";
                        }
                    }
                };
            }
        }

        private void gridView_Compare_CustomDrawCell(object sender, RowCellCustomDrawEventArgs e)
        {
            GridView gridView = sender as GridView;

            // Kiểm tra nếu cột đang vẽ là cột QUANTITY_DIFFERENCE
            if (e.Column.FieldName == "QUANTITY_DIFFERENCE")
            {
                // Lấy giá trị số lượng từ dòng hiện tại
                int quantity = Convert.ToInt32(gridView.GetRowCellValue(e.RowHandle, "QUANTITY_DIFFERENCE"));

                // Đặt màu nền tương ứng
                if (quantity > 0)
                {
                    e.Appearance.BackColor = Color.Red;
                    e.Appearance.ForeColor = Color.Yellow;
                } else if (quantity < 0)
                {
                    e.Appearance.BackColor = Color.Blue ;
                    e.Appearance.ForeColor = Color.Yellow;
                }
            }
        }

        private bool Check_Error_Before_Compare()
        {
            if(gridView_Data_Compare.RowCount == 0)
            {
                MessageBox.Show("Dữ liệu so sánh đang trống", "Cảnh báo", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                xMainTab.SelectedTabPageIndex = 1;
                return false;
            }

            if (gridView_Picking_List.RowCount == 0)
            {
                MessageBox.Show("Dữ liệu Picking List đang trống", "Cảnh báo", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                xMainTab.SelectedTabPageIndex = 0;
                return false;
            }

            return true;
        }

        private void barBtn_Compare_Data_Inv_PL_ItemClick(object sender, ItemClickEventArgs e)
        {
            if (Check_Error_Before_Compare())
            {
                sum_DataCompares =
                    list_DataCompare
                    .GroupBy(
                        i =>
                            new {
                                i.CUSTOMER_ITEM_CODE,
                                i.TVC_ITEM_CODE
                            }
                    )
                    .Select(
                        g =>
                            new Sum_DataCompare(
                                g.Key.CUSTOMER_ITEM_CODE,
                                g.Key.TVC_ITEM_CODE,
                                g.Sum(i => i.QUANTITY)
                            )
                    )
                    .OrderBy(
                        x => x.TVC_ITEM_CODE
                    )
                    .ToList();

                sum_PickingList =
                    list_Handy_Picking_Detail
                    .GroupBy(
                        i => 
                        new {
                            i.CUS_ITEM_CODE,
                            i.TVC_ITEM_CODE
                        }
                    )
                    .Select(
                        g =>
                            new Sum_PickingList(
                                g.Key.CUS_ITEM_CODE,
                                g.Key.TVC_ITEM_CODE,
                                g.Sum(i => i.QTY_TOTAL)
                            )
                    )
                    .OrderBy(x => x.PL_TVC_ITEM_CODE)
                    .ToList();

                // Compare with invoice
                if (!String.IsNullOrEmpty(list_DataCompare[0].CUSTOMER_ITEM_CODE))
                {
                    // Phần 1: Lấy những item với điều kiện CUSTOMER_ITEM_CODE = CUSTOMER_ITEM_CODE
                    var part1 = from
                                    dataCompares
                                    in sum_DataCompares
                                join picking
                                    in sum_PickingList
                                    on dataCompares.CUSTOMER_ITEM_CODE equals picking.PL_CUSTOMER_ITEM_CODE
                                select new Compare_DataImport_With_PickingList
                                (
                                    dataCompares.CUSTOMER_ITEM_CODE,
                                    dataCompares.TVC_ITEM_CODE,
                                    dataCompares.QUANTITY,
                                    picking.PL_CUSTOMER_ITEM_CODE,
                                    picking.PL_TVC_ITEM_CODE,
                                    picking.PL_QUANTITY,
                                    dataCompares.QUANTITY - picking.PL_QUANTITY
                                );

                    // Phần 2: Lấy những item với điều kiện TVC_ITEM_CODE không tồn tại trong Sum_PickingList
                    var part2 = from
                                    dataCompares
                                    in sum_DataCompares
                                where
                                    !sum_PickingList
                                    .Any(
                                        picking => picking.PL_CUSTOMER_ITEM_CODE == dataCompares.CUSTOMER_ITEM_CODE
                                    )
                                select new Compare_DataImport_With_PickingList
                                (
                                    dataCompares.CUSTOMER_ITEM_CODE,
                                    dataCompares.TVC_ITEM_CODE,
                                    dataCompares.QUANTITY,
                                    "",
                                    "",
                                    0,
                                    dataCompares.QUANTITY
                                );

                    // Phần 3: Lấy những item với điều kiện PL_TVC_ITEM_CODE không tồn tại trong Sum_PickingList
                    var part3 = from
                                    picking
                                    in sum_PickingList
                                where
                                    !sum_DataCompares
                                    .Any(
                                        invoice =>
                                            invoice.CUSTOMER_ITEM_CODE == picking.PL_CUSTOMER_ITEM_CODE
                                    )
                                select new Compare_DataImport_With_PickingList
                                (
                                    "",
                                    "",
                                    0,
                                    picking.PL_CUSTOMER_ITEM_CODE,
                                    picking.PL_TVC_ITEM_CODE,
                                    picking.PL_QUANTITY,
                                    0 - picking.PL_QUANTITY
                                );

                    // Kết hợp 3 phần lại với nhau bằng phép Union
                    compare_Invoice_PickingList = part1.Union(part2).Union(part3).ToList();
                }
                // Compare with picking list D365
                else if (!String.IsNullOrEmpty(list_DataCompare[0].TVC_ITEM_CODE))
                {
                    // Phần 1: Lấy những item với điều kiện TVC_ITEM_CODE = PL_TVC_ITEM_CODE
                    var part1 = 
                        from
                            dataCompares
                            in sum_DataCompares
                        join picking
                            in sum_PickingList
                            on dataCompares.TVC_ITEM_CODE equals picking.PL_TVC_ITEM_CODE
                        select new Compare_DataImport_With_PickingList
                        (
                            dataCompares.CUSTOMER_ITEM_CODE,
                            dataCompares.TVC_ITEM_CODE,
                            dataCompares.QUANTITY,
                            picking.PL_CUSTOMER_ITEM_CODE,
                            picking.PL_TVC_ITEM_CODE,
                            picking.PL_QUANTITY,
                            dataCompares.QUANTITY - picking.PL_QUANTITY
                        );

                    // Phần 2: Lấy những item với điều kiện TVC_ITEM_CODE không tồn tại trong Sum_PickingList
                    var part2 = 
                        from
                            dataCompares
                        in  sum_DataCompares
                        where
                            !sum_PickingList
                            .Any(
                                picking => picking.PL_TVC_ITEM_CODE == dataCompares.TVC_ITEM_CODE
                            )
                        select new Compare_DataImport_With_PickingList
                        (
                            dataCompares.CUSTOMER_ITEM_CODE,
                            dataCompares.TVC_ITEM_CODE,
                            dataCompares.QUANTITY,
                            "",
                            "",
                            0,
                            dataCompares.QUANTITY
                        );

                    // Phần 3: Lấy những item với điều kiện PL_TVC_ITEM_CODE không tồn tại trong Sum_PickingList
                    var part3 = 
                        from
                            picking
                        in  sum_PickingList
                        where
                            !sum_DataCompares
                            .Any(
                                invoice =>
                                    invoice.TVC_ITEM_CODE == picking.PL_TVC_ITEM_CODE
                            )
                        select new Compare_DataImport_With_PickingList
                        (
                            "",
                            "",
                            0,
                            picking.PL_CUSTOMER_ITEM_CODE,
                            picking.PL_TVC_ITEM_CODE,
                            picking.PL_QUANTITY,
                            0 - picking.PL_QUANTITY
                        );

                    // Kết hợp 3 phần lại với nhau bằng phép Union
                    compare_Invoice_PickingList = part1.Union(part2).Union(part3).ToList();
                } 

                xMainTab.SelectedTabPageIndex   = 2;
                gridControl_CompareWithInvoice.DataSource  = compare_Invoice_PickingList;
            }
        }

        private void barBtn_Import_Invoice_ItemClick(object sender, ItemClickEventArgs e)
        {
            //Get link file excel
            OpenFileDialog theDialog = new OpenFileDialog
            {
                Title   = "Chọn file dữ liệu so sánh cần import",
                Filter  = "Files Excel|*.xls;*.xlsx"
            };
            if (theDialog.ShowDialog() == DialogResult.OK)
            {
                // Create a new Excel data source.
                ExcelDataSource excelDataSource = new ExcelDataSource   
                {
                    FileName = theDialog.FileName
                };

                // Select a required worksheet.
                ExcelWorksheetSettings excelWorksheetSettings = new ExcelWorksheetSettings
                {
                    WorksheetIndex = 0
                };

                // Specify import settings.
                ExcelSourceOptions excelSourceOptions = new ExcelSourceOptions
                {
                    ImportSettings      = excelWorksheetSettings,
                    SkipHiddenRows      = true,
                    SkipHiddenColumns   = true,
                    SkipEmptyRows       = true,
                    UseFirstRowAsHeader = true
                };
                excelDataSource.SourceOptions = excelSourceOptions;

                excelDataSource.Fill();

                DataTable result = excelDataSource.ExcelToDataTable();

                for (int i = 0; i < result.Rows.Count; i++)
                {
                    // Transform the data as needed
                    object[] values = result.Rows[i].ItemArray;

                    string CUSTOMER_ITEM_CODE   = values[0].ToString();
                    string TVC_ITEM_CODE        = values[1].ToString();
                    int quantity                = Convert.ToInt32(Convert.ToString(values[2]).Replace(",", ""));

                    Data_Compare dataCompare = 
                        new Data_Compare(
                            CUSTOMER_ITEM_CODE,
                            TVC_ITEM_CODE,
                            quantity
                        );

                    list_DataCompare.Add(dataCompare);
                }

                gridControl_Data_Compare.DataSource = list_DataCompare;
                xMainTab.SelectedTabPageIndex       = 1;
            }
        }
            
        private void barBtn_Add_PickingList_ItemClick(object sender, ItemClickEventArgs e)
        {
            Add_PickingList();
        }

        private void gridView_Picking_List_CustomDrawRowIndicator(object sender, RowIndicatorCustomDrawEventArgs e)
        {
            if (e.RowHandle >= 0)
            {     
                e.Info.DisplayText = (e.RowHandle + 1).ToString();
                e.Appearance.TextOptions.HAlignment = HorzAlignment.Center;
            }
        }

        private void gridView_Picking_List_Merge_CustomDrawRowIndicator(object sender, RowIndicatorCustomDrawEventArgs e)
        {
            if (e.RowHandle >= 0)
            {
                e.Info.DisplayText = (e.RowHandle + 1).ToString();
                e.Appearance.TextOptions.HAlignment = HorzAlignment.Center;
            }
        }

        private void gridView_Data_Compare_CustomDrawRowIndicator(object sender, RowIndicatorCustomDrawEventArgs e)
        {
            if (e.RowHandle >= 0)
            {
                e.Info.DisplayText = (e.RowHandle + 1).ToString();
                e.Appearance.TextOptions.HAlignment = HorzAlignment.Center;
            }
        }

        private void gridView_Compare_With_Invoice_CustomDrawRowIndicator(object sender, RowIndicatorCustomDrawEventArgs e)
        {
            if (e.RowHandle >= 0)
            {
                e.Info.DisplayText = (e.RowHandle + 1).ToString();
                e.Appearance.TextOptions.HAlignment = HorzAlignment.Center;
            }
        }

        private void barBtnLoadPackagingStandards_ItemClick(object sender, ItemClickEventArgs e)
        {
            using(HANDY_PICKING_Entities db = new HANDY_PICKING_Entities())
            {
                List<HANDY_PICKING_DETAIL> resultList = 
                    (
                        from o 
                            in list_Handy_Picking_Detail
                        join t 
                            in db.PRODUCTMFs
                        on  o.TVC_ITEM_CODE equals t.ITEM_CODE into joinedData
                        from j
                            in joinedData.DefaultIfEmpty()
                        select new HANDY_PICKING_DETAIL
                        {
                            PICKING_LIST_NO     = o.PICKING_LIST_NO,
                            INVOICE_NO          = o.INVOICE_NO,
                            SALE_ORDER          = o.SALE_ORDER,
                            ITEM_CODE           = o.ITEM_CODE,
                            LOT_ID              = o.LOT_ID,
                            QUANTITY            = o.QUANTITY,
                            PALLET_NO           = o.PALLET_NO,
                            SERIES              = o.SERIES,
                            CUS_ITEM_CODE       = o.CUS_ITEM_CODE,
                            TVC_ITEM_CODE       = o.TVC_ITEM_CODE,
                            CUSTOMER_PO         = o.CUSTOMER_PO,
                            QTY_CARTON          = j != null ? (int)Math.Ceiling((decimal)((decimal)o.QTY_TOTAL / (decimal)j.BOX_QUANTITY)) : 0,
                            QTY_PER_CARTON      = j != null ? Convert.ToInt32(j.BOX_QUANTITY) : 0,
                            QTY_TOTAL           = o.QTY_TOTAL,
                            NET_WEIGHT          = (decimal)(j != null ? j.WEIGHT : 0),
                            NET_WEIGHT_TOTAL    = (decimal)(j != null ? o.QTY_TOTAL * j.WEIGHT : 0),
                            COLUMN1             = o.COLUMN1,
                            COLUMN2             = o.COLUMN2,
                            COLUMN3             = o.COLUMN3,
                            COLUMN4             = o.COLUMN4,
                            COLUMN5             = o.COLUMN5
                        }
                    ).ToList();

                list_Handy_Picking_Detail           = resultList;
                gridControl_Picking_List.DataSource = list_Handy_Picking_Detail;
                xMainTab.SelectedTabPageIndex       = 0;
            }    
        }

        private void barBtn_Clear_PL_Data_ItemClick(object sender, ItemClickEventArgs e)
        {
            list_Handy_Picking_Detail.Clear();
            sLookUp_PickingList.EditValue       = "";
            gridControl_Picking_List.DataSource = null;
            xMainTab.SelectedTabPageIndex       = 0;
        }

        private void barBtn_Inv_Clear_Data_ItemClick(object sender, ItemClickEventArgs e)
        {
            list_DataCompare.Clear();
            gridControl_Data_Compare.DataSource = null;
        }

        private void btnSavePicking_ItemClick(object sender, ItemClickEventArgs e)
        {
            if (MessageBox.Show($"Bạn muốn cập nhật dữ liệu picking list ?", "Xác Nhận"
                , MessageBoxButtons.YesNo
                , MessageBoxIcon.Question
                , MessageBoxDefaultButton.Button1) == DialogResult.Yes)
            {
                using(HANDY_PICKING_Entities db = new HANDY_PICKING_Entities())
                {
                    foreach(HANDY_PICKING_DETAIL item in list_Handy_Picking_Detail_Delete)
                    {
                        // Kiểm tra xem đối tượng có tồn tại trong cơ sở dữ liệu hay không
                        HANDY_PICKING_DETAIL existingItem = 
                            db.HANDY_PICKING_DETAIL
                            .Where(
                                x=>(
                                    x.SERIES.Equals(item.SERIES)
                                &&  x.PICKING_LIST_NO.Equals(item.PICKING_LIST_NO)
                                )
                            )
                            .FirstOrDefault();

                        if (existingItem != null)
                        {
                            // Đính kèm đối tượng vào context nếu nó chưa được đính kèm
                            db.HANDY_PICKING_DETAIL.Attach(existingItem);
                            // Xóa đối tượng
                            db.HANDY_PICKING_DETAIL.Remove(existingItem);
                        }
                    }
                    
                    foreach(HANDY_PICKING_DETAIL item in list_Handy_Picking_Detail)
                    {
                        var result = 
                            db
                            .HANDY_PICKING_DETAIL
                            .FirstOrDefault(
                                x => 
                                    (x.PICKING_LIST_NO.Equals(item.PICKING_LIST_NO)) 
                                 && (x.SERIES.Equals(item.SERIES))
                            );

                        if (result != null)
                        {
                            result.QTY_CARTON       = item.QTY_CARTON;
                            result.QTY_PER_CARTON   = item.QTY_PER_CARTON;
                            result.NET_WEIGHT       = item.NET_WEIGHT;
                            result.NET_WEIGHT_TOTAL = item.NET_WEIGHT_TOTAL;
                            result.GROSS_WEIGHT     = item.GROSS_WEIGHT;
                            result.LOT_NO           = item.LOT_NO;
                            result.EDIT_BY          = userMS.USERNAME;
                            result.EDIT_DATE        = DateTime.Now;
                        }

                        try
                        {
                            db.SaveChanges();  
                        }
                        catch (DbUpdateException ex)
                        {
                            // Xử lý lỗi do cơ sở dữ liệu (ví dụ: khóa chính trùng lặp, ràng buộc, ...)
                            MessageBox.Show("Lỗi cơ sở dữ liệu: " + ex.Message, "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        }
                        catch (Exception ex)
                        {
                            MessageBox.Show($"Lỗi: {ex.GetType()}.\nLỗi: {ex}", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        }
                    }

                    MessageBox.Show($"Cập nhật dữ liệu picking list thành công", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }    
            }
        }

        private void barBtn_ExportPL_ItemClick(object sender, ItemClickEventArgs e)
        {
            if (MessageBox.Show($"Xuất dữ liệu picking list merge đang hiển thị trên lưới?", "Xác Nhận" 
                , MessageBoxButtons.YesNo, MessageBoxIcon.Question
                , MessageBoxDefaultButton.Button1) == DialogResult.Yes)
            {
                SaveFileDialog saveDialog = new SaveFileDialog
                {
                    Title       = "Save Report Data",
                    FileName    = "Picking_List_Merge" + DateTime.Now.ToString("ddMMyyyy_hhmmss"),
                    Filter      = "Files Excel|*.xlsx;*.xls"
                };
                if (saveDialog.ShowDialog() == DialogResult.OK)
                {
                    string path = saveDialog.FileName;
                    gridControl_Picking_List_Merge.ExportToXlsx(path);

                    // Open the created XLSX file with the default application.
                    Process.Start(path);
                }
            }
        }

        private void gridView_Picking_List_CellMerge(object sender, DevExpress.XtraGrid.Views.Grid.CellMergeEventArgs e)
        {
            GridView view = sender as GridView;
            try
            {
                if ((e.Column.FieldName == grid_PL_Col_PALLET_NO.Name))
                {
                    object value1 = view.GetRowCellValue(e.RowHandle1, e.Column);
                    object value2 = view.GetRowCellValue(e.RowHandle2, e.Column);

                    e.Merge = (value1 == value2) || (value1 == null || value2 == null);
                    e.Handled = true;
                    return;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Lỗi: {ex.Message}", "Lỗi", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void barBtnLockPickingList_ItemClick(object sender, ItemClickEventArgs e)
        {
            if (MessageBox.Show($"Bạn muốn khóa/ mở khóa picking list {Convert.ToString(sLookUp_PickingList.EditValue)}?", "Xác Nhận"
                , MessageBoxButtons.YesNo, MessageBoxIcon.Question
                , MessageBoxDefaultButton.Button1) == DialogResult.Yes)
            {
                using (HANDY_PICKING_Entities db = new HANDY_PICKING_Entities())
                {
                    String pickingListNo = Convert.ToString(sLookUp_PickingList.EditValue);
                    String message = "";

                    if(!String.IsNullOrEmpty(pickingListNo))
                    {
                        HANDY_PICKING_MS handyPickingMS =
                            db.HANDY_PICKING_MS.Where(x => x.PICKING_LIST_NO.Equals(pickingListNo)).FirstOrDefault();

                        if(handyPickingMS != null)
                        {
                            if(handyPickingMS.STATUS == 0)
                            {
                                handyPickingMS.STATUS = 1;
                                message = $"Khóa picking list {handyPickingMS.PICKING_LIST_NO} thành công";                            
                            } else if (handyPickingMS.STATUS == 1)
                            {
                                handyPickingMS.STATUS = 0;
                                message = $"Mở khóa picking list {handyPickingMS.PICKING_LIST_NO} thành công";
                            }
                        }

                        try
                        {
                            db.SaveChanges();

                            Setting_Data_Item_SLookUp(sLookUp_PickingList);
                        }
                        catch (DbUpdateException ex)
                        {
                            // Xử lý lỗi do cơ sở dữ liệu (ví dụ: khóa chính trùng lặp, ràng buộc, ...)
                            MessageBox.Show("Lỗi cơ sở dữ liệu: " + ex.Message, "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        }
                        catch (Exception ex)
                        {
                            MessageBox.Show($"Lỗi: {ex.GetType()}.\nLỗi: {ex}", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Error); // What is the real exception?
                        }

                        MessageBox.Show(message, "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }    
                }
            }
        }

        private void gridView_SumInvoice_CustomDrawRowIndicator(object sender, RowIndicatorCustomDrawEventArgs e)
        {
            if (e.RowHandle >= 0)
            {
                e.Info.DisplayText                  = (e.RowHandle + 1).ToString();
                e.Appearance.TextOptions.HAlignment = HorzAlignment.Center;
            }
        }

        private void gridView_SumPickingList_CustomDrawRowIndicator(object sender, RowIndicatorCustomDrawEventArgs e)
        {
            if (e.RowHandle >= 0)
            {
                e.Info.DisplayText                  = (e.RowHandle + 1).ToString();
                e.Appearance.TextOptions.HAlignment = HorzAlignment.Center;
            }
        }

        private void grid_PL_repo_btn_DeleteRow_Click(object sender, EventArgs e)
        {
            string TVC_ItemCode = Convert.ToString(gridView_Picking_List.GetRowCellValue(gridView_Picking_List.FocusedRowHandle, "TVC_ITEM_CODE"));
            string series       = Convert.ToString(gridView_Picking_List.GetRowCellValue(gridView_Picking_List.FocusedRowHandle, "SERIES"));

            if ((MessageBox.Show($"Xóa dữ liệu picking:\nItem: {TVC_ItemCode}.\nSeries: {series} ?", "Xác Nhận"
                , MessageBoxButtons.YesNo, MessageBoxIcon.Question
                , MessageBoxDefaultButton.Button1) == DialogResult.Yes))
            {
                using(HANDY_PICKING_Entities db = new HANDY_PICKING_Entities())
                {
                    HANDY_PICKING_DETAIL handyPickingDetail = list_Handy_Picking_Detail.Where(x=>x.SERIES.Equals(series)).FirstOrDefault();
                    list_Handy_Picking_Detail_Delete.Add(handyPickingDetail);
                    list_Handy_Picking_Detail.Remove(handyPickingDetail);

                    gridControl_Picking_List.DataSource = null;
                    gridControl_Picking_List.DataSource = list_Handy_Picking_Detail;
                }    
            }
        }

        private void Form_Main_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (e.CloseReason == CloseReason.UserClosing)
            {
                DialogResult dialogResult =
                    MessageBox.Show(
                        "Bạn có muốn thoát khỏi phần mềm so sánh Invoice và Picking List",
                        "Thông Báo",
                        MessageBoxButtons.OKCancel,
                        MessageBoxIcon.Information
                    );
                if (dialogResult == DialogResult.OK)
                {
                    Application.Exit();
                }
                else if (dialogResult == DialogResult.Cancel)
                {
                    e.Cancel = true;    // Stopping Form Close perocess.
                }
            }
        }

        private void sLookUp_PickingList_Properties_EditValueChanged(object sender, EventArgs e)
        {
            if(gridView_Picking_List.RowCount == 0)
            {
                Add_PickingList();
            }    
        }

        private void Add_PickingList()
        {
            string PL_No = Convert.ToString(sLookUp_PickingList.EditValue);

            if (!string.IsNullOrEmpty(PL_No))
            {
                list_Handy_Picking_Detail           = list_Handy_Picking_Detail.Concat(Get_Data_PickingList_Detail(PL_No)).ToList();
                gridControl_Picking_List.DataSource = list_Handy_Picking_Detail;
                xMainTab.SelectedTabPageIndex       = 0;
            }
        }

        private void btn_InputPacking_ItemClick(object sender, ItemClickEventArgs e)
        {
             
        }

        private void barBtn_Merge_Picking_List_ItemClick(object sender, ItemClickEventArgs e)
        {
            List<Picking_List_Merge> pickingListMerges = 
                list_Handy_Picking_Detail
                .GroupBy(p => 
                    new {
                        //p.PICKING_LIST_NO,
                        p.PALLET_NO,
                        p.CUS_ITEM_CODE,
                        p.TVC_ITEM_CODE,
                        p.QTY_TOTAL,
                        p.LOT_NO
                    }
                )
                .Select(g => 
                    new Picking_List_Merge
                    (
                        //g.Key.PICKING_LIST_NO,
                        g.Key.PALLET_NO,
                        g.Key.CUS_ITEM_CODE,
                        g.Key.TVC_ITEM_CODE,
                        g.Count(),
                        g.Max(p => p.QTY_TOTAL),
                        g.Sum(p => p.QTY_TOTAL),
                        g.Sum(p => p.NET_WEIGHT_TOTAL),
                        g.Sum(p => p.GROSS_WEIGHT),
                        g.Key.LOT_NO
                    )
                )
                .OrderBy(p => p.PALLET_NO)
                //.OrderBy(p => p.CUS_ITEM_CODE)
                .ToList();

            xMainTab.SelectedTabPageIndex               = 3;
            gridControl_Picking_List_Merge.DataSource   = pickingListMerges;
        }
    }
}   