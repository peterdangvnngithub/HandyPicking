using System;
using System.Data;
using System.Drawing;
using System.Windows.Forms;
using DevExpress.Utils;
using DevExpress.XtraBars;
using DevExpress.XtraEditors.Repository;
using DevExpress.XtraGrid.Columns;
using DevExpress.DataAccess.Excel;
using Handy_Picking_Winform;

namespace Time_Keeping
{
    public partial class Form_Main : DevExpress.XtraBars.Ribbon.RibbonForm
    {
        // PICKING LIST
        private readonly GridColumn grid_PL_Col_PALLET_NO           = new GridColumn();
        private readonly GridColumn grid_PL_Col_CUS_ITEM_CODE       = new GridColumn();
        private readonly GridColumn grid_PL_Col_TVC_ITEM_CODE       = new GridColumn();
        private readonly GridColumn grid_PL_Col_CUSTOMER_PO         = new GridColumn();
        private readonly GridColumn grid_PL_Col_QTY_CARTON          = new GridColumn();
        private readonly GridColumn grid_PL_Col_QTY_PER_CARTON      = new GridColumn();
        private readonly GridColumn grid_PL_Col_QTY_TOTAL           = new GridColumn();
        private readonly GridColumn grid_PL_Col_NET_WEIGHT          = new GridColumn();
        private readonly GridColumn grid_PL_Col_NET_WEIGHT_TOTAL    = new GridColumn();
        private readonly GridColumn grid_PL_Col_GROSS_WEIGHT        = new GridColumn();
        private readonly GridColumn grid_PL_Col_LOT_NO              = new GridColumn();

        // INVOICE
        private readonly GridColumn grid_Inv_Col_NO                 = new GridColumn();
        private readonly GridColumn grid_Inv_Col_PART_NAME          = new GridColumn();
        private readonly GridColumn grid_Inv_Col_PART_NO            = new GridColumn();
        private readonly GridColumn grid_Inv_Col_CUSTOMER_PO        = new GridColumn();
        private readonly GridColumn grid_Inv_Col_QUANTITY           = new GridColumn();
        private readonly GridColumn grid_Inv_Col_UNIT_PRICE         = new GridColumn();
        private readonly GridColumn grid_Inv_Col_AMOUNT             = new GridColumn();

        //List<CHECKINOUT_CONSOL> lMainData = new List<CHECKINOUT_CONSOL>();

        public Form_Main()
        {
            InitializeComponent();
        }

        private void Form_Main_Load(object sender, EventArgs e)
        {
            // Define gridview structure
            Define_GridView(gridView_Picking_List);
            Define_GridView(gridView_Invoice);

            Setting_Init();
        }

        //private List<CHECKINOUT_CONSOL> Get_Data_Attendance()
        //{
        //    DateTime dateFrom = Convert.ToDateTime(dateEdit_From.EditValue).AddHours(0).AddMinutes(0).AddSeconds(0);
        //    DateTime dateTo = Convert.ToDateTime(dateEdit_To.EditValue).AddHours(23).AddMinutes(59).AddSeconds(59);

        //    List<CHECKINOUT_CONSOL> list_checkInOut = new List<CHECKINOUT_CONSOL>();

        //    using (ATT_Entities db = new ATT_Entities())
        //    {
        //        string sqlQuery =
        //        @"WITH AVGS AS
        //        (
        //            SELECT
        //                     pin
        //                    ,CASE
        //                        WHEN RIGHT(reader_name, 2) = 'In'
        //                        THEN min(event_time)
        //                        WHEN RIGHT(reader_name, 2) = 'ut'
        //                        THEN max(event_time)
        //                        ELSE null
        //                    END									AS CHECKTIME
        //                    ,CASE
        //                        WHEN RIGHT(reader_name, 2) = 'In'
        //                        THEN 'I'
        //                        WHEN RIGHT(reader_name, 2) = 'ut'
        //                        THEN 'O'
        //                        ELSE null
        //                    END						            AS CHECKTYPE
                
        //            FROM
        //                    acc_transaction			            AS acc
        //            WHERE
        //                    event_time  BETWEEN @dateFrom AND @dateTo
        //            GROUP BY
        //                     pin
        //                    ,reader_name
        //                    ,CASE
        //                        WHEN RIGHT(reader_name, 2) = 'In'
        //                        THEN 'I'
        //                        WHEN RIGHT(reader_name, 2) = 'ut'
        //                        THEN 'O'
        //                        ELSE null
        //                    END
        //        )
        //        SELECT
        //                 CAST(userInfo.USERID  AS INT)      AS USERID
        //                ,AVGS.CHECKTIME
        //                ,AVGS.CHECKTYPE
        //                ,1						            AS VERIFYCODE
        //                ,'4'					            AS SENSORID
        //                ,0                                  AS WorkCode
        //                ,'Transfer'                         AS sn
        //                ,0                                  AS UserExtFmt
        //                ,0                                  AS ID
        //                ,'1'                                AS Status
        //                ,null                               AS ShiftID
        //                ,AVGS.CHECKTIME			            AS CreateDate
        //                ,null                               AS ChangeBy
        //        FROM
        //                AVGS
        //        JOIN	[Hafusa.takako.vn].[TakakoATT_TEST].[dbo].USERINFO_CONSOL userInfo
        //        ON		AVGS.pin     = userInfo.StaffID
        //        ORDER BY
        //                 CHECKTIME
        //                ,USERID";
        //        list_checkInOut = 
        //            db
        //            .Database
        //            .SqlQuery<CHECKINOUT_CONSOL>(sqlQuery, new SqlParameter("@dateFrom", dateFrom), new SqlParameter("@dateTo", dateTo))
        //            .ToList();
        //    }   
        //    return list_checkInOut;
        //}

        private void Setting_Init()
        {
            //dateEdit_From.EditValue = new DateTime(DateTime.Now.Year, DateTime.Now.Month, 1);
            //dateEdit_To.EditValue = new DateTime(DateTime.Now.Year, DateTime.Now.Month + 1, 1).AddDays(-1);

            Get_Data();
        }

        private void Get_Data()
        {
            //lMainData = Get_Data_Attendance();

            //gridControl_Transfer_Attendance.DataSource = lMainData;
        }

        private void Define_GridView(DevExpress.XtraGrid.Views.Grid.GridView gridViewControl)
        {
            if (gridViewControl.Name == gridView_Picking_List.Name)
            {
                gridView_Picking_List.OptionsPrint.AutoWidth = false;
                gridView_Picking_List.OptionsView.ColumnAutoWidth = false;

                // PALLET_NO
                grid_PL_Col_PALLET_NO.Name = "grid_PL_Col_PALLET_NO";
                grid_PL_Col_PALLET_NO.Caption = "PALLET_NO";
                grid_PL_Col_PALLET_NO.FieldName = "PALLET_NO";
                grid_PL_Col_PALLET_NO.ColumnEdit = new RepositoryItemDateEdit();
                grid_PL_Col_PALLET_NO.VisibleIndex = 0;
                grid_PL_Col_PALLET_NO.Width = 70;
                grid_PL_Col_PALLET_NO.AppearanceCell.Options.UseTextOptions = true;
                grid_PL_Col_PALLET_NO.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // CUSTOMER ITEM CODE
                grid_PL_Col_CUS_ITEM_CODE.Name = "grid_PL_Col_CUS_ITEM_CODE";
                grid_PL_Col_CUS_ITEM_CODE.Caption = "CUS_ITEM_CODE";
                grid_PL_Col_CUS_ITEM_CODE.FieldName = "CUS_ITEM_CODE";
                grid_PL_Col_CUS_ITEM_CODE.VisibleIndex = 1;
                grid_PL_Col_CUS_ITEM_CODE.Width = 130;
                grid_PL_Col_CUS_ITEM_CODE.AppearanceCell.Options.UseTextOptions = true;
                grid_PL_Col_CUS_ITEM_CODE.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // TVC ITEM CODE
                grid_PL_Col_TVC_ITEM_CODE.Name = "grid_PL_Col_TVC_ITEM_CODE";
                grid_PL_Col_TVC_ITEM_CODE.Caption = "TVC_ITEM_CODE";
                grid_PL_Col_TVC_ITEM_CODE.FieldName = "TVC_ITEM_CODE";
                grid_PL_Col_TVC_ITEM_CODE.VisibleIndex = 2;
                grid_PL_Col_TVC_ITEM_CODE.Width = 130;
                grid_PL_Col_TVC_ITEM_CODE.AppearanceCell.Options.UseTextOptions = true;
                grid_PL_Col_TVC_ITEM_CODE.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // CUSTOMER PO
                grid_PL_Col_CUSTOMER_PO.Name = "grid_PL_Col_CUSTOMER_PO";
                grid_PL_Col_CUSTOMER_PO.Caption = "CUSTOMER_PO";
                grid_PL_Col_CUSTOMER_PO.FieldName = "CUSTOMER_PO";
                grid_PL_Col_CUSTOMER_PO.VisibleIndex = 3;
                grid_PL_Col_CUSTOMER_PO.Width = 100;
                grid_PL_Col_CUSTOMER_PO.AppearanceCell.Options.UseTextOptions = true;
                grid_PL_Col_CUSTOMER_PO.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // QTY CARTON
                grid_PL_Col_QTY_CARTON.Name = "grid_PL_Col_QTY_CARTON";
                grid_PL_Col_QTY_CARTON.Caption = "QTY_CARTON";
                grid_PL_Col_QTY_CARTON.FieldName = "QTY_CARTON";
                grid_PL_Col_QTY_CARTON.VisibleIndex = 4;
                grid_PL_Col_QTY_CARTON.Width = 100;
                grid_PL_Col_QTY_CARTON.AppearanceCell.Options.UseTextOptions = true;
                grid_PL_Col_QTY_CARTON.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Far;
                grid_PL_Col_QTY_CARTON.DisplayFormat.FormatType = FormatType.Numeric;

                // QTY PER CARTON
                grid_PL_Col_QTY_PER_CARTON.Name = "grid_PL_Col_QTY_PER_CARTON";
                grid_PL_Col_QTY_PER_CARTON.Caption = "QTY_PER_CARTON";
                grid_PL_Col_QTY_PER_CARTON.FieldName = "QTY_PER_CARTON";
                grid_PL_Col_QTY_PER_CARTON.VisibleIndex = 5;
                grid_PL_Col_QTY_PER_CARTON.Width = 120;
                grid_PL_Col_QTY_PER_CARTON.AppearanceCell.Options.UseTextOptions = true;
                grid_PL_Col_QTY_PER_CARTON.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Far;
                grid_PL_Col_QTY_PER_CARTON.DisplayFormat.FormatType = FormatType.Numeric;

                // QTY TOTAL
                grid_PL_Col_QTY_TOTAL.Name = "grid_PL_Col_QTY_TOTAL";
                grid_PL_Col_QTY_TOTAL.Caption = "QTY_TOTAL";
                grid_PL_Col_QTY_TOTAL.FieldName = "QTY_TOTAL";
                grid_PL_Col_QTY_TOTAL.VisibleIndex = 6;
                grid_PL_Col_QTY_TOTAL.Width = 120;
                grid_PL_Col_QTY_TOTAL.AppearanceCell.Options.UseTextOptions = true;
                grid_PL_Col_QTY_TOTAL.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;
                grid_PL_Col_QTY_TOTAL.DisplayFormat.FormatType = FormatType.Numeric;

                // NET WEIGHT
                grid_PL_Col_NET_WEIGHT.Name = "grid_PL_Col_NET_WEIGHT";
                grid_PL_Col_NET_WEIGHT.Caption = "NET_WEIGHT";
                grid_PL_Col_NET_WEIGHT.FieldName = "NET_WEIGHT";
                grid_PL_Col_NET_WEIGHT.VisibleIndex = 6;
                grid_PL_Col_NET_WEIGHT.Width = 120;
                grid_PL_Col_NET_WEIGHT.AppearanceCell.Options.UseTextOptions = true;
                grid_PL_Col_NET_WEIGHT.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;
                grid_PL_Col_NET_WEIGHT.DisplayFormat.FormatType = FormatType.Numeric;

                // NET WEIGHT TOTAL
                grid_PL_Col_NET_WEIGHT_TOTAL.Name = "grid_PL_Col_NET_WEIGHT_TOTAL";
                grid_PL_Col_NET_WEIGHT_TOTAL.Caption = "NET_WEIGHT_TOTAL";
                grid_PL_Col_NET_WEIGHT_TOTAL.FieldName = "NET_WEIGHT_TOTAL";
                grid_PL_Col_NET_WEIGHT_TOTAL.VisibleIndex = 6;
                grid_PL_Col_NET_WEIGHT_TOTAL.Width = 120;
                grid_PL_Col_NET_WEIGHT_TOTAL.AppearanceCell.Options.UseTextOptions = true;
                grid_PL_Col_NET_WEIGHT_TOTAL.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;
                grid_PL_Col_NET_WEIGHT_TOTAL.DisplayFormat.FormatType = FormatType.Numeric;

                // GROSS WEIGHT
                grid_PL_Col_GROSS_WEIGHT.Name = "grid_PL_Col_GROSS_WEIGHT";
                grid_PL_Col_GROSS_WEIGHT.Caption = "GROSS_WEIGHT";
                grid_PL_Col_GROSS_WEIGHT.FieldName = "GROSS_WEIGHT";
                grid_PL_Col_GROSS_WEIGHT.VisibleIndex = 6;
                grid_PL_Col_GROSS_WEIGHT.Width = 120;
                grid_PL_Col_GROSS_WEIGHT.AppearanceCell.Options.UseTextOptions = true;
                grid_PL_Col_GROSS_WEIGHT.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;
                grid_PL_Col_GROSS_WEIGHT.DisplayFormat.FormatType = FormatType.Numeric;

                // LOT NO
                grid_PL_Col_LOT_NO.Name = "grid_PL_Col_LOT_NO";
                grid_PL_Col_LOT_NO.Caption = "LOT_NO";
                grid_PL_Col_LOT_NO.FieldName = "LOT_NO";
                grid_PL_Col_LOT_NO.VisibleIndex = 10;
                grid_PL_Col_LOT_NO.Visible = false;
                grid_PL_Col_LOT_NO.Width = 140;
                grid_PL_Col_LOT_NO.AppearanceCell.Options.UseTextOptions = true;
                grid_PL_Col_LOT_NO.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // Add column to gridview
                gridView_Picking_List.Columns.Add(grid_PL_Col_PALLET_NO);
                gridView_Picking_List.Columns.Add(grid_PL_Col_CUS_ITEM_CODE);
                gridView_Picking_List.Columns.Add(grid_PL_Col_TVC_ITEM_CODE);
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
                    c.AppearanceHeader.Options.UseFont = true;
                    c.AppearanceHeader.Options.UseForeColor = true;
                    c.AppearanceHeader.Options.UseTextOptions = true;
                    c.AppearanceHeader.Font = new Font("Segoe UI", 8.25F, FontStyle.Bold, GraphicsUnit.Point, ((byte)(0)));
                    c.AppearanceHeader.ForeColor = Color.Black;
                    c.AppearanceHeader.TextOptions.HAlignment = HorzAlignment.Center;
                    c.AppearanceHeader.TextOptions.WordWrap = WordWrap.Wrap;
                }
            }
            else if (gridViewControl.Name == gridView_Invoice.Name)
            {
                gridView_Invoice.OptionsPrint.AutoWidth = false;
                gridView_Invoice.OptionsView.ColumnAutoWidth = false;

                // NO
                grid_Inv_Col_NO.Name = "grid_Inv_Col_NO";
                grid_Inv_Col_NO.Caption = "NO";
                grid_Inv_Col_NO.FieldName = "NO";
                grid_Inv_Col_NO.VisibleIndex = 1;
                grid_Inv_Col_NO.Width = 60;
                grid_Inv_Col_NO.AppearanceCell.Options.UseTextOptions = true;
                grid_Inv_Col_NO.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // PART NAME
                grid_Inv_Col_PART_NAME.Name = "grid_Inv_Col_PART_NAME";
                grid_Inv_Col_PART_NAME.Caption = "PART_NAME";
                grid_Inv_Col_PART_NAME.FieldName = "PART_NAME";
                grid_Inv_Col_PART_NAME.VisibleIndex = 2;
                grid_Inv_Col_PART_NAME.Width = 120;
                grid_Inv_Col_PART_NAME.AppearanceCell.Options.UseTextOptions = true;
                grid_Inv_Col_PART_NAME.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // PART NO
                grid_Inv_Col_PART_NO.Name = "grid_Inv_Col_PART_NO";
                grid_Inv_Col_PART_NO.Caption = "PART_NO";
                grid_Inv_Col_PART_NO.FieldName = "PART_NO";
                grid_Inv_Col_PART_NO.VisibleIndex = 3;
                grid_Inv_Col_PART_NO.Width = 120;
                grid_Inv_Col_PART_NO.AppearanceCell.Options.UseTextOptions = true;
                grid_Inv_Col_PART_NO.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // CUSTOMER_PO
                grid_Inv_Col_CUSTOMER_PO.Name = "grid_Inv_Col_CUSTOMER_PO";
                grid_Inv_Col_CUSTOMER_PO.Caption = "CUSTOMER_PO";
                grid_Inv_Col_CUSTOMER_PO.FieldName = "CUSTOMER_PO";
                grid_Inv_Col_CUSTOMER_PO.VisibleIndex = 4;
                grid_Inv_Col_CUSTOMER_PO.Width = 120;
                grid_Inv_Col_CUSTOMER_PO.AppearanceCell.Options.UseTextOptions = true;
                grid_Inv_Col_CUSTOMER_PO.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // QUANTITY
                grid_Inv_Col_QUANTITY.Name = "grid_Inv_Col_QUANTITY";
                grid_Inv_Col_QUANTITY.Caption = "QUANTITY";
                grid_Inv_Col_QUANTITY.FieldName = "QUANTITY";
                grid_Inv_Col_QUANTITY.VisibleIndex = 5;
                grid_Inv_Col_QUANTITY.Width = 100;
                grid_Inv_Col_QUANTITY.AppearanceCell.Options.UseTextOptions = true;
                grid_Inv_Col_QUANTITY.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Far;
                grid_Inv_Col_QUANTITY.DisplayFormat.FormatString = "#,##0";
                grid_Inv_Col_QUANTITY.DisplayFormat.FormatType = FormatType.Numeric;

                // UNIT PRICE
                grid_Inv_Col_UNIT_PRICE.Name = "grid_Inv_Col_UNIT_PRICE";
                grid_Inv_Col_UNIT_PRICE.Caption = "UNIT_PRICE";
                grid_Inv_Col_UNIT_PRICE.FieldName = "UNIT_PRICE";
                grid_Inv_Col_UNIT_PRICE.VisibleIndex = 6;
                grid_Inv_Col_UNIT_PRICE.Width = 100;
                grid_Inv_Col_UNIT_PRICE.AppearanceCell.Options.UseTextOptions = true;
                grid_Inv_Col_UNIT_PRICE.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Far;
                grid_Inv_Col_UNIT_PRICE.DisplayFormat.FormatString = "#,##0";
                grid_Inv_Col_UNIT_PRICE.DisplayFormat.FormatType = FormatType.Numeric;

                // AMOUNT
                grid_Inv_Col_AMOUNT.Name = "grid_Inv_Col_AMOUNT";
                grid_Inv_Col_AMOUNT.Caption = "AMOUNT";
                grid_Inv_Col_AMOUNT.FieldName = "AMOUNT";
                grid_Inv_Col_AMOUNT.VisibleIndex = 7;
                grid_Inv_Col_AMOUNT.Width = 120;
                grid_Inv_Col_AMOUNT.AppearanceCell.Options.UseTextOptions = true;
                grid_Inv_Col_AMOUNT.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Far;
                grid_Inv_Col_AMOUNT.DisplayFormat.FormatString = "#,##0";
                grid_Inv_Col_AMOUNT.DisplayFormat.FormatType = FormatType.Numeric;

                // Add column to gridview
                gridView_Invoice.Columns.Add(grid_Inv_Col_NO);
                gridView_Invoice.Columns.Add(grid_Inv_Col_PART_NAME);
                gridView_Invoice.Columns.Add(grid_Inv_Col_PART_NO);
                gridView_Invoice.Columns.Add(grid_Inv_Col_CUSTOMER_PO);
                gridView_Invoice.Columns.Add(grid_Inv_Col_QUANTITY);
                gridView_Invoice.Columns.Add(grid_Inv_Col_UNIT_PRICE);
                gridView_Invoice.Columns.Add(grid_Inv_Col_AMOUNT);

                // Set common attribute
                foreach (GridColumn c in gridView_Invoice.Columns)
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
        }

        private void barBtn_Search_ItemClick(object sender, ItemClickEventArgs e)
        {
            //int count = 0;

            //if ((MessageBox.Show($"Cập nhật dữ liệu chấm công từ ngày {Convert.ToDateTime(dateEdit_From.EditValue).AddHours(0).AddMinutes(0).AddSeconds(0)} đến ngày {Convert.ToDateTime(dateEdit_To.EditValue).AddHours(23).AddMinutes(59).AddSeconds(59)}?", "Xác Nhận"
            //        , MessageBoxButtons.YesNo, MessageBoxIcon.Question
            //        , MessageBoxDefaultButton.Button1) == System.Windows.Forms.DialogResult.Yes))
            //{
            //    using (TakakoAttendance_Entities db = new TakakoAttendance_Entities())
            //    {
            //        foreach (CHECKINOUT_CONSOL item in lMainData)
            //        {
            //            var result = db.CHECKINOUT_CONSOL.FirstOrDefault(g => (g.USERID == item.USERID) && (g.CHECKTIME == item.CHECKTIME));

            //            if (result == null)
            //            {
            //                db.CHECKINOUT_CONSOL.Add(item);
            //                count++;
            //            }
            //        }

            //        try
            //        {
            //            db.SaveChanges();

            //            //Setting_Init();

            //            MessageBox.Show($"Cập nhật dữ liệu chấm công thành công ({count} rows)", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Information);
            //        }
            //        catch (Exception ex)
            //        {
            //            MessageBox.Show($"Loại lỗi: {ex.GetType()}.\nLỗi: {ex}", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Error);
            //        }
            //    }
            //}
        }

        private bool Check_Error()
        {
            //DateTime dateEditFrom = Convert.ToDateTime(dateEdit_From.EditValue);
            //DateTime dateEditTo = Convert.ToDateTime(dateEdit_To.EditValue);

            //if(dateEditFrom > dateEditTo)
            //{
            //    MessageBox.Show("Ngày bắt đầu không được lớn hơn ngày kết thúc", "Cảnh báo", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            //    dateEdit_From.Focus();
            //    return false;
            //}

            return true;
        }

        private void barBtn_Get_Data_ItemClick(object sender, ItemClickEventArgs e)
        {
            gridControl_Invoice.Focus();

            if (Check_Error())
            {
                Get_Data();
            }
        }

        private void barBtn_Import_Invoice_ItemClick(object sender, ItemClickEventArgs e)
        {
            //Get link file excel
            OpenFileDialog theDialog = new OpenFileDialog
            {
                Title = "Chọn file dữ liệu invoice cần import",
                Filter = "Files Excel|*.xls;*.xlsx"
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
                    ImportSettings = excelWorksheetSettings,
                    SkipHiddenRows = true,
                    SkipHiddenColumns = true,
                    SkipEmptyRows = true,
                    UseFirstRowAsHeader = true
                };
                excelDataSource.SourceOptions = excelSourceOptions;

                excelDataSource.Fill();

                DataTable tableEmployee = new DataTable();
                tableEmployee.Columns.Add("NO",             typeof(string));
                tableEmployee.Columns.Add("PART_NAME",      typeof(string));
                tableEmployee.Columns.Add("PART_NO",        typeof(string));
                tableEmployee.Columns.Add("CUSTOMER_PO",    typeof(string));
                tableEmployee.Columns.Add("QUANTITY",       typeof(int));
                tableEmployee.Columns.Add("UNIT_PRICE",     typeof(string));
                tableEmployee.Columns.Add("AMOUNT",         typeof(string));

                DataTable result = excelDataSource.ExcelToDataTable();

                for(int i = 14; i < result.Rows.Count; i++)
                {
                    // Transform the data as needed
                    object[] values = result.Rows[i].ItemArray;

                    string no           = values[0].ToString();
                    string part_name    = values[1].ToString();
                    string part_no      = values[8].ToString();
                    string customer_po  = values[9].ToString();
                    int quantity        = Convert.ToInt16(Convert.ToString(values[10]).Replace(",",""));
                    string unit_price   = values[13].ToString();
                    string amount       = "";

                    tableEmployee.Rows.Add(no, part_name, part_no, customer_po, quantity, unit_price, amount);
                }    

                    //using (ATPOS_Entities db = new ATPOS_Entities())
                    //{
                    //    foreach (DataRow row in tableEmployee.Rows)
                    //    {
                    //        DateTime _date = Convert.ToDateTime(row["DATE"]);
                    //        string _EmpCode = Convert.ToString(row["CODE"]);
                    //        string _M = Convert.ToString(row["M"]);
                    //        string _E1 = Convert.ToString(row["E1"]);
                    //        string _E2 = Convert.ToString(row["E2"]);
                    //        string _E3 = Convert.ToString(row["E3"]);
                    //        string _E4 = Convert.ToString(row["E4"]);
                    //        string _WK = Convert.ToString(row["WK"]);

                    //        if (!String.IsNullOrEmpty(_EmpCode))
                    //        {
                    //            //Check exit employee in date in DB
                    //            var isExist = db.DATA_SHIFT.Where(x => (x.WORKER.Equals(_EmpCode))
                    //                                                && (x.DATE.Equals(_date.Date))).SingleOrDefault();

                    //            if (isExist != null)
                    //            {
                    //                //If exist employee update info
                    //                if (Convert.ToInt16(isExist.M) == 0)
                    //                {
                    //                    isExist.M = _M;
                    //                }

                    //                if (Convert.ToInt16(isExist.E1) == 0)
                    //                {
                    //                    isExist.E1 = _E1;
                    //                }

                    //                if (Convert.ToInt16(isExist.E2) == 0)
                    //                {
                    //                    isExist.E2 = _E2;
                    //                }

                    //                if (Convert.ToInt16(isExist.E3) == 0)
                    //                {
                    //                    isExist.E3 = _E3;
                    //                }

                    //                if (Convert.ToInt16(isExist.E4) == 0)
                    //                {
                    //                    isExist.E4 = _E4;
                    //                }

                    //                isExist.WK = _WK;
                    //            }
                    //            else
                    //            {
                    //                //Otherwise add new
                    //                DATA_SHIFT empInfo = new DATA_SHIFT
                    //                {
                    //                    DATE = get_date.Date,
                    //                    SHIFT = null,
                    //                    WORKER = _EmpCode,
                    //                    IS_M_PRINTED = 0,
                    //                    M = _M,
                    //                    E1 = _E1,
                    //                    E2 = _E2,
                    //                    E3 = _E3,
                    //                    E4 = _E4,
                    //                    IS_E_PRINTED = 0,
                    //                    WK = _WK,
                    //                };
                    //                db.DATA_SHIFT.Add(empInfo);
                    //            }
                    //        }

                    //        //Excute
                    //        try
                    //        {
                    //            db.SaveChanges();
                    //        }
                    //        catch (Exception ex)
                    //        {
                    //            MessageBox.Show($"Loại lỗi: {ex.GetType()}.\nLỗi: {ex}", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Error); // What is the real exception?
                    //        }
                    //    }
                    //}

                    //MessageBox.Show("Import dữ liệu phân ca thành công.", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Information);

                    //////Clear header
                    ////Qly_PhanCa_Clear_Header_Data();

                    //Set gridview data
                    gridControl_Invoice.DataSource = tableEmployee;
            }
        }
    }
}   