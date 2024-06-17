using System;
using System.Linq;
using System.Drawing;
using System.Windows.Forms;
using System.Collections.Generic;
using DevExpress.Utils;
using DevExpress.XtraBars;
using DevExpress.XtraGrid.Columns;
using DevExpress.XtraGrid.Views.Grid;
using DevExpress.DataAccess.Excel;
using System.Data;
using System.Data.Entity.Infrastructure;
using Handy_Picking_Winform.Utils;
using DevExpress.XtraEditors.Repository;
using DevExpress.XtraEditors.Controls;

namespace Handy_Picking_Winform
{
    public partial class Form_Product_Master : DevExpress.XtraBars.Ribbon.RibbonForm
    {
        #region Define the global variable  
        // PRODUCT MASTER
        private readonly GridColumn grid_Product_Master_Col_DELETE_ROW                      = new GridColumn();
        private readonly GridColumn grid_Product_Master_Col_PRODUCT_NUMBER                  = new GridColumn();
        private readonly GridColumn grid_Product_Master_Col_TYPE                            = new GridColumn();
        private readonly GridColumn grid_Product_Master_Col_REFERENCE                       = new GridColumn();
        private readonly GridColumn grid_Product_Master_Col_ADDRESS_CODE                    = new GridColumn();
        private readonly GridColumn grid_Product_Master_Col_EXTERNAL_ITEM_CODE              = new GridColumn();
        private readonly GridColumn grid_Product_Master_Col_EXTERNAL_ITEM_NAME              = new GridColumn();
        private readonly RepositoryItemButtonEdit grid_Product_Master_repo_btn_DeleteRow    = new RepositoryItemButtonEdit();

        private readonly USER_MS userMS                                                     = new USER_MS();
        List<PRODUCT_MASTER> list_Product_Master                                            = new List<PRODUCT_MASTER>();
        #endregion

        public Form_Product_Master(USER_MS _userMS)
        {
            InitializeComponent();
            userMS = _userMS;
        }

        private void Form_Main_Load(object sender, EventArgs e)
        {
            // Define gridview structure
            Define_GridView(gridView_Product_Master);

            SettingInit();
        }

        private void SettingInit()
        {
            barStatic_User.Caption = userMS.USERNAME;
            barStatic_Version.Caption = "Version: " + Common.Get_Current_Version();

            list_Product_Master = Get_Data_Product_Master();
            gridControl_Product_Master.DataSource = list_Product_Master;
        }

        private List<PRODUCT_MASTER> Get_Data_Product_Master()
        {
            using (HANDY_PICKING_Entities db = new HANDY_PICKING_Entities())
            {
                return db.PRODUCT_MASTER.ToList();
            }
        }

        private void Define_GridView(GridView gridViewControl)
        {
            if (gridViewControl.Name == gridView_Product_Master.Name)
            {
                gridView_Product_Master.OptionsPrint.AutoWidth = false;
                gridView_Product_Master.OptionsView.ColumnAutoWidth = false;
                gridView_Product_Master.OptionsView.RowAutoHeight = true;
                gridView_Product_Master.OptionsView.ColumnHeaderAutoHeight = DefaultBoolean.True;
                gridView_Product_Master.CustomDrawRowIndicator += new RowIndicatorCustomDrawEventHandler(this.gridView_Product_Master_CustomDrawRowIndicator);

                // DELETE ROW
                grid_Product_Master_Col_DELETE_ROW.ColumnEdit = grid_Product_Master_repo_btn_DeleteRow;
                grid_Product_Master_Col_DELETE_ROW.Name = "grid_Product_Master_Col_DELETE_ROW";
                grid_Product_Master_Col_DELETE_ROW.Visible = true;
                grid_Product_Master_Col_DELETE_ROW.VisibleIndex = 0;
                grid_Product_Master_Col_DELETE_ROW.Width = 22;
                grid_Product_Master_Col_DELETE_ROW.OptionsColumn.AllowMerge = DefaultBoolean.False;

                // REPOSITORY BUTTON DELETE ROW
                grid_Product_Master_repo_btn_DeleteRow.AutoHeight = false;
                grid_Product_Master_repo_btn_DeleteRow.Buttons.AddRange(new EditorButton[] { new EditorButton(ButtonPredefines.Delete) });
                grid_Product_Master_repo_btn_DeleteRow.Name = "grid_Product_Master_repo_btn_DeleteRow";
                grid_Product_Master_repo_btn_DeleteRow.TextEditStyle = TextEditStyles.HideTextEditor;
                grid_Product_Master_repo_btn_DeleteRow.Click += new EventHandler(grid_Product_Master_repo_btn_DeleteRow_Click);

                // PRODUCT_NUMBER
                grid_Product_Master_Col_PRODUCT_NUMBER.Name = "grid_Product_Master_Col_PRODUCT_NUMBER";
                grid_Product_Master_Col_PRODUCT_NUMBER.Caption = "PRODUCT_NUMBER";
                grid_Product_Master_Col_PRODUCT_NUMBER.FieldName = "PRODUCT_NUMBER";
                grid_Product_Master_Col_PRODUCT_NUMBER.VisibleIndex = 0;
                grid_Product_Master_Col_PRODUCT_NUMBER.Width = 140;
                grid_Product_Master_Col_PRODUCT_NUMBER.OptionsColumn.AllowEdit = false;
                grid_Product_Master_Col_PRODUCT_NUMBER.OptionsColumn.AllowMerge = DefaultBoolean.False;
                grid_Product_Master_Col_PRODUCT_NUMBER.AppearanceCell.Options.UseTextOptions = true;
                grid_Product_Master_Col_PRODUCT_NUMBER.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // TYPE
                grid_Product_Master_Col_TYPE.Name = "grid_Product_Master_Col_TYPE";
                grid_Product_Master_Col_TYPE.Caption = "TYPE";
                grid_Product_Master_Col_TYPE.FieldName = "TYPE";
                grid_Product_Master_Col_TYPE.VisibleIndex = 1;
                grid_Product_Master_Col_TYPE.Width = 100;
                grid_Product_Master_Col_TYPE.OptionsColumn.AllowEdit = true;
                grid_Product_Master_Col_TYPE.OptionsColumn.AllowMerge = DefaultBoolean.False;
                grid_Product_Master_Col_TYPE.AppearanceCell.Options.UseTextOptions = true;
                grid_Product_Master_Col_TYPE.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // REFERENCE
                grid_Product_Master_Col_REFERENCE.Name = "grid_Product_Master_Col_REFERENCE";
                grid_Product_Master_Col_REFERENCE.Caption = "REFERENCE";
                grid_Product_Master_Col_REFERENCE.FieldName = "REFERENCE";
                grid_Product_Master_Col_REFERENCE.VisibleIndex = 2;
                grid_Product_Master_Col_REFERENCE.Width = 120;
                grid_Product_Master_Col_REFERENCE.OptionsColumn.AllowEdit = false;
                grid_Product_Master_Col_REFERENCE.OptionsColumn.AllowMerge = DefaultBoolean.False;
                grid_Product_Master_Col_REFERENCE.AppearanceCell.Options.UseTextOptions = true;
                grid_Product_Master_Col_REFERENCE.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // ADDRESS CODE 
                grid_Product_Master_Col_ADDRESS_CODE.Name = "grid_Product_Master_Col_ADDRESS_CODE";
                grid_Product_Master_Col_ADDRESS_CODE.Caption = "ADDRESS_CODE";
                grid_Product_Master_Col_ADDRESS_CODE.FieldName = "ADDRESS_CODE";
                grid_Product_Master_Col_ADDRESS_CODE.VisibleIndex = 3;
                grid_Product_Master_Col_ADDRESS_CODE.Width = 130;
                grid_Product_Master_Col_ADDRESS_CODE.OptionsColumn.AllowEdit = false;
                grid_Product_Master_Col_ADDRESS_CODE.OptionsColumn.AllowMerge = DefaultBoolean.False;
                grid_Product_Master_Col_ADDRESS_CODE.AppearanceCell.Options.UseTextOptions = true;
                grid_Product_Master_Col_ADDRESS_CODE.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // EXTERNAL_ITEM_CODE
                grid_Product_Master_Col_EXTERNAL_ITEM_CODE.Name = "grid_Product_Master_Col_EXTERNAL_ITEM_CODE";
                grid_Product_Master_Col_EXTERNAL_ITEM_CODE.Caption = "EXTERNAL_ITEM_CODE";
                grid_Product_Master_Col_EXTERNAL_ITEM_CODE.FieldName = "EXTERNAL_ITEM_CODE";
                grid_Product_Master_Col_EXTERNAL_ITEM_CODE.VisibleIndex = 4;
                grid_Product_Master_Col_EXTERNAL_ITEM_CODE.Width = 150;
                grid_Product_Master_Col_EXTERNAL_ITEM_CODE.OptionsColumn.AllowEdit = false;
                grid_Product_Master_Col_EXTERNAL_ITEM_CODE.AppearanceCell.Options.UseTextOptions = true;
                grid_Product_Master_Col_EXTERNAL_ITEM_CODE.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;

                // EXTERNAL_ITEM_NAME
                grid_Product_Master_Col_EXTERNAL_ITEM_NAME.Name = "grid_Product_Master_Col_EXTERNAL_ITEM_NAME";
                grid_Product_Master_Col_EXTERNAL_ITEM_NAME.Caption = "EXTERNAL_ITEM_NAME";
                grid_Product_Master_Col_EXTERNAL_ITEM_NAME.FieldName = "EXTERNAL_ITEM_NAME";
                grid_Product_Master_Col_EXTERNAL_ITEM_NAME.VisibleIndex = 5;
                grid_Product_Master_Col_EXTERNAL_ITEM_NAME.Width = 150;
                grid_Product_Master_Col_EXTERNAL_ITEM_NAME.OptionsColumn.AllowEdit = false;
                grid_Product_Master_Col_EXTERNAL_ITEM_NAME.OptionsColumn.AllowMerge = DefaultBoolean.False;
                grid_Product_Master_Col_EXTERNAL_ITEM_NAME.AppearanceCell.Options.UseTextOptions = true;
                grid_Product_Master_Col_EXTERNAL_ITEM_NAME.AppearanceCell.TextOptions.HAlignment = HorzAlignment.Center;


                // Add column to gridview
                gridView_Product_Master.Columns.Add(grid_Product_Master_Col_DELETE_ROW);
                gridView_Product_Master.Columns.Add(grid_Product_Master_Col_PRODUCT_NUMBER);
                gridView_Product_Master.Columns.Add(grid_Product_Master_Col_TYPE);
                gridView_Product_Master.Columns.Add(grid_Product_Master_Col_REFERENCE);
                gridView_Product_Master.Columns.Add(grid_Product_Master_Col_ADDRESS_CODE);
                gridView_Product_Master.Columns.Add(grid_Product_Master_Col_EXTERNAL_ITEM_CODE);
                gridView_Product_Master.Columns.Add(grid_Product_Master_Col_EXTERNAL_ITEM_NAME);

                // Set common attribute
                foreach (GridColumn c in gridView_Product_Master.Columns)
                {
                    c.AppearanceHeader.Options.UseFont = true;
                    c.AppearanceHeader.Options.UseForeColor = true;
                    c.AppearanceHeader.Options.UseTextOptions = true;
                    c.AppearanceHeader.Font = new Font("Segoe UI", 8.25F, FontStyle.Bold, GraphicsUnit.Point, ((byte)(0)));
                    c.AppearanceHeader.ForeColor = Color.Black;
                    c.AppearanceHeader.TextOptions.HAlignment = HorzAlignment.Center;
                    c.AppearanceHeader.TextOptions.WordWrap = WordWrap.Wrap;
                    c.AppearanceCell.TextOptions.WordWrap = WordWrap.Wrap;
                }
            }
        }

        private void grid_Product_Master_repo_btn_DeleteRow_Click(object sender, EventArgs e)
        {
            string _productNumber = Convert.ToString(gridView_Product_Master.GetRowCellValue(gridView_Product_Master.FocusedRowHandle, "PRODUCT_NUMBER"));
            string _addressCode = Convert.ToString(gridView_Product_Master.GetRowCellValue(gridView_Product_Master.FocusedRowHandle, "ADDRESS_CODE"));

            if ((MessageBox.Show($"Xóa dữ liệu product:\nProduct Number: {_productNumber}.\nAddress Code: {_addressCode} ?", "Xác Nhận"
                , MessageBoxButtons.YesNo, MessageBoxIcon.Question
                , MessageBoxDefaultButton.Button1) == DialogResult.Yes))
            {
                using (HANDY_PICKING_Entities db = new HANDY_PICKING_Entities())
                {
                    // Lấy đối tượng từ database context
                    PRODUCT_MASTER productMaster = db.PRODUCT_MASTER
                        .FirstOrDefault(x => x.PRODUCT_NUMBER == _productNumber && x.ADDRESS_CODE == _addressCode);

                    if (productMaster != null)
                    {
                        // Xóa đối tượng khỏi context
                        db.PRODUCT_MASTER.Remove(productMaster);

                        // Xóa đối tượng khỏi danh sách local
                        list_Product_Master.Remove(
                            list_Product_Master
                            .FirstOrDefault(x => x.PRODUCT_NUMBER == _productNumber && x.ADDRESS_CODE == _addressCode)
                        );

                        // Thực hiện
                        try
                        {
                            db.SaveChanges();

                            // Xóa tất cả các bộ lọc trên lưới
                            gridView_Product_Master.ClearColumnsFilter();

                            // Refresh lại lưới
                            gridView_Product_Master.RefreshData();

                            MessageBox.Show("Xóa dữ liệu product thành công", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        }
                        catch (DbUpdateException ex)
                        {
                            // Xử lý lỗi cơ sở dữ liệu (ví dụ: khóa chính trùng lặp, ràng buộc, ...)
                            MessageBox.Show("Lỗi cơ sở dữ liệu: " + ex.Message, "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        }
                        catch (Exception ex)
                        {
                            MessageBox.Show($"Loại lỗi: {ex.GetType()}.\nLỗi: {ex}", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Error); // What is the real exception?
                        }
                    }
                    else
                    {
                        MessageBox.Show("Không tìm thấy product để xóa.", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }
                }
            }
        }

        private void gridView_Product_Master_CustomDrawRowIndicator(object sender, RowIndicatorCustomDrawEventArgs e)
        {
            if (e.RowHandle >= 0)
            {
                e.Info.DisplayText = (e.RowHandle + 1).ToString();
                e.Appearance.TextOptions.HAlignment = HorzAlignment.Center;
            }
        }

        private void barBtn_ExportPL_ItemClick(object sender, ItemClickEventArgs e)
        {
            if (MessageBox.Show($"Xuất dữ liệu product master?"
                , "Xác Nhận"
                , MessageBoxButtons.YesNo
                , MessageBoxIcon.Question
                , MessageBoxDefaultButton.Button1) == DialogResult.Yes)
            {
                SaveFileDialog saveDialog = new SaveFileDialog
                {
                    Title = "Save Report Data",
                    FileName = "Product_Master" + DateTime.Now.ToString("ddMMyyyy_hhmmss"),
                    Filter = "Files Excel|*.xlsx;*.xls"
                };

                if (saveDialog.ShowDialog() == DialogResult.OK)
                {
                    string path = saveDialog.FileName;
                    gridControl_Product_Master.ExportToXlsx(path);

                    MessageBox.Show("Xuất dữ liệu product master thành công", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Information);

                    //// Open the created XLSX file with the default application.
                    //Process.Start(path);
                }
            }
        }
        private void Form_Main_FormClosing(object sender, FormClosingEventArgs e)
        {
            DialogResult dialogResult = MessageBox.Show(
                "Bạn muốn đóng form Product Master?",
                "Xác nhận",
                MessageBoxButtons.YesNo,
                MessageBoxIcon.Question
            );

            if (dialogResult == DialogResult.No)
            {
                e.Cancel = true; // Cancel event close form
            }
        }

        private void barBtn_Refresh_ItemClick(object sender, ItemClickEventArgs e)
        {
            SettingInit();
        }

        private void barBtn_Import_Production_Master_ItemClick(object sender, ItemClickEventArgs e)
        {
            //Get link file excel
            OpenFileDialog theDialog = new OpenFileDialog
            {
                Title = "Chọn file dữ liệu cần import",
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

                DataTable tableProductionMaster = new DataTable();
                tableProductionMaster.Columns.Add("PRODUCT_NUMBER",     typeof(string));
                tableProductionMaster.Columns.Add("TYPE",               typeof(string));
                tableProductionMaster.Columns.Add("REFERENCE",          typeof(string));
                tableProductionMaster.Columns.Add("ADDRESS_CODE",       typeof(string));
                tableProductionMaster.Columns.Add("EXTERNAL_ITEM_CODE", typeof(string));
                tableProductionMaster.Columns.Add("EXTERNAL_ITEM_NAME", typeof(string));

                tableProductionMaster = excelDataSource.ExcelToDataTable();

                using (HANDY_PICKING_Entities db = new HANDY_PICKING_Entities())
                {
                    foreach (DataRow row in tableProductionMaster.Rows)
                    {
                        string _productNumber       = Convert.ToString(row["PRODUCT_NUMBER"]);
                        string _type                = Convert.ToString(row["TYPE"]);
                        string _refrence            = Convert.ToString(row["REFERENCE"]);
                        string _addressCode         = Convert.ToString(row["ADDRESS_CODE"]);
                        string _externalItemCode    = Convert.ToString(row["EXTERNAL_ITEM_CODE"]);
                        string _externalItemName    = Convert.ToString(row["EXTERNAL_ITEM_NAME"]);

                        if (!String.IsNullOrEmpty(_productNumber) && !String.IsNullOrEmpty(_addressCode))
                        {
                            //Check exit prduction master in date in DB
                            var isExist = 
                                db
                                .PRODUCT_MASTER
                                .Where(
                                        x => (  x.PRODUCT_NUMBER.Equals(_productNumber))
                                            && (x.ADDRESS_CODE.Equals(_addressCode)
                                            )
                                ).SingleOrDefault();

                            if (isExist != null)
                            {
                                isExist.PRODUCT_NUMBER      = _productNumber;
                                isExist.TYPE                = _type;
                                isExist.REFERENCE           = _refrence;
                                isExist.ADDRESS_CODE        = _addressCode;
                                isExist.EXTERNAL_ITEM_CODE  = _externalItemCode;
                                isExist.EXTERNAL_ITEM_NAME  = _externalItemName;
                                isExist.EDIT_BY             = Convert.ToString(userMS.USERNAME);
                                isExist.EDIT_DATE           = DateTime.Now;
                            }
                            else
                            {
                                //Otherwise add new
                                PRODUCT_MASTER productionMaster = new PRODUCT_MASTER
                                {
                                    PRODUCT_NUMBER      = _productNumber,
                                    TYPE                = _type,
                                    REFERENCE           = _refrence,
                                    ADDRESS_CODE        = _addressCode,
                                    EXTERNAL_ITEM_CODE  = _externalItemCode,
                                    EXTERNAL_ITEM_NAME  = _externalItemName,
                                    CREATE_DATE         = DateTime.Now,
                                    CREATE_BY           = Convert.ToString(userMS.USERNAME),
                                    EDIT_DATE           = null,
                                    EDIT_BY             = null
                                };
                                db.PRODUCT_MASTER.Add(productionMaster);
                            }
                        }

                        //Excute
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
                            MessageBox.Show($"Loại lỗi: {ex.GetType()}.\nLỗi: {ex}", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Error); // What is the real exception?
                        }
                    }
                }

                gridControl_Product_Master.DataSource = list_Product_Master;
                xMainTab.SelectedTabPageIndex = 1;

                MessageBox.Show("Import dữ liệu product master thành công", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }
    }
}   