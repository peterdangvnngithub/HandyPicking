using System.Data;
using System.Linq;
using System.Collections;
using System.ComponentModel;
using System.Collections.Generic;
using DevExpress.DataAccess.Excel;

namespace Handy_Picking_Winform
{
    public static class Extension
    {
        public static DataTable ExcelToDataTable(this ExcelDataSource excelDataSource)
        {
            IList list = ((IListSource)excelDataSource).GetList();
            DevExpress.DataAccess.Native.Excel.DataView dataView = (DevExpress.DataAccess.Native.Excel.DataView)list;
            List<PropertyDescriptor> props = dataView.Columns.ToList<PropertyDescriptor>();

            DataTable table = new DataTable();
            for (int i = 0; i < props.Count; i++)
            {
                PropertyDescriptor prop = props[i];
                table.Columns.Add(prop.Name, prop.PropertyType);
            }
            object[] values = new object[props.Count];
            foreach (DevExpress.DataAccess.Native.Excel.ViewRow item in list)
            {
                for (int i = 0; i < values.Length; i++)
                {
                    values[i] = props[i].GetValue(item);
                }
                table.Rows.Add(values);
            }
            return table;
        }
    }

    public class Data_Compare
    {
        public string CUSTOMER_ITEM_CODE { get; set; }
        public string TVC_ITEM_CODE { get; set; }
        public int QUANTITY { get; set; }

        public Data_Compare(
            string _customer_item_code, 
            string _tvc_item_code,
            int _quantity)
        {
            CUSTOMER_ITEM_CODE  = _customer_item_code;
            TVC_ITEM_CODE       = _tvc_item_code;
            QUANTITY            = _quantity;
        }
    }

    public class Sum_DataCompare
    {
        public string CUSTOMER_ITEM_CODE { get; set; }
        public string TVC_ITEM_CODE { get; set; }
        public int QUANTITY { get; set; }

        public Sum_DataCompare(
            string _customer_item_code,
            string _tvc_item_code, 
            int _quantity)
        {
            CUSTOMER_ITEM_CODE  = _customer_item_code;
            TVC_ITEM_CODE       = _tvc_item_code;
            QUANTITY            = _quantity;
        }
    }

    public class Sum_PickingList
    {
        public string PL_CUSTOMER_ITEM_CODE { get; set; }
        public string PL_TVC_ITEM_CODE { get; set; }
        public int PL_QUANTITY { get; set; }

        public Sum_PickingList(
            string _pl_CUSTOMER_ITEM_CODE,
            string _pl_TVC_ITEM_CODE,
            int _pl_QUANTITY
        )
        {
            PL_CUSTOMER_ITEM_CODE   = _pl_CUSTOMER_ITEM_CODE;
            PL_TVC_ITEM_CODE        = _pl_TVC_ITEM_CODE;
            PL_QUANTITY             = _pl_QUANTITY;
        }
    }

    public class Compare_DataImport_With_PickingList
    {
        public string CUSTOMER_ITEM_CODE { get; set; }
        public string TVC_ITEM_CODE { get; set; }
        public int QUANTITY { get; set; }
        public string PL_CUSTOMER_ITEM_CODE { get; set; }
        public string PL_TVC_ITEM_CODE { get; set; }
        public int PL_QUANTITY { get; set; }
        public int QUANTITY_DIFFERENCE { get; set; }

        public Compare_DataImport_With_PickingList(
            string _customer_item_code,
            string _tvc_item_code,
            int _quantity,
            string _pl_CUSTOMER_ITEM_CODE,
            string _pl_TVC_ITEM_CODE,
            int _pl_QUANTITY,
            int _quantity_DIFFERENCE)
        {
            CUSTOMER_ITEM_CODE      = _customer_item_code;
            TVC_ITEM_CODE           = _tvc_item_code;
            QUANTITY                = _quantity;
            PL_CUSTOMER_ITEM_CODE   = _pl_CUSTOMER_ITEM_CODE;
            PL_TVC_ITEM_CODE        = _pl_TVC_ITEM_CODE;
            PL_QUANTITY             = _pl_QUANTITY;
            QUANTITY_DIFFERENCE     = _quantity_DIFFERENCE;
        }
    }

    public class Picking_List_Merge
    {
        //public string PICKING_LIST_NO { get; set; }
        public string PALLET_NO { get; set; }
        public string CUS_ITEM_CODE { get; set; }
        public string TVC_ITEM_CODE { get; set; }
        public int QTY_CARTON { get; set; }
        public int QTY_PER_CARTON { get; set; }
        public int QTY_TOTAL { get; set; }
        public decimal NET_WEIGHT_TOTAL { get; set; }
        public decimal GROSS_WEIGHT { get; set; }
        public string LOT_NO { get; set; }

        public Picking_List_Merge(
            //string _picking_list_no, 
            string _pallet_no, 
            string _cus_item_code, 
            string _tvc_item_code, 
            int _qty_carton, 
            int _qty_per_carton, 
            int _qty_total, 
            decimal _net_weight_total, 
            decimal _gross_weight, 
            string _lot_no
        )
        {
            //PICKING_LIST_NO     = _picking_list_no;
            PALLET_NO           = _pallet_no;
            CUS_ITEM_CODE       = _cus_item_code;
            TVC_ITEM_CODE       = _tvc_item_code;
            QTY_CARTON          = _qty_carton;
            QTY_PER_CARTON      = _qty_per_carton;
            QTY_TOTAL           = _qty_total;
            NET_WEIGHT_TOTAL    = _net_weight_total;
            GROSS_WEIGHT        = _gross_weight;
            LOT_NO              = _lot_no;
        }
    }
}
