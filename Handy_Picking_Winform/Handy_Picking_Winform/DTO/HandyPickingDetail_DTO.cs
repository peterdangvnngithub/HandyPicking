using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Handy_Picking_Winform.DTO
{
    public class HandyPickingDetail_DTO
    {
        public string PICKING_LIST_NO { get; set; }
        public string INVOICE_NO { get; set; }
        public string SALE_ORDER { get; set; }
        public string ITEM_CODE { get; set; }
        public string LOT_ID { get; set; }
        public int QUANTITY { get; set; }
        public string PALLET_NO { get; set; }
        public string SERIES { get; set; }
        public string CUS_ITEM_CODE { get; set; }
        public string TVC_ITEM_CODE { get; set; }
        public string CUSTOMER_PO { get; set; }
        public int QTY_CARTON { get; set; }
        public int QTY_PER_CARTON { get; set; }
        public int QTY_TOTAL { get; set; }
        public decimal NET_WEIGHT { get; set; }
        public decimal NET_WEIGHT_TOTAL { get; set; }
        public decimal GROSS_WEIGHT { get; set; }
        public string LOT_NO { get; set; }
        public System.DateTime CREATE_DATE { get; set; }
        public string CREATE_BY { get; set; }
        public Nullable<System.DateTime> EDIT_DATE { get; set; }
        public string EDIT_BY { get; set; }
        public Nullable<int> STATUS { get; set; }
        public string COLUMN1 { get; set; }
        public string COLUMN2 { get; set; }
        public string COLUMN3 { get; set; }
        public string COLUMN4 { get; set; }
        public string COLUMN5 { get; set; }

        public HANDY_PICKING_DETAIL ToHandyPickingDetail()
        {
            return new HANDY_PICKING_DETAIL
            {
                PICKING_LIST_NO = this.PICKING_LIST_NO,
                INVOICE_NO = this.INVOICE_NO,
                SALE_ORDER = this.SALE_ORDER,
                ITEM_CODE = this.ITEM_CODE,
                LOT_ID = this.LOT_ID,
                QUANTITY = this.QUANTITY,
                PALLET_NO = this.PALLET_NO,
                SERIES = this.SERIES,
                CUS_ITEM_CODE = this.CUS_ITEM_CODE,
                TVC_ITEM_CODE = this.TVC_ITEM_CODE,
                CUSTOMER_PO = this.CUSTOMER_PO,
                QTY_CARTON = this.QTY_CARTON,
                QTY_PER_CARTON = this.QTY_PER_CARTON,
                QTY_TOTAL = this.QTY_TOTAL,
                NET_WEIGHT = this.NET_WEIGHT,
                NET_WEIGHT_TOTAL = this.NET_WEIGHT_TOTAL,
                GROSS_WEIGHT = this.GROSS_WEIGHT,
                LOT_NO = this.LOT_NO,
                CREATE_DATE = this.CREATE_DATE,
                CREATE_BY = this.CREATE_BY,
                EDIT_DATE = this.EDIT_DATE,
                EDIT_BY = this.EDIT_BY,
                STATUS = this.STATUS,
                COLUMN1 = this.COLUMN1,
                COLUMN2 = this.COLUMN2,
                COLUMN3 = this.COLUMN3,
                COLUMN4 = this.COLUMN4,
                COLUMN5 = this.COLUMN5
            };
        }
    }
}
