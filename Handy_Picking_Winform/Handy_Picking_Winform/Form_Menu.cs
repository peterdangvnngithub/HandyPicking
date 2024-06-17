using System;
using System.Drawing;
using System.Windows.Forms;

namespace Handy_Picking_Winform
{
    public partial class Form_Menu : DevExpress.XtraEditors.XtraForm
    {
        private readonly USER_MS userMS;
        public Form_Menu(USER_MS _userMS)
        {
            InitializeComponent();
            userMS = _userMS;
        }

        #region Moveable
        bool mouseDown = false;
        Point startPoint = new Point(0, 0);
        private void panelTop_MouseDown(object sender, MouseEventArgs e)
        {
            mouseDown = true;
            startPoint = new Point(e.X, e.Y);
        }

        private void panelTop_MouseUp(object sender, MouseEventArgs e)
        {
            mouseDown = false;
        }

        private void panelTop_MouseMove(object sender, MouseEventArgs e)
        {
            if (mouseDown)
            {
                Point p = PointToScreen(e.Location);
                this.Location = new Point(p.X - startPoint.X, p.Y - startPoint.Y);
            }
        }
        #endregion

        private void picBox_ProductMaster_Click(object sender, EventArgs e)
        {
            Form_Product_Master formProductionMaster = new Form_Product_Master(userMS);
            formProductionMaster.ShowDialog();
        }

        private void lbl_ImportProductMaster_Click(object sender, EventArgs e)
        {
            Form_Product_Master formProductionMaster = new Form_Product_Master(userMS);
            formProductionMaster.ShowDialog();
        }

        private void picBox_HandyPicking_Click(object sender, EventArgs e)
        {
            Form_Main formMain = new Form_Main(userMS);
            formMain.ShowDialog();
        }

        private void lbl_HandyPicking_Click(object sender, EventArgs e)
        {
            Form_Main formMain = new Form_Main(userMS);
            formMain.ShowDialog();
        }

        private void Form_Menu_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (e.CloseReason == CloseReason.UserClosing)
            {
                if (!ConfirmClose())
                {
                    e.Cancel = true;
                }
            }
        }

        private void lbl_X_Click(object sender, EventArgs e)
        {
            // Create object FormClosingEventArgs fake using in event ConfirmClose
            var fakeClosingArgs = new FormClosingEventArgs(CloseReason.UserClosing, false);

            if (!ConfirmClose())
            {
                fakeClosingArgs.Cancel = true;
            }
            else
            {
                Application.Exit();
            }
        }

        private bool ConfirmClose()
        {
            DialogResult dialogResult =
                MessageBox.Show(
                    "Bạn có muốn thoát khỏi phần mềm Handy Picking?",
                    "Thông Báo",
                    MessageBoxButtons.OKCancel,
                    MessageBoxIcon.Information
                );

            return dialogResult == DialogResult.OK;
        }
    }
}