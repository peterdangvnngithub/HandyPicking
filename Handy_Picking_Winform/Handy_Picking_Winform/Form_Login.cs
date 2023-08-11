using System;
using System.Linq;
using System.Drawing;
using System.Windows.Forms;

namespace Handy_Picking_Winform
{
    public partial class Form_Login : Form
    {
        public Form_Login()
        {
            InitializeComponent();
        }

        #region Moveable
        bool mouseDown = false;
        Point startPoint = new Point(0, 0);
        private void panelTopLeft_MouseDown(object sender, MouseEventArgs e)
        {
            mouseDown = true;
            startPoint = new Point(e.X, e.Y);
        }

        private void panelTopRight_MouseDown(object sender, MouseEventArgs e)
        {
            mouseDown = true;
            startPoint = new Point(e.X, e.Y);
        }

        private void panelTopLeft_MouseUp(object sender, MouseEventArgs e)
        {
            mouseDown = false;
        }

        private void panelTopRight_MouseUp(object sender, MouseEventArgs e)
        {
            mouseDown = false;
        }

        private void panelTopLeft_MouseMove(object sender, MouseEventArgs e)
        {
            if (mouseDown)
            {
                Point p = PointToScreen(e.Location);
                this.Location = new Point(p.X - startPoint.X, p.Y - startPoint.Y);
            }
        }

        private void panelTopRight_MouseMove(object sender, MouseEventArgs e)
        {
            if (mouseDown)
            {
                Point p = PointToScreen(e.Location);
                this.Location = new Point(p.X - startPoint.X, p.Y - startPoint.Y);
            }
        }
        #endregion

        private void picShowPass_MouseDown(object sender, MouseEventArgs e)
        {
            picShowPass.Image = Properties.Resources.eye_gray;
            txtPassWord.PasswordChar = '\0';
        }

        private void picShowPass_MouseHover(object sender, EventArgs e)
        {
            ToolTip tt = new ToolTip();
            tt.SetToolTip(this.picShowPass, "Show Password");
        }

        private void picShowPass_MouseUp(object sender, MouseEventArgs e)
        {
            picShowPass.Image           = Properties.Resources.icon_eye;
            txtPassWord.PasswordChar    = '*';
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnLogin_Click(object sender, EventArgs e)
        {
            string _userName = txtUserName.Text.Trim();
            string _passWord = txtPassWord.Text.Trim();
            if (Check_Error())
            {
                USER_MS userInfo = new USER_MS();
                using (HANDY_PICKING_Entities db = new HANDY_PICKING_Entities())
                {
                    userInfo =
                        db
                        .USER_MS
                        .FirstOrDefault(
                            x => (
                                   x.USERNAME.Equals(_userName)
                                && x.PASSWORD.Equals(_passWord)
                            )
                        );
                    if(userInfo == null)
                    {
                        MessageBox.Show("Tên đăng nhập hoặc mật khẩu không đúng", "Cảnh Báo", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    } else
                    {
                        this.Hide();

                        Form_Main formMain = new Form_Main(userInfo);
                        formMain.Show();
                    }
                }
            }    
        }

        private bool Check_Error()
        {
            string _userName = txtUserName.Text.Trim();
            string _passWord = txtPassWord.Text.Trim();
            if (String.IsNullOrEmpty(_userName))
            {
                MessageBox.Show("Chưa nhập Username", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                txtUserName.Focus();
                return false;
            }
            if (String.IsNullOrEmpty(_passWord))
            {
                MessageBox.Show("Chưa nhập mật khẩu", "Thông Báo", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                txtPassWord.Focus();
                return false;
            }
            return true;
        }

        private void txtUserName_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                this.SelectNextControl((Control)sender, true, true, true, true);
            }
        }

        private void txtPassWord_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                btnLogin_Click(sender, e);
            }
        }
    }
}
