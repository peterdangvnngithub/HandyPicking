using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace Handy_Picking_Winform.Utils
{
    public static class Common
    {
        // Get information about the application's assembly
        public static string Get_Current_Version()
        {
            Assembly assembly = Assembly.GetExecutingAssembly();

            Version version = assembly.GetName().Version;

            return Convert.ToString(version);
        }
    }
}
