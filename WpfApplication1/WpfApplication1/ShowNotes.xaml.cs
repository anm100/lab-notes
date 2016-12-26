using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace WpfApplication1
{
    /// <summary>
    /// Interaction logic for ShowNotes.xaml
    /// </summary>
    public partial class ShowNotes : Window
    {
        SqlDataAdapter adp;
        SqlCommandBuilder scb;
        DataSet ds;
        public ShowNotes()
        {
            InitializeComponent();
            double screenWidth = System.Windows.SystemParameters.PrimaryScreenWidth;
            double screenHeight = System.Windows.SystemParameters.PrimaryScreenHeight;
            double windowWidth = this.Width;
            double windowHeight = this.Height;
            this.Left = (screenWidth / 2) - (windowWidth / 2);
            this.Top = (screenHeight / 2) - (windowHeight / 2);
            this.Width = screenWidth / 2;
            this.Height = (screenHeight / 2);
            dataGrid.Width = this.Width;
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
                    
                 adp = new SqlDataAdapter("select * from NOTES", ConfigurationManager.ConnectionStrings["ConnectionString"].ToString());
                 ds = new DataSet();
                adp.Fill(ds, "TableName");
            dataGrid.DataContext = ds.Tables[0];
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            scb = new SqlCommandBuilder(adp);
            adp.Update(ds.Tables[0]);
        }
    }
}
