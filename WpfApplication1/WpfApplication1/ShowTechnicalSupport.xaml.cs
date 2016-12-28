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
    /// Interaction logic for ShowTechnicalSupport.xaml
    /// </summary>
    public partial class ShowTechnicalSupport : Window
    {
        SqlDataAdapter adp;
        SqlCommandBuilder scb;
        DataSet ds;
        public ShowTechnicalSupport()
        {
            InitializeComponent();
        }
        private void Window_Loaded(object sender, RoutedEventArgs e)
        {

            adp = new SqlDataAdapter("select * from TECHNICAL_SUPPORT", ConfigurationManager.ConnectionStrings["ConnectionString"].ToString());
            ds = new DataSet();
            adp.Fill(ds, "TableName");
            dataGrid.DataContext = ds.Tables[0];
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            scb = new SqlCommandBuilder(adp);
            adp.Update(ds.Tables[0]);
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            Application.Current.MainWindow.Show();
            this.Close();

        }
    }
}
