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
    /// Interaction logic for ShowLaboratory.xaml
    /// </summary>
    public partial class ShowLaboratory : Window
    {
        SqlDataAdapter adp;
        SqlCommandBuilder scb;
        DataSet ds;
        public ShowLaboratory()
        {
            InitializeComponent();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {

            adp = new SqlDataAdapter("select TECHNICAL_SUPPORT.NAME,TECHNICAL_SUPPORT.ADDRESS,laboratory.Patient_Name,laboratory.Patient_Phone,laboratory.IN_TIME,laboratory.OUT_TIME,laboratory.DETAILS  from TECHNICAL_SUPPORT  inner join laboratory on TECHNICAL_SUPPORT.ID= laboratory.TechnicalID", ConfigurationManager.ConnectionStrings["ConnectionString"].ToString());
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
