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
        List<string> deleted = new List<string>();
        public ShowLaboratory()
        {
            InitializeComponent();
            double screenWidth = System.Windows.SystemParameters.PrimaryScreenWidth;
            double screenHeight = System.Windows.SystemParameters.PrimaryScreenHeight;
            double windowWidth = this.Width;
            double windowHeight = this.Height;
            this.Left = (screenWidth / 2) - (windowWidth / 2);
            this.Top = (screenHeight / 2) - (windowHeight / 2);
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {

            adp = new SqlDataAdapter("select *  from TECHNICAL_SUPPORT  inner join laboratory on TECHNICAL_SUPPORT.ID= laboratory.TechnicalID", ConfigurationManager.ConnectionStrings["ConnectionString"].ToString());
            ds = new DataSet();
            adp.Fill(ds, "TableName");
            dataGrid.DataContext = ds.Tables[0];
        }
        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            Application.Current.MainWindow.Show();

        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            try { 
            foreach (string id in deleted)
            {
                using (SqlConnection openCon = new SqlConnection(ConfigurationManager.ConnectionStrings["ConnectionString"].ToString()))
                {
                    string save = "DELETE FROM  laboratory WHERE ID=" + id;

                    using (SqlCommand query = new SqlCommand(save))
                    {
                        query.Connection = openCon;
                        openCon.Open();
                        query.ExecuteNonQuery();
                        openCon.Close();
                    }
                }
            }
                deleted.Clear();
            if (MessageBox.Show("!המחיקה התבצעה בהצלחה", "שאלה", MessageBoxButton.OK, MessageBoxImage.Information) == MessageBoxResult.OK)
            {
                return;
            }
        }catch(Exception ex)
            {
                if (MessageBox.Show("!המחיקה לא התבצעה ", "שאלה", MessageBoxButton.OK, MessageBoxImage.Error) == MessageBoxResult.OK)
                {
                    return;
                }

            }

}

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            Application.Current.MainWindow.Show();
            this.Close();
        }

        private void dataGrid_PreviewKeyDown(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Delete)
            {
               
                var dataGrid = (DataGrid)sender;
                foreach (DataRowView row in dataGrid.SelectedItems)
                {
                    deleted.Add(row["ID1"].ToString());
                 }
            }
        }
    }
}
