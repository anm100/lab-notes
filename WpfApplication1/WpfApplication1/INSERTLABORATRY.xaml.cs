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
    /// Interaction logic for INSERTLABORATRY.xaml
    /// </summary>
    public partial class INSERTLABORATRY : Window
    {
        SqlDataAdapter adp;
        SqlCommandBuilder scb;
        DataSet ds;
        public INSERTLABORATRY()
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

            adp = new SqlDataAdapter("select * from TECHNICAL_SUPPORT", ConfigurationManager.ConnectionStrings["ConnectionString"].ToString());
            ds = new DataSet();
            adp.Fill(ds, "TableName");
            comboBox.ItemsSource = ds.Tables[0].DefaultView;
            comboBox.DisplayMemberPath = ds.Tables[0].Columns["NAME"].ToString();
            comboBox.SelectedValuePath = ds.Tables[0].Columns["ID"].ToString();
        }
        private void button_Click(object sender, RoutedEventArgs e)
        {
            if (textBox1.Text == "" || textBox.Text == "" || textBox_Copy.Text == "" || dt1.SelectedDate == null || dt2.SelectedDate == null)
            {
                if (MessageBox.Show("!נא למלא כל השדות", "אזהרה", MessageBoxButton.OK, MessageBoxImage.Warning) == MessageBoxResult.OK)
                {
                    return;
                }
            }
            else
            {
                string name = this.textBox.Text;
                string phone = this.textBox_Copy.Text;
                int id = int.Parse(comboBox.SelectedValue.ToString());
                string from = dt1.SelectedDate.Value.ToString("MM/dd/yyyy");
                string to = dt2.SelectedDate.Value.ToString("MM/dd/yyyy");
                string details = textBox1.Text;
                using (SqlConnection openCon = new SqlConnection(ConfigurationManager.ConnectionStrings["ConnectionString"].ToString()))
                {
                    string save = "INSERT into laboratory (Patient_Name,Patient_Phone,IN_TIME,OUT_TIME,TechnicalID,DETAILS) VALUES (@Patient_Name,@Patient_Phone,@IN_TIME,@OUT_TIME,@TechnicalID,@DETAILS)";

                    using (SqlCommand query = new SqlCommand(save))
                    {
                        query.Parameters.Add("@Patient_Name", name);
                        query.Parameters.Add("@Patient_Phone", phone);
                        query.Parameters.Add("@IN_TIME", from);
                        query.Parameters.Add("@OUT_TIME", to);
                        query.Parameters.Add("@TechnicalID", id);
                        query.Parameters.Add("@DETAILS", details);
                        query.Connection = openCon;
                        openCon.Open();
                        query.ExecuteNonQuery();
                        openCon.Close();
                    }
                }

                if (MessageBox.Show("!ההוספה התבצעה בהצלחה", "שאלה", MessageBoxButton.OK, MessageBoxImage.Information) == MessageBoxResult.OK)
                {
                    this.Close();
                }

            }
        }

        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            Application.Current.MainWindow.Show();
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            this.Close();

        }
    }
}
