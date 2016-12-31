using System;
using System.Collections.Generic;
using System.Configuration;
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
    /// Interaction logic for InsertTechnicalSupport.xaml
    /// </summary>
    public partial class InsertTechnicalSupport : Window
    {
        public InsertTechnicalSupport()
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
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            if (textBox.Text == "" || textBox1.Text == "")
            {
                if (MessageBox.Show("!נא למלא כל השדות", "אזהרה", MessageBoxButton.OK, MessageBoxImage.Warning) == MessageBoxResult.OK)
                {
                    return;
                }
            }
            else
            {
                    string name = this.textBox.Text;
                    string address = this.textBox1.Text;
                    insertTECHNICAL_SUPPORT(name, address);
                    if (MessageBox.Show("!ההוספה התבצעה בהצלחה", "שאלה", MessageBoxButton.OK, MessageBoxImage.Information) == MessageBoxResult.OK)
                    {
                        Application.Current.MainWindow.Show();
                        this.Close();
                    }
            }
        }

        private void insertTECHNICAL_SUPPORT(string name, string address)
        {
            using (SqlConnection openCon = new SqlConnection(ConfigurationManager.ConnectionStrings["ConnectionString"].ToString()))
            {
                string save = "INSERT into TECHNICAL_SUPPORT (NAME,ADDRESS) VALUES (@NAME,@ADDRESS)";

                using (SqlCommand query = new SqlCommand(save))
                {
                    query.Parameters.Add("@NAME", name);
                    query.Parameters.Add("@ADDRESS", address);
                    query.Connection = openCon;
                    openCon.Open();
                    query.ExecuteNonQuery();
                    openCon.Close();
                }
            }
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            Application.Current.MainWindow.Show();
            this.Close();
        }

        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            Application.Current.MainWindow.Show();
        }
    }
}
