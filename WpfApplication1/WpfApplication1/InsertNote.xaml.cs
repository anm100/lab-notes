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
    /// Interaction logic for InsertNote.xaml
    /// </summary>
    public partial class InsertNote : Window
    {
        public InsertNote()
        {
            InitializeComponent();
        }

        private void button_Click(object sender, RoutedEventArgs e)
        {
            string details=textBox.Text;
            string dt = datetime.SelectedDate.Value.ToString("MM/dd/yyyy");
            insertNote(details, dt);
            if (MessageBox.Show("!ההוספה התבצעה בהצלחה", "שאלה", MessageBoxButton.OK, MessageBoxImage.Information) == MessageBoxResult.OK)
            {
                Application.Current.MainWindow.Show();
                this.Close();
            }

        }

        private void insertNote(string details, string dt)
        {
            using (SqlConnection openCon = new SqlConnection(ConfigurationManager.ConnectionStrings["ConnectionString"].ToString()))
            {
                string save = "INSERT into NOTES (DATE,DETAILS) VALUES (@DATE,@DETAILS)";

                using (SqlCommand query = new SqlCommand(save))
                {
                    query.Parameters.Add("@DATE",dt);
                    query.Parameters.Add("@DETAILS", details);
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
    }
}
