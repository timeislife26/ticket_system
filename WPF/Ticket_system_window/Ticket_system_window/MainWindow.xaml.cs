using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Ticket_system_window
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }
        private async void onClickSubmit(object sender, RoutedEventArgs e)
        {
            //var userId = ((TextBox)LogicalTreeHelper.FindLogicalNode(this, "userIDTB"))?.Text;
            var title = ((TextBox)LogicalTreeHelper.FindLogicalNode(this, "titleTB"))?.Text;
            var description = ((TextBox)LogicalTreeHelper.FindLogicalNode(this, "descriptionTB"))?.Text;
            var priority = ((TextBox)LogicalTreeHelper.FindLogicalNode(this, "priorityTB"))?.Text;

            var ticket = new
            {
                createdUser = new { userId = 1 }, //int.TryParse(userId, out var id) ? id : 0,
                title = title,
                description = description,
                priority = int.TryParse(priority, out var p) ? p : 0,
            };

            var json = JsonSerializer.Serialize(ticket);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            using var client = new HttpClient();

            try
            {
                var response = await client.PostAsync("http://localhost:8080/ticket/create", content);
                if (response.IsSuccessStatusCode)
                {
                    MessageBox.Show("Ticket submitted successfully!");
                }
                else
                {
                    string responseMessage = await response.Content.ReadAsStringAsync();
                    //MessageBox.Show($"Failed to submit ticket. Status code: {response.StatusCode}");
                    MessageBox.Show($"Status code: {response.StatusCode}\n Message: {responseMessage}");
                }
            }
            catch (HttpRequestException ex)
            {
                MessageBox.Show($"Request failed: {ex.Message}");
            }
        }
    }
}