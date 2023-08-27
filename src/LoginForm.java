import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class LoginForm extends JDialog {
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnOK;
    private JButton btnCancel;
    private JPanel loginPanel;
    private JButton btnRegistration;

    public LoginForm(JFrame parent){
        super(parent);
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setTitle("Login");
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnOK.addActionListener(e -> {

            String email = tfEmail.getText();

            String password = String.valueOf(pfPassword.getPassword());

             User user = getAuthenticatedUser(email, password);
            if(user != null){
                // user exist
                dispose();
                new InfoPage(null, user);

            } else {
                JOptionPane.showMessageDialog(LoginForm.this,
                        "Email or Password Invalid",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        btnCancel.addActionListener(e -> dispose());

        btnRegistration.addActionListener(e -> {
            //if registration is done correctly we will return valid user
            dispose();
            new RegistrationForm(null);

        });
        setVisible(true);
    }

    private User getAuthenticatedUser(String email, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/newdatabase";
        final String USERNAME = "root";
        final String PASSWORD = "68ezonaT";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM newdatabase WHERE email =? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.address = resultSet.getString("address");
                user.password = resultSet.getString("password");
            }

            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        new LoginForm(null);
    }
}
