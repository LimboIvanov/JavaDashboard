import javax.swing.*;
import java.awt.*;

public class InfoPage extends JDialog {
    private JPanel InfoPage;
    private JLabel fnName;
    private JLabel fnEmail;
    private JLabel fnPhone;
    private JLabel fnAddress;
    private JButton btnBack;


    public InfoPage(JFrame parent, User user) {
        super(parent);
        setContentPane(InfoPage);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setTitle("Information about user");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        fnName.setText(user.name);
        fnEmail.setText(user.email);
        fnPhone.setText(user.phone);
        fnAddress.setText(user.address);
        btnBack.addActionListener(e -> {
            dispose();
            new LoginForm(null);
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new InfoPage(null, null);
    }
}

