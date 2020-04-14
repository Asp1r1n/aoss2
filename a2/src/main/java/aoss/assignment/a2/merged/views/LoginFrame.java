package aoss.assignment.a2.merged.views;

import aoss.assignment.a2.merged.controllers.auth.AuthController;
import aoss.assignment.a2.merged.models.UserSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JButton submitButton;
    private JLabel lbMessage;
    private JTextField tfServerAddress;

    private UserSession session;
    private AuthController authController;

    public LoginFrame() {
        add(mainPanel);
        setTitle("Login Frame");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 350);

        initController();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                postLogin();
            }
        });
    }

    private void initController() {
        authController = new AuthController();
    }

    private boolean postLogin() {
        lbMessage.setVisible(false);

        if (tfServerAddress.getText().isEmpty()) {
            lbMessage.setText("Server address should not be empty");
            lbMessage.setVisible(true);
            return false;
        }

        if (tfUsername.getText().isEmpty() || tfPassword.getText().isEmpty()) {
            lbMessage.setText("Username and Password should not be empty");
            lbMessage.setVisible(true);
            return false;
        }

        String username = tfUsername.getText();
        String password = tfPassword.getText();
        authController.setServerAddress(tfServerAddress.getText());
        session = authController.postToken(username, password);
        if (session != null) {
            java.awt.EventQueue.invokeLater(() -> new InventoryManagerFrame(session).setVisible(true));
            this.dispose();
        } else {
            lbMessage.setText("Invalid username and password");
            lbMessage.setVisible(true);
            return false;
        }

        return true;
    }
}
