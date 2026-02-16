package it.unina.poo.gui;

import it.unina.poo.controller.LoginController;
import it.unina.poo.model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginGUI {

    private JPanel mainLoginPanel;
    private JLabel lblTitle;
    private JLabel lblEmail;
    private JLabel lblPassword;
    private JTextField textFieldEmail;
    private JPasswordField passwordField;
    private JPanel btnPanel;
    private JButton btnLogin;
    private JButton btnSignIn;

    private static JFrame loginFrame;

    public static void main(String[] args) {
        loginFrame = new JFrame("Hackathon Login Frame");
        loginFrame.setContentPane(new LoginGUI().mainLoginPanel);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.pack();
        //Visualizza centro dello schermo
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    public LoginGUI(){

        //Apre la finestra per Sign In
        btnSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignInGUI signInGUI = new SignInGUI(loginFrame);
                loginFrame.setVisible(false);
            }
        });

        //Funzione login
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = textFieldEmail.getText().trim();
                String password = String.valueOf(passwordField.getPassword()).trim();

                LoginController loginController = new LoginController();

                if(!loginController.checkEmail(email)){
                    JOptionPane.showMessageDialog(loginFrame, "Email non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!loginController.checkPassword(password)){
                    JOptionPane.showMessageDialog(loginFrame, "Password non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //login
                try {
                    Utente utenteLogin = loginController.login(email, password);
                    if(utenteLogin != null){
                        HomeGUI homeGUI = new HomeGUI(utenteLogin, loginFrame);
                        loginFrame.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(loginFrame, "Utente non trovato", "Errore", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(loginFrame, "Errore durante login, riprova", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
