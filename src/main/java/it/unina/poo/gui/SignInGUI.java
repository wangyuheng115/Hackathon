package it.unina.poo.gui;

import it.unina.poo.controller.SignInController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SignInGUI {
    private JPanel mainSignInPanel;
    private JLabel lblTitle;
    private JLabel lblNome;
    private JTextField textFieldNome;
    private JLabel lblEmail;
    private JTextField textFieldEmail;
    private JLabel lblPassword;
    private JPasswordField passwordField;
    private JPanel btnPanel;
    private JButton btnSignIn;
    private JButton btnRitorno;
    private Boolean checkSignIn = false;

    public SignInGUI(JFrame loginFrame) {
        JFrame signInFrame = new JFrame("Hackathon Sign In Frame");
        signInFrame.setContentPane(this.mainSignInPanel);
        signInFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        signInFrame.pack();
        signInFrame.setLocationRelativeTo(null);
        signInFrame.setVisible(true);

        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignInController utenteController = new SignInController();
                String nome = textFieldNome.getText().trim();
                String email = textFieldEmail.getText().trim();
                String password = String.valueOf(passwordField.getPassword()).trim();

                if(!utenteController.checkNome(nome)){
                    JOptionPane.showMessageDialog(signInFrame, "Nome utente non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(!utenteController.checkEmail(email)){
                    JOptionPane.showMessageDialog(signInFrame, "Email utente non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(!utenteController.checkPassword(password)){
                    JOptionPane.showMessageDialog(signInFrame, "Password utente non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                checkSignIn = utenteController.signInUtente(nome, email, password);

                if(checkSignIn){
                    JOptionPane.showMessageDialog(signInFrame, "Registrazione successa, ritorna per login", "Sign In", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(signInFrame, "Registrazione fallita, riprova", "Sign In", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signInFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToLogin(loginFrame,signInFrame);
            }
        });

        btnRitorno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnToLogin(loginFrame,signInFrame);
            }
        });
    }

    private void returnToLogin(JFrame loginFrame,JFrame signInFrame) {
        int rls = -1;
        if(Boolean.FALSE.equals(checkSignIn)){
            rls = JOptionPane.showConfirmDialog(signInFrame, "Utente non registrato, conferma il ritorno?","Sign In", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        }

        if (Boolean.TRUE.equals(checkSignIn) || rls == JOptionPane.YES_OPTION) {
            loginFrame.setVisible(true);
            signInFrame.setVisible(false);
            signInFrame.dispose();
        }
    }
}
