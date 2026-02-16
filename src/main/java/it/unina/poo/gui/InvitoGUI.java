package it.unina.poo.gui;

import it.unina.poo.controller.InvitoController;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Team;
import it.unina.poo.model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InvitoGUI {
    private JPanel mainInvitoPanel;
    private JLabel lblTitle;
    private JLabel lblDestinatario;
    private JComboBox<Utente> comboBoxUtente;
    private JLabel lblMotivazione;
    private JTextArea textAreaMotivazione;
    private JButton btnInvio;
    private JButton btnAnnulla;
    private JLabel lblTipo;
    private JTextField textFieldTipo;
    private JLabel lblMittente;
    private JTextField textFieldMittente;

    private boolean checkInvio;

    public InvitoGUI(JFrame frameChiamante, String typeInvito, Utente utenteMitt, Hackathon hackathon, Team team) {
        JFrame invitoFrame = new JFrame("Invito");
        invitoFrame.setContentPane(mainInvitoPanel);
        invitoFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        invitoFrame.setVisible(true);

        InvitoController invitoController = new InvitoController();

        //Inizializza tipo invito
        textFieldTipo.setText(typeInvito);

        //Inizializza nome mittente
        textFieldMittente.setText(utenteMitt.getNome());

        //Inizializza list destinatario
        if(invitoController.getListInvito(hackathon.getTitolo(), typeInvito)){
            DefaultComboBoxModel<Utente> model = new DefaultComboBoxModel<>();
            model.addAll(invitoController.getListInvito());
            comboBoxUtente.setModel(model);
            if(model.getSize() > 0){
                comboBoxUtente.setSelectedIndex(0);
            }
        }else {
            JOptionPane.showMessageDialog(invitoFrame, "Errore durante get list invito", "Errore", JOptionPane.ERROR_MESSAGE);
        }

        //Invio
        btnInvio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String motivazione = textAreaMotivazione.getText();
                String tipoInvito = textFieldTipo.getText();
                Utente utenteDestinatario = (Utente) comboBoxUtente.getSelectedItem();

                if(utenteDestinatario != null){
                    checkInvio = invitoController.invioInvito(motivazione, tipoInvito, utenteMitt, utenteDestinatario, hackathon, team);
                    if(checkInvio){
                        JOptionPane.showMessageDialog(invitoFrame, "Invito inviato!", "Invito", JOptionPane.INFORMATION_MESSAGE);
                        returnToChiamante(frameChiamante, invitoFrame);
                    }else {
                        JOptionPane.showMessageDialog(invitoFrame, "Errore durante invio, ripova(Attenzione sul massimo persone per team)", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(invitoFrame, "Scegli un destinatario", "Invito", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Ritorno indietro
        invitoFrame.addWindowListener(new  WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToChiamante(frameChiamante, invitoFrame);
            }
        });

        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToChiamante(frameChiamante, invitoFrame);
            }
        });

        invitoFrame.pack();
        invitoFrame.setLocationRelativeTo(null);
    }

    private void returnToChiamante(JFrame frameChiamante, JFrame invitoFrame) {
        if(checkInvio){
            frameChiamante.setVisible(true);
            invitoFrame.setVisible(false);
            invitoFrame.dispose();
        }else{
            int rls = JOptionPane.showConfirmDialog(invitoFrame, "Invito non inviato, conferma il ritorno?","Invito", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (rls == JOptionPane.YES_OPTION){
                frameChiamante.setVisible(true);
                invitoFrame.setVisible(false);
                invitoFrame.dispose();
            }
        }
    }
}
