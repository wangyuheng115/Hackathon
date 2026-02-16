package it.unina.poo.gui;

import it.unina.poo.controller.TeamController;
import it.unina.poo.model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TeamGUI {
    private JPanel mainTeamPanel;
    private JLabel lblTitle;
    private JButton btnBack;
    private JScrollPane panelListMembri;
    private JList<TeamPartecipante> listMembri;
    private JButton btnInvito;
    private JButton btnDocumento;
    private JList<Documento> listDocumento;
    private JScrollPane panelListDocumento;
    private JLabel lblListMembri;
    private JLabel lblListDocumenti;
    private JButton btnEditDocumento;
    private JButton btnDeleteDocumento;

    private final JFrame teamFrame;
    private final Team team;
    private final Utente utente;
    private final Hackathon hackathon;
    private final TeamController teamController;
    private DefaultListModel<Documento> listModelDocument = new DefaultListModel<>();

    public TeamGUI(JFrame hackathonFrame, Team team, Utente utente, Hackathon hackathon){
        teamFrame = new JFrame("Team");
        teamFrame.setContentPane(mainTeamPanel);
        teamFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        teamFrame.setVisible(true);

        this.team = team;
        this.utente = utente;
        this.hackathon = hackathon;

        this.teamController = new TeamController();

        //Inizializza nome team
        lblTitle.setText("Team: " + team.getNome());

        //Inizializza lista membri team
        this.initListMembri();

        //Inizializza lista documenti team
        this.initListDocument();

        //Aggiunge evento per invito
        this.addEventBtnInvito();

        //Aggiunge evento per new documento
        this.addEventBtnDocumento();

        this.controlloBtnEditDelete();

        this.addEventBtnEdit();

        this.addEventBtnDelete();

        //Bottone ritorno
        teamFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToHackathon(hackathonFrame);
            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToHackathon(hackathonFrame);
            }
        });

        teamFrame.pack();
        teamFrame.setLocationRelativeTo(null);
    }

    private void initListMembri(){
        if(teamController.getListMembriTeam(team.getIdTeam())){
            DefaultListModel<TeamPartecipante> listModelMembri = new DefaultListModel<>();
            listModelMembri.addAll(teamController.getListTeamPartecipante());
            listMembri.setModel(listModelMembri);
        }else {
            JOptionPane.showMessageDialog(teamFrame, "Errore durante inizialiazzione list mebri", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initListDocument(){
        if(teamController.getListDocumentiTeam(team.getIdTeam())){
            listModelDocument.clear();
            listModelDocument.addAll(teamController.getListDocumento());
            listDocumento.setModel(listModelDocument);
        }else {
            JOptionPane.showMessageDialog(teamFrame, "Errore durante inizializzazione list documnti", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addEventBtnInvito(){
        btnInvito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InvitoGUI invitoGUI = new InvitoGUI(teamFrame, "team", utente, hackathon, team);
                teamFrame.setVisible(false);
            }
        });
    }

    private void addEventBtnDocumento(){
        btnDocumento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea textAreaDocumento = new JTextArea(10,50);
                JScrollPane scrollPaneDocumento = new JScrollPane(textAreaDocumento);

                int rls = JOptionPane.showConfirmDialog(teamFrame, scrollPaneDocumento, "Documento", JOptionPane.OK_CANCEL_OPTION);
                if(rls == JOptionPane.OK_OPTION){

                    if(teamController.creaDocumento(team, textAreaDocumento.getText())){
                        JOptionPane.showMessageDialog(teamFrame, "Documento aggiunto!", "Documento", JOptionPane.INFORMATION_MESSAGE);
                        initListDocument();
                    }else {
                        JOptionPane.showMessageDialog(teamFrame, "Errore durante la creazione del nuovo documento", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void controlloBtnEditDelete(){
        btnEditDocumento.setEnabled(false);
        btnDeleteDocumento.setEnabled(false);

        listDocumento.addListSelectionListener(new  ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(listDocumento.getSelectedValue() != null){
                    btnEditDocumento.setEnabled(true);
                    btnDeleteDocumento.setEnabled(true);
                }else {
                    btnEditDocumento.setEnabled(false);
                    btnDeleteDocumento.setEnabled(false);
                }
            }
        });
    }

    private void addEventBtnEdit(){
        btnEditDocumento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea textAreaDocumento = new JTextArea(10,50);
                textAreaDocumento.setText(listDocumento.getSelectedValue().getDocumento());
                JScrollPane scrollPaneDocumento = new JScrollPane(textAreaDocumento);

                int rls = JOptionPane.showConfirmDialog(teamFrame, scrollPaneDocumento, "Modifica Documento", JOptionPane.OK_CANCEL_OPTION);
                if(rls == JOptionPane.OK_OPTION){

                    if(teamController.modificaDocumento(textAreaDocumento.getText(), listDocumento.getSelectedValue().getIdDocumento())){
                        JOptionPane.showMessageDialog(teamFrame, "Documento modificato!", "Documento", JOptionPane.INFORMATION_MESSAGE);
                        initListDocument();
                    }else {
                        JOptionPane.showMessageDialog(teamFrame, "Errore durante la modificazione del documento", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void addEventBtnDelete(){
        btnDeleteDocumento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rls = JOptionPane.showConfirmDialog(teamFrame, "Eliminare il documento?", "Eliminazione", JOptionPane.YES_NO_OPTION);
                if(rls == JOptionPane.YES_OPTION){
                    if(teamController.eliminaDocumento(listDocumento.getSelectedValue().getIdDocumento())){
                        initListDocument();
                    }else {
                        JOptionPane.showMessageDialog(teamFrame, "Errore durante la eliminazione del documento", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void returnToHackathon(JFrame hackathonFrame) {
        hackathonFrame.setVisible(true);
        teamFrame.setVisible(false);
        teamFrame.dispose();
    }
}
