package it.unina.poo.gui;

import it.unina.poo.controller.ListProblemaController;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Problema;
import it.unina.poo.model.Utente;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListProblemaGUI {
    private JPanel mainListProblemPanel;
    private JLabel lblTitle;
    private JScrollPane panelList;
    private JList<Problema> listProblema;
    private JButton btnNewProblem;
    private JButton btnDeleteProblem;
    private JButton btnEditProblem;

    private final JFrame listProblemaFrame;
    private final ListProblemaController listProblemaController;
    private DefaultListModel<Problema> model = new DefaultListModel<>();

    public ListProblemaGUI(JFrame hackathonFrame, Hackathon hackathon, Utente utente, String ruolo) {
        listProblemaFrame = new JFrame("List Problema");
        listProblemaFrame.setContentPane(mainListProblemPanel);
        listProblemaFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        listProblemaFrame.setVisible(true);

        listProblemaController = new ListProblemaController();

        //Inizializza lista problema
        this.initListProblem(hackathon.getTitolo());

        //Controllo bottone per ruolo
        if(!ruolo.equals("giudice")){
            btnNewProblem.setVisible(false);
            btnEditProblem.setVisible(false);
            btnDeleteProblem.setVisible(false);
        }
        btnEditProblem.setEnabled(false);
        btnDeleteProblem.setEnabled(false);

        //Funzione crea new problema
        this.creaNewProblema(utente, hackathon);

        //Verifica bottoni edit e delete
        this.controlloBottoniEditDelete(utente);

        //Aggiunge evento per bottone edit
        this.addEventBtnEdit(utente, hackathon);

        //Aggiunge evento per bottone delete
        this.addEventBtnDelete(utente, hackathon);

        //Torna a frame hackathon
        listProblemaFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToHackathon(hackathonFrame);
            }
        });

        listProblemaFrame.pack();
        listProblemaFrame.setLocationRelativeTo(null);
    }

    private void initListProblem(String titoloHackathon){

        if(listProblemaController.getProblemaLista(titoloHackathon)){
            model.clear();
            model.addAll(listProblemaController.getListaProblema());
            listProblema.setModel(model);
        }else {

            JOptionPane.showMessageDialog(listProblemaFrame, "Errore durante get list problema", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void creaNewProblema(Utente utente, Hackathon hackathon){
        btnNewProblem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea textAreaDescrizione = new JTextArea(10,50);
                JScrollPane scrollPane = new JScrollPane(textAreaDescrizione);

                int rls = JOptionPane.showConfirmDialog(listProblemaFrame, scrollPane, "Creare il problema",JOptionPane.YES_NO_OPTION);
                if(rls == JOptionPane.YES_OPTION){
                    if(listProblemaController.creaProblema(textAreaDescrizione.getText(), utente, hackathon)){
                        listProblemaController.getProblemaLista(hackathon.getTitolo());
                        model.clear();
                        model.addAll(listProblemaController.getListaProblema());
                    }else {
                        JOptionPane.showMessageDialog(listProblemaFrame, "Il nuovo problema non pubblicata(verifica data inizio hackathon)", "Errore", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
    }

    private void controlloBottoniEditDelete(Utente utente){
        listProblema.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(listProblema.getSelectedValue() != null && listProblema.getSelectedValue().getUtente().getIdUtente() == utente.getIdUtente()){
                    btnEditProblem.setEnabled(true);
                    btnDeleteProblem.setEnabled(true);
                }else {
                    btnEditProblem.setEnabled(false);
                    btnDeleteProblem.setEnabled(false);
                }
            }
        });
    }

    private void addEventBtnEdit(Utente utente, Hackathon hackathon){
        btnEditProblem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea textAreaDescrizione = new JTextArea(10,50);
                textAreaDescrizione.setText(listProblema.getSelectedValue().getDescrizioneProblema());
                JScrollPane scrollPane = new JScrollPane(textAreaDescrizione);

                int rls = JOptionPane.showConfirmDialog(listProblemaFrame, scrollPane, "Modifica il problema",JOptionPane.YES_NO_OPTION);
                if(rls == JOptionPane.YES_OPTION){
                    if(listProblemaController.modificaProblema(textAreaDescrizione.getText(), utente, hackathon)){
                        listProblemaController.getProblemaLista(hackathon.getTitolo());
                        model.clear();
                        model.addAll(listProblemaController.getListaProblema());
                    }else {
                        JOptionPane.showMessageDialog(listProblemaFrame, "La modificazione è fallita", "Errore", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
    }

    private void addEventBtnDelete(Utente utente, Hackathon hackathon){
        btnDeleteProblem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rls = JOptionPane.showConfirmDialog(listProblemaFrame, "Elimina il problema?", "Eliminazione",JOptionPane.YES_NO_OPTION);
                if(rls == JOptionPane.YES_OPTION){
                    if(!listProblemaController.eliminaProblema(utente, hackathon)){
                        JOptionPane.showMessageDialog(listProblemaFrame, "Errore durante la eliminazione", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                    listProblemaController.getProblemaLista(hackathon.getTitolo());
                    model.clear();
                    model.addAll(listProblemaController.getListaProblema());
                }
            }
        });
    }

    private void returnToHackathon(JFrame hackathonFrame) {
        hackathonFrame.setVisible(true);
        listProblemaFrame.setVisible(false);
        listProblemaFrame.dispose();
    }
}
