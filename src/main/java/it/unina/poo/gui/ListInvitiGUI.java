package it.unina.poo.gui;

import it.unina.poo.controller.HomeController;
import it.unina.poo.controller.ListInvitiController;
import it.unina.poo.model.Invito;
import it.unina.poo.model.Utente;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ListInvitiGUI {
    private JPanel mainInvitiPanel;
    private JLabel lblTitle;
    private JScrollPane panelList;
    private JList<Invito> listInviti;

    private final JFrame listInvitiFrame;
    private final ListInvitiController listInvitiController;
    private final HomeController homeController;

    public ListInvitiGUI(JFrame homeFrame, Utente utente, HomeController homeController) {
        listInvitiFrame = new JFrame("Lista Inviti");
        listInvitiFrame.setContentPane(mainInvitiPanel);
        listInvitiFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        listInvitiFrame.setVisible(true);

        this.listInvitiController = new ListInvitiController();
        this.homeController =  homeController;

        //Ritorno home
        listInvitiFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToHome(homeFrame);
            }
        });

        //Get list inviti
        this.getListInviti(utente.getIdUtente());

        //Add evento click
        this.addEventClick(utente.getIdUtente());

        listInvitiFrame.pack();
        listInvitiFrame.setLocationRelativeTo(null);
    }

    private void getListInviti(int idUtente) {
        if(listInvitiController.getListInviti(idUtente)){
            DefaultListModel<Invito> listInvitiModel = new DefaultListModel<>();
            listInvitiModel.addAll(listInvitiController.getListInviti());
            listInviti.setModel(listInvitiModel);
        }else {
            JOptionPane.showMessageDialog(listInvitiFrame, "Errore durante get list inviti", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addEventClick(int idUtente) {
        listInviti.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    Invito invito = listInviti.getSelectedValue();
                    if (invito.getUtenteMittente().getIdUtente() == idUtente){
                        JOptionPane.showMessageDialog(listInvitiFrame, "Non è possibile rispondere invito che hai inviato tu");
                    }else if(!invito.getStato().equals("in attesa")){
                        JOptionPane.showMessageDialog(listInvitiFrame, "Invito già risposto");
                    } else {
                        String rispostaInvito = null;
                        int risp = JOptionPane.showConfirmDialog(listInvitiFrame, "Accetti questo invito?", "Invito", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (risp == JOptionPane.YES_OPTION){
                            rispostaInvito = "accettato";
                        }else if(risp == JOptionPane.NO_OPTION){
                            rispostaInvito = "rifiutato";
                        }else {
                            return;
                        }

                        if(listInvitiController.rispostaInvito(invito.getIdInvito(), rispostaInvito)){
                            JOptionPane.showMessageDialog(listInvitiFrame, "Invito risposto con successo");
                            getListInviti(idUtente);

                            HomeGUI.listHackathonDisp.clear();
                            homeController.getListHackathon("Disponibili", idUtente);
                            HomeGUI.listHackathonDisp.addAll(homeController.getListHackathonDisp());

                            HomeGUI.listHackathonInCorso.clear();
                            homeController.getListHackathon("In Corso", idUtente);
                            HomeGUI.listHackathonInCorso.addAll(homeController.getListHackathonInCorso());
                        }else{
                            JOptionPane.showMessageDialog(listInvitiFrame, "La risposta non inviata.", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    private void returnToHome(JFrame homeFrame) {
        homeFrame.setVisible(true);
        listInvitiFrame.setVisible(false);
        listInvitiFrame.dispose();
    }
}
