package it.unina.poo.gui;

import it.unina.poo.controller.TeamsController;
import it.unina.poo.model.Documento;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Team;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TeamsGUI {
    private JPanel mainTeamsPanel;
    private JLabel lblTitle;
    private JScrollPane scrollPaneTeams;
    private JScrollPane scrollPaneDocumenti;
    private JLabel lblTeams;
    private JLabel lblDocumenti;
    private JList<Team> listTeams;
    private JList<Documento> listDocumenti;

    private final JFrame teamsFrame;
    private final Hackathon hackathon;
    private final TeamsController teamsController;

    public TeamsGUI(JFrame hackathonFrame, Hackathon hackathon) {
        teamsFrame = new JFrame("Teams e Documenti");
        teamsFrame.setContentPane(mainTeamsPanel);
        teamsFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        teamsFrame.setVisible(true);

        this.hackathon = hackathon;
        this.teamsController = new TeamsController();

        //Get list teams
        this.getListTeams();

        //Aggiungere evento per list team
        this.addEventListTeams();

        //Ritorno a hackathon page
        teamsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToHackathon(hackathonFrame);
            }
        });

        teamsFrame.pack();
        teamsFrame.setLocationRelativeTo(null);
    }

    private void getListTeams(){
        if(teamsController.getListTeams(hackathon.getTitolo())){
            DefaultListModel<Team> listModelTeams = new DefaultListModel<>();
            listModelTeams.addAll(teamsController.getTeams());
            listTeams.setModel(listModelTeams);
        }else {
            JOptionPane.showMessageDialog(teamsFrame, "Errore get list teams", "Erroe", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addEventListTeams(){
        listTeams.addListSelectionListener(new  ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(teamsController.getListDocumenti(listTeams.getSelectedValue().getIdTeam())){
                    DefaultListModel<Documento> listModelDocumenti = new DefaultListModel<>();
                    listModelDocumenti.addAll(teamsController.getDocumentoList());
                    listDocumenti.setModel(listModelDocumenti);
                }
            }
        });
    }

    private void returnToHackathon(JFrame hackathonFrame){
        hackathonFrame.setVisible(true);
        teamsFrame.dispose();
        teamsFrame.setVisible(false);
    }
}
