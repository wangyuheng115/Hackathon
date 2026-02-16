package it.unina.poo.gui;

import it.unina.poo.controller.HackathonController;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Partecipazione;
import it.unina.poo.model.Team;
import it.unina.poo.model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HackathonGUI {
    private JPanel mainHackathonPanel;
    private JLabel lblTitle;
    private JLabel lblUtente;
    private JButton btnBackHome;
    private JButton btnInviti;
    private JButton btnPubblicaProblema;
    private JEditorPane editorPaneInfoHackathon;
    private JScrollPane scrollPanelPartecipazioni;
    private JList<Partecipazione> listPartecipazioni;
    private JLabel lblInfo;
    private JLabel lblMembri;
    private JPanel panelBtn;
    private JButton btnListTeam;

    private final JFrame hackathonFrame;
    private final HackathonController hackathonController;
    private Team team;
    private String ruolo;
    private final Hackathon hackathon;
    private final Utente utente;

    public HackathonGUI(JFrame homeFrame,Hackathon hackathon, Utente utente) {
        hackathonFrame = new JFrame("Hackathon");
        hackathonFrame.setContentPane(mainHackathonPanel);
        hackathonFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        hackathonFrame.setVisible(true);

        hackathonController = new HackathonController();
        this.hackathon = hackathon;
        this.utente = utente;

        //Ritorno home
        hackathonFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                returnToHome(homeFrame, hackathonFrame);
            }
        });

        btnBackHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnToHome(homeFrame, hackathonFrame);
            }
        });

        //Inizializza titolo
        lblTitle.setText(hackathon.getTitolo());

        //Get utente e ruolo
        this.getRuoloUtente();

        //Get team
        this.getTeam();

        //Inizializza info hackathon
        editorPaneInfoHackathon.setText(hackathon.toString());

        //Inizializza list membri
        this.initListMembri();

        //Visualizza diverse bottone
        this.controllBottoni();

        hackathonFrame.pack();
        hackathonFrame.setLocationRelativeTo(null);
    }

    private void getRuoloUtente(){
        ruolo = hackathonController.getRuoloUtente(hackathon.getTitolo(), utente.getIdUtente());
        if (ruolo.isEmpty()) {
            JOptionPane.showMessageDialog(hackathonFrame, "Ruolo non trovato.", "Errore Ruolo", JOptionPane.ERROR_MESSAGE);
        }
        lblUtente.setText("Ciao, "+utente.getNome()+" ["+ ruolo +"]");
    }

    private void getTeam(){
        if(ruolo.equals("partecipante")){
            team = hackathonController.getTeam(utente.getIdUtente(), hackathon.getTitolo());
        } else {
            team = null;
        }
    }

    private void initListMembri(){
        if(hackathonController.getListaPartecipazioni(hackathon.getTitolo())){
            DefaultListModel<Partecipazione> listaPartecipazioni = new DefaultListModel<>();
            listaPartecipazioni.addAll(hackathonController.getListaPartecipazioni());
            listPartecipazioni.setModel(listaPartecipazioni);
        }else{
            JOptionPane.showMessageDialog(hackathonFrame, "Errore durante get list partecipazione", "Errore Get List", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void controllBottoni(){
        if (ruolo.equals("organizzatore")){
            btnInviti.setText("Invita Giudice");
        }else if(ruolo.equals("partecipante")){
            if (team == null) {
                btnInviti.setText("Forma Team");
            }else {
                btnInviti.setText("My Team");
            }

            btnListTeam.setVisible(false);
        }else{
            btnInviti.setVisible(false);
        }

        btnInviti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ruolo.equals("organizzatore")){
                    InvitoGUI invitoGUI = new InvitoGUI(hackathonFrame, "giudice", utente, hackathon, null);
                    hackathonFrame.setVisible(false);
                }

                if(ruolo.equals("partecipante")){
                    if(team == null){
                        String nomeTeam = JOptionPane.showInputDialog(hackathonFrame, "Nome Team: ", "Formare Team", JOptionPane.QUESTION_MESSAGE);
                        if(nomeTeam != null && !nomeTeam.trim().isEmpty()){
                            if(hackathonController.creaTeam(nomeTeam, hackathon.getTitolo(), utente.getIdUtente())){
                                JOptionPane.showMessageDialog(hackathonFrame, "Team creata");
                                team = hackathonController.getTeam(utente.getIdUtente(), hackathon.getTitolo());
                                if(team != null){
                                    btnInviti.setText("My Team");
                                }
                            }else {
                                JOptionPane.showMessageDialog(hackathonFrame, "Team non creata, riprova");
                            }
                        }else if (nomeTeam != null){
                            JOptionPane.showMessageDialog(hackathonFrame, "Nome team non valido", "Errore Nome Team", JOptionPane.ERROR_MESSAGE);
                        }
                    }else {
                        TeamGUI teamGUI = new TeamGUI(hackathonFrame, team, utente, hackathon);
                        hackathonFrame.setVisible(false);
                    }
                }
            }
        });

        btnPubblicaProblema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListProblemaGUI listProblemaGUI = new ListProblemaGUI(hackathonFrame, hackathon, utente, ruolo);
                hackathonFrame.setVisible(false);
            }
        });

        btnListTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeamsGUI teamsGUI = new TeamsGUI(hackathonFrame, hackathon);
                hackathonFrame.setVisible(false);
            }
        });

    }

    private void returnToHome(JFrame homeFrame, JFrame hackathonFrame) {
        homeFrame.setVisible(true);
        hackathonFrame.setVisible(false);
        hackathonFrame.dispose();
    }
}
