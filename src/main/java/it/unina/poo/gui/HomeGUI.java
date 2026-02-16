package it.unina.poo.gui;

import it.unina.poo.controller.HomeController;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeGUI {
    private JPanel mainHomePanel;
    private JLabel lblTitle;
    private JLabel lblDisponibileList;
    private JLabel lblInCorsoList;
    private JLabel lblChiusoList;
    private JScrollPane dispPanel;
    private JScrollPane inCorPanel;
    private JScrollPane chiusoPanel;
    private JLabel lblUtente;
    private JList<Hackathon> listDisponibile;
    private JList<Hackathon> listInCorso;
    private JList<Hackathon> listChiuso;
    private JButton btnLogout;
    private JButton btnNewHackathon;
    private JButton btnInviti;
    private JButton btnModificaNome;
    private JButton btnModificaEmail;
    private JButton btnModificaPassword;
    private final Utente utenteLogin;
    private final HomeController homeController;
    private final JFrame homeFrame;

    public static final DefaultListModel<Hackathon> listHackathonDisp = new DefaultListModel<>();
    public static final DefaultListModel<Hackathon> listHackathonInCorso = new DefaultListModel<>();
    public static final DefaultListModel<Hackathon> listHackathonChiuso = new DefaultListModel<>();

    public HomeGUI(Utente utente, JFrame loginFrame) {
        this.utenteLogin = utente;
        this.homeController = new HomeController();

        homeFrame = new JFrame("Hackathon Home");
        homeFrame.setContentPane(this.mainHomePanel);
        homeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        homeFrame.setPreferredSize(new Dimension(800, 600));
        homeFrame.setVisible(true);

        //Set nome utente alto destra
        lblUtente.setText("Ciao, "+utente.getNome());

        //Inizializza le list
        this.initListHackathon();

        //Logout
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(true);
                homeFrame.setVisible(false);
                homeFrame.dispose();
            }
        });

        //New Hackathon
        this.addEventBtnNewHackathon();

        //Apre pagina hackathon in corso
        this.addEventListInCorso();

        //Partecipa hackathon disponibile
        this.addEventListDisp();

        //Apre pagina classifica team per hackathon chiusi
        this.addEventListChiusi();

        //Aggiunge event per bottone inviti
        this.addEventBtnInviti();

        //Bottone modifica nome
        this.addEventBtnModificaNome();

        //Bottone modifica email
        this.addEventBtnModificaEmail();

        //Bottone modifica password
        this.addEventBtnModificaPassword();

        homeFrame.pack();
        homeFrame.setLocationRelativeTo(null);
    }

    private void initListHackathon(){
        if(!homeController.getListHackathon("Disponibili", utenteLogin.getIdUtente())){
            JOptionPane.showMessageDialog(homeFrame, "Errore durante get list hackathon disponibili.", "Errore Hackathon Disponibile", JOptionPane.ERROR_MESSAGE);
        }
        listHackathonDisp.clear();
        listHackathonDisp.addAll(homeController.getListHackathonDisp());
        listDisponibile.setModel(listHackathonDisp);


        if(!homeController.getListHackathon("In Corso", utenteLogin.getIdUtente())){
            JOptionPane.showMessageDialog(homeFrame, "Errore durante get list hackathon in corso.", "Errore Hackathon in corso", JOptionPane.ERROR_MESSAGE);
        }
        listHackathonInCorso.clear();
        listHackathonInCorso.addAll(homeController.getListHackathonInCorso());
        listInCorso.setModel(listHackathonInCorso);


        if(!homeController.getListHackathon("Chiusi", utenteLogin.getIdUtente())){
            JOptionPane.showMessageDialog(homeFrame, "Errore durante get list hackathon chiusi.", "Errore Hackathon chiusi", JOptionPane.ERROR_MESSAGE);
        }
        listHackathonChiuso.clear();
        listHackathonChiuso.addAll(homeController.getListHackathonChiuso());
        listChiuso.setModel(listHackathonChiuso);
    }

    private void addEventBtnNewHackathon(){
        btnNewHackathon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewHackathonGUI newHackathonGUI = new NewHackathonGUI(homeFrame, utenteLogin, homeController);
                homeFrame.setVisible(false);
            }
        });
    }

    private void addEventListInCorso(){
        listInCorso.addMouseListener(new  MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int index = listInCorso.getSelectedIndex();
                    if(index != -1){
                        Hackathon hackathon = listInCorso.getModel().getElementAt(index);
                        HackathonGUI hackathonGUI = new HackathonGUI(homeFrame, hackathon, utenteLogin);
                        homeFrame.setVisible(false);
                    }
                }
            }
        });
    }

    private void addEventListDisp(){
        listDisponibile.addMouseListener(new  MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int index = listDisponibile.getSelectedIndex();
                    if(index != -1){
                        int rls = JOptionPane.showConfirmDialog(homeFrame, "Partecipa alla hackathon scelto?", "Partecipazione", JOptionPane.YES_NO_OPTION);
                        if(rls == JOptionPane.YES_OPTION){
                            if(homeController.partecipaHacakthon(utenteLogin.getIdUtente(), listDisponibile.getSelectedValue().getTitolo())){
                                JOptionPane.showMessageDialog(homeFrame, "Partecipazione successa", "Partecipazione",  JOptionPane.INFORMATION_MESSAGE);
                                listHackathonDisp.clear();
                                homeController.getListHackathon("Disponibili", utenteLogin.getIdUtente());
                                listHackathonDisp.addAll(homeController.getListHackathonDisp());

                                listHackathonInCorso.clear();
                                homeController.getListHackathon("In Corso", utenteLogin.getIdUtente());
                                listHackathonInCorso.addAll(homeController.getListHackathonInCorso());
                            }
                            else {
                                JOptionPane.showMessageDialog(homeFrame, "Partecipazione fallita, riprova", "Errore",  JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });
    }

    private void addEventListChiusi(){
        listChiuso.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int index = listChiuso.getSelectedIndex();
                    if(index != -1){
                        Hackathon hackathon = listChiuso.getModel().getElementAt(index);
                        ClassificaGUI classificaGUI = new ClassificaGUI(homeFrame, hackathon, utenteLogin);
                        homeFrame.setVisible(false);
                    }
                }
            }
        });
    }

    private void addEventBtnInviti(){
        btnInviti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListInvitiGUI listInvitiGUI = new ListInvitiGUI(homeFrame, utenteLogin, homeController);
                homeFrame.setVisible(false);
            }
        });
    }

    private void addEventBtnModificaNome(){
        btnModificaNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newNome = JOptionPane.showInputDialog(homeFrame, "Nuovo nome: ");

                if(newNome != null){
                    if(!newNome.trim().isEmpty()){
                        if(homeController.modificaNomeUtente(newNome, utenteLogin.getIdUtente())){
                            JOptionPane.showMessageDialog(homeFrame, "Nome modificato con successo", "Modificazione del nome", JOptionPane.INFORMATION_MESSAGE);
                            lblUtente.setText("Ciao, " + newNome);
                        }else{
                            JOptionPane.showMessageDialog(homeFrame, "Errore durante la modificazione del nome", "Errore", JOptionPane.ERROR_MESSAGE);
                        }

                    }else{
                        JOptionPane.showMessageDialog(homeFrame, "Nome non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void addEventBtnModificaEmail(){
        btnModificaEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newEmail = JOptionPane.showInputDialog(homeFrame, "Nuovo email: ");

                if(newEmail != null){
                    if(!newEmail.trim().isEmpty()){
                        if(homeController.modificaEmailUtente(newEmail, utenteLogin.getIdUtente())){
                            JOptionPane.showMessageDialog(homeFrame, "Email modificato con successo", "Modificazione del email", JOptionPane.INFORMATION_MESSAGE);
                        }else {
                            JOptionPane.showMessageDialog(homeFrame, "Errore durante la modificazione del email", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(homeFrame, "Email non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void addEventBtnModificaPassword(){
        btnModificaPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPassword = JOptionPane.showInputDialog(homeFrame, "Nuovo password: ");

                if(newPassword != null){
                    if (!newPassword.trim().isEmpty()){
                        if(homeController.modificaPasswordUtente(newPassword, utenteLogin.getIdUtente())){
                            JOptionPane.showMessageDialog(homeFrame, "Password modificato con successo", "Modificazione del password", JOptionPane.INFORMATION_MESSAGE);
                        }else {
                            JOptionPane.showMessageDialog(homeFrame, "Errore durante la modificazione del password", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }else {
                        JOptionPane.showMessageDialog(homeFrame, "Password non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
