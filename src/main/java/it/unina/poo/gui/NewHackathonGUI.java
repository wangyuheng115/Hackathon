package it.unina.poo.gui;

import com.toedter.calendar.JDateChooser;
import it.unina.poo.controller.HomeController;
import it.unina.poo.controller.NewHackathonController;
import it.unina.poo.model.Sede;
import it.unina.poo.model.Utente;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NewHackathonGUI {
    private JPanel mainNewHackathonPanel;
    private JLabel lblTitle;
    private JLabel lblOrganizzatore;
    private JTextField textFieldOrganizzatore;
    private JTextField textFieldTitolo;
    private JLabel lblTitolo;
    private JLabel lblInizioHackathon;
    private JLabel lblMaxIscritti;
    private JSpinner spinnerMaxIscritti;
    private JLabel lblMaxPerTeam;
    private JSpinner spinnerMaxPerTeam;
    private JLabel lblInizioIscrizione;
    private JLabel lblSede;
    private JComboBox<Sede> comboBoxSede;
    private JButton btnNewSede;
    private JButton btnConferma;
    private JButton btnAnnulla;
    private JPanel panelDateInizio;
    private JPanel panelDateIscrizione;
    private boolean checkConferma = false;
    private final JFrame newHackathonFrame;
    private final NewHackathonController newHackathonController;

    public static DefaultComboBoxModel<Sede> comboBoxSedeModel;

    public NewHackathonGUI(JFrame homeFrame, Utente utente, HomeController homeController) {
        newHackathonFrame = new JFrame("New Hackathon");
        newHackathonFrame.setContentPane(mainNewHackathonPanel);
        newHackathonFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        newHackathonFrame.setVisible(true);

        newHackathonController = new NewHackathonController();

        JDateChooser dateChooserInizio = new JDateChooser();
        dateChooserInizio.setLocale(Locale.ITALY);
        JDateChooser dateChooserIscrizione = new JDateChooser();
        dateChooserIscrizione.setLocale(Locale.ITALY);
        panelDateInizio.setLayout(new BorderLayout());
        panelDateIscrizione.setLayout(new BorderLayout());
        panelDateInizio.add(dateChooserInizio, BorderLayout.CENTER);
        panelDateIscrizione.add(dateChooserIscrizione, BorderLayout.CENTER);

        //Inizializza organizzatore
        textFieldOrganizzatore.setText(utente.getNome());

        //Inizializza combobox sede
        if(!newHackathonController.getListSede()){
            JOptionPane.showMessageDialog(newHackathonFrame,"Errore durante get list sede", "Errore",JOptionPane.ERROR_MESSAGE);
        }
        comboBoxSedeModel = new DefaultComboBoxModel<>();
        comboBoxSedeModel.addAll(newHackathonController.getArrayListSede());
        comboBoxSede.setModel(comboBoxSedeModel);
        comboBoxSede.setSelectedIndex(0);

        //Ritorno indietro
        newHackathonFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToHome(homeFrame, newHackathonFrame);
            }
        });

        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToHome(homeFrame, newHackathonFrame);
            }
        });

        //New sede
        btnNewSede.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSedeGUI newSedeGUI = new NewSedeGUI(newHackathonFrame, newHackathonController);

                newHackathonFrame.setVisible(false);
            }
        });

        //Conferma nuovo hackathon
        btnConferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titolo = textFieldTitolo.getText().trim();
                int maxIscritti = (int) spinnerMaxIscritti.getValue();
                int maxPerTeam = (int) spinnerMaxPerTeam.getValue();
                Sede sede = (Sede) comboBoxSede.getSelectedItem();

                if (titolo.isEmpty() || maxIscritti <= 0 || maxPerTeam <= 0 || sede == null) {
                    JOptionPane.showMessageDialog(newHackathonFrame, "Tutti i campi sono obbligatori", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Date dateInizioHackathon = dateChooserInizio.getDate();
                Date dateInizioIscrizione = dateChooserIscrizione.getDate();
                LocalDate inizio = null;
                LocalDate inizioIscrizione = null;

                if(dateInizioHackathon != null && dateInizioIscrizione != null) {
                    inizio = dateInizioHackathon.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    inizioIscrizione = dateInizioIscrizione.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    //Inizio iscrizione deve essere almeno 3 giorni prima di inizio hackathon
                    if(!inizioIscrizione.isBefore(inizio.minusDays(2))) {
                        JOptionPane.showMessageDialog(newHackathonFrame, "Inizio iscrizione deve essere almeno 3 giorni prima di inizio hackathon", "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }else {
                    JOptionPane.showMessageDialog(newHackathonFrame, "Inserisci le date", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (newHackathonController.creaHackathon(titolo, inizio, maxIscritti, maxPerTeam, inizioIscrizione, sede, utente.getIdUtente())) {
                    JOptionPane.showMessageDialog(newHackathonFrame, "Hackathon creata", "Informazione", JOptionPane.INFORMATION_MESSAGE);
                    checkConferma = true;
                    HomeGUI.listHackathonInCorso.clear();
                    homeController.getListHackathon("In Corso", utente.getIdUtente());
                    HomeGUI.listHackathonInCorso.addAll(homeController.getListHackathonInCorso());

                    returnToHome(homeFrame,newHackathonFrame);
                } else {
                    checkConferma = false;
                    JOptionPane.showMessageDialog(newHackathonFrame, "Errore durante creazione del Hackathon, riprova", "Errore", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        newHackathonFrame.pack();
        newHackathonFrame.setLocationRelativeTo(null);
    }

    private void returnToHome(JFrame homeFrame, JFrame newHackathonFrame) {

        if (!checkConferma) {
            int rls = -1;
            rls = JOptionPane.showConfirmDialog(newHackathonFrame, "Nuovo hackathon non salvato, conferma il ritorno?", "New Hacakthon", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (rls == JOptionPane.YES_OPTION) {
                homeFrame.setVisible(true);
                newHackathonFrame.setVisible(false);
                newHackathonFrame.dispose();
            }

        } else {
            homeFrame.setVisible(true);
            newHackathonFrame.setVisible(false);
            newHackathonFrame.dispose();
        }
    }
}
