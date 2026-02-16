package it.unina.poo.gui;

import it.unina.poo.controller.ClassificaController;
import it.unina.poo.controller.HackathonController;
import it.unina.poo.model.ClassificaVotoTeam;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Utente;
import it.unina.poo.model.Voto;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClassificaGUI {
    private JPanel mainClassificaPanel;
    private JLabel lblTitle;
    private JScrollPane scrollPaneLIst;
    private JList<ClassificaVotoTeam> listClassifica;
    private JPanel btnPanel;
    private JButton btnValuta;
    private JButton btnEdit;
    private JButton btnBack;
    private JScrollPane scrollPaneLIstVoto;
    private JList<Voto> listVoto;

    private final JFrame classificaFrame;
    private final ClassificaController classificaController;
    private final DefaultListModel<ClassificaVotoTeam> listClassificaModel = new DefaultListModel<>();
    private final DefaultListModel<Voto> listVotoModel = new DefaultListModel<>();
    private final Hackathon hackathon;
    private final Utente utente;
    private final HackathonController hackathonController;
    private final String ruolo;

    public ClassificaGUI(JFrame homeFrame, Hackathon hackathon, Utente utente) {
        classificaFrame = new JFrame("Classifica Team Hackathon");
        classificaFrame.setContentPane(mainClassificaPanel);
        classificaFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        classificaFrame.setVisible(true);

        classificaController = new ClassificaController();
        hackathonController = new HackathonController();
        this.hackathon = hackathon;
        this.utente = utente;

        listClassifica.setModel(listClassificaModel);
        listVoto.setModel(listVotoModel);

        //Get ruolo utente
        this.ruolo = hackathonController.getRuoloUtente(hackathon.getTitolo(), utente.getIdUtente());

        btnValuta.setVisible(false);
        btnEdit.setVisible(false);

        if(ruolo.equals("giudice")){
            btnValuta.setVisible(true);
            btnEdit.setVisible(true);
        }

        btnValuta.setEnabled(false);
        btnEdit.setEnabled(false);

        //Init list classifica
        this.initListClassifica();

        //Controllo select list
        this.controlloSelectListClassifica();

        //Controllo select list voto
        this.controlloSelectListVoto();

        //Gestione della bottone valuta team
        this.gestBtnValutaTeam();

        //Gestione della bottone edit valore team
        this.gestBtnEditValore();

        //Ritorno home
        classificaFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToHome(homeFrame);
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToHome(homeFrame);
            }
        });

        classificaFrame.pack();
        classificaFrame.setLocationRelativeTo(null);
    }

    private void initListClassifica(){
        if(classificaController.initListClassificaVotoTeam(hackathon.getTitolo())){
            listClassificaModel.clear();
            listClassificaModel.addAll(classificaController.getListClassificaVotoTeam());
        }else{
            JOptionPane.showMessageDialog(classificaFrame, "Errore durante init list classifica", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadListVoto(){
        if (listClassifica.getSelectedValue() == null){
            listVotoModel.clear();
            return;
        }

        if(classificaController.getListVoto(listClassifica.getSelectedValue().getIdTeam(), utente.getIdUtente(), ruolo)){
            listVotoModel.clear();
            listVotoModel.addAll(classificaController.getListVoto());
        }else {
            JOptionPane.showMessageDialog(classificaFrame, "Errore durante get list voto", "Errore",  JOptionPane.ERROR_MESSAGE);
        }
    }

    private void controlloSelectListClassifica(){
        listClassifica.addListSelectionListener(new  ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(listClassifica.getSelectedValue() != null){
                    btnValuta.setEnabled(true);
                    btnEdit.setEnabled(false);
                    loadListVoto();
                }
            }
        });
    }

    private void controlloSelectListVoto(){
        if(ruolo.equals("giudice")){
            listVoto.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    btnEdit.setEnabled(true);
                }
            });
        }
    }

    private void gestBtnValutaTeam(){
        btnValuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputValore = JOptionPane.showInputDialog(classificaFrame, "Inserisci il valore da 0-10: ");
                if(inputValore != null && !inputValore.isEmpty()){
                    float valore = Float.parseFloat(inputValore);

                    if(classificaController.valutaTeam(utente.getIdUtente(), listClassifica.getSelectedValue().getIdTeam(), valore)){
                        initListClassifica();
                    }else{
                        JOptionPane.showMessageDialog(classificaFrame, "Errore durante la valutazione", "Errore",  JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void gestBtnEditValore(){
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listVoto.getSelectedValue() == null){
                    JOptionPane.showMessageDialog(classificaFrame, "Seleziona il voto da modificare", "Warninge",  JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String inputNewValore = JOptionPane.showInputDialog(classificaFrame, "Inserisci il nuovo valore da 0-10: ");

                if(inputNewValore != null && !inputNewValore.isEmpty()){
                    float valore = Float.parseFloat(inputNewValore);

                    if(classificaController.modificaValutazione(valore, listVoto.getSelectedValue().getIdVoto())){
                        initListClassifica();
                        loadListVoto();
                    }else {
                        JOptionPane.showMessageDialog(classificaFrame, "Errore durante la modificazione della valuta", "Errore",  JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }

    private void returnToHome(JFrame homeFrame) {
        homeFrame.setVisible(true);
        classificaFrame.setVisible(false);
        classificaFrame.dispose();
    }
}
