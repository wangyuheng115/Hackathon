package it.unina.poo.gui;

import it.unina.poo.controller.NewHackathonController;
import it.unina.poo.controller.NewSedeController;
import it.unina.poo.model.Sede;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class NewSedeGUI {
    private JPanel mainSedePanel;
    private JLabel lblTitle;
    private JLabel lblVia;
    private JTextField textFieldVia;
    private JLabel lblNumeroCivico;
    private JTextField textFieldNumeroCivico;
    private JLabel lblCitta;
    private JTextField textFieldCitta;
    private JLabel lblCAP;
    private JTextField textFieldCAP;
    private JLabel lblProvincia;
    private JTextField textFieldProvincia;
    private JPanel panelBtn;
    private JButton btnSave;
    private JButton btnAnnulla;
    private boolean checkSave = false;
    private final JFrame newSedeFrame;
    private final NewHackathonController newHackathonController;

    public NewSedeGUI(JFrame newHackathonFrame, NewHackathonController newHackathonController) {
        newSedeFrame = new JFrame("New Sede");
        newSedeFrame.setContentPane(this.mainSedePanel);
        newSedeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        newSedeFrame.pack();
        newSedeFrame.setLocationRelativeTo(null);
        newSedeFrame.setVisible(true);

        this.newHackathonController = newHackathonController;

        //Ritorno indietro
        newSedeFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToNewHacakthon(newHackathonFrame, newSedeFrame);
            }
        });

        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToNewHacakthon(newHackathonFrame, newSedeFrame);
            }
        });

        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String via = textFieldVia.getText().trim();
                String numeroCivico = textFieldNumeroCivico.getText().trim();
                String citta = textFieldCitta.getText().trim();
                String cap = textFieldCAP.getText().trim();
                String  provincia = textFieldProvincia.getText().trim();

                if(via.isEmpty() || numeroCivico.isEmpty() || citta.isEmpty() || cap.isEmpty() || provincia.isEmpty()) {
                    JOptionPane.showMessageDialog(newSedeFrame,"Tutti i campi sono obbligatori", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                NewSedeController newSedeController = new NewSedeController();
                if(newSedeController.createSede(via, numeroCivico, citta, cap, provincia)) {
                    JOptionPane.showMessageDialog(newSedeFrame, "Sede creata correttamente", "Info", JOptionPane.INFORMATION_MESSAGE);
                    checkSave = true;
                    returnToNewHacakthon(newHackathonFrame, newSedeFrame);
                }else{
                    JOptionPane.showMessageDialog(newSedeFrame, "Sede non creata, riprova", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void returnToNewHacakthon(JFrame newHackathonFrame, JFrame newSedeFrame) {
        //Se non salvato nuovo sede verrà chiesto se si deve salvare, se nuovo sede salvato verrà aggiornato list del sede
        if(!checkSave){
            int rls = -1;
            rls = JOptionPane.showConfirmDialog(newSedeFrame, "Nuovo sede non salvato, conferma il ritorno?","New Sede", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (rls == JOptionPane.YES_OPTION){
                newHackathonFrame.setVisible(true);
                newSedeFrame.setVisible(false);
                newSedeFrame.dispose();
            }
        }else {
            newHackathonFrame.setVisible(true);
            if(!newHackathonController.getListSede()){
                JOptionPane.showMessageDialog(newHackathonFrame,"Errore durante get list sede", "Errore",JOptionPane.ERROR_MESSAGE);
            }else{
                NewHackathonGUI.comboBoxSedeModel.removeAllElements();
                List<Sede> sedeList = newHackathonController.getArrayListSede();
                NewHackathonGUI.comboBoxSedeModel.addAll(sedeList);
                NewHackathonGUI.comboBoxSedeModel.setSelectedItem(sedeList.getLast());
            }

            newSedeFrame.setVisible(false);
            newSedeFrame.dispose();
        }

    }
}
