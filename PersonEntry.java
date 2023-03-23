import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonEntry extends JFrame
{
    private JTextField  txtName;       //name
    private JTextField  txtAge;        //age
    private JCheckBox   willPublish;    //willPublish
    private JButton     cmdSave;
    private JButton     cmdClose;

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    //question 1b
    private PersonListing personListing;

    PersonEntry PersonEntry = this;//question 2b

  
    public PersonEntry(PersonListing personListing)
    {
        this.personListing = personListing;//question 1c
        setTitle("Entering new person");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.add(new JLabel("Name:")); 
        txtName = new JTextField(20);
        pnlDisplay.add(txtName);
        pnlDisplay.add(new JLabel("Age:"));
        txtAge = new JTextField(3);
        pnlDisplay.add(txtAge);
        willPublish = new JCheckBox("Will Publish?");//2a
        pnlDisplay.add(willPublish);
        

        pnlDisplay.setLayout(new GridLayout(3,4));
       
        cmdSave = new JButton("Save");
        cmdClose = new JButton("Close");
        cmdSave.setBackground(Color.GREEN);
        cmdClose.setBackground(Color.RED);

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        cmdClose.addActionListener(new ClosePersonEntryListener());
        cmdSave.addActionListener(new SavePersonEntryListener());
       
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

         
    }

    private class ClosePersonEntryListener implements ActionListener{
        public void actionPerformed(ActionEvent e)//question 2b
        {
            PersonEntry.setVisible(false);
        }
    
    }

    private class SavePersonEntryListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
                if(!txtName.getText().isEmpty() && !txtAge.getText().isEmpty()){
                    String name = txtName.getText();
                    String[] names = name.split(" ");
                    if(names.length > 1) {
                            try{
                                int ageStr = Integer.parseInt(txtAge.getText());
                                personListing.addPerson(new Person(name, ageStr, willPublish.isSelected()));
                                PersonEntry.setVisible(false);
                            }
                            catch(NumberFormatException numerr){
                               System.out.println(numerr.getMessage() + " must be a number");
                            }                   
                    }
                }else if(txtName.getText().isEmpty()){
                    System.out.println("Enter a name");
                }else{
                    System.out.println("Enter age");
                }
            }
        }
    }

