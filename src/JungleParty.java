import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Created by martin on 2017/5/27.
 */
public class JungleParty extends JFrame{

    private JLabel[] animals = new JLabel[10];
    private JPanel topPanel = new JPanel(new GridLayout(2,5));
    private JPanel medPanel = new JPanel(new GridBagLayout());
    private JPanel bottomPanel = new JPanel(new FlowLayout());
    private JLabel text = new JLabel("How many animals have come to the party?");
    private JTextField answerField = new JTextField(2);
    private JButton checkButton = new JButton("CHECK!");
    private int picNumber = 10;

    JungleParty()
    {
        ClickMouse clickListener = new ClickMouse();

        Random randGenerator = new Random();
        for(int i = 0; i < 10; i++)
        {
            animals[i] = new JLabel(new ImageIcon("src/jungle/animal" + (i+1) + ".png"));
            topPanel.add(animals[i]);
            animals[i].addMouseListener(clickListener);
        }

        medPanel.add(text);
        medPanel.add(answerField);

        checkButton.setSize(new Dimension(50, 50));
        bottomPanel.add(checkButton,BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(medPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    int answer = Integer.parseInt(answerField.getText());
                    if(answer == getPicNumber())
                    {
                        text.setText("Correct! How many animals are in the party now?");
                        topPanel.removeAll();
                        setPicNumber(randGenerator.nextInt(9) + 1);
                        putIconLabels(getPicNumber());
                        topPanel.repaint();
                        topPanel.validate();
                    }
                    else
                        text.setText("Wrong! Try again!");
                }
                catch(NumberFormatException e1)
                {
                    JOptionPane.showMessageDialog(null, "Input error. Please try again.",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void setPicNumber(int newNumber)
    {
        this.picNumber = newNumber;
    }

    private int getPicNumber()
    {
        return this.picNumber;
    }

    private void putIconLabels(int number)
    {
        for (int i = 0; i < number; i++)
            topPanel.add(animals[i]);
    }

    class ClickMouse implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            Object selected = e.getSource();
            for(int i = 0; i < 10; i++) {
                if (animals[i].equals(selected)) {
                    topPanel.remove(animals[i]);
                    setPicNumber(getPicNumber() - 1);
                    topPanel.repaint();
                    topPanel.validate();
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }
    }

    public static void main(String[] args)
    {
        JungleParty jpTest = new JungleParty();
        jpTest.setTitle("Welcome to the Jungle Party!");
        jpTest.setSize(960,400);
        jpTest.setLocationRelativeTo(null);
        jpTest.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jpTest.setVisible(true);
    }
}