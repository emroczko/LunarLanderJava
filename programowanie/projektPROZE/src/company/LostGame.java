package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class LostGame extends JPanel {

    /** Zmienna przechowująca obrazek tła*/
    private ImageIcon MainMenuImage;
    /** Zmienna przechowująca nick gracza*/
    private String nick;
    /** Zmienne przechowująca wielkość poprzedniego okna*/
    private int a, b;
    private int points;

    Color aqua = new Color (51, 134, 175);
    Color citron = new Color (223, 234, 24);

    JButton startButton = new JButton("Play again");
    JButton backButton = new JButton("Return to main menu");
    JLabel lost = new JLabel("THE MISSION HAS FAILED");
    JLabel pointsLabel = new JLabel();

    LabelCustomizer customLabelAqua = new LabelCustomizer(aqua, 40);
    LabelCustomizer customLabelWhite = new LabelCustomizer(aqua, 40);
    ButtonCustomizer customButton = new ButtonCustomizer(true, citron, 32);

    /** Obiekt klasy GridBagConstraintsMaker**/
    GridBagConstraintsMaker customGBC = new GridBagConstraintsMaker();
    /** Obiekt klasy NewWindow **/
    NewWindow newWindow = new NewWindow();

    public LostGame(int xSize, int ySize, int earnedPoints, String nickName){
        this.removeAll();
        repaint();
        revalidate();
        points = earnedPoints;
        nick = nickName;
        a = xSize;
        b = ySize;
        setPreferredSize(new Dimension(a,b));

        initializeVariables();
        this.setLayout(new GridBagLayout());

        save();

        startButton.addActionListener(playAgainButtonListener());
        backButton.addActionListener(returnToMainMenuButtonListener());

        pointsLabel.setText("Your score is: "+ earnedPoints);
        customButton.customizer(startButton);
        customButton.customizer(backButton);
        customLabelAqua.customizer(lost);
        customLabelWhite.customizer(pointsLabel);


        this.add(lost, customGBC.gbcCustomize(0,2, 0, 0,3, "none"));
        this.add(pointsLabel, customGBC.gbcCustomize(0,3, 0, 0,3, "none"));
        this.add(startButton, customGBC.gbcCustomize(0,4, 0, 0,3, "none"));
        this.add(backButton, customGBC.gbcCustomize(0,5, 0, 0,3, "none"));
        customGBC.gbcCustomize(0,0,1,1,0,"none");



        }

    private void save(){
        try {
            RankingSaver.saveToFile(nick, points);
        }
        catch(Exception E){
            E.printStackTrace();
        }
    }

        /** metoda inicjalizująca obrazek tła za pomocą metody obiektu ImageFactory*/
        private void initializeVariables() {
            this.MainMenuImage = ImageFactory.createImage(Image.MainMenu);
        }
        /** metoda przesłaniająca metodę paintComponent, w celu odpowiedniego skalowania obrazka w tle*/
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(MainMenuImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

        }


        /**
         * Odpowiada za przypisanie akcji przyciskowi START
         */
        private ActionListener playAgainButtonListener() {
            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cleanWindow();
                    add(new IntroLevel(getWidth(),getHeight(),1, PropertiesLoad.numberOfLives, 0, nick),newWindow.buttonsClickedBehaviour());

                }
            };
            return actionListener;
        }

        /**
         * Odpowiada za przypisanie akcji przyciskowi BACK
         */
        private ActionListener returnToMainMenuButtonListener() {
            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cleanWindow();
                    add(new Menu(),newWindow.buttonsClickedBehaviour());
                }
            };
            return actionListener;
        }

    private void cleanWindow(){
        newWindow.layoutMakerLostGame(this);
    }
}

