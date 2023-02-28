package view.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomeView extends JPanel {

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = (int) screenSize.getWidth(), HEIGHT = (int) screenSize.getHeight();
    private JButton dominosBtn, CarcassonneBtn, loadDominos, loadCarcassonne;
    private JPanel dominosPnl, CarcassonnePnl;
    private JSlider nbJoueursDominos, nbJoueursCarcassonne, nbJoueursADominos, nbJoueursACCarcassonne;
    private JLabel dominosPlayersLbl, CarcassonnePlayersLbl, IADominosLbl, IACCarcassonneLbl;


    private static class ImagePanel extends JPanel {

        private BufferedImage image;

        public ImagePanel(String path) {
            setLayout(null);
            try {
                image = ImageIO.read(new File(path));
                setSize(image.getWidth(), image.getHeight());
            } catch (IOException e) {
                System.err.println("Erreur dans le chargement de l'image ImagePanel");
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(image != null) g.drawImage(image, (int) (0.5 * getWidth() - 0.5 * image.getWidth()), 0, this);
        }
    }

    public HomeView() {
        initPanels();
        initButtons();
        initNbPlayers();
        style();

        dominosPnl.add(dominosBtn);
        CarcassonnePnl.add(CarcassonneBtn);
        dominosPnl.add(nbJoueursDominos);
        CarcassonnePnl.add(nbJoueursCarcassonne);
        dominosPnl.add(dominosPlayersLbl);
        CarcassonnePnl.add(CarcassonnePlayersLbl);
        dominosPnl.add(nbJoueursADominos);
        CarcassonnePnl.add(nbJoueursACCarcassonne);
        dominosPnl.add(loadDominos);
        CarcassonnePnl.add(loadCarcassonne);
        dominosPnl.add(IADominosLbl);
        CarcassonnePnl.add(IACCarcassonneLbl);

        add(dominosPnl);
        add(CarcassonnePnl);
    }

    private void initPanels() {
        setSize(WIDTH, HEIGHT);
        setLayout(new GridLayout(1, 2));

        dominosPnl = new ImagePanel("src/Resources/images/menu/R.png");
        CarcassonnePnl = new ImagePanel("src/Resources/images/menu/c2.jpg");

        dominosPnl.setBackground(new Color(0,0,0));
        CarcassonnePnl.setBackground(new Color(255, 255, 255));
    }

    private void initButtons() {
        dominosBtn = new JButton(new ImageIcon("src/Resources/images/menu/button1.png"));
        CarcassonneBtn = new JButton(new ImageIcon("src/Resources/images/menu/playCarcassonne.png"));
        loadDominos = new JButton(new ImageIcon("src/Resources/images/menu/loadDominos.png"));
        loadCarcassonne = new JButton(new ImageIcon("src/Resources/images/menu/loadCarcassonne.png"));

        dominosBtn.setBackground(Color.BLACK);
        CarcassonneBtn.setBackground(Color.WHITE);
        loadDominos.setBackground(Color.WHITE);
        loadCarcassonne.setBackground(Color.BLACK);
        loadCarcassonne.setForeground(Color.WHITE);
    }

    private void initNbPlayers() {
        nbJoueursDominos = new JSlider(2, 4, 2);
        nbJoueursDominos.setMajorTickSpacing(1);
        nbJoueursDominos.setMinorTickSpacing(1);
        nbJoueursDominos.setPaintLabels(true);
        nbJoueursDominos.setPaintTicks(true);

        nbJoueursADominos = new JSlider(0, 2, 0);
        nbJoueursADominos.setMajorTickSpacing(1);
        nbJoueursADominos.setMinorTickSpacing(1);
        nbJoueursADominos.setPaintLabels(true);
        nbJoueursADominos.setPaintTicks(true);

        nbJoueursCarcassonne = new JSlider(2, 4, 2);
        nbJoueursCarcassonne.setMajorTickSpacing(1);
        nbJoueursCarcassonne.setMinorTickSpacing(1);
        nbJoueursCarcassonne.setPaintTicks(true);
        nbJoueursCarcassonne.setPaintLabels(true);

        nbJoueursACCarcassonne = new JSlider(0, 2, 0);
        nbJoueursACCarcassonne.setMajorTickSpacing(1);
        nbJoueursACCarcassonne.setMinorTickSpacing(1);
        nbJoueursACCarcassonne.setPaintTicks(true);
        nbJoueursACCarcassonne.setPaintLabels(true);

        dominosPlayersLbl = new JLabel("Selectionner le nombre de joueurs");
        CarcassonnePlayersLbl = new JLabel("Selectionner le nombre de joueurs");
        IADominosLbl = new JLabel("Selectionner le nombre de IA");
        IACCarcassonneLbl = new JLabel("Selectionner le nombre de IA");
        Font labelsFont = new Font("ariel", Font.BOLD, 25);
        dominosPlayersLbl.setFont(labelsFont);
        CarcassonnePlayersLbl.setFont(labelsFont);
        IADominosLbl.setFont(labelsFont);
        IACCarcassonneLbl.setFont(labelsFont);
        dominosPlayersLbl.setForeground(Color.WHITE);
        IADominosLbl.setForeground(Color.WHITE);
    }

    private void style() {
        dominosBtn.setBounds((int) (0.25 * WIDTH) - 105, (int) (0.5 * HEIGHT) - 29, 210, 58);
        CarcassonneBtn.setBounds((int) (0.25 * WIDTH) - 125, (int) (0.5 * HEIGHT) - 29, 250, 58);
        loadDominos.setBounds((int) (0.25 * WIDTH) - 105, (int) (0.6 * HEIGHT) - 48, 210, 58);
        loadCarcassonne.setBounds((int) (0.25 * WIDTH) - 125, (int) (0.6 * HEIGHT) - 48, 250, 58);
        loadCarcassonne.setBackground(Color.WHITE);

        nbJoueursDominos.setBounds((int) (0.25 * WIDTH) - 105, (int) (0.7 * HEIGHT), 210, 58);
        nbJoueursDominos.setForeground(Color.WHITE);
        nbJoueursDominos.setBackground(Color.BLACK);
        nbJoueursADominos.setBounds((int) (0.25 * WIDTH) - 105, (int) (0.85 * HEIGHT), 210, 58);
        nbJoueursADominos.setForeground(Color.WHITE);
        nbJoueursADominos.setBackground(Color.BLACK);
        nbJoueursCarcassonne.setBounds((int) (0.25 * WIDTH) - 105, (int) (0.7 * HEIGHT), 210, 58);
        nbJoueursCarcassonne.setBackground(Color.WHITE);
        nbJoueursACCarcassonne.setBounds((int) (0.25 * WIDTH) - 105, (int) (0.85 * HEIGHT), 210, 58);
        nbJoueursACCarcassonne.setBackground(Color.WHITE);

        dominosPlayersLbl.setBounds((int) (0.25 * WIDTH) - 220, (int) (0.65 * HEIGHT) - 29, 440, 58);
        CarcassonnePlayersLbl.setBounds((int) (0.25 * WIDTH) - 220, (int) (0.65 * HEIGHT) - 29, 440, 58);
        IADominosLbl.setBounds((int) (0.25 * WIDTH) - 180, (int) (0.8 * HEIGHT) - 29, 360, 58);
        IACCarcassonneLbl.setBounds((int) (0.25 * WIDTH) - 180, (int) (0.8 * HEIGHT) - 29, 360, 58);
        personnaliseButton();
    }

    public void personnaliseButton() {
        loadCarcassonne.setBackground(new Color(255, 255, 255)); loadCarcassonne.setOpaque(false); loadCarcassonne.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        CarcassonneBtn.setBackground(new Color(255, 255, 255)); CarcassonneBtn.setOpaque(false); CarcassonneBtn.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        loadDominos.setBackground(new Color(255, 255, 255)); loadDominos.setOpaque(false); loadDominos.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        dominosBtn.setBackground(new Color(255, 255, 255)); dominosBtn.setOpaque(false); dominosBtn.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
    }

    public int getNbJoueursDominos() {
        return nbJoueursDominos.getValue();
    }

    public int getNbJoueursADominos() {
        return nbJoueursADominos.getValue();
    }

    public int getNbJoueursCarcassonne() {
        return nbJoueursCarcassonne.getValue();
    }

    public int getNbJoueursACCarcassonne() {
        return nbJoueursACCarcassonne.getValue();
    }

    public void setDominosBtnActionListener(ActionListener actionListener) {
        dominosBtn.addActionListener(actionListener);
    }

    public void setCarcassonneBtnActionListener(ActionListener actionListener) {
        CarcassonneBtn.addActionListener(actionListener);
    }

    public void setLoadDominosBtnActionListener(ActionListener actionListener) {
        loadDominos.addActionListener(actionListener);
    }

    public void setLoadCarcassonneBtnActionListener(ActionListener actionListener) {
        loadCarcassonne.addActionListener(actionListener);
    }

    public void enableLoadDominos(boolean enabled) {
        loadDominos.setEnabled(enabled);
    }

    public void enableLoadCarcassonne(boolean enabled) {
        loadCarcassonne.setEnabled(enabled);
    }
}
