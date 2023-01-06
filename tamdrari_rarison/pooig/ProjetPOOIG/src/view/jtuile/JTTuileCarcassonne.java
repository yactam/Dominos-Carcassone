package view.jtuile;

import components.tuiles.Tuile;
import components.tuiles.TuileCarcassonne;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class JTTuileCarcassonne extends JTuile {

    public JTTuileCarcassonne() {
        initTuile();
        repaint();
    }

    private void initTuile() {
        setSize(TUILE_WIDTH, TUILE_HEIGHT);
        tuile = null;
    }

    public Tuile getTuile() {
        return tuile;
    }

    @Override
    public void setTuile(Tuile t) {
        if(t == null) {
            this.tuile = null;
            repaint();
        } else {
            if(!(t instanceof TuileCarcassonne)) return;
            this.tuile = t;
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image, partisanImg;
        String tuileDescription = "";
        try {
            if(tuile == null) {
                //image = ImageIO.read(new File("src/Resources/images/Carcassonne/back.jpg"));
                //g.drawImage(image, 0, 0, TUILE_WIDTH, TUILE_HEIGHT, null);
                g.setColor(new Color(151, 222, 206));
                g.fillRect(0, 0, TUILE_WIDTH, TUILE_HEIGHT);
            } else  {
                tuileDescription = tuile.toString();
                image =ImageIO.read(new File("src/Resources/images/Carcassonne/Tuiles/" + tuileDescription + ".png"));
                g.drawImage(image, 0, 0, TUILE_WIDTH, TUILE_HEIGHT, null);
                if(((TuileCarcassonne)tuile).getPartisan() != null) {
                    partisanImg = ImageIO.read(new File("src/Resources/images/Carcassonne/Partisans/" + ((TuileCarcassonne)tuile).getPartisan().couleur() + ".png"));
                    Point partisanPos = getPartisanPosition();
                    g.drawImage(partisanImg, (int) partisanPos.getX(), (int) partisanPos.getY(), JPartisan.PARTISAN_WIDTH, JPartisan.PARTISAN_HEIGHT, this);
                }
            }
        } catch (Exception e) {
            System.err.println("Error while loading image of tuile");
            System.out.println(tuileDescription);
        }
    }

    private Point getPartisanPosition() {
        Point res;
        switch (((TuileCarcassonne)tuile).getCotePartisan()) {
            case 0 : res = new Point(0, 35);
                break;
            case 1 : res = new Point(35, 0);
                break;
            case 2 : res = new Point(60, 35);
                break;
            case 3 : res = new Point(35, 60);
                break;
            default : res = new Point(37, 37);
        };
        return res;
    }
}
