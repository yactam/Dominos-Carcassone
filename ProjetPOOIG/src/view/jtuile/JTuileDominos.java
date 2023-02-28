package view.jtuile;

import components.tuiles.Tuile;
import components.tuiles.TuileDominos;

import java.awt.*;

public class JTuileDominos extends JTuile {

    public static final int RECT_WIDTH  = TUILE_WIDTH / 5;
    public static final int RECT_HEIGHT = TUILE_HEIGHT / 5;
    private static final int TEXT_PADDING_TOP = RECT_HEIGHT - (RECT_HEIGHT / 6);
    private static final int TEXT_PADDING_LEFT = RECT_WIDTH / 6;
    private int bx, by; // starting point (x, y) for painting

    public JTuileDominos() {
        super();
    }

    public TuileDominos getTuile() {
        return (TuileDominos) super.tuile;
    }

    @Override
    public void setTuile(Tuile t) {
        if(t == null) {
            this.tuile = null;
            repaint();
        } else {
            if(!(t instanceof TuileDominos)) return;
            this.tuile = (TuileDominos) t;
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        if(tuile != null) {
            paintEmpty(graphics2D);
            graphics2D.setFont(new Font("Serif", Font.PLAIN, 15));
            paintTuile(graphics2D);
        } else {
            graphics2D.setColor(Color.BLACK);
            graphics2D.fillRect(0, 0, TUILE_WIDTH, TUILE_HEIGHT);
        }
    }

    private void erase(Graphics2D g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, TUILE_WIDTH, TUILE_HEIGHT);
    }

    private void paintEmpty(Graphics2D g) {
        bx = 0;
        by = 0;
        erase(g);
        g.setColor(Color.gray);
        g.fillRect(bx, by, RECT_WIDTH, RECT_HEIGHT);
        for(int i = 0; i < 3; i++) {
            g.drawRect(bx + (i+1) * RECT_WIDTH, by, RECT_WIDTH, RECT_HEIGHT);
        }
        g.fillRect(bx + 4 * RECT_WIDTH, by, RECT_WIDTH, RECT_HEIGHT);
        by += RECT_HEIGHT;
        bx = 0;
        for(int i = 0; i < 5; i++) {
            if(i == 0 || i == 4) {
                g.setColor(Color.gray);
                g.drawRect(bx + i * RECT_WIDTH, by, RECT_WIDTH, RECT_HEIGHT);
                g.drawRect(bx + i * RECT_WIDTH, by + RECT_HEIGHT, RECT_WIDTH, RECT_HEIGHT);
                g.drawRect(bx + i * RECT_WIDTH, by + 2 * RECT_HEIGHT, RECT_WIDTH, RECT_HEIGHT);
            } else {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(bx + i * RECT_WIDTH, by, RECT_WIDTH, RECT_HEIGHT);
                g.fillRect(bx + i * RECT_WIDTH, by + RECT_HEIGHT, RECT_WIDTH, RECT_HEIGHT);
                g.fillRect(bx + i * RECT_WIDTH, by + 2 * RECT_HEIGHT, RECT_WIDTH, RECT_HEIGHT);
            }
        }
        by += 3 * RECT_HEIGHT;
        bx = 0;
        g.fillRect(bx, by, RECT_WIDTH, RECT_HEIGHT);
        for(int i = 0; i < 3; i++) {
            g.drawRect(bx + (i+1) * RECT_WIDTH, by, RECT_WIDTH, RECT_HEIGHT);
        }
        g.fillRect(bx + 4 * RECT_WIDTH, by, RECT_WIDTH, RECT_HEIGHT);
    }

    private void paintTuile(Graphics2D g) {
        bx = 0;
        by = 0;
        g.setColor(Color.black);
        for(int i = 0; i < 3; i++) {
            g.drawString(String.valueOf(((TuileDominos)tuile).getValue(1, i)), bx + (i+1) * RECT_WIDTH + TEXT_PADDING_LEFT, by + TEXT_PADDING_TOP);
        }
        by += RECT_HEIGHT;
        bx = 0;
        for(int i = 0; i < 5; i++) {
            if(i == 0 || i == 4) {
                g.drawString(String.valueOf(((TuileDominos)tuile).getValue(i/2, 0)), bx + i * RECT_WIDTH + TEXT_PADDING_LEFT, by + TEXT_PADDING_TOP);
                g.drawString(String.valueOf(((TuileDominos)tuile).getValue(i/2, 1)), bx + i * RECT_WIDTH + TEXT_PADDING_LEFT, by + RECT_HEIGHT + TEXT_PADDING_TOP);
                g.drawString(String.valueOf(((TuileDominos)tuile).getValue(i/2, 2)), bx + i * RECT_WIDTH + TEXT_PADDING_LEFT, by + 2 * RECT_HEIGHT+TEXT_PADDING_TOP);
            }
        }
        by += 3 * RECT_HEIGHT;
        bx = 0;
        for(int i = 0; i < 3; i++) {
            g.drawString(String.valueOf(((TuileDominos)tuile).getValue(3, i)), bx + (i+1) * RECT_WIDTH + TEXT_PADDING_LEFT, by + TEXT_PADDING_TOP);
        }
    }

}
