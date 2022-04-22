import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Country extends Entity {
    String name;
    double cash;
    double gold;
    double worth;
    double happiness;
    Image image = null;

    public Country(double x, double y, String name) {
        super(x, y);
        this.name = name;
        try {
            image = ImageIO.read(new File("images/" + this.name + ".png")).getScaledInstance(150, 150,
                    Image.SCALE_DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (image != null) {
            g.drawImage(image, (int) position.getX(),
                    (int) position.getY(), null);
        }

        g.setFont(new Font("Arial", Font.BOLD, 22));

        g.drawString(this.name, (int) position.getX(),
                (int) position.getY() + 200);

        g.setColor(Color.BLUE);
        g.drawString("Worth : ", (int) position.getX(),
                (int) position.getY() + 240);
        g.drawString(this.worth + "", (int) position.getX() + 150,
                (int) position.getY() + 240);

        g.setColor(new Color(0, 100, 0));
        g.drawString("Cash : ", (int) position.getX(),
                (int) position.getY() + 280);
        g.drawString(this.cash + "", (int) position.getX() + 150,
                (int) position.getY() + 280);

        g.setColor(Color.YELLOW);
        g.drawString("Gold : ", (int) position.getX(),
                (int) position.getY() + 320);
        g.drawString(this.gold + "", (int) position.getX() + 150,
                (int) position.getY() + 320);

        g.setColor(new Color(180, 0, 0));
        g.drawString("Happiness : ", (int) position.getX(),
                (int) position.getY() + 360);
        g.drawString(this.cash + "", (int) position.getX() + 150,
                (int) position.getY() + 360);

    }

    @Override
    public void step() {
        // TODO Auto-generated method stub

    }
    // TODO
    // Country image is 150 x 150
    // Name RGB --> Black
    // Worth RGB --> Blue
    // Cash RGB --> (0, 100, 0)
    // Gold RGB --> Yellow
    // Happiness RGB --> (180, 0, 0)

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}