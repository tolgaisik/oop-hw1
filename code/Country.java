import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Country extends Entity {
    String name;
    double cash;
    double gold;
    double worth;
    double happiness;
    BufferedImage image = null;

    public Country(double x, double y, String name) {
        super(x, y);
        this.name = name;
        try {
            image = ImageIO.read(new File("images/" + this.name + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (image != null) {
            g2d.drawImage(image.getScaledInstance(150, 150, Image.SCALE_DEFAULT), (int) position.getX(),
                    (int) position.getY(), null);
        }
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
}