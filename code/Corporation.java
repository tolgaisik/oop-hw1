import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
public class Corporation extends Entity {
    private String name;
    private Image image;
    private State state;
    public Corporation(double x, double y, String name) {
        super(x, y);
        this.name = name;
        try {
            image = ImageIO.read(new File("images/" + this.name + ".png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        this.state = new Shake();
    }

    @Override
    public void draw(Graphics2D g) {
        if(this.image != null) {
            g.drawImage(this.image, (int) position.getX(),
            (int) position.getY(), null);
        }
    }

    @Override
    public void step() {
        // TODO Auto-generated method stub

    }
    // TODO
    // Corporation image is 100 x 100
    // Cash RGB --> (180, 0, 0)
    // Badge is 20 x 20
    public String getName() {
        return this.name;
    }
}