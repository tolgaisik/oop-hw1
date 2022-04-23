import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Corporation extends Entity {
    private String name;
    private Image image;
    private State state;

    public Corporation(double x, double y, String name) {
        super(x, y);
        this.name = name;
        try {
            image = ImageIO.read(new File("images/" + this.name + ".png")).getScaledInstance(100, 100,
                    Image.SCALE_DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.state = Common.generateRandomState();
    }

    @Override
    public void draw(Graphics2D g) {
        // draw corporate image
        if (this.image != null) {
            g.drawImage(this.image, (int) position.getX(),
                    (int) position.getY(), null);
        }
        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 20));
        g.drawString(this.state.getState(), (int) position.getX() + 15, (int) position.getY() + 120);
    }

    /**
     * calculates the position based on the type of current state object
     * 
     * @see State#next(Entity)
     * @params none
     */
    @Override
    public void step() {
        Position p = this.state.next(this);
        this.position.setX(p.getX());
        this.position.setY(p.getY());
    }

    /**
     * updates the state of the corporation
     * calls the random state generator function @see
     * Corporation.generateRandomState()
     * 
     * @params
     * @return
     */
    public void update() {
        this.state = Common.generateRandomState();
    }

    // TODO
    // Corporation image is 100 x 100
    // Cash RGB --> (180, 0, 0)
    // Badge is 20 x 20
    /**
     * 
     * @return name of corporation
     */
    public String getName() {
        return this.name;
    }
}