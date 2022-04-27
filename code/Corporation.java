import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Corporation extends Entity {
    private String name;
    private Image image;
    private State state;
    private double cash;

    public Corporation(double x, double y, String name, String initStateName) {
        super(x, y);
        this.name = name;
        try {
            image = ImageIO.read(new File("images/" + this.name + ".png")).getScaledInstance(100, 100,
                    Image.SCALE_DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.state = createState(initStateName);
        this.cash = new Random().nextInt(1000);
    }

    @Override
    public void draw(Graphics2D g) {
        // draw corporate image
        if (this.image != null) {
            g.drawImage(this.image, (int) position.getX(),
                    (int) position.getY(), null);
        }
        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 20));
        g.drawString(this.state.getName(), (int) position.getX() + 15, (int) position.getY() + 120);
    }

    // define getters
    double getCash() {
        return cash;
    }

    // define setters
    void setCash(double cash) {
        this.cash = (int) cash;
    }

    /**
     * calculates the position based on the type of current state object
     * 
     * @see State#next(Entity)
     * @params none
     */
    @Override
    public void step() {

        // state next function takes an entity object
        // and based on the current state it generates new destination
        Position p = state.next(this);

        // We have to check if the next position is outside of the sandbox
        // If it is, we just pass
        this.position.setX(p.getX());
        this.position.setY(p.getY());

    }

    /**
     * generates a state
     * 
     * @return State object
     */
    public State createState(String stateType) {
        try {
            Class<?> stateClass = Class.forName(stateType);
            Constructor<?> constructor = stateClass.getConstructors()[0];
            return (State) constructor.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * sets the current state of the country with the given parameter state object
     * 
     * @see State
     * @param state
     * @return void
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * returns the current state of the country
     * 
     * @return State
     */
    public State getState() {
        return this.state;
    }

    /**
     * 
     * @return name of corporation
     */
    public String getName() {
        return this.name;
    }

    public void gainCash(double amount) {
        this.cash += amount;
    }

    public void loseCash(double amount) {
        this.cash -= amount;
    }

}