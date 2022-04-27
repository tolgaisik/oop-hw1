import java.awt.Color;
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
    private String acronym;
    private Image image;
    private State state;
    private double cash;

    public Corporation(double x, double y, String name, String initStateName) {
        super(x, y);
        this.name = name.split(":")[0];
        this.acronym = name.split(":")[1];
        try {
            image = ImageIO.read(new File("images/" + this.name + ".png")).getScaledInstance(100, 100,
                    Image.SCALE_DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.state = createState(initStateName);
        this.cash = new Random().nextInt(2000) + 1000;
    }

    @Override
    public void draw(Graphics2D g) {
        // draw corporate image
        int x = position.getIntX();
        int y = position.getIntY();

        if (cash > 2000) // white badge
        {
            g.setColor(Color.WHITE);
            g.fillRect(x, y - 40, 15, 15);
        }
        if (cash > 4000) // yellow badge
        {
            g.setColor(Color.YELLOW);
            g.fillRect(x + 15, y - 40, 15, 15);
        }
        if (cash > 6000) // red badge
        {
            g.setColor(Color.RED);
            g.fillRect(x + 30, y - 40, 15, 15);
        }
        g.setColor(Color.BLACK);

        g.drawString(this.acronym, x, y - 10);
        if (this.image != null) {
            g.drawImage(this.image, x,
                    y, null);
        }
        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 20));
        g.drawString(this.state.getName(), x + 15, y + 120);
        g.drawString(Integer.toString((int) cash), x + 15, y + 140);

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