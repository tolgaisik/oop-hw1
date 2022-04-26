import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        init();

    }

    private void init() {
        Random rand = new Random();
        cash = rand.nextDouble(100);
        gold = rand.nextDouble(100);
        worth = rand.nextDouble(100);
        happiness = rand.nextDouble(100);
    }

    @Override
    public void draw(Graphics2D g) {
        int x = (int) this.position.getX();
        int y = (int) this.position.getY();
        if (image != null) {
            g.drawImage(image, x,
                    y, null);
        }

        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 15));
        g.drawString(this.name, x,
                y + 200);
        // draw worth value
        g.setColor(Color.BLUE);
        g.drawString("Worth : ", x,
                y + 240);
        g.drawString(this.worth + "", x + 150,
                y + 240);
        // draw cash value
        g.setColor(new Color(0, 100, 0));
        g.drawString("Cash : ", x,
                y + 280);
        g.drawString(this.cash + "", x + 150,
                y + 280);
        // draw gold value
        g.setColor(Color.YELLOW);
        g.drawString("Gold : ", x,
                y + 320);
        g.drawString(this.gold + "", x + 150,
                y + 320);
        // draw happiness value
        g.setColor(new Color(180, 0, 0));
        g.drawString("Happiness : ", x,
                y + 360);
        g.drawString(this.cash + "", x + 150,
                y + 360);

    }

    @Override
    public void step() {
        // TODO Auto-generated method stub
        // calculate variables of the current country
        // check if there is a order at the horizon
        // if there is, calculate the country economics
        this.worth = this.cash + this.gold * Common.getGoldPrice().getCurrentPrice();

    }

    /**
     * @param orderClassName
     * @return Order
     *         takes order name for the creation
     *         since Country class is a OrderFactory
     *         using reflection helps us the distinction between Country and Order
     *         classes
     */
    public Order createOrder(String orderClassName) {
        try {
            Class<?> stateClass = Class.forName(orderClassName);
            Constructor<?> constructor = stateClass.getConstructor(double.class, double.class, Country.class);
            return (Order) constructor.newInstance(this.getPosition().getX() + 75, this.getPosition().getY() - 20,
                    this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

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

    public void setCash(double amount) {
        this.cash = amount;
    }

    public void setGold(double amount) {
        this.gold = amount;
    }

    public void gainGold(double amount) {
        this.gold += amount;
    }

    public void loseGold(double amount) {
        this.gold -= amount;
    }

    public void gainCash(double amount) {
        this.cash += amount;
    }

    public void loseCash(int amount) {
        this.cash -= amount;
    }
}