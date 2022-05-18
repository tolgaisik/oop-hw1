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
        cash = rand.nextDouble(3000) + 1000;
        gold = rand.nextDouble(100) + 50;
        worth = this.cash + this.gold * Common.getGoldPrice().getCurrentPrice();
        happiness = rand.nextDouble(100);
    }

    @Override
    public void draw(Graphics2D g) {
        int x = this.position.getIntX();
        int y = this.position.getIntY();

        if (image != null) {
            g.drawImage(image, x,
                    y, null);
        }
        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 15));
        g.setColor(Color.BLACK);
        g.drawString(this.name, x,
                y + 170);

        // draw worth value
        g.setColor(Color.BLUE);
        g.drawString("Worth : ", x,
                y + 190);
        g.drawString(String.format("%.2f", worth), x + 150,
                y + 185);

        // draw cash value
        g.setColor(new Color(0, 100, 0));
        g.drawString("Cash : ", x,
                y + 210);
        g.drawString(String.format("%.2f", cash), x + 150,
                y + 210);

        // draw gold value
        g.setColor(Color.YELLOW);
        g.drawString("Gold : ", x,
                y + 230);
        g.drawString(String.format("%.2f", gold), x + 150,
                y + 230);

        // draw happiness value
        g.setColor(new Color(180, 0, 0));
        g.drawString("Happiness : ", x,
                y + 250);
        g.drawString(String.format("%.2f", happiness), x + 150,
                y + 250);

    }

    @Override
    public void step() {
        // calculate variables of the current country
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

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
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

    public void loseCash(double d) {
        this.cash -= d;
    }

    public void gainHappiness(double d) {
        this.happiness += d;
    }

    public void loseHappiness(double d) {
        this.happiness -= d;
    }

    public double getHappiness() {
        return happiness;
    }

}