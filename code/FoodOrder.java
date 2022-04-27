import java.awt.Graphics2D;
import java.awt.Color;

public class FoodOrder extends Order {

    public FoodOrder(double x, double y, Country country) {
        super(x, y, country);
    }

    /**
     * set color of the order
     * draw oval
     * draw amount as string
     */
    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(245, 222, 179));
        g.fillOval((int) position.getX(), (int) position.getY(), 24, 24);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(amount), (int) position.getX() + 5, (int) position.getY() + 18);
    }

    /**
     * move the order
     */
    @Override
    public void step() {
        int x_vec = (int) (speed * path.getX());
        int y_vec = (int) (speed * path.getY());
        position.setX(position.getX() + x_vec);
        position.setY(position.getY() + y_vec);
    }

    /**
     * since this is called when order is out of horizontalLineY
     * it updates its country and dispose itself
     */
    @Override
    void out() {
        country.loseCash(amount * Common.getFoodPrice().getCurrentPrice());
        country.gainHappiness(amount * 0.2);
        dispose();
    }
}