import java.awt.Graphics2D;
import java.awt.Color;

public class SellGoldOrder extends GoldOrder {

    public SellGoldOrder(double x, double y, Country country) {
        super(x, y, country);
    }

    /**
     * basic draw
     */
    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(180, 0, 0));
        g.fillOval((int) position.getX(), (int) position.getY(), 24, 24);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(amount), (int) position.getX() + 5, (int) position.getY() + 18);

    }

    // move the order
    @Override
    public void step() {
        int x_vec = (int) (speed * path.getX());
        int y_vec = (int) (speed * path.getY());
        position.setX(position.getX() + x_vec);
        position.setY(position.getY() + y_vec);
    }

    /**
     * called when order is out of the horizon
     * update country
     * then dispose itself
     */
    @Override
    void out() {
        country.loseGold(this.amount);
        country.gainCash(this.amount * Common.getGoldPrice().getCurrentPrice());
        dispose();
    }

    /**
     * this function is called if order is inside of a corporation object
     * 
     * @param corp which the order inside
     */
    @Override
    public void inside(Corporation corp) {
        corp.gainCash(amount * Common.getGoldPrice().getCurrentPrice());
        country.loseGold(amount);
        country.loseHappiness(amount * 0.1);
        dispose();
    }

}