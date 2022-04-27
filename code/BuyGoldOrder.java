import java.awt.Color;
import java.awt.Graphics2D;

public class BuyGoldOrder extends GoldOrder {

    public BuyGoldOrder(double x, double y, Country country) {
        super(x, y, country);
    }

    /**
     * set color of the order
     * draw oval
     * draw amount as string
     */
    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.GREEN);
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
     * update its country
     * then dispose itself
     */
    @Override
    void out() {
        country.gainGold(this.amount);
        country.loseCash((int) (this.amount * Common.getGoldPrice().getCurrentPrice()));
        dispose();
    }

    @Override
    public void inside(Corporation corp) {
        corp.gainCash(amount * Common.getGoldPrice().getCurrentPrice());
        country.loseCash(amount * Common.getGoldPrice().getCurrentPrice());
        country.loseHappiness(amount * 0.1);
        dispose();
    }

}