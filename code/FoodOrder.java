import java.awt.Graphics2D;
import java.awt.Color;

public class FoodOrder extends Order {

    public FoodOrder(double x, double y, Country country) {
        super(x, y, country);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(245, 222, 179));
        g.fillOval((int) position.getX(), (int) position.getY(), 24, 24);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(amount), (int) position.getX() + 5, (int) position.getY() + 18);
    }

    @Override
    public void step() {
        int x_vec = (int) (speed * path.getX());
        int y_vec = (int) (speed * path.getY());
        position.setX(position.getX() + x_vec);
        position.setY(position.getY() + y_vec);
    }
    // TODO
    // RGB --> (245, 222, 179)

    @Override
    void out() {
        // TODO Auto-generated method stub

    }
}