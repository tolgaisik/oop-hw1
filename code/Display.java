import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    public Display() {
        this.setBackground(new Color(180, 180, 180));
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }

    @Override
    protected void paintComponent(Graphics g2d) {
        super.paintComponent(g2d);
        Graphics2D g = (Graphics2D) g2d;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Common.getFoodPrice().draw(g);
        Common.getElectronicsPrice().draw(g);
        Common.getGoldPrice().draw(g);

        g.drawLine(Common.getFirstVerticalLineX(), 0, Common.getFirstVerticalLineX(), Common.getHorizontalLineY());
        g.drawLine(Common.getSecondVerticalLineX(), 0, Common.getSecondVerticalLineX(), Common.getHorizontalLineY());
        g.drawLine(0, Common.getHorizontalLineY(), Common.getWindowWidth(), Common.getHorizontalLineY());

        Common.getCountries().forEach(country -> country.draw(g));
        Common.getCorporations().forEach(corporation -> corporation.draw(g));
        g.setColor(Color.RED);
        g.drawRect((int) Common.getSandBox().getX(), (int) Common.getSandBox().getY(),
                (int) Common.getSandBox().getWidth(), (int) Common.getSandBox().getHeight());

        Common.getOrders().forEach(order -> order.draw(g));
    }
}