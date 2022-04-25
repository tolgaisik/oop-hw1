import java.awt.*;

/**
 * @classdesc implements State interface to go to a random position within the
 *            current frame.
 */
public class GotoXY extends State {
    double dest_x = Common.getRandomGenerator().nextDouble(Common.getSandBox().width);
    double dest_y = Common.getRandomGenerator().nextDouble(Common.getSandBox().height + Common.getSandBox().y);

    @Override
    public Position next(Entity entity) {

        Position currentPosition = entity.getPosition();
        Point directionVector = new Point((int) (this.dest_x - currentPosition.getX()),
                (int) (this.dest_y - currentPosition.getY()));
        // vector magnitude
        double length = Math.sqrt(directionVector.x * directionVector.x + directionVector.y * directionVector.y);
        directionVector.x = (int) Math.round(directionVector.x / length); // normalize x
        directionVector.y = (int) Math.round(directionVector.y / length); // normalize y
        return new Position(currentPosition.getX() + directionVector.x, currentPosition.getY() + directionVector.y);

    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    /*
     * public static void main(String[] args) {
     * int x1, y1, x2, y2; // 2 is target
     * x1 = 0;
     * y1 = 0;
     * x2 = 0;
     * y2 = 100;
     * Point dirVector = new Point(x2 - x1, y2 - y1);
     * // normalize direction
     * double length = Math.sqrt(dirVector.x * dirVector.x + dirVector.y *
     * dirVector.y);
     * dirVector.x = (int) Math.round(dirVector.x / length);
     * dirVector.y = (int) Math.round(dirVector.y / length);
     * 
     * System.out.println("x: " + dirVector.x + " y: " + dirVector.y);
     * 
     * }
     */
}