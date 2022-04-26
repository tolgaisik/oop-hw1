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

}