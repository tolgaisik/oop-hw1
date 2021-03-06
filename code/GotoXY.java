import java.awt.*;

/**
 * @classdesc implements State interface to go to a random position within the
 *            current frame.
 */
public class GotoXY extends State {
    // created randomly destination points over the safe area sandbox
    double dest_x = Common.getRandomGenerator().nextDouble(Common.getSandBox().width);
    double dest_y = Common.getRandomGenerator().nextDouble(Common.getSandBox().height + Common.getSandBox().y);

    /**
     * calculates a vector based on the destionation and entity object
     * then normalize it
     * then round the values to find nearest pixel to the direction
     * 
     * @param Entity
     * 
     */
    @Override
    public Position next(Entity entity) {

        Position currentPosition = entity.getPosition();
        Point directionVector = new Point((int) (this.dest_x - currentPosition.getX()),
                (int) (this.dest_y - currentPosition.getY()));
        // vector magnitude
        double length = Math.sqrt(directionVector.x * directionVector.x + directionVector.y * directionVector.y);
        directionVector.x = (int) Math.round(2 * (directionVector.x / length)); // normalize x
        directionVector.y = (int) Math.round(2 * (directionVector.y / length)); // normalize y
        return new Position(currentPosition.getX() + directionVector.x, currentPosition.getY() + directionVector.y);

    }

    /**
     * returns the name of the class
     */
    @Override
    public String getName() {
        return this.getClass().getName();
    }

}