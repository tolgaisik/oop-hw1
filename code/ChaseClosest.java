import java.util.List;

/**
 * @classdesc this is a class that implements the State
 *            interface to chase the closest gold order.
 */
public class ChaseClosest extends State {
    int speed = Common.getRandomGenerator().nextInt(3) + 2;

    /**
     * calculates the next position of the entity
     * finds the closest gold order and moves towards the closest gold order
     * 
     * @param Entity
     * 
     */
    @Override
    public Position next(Entity entity) {
        // this is not working as expected
        // sometimes corporation is not chasing a gold order at all
        double min = Double.MAX_VALUE;
        Integer index = -1;
        List<Order> orders = Common.getOrders();
        int SIZE = orders.size();
        /**
         * get orders and find the closest by calculating the distance between them
         * find the shortest distance then check if it is a gold order and move towards
         * to the one
         * 
         */
        for (int i = 0; i < SIZE; i++) {
            Order order = orders.get(i);
            double distance = distance(order.getPosition(), entity.getPosition());
            if (distance < min) {
                min = distance;
                index = i;
            }
        }
        if (index == -1)
            return entity.getPosition();
        Order order = orders.get(index);
        if (order instanceof GoldOrder) {
            Position target = order.getPosition();
            Position pos = entity.getPosition();
            Position directionVector = new Position(target.getX() - pos.getX(), target.getY() - pos.getY());
            double length = Math.sqrt(
                    directionVector.getX() * directionVector.getX() + directionVector.getY() * directionVector.getY());
            directionVector.setX(speed * directionVector.getX() / length + pos.getX());
            directionVector.setY(speed * directionVector.getY() / length + pos.getY());
            // now we have a normalized direction vector
            return directionVector;
        }
        return entity.getPosition();
    }

    /**
     * returns the name of the class
     * 
     * @return String
     */
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    /**
     * calculates the distance between two position
     * 
     * @param position1
     * @param position2
     * @return
     */
    public double distance(Position position1, Position position2) {
        return Math.sqrt(
                Math.pow(position1.getX() - position2.getX(), 2) + Math.pow(position1.getY() - position2.getY(), 2));
    }

}