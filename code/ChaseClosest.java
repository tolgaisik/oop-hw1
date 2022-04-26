import java.util.List;

/**
 * @classdesc this is a class that implements the State
 *            interface to chase the closest order.
 */
public class ChaseClosest extends State {

    @Override
    public Position next(Entity entity) {
        double min = Double.MAX_VALUE;
        Integer index = -1;
        List<Order> orders = Common.getOrders();
        int SIZE = orders.size();
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
        Position target = order.getPosition();
        Position pos = entity.getPosition();
        Position directionVector = new Position(target.getX() - pos.getX(), target.getY() - pos.getY());
        double length = Math.sqrt(
                directionVector.getX() * directionVector.getX() + directionVector.getY() * directionVector.getY());
        directionVector.setX(directionVector.getX() / length);
        directionVector.setY(directionVector.getY() / length);
        return entity.getPosition();

    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    public double distance(Position position1, Position position2) {
        return Math.sqrt(
                Math.pow(position1.getX() - position2.getX(), 2) + Math.pow(position1.getY() - position2.getY(), 2));
    }

}