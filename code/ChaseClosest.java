/**
 * @classdesc this is a class that implements the State
 *            interface to chase the closest order.
 */
public class ChaseClosest extends State {

    @Override
    public Position next(Entity entity) {
        return entity.getPosition();
    }

    @Override
    public String getState() {
        return this.getClass().getName();
    }

}