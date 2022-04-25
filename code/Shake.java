/**
 * Randomly moves the entity couple of pixels around
 * This is a movement behavior
 * 
 * @params
 */
public class Shake extends State {
    /**
     * It takes an Entity object and updates it current state which is Shake
     * Shake does a random movement to a random direction.
     */
    @Override
    public Position next(Entity entity) {

        /**
         * get current position and update
         */
        Position p = new Position(entity.getPosition().getX() + Common.getRandomGenerator().nextDouble(-3, 3),
                entity.getPosition().getY() + Common.getRandomGenerator().nextDouble(-3, 3));
        return p;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}