public class Rest extends State {
    /**
     * rest state returns the position itself
     */
    @Override
    public Position next(Entity entity) {
        return entity.getPosition();
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}