public class Rest extends State {

    @Override
    public Position next(Entity entity) {
        return entity.getPosition();
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}