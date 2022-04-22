public class Shake extends State {

    @Override
    public double getX() {
        return Common.getRandomGenerator().nextDouble(10);
    }

    @Override
    public double getY() {
        return Common.getRandomGenerator().nextDouble(10);
    }

    @Override
    public boolean updateState(Position position) {
        return false;
    }

    @Override
    public Position getNextPosition(Entity entity) {
        Position p = new Position(entity.getPosition().getX() + Common.getRandomGenerator().nextDouble(-3, 3),
                entity.getPosition().getY() + Common.getRandomGenerator().nextDouble(-3, 3));
        return p;
    }

    @Override
    public String getState() {
        return this.getClass().getName();
    }
}