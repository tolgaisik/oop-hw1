public class Rest extends State {

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public boolean updateState(Position position) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Position getNextPosition(Entity entity) {
        return entity.getPosition();
    }

    @Override
    public String getState() {
        return this.getClass().getName();
    }
}