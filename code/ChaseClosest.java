public class ChaseClosest extends State {

    @Override
    public double getX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getY() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean updateState(Position position) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Position getNextPosition(Entity entity) {
        // TODO Auto-generated method stub
        return entity.getPosition();
    }

    @Override
    public String getState() {
        return this.getClass().getName();
    }

}