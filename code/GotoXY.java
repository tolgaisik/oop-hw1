public class GotoXY extends State {
    double dest_x, dest_y;

    GotoXY() {
        dest_x = Common.getRandomGenerator().nextDouble(Common.getWindowWidth());
        dest_y = Common.getRandomGenerator().nextDouble(Common.getWindowHeight());
    }

    @Override
    public double getX() {

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