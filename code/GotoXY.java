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
        Position currentPosition = entity.getPosition();
        double slope = (currentPosition.getY() - dest_y) / (currentPosition.getX() - dest_x);
        double angle = Math.toDegrees(Math.atan(slope));
        return entity.getPosition();
    }

    @Override
    public String getState() {
        return this.getClass().getName();
    }

    public static void main(String[] args) {
        int x1, y1, x2, y2; // 2 is target
        x1 = 0;
        y1 = 0;
        x2 = -100;
        y2 = 100;
        double slope = (y1 - y2) / (x1 - x2);
        double angle = Math.toRadians(Math.atan(slope));
        System.out.println(angle);
    }
}