public abstract class State {
    abstract public boolean updateState(Position position);

    abstract public double getX();

    abstract public double getY();

    abstract public Position getNextPosition(Entity entity);

    abstract public String getState();
}