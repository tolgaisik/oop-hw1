public abstract class State {
    /**
     * Get next position of entity
     * 
     * @param entity
     * @return
     */
    abstract public Position next(Entity entity);

    /**
     * Get state name
     * 
     * @param
     * @return String state's name
     */
    abstract public String getName();
}