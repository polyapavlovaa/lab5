package commands;

/**
 * interface with methods that implement command classes
 */
public interface Commandable {
    String getDescription();
    boolean execute (String argument);

    default String getName() {
        return getClass().getSimpleName().toLowerCase();
    }
}
