package seedu.address.model.task;

import seedu.address.commons.exceptions.IllegalValueException;


/**
 * Represents the priority level of a task in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(int)}
 */
public class Priority {

    public static final String MESSAGE_PRIORITY_CONSTRAINTS =
            "Priority can only contain number 0, 1 or 2";
    private int value;

    /**
     * Validates given priority level.
     *
     * @throws IllegalValueException if given priority int is invalid.
     */
    public Priority(int priority) throws IllegalValueException {
        if (!isValidPriority(priority)) {
            throw new IllegalValueException(MESSAGE_PRIORITY_CONSTRAINTS);
        }
        this.value = priority;
    }

    /**
     * Returns true is the given integer is a valid priority level
     */
    public static boolean isValidPriority(int test) {
        return test <= 2 && test >= 0;
    }

    @Override
    public String toString() {
        return Integer.toString(value); }

}
