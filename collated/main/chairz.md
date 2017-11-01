# chairz
###### \java\seedu\address\logic\commands\DeleteCommand.java
``` java
        for (Index i : targetIndex) {
            if (i.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }

        String result = "";
        Collections.sort(targetIndex);
        for (Index i : targetIndex) {
            ReadOnlyPerson personToDelete = lastShownList.get(i.getZeroBased());

            try {
                model.deletePerson(personToDelete);
                if (targetIndex.size() == 1) {
                    result = result.concat(personToDelete.toString());
                } else {
                    result = result.concat("\n" + personToDelete.toString());
                }
            } catch (PersonNotFoundException pnfe) {
                assert false : "The target person cannot be missing";
            }

        }

        if (autoCorrectCommand.getMessageToUser().equals("")) {
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, result));
        } else {
            return new CommandResult(autoCorrectCommand.getMessageToUser()
                    + "\n"
                    + String.format(MESSAGE_DELETE_PERSON_SUCCESS, result));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && this.targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
```
###### \java\seedu\address\model\task\Description.java
``` java
/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_DESCRIPTION_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String DESCRIPTION_VALIDATION_REGEX = ".+";

    public final String descriptionName;

    /**
     * Validates given description for a task.
     *
     * @throws IllegalValueException if the given description string is invalid.
     */
    public Description(String description) throws IllegalValueException {
        requireNonNull(description);
        String descriptionName = description.trim();
        if (!isValidDescription(descriptionName)) {
            throw new IllegalValueException(MESSAGE_DESCRIPTION_CONSTRAINTS);
        }
        this.descriptionName = descriptionName;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(DESCRIPTION_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && this.descriptionName.equals(((Description) other).descriptionName)); // state check
    }

    @Override
    public int hashCode() {
        return descriptionName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return descriptionName;
    }
}
```
###### \java\seedu\address\model\task\Name.java
``` java
/**
 * Represents a task name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Task names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Name(String name) throws IllegalValueException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!isValidName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.fullName = trimmedName;
    }

    /**
     * Returns true if a given string is a valid person name.
     */
    public static boolean isValidName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}

```
