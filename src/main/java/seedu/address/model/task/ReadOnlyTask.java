package seedu.address.model.task;

import java.util.Date;

import javafx.beans.property.ObjectProperty;




/**
 * A read-only immutable interface for a Task in the addressbook.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyTask {

    ObjectProperty<Name> nameProperty();
    Name getName();
    ObjectProperty<Date> dateProperty();
    Date getDate();
    ObjectProperty<Description> descriptionProperty();
    Description getDescription();
    ObjectProperty<Priority> priorityProperty();
    Priority getPriority();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyTask other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getDate().equals(this.getDate())
                && other.getDescription().equals(this.getDescription())
                && other.getPriority().equals(this.getPriority()));
    }

    /**
     * Formats the person as text, showing all contact details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Description: ")
                .append(getDescription())
                .append(" Date: ")
                .append(getDate())
                .append(" Priority ")
                .append(getPriority());
        return builder.toString();
    }

}
