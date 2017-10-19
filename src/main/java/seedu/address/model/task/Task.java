package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Date;
import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask {

    private ObjectProperty<Name> name;
    private ObjectProperty<Date> date;
    private ObjectProperty<Description> description;
    private ObjectProperty<Priority> priority;

    public Task(Name name, Date date, Description description, Priority priority) {
        requireAllNonNull(name, date, description, priority);
        this.name = new SimpleObjectProperty<>(name);
        this.date = new SimpleObjectProperty<>(date);
        this.description = new SimpleObjectProperty<>(description);
        this.priority = new SimpleObjectProperty<>(priority);

    }

    public void setName(Name name) {
        this.name.set(requireNonNull(name));
    }


    public ObjectProperty<Name> nameProperty() {
        return name;
    }


    public Name getName() {
        return name.get();
    }

    public void setDate(Date date) {
        this.date.set(requireNonNull(date));
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }

    public Date getDate() {
        return date.get();
    }

    public void setDescription(Description description) {
        this.description.set(requireNonNull(description));
    }

    public ObjectProperty<Description> descriptionProperty() {
        return description;
    }

    public Description getDescription() {
        return description.get();
    }

    public void setPriority(Priority priority) {
        this.priority.set(requireNonNull(priority));
    }

    public ObjectProperty<Priority> priorityProperty() {
        return priority;
    }

    public Priority getPriority() {
        return priority.get();
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, date, priority);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}
