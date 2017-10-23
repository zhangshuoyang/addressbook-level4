package seedu.address.model.task.exceptions;

import seedu.address.commons.exceptions.DuplicateDataException;

public class DuplicateTaskException extends DuplicateDataException {

    public DuplicateTaskException() {
        super("Operation would result in duplicated tasks.");
    }

}
