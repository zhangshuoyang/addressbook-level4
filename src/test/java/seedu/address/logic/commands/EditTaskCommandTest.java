package seedu.address.logic.commands;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditTaskCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new EditTaskCommand(null, new EditTaskCommand.EditTaskDescriptor());
    }
}

