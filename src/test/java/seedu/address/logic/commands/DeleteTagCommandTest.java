package seedu.address.logic.commands;

import static org.junit.Assert.assertNotEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;

import org.junit.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteCommand}.
 */
public class DeleteTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    /**
     * Ensure that after a tag is properly deleted from all contacts in the address book
     */
    public void executeFindDeletedTagFailure() {
        ObservableList<ReadOnlyPerson> listOfPersons = model.getFilteredPersonList();

        // Delete a tag from all contacts in the address book
        DeleteTagCommand deleteTagCommand = prepareCommand("friends");
        try {
            deleteTagCommand.execute();
        } catch (CommandException e) {
            e.printStackTrace();
        }

        // Check if the the tag is still present in anyone in the address book
        for (ReadOnlyPerson person : listOfPersons) {
            Set<Tag> tags = person.getTags();
            for (Tag t : tags) {
                String tagName = t.toString();
                assertNotEquals(tagName, "friends");
            }
        }
    }

    /**
     * Returns a {@code DeleteTagCommand} with the parameter {@code tag_name}.
     */
    private DeleteTagCommand prepareCommand(String tagName) {
        DeleteTagCommand deleteTagCommand = null;
        try {
            deleteTagCommand = new DeleteTagCommand(new Tag(tagName));
            deleteTagCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleteTagCommand;
    }
}
