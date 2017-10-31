package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ClearTaskCommandTest {


    @Test
    public void executeEmptyAddressBookSuccess() {
        Model model = new ModelManager();
        assertCommandSuccess(prepareCommand(model), model, ClearTaskCommand.MESSAGE_SUCCESS, model);
    }

    /**
     * Generates a new {@code ClearCommand} which upon execution, clears the contents in {@code model}.
     */
    private ClearTaskCommand prepareCommand(Model model) {
        ClearTaskCommand command = new ClearTaskCommand();
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

}
