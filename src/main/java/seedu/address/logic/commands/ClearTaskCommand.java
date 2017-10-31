package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.logic.parser.AutoCorrectCommand;
import seedu.address.model.task.ReadOnlyTask;

/**
 * Clears the task list.
 */
public class ClearTaskCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "cleartask";
    public static final String COMMAND_WORD_ALIAS = "ctask";
    public static final String AUTOCOMPLETE_FORMAT = COMMAND_WORD;
    public static final String MESSAGE_USAGE = "";
    public static final String MESSAGE_SUCCESS = "Task list has been cleared!";
    private AutoCorrectCommand autoCorrectCommand = new AutoCorrectCommand();


    @Override
    public CommandResult executeUndoableCommand() {
        requireNonNull(model);
        List<ReadOnlyTask> lastShownList = model.getFilteredTaskList();
        lastShownList.clear();

        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        if (autoCorrectCommand.getMessageToUser().equals("")) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(autoCorrectCommand.getMessageToUser()
                    + "\n"
                    + MESSAGE_SUCCESS);
        }
    }
}
