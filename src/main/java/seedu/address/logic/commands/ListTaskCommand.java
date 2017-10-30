package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import javafx.collections.ObservableList;
import seedu.address.logic.parser.AutoCorrectCommand;
import seedu.address.model.task.ReadOnlyTask;

/**
 * Lists all tasks in the address book to the user.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "listtask";
    public static final String AUTOCOMPLETE_FORMAT = COMMAND_WORD;
    public static final String MESSAGE_USAGE = "";
    public static final String MESSAGE_SUCCESS = "Listed all tasks!";

    private AutoCorrectCommand autoCorrectCommand = new AutoCorrectCommand();

    @Override
    public CommandResult execute() {
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        ObservableList<ReadOnlyTask> listOfTasks = model.getFilteredTaskList();

        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.print("Task " + (i + 1) + " ");
            System.out.println(listOfTasks.toString());
        }

        if (autoCorrectCommand.getMessageToUser().equals("")) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(autoCorrectCommand.getMessageToUser()
                    + "\n"
                    + MESSAGE_SUCCESS);
        }
    }
}
