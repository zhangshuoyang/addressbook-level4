package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DUEDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.task.ReadOnlyTask;


/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an add command string for adding the {@code task}.
     */
    public static String getAddCommand(ReadOnlyTask task) {
        return AddCommand.COMMAND_WORD + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(ReadOnlyTask task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_DESCIPTION + task.getDescription().descriptionName + " ");
        sb.append(PREFIX_PRIORITY + task.getPriority().value + " ");
        sb.append(PREFIX_DUEDATE + task.getDueDate().date + " ");
        return sb.toString();
    }
}
