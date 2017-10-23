package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AutoCorrectCommand;
import seedu.address.model.task.*;
import seedu.address.model.task.exceptions.DuplicateTaskException;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds a task to the address book.
 */
public class AddTaskCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "addtask";
    public static final String COMAND_WORD_ALIAS = "at";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the address book."
            + "Parameters: "
            + "Name "
            + PREFIX_PRIORITY + "priority(0/1/2)";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book.";

    private final Task toAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code ReadOnlyTask}
     */
    public AddTaskCommand(ReadOnlyTask task) {
        toAdd = new Task(task.getName(), task.getDate(), task.getDescription(), task.getPriority());
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (DuplicateTaskException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }

}





