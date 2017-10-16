package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {
    public static final List<String> listOfAvailableCommands = Arrays.asList (
            AddCommand.COMMAND_WORD,
            ClearCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            DeleteTagCommand.COMMAND_WORD,
            EditCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD,
            FindCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD,
            HistoryCommand.COMMAND_WORD,
            ListCommand.COMMAND_WORD,
            MultiFilterCommand.COMMAND_WORD,
            RedoCommand.COMMAND_WORD,
            SearchCommand.COMMAND_WORD,
            SelectCommand.COMMAND_WORD,
            UndoCommand.COMMAND_WORD
    );

    public static final List<String> listOfAvailableCommandAliases = Arrays.asList (
            AddCommand.COMMAND_WORD_ALIAS,
            ClearCommand.COMMAND_WORD_ALIAS,
            DeleteCommand.COMMAND_WORD_ALIAS,
            DeleteTagCommand.COMMAND_WORD_ALIAS,
            EditCommand.COMMAND_WORD_ALIAS,
            FindCommand.COMMAND_WORD_ALIAS,
            HistoryCommand.COMMAND_WORD_ALIAS,
            ListCommand.COMMAND_WORD_ALIAS,
            MultiFilterCommand.COMMAND_WORD_ALIAS,
            RedoCommand.COMMAND_WORD_ALIAS,
            SearchCommand.COMMAND_WORD_ALIAS,
            SelectCommand.COMMAND_WORD_ALIAS,
            UndoCommand.COMMAND_WORD_ALIAS
    );

    protected Model model;
    protected CommandHistory history;
    protected UndoRedoStack undoRedoStack;

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of persons.
     *
     * @param displaySize used to generate summary
     * @return summary message for persons displayed
     */
    public static String getMessageForPersonListShownSummary(int displaySize) {
        return String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, displaySize);
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of names of persons.
     *
     * @param displayList used to generate summary
     * @return summary message for names of persons displayed
     */
    public static String getMessageForPersonList(ObservableList<ReadOnlyPerson> displayList) {
        if (displayList.isEmpty()) {
            return Messages.MESSAGE_UNKNOWN_TAG;
        }

        StringBuilder builder = new StringBuilder();
        for (ReadOnlyPerson person : displayList) {
            builder.append(person.getName());
            builder.append("\n");
        }
        return  builder.toString();
    }

    /**
     * Executes the command and returns the result message.
     *
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute() throws CommandException;

    /**
     * Provides any needed dependencies to the command.
     * Commands making use of any of these should override this method to gain
     * access to the dependencies.
     */
    public void setData(Model model, CommandHistory history, UndoRedoStack undoRedoStack) {
        this.model = model;
    }
}
