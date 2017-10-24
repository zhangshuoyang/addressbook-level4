package seedu.address.logic.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final HashMap<String, String> mapOfAvailableCommands = new HashMap<>();

    private static final List<String> listOfAvailableCommandAliases = Arrays.asList (
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

    public static Map<String, String> getMapOfAvailableCommands() {
        // Initialize map of available commands the first time autocomplete is used
        if (mapOfAvailableCommands.isEmpty()) {
            intializeCommandMap();
        }
        return mapOfAvailableCommands;
    }

    public static List<String> getListOfAvailableCommandAliases() {
        return listOfAvailableCommandAliases;
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

    /**
     *
     * Initialises the mapOfAvailableCommands when it is being used for the first time
     * (i.e. this method is called only once throughout the program execution)
     *
     */
    private static void intializeCommandMap() {
        mapOfAvailableCommands.put(AddCommand.COMMAND_WORD, AddCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(ClearCommand.COMMAND_WORD, ClearCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(DeleteCommand.COMMAND_WORD, DeleteCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(DeleteTagCommand.COMMAND_WORD, DeleteTagCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(EditCommand.COMMAND_WORD, EditCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(ExitCommand.COMMAND_WORD, ExitCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(FindCommand.COMMAND_WORD, FindCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(HelpCommand.COMMAND_WORD, HelpCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(HistoryCommand.COMMAND_WORD, HistoryCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(ListCommand.COMMAND_WORD, ListCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(MultiFilterCommand.COMMAND_WORD, MultiFilterCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(RedoCommand.COMMAND_WORD, RedoCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(SearchCommand.COMMAND_WORD, SearchCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(SelectCommand.COMMAND_WORD, SelectCommand.AUTOCOMPLETE_FORMAT);
        mapOfAvailableCommands.put(UndoCommand.COMMAND_WORD, UndoCommand.AUTOCOMPLETE_FORMAT);
    }
}
