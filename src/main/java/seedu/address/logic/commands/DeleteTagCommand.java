package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AutoCorrectCommand;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;

/**
 * Deletes a person identified using it's last displayed index from the address book.
 */
public class DeleteTagCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "deletetag";
    public static final String COMMAND_WORD_ALIAS = "dt";
    public static final String AUTOCOMPLETE_FORMAT = COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a tag from all records.\n"
            + "Example: " + COMMAND_WORD + " friends";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted Tag: %1$s";

    private final Tag tagToDelete;

    private AutoCorrectCommand autoCorrectCommand = new AutoCorrectCommand();

    //@@author lancehaoh
    public DeleteTagCommand(Tag tagToDelete) {
        this.tagToDelete = tagToDelete;
    }

    //@@author lancehaoh
    @Override
    public CommandResult executeUndoableCommand() {
        boolean tagWasDeleted = model.deleteTag(tagToDelete);

        String messageToUser = (!tagWasDeleted ? "There is no such tag." : MESSAGE_DELETE_TAG_SUCCESS);

        if (autoCorrectCommand.getMessageToUser().equals("")) {
            return new CommandResult(String.format(messageToUser, tagToDelete));
        } else {
            return new CommandResult(autoCorrectCommand.getMessageToUser()
                    + "\n"
                    + String.format(MESSAGE_DELETE_TAG_SUCCESS, tagToDelete));
        }
    }
}
