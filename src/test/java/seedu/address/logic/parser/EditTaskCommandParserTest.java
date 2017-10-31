package seedu.address.logic.parser;

import org.junit.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.testutil.EditTaskDescriptorBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DUEDATE_TASK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_TASK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESC__AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DUEDATE__AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_PRIORITY__AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;

public class EditTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TASK_DESC__AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DESC_TASK_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + DESC_TASK_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TASK.get(0);
        String userInput = targetIndex.getOneBased() + DESC_TASK_AMY + PRIORITY_TASK_AMY
                + DUEDATE_TASK_AMY;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDescription(VALID_TASK_DESC__AMY)
                .withPriority(VALID_TASK_PRIORITY__AMY).withDueDate(VALID_TASK_DUEDATE__AMY).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK.get(0);
        String userInput = targetIndex.getOneBased() + DESC_TASK_AMY + PRIORITY_TASK_AMY;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDescription(VALID_TASK_DESC__AMY)
                .withPriority(VALID_TASK_PRIORITY__AMY).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // description
        Index targetIndex = INDEX_THIRD_TASK.get(0);
        String userInput = targetIndex.getOneBased() + DESC_TASK_AMY;
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDescription(VALID_TASK_DESC__AMY).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        //assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        userInput = targetIndex.getOneBased() + PRIORITY_TASK_AMY;
        descriptor = new EditTaskDescriptorBuilder().withPriority(VALID_TASK_PRIORITY__AMY).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duedate
        userInput = targetIndex.getOneBased() + DUEDATE_TASK_AMY;
        descriptor = new EditTaskDescriptorBuilder().withDueDate(VALID_TASK_DUEDATE__AMY).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK.get(0);
        String userInput = targetIndex.getOneBased() + DESC_TASK_AMY + PRIORITY_TASK_AMY
                + DUEDATE_TASK_AMY + DESC_TASK_AMY + PRIORITY_TASK_AMY + DUEDATE_TASK_AMY;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDescription(VALID_TASK_DESC__AMY)
                .withPriority(VALID_TASK_PRIORITY__AMY).withDueDate(VALID_TASK_DUEDATE__AMY).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
