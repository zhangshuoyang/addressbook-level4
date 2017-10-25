package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.ReadOnlyTask;


/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final ReadOnlyTask HOMEWORK = new TaskBuilder().withDescription("CS2103 Homework")
            .withPriority("2")
            .withDueDate("25/10/2017").build();

    public static final ReadOnlyTask SURVEY = new TaskBuilder().withDescription("Complete Peer Review Survey")
            .withPriority("1")
            .withDueDate("01/11/2017").build();


    private TypicalTasks() {} // prevents instantiation

    public static List<ReadOnlyTask> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(HOMEWORK, SURVEY));
    }

}

