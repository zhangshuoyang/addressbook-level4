package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Tests certain fields in the Command class
 */
public class CommandTest {
    //@@author lancehaoh
    @Test
    /**
     * Check for duplicate command aliases.
     */
    public void executeFindDuplicateCommandAliasesErrorIfFound() {
        Set<String> uniqueAliases = new HashSet<>();
        uniqueAliases.addAll(Command.getListOfAvailableCommandAliases());
        assertEquals(Command.getListOfAvailableCommandAliases().size(), uniqueAliases.size());
    }
}
