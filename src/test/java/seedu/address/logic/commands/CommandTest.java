package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Tests certain fields in the Command class
 */
public class CommandTest {
    @Test
    /**
     * Check for duplicate command aliases.
     */
    public void execute_findDuplicateCommandAliases_errorIfFound() {
         Set<String> uniqueAliases = new HashSet<>();
         uniqueAliases.addAll(Command.listOfAvailableCommandAliases);
         assertEquals(Command.listOfAvailableCommandAliases.size(), uniqueAliases.size());
    }
}
