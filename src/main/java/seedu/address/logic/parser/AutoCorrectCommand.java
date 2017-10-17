package seedu.address.logic.parser;

import java.util.ArrayList;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MultiFilterCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.UndoCommand;

public class AutoCorrectCommand {

    public static ArrayList<String> editDistance1(String word) {
        ArrayList<String> results = new ArrayList<String>();
        String formattedWord = word.toLowerCase();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        //Adding any one character (from teh alphabet) anywhere in the word.
        for(int i=0; i <= formattedWord.length(); i++) {
            for(int j=0; j < alphabet.length(); j++) {
                String newWord = formattedWord.substring(0,i) + alphabet.charAt(j) + formattedWord.substring(i, formattedWord.length());
                results.add(newWord);
            }
        }

        //Removing any one character from the words
        if(word.length() > 1) {
            for(int i=0; i<formattedWord.length(); i++) {
                String newWord = formattedWord.substring(0, i) + formattedWord.substring(i+1, formattedWord.length());
                results.add(newWord);
            }
        }

        //Transposing (switching) the order of anyt wo adjacent characters in a word.
        if(word.length() > 1) {
            for (int i=0; i<word.length() - 1; i++) {
                String newWord = formattedWord.substring(0, i) + formattedWord.charAt(i+1)  +formattedWord.charAt(i) + formattedWord.substring(i+2, formattedWord.length());
                results.add(newWord);
            }
        }

        //Substituting any character in the word with another character.
        for (int i=0; i<word.length(); i++) {
            for(int j=0; j<alphabet.length(); j++) {
                String newWord = formattedWord.substring(0,i) + alphabet.charAt(j) + formattedWord.substring(i+1, formattedWord.length());
                results.add(newWord);
            }
        }
        return results;
    }

    /* Given a word, attempts to correct the spelling of that word.
    - First, if the word is a known word, return the word.
    - Second, if the word has any known words edit-distance 1 away, return the one with
      the highest frequency, as recorded in NWORDS.
    - Third, if the word has any known words edit-distance 2 away, return the one with
      the highest frequency, as recorded in NWORDS. (HINT: what does applying
      "editDistance1" *again* to each word of its own output do?)
    - Finally, if no good replacements are found, return the word.
    */
    public static String correctWord (String word) {

        word = word.toLowerCase();
        ArrayList<String> commandPool = getCommandPool();

        if(commandPool.contains(word)) {
            return word;
        }

        ArrayList<String> editDistance1WordsAdd = editDistance1(AddCommand.COMMAND_WORD);
        ArrayList<ArrayList<String>> editDistance2WordsAdd = new ArrayList<ArrayList<String>>();
        for (String editDistance1WordAdd : editDistance1WordsAdd) {
            editDistance2WordsAdd.add(editDistance1(editDistance1WordAdd));
        }

        if(editDistance1WordsAdd.contains(word)) {
            return AddCommand.COMMAND_WORD;
        }

        for(ArrayList<String> editDistance2WordAdd : editDistance2WordsAdd) {
            if(editDistance2WordAdd.contains(word)) return AddCommand.COMMAND_WORD;
        }

        ArrayList<String> editDistance1WordsDelete = editDistance1(DeleteCommand.COMMAND_WORD);
        ArrayList<ArrayList<String>> editDistance2WordsDelete = new ArrayList<ArrayList<String>>();
        for (String editDistance1WordDelete : editDistance1WordsDelete) {
            editDistance2WordsDelete.add(editDistance1(editDistance1WordDelete));
        }

        if(editDistance1WordsDelete.contains(word)) {
            return DeleteCommand.COMMAND_WORD;
        }

        for(ArrayList<String> editDistance2WordDelete : editDistance2WordsDelete) {
            if(editDistance2WordDelete.contains(word)) return DeleteCommand.COMMAND_WORD;
        }

        ArrayList<String> editDistance1WordsSearch = editDistance1(SearchCommand.COMMAND_WORD);
        ArrayList<ArrayList<String>> editDistance2WordsSearch = new ArrayList<ArrayList<String>>();
        for (String editDistance1WordSearch : editDistance1WordsSearch) {
            editDistance2WordsSearch.add(editDistance1(editDistance1WordSearch));
        }

        if(editDistance1WordsSearch.contains(word)) {
            return SearchCommand.COMMAND_WORD;
        }

        for(ArrayList<String> editDistance2WordSearch : editDistance2WordsSearch) {
            if(editDistance2WordSearch.contains(word)) return SearchCommand.COMMAND_WORD;
        }

        return "no such command";
    }

    public static ArrayList<String> getCommandPool () {

        ArrayList<String> commandPool = new ArrayList<String>();
        commandPool.add(FindCommand.COMMAND_WORD);
        commandPool.add(AddCommand.COMMAND_WORD);
        commandPool.add(SearchCommand.COMMAND_WORD);
        commandPool.add(DeleteCommand.COMMAND_WORD);
        commandPool.add(DeleteTagCommand.COMMAND_WORD);
        commandPool.add(ClearCommand.COMMAND_WORD);
        commandPool.add(EditCommand.COMMAND_WORD);
        commandPool.add(ExitCommand.COMMAND_WORD);
        commandPool.add(HelpCommand.COMMAND_WORD);
        commandPool.add(HistoryCommand.COMMAND_WORD);
        commandPool.add(ListCommand.COMMAND_WORD);
        commandPool.add(MultiFilterCommand.COMMAND_WORD);
        commandPool.add(RedoCommand.COMMAND_WORD);
        commandPool.add(SelectCommand.COMMAND_WORD);
        commandPool.add(UndoCommand.COMMAND_WORD);

        return commandPool;
    }

}
