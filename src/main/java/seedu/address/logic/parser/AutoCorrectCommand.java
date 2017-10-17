package seedu.address.logic.parser;

import java.util.ArrayList;

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
        ArrayList<String> commandPool = new ArrayList<String>();
        commandPool.add("find");
        commandPool.add("add");
        commandPool.add("search");
        commandPool.add("delete");

        if(commandPool.contains(word)) {
            return word;
        }

        ArrayList<String> editDistance1WordsAdd = editDistance1("add");
        ArrayList<ArrayList<String>> editDistance2WordsAdd = new ArrayList<ArrayList<String>>();
        for (String editDistance1WordAdd : editDistance1WordsAdd) {
            editDistance2WordsAdd.add(editDistance1(editDistance1WordAdd));
        }

        if(editDistance1WordsAdd.contains(word)) {
            return "add";
        }

        for(ArrayList<String> editDistance2WordAdd : editDistance2WordsAdd) {
            if(editDistance2WordAdd.contains(word)) return "add";
        }

        ArrayList<String> editDistance1WordsDelete = editDistance1("delete");
        ArrayList<ArrayList<String>> editDistance2WordsDelete = new ArrayList<ArrayList<String>>();
        for (String editDistance1WordDelete : editDistance1WordsDelete) {
            editDistance2WordsDelete.add(editDistance1(editDistance1WordDelete));
        }

        if(editDistance1WordsDelete.contains(word)) {
            return "delete";
        }

        for(ArrayList<String> editDistance2WordDelete : editDistance2WordsDelete) {
            if(editDistance2WordDelete.contains(word)) return "delete";
        }

        ArrayList<String> editDistance1WordsSearch = editDistance1("search");
        ArrayList<ArrayList<String>> editDistance2WordsSearch = new ArrayList<ArrayList<String>>();
        for (String editDistance1WordSearch : editDistance1WordsSearch) {
            editDistance2WordsSearch.add(editDistance1(editDistance1WordSearch));
        }

        if(editDistance1WordsSearch.contains(word)) {
            return "search";
        }

        for(ArrayList<String> editDistance2WordSearch : editDistance2WordsSearch) {
            if(editDistance2WordSearch.contains(word)) return "search";
        }

        return "no such command";
    }

}
