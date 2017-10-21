package seedu.address.ui;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.NewResultAvailableEvent;
import seedu.address.commons.util.AudioUtil;
import seedu.address.commons.util.AutoCompleteUtil;
import seedu.address.logic.ListElementPointer;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final Logger logger = LogsCenter.getLogger(CommandBox.class);
    private final Logic logic;
    private ListElementPointer historySnapshot;

    @FXML
    private TextField commandTextField;

    public CommandBox(Logic logic) {
        super(FXML);
        this.logic = logic;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        historySnapshot = logic.getHistorySnapshot();
    }

    /**
     * Handles the key press event, {@code keyEvent}.
     */
    @FXML
    private void handleKeyPress(KeyEvent keyEvent) {
        // Handles cases where multple keys are pressed simultaneously
        if (keyEvent.isControlDown()) {
            keyEvent.consume();
            launchExtendedAutocomplete();
            commandTextField.requestFocus();
            commandTextField.end();
            return;
        }

        // Handles single key press
        switch (keyEvent.getCode()) {
        case UP:
            // As up and down buttons will alter the position of the caret,
            // consuming it causes the caret's position to remain unchanged
            keyEvent.consume();
            navigateToPreviousInput();
            break;
        case DOWN:
            keyEvent.consume();
            navigateToNextInput();
            break;
        case TAB:
            keyEvent.consume();
            launchAutoComplete();
            commandTextField.requestFocus(); // focus the caret in the command box after autocomplete
            commandTextField.end(); // move caret to the end of the completed command
            break;
        default:
            // let JavaFx handle the keypress
        }
    }

    /**
     * Launches extended auto complete mechanism in logic
     * when the special key is pressed in the Command Box
     * Extended auto complete fills the command box with the full format of
     * a command rather than just the command word
     */
    private void launchExtendedAutocomplete() {
        String userCommand = commandTextField.getText().split("\\s+")[0];
        System.out.println("The user's command is " + userCommand);
        Map<String, String> commandFormatMap = Command.getMapOfAvailableCommands();
        if (commandFormatMap.containsKey(userCommand)) {
            commandTextField.setText(commandFormatMap.get(userCommand));
        }
    }

    /**
     * Launches auto complete mechanism in logic
     * when the auto complete hotkey is pressed in the Command Box
     */
    private void launchAutoComplete() {
        List<String> listOfPossibleCommandWords = logic.getPossibleCommands(commandTextField.getText());
        final int numberOfCommandsOnOneLine = 6;
        int count = 0;
        StringBuffer feedbackToUser = new StringBuffer();

        /*
         * Append all possible command words into a single formatted string
         */
        for (String commandWord : listOfPossibleCommandWords) {
            // format the command so that it will be aligned when displayed to user
            String s = String.format("%-20s", commandWord);
            feedbackToUser.append(s);
            count++;
            if (count == numberOfCommandsOnOneLine) {
                count = 0;
                feedbackToUser.append("\n");
            }
        }

        logger.info(listOfPossibleCommandWords.toString());

        if (listOfPossibleCommandWords.size() == 1) {
            /*
             * there is only one possible command for the given prefix
             * directly complete the command in the command box
             */
            commandTextField.setText(listOfPossibleCommandWords.get(0));
            raise(new NewResultAvailableEvent(""));
        } else {
            // Display the list of command words to the user
            if (feedbackToUser.length() > 0) {
                raise(new NewResultAvailableEvent(feedbackToUser.toString()));
            } else {
                // Clear suggestions if there were any when the user types a wrong prefix
                raise(new NewResultAvailableEvent(""));
            }
        }
    }

    /**
     * Updates the text field with the previous input in {@code historySnapshot},
     * if there exists a previous input in {@code historySnapshot}
     */
    private void navigateToPreviousInput() {
        assert historySnapshot != null;
        if (!historySnapshot.hasPrevious()) {
            return;
        }

        replaceText(historySnapshot.previous());
    }

    /**
     * Updates the text field with the next input in {@code historySnapshot},
     * if there exists a next input in {@code historySnapshot}
     */
    private void navigateToNextInput() {
        assert historySnapshot != null;
        if (!historySnapshot.hasNext()) {
            return;
        }

        replaceText(historySnapshot.next());
    }

    /**
     * Sets {@code CommandBox}'s text field with {@code text} and
     * positions the caret to the end of the {@code text}.
     */
    private void replaceText(String text) {
        commandTextField.setText(text);
        commandTextField.positionCaret(commandTextField.getText().length());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandInputChanged() {
        try {
            CommandResult commandResult = logic.execute(commandTextField.getText());
            initHistory();
            historySnapshot.next();
            // process result of the command
            commandTextField.setText("");
            raise(new NewResultAvailableEvent(commandResult.feedbackToUser));

            // Play success sound
            AudioUtil.playClip("command-success.wav");
            logger.info("Result: " + commandResult.feedbackToUser);
        } catch (CommandException | ParseException e) {
            initHistory();
            // handle command failure
            setStyleToIndicateCommandFailure();
            logger.info("Invalid command: " + commandTextField.getText());
            raise(new NewResultAvailableEvent(e.getMessage()));

            // Play failure sound
            try {
                AudioUtil.playClip("command-failure.wav");
            } catch (IllegalArgumentException | MalformedURLException err) {
                err.printStackTrace();
            }
        } catch (IllegalArgumentException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the history snapshot.
     */
    private void initHistory() {
        historySnapshot = logic.getHistorySnapshot();
        // add an empty string to represent the most-recent end of historySnapshot, to be shown to
        // the user if she tries to navigate past the most-recent end of the historySnapshot.
        historySnapshot.add("");
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

}
