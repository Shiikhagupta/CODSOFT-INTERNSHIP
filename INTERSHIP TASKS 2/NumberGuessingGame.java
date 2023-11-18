import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private final int MIN_NUMBER = 1;
    private final int MAX_NUMBER = 100;

    private int generatedNumber;
    private int attempts;
    private int roundsWon;

    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JLabel attemptsLabel;
    private JLabel roundsWonLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultLabel = new JLabel("");
        attemptsLabel = new JLabel("Attempts: 0");
        roundsWonLabel = new JLabel("Rounds Won: 0");

        guessButton.addActionListener(new GuessButtonListener());

        setLayout(new FlowLayout());
        add(new JLabel("Enter your guess (1-100): "));
        add(guessField);
        add(guessButton);
        add(resultLabel);
        add(attemptsLabel);
        add(roundsWonLabel);

        startNewRound();
    }

    private void startNewRound() {
        generatedNumber = generateRandomNumber();
        attempts = 0;
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
        resultLabel.setText("");
        attemptsLabel.setText("Attempts: 0");
        roundsWonLabel.setText("Rounds Won: " + roundsWon);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if (userGuess == generatedNumber) {
                roundsWon++;
                resultLabel.setText("Congratulations! You guessed it.");
                guessField.setEnabled(false);
                guessButton.setEnabled(false);
            } else if (userGuess < generatedNumber) {
                resultLabel.setText("Too low. Try again.");
            } else {
                resultLabel.setText("Too high. Try again.");
            }

            attemptsLabel.setText("Attempts: " + attempts);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGame game = new NumberGuessingGame();
            game.setVisible(true);
        });
    }
}
