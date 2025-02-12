import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private boolean gameOver = false;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        // Initialize buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton(" ");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) return; // Stop actions if the game is over

        JButton clickedButton = (JButton) e.getSource();

        // Find the clicked button
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col] == clickedButton) {
                    if (buttons[row][col].getText().equals(" ")) {
                        buttons[row][col].setText(String.valueOf(currentPlayer));

                        if (checkWin(currentPlayer)) {
                            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                            gameOver = true;
                            return;
                        } else if (isDraw()) {
                            JOptionPane.showMessageDialog(this, "It's a draw!");
                            gameOver = true;
                            return;
                        }

                        // Switch player
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    }
                }
            }
        }
    }

    private boolean checkWin(char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((buttons[i][0].getText().equals(String.valueOf(player)) &&
                 buttons[i][1].getText().equals(String.valueOf(player)) &&
                 buttons[i][2].getText().equals(String.valueOf(player))) ||

                (buttons[0][i].getText().equals(String.valueOf(player)) &&
                 buttons[1][i].getText().equals(String.valueOf(player)) &&
                 buttons[2][i].getText().equals(String.valueOf(player)))) {
                return true;
            }
        }
        return (buttons[0][0].getText().equals(String.valueOf(player)) &&
                buttons[1][1].getText().equals(String.valueOf(player)) &&
                buttons[2][2].getText().equals(String.valueOf(player))) ||

               (buttons[0][2].getText().equals(String.valueOf(player)) &&
                buttons[1][1].getText().equals(String.valueOf(player)) &&
                buttons[2][0].getText().equals(String.valueOf(player)));
    }

    private boolean isDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals(" ")) {
                    return false; // Still moves left
                }
            }
        }
        return true; // No empty spaces left
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
