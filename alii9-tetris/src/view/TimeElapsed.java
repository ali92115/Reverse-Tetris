package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.Board;;

public class TimeElapsed extends JPanel implements ActionListener {
    
    private Board gameBoard;
    
    public TimeElapsed(Board theBoard) {
        super();
        gameBoard = theBoard;
    }
    
    public void actionPerformed(final ActionEvent event) {
        gameBoard.step();
        repaint();
    }
}
