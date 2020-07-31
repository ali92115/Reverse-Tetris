package view;
import model.Board;
import model.Point;
import model.TetrisPiece;
import java.awt.*;
import javax.swing.*;

/**
 * This class is responsible for creating the next Piece and Instructions.
 * @author Ali Iftakhar
 * @version 12/12/2019
 */
public class NextPiecePanel extends JPanel {
    /**
     * This is the board we will use to play.
     */
    private final Board gameBoard;
    
    /**
     * This is our constructor for initializing the gameBoard.
     * @param theBoard the board we want to be initialized to
     */
    public NextPiecePanel(final Board theBoard) {
        super();
        gameBoard = theBoard;
    }
    
    /**
     * paintComponent draws the next piece and Insturctions for game.
     * 
     * @param g for drawing the shapes and drawing.
     */
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g;
        final Rectangle rightPanel = new Rectangle(0, 0, 280, 400);
        g2.setColor(Color.MAGENTA);
        g2.setStroke(new BasicStroke(10.0f));
        g2.draw(rightPanel);
        g2.setColor(new Color(55, 105, 229));  // Just some random color. 
        g2.drawString("The Next Piece Will Be ", 65, 30);
        g2.setColor(Color.black);
        g2.drawString("CONTROLS", 10, 150);
        g2.drawString("Left Arrow or A -> Left", 10, 175);
        g2.drawString("Right Arrow or D -> Right", 10, 200);
        g2.drawString("Down Arrow or S -> Drop Down", 10, 225);
        g2.drawString("Up Arrow or W -> Rotate CCW", 10, 250);
        g2.drawString("Space -> Immediate Drop", 10, 275);
        g2.drawString("N After losing -> New Game", 10, 325);
        g2.setColor(Color.WHITE);
        g2.drawString("P -> PAUSE", 10, 300);
        Font f = new Font("serif", Font.PLAIN, 20);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Courier", Font.PLAIN, 30));
        g2.drawString("SCORE: " + gameBoard.getScore(), 10, 375);
        g2.setStroke(new BasicStroke(1.0f));
        g2.setColor(Color.CYAN);
        
        if (TetrisGUI.start) {
            final TetrisPiece nextPiece = gameBoard.getNextPiece();
            final Point[] currentPoints = nextPiece.getPoints();
            for (int i = 0; i < currentPoints.length; i++) {         
                final Rectangle rect = new Rectangle((currentPoints[i].x() + 5) 
                                                     * TetrisGUI.BLOCK_SIZE,
                                                     (currentPoints[i].y() + 2) 
                                                     * TetrisGUI.BLOCK_SIZE, 
                                                     TetrisGUI.BLOCK_SIZE
                                                     , TetrisGUI.BLOCK_SIZE);
            
                g2.fill(rect);
                repaint();
            }
        }
        
        g2.setColor(Color.CYAN.darker());
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                final Rectangle rect = new Rectangle((i + 4) * TetrisGUI.BLOCK_SIZE,
                                               (j + 2) * TetrisGUI.BLOCK_SIZE, 
                                               TetrisGUI.BLOCK_SIZE, TetrisGUI.BLOCK_SIZE);
                g2.draw(rect);
            }
        }
        
    }
}
