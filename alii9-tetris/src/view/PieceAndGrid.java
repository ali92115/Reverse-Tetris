package view;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import model.Board;
import model.Block;
import model.TetrisPiece;
import model.Point;
import model.MovableTetrisPiece;
import java.util.List;

/**
 * This class is responsible for creating the Grid and the current piece
 * and frozen blocks.
 * @author Ali Iftakhar
 * @version 12/12/2019
 */
public class PieceAndGrid extends JPanel {
    
    /**
     * A serialVersion.
     */
    private static final long serialVersionUID = 1L;
    /**
     * This is the board we are using for the tetris.
     */
    private final Board gameBoard;
    
    /**
     * Font size for gameover.
     */
    private static final int GAME_OVER_FONT = 30;
    /**
     * This is our constructor for this class.
     * @param theBoard is the board we will use for the tetris game.
     */
    public PieceAndGrid(final Board theBoard) {
        super();
        gameBoard = theBoard; 
        
    }
    
    /**
     * paintComponent draws the grids of tetris along with the current Piece itself.
     * 
     * @param g for drawing the shapes and drawing.
     */
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        for (int rows = 0; rows < TetrisGUI.BOARD_WIDTH; rows++) {
            for (int col = 0; col < TetrisGUI.BOARD_HEIGHT; col++) {
                final Rectangle rect = new Rectangle(rows * TetrisGUI.BLOCK_SIZE
                                               , col * TetrisGUI.BLOCK_SIZE, 
                                               TetrisGUI.BLOCK_SIZE, TetrisGUI.BLOCK_SIZE);
                g2.draw(rect);
            }
        }
        
        if(TetrisGUI.start) {
            drawFrozenBlocks(g2, TetrisGUI.BOARD_WIDTH);
            drawCurrentPiece(g2);
        }
        if (gameBoard.isGameOver()) {
            g2.setFont(new Font("TimesRoman", Font.PLAIN, GAME_OVER_FONT)); 
            g2.setColor(Color.BLACK);
            g2.drawString("GAME OVER!", 10, 220);
        }
    }
    
    /**
     * This method draws our current piece for us.
     * @param theGraphics which is what we'll use to draw.
     */
    public void drawCurrentPiece(final Graphics2D theGraphics) {
        final MovableTetrisPiece currentPiece = gameBoard.getCurrentPiece();
        final Point[] currentPoints = currentPiece.getBoardPoints();
        theGraphics.setColor(Color.red);
        for (int i = 0; i < currentPoints.length; i++) {         
            final Rectangle rect = new Rectangle(currentPoints[i].x() * TetrisGUI.BLOCK_SIZE, 
                                           currentPoints[i].y() * TetrisGUI.BLOCK_SIZE, 
                                           TetrisGUI.BLOCK_SIZE, TetrisGUI.BLOCK_SIZE);
            
            theGraphics.fill(rect);
            repaint();
        }
    }
       
     /**Draws the frozen blocks.
     * 
     * @param theG2d theG2d is the passed in object.
     * @param theWidth theWidht is the width of the board.
     */
    private void drawFrozenBlocks(final Graphics2D theG2d, final int theWidth) {
        final List<Block[]> blocks = gameBoard.myFrozenBlocks(); 
        for (int row = 0; row < blocks.size(); row++) { 
            for (int column = 0; column < theWidth; column++) { 
                final Block piece = blocks.get(row)[column];
                if (piece != null) {  
                    theG2d.setColor(Color.BLUE); 
                    theG2d.drawRect(column * TetrisGUI.BLOCK_SIZE, row * TetrisGUI.BLOCK_SIZE
                                    , TetrisGUI.BLOCK_SIZE, TetrisGUI.BLOCK_SIZE);
                    theG2d.fillRect(column * TetrisGUI.BLOCK_SIZE, row * TetrisGUI.BLOCK_SIZE
                                    , TetrisGUI.BLOCK_SIZE, TetrisGUI.BLOCK_SIZE);
                    theG2d.setColor(Color.MAGENTA.darker()); 
                    theG2d.fillRect(column * TetrisGUI.BLOCK_SIZE, row * TetrisGUI.BLOCK_SIZE
                                    , TetrisGUI.BLOCK_SIZE - 1, TetrisGUI.BLOCK_SIZE - 1);
                } 
            } 
        } 
    }
}
