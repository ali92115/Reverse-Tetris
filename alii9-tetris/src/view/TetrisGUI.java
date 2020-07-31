package view;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import model.Board;
import javax.swing.Timer;
import model.TetrisPiece;

/**
 * This class acts as a mediator for all other classes. It takes all the separate
 * works of each class and displays the final results.
 * @author Ali Iftakhar
 * @version 12/12/2019
 */
public class TetrisGUI extends KeyAdapter implements Observer {
    
    /**
     * This just makes sure we don't get null pointer issues.
     */
    public static boolean start = false;
    /**
     * This our timer for each "tick".
     */
    public static final int TIME_PER_REFRESH = 900;
    /**
     * This our block size.
     */
    public static final int BLOCK_SIZE = 20;
    
    /**
     * This our tetris panel X size.
     */
    public static final int GAME_X = 500;
    
    /**
     * This our tetris panel Y size.
     */
    public static final int GAME_Y = 440;
    
    /**
     * This our board width.
     */
    
    public static final int BOARD_WIDTH = 10;
    /**
     * This our board height.
     */
    public static final int BOARD_HEIGHT = 20;
    /**
     * This our movement speed.
     */
    public static final int MOVE_TIME = 300;
    /**
     * This our frame on which game runs.
     */
    private JFrame tetris;
    /**
     * This our Board object for monitoring game.
     */
    private Board gameBoard;
    /**
     * This our timer that moves us after each "tick".
     */
    private Timer moveTimer;
    /**
     * This our game panel where game plays out.
     */
    private JPanel gamePanel;
    /**
     * This our panel for next piece.
     */
    private JPanel nextPanel;

    /**
     * This our boolean for checking if game paused.
     */
    private boolean gamePaused;

    /**
     * This our boolean to see if game is over.
     */
    private boolean myGameOver;
    
    /**
     * TetrisGUI is our constructor that initializes the gamePaused.
     */
    public TetrisGUI() {
        gamePaused = false;
    }
    
    /**
     * Play is where our game is created and is played.
     */
    public void play() {
        tetris = new JFrame();
        tetris.setTitle("REVERSE TETRIS");
        gameBoard = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        gameBoard.addObserver(this);
        moveTimer = new Timer(MOVE_TIME, new TimeElapsed(gameBoard)); 
        tetris.addKeyListener(this);       
        myGameOver = false;
        gamePanel = new PieceAndGrid(gameBoard);
        nextPanel = new NextPiecePanel(gameBoard);
        nextPanel.setPreferredSize(new Dimension(282, 440));
        nextPanel.setBackground(Color.ORANGE);
        tetris.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tetris.setMinimumSize(new Dimension(GAME_X, GAME_Y));
        gamePanel.setPreferredSize(new Dimension(BOARD_WIDTH * BLOCK_SIZE
                                                 , BOARD_HEIGHT * BLOCK_SIZE));
        gamePanel.setBackground(Color.WHITE);
        tetris.setVisible(true);
        tetris.add(nextPanel, BorderLayout.EAST);
        tetris.add(gamePanel);
        final int gameStart = JOptionPane.showConfirmDialog(tetris, "Start the Game?"
                                                    , "-Confirm Play-"
                                                    , JOptionPane.YES_NO_OPTION);
        if (gameStart == JOptionPane.YES_OPTION) {
            moveTimer.start(); 
            gameBoard.newGame();
            start = true; // Protects from null pointer in piece and Grid and nextpiece class.

        } else {
            System.exit(0);
        }
    }
    
    /**
     * This method checks for any key we press.
     * @param event which is the key event that took place.
     */
    public void keyPressed(final KeyEvent event) {
        if (myGameOver && event.getKeyCode() == KeyEvent.VK_N) {
            final int newGame = JOptionPane.showConfirmDialog(tetris, "New game?", 
                                           "Better Luck Next Time", JOptionPane.YES_NO_OPTION);
            if (newGame == JOptionPane.NO_OPTION) {
                System.exit(0);
            } else {
                gameBoard.newGame();
            }
        }
        
        if (event.getKeyCode() == KeyEvent.VK_P) {
            if (!gamePaused) {
                gamePaused = true;
                moveTimer.stop();
            } else {
                gamePaused = false;
                moveTimer.start();
            }
        }
        
        if (!gamePaused) {
            if (event.getKeyCode() == KeyEvent.VK_LEFT 
                            ||  event.getKeyCode() == KeyEvent.VK_A) {
                gameBoard.left();
            } else if (event.getKeyCode() == KeyEvent.VK_RIGHT 
                            ||  event.getKeyCode() == KeyEvent.VK_D) {
                gameBoard.right();
            } else if (event.getKeyCode() == KeyEvent.VK_DOWN 
                            ||  event.getKeyCode() == KeyEvent.VK_S) {
                gameBoard.down();
            } else if (event.getKeyCode() == KeyEvent.VK_UP 
                            ||  event.getKeyCode() == KeyEvent.VK_W) {
                gameBoard.rotateCW();
            } else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
                gameBoard.drop();            
            } 
        }
    }
    
    /**
     * This returns the width of the Board.
     * @return board width.
     */
    public int getBoardWidth() {
        return BOARD_WIDTH;
    }
    
    /**
     * This returns the height of the Board.
     * @return board width.
     */
    public int getBoardHeight() {
        return BOARD_HEIGHT;
    }
    
    /**
     * This returns the block size.
     * @return block's size.
     */
    public int getBlockSize() {
        return BLOCK_SIZE;
    }
    
    /**
     * This method updates all the panels we have to match the time we're advancing into.
     * @param theObservable is what we're observing
     * @param theObject is the object.
     */
    public void update(final Observable theObservable, final Object theObject) {
        gamePanel.repaint();
        nextPanel.repaint();
        myGameOver = gameBoard.isGameOver();
    }
    
}
