package view;

/**
 * This class is main tetris class that calls the Tetris GUI.
 * @author Ali Iftakhar
 * @version 12/12/2019
 */
public class TetrisMain {
    
    /**
     * This is the main method. Running the Tetris gui class.
     * @param args 
     */
    public static void main(final String[] args) {
        new TetrisGUI().play();
    }
}
