import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;


public class Minesweeper {
    static Field field = new Field(6, 6, 12);
    static int selectedBombs = 0;
    static int bombsLeft = field.getNumberOfBombs();
    public static void main(String[] args) {

        final int START_BUTTON_X = 100;
        final int START_BUTTON_Y = 100;
        final int DISTANCE_BETWEEN_BUTTONS = 60;
        
        JFrame window = new JFrame("GUI App");
		window.setBounds(200, 200, 500, 750);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setLayout(null);

        JLabel endOfGameMessage = new JLabel();
        endOfGameMessage.setBounds(150, 20, 250, 100);

        int xButton = START_BUTTON_X;
        int yButton = START_BUTTON_Y;

        int columnCounter = 0;
        field.printField();

        JLabel bombTracker = new JLabel("Bombs left: " + bombsLeft);
        bombTracker.setBounds(150, 20, 250, 100);
        window.add(bombTracker);

        for (int i = 0; i < field.getHeight(); i++) {
            for(int j = 0; j < field.getWidth(); j++) {
                JButton button = new JButton();
                button.setActionCommand(String.valueOf(field.getField()[i][j]));

                button.addMouseListener(new MouseAdapter(){
                    public void mousePressed(MouseEvent mouseEvent) {
                        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                            if(button.getText().isEmpty()){
                                button.setText(button.getActionCommand());
                                if(button.getText().equals("9")){
                                    window.getContentPane().removeAll();
                                    window.repaint();
                                    window.add(endOfGameMessage);
                                    endOfGameMessage.setText("Game Over! You Clicked on a bomb!");
                                }
                            }
                        }
                        if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                            if(button.getText().isEmpty()) {
                                button.setText("X");
                                bombsLeft--;
                                bombTracker.setText("Bombs left: " + bombsLeft);
                                if(button.getActionCommand().equals("9")){
                                    selectedBombs++;
                                    if(selectedBombs == field.getNumberOfBombs()){
                                        window.getContentPane().removeAll();
                                        window.repaint();
                                        window.add(endOfGameMessage);
                                        endOfGameMessage.setText("You win!");
                                    }
                                }
                            }else if(button.getText().equals("X")){
                                bombsLeft++;
                                bombTracker.setText("Bombs left: " + bombsLeft);
                                button.setText("");
                                if(button.getActionCommand().equals("9")){
                                    selectedBombs--;
                                }
                            }
                            
                        }
                    }
                });

                if(columnCounter % field.getWidth() == 0 && columnCounter != 0){
                    columnCounter = 1;
                    xButton = START_BUTTON_X;
                    yButton += DISTANCE_BETWEEN_BUTTONS;
                    button.setBounds(xButton, yButton, 50, 50);
                }else {
                    if(columnCounter != 0){
                        xButton += DISTANCE_BETWEEN_BUTTONS;
                    }
                    button.setBounds(xButton, yButton, 50, 50);
                    columnCounter++;
                }
                window.add(button);
            }
        }
        window.repaint();
    }
}
