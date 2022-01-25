import java.util.Random;

public class Field {
    private final int BOMB = 9;
    private int width;
    private int height;
    private int [][] field;
    private int numberOfBombs;

    public Field(int width, int height, int numberOfBombs) {
        if(width <= 0 && height <= 0 && numberOfBombs <= 0){
            throw new IllegalArgumentException("The width, height and amount of bombs must be greater than zero!");
        }
        if(numberOfBombs >= height*width){
            throw new IllegalArgumentException("The number of bombs cannot be greater or equal to the amount of tiles!");
        }
        this.width = width;
        this.height = height;
        this.numberOfBombs = numberOfBombs;
        field = new int [height][width];
        this.generateField(numberOfBombs);
    }
    public Field(){
        this.width = 10;
        this.height = 10;
        field = new int [10][10];
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public int[][] getField(){
        return this.field;
    }

    public int getNumberOfBombs(){
        return this.numberOfBombs;
    }

    public void generateField(int numberOfBombs){
        this.numberOfBombs = numberOfBombs;
        Random rand = new Random();

        for(int i = 0; i < numberOfBombs; i++){
            int x = rand.nextInt(height);
            int y = rand.nextInt(width);

            while(field[x][y] == BOMB){
                x = rand.nextInt(height);
                y = rand.nextInt(width);
            }

            field[x][y] = BOMB;

            for(int j = x-1; j <= x+1; j++){
                for(int k = y-1; k <= y+1; k++){
                    if((k < getWidth() && k >= 0) && (j < getHeight() && j >= 0)){
                        if(field[j][k] != BOMB){
                            field[j][k]++;
                        }
                    }
                }
            }
        }
    }
    public void printField(){
        for(int i = 0; i < getHeight(); i++){
            for(int j = 0; j < getWidth(); j++){
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    
}
