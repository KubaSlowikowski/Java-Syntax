package Day8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8 {

    private static final String INPUT_PATH = "C:\\Users\\Admin\\IdeaProjects\\Java\\Advent Of Code 2016\\src\\main\\resources\\commands.txt";
    private static final Pattern RECT_COMMAND_PATTERN 		   = Pattern.compile("(?:rect) (?<width>\\d+)x(?<height>\\d+)");
    private static final Pattern ROTATE_COLUMN_COMMAND_PATTERN = Pattern.compile("(?:rotate column) x=(?<column>\\d+) by (?<by>\\d+)");
    private static final Pattern ROTATE_ROW_COMMAND_PATTERN    = Pattern.compile("(?:rotate row) y=(?<row>\\d+) by (?<by>\\d+)");
    private static final int sizeX = 50;
    private static final int sizeY = 6;
    private List<String> commandsList = new ArrayList<>();
    private boolean[][] display;

    Day8() {
        readFile(INPUT_PATH);
        System.out.println(commandsList);
        display = new boolean[sizeY][sizeX];
        for(String command : commandsList) {
            readCommand(command);
        }
        System.out.println("DISPLAY:");
        showDisplay(display);
        System.out.println("Sum of pixels: " + countPixels());
    }

    private void readFile(String inputPath) {
        try( BufferedReader fileReader = new BufferedReader(new FileReader(inputPath));) {
            while(true) {
                String s = fileReader.readLine();
                if(!s.isEmpty()) {
                    commandsList.add(s);
                }
                else break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {}
    }

    private void readCommand(String command) {
        Matcher matcher = RECT_COMMAND_PATTERN.matcher(command);
        if(matcher.matches()) {
            int width =  Integer.parseInt(matcher.group("width"));
            int height = Integer.parseInt(matcher.group("height"));
            display = doRectCommand(width, height);
            return;
        }

        matcher = ROTATE_COLUMN_COMMAND_PATTERN.matcher(command);
        if(matcher.matches()) {
            int column = Integer.parseInt(matcher.group("column"));
            int by =	 Integer.parseInt(matcher.group("by"));
            display = doRotateColumnCommand(column, by);
            return;
        }

        matcher = ROTATE_ROW_COMMAND_PATTERN.matcher(command);
        if(matcher.matches()) {
            int row = Integer.parseInt(matcher.group("row"));
            int by  = Integer.parseInt(matcher.group("by"));
            display = doRotateRowCommand(row, by);
            return;
        }
    }

    private boolean[][] doRectCommand(int width, int height) {
        boolean[][] displayCopy = copyDisplay();
        for(int i=0; i<width; i++) {
            for(int j=0; j<height; j++) {
                displayCopy[j][i]=true;
            }
        }
        return displayCopy;
    }

    private boolean[][] doRotateColumnCommand(int column, int by) {
        boolean[][] displayCopy = copyDisplay();
        if(by>sizeY)
            by%=sizeY;
        for(int i=0; i<sizeY; i++) {
            int rotatedRow = (i-by+sizeY) % sizeY;
            displayCopy[i][column] = display[rotatedRow][column];
        }
        return displayCopy;
    }

    private boolean[][] doRotateRowCommand (int row, int by) {
        boolean[][] displayCopy = copyDisplay();
        if(by>sizeX)
            by%=sizeX;
        for(int i=0; i<sizeX; i++) {
            int rotatedColumn = (i-by+sizeX) % sizeX;
            displayCopy[row][i] = display[row][rotatedColumn];
        }
        return displayCopy;
    }

    private void showDisplay(boolean[][] display) {
        for(int i=0; i<sizeY; i++)
        {
            for(int j=0; j<sizeX; j++)
            {
                if(display[i][j]==true)
                    System.out.print("# ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    private boolean[][] copyDisplay() {
        boolean[][] displayCopy = new boolean[sizeY][sizeX];
        for(int i=0; i<sizeY; i++) {
            for(int j=0; j<sizeX; j++) {
                displayCopy[i][j] = display[i][j];
            }
        }
        return displayCopy;
    }

    private int countPixels() {
        int result = 0;
        for(int i=0; i<sizeY; i++) {
            for(int j=0; j<sizeX; j++) {
                if(display[i][j] == true) result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new Day8();
    }
}