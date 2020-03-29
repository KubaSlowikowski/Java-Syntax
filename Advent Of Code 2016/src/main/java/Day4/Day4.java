package Day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    private static final Pattern ROOM_NAME_PATTERN = Pattern.compile("((?:[a-z]+-?)+)-(\\d+)\\[([a-z]+)\\]");
    private static final String INPUT_PATH = "C:\\Users\\Admin\\IdeaProjects\\Java\\Advent Of Code 2016\\src\\main\\resources\\rooms.txt";
    private static final int FIVE_LETTERS = 5;
    private final int NUMBER_OF_LETTERS = 26;
    private final int ASCII_A = 97;
    private static List<String> roomsList = new ArrayList<>();
    private static String roomName;
    private static int roomId;
    private static String roomChecksum;
    private long sum=0;
    private List<String> listWithDecodedRoomNames = new ArrayList<>();

    public Day4() {
        readFile(INPUT_PATH);
        for(int i=0; i<roomsList.size(); i++) {
            readRegExp(i);
            if(isRoomCorrect()) {
                //decodeRoomName();
            }
            decodeRoomName();
        }
        //System.out.println(listWithDecodedRoomNames);
        System.out.println("Sum: " + sum);
        if (listWithDecodedRoomNames.contains("northpole object storage"))
            readRegExp(listWithDecodedRoomNames.indexOf("northpole object storage"));
        System.out.println(roomId);
    }

    private void readFile(String inputPath) {
        try( BufferedReader fileReader = new BufferedReader(new FileReader(inputPath));) {
            while(true) {
                String s = fileReader.readLine();
                if(!s.isEmpty()) {
                    roomsList.add(s);
                }
                else break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {}
    }

    private void readRegExp(int roomIndex) {
        //System.out.println(roomsList.get(roomIndex));

        Matcher matcher = ROOM_NAME_PATTERN.matcher(roomsList.get(roomIndex));
        if(matcher.matches()) {
            roomName = matcher.group(1);
            roomId = Integer.parseInt(matcher.group(2));
            roomChecksum = matcher.group(3);
        }
    }

    private boolean validateChecksum(String roomName, String roomChecksum) {
        Map<Character, Integer> lettersMap = new HashMap<>();
        lettersMap = countLetters(roomName); //zwraca mape, w ktorej kazda litera ma przypisana ilosc wystapien
        List<Map.Entry<Character, Integer>> sortedList = sortLetters(lettersMap); //lista posortowanych literek (dodatkowo informacja ile razy wystapila)
        //System.out.println("Sorted checksum:   " + sortedList);

        StringBuilder sb = new StringBuilder();
        int lenght = Math.min(FIVE_LETTERS, sortedList.size()); //sprawdza, dobiera odpowiednia dlugosc posortowanej listy w celu porownania z [dana] z pliku
        for(int i=0; i<lenght; i++) {
            sb.append(sortedList.get(i).getKey());
        }
        String correctChecksum = sb.toString();

        if(correctChecksum.equals(roomChecksum))
            return true;
        return false;
    }

    private Map<Character, Integer> countLetters(String roomName) {
        String roomNameWithoutHyphens = roomName.replaceAll("-", "");
        Map<Character, Integer> lettersMap = new HashMap<>();
        for (Character letter : roomNameWithoutHyphens.toCharArray()) {
            int letterCount = 1;
            if (lettersMap.containsKey(letter)) {
                letterCount += lettersMap.get(letter);
            }
            lettersMap.put(letter, letterCount);
        }
        return lettersMap;
    }

    private List<Map.Entry<Character, Integer>> sortLetters(Map<Character,Integer> lettersMap) {
        List<Map.Entry<Character,Integer>> list = new ArrayList<>(lettersMap.entrySet()); //It builds array list which has specified initial capacity used to store the elements.
        Collections.sort(list, new Comparator<Map.Entry<Character,Integer>>() {
            @Override
            public int compare(Map.Entry<Character,Integer> x1, Map.Entry<Character,Integer> x2) {
                int result = x2.getValue().compareTo(x1.getValue()); //sortuje po dlugosci malejaco
                if(result == 0) { // jesli dlugosc jest taka sama, sortuje alfabetycznie
                    return x1.getKey().compareTo(x2.getKey());
                }
                return result;
            }
        });
        return list;
    }

    private boolean isRoomCorrect() {
        if(validateChecksum(roomName,roomChecksum)) {
            //System.out.println("CORRECT ROOM");
            sum+=roomId;
            return true;
        }
        else {
            //System.out.println("INCORRECT ROOM");
            return false;
        }
    }

    private void decodeRoomName() {

        int rotNumber = roomId % NUMBER_OF_LETTERS;
        StringBuilder sb = new StringBuilder();

        for(Character letter : roomName.toCharArray()) {
            if(letter == '-')
                sb.append(" ");
            else {
                int newLetterBase = letter % ASCII_A;	//literka od 0 do 26
                int rotatedLetter = (newLetterBase + rotNumber) % NUMBER_OF_LETTERS;
                sb.append((char) (rotatedLetter + ASCII_A));
            }
        }
        listWithDecodedRoomNames.add(sb.toString());
        //System.out.println("DECODED ROOM NAME: " + sb.toString() + "\n");
    }

    public static void main(String[] args) {
        new Day4();
    }
}