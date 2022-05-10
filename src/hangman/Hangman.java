/* By Joaquin Cuellar for CSIS 1400 */
package hangman;
import java.lang.Math;
import java.util.Scanner;

public class Hangman
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        char userGuess;//character guessed by user
        
        String[] words = {"javascript", "declaration", "object", "class", "failing"};
        char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        
        
        String selectedWord;
        char[] builtWord;
        
        boolean win = false;
        
        int lossCount = 0;
        
        boolean correctLetter = false;
        
        //START MAIN GAME
        //Select Word from array
        selectedWord = words[(int)(Math.floor(Math.random() * words.length))];
        builtWord = createBuiltWord(selectedWord);
        
        while (win != true && lossCount < 7)
        {
            showScreen(alphabet, selectedWord, builtWord, lossCount);

            //Request Input
            userGuess = input.next().charAt(0);
            //Check against alphabet
            for (int i = 0; i < alphabet.length; i++)
            {
                if(Character.toUpperCase(userGuess) == Character.toUpperCase(alphabet[i]))
                {
                    alphabet[i] = '_';
                }
            }
            //Check against Word
            for (int i = 0; i < selectedWord.length(); i++)
            {
                if(Character.toUpperCase(userGuess) == Character.toUpperCase(explode(selectedWord)[i]))
                        {
                        System.out.println("MATCH");
                        builtWord[i] = Character.toLowerCase(userGuess);
                        correctLetter = true;
                        }
            }
            
            //Lose Point if not correct letter
            if (!correctLetter)
                lossCount++;
            correctLetter = false;
            
            //Check for win
            for (int i = 0; i < builtWord.length; i++)
            {
                if(builtWord[i] != explode(selectedWord)[i])
                        {
                        System.out.println("ERROR");
                        break;
                        }
                if(i == builtWord.length - 1)
                {
                    win = true;
                }
            }
        }
        //END OF LOOP
        showScreen(alphabet, selectedWord, builtWord, lossCount);
        if(win)
            System.out.println("\n\nYOU WON!");
        else
            System.out.println("\n\nYOU LOST!");
    }
    
    
    
    
    public static void showScreen(char[] alphabet, String selectedWord, char[] builtWord, int lossCount)
    {
        cleanScreen();
        
        System.out.println(" --------");
        System.out.println("|       |");
        System.out.println("|       " + (lossCount>=1 ? "O" : "") + "\t\t" + showAlphabet(alphabet));
        System.out.println("|      " + (lossCount>=3 ? "/" : "") + (lossCount>=2 ? "|" : "") + (lossCount>=4 ? "\\" : ""));
        System.out.println("|       " + (lossCount>=5 ? "|" : ""));
        System.out.println("|      " + (lossCount>=6 ? "/" : "") + (lossCount>=7 ? " \\" : ""));
        System.out.println("\n\n" + showWord(selectedWord, builtWord));
    }
    
    public static void cleanScreen()
    {
        System.out.println("\n\n\n\n\n\n");
    }
    
    public static String showAlphabet(char[] alphabet)
    {
        String output = "";
        
        for (int i = 0; i < alphabet.length; i++)
        {
            output = output + " " + alphabet[i];
        }
        return output;
    }
    
    public static String showWord(String selectedWord, char[] builtWord)
    {
        String output = "";
        
        for (int i = 0; i < builtWord.length; i++)
        {
            output = output + " " + builtWord[i];
        }
        return output;
    }
    
    public static char[] explode(String word)
    {
        char[] output = new char[word.length()];
        
        for (int i = 0; i < word.length(); i++)
        {
            output[i] = word.charAt(i);
        }
        return output;
    }
    
    public static char[] createBuiltWord(String word)
    {
        char[] output = new char[word.length()];
        
        for (int i = 0; i < word.length(); i++)
        {
            output[i] = '_';
        }
        return output;
    }
}
