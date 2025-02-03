import java.util.*;
// Made by Taransh Goyal

// This program is a trivia game that quizzes the user, offers lifelines for help, and awards points and prizes based on their answers.


public class Trivia {

    // Method for displaying each question
    public static boolean displayQuestion(int iQuestionNumber, String[] sAllQuestions, String[] sAllQuestionOptions, int iLifelinesAmount, boolean bShowLifelines )
    {
        // Displaying the question number
        System.out.format("\nQuestion Number %d: %n", iQuestionNumber);

        // Displaying the question itself
        System.out.println(sAllQuestions[iQuestionNumber-1]);

        // Displaying all of the options for the question

        // Each question has four options, and each option is being printed one by one
        // The starting index of the correct options is being found as (iQuestionNumber * 4) - 4 
        // The options are printed until the ending index (iQuestionNumber * 4) is reached
        // The correct options are being shown by finding and printing the starting index and ending index of the question options based on the question number
        for (int i = ((iQuestionNumber * 4) - 4 ); i < (iQuestionNumber * 4); i+=1)
        {   
            System.out.println(sAllQuestionOptions[i]);
        }

        // If there are life lines available and the variable to show the life lines is set to true, the lifelines will be shown
        if (iLifelinesAmount > 0 && bShowLifelines)
        {
            // Displaying the available life lines (get a hint and ask the audience)
            System.out.format("\nFeeling stuck? You still have %d lifelines available to use! %n", iLifelinesAmount);

            // Displaying the life lines
            System.out.println("5) Recieve a Hint");
            System.out.println("6) Ask the Audience");

            // Reminding the user that they will not be able to use a lifeline for that question if they enter an invalid input
            System.out.println("Note: Entering an invalid input disables lifelines for that question, but you can still answer the question without them.");

            // Returning true to indicate that lifelines are available to be used for this question
            return true;
        }
        // If lifelines are not avaiable to be used for the question
        return false;
    }

    // Method for getting the users answeer to the question
    public static int getUserAnswer(int iUserAnswer)
    {
        // Defining the scanner to allow for user input
        Scanner sc = new Scanner(System.in);

        // Try and catch is being used to ensure the program does not crash if the user enters an invalid data type
        try
        {
                // Asking the user to enter their answer to the question
                System.out.println("\nPlease enter the number corresponding to the option you want to choose:");
                iUserAnswer = sc.nextInt();
                sc.nextLine(); // Consuming any extra characters
        }
        // If an invalid data type is entered and an error is being caused
        catch (Exception e)
        {
            // Setting the variable for the user answer to 0 if an exception occurs
            iUserAnswer = 0;
            sc.nextLine(); // consuming any extra characters
        }
        
        // Returning the answer stated by the user
        return iUserAnswer;

    }
    
    // Method for displaying the hint and reducing the number of available life lines by one
    public static int useHint(int iLifelinesAmount, int iQuestionNumber, String[] sQuestionHints)
    {
        // Determining what the correct corresponding hint is to the question and printing that out
        System.out.println(sQuestionHints[iQuestionNumber-1]);

        // One life line has been used, so reducing the amount of life lines by one
        iLifelinesAmount -= 1;

        // Returning the new amount of life lines
        return iLifelinesAmount;
    }

    // Method for simulating an audience answering the question
    // Method will also reduce amount of lifelines by one 
    public static int askAudience (int iLifelinesAmount, int iQuestionNumber, String[] sAllQuestionOptions)
    {
        // Creating an object with type random
        Random r = new Random();

        // Letting the user know what the audience thinks the answer is
        // The audience answer will be randomly generated by using the Random object
        System.out.format("\nThe audience thinks that the answer is option %s %n", sAllQuestionOptions[iQuestionNumber*4 - 5 + r.nextInt(1,5)]);
        System.out.println("Will you trust them? ");

        // Since the user has now used a lifeline, the amount of lifelines now goes down by one
        iLifelinesAmount -=1;

        // Returning the new lifeline amount 
        return iLifelinesAmount;
    }

    // Method for checking if the user got the correct answer and updating their points accordindly 
    public static int checkAnswer(int iUserAnswer, int[] iAnswerKey, int iQuestionNumber, int iTotalPoints, String[] sAllQuestionOptions)
    {
        // Checking if user got the question correct

        // If the user got the answer correct
        // Determining if the answer is correct by comparing the answer given by the user to the answer in the answer key
        if (iUserAnswer == iAnswerKey[iQuestionNumber - 1])
        {
            System.out.println("\nCongralutions, you got the correct answer. You have earned two more points. ");
            // Increasing the amount of points by two
            iTotalPoints +=2;

            // Letting the user know how many points they have now
            System.out.format("You now have %d total points, Good Work! %n \n", iTotalPoints);
        }
        // If the user got the answer incorrect
        else
        {
            // Letting the user know that they got the answer wrong, and what the correct answer is
            System.out.format("\nSorry, you did not get the correct answer. The correct answer was option %s %n", sAllQuestionOptions[iQuestionNumber*4-5 + iAnswerKey[iQuestionNumber-1]]);
            System.out.println("You have gained no points from this question, better luck next time... \n");
        }

        // Returning the updated amount of points 
        return iTotalPoints;
    }

    // Method for letting the user know what prize they won with the amount of points they have 
    public static void userPrize(int iTotalPoints, String[] sPrizes)
    {
        // Letting the user know what their final score was
        // The amount of questions answered correctly is determined by dividing the total points earned by the points awarded for each question
        System.out.format("You corrrectly answered %d/8 questions. With this you ended with a total of %d points. %n", (iTotalPoints -2)/2 ,iTotalPoints);

        // Letting the user know what grand prize they have one based on the amount of points they had
        System.out.format("The grand prize that you have earned isss %s %n", sPrizes[iTotalPoints-3]); 
    }


    // The beginning of the main program
    public static void main(String[] args) {

        // Creating the scanner for user input
        Scanner sc = new Scanner(System.in);

        // Initializing the variable for the input of the user for the answer they pick
        int iUserAnswer = 0;

        //  Initializing the variable which will store the amount of points the user has
        // (being set to 2 since the user starts of with 2 points)
        int iTotalPoints = 2;

        // Initialzing the variable for the amount of lifelines the user has
        // (being set to 3 since the user has 3 life lines)
        int iLifelinesAmount = 3;

        // Initializing the variable for if the lifelines should be shown
        boolean bShowLifelines = true;

        // Initializing the array which will store all of the questions
        // (There are eight questions hence the array has a length of eight)
        String[]sAllQuestions = new String[8];

        // Adding all of the questions into the array

        // Adding the First Question to the array
        sAllQuestions[0] = "\nWhich planet is known as the Red Planet?";
        // Second Question
        sAllQuestions[1] = "What is the largest mammal on Earth?";
        // Third Question
        sAllQuestions[2] = "How many continents are there on Earth?";
        // Fourth Question
        sAllQuestions[3] = "Who painted the Mona Lisa?";
        // Fifth Question
        sAllQuestions[4] = "What is the largest organ in the human body?";
        // Sixth Question
        sAllQuestions[5] = "What is the largest ocean on Earth?";
        // Seventh Question
        sAllQuestions[6] = "What is the universally recognized emergency distress signal used by survivors in distress?";
        // Eigth Question
        sAllQuestions[7] = "In the wilderness, what can be used as a natural compass to find directions during the day?";


        // Initializing the array which will store which option in each question is the correct answer
        // (There are eight correct answers hence the array has a length of eight)
        int[]iAnswerKey = new int[8];
        
        // Adding the option numbers of all of the correct answers into the array

        // The number of the correct answer for the first question
        iAnswerKey[0] = 2;
        // Second question
        iAnswerKey[1] = 2;
        // Third question
        iAnswerKey[2] = 3;
        // Fourth question
        iAnswerKey[3] = 3;
        // Fifth question
        iAnswerKey[4] = 3;
        // Sixth question
        iAnswerKey[5] = 4;
        // Seveth question
        iAnswerKey[6] = 1;
        // Eigth question
        iAnswerKey[7] = 2;


        // Initializing the array which will store all the options for the questions
        // (There are eight questions each with four options hence the array has a length of 32)
        String[]sAllQuestionOptions = new String[32];
        
        // Adding all of the options into the array
        // Each four elements represent the options for one question

        // First QUestion
        sAllQuestionOptions[0] = "1) Venus";
        sAllQuestionOptions[1] = "2) Mars";
        sAllQuestionOptions[2] = "3) Jupiter";
        sAllQuestionOptions[3] = "4) Saturn";

        // Second Question
        sAllQuestionOptions[4] = "1) African Elephant";
        sAllQuestionOptions[5] = "2) Blue Whale";
        sAllQuestionOptions[6] = "3) Giraffe";
        sAllQuestionOptions[7] = "4) Hippopotamus";

        // Third Question
        sAllQuestionOptions[8] = "1) 6 ";
        sAllQuestionOptions[9] = "2) 5 ";
        sAllQuestionOptions[10] = "3) 7 ";
        sAllQuestionOptions[11] = "4) 8";

        // Fourth Question
        sAllQuestionOptions[12] = "1) Pablo Picasso";
        sAllQuestionOptions[13] = "2) Vincent van Gogh";
        sAllQuestionOptions[14] = "3) Leonardo da Vinci";
        sAllQuestionOptions[15] = "4) Claude Monet";

        // Fifth Question
        sAllQuestionOptions[16] = "1) Heart ";
        sAllQuestionOptions[17] = "2) Liver";
        sAllQuestionOptions[18] = "3) Skin";
        sAllQuestionOptions[19] = "4) Brain";

        // Sixth Question
        sAllQuestionOptions[20] = "1) Indian Ocean";
        sAllQuestionOptions[21] = "2) Atlantic Ocean";
        sAllQuestionOptions[22] = "3) Arctic Ocean";
        sAllQuestionOptions[23] = "4) Pacific Ocean";

        // Seventh Question
        sAllQuestionOptions[24] = "1) SOS";
        sAllQuestionOptions[25] = "2) SOSA";
        sAllQuestionOptions[26] = "3) 911";
        sAllQuestionOptions[27] = "4) MAYDAY";

        // Eight Question
        sAllQuestionOptions[28] = "1) Moss on trees  ";
        sAllQuestionOptions[29] = "2) The Sun ";
        sAllQuestionOptions[30] = "3) Stars ";
        sAllQuestionOptions[31] = "4) A river's flow ";

        

        // Making the array which includes all of the hints present
        // (There are eight questions each with one hint hence the array has a length of eight)
        String[] sQuestionHints = new String[8];

        // Defining the hints for each question

        // First question
        sQuestionHints[0] = "Hint: It is the fourth planet from the Sun in our solar system";
        // Second Question
        sQuestionHints[1] = "Hint: It spends most of its time in water";
        // Third 
        sQuestionHints[2] = "Hint: It is an odd number, and is considered a 'lucky number' in many cultures.";
        // Fourth
        sQuestionHints[3] = "Hint: The artist was incredibly talented, not only in painting, but also in other areas like anatomy, engineering, and mathematics."; 
        // FIfth
        sQuestionHints[4] = "Hint: This organ serves as a remarkable protective barrier for the human body";
        // Sixth
        sQuestionHints[5] = "Hint: This ocean is home to the Mariana Trench, the deepest point known in the worlds oceans";
        // Seventh
        sQuestionHints[6] = "Hint: The signal in morse code consists of three short signals, followed by three long signals, and three short signals again";
        // Eight
        sQuestionHints[7] = "Hint: It is found only in the sky";


        // The array which will store all the prizes and the points required to win them
        String[] sPrizes = new String[17];

        // Defining what prize each point will recieve 

        // If the user gets 2-4 points
        sPrizes[0] = "a wonderfull dog sticker";
        sPrizes[1] = "two colourfull dog stickers";
        sPrizes[2] = "three marvelous dog stickers";

        // If the user gets 5-7 points 
        sPrizes[3] = "a rubber chicken";
        sPrizes[4] = "a rubber chicken";
        sPrizes[5] = "a rubber chicken";

        // If the user gets 8-9 points 
        sPrizes[6] = "a woopy cushion";
        sPrizes[7] = "a woopy cushion";

        // If the user gets 10-12 points;
        sPrizes[8] = "one can of slime ";
        sPrizes[9] = "one can of slime";
        sPrizes[10] = "one can of slime ";
        
        // If the user gets 13-15 points
        sPrizes[11] = "a disguise set. Includes glasses, a moustache and a wig. Enjoy!";
        sPrizes[12] = "a disguise set. Includes glasses, a moustache and a wig. Enjoy!";
        sPrizes[13] = "a disguise set. Includes glasses, a moustache and a wig. Enjoy! ";

        // if the user gets 16-18 points
        sPrizes[14] = "a fun dancing sollar powered toy";
        sPrizes[15] = "a fun dancing sollar powered toy";
        sPrizes[16] = "a fun dancing sollar powered toy";

    


        // Displaying the welcome message of the program to the user
        System.out.println("\nWelcome to Goyals Trivia Night, here with us we have a grand audience watching you play. \n");
        System.out.println("You have 8 questions and each correct answer is worth 2 points."); 
        System.out.println("There will also be prizes given to you at the end. The more points you have, the better the prizes.");
        System.out.println("You are starting off with two BONUS points. To earn more, answer questions correctly.");
        System.out.println("\nTo help you get more points, you have 3 total uses of lifelines limited to only 1 use per question. Below are the lifelines\n");

        System.out.println("- Hint: Get a helpful hint relating to the answer of the question (costs 1 lifeline per use)");
        System.out.println("- Ask the Audience: See what the audience thinks the answer is (costs 1 lifeline per use)");
        System.out.println("Note: Entering an invalid input disables lifelines for that question, but you can still answer without them.");
        System.out.println("\nLets Get Started! Good Luck! \n");


        // Running the trivia game

        // The for loop will go through each question in the trivia game
        // Will stop running as soon as the variable representing the question number (iQuestionNumber) reaches 9
        for (int iQuestionNumber = 1; iQuestionNumber <= 8; iQuestionNumber+=1)
        {
            // Displaying the question its options, and checking if the user has life lines or not by calling the displayQuestion method
            boolean iLifeLinesAvailabe = displayQuestion(iQuestionNumber, sAllQuestions, sAllQuestionOptions, iLifelinesAmount, bShowLifelines );

            // Asking the user to enter their answer using the getUserAnswer method
            iUserAnswer = getUserAnswer(iUserAnswer);

            // If the user had life lines avaiable, the iLifeLinesAvaiable variable will be set to true
            if (iLifeLinesAvailabe && iUserAnswer == 5 || iUserAnswer == 6)
            {   

                // If the user chose to use a hint
                if (iUserAnswer == 5)
                {
                    System.out.println("\nHere is your hint. Hope it helps!");
                    // Displaying a hint to the user by calling the useHint method
                    // Will also reduce the amount of life lines by one since the user has used one
                    iLifelinesAmount = useHint(iLifelinesAmount, iQuestionNumber, sQuestionHints);
                }
                // If the user chooses to ask the audience 
                else if (iUserAnswer == 6)
                {
                    // Displaying the audience answer to the user by calling the askAudience method
                    // Will also reduce the amount of life lines by one since the user has used one
                    iLifelinesAmount = askAudience(iLifelinesAmount, iQuestionNumber, sAllQuestionOptions);
                }
                // Telling the user that they can no longer use a life line for this question
                System.out.format("\nPlease Note: You can no longer use a life line for this question. For other questions, you still have %d life line(s) remaining. %n", iLifelinesAmount);

                // Once the user is done using the life line, with the help of the getUserAnswer method they now enter what they think the correct answer is
                iUserAnswer = getUserAnswer(iUserAnswer);
            }
            // Setting the variable which determines if lifelines should be shown even when available to false
            // This is being done since at this point the user can not use life lines even if they are available 
            bShowLifelines = false;
            
            // Validitating the users answer to make sure its in the correct range (1-4)
            while (iUserAnswer > 4 || iUserAnswer < 1)
            {
                // Giving the user instructions to enter a valid input next time
                System.out.println("Sorry you entered an invalid input, next time please enter a number from 1-4 which corresponds with the option you want to choose.");

                // Displaying the question along with options again by using the displayQuestion method
                iLifeLinesAvailabe = displayQuestion(iQuestionNumber, sAllQuestions, sAllQuestionOptions, iLifelinesAmount, bShowLifelines );

                // Having the user input their answer by using the getUserAnswer method
                iUserAnswer = getUserAnswer(iUserAnswer);
            }
            // Setting the show lifelines variable to true, so lifelines are shown when available for the next quesiton
            bShowLifelines = true;

            // Checking if the user got the correct answer and accordindly updating the amount of points the user now has using the checkAnswer method
            iTotalPoints = checkAnswer(iUserAnswer, iAnswerKey, iQuestionNumber, iTotalPoints, sAllQuestionOptions);
        }

        // Once the game is finished

        // Letting the user know that the game is now finished
        System.out.println("Congralutions you have now finished answering all of the questions. Here are your results along with your promised prize. \n");

        // Telling the user what prize they ended up winning by using the userPrize method
        userPrize(iTotalPoints, sPrizes);

        // Thanking the user for playing
        System.out.println("\nThank you for playing Goyals Trivia. Audience lets give our wonderfull contestant one final round of applause! ");
        
    }
}
