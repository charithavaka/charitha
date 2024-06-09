import java.util.Scanner;
import java.util.Random;

public class SimpleChatbot {

    public static String getResponse(String userInput) {
        // Array of generic responses
        String[] responses = {
            "Interesting, tell me more.",
            "I see. What else can you tell me?",
            "That's fascinating!",
            "Hmm, I'll need to think about that.",
            "I'm not sure I understand. Can you elaborate?",
            "That's beyond my scope of knowledge.",
            "Great! Anything else?",
            "I'm here to help. What do you need?",
            "I'm sorry, could you repeat that?",
        };

        // Generate a random response from the array
        Random random = new Random();
        int index = random.nextInt(responses.length);
        return responses[index];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Simple Chatbot! Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Chatbot: Goodbye! Have a great day!");
                break;
            }
            String response = getResponse(userInput);
            System.out.println("Chatbot: " + response);
        }

        scanner.close();
    }
}
