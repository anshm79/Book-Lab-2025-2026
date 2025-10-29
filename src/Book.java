public class Book {

    public String pigLatin(String word) {
        return translateWord(word);
    }

 
    public int endPunctuation(String word) {
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                if (i == word.length() - 1) return -1; 
                return i + 1;
            }
        }
        return 0; 
    }

    // Translates a single word into Pig Latin
    public String translateWord(String word) {
        if (word.length() == 0) return word;

        // Separate punctuation
        int punctIndex = endPunctuation(word);
        String coreWord = (punctIndex == -1) ? word : word.substring(0, punctIndex);
        String punctuation = (punctIndex == -1) ? "" : word.substring(punctIndex);

        boolean isCapitalized = Character.isUpperCase(coreWord.charAt(0));
        String lowerWord = coreWord.toLowerCase();

        String vowels = "aeiou";
        String convertedWord = "";

        if (vowels.indexOf(lowerWord.charAt(0)) != -1) {
            convertedWord = lowerWord + "yay";
        } else {
            int vowelPos = -1;
            for (int i = 0; i < lowerWord.length(); i++) {
                if (vowels.indexOf(lowerWord.charAt(i)) != -1) {
                    vowelPos = i;
                    break;
                }
            }
            if (vowelPos == -1) {
                convertedWord = lowerWord + "ay"; 
            } else {
                convertedWord = lowerWord.substring(vowelPos) + lowerWord.substring(0, vowelPos) + "ay";
            }
        }

        if (isCapitalized) {
            convertedWord = convertedWord.substring(0, 1).toUpperCase() + convertedWord.substring(1);
        }

        convertedWord += punctuation;

        return convertedWord;
    }

    public String translateSentence(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            result.append(translateWord(words[i]));
            if (i < words.length - 1) result.append(" ");
        }

        return result.toString();
    }

    // Quick test
    public static void main(String[] args) {
        Book b = new Book();
        System.out.println(b.translateSentence("hello!"));      
        System.out.println(b.translateSentence("What?!?"));     
        System.out.println(b.translateSentence("Allons-y"));   
    }
}
