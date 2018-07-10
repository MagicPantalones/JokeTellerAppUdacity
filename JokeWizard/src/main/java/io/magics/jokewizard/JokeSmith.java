package io.magics.jokewizard;

import java.util.Random;

//Jokes borrowed from https://thoughtcatalog.com/brandon-gorrell/2015/03/50-short-clean-jokes-and-puns-that-will-get-a-laugh-every-time/
public class JokeSmith {

    private String[] jokes = new String[] {
            "You know why you never see elephants hiding up in trees?\nBecause they're really good at it.",
            "What is red and smells like blue paint?\nRed paint.",
            "A dyslexic man walks into a bra.",
            "Why aren't koalas actual bears?\nThey don't meet the koalafications.",
            "What do you call bears with no ears?\nB",
            "Why don't blind people skydive?\nBecause it scares the crap out of their dogs.",
            "What's brown and sticky?\nA stick.",
            "Two gold fish are in a tank.\nOne looks at the other and says, \"You know how to drive this thing?!\"",
            "Two soldiers are in a tank.\nOne looks at the other and says, \"BLUBLUBBLUBLUBBLUB.\"",
            "What is the resemblance between a green apple and a red apple?\nThey're both red except for the green one.",
            "I have an EpiPen.\nMy friend gave it to me when he was dying, it seemed very important to him that I have it."
    };

    public String getJoke() {
        int randomInt = new Random().nextInt(jokes.length - 1);
        return jokes[randomInt];
    }

}