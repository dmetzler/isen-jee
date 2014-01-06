package org.isen.guess;

import java.io.Serializable;

public class GuessGame implements Serializable {

    private static final long serialVersionUID = -489284572972038902L;

    private int maxNumber;
    private int number;
    private int triesLeft;

    private Integer lastGuess;

    public GuessGame(int maxNumber, int maxTries) {
        this.maxNumber = maxNumber;
        this.triesLeft = maxTries;
        this.number = (int) (Math.random() * maxNumber);
    }


    /**
     * Allow to set guess number.
     * @param number
     */
    void setNumber(int number) {
        this.number = number;
    }


    public Integer getMaxNumber() {
        return maxNumber;
    }

    public Integer getTurnLeft() {
        return triesLeft;
    }

    public void guess(Integer guessNumber) {
        lastGuess = guessNumber;
        triesLeft--;
    }

    public boolean tooHigh() {
        return lastGuess != null && lastGuess > number;
    }

    public boolean tooLow() {
        return lastGuess != null && lastGuess < number;
    }

    public boolean hasWon() {
        return lastGuess != null && number == lastGuess;
    }

    public boolean hasLost() {
        return triesLeft <= 0;
    }

}
