package org.dmetzler.isen.puissance4.core;

/**
 * Created by dmetzler on 03/09/2014.
 */
public interface Puissance4Game {


	/**
	 * Play a chip in a given column.
	 * @param colour
	 * @param column
	 * @throws GameException if it is not allowed to play in that cell.
	 */
    void play(ChipColour colour, int column) throws GameException;

    /**
     * Returns the colour of the chip in a given cell, null if no
     * chip is present.
     * @param column
     * @param row
     * @return
     */
    ChipColour getCell(int column, int row);

    /**
     * Returns the number of columns.
     * @return
     */
    int getColumnsNumber();

    /**
     * Returns the number of rows.
     * @return
     */
    int getRowsNumber();

    /**
     * Returns the colour of the winner, null if no winner.
     * @return
     */
    ChipColour getWinner();

}
