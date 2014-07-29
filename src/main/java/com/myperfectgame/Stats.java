package com.myperfectgame;

import java.io.Serializable;
import java.util.Comparator;

/**
 * The class with all the STATS
 * Date: 07/05/14
 */
public class Stats implements Serializable {

    private static final long serialVersionUID = 7526372295622776147L;

    private int clearedLines;
    private int blocks;
    private int score;

    private int keypresses;
    private int rotations;
    private int downpresses;
    private int left;
    private int right;
    private int drop;

    private int pauses;
    private long playTime; // milliseconds


    public Stats() {
        clearedLines = 0;
        blocks = 0;
        score = 0;
        keypresses = 0;
        rotations = 0;
        downpresses = 0;
        pauses = 0;
        playTime = 0;
        left = 0;
        right = 0;
        drop = 0;

    }

    public int getClearedLines() {
        return clearedLines;
    }

    public void addClearedLines(int clearedLines) {
        this.clearedLines += clearedLines;
    }

    public int getBlocks() {
        return blocks;
    }

    public void incBlocks() {
        this.blocks++;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getKey() {
        return keypresses;
    }

    public void incKey() {
        this.keypresses = keypresses;
    }

    public int getRotate() {
        return rotations;
    }

    public void incRotate() {
        this.rotations++;
    }

    public int getDown() {
        return downpresses;
    }

    public void incDown() {
        this.downpresses++;
        incKey();
    }

    public int getPause() {
        return pauses;
    }

    public void incPause() {
        this.pauses++;
    }

    public long getPlayTime() {
        return playTime;
    }

    public void addPlayTime(int playTime) {
        this.playTime += playTime;
    }

    public int getLeft() {
        return left;
    }

    public void incLeft() {
        this.left++;
        incKey();
    }

    public int getRight() {
        return right;
    }

    public void incRight() {
        this.right++;
        incKey();
    }

    public int getDrop() {
        return drop;
    }

    public void incDrop() {
        this.drop++;
        incKey();
    }

    public static Comparator<Stats> COMPARE_BY_SCORE = new Comparator<Stats>() {
        public int compare(Stats one, Stats other) {
            return one.getScore() < other.getScore() ? 1 : one.getScore() > other.getScore() ? -1 : 0;
        }
    };


}
