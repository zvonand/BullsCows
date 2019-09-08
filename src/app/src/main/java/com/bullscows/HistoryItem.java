package com.bullscows;


public class HistoryItem {
    private int mBulls, mCows, mCount;
    private String mNumber;

    public HistoryItem(int bulls, int cows, int count, String number){
        mBulls = bulls;
        mCows = cows;
        mCount = count;
        mNumber = number;
    }
    public int getBulls(){return mBulls;}
    public int getCows(){return mCows;}
    public int getCount(){return mCount;}
    public String getNumber(){return mNumber;}
}
