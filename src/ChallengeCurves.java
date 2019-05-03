public class ChallengeCurves {

    public static double getHighestChallenge(int levelID, int timeBin){
        if(levelID == 7)
            return getHighestChallenge_level7(timeBin);
        if(levelID == 11)
            return getHighestChallenge_level11(timeBin);
        if(levelID == 16)
            return getHighestChallenge_level16(timeBin);

        return Double.NaN;
    }

    private static double getHighestChallenge_level7(int timeBin){
        if(timeBin == 0)
            return 0.4381;
        if(timeBin == 1)
            return 0.51455;
        if(timeBin == 2)
            return 0.61513;
        if(timeBin == 3)
            return 0.75831;
        if(timeBin == 4)
            return 0.81177;
        if(timeBin == 5)
            return 0.86384;
        if(timeBin == 6)
            return 0.92944;
        if(timeBin == 7)
            return 0.95007;
        if(timeBin == 8)
            return 0.96731;
        if(timeBin == 9)
            return 0.98502;
        if(timeBin == 10)
            return 0.99162;

        return 0.99604; //if(timeBin == 11)
    }

    private static double getHighestChallenge_level11(int timeBin) {
        if(timeBin == 0)
            return 0.00067875;
        if(timeBin == 1)
            return 0.0068207;
        if(timeBin == 2)
            return 0.10319;
        if(timeBin == 3)
            return 0.37767;
        if(timeBin == 4)
            return 0.50773;
        if(timeBin == 5)
            return 0.64942;
        if(timeBin == 6)
            return 0.76883;
        if(timeBin == 7)
            return 0.84273;
        if(timeBin == 8)
            return 0.90533;
        if(timeBin == 9)
            return 0.97803;
        if(timeBin == 10)
            return 0.99714;

        return 0.99905; //if(timeBin == 11)
    }

    private static double getHighestChallenge_level16(int timeBin) {
        if(timeBin == 0)
            return 0.000000000000002;
        if(timeBin == 1)
            return 0.000000000000002;
        if(timeBin == 2)
            return 0.00035006465725490266;
        if(timeBin == 3)
            return 0.04154677277450983;
        if(timeBin == 4)
            return 0.32797730608823517;
        if(timeBin == 5)
            return 0.7532962770686277;
        if(timeBin == 6)
            return 0.9640247019215689;
        if(timeBin == 7)
            return 0.9984364760588235;
        if(timeBin == 8)
            return 0.999103966;
        if(timeBin == 9)
            return 0.999103966;
        if(timeBin == 10)
            return 0.999103966;

        return 0.999103966; //if(timeBin == 11)
    }

}
