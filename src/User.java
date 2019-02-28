import java.util.*;

public class User {
    private int userId;
    private int amountOfBasicPathInfosDone;
    private int amountOfOptimizationsDone;
    private double averageOptimizationLevel;
    private List<Session> sessions;
    private double[][] highScoreTimeBinLevelWithOptimization;
    private double[][] averageScoreTimeBinLevelWithOptimization;
    private double[][] highScoreTimeBinLevelWithoutOptimization;
    private double[][] averageScoreTimeBinLevelWithoutOptimization;
    private int[][] numWithOptimization;
    private int[][] numWithoutOptimization;
    private boolean alreadyCalculatedScores;

    //rank division:
    private List<List<BasicPathInfo>> rankOfBasicPathInfosByScore;
    public static final int divisionNumForRankOfBasicPathInfosByScore = 10; //each is 10% of population(seeds)
    private boolean alreadyCalculatedRankOfBasicPathInfosByScore;
    private double[] numOfPressOnOptForEachRank;
    private double[] improvementInFidelityForEachRank;

    //time bins division:
    private List<List<BasicPathInfo>> rankOfBasicPathInfosByTimeBins;
    private boolean alreadyCalculatedRankOfBasicPathInfosByTimeBins;
    private double[] numOfPressOnOptForEachRankByTimeBins;
    private double[] improvementInFidelityForEachRankByTimeBins;

    public static final int levelsNum = 20;
    public static final int timeBinsNum = 12;

    public static HashMap<Integer, User> users = new HashMap<>();

    public static User addSession(Session session){
        int wantedUserId = session.getUserId();
        User curr = users.get(wantedUserId);
        if(curr == null){
            curr = new User(wantedUserId);
            users.put(wantedUserId, curr);
        }
        curr.sessions.add(session);

        return curr;
    }

    private User(int userId) {
        this.userId = userId;
        this.sessions = new ArrayList<>();
        this.amountOfBasicPathInfosDone = 0;
        this.amountOfOptimizationsDone = 0;
        this.averageOptimizationLevel = 0;

        this.highScoreTimeBinLevelWithOptimization = new double[levelsNum][];
        this.averageScoreTimeBinLevelWithOptimization = new double[levelsNum][];

        this.highScoreTimeBinLevelWithoutOptimization = new double[levelsNum][];
        this.averageScoreTimeBinLevelWithoutOptimization = new double[levelsNum][];

        this.numWithOptimization = new int[levelsNum][];
        this.numWithoutOptimization = new int[levelsNum][];

        this.alreadyCalculatedScores = false;

        for(int i = 0; i < levelsNum; i++)
        {
            this.highScoreTimeBinLevelWithOptimization[i] = new double[timeBinsNum];
            this.averageScoreTimeBinLevelWithOptimization[i] = new double[timeBinsNum];

            this.highScoreTimeBinLevelWithoutOptimization[i] = new double[timeBinsNum];
            this.averageScoreTimeBinLevelWithoutOptimization[i] = new double[timeBinsNum];

            this.numWithOptimization[i] = new int[timeBinsNum];
            this.numWithoutOptimization[i] = new int[timeBinsNum];

            for(int j = 0; j < timeBinsNum; j++){
                this.highScoreTimeBinLevelWithOptimization[i][j] = 0;
                this.averageScoreTimeBinLevelWithOptimization[i][j] = 0;

                this.highScoreTimeBinLevelWithoutOptimization[i][j] = 0;
                this.averageScoreTimeBinLevelWithoutOptimization[i][j] = 0;

                this.numWithOptimization[i][j] = 0;
                this.numWithoutOptimization[i][j] = 0;
            }
        }

        //division by score:
        this.rankOfBasicPathInfosByScore = new ArrayList<>(divisionNumForRankOfBasicPathInfosByScore);
        this.numOfPressOnOptForEachRank = new double[divisionNumForRankOfBasicPathInfosByScore];
        this.improvementInFidelityForEachRank = new double[divisionNumForRankOfBasicPathInfosByScore];
        for(int i = 0; i < divisionNumForRankOfBasicPathInfosByScore; i++){
            this.rankOfBasicPathInfosByScore.add(new ArrayList<>());
            this.numOfPressOnOptForEachRank[i] = 0;
            this.improvementInFidelityForEachRank[i] = 0;
        }
        alreadyCalculatedRankOfBasicPathInfosByScore = false;

        //division by time bins:
        this.rankOfBasicPathInfosByTimeBins = new ArrayList<>(timeBinsNum);
        this.numOfPressOnOptForEachRankByTimeBins = new double[timeBinsNum];
        this.improvementInFidelityForEachRankByTimeBins = new double[timeBinsNum];
        for(int i = 0; i < timeBinsNum; i++){
            this.rankOfBasicPathInfosByTimeBins.add(new ArrayList<>());
            this.numOfPressOnOptForEachRankByTimeBins[i] = 0;
            this.improvementInFidelityForEachRankByTimeBins[i] = 0;
        }
        alreadyCalculatedRankOfBasicPathInfosByTimeBins = false;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public int getAmountOfBasicPathInfosDone() {
        return amountOfBasicPathInfosDone;
    }

    public void calculateAmountOfBasicPathInfosDone() {
        int amount = 0;
        for(Session session : sessions){
            amount += session.getBasicPathInfos().size();
        }
        amountOfBasicPathInfosDone = amount;
    }

    public int getAmountOfOptimizationsDone() {
        return amountOfOptimizationsDone;
    }

    public void calculateAmountOfOptimizationsDone(){
        int amount = 0;
        for(Session session : sessions){
            for(BasicPathInfo basicPathInfo : session.getBasicPathInfos()){
                if(basicPathInfo.getOptimization() != null)
                    amount++;
            }
        }
        amountOfOptimizationsDone = amount;
    }

    public double getAverageOptimizationLevel() {
        return averageOptimizationLevel;
    }

    public void calculateAverageOptimizationLevel(){
        double amount = 0;
        double num = 0;
        for(Session session : sessions){
            for(BasicPathInfo basicPathInfo : session.getBasicPathInfos()) {
                BasicPathInfo opt = basicPathInfo.getOptimization();
                amount += opt.getOptimizationIteration();
                num++;
            }
        }
        if(num != 0)
            averageOptimizationLevel = (amount / num);
        else
            averageOptimizationLevel = 0;
    }

    public double getHighScoreTimeBinLevelWithOptimization(int levelId, int timeBin) {
        return highScoreTimeBinLevelWithOptimization[levelId][timeBin];
    }

    public double getAverageScoreTimeBinLevelWithOptimization(int levelId, int timeBin) {
        return averageScoreTimeBinLevelWithOptimization[levelId][timeBin];
    }

    public double getHighScoreTimeBinLevelWithoutOptimization(int levelId, int timeBin) {
        return highScoreTimeBinLevelWithoutOptimization[levelId][timeBin];
    }

    public double getAverageScoreTimeBinLevelWithoutOptimization(int levelId, int timeBin) {
        return averageScoreTimeBinLevelWithoutOptimization[levelId][timeBin];
    }

    public TwoIntAndOneDoubleTuple getLevelIdAndTimeBinMaxScoreWithOptimization() {
        return getLevelIdAndTimeBinMaxScore(highScoreTimeBinLevelWithOptimization);
    }

    public TwoIntAndOneDoubleTuple getLevelIdAndTimeBinMaxScoreWithoutOptimization(){
        return getLevelIdAndTimeBinMaxScore(highScoreTimeBinLevelWithoutOptimization);
    }

    private TwoIntAndOneDoubleTuple getLevelIdAndTimeBinMaxScore(double[][] array){
        if (!alreadyCalculatedScores)
            return null;

        int maxLvl = -1;
        int maxTimeBin = -1;
        double maxScore = -1;

        for (int i = 0; i < levelsNum; i++) {
            for (int j = 0; j < timeBinsNum; j++) {
                if (maxScore < array[i][j]) {
                    maxScore = array[i][j];
                    maxLvl = i;
                    maxTimeBin = j;
                }
            }
        }

        return new TwoIntAndOneDoubleTuple(maxLvl, maxTimeBin, maxScore);
    }

    public void calculateScoresTimeBinLevel(){
        if(alreadyCalculatedScores)
            return;
        alreadyCalculatedScores = true;

        for(Session session : sessions){
            for(BasicPathInfo seed : session.getBasicPathInfos()){
                int levelId = seed.getLevelId();
                int timeBin = seed.getTimeBin();
                OptimizedFidelity last = seed.getLast();
                if(last != null)
                {
                    //calculate with:
                    double scoreWith = last.getFidelity();

                    if(highScoreTimeBinLevelWithOptimization[levelId][timeBin] < scoreWith)
                        highScoreTimeBinLevelWithOptimization[levelId][timeBin] = scoreWith;

                    averageScoreTimeBinLevelWithOptimization[levelId][timeBin] += scoreWith;
                    numWithOptimization[levelId][timeBin]++;
                }

                //calculate without:
                double scoreWithout = seed.getFinalFidelity();

                if(highScoreTimeBinLevelWithoutOptimization[levelId][timeBin] < scoreWithout)
                    highScoreTimeBinLevelWithoutOptimization[levelId][timeBin] = scoreWithout;

                averageScoreTimeBinLevelWithoutOptimization[levelId][timeBin] += scoreWithout;
                numWithoutOptimization[levelId][timeBin]++;
            }
        }

        //calculate avg:
        for(int i = 0; i < levelsNum; i++){
            for(int j = 0; j < timeBinsNum; j++){
                if(numWithoutOptimization[i][j] > 0)
                    averageScoreTimeBinLevelWithoutOptimization[i][j] /= numWithoutOptimization[i][j];
                else
                    averageScoreTimeBinLevelWithoutOptimization[i][j] = 0;

                if(numWithOptimization[i][j] > 0)
                    averageScoreTimeBinLevelWithOptimization[i][j] /= numWithOptimization[i][j];
                else
                    averageScoreTimeBinLevelWithOptimization[i][j] = 0;
            }
        }
        //done
    }

    public double[] getNumOfPressOnOptForEachRank() {
        return numOfPressOnOptForEachRank;
    }

    public double[] getImprovementInFidelityForEachRank() {
        return improvementInFidelityForEachRank;
    }

    public double[] getNumOfPressOnOptForEachRankByTimeBins() {
        return numOfPressOnOptForEachRankByTimeBins;
    }

    public double[] getImprovementInFidelityForEachRankByTimeBins() {
        return improvementInFidelityForEachRankByTimeBins;
    }

    public void calculateFidelityRankingOf_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt() {
        calculateRankOfBasicPathInfosByScore();

        calculate_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt(rankOfBasicPathInfosByScore,numOfPressOnOptForEachRank,improvementInFidelityForEachRank,divisionNumForRankOfBasicPathInfosByScore);
    }

    private void calculate_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt(List<List<BasicPathInfo>> rank, double[] numOfPressOnOpt, double[] improvementInFidelity, int size){
        for(int i = 0; i < size; i++){
            for(BasicPathInfo seed : rank.get(i)) {
                BasicPathInfo opt = seed.getOptimization();
                if (opt != null) {
                    numOfPressOnOpt[i]++;
                    improvementInFidelity[i] += (opt.getFinalFidelity() - seed.getFinalFidelity());
                }
            }
            if(numOfPressOnOpt[i] > 0){
                improvementInFidelity[i] /= numOfPressOnOpt[i];
                numOfPressOnOpt[i] /= rank.get(i).size();
            }
        }

        //done :)
    }

    private void calculateRankOfBasicPathInfosByScore(){
        if(alreadyCalculatedRankOfBasicPathInfosByScore)
            return;
        alreadyCalculatedRankOfBasicPathInfosByScore = true;

        PriorityQueue<BasicPathInfo> seedsByOrder = new PriorityQueue<>((o1, o2) -> {
            return Double.compare(o2.getFinalFidelity(), o1.getFinalFidelity()); //order from largest fidelity to smallest
        });

        //add all by the order
        for(Session session : sessions){
            for(BasicPathInfo seed : session.getBasicPathInfos()){
                if(!seed.isOptimizableLevel() || !seed.isSeed()) // only count optimizable levels and real seeds
                    continue;

                seedsByOrder.add(seed);
            }
        }

        //split into the different groups
        int N = seedsByOrder.size();
        int amountInBucket = N / divisionNumForRankOfBasicPathInfosByScore;
        int restLeft = N % divisionNumForRankOfBasicPathInfosByScore;

        for(int i = 0; i < divisionNumForRankOfBasicPathInfosByScore; i++){
            for(int j = 0; j < amountInBucket; j++){
                BasicPathInfo curr = seedsByOrder.poll();
                rankOfBasicPathInfosByScore.get(i).add(curr);
            }

            //add the rest that is left
            if(restLeft > 0){
                BasicPathInfo curr = seedsByOrder.poll();
                rankOfBasicPathInfosByScore.get(i).add(curr);

                restLeft--;
            }
        }
        //done :)
    }

    public void calculateTimeBinsOf_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt() {
        calculateRankOfBasicPathInfosByTimeBins();

        calculate_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt(rankOfBasicPathInfosByTimeBins, numOfPressOnOptForEachRankByTimeBins,improvementInFidelityForEachRankByTimeBins,timeBinsNum);
    }

    private void calculateRankOfBasicPathInfosByTimeBins(){
        if(alreadyCalculatedRankOfBasicPathInfosByTimeBins)
            return;
        alreadyCalculatedRankOfBasicPathInfosByTimeBins = true;

        //add all by the order
        for(Session session : sessions){
            for(BasicPathInfo seed : session.getBasicPathInfos()){
                if(!seed.isOptimizableLevel() || !seed.isSeed()) // only count optimizable levels and real seeds
                    continue;

                int timeBin = seed.getTimeBin();
                rankOfBasicPathInfosByTimeBins.get(timeBin).add(seed);
            }
        }

        //done :)
    }
}
