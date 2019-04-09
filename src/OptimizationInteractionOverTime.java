import java.util.Date;
import java.util.Objects;

public class OptimizationInteractionOverTime implements Comparable {
    private boolean optimized;
    private Date timeOfGame;

    public OptimizationInteractionOverTime(boolean optimized, Date timeOfGame) {
        this.optimized = optimized;
        this.timeOfGame = timeOfGame;
    }

    public boolean isOptimized() {
        return optimized;
    }

    public void setOptimized(boolean optimized) {
        this.optimized = optimized;
    }

    public Date getTimeOfGame() {
        return timeOfGame;
    }

    public void setTimeOfGame(Date timeOfGame) {
        this.timeOfGame = timeOfGame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptimizationInteractionOverTime that = (OptimizationInteractionOverTime) o;
        return optimized == that.optimized &&
                timeOfGame.equals(that.timeOfGame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optimized, timeOfGame);
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof OptimizationInteractionOverTime){
            return this.timeOfGame.compareTo(((OptimizationInteractionOverTime) o).timeOfGame);
        }
        return 0;
    }
}
