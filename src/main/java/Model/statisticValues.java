package Model;

/**
 * Created by lucas.crandle on 6/16/2017.
 */
public class statisticValues {
    public int countInSet;
    public int falsePositives;
    public int falsePositivePercentage;
    public int attemptsNotInSet;

    public statisticValues(int countInSet, int falsePositive, int attemptsNotInSet){
        this.countInSet = countInSet;
        this.falsePositives = falsePositive;
        this.falsePositivePercentage = (100 * falsePositive/countInSet);
        this.attemptsNotInSet = attemptsNotInSet;
    }
}
