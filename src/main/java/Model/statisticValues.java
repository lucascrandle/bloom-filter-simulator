package Model;

/**
 * Created by lucas.crandle on 6/16/2017.
 */
public class statisticValues {
    public int countInSet; /** Count of attempts that hit in the filter */
    public int falsePositives; /** Count of hits that were false */
    public int falsePositivePercentage; /** Percentage of hits that were false */
    public int attemptsNotInSet; /** Attempts that did not hit in the filter */

    public statisticValues(int countInSet, int falsePositive, int attemptsNotInSet){
        this.countInSet = countInSet;
        this.falsePositives = falsePositive;
        this.falsePositivePercentage = (100 * falsePositive/countInSet);
        this.attemptsNotInSet = attemptsNotInSet;
    }
}
