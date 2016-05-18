package graphics;

import data.Coordinates;

/**
 *
 * @author benjamin
 */
public class StraightLine {
    
    private Coordinates start;
    private Coordinates end;

    public StraightLine(Coordinates start, Coordinates end){

        this.start = start;
        this.end = end;
    }

    /**
     * @return the start
     */
    public Coordinates getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Coordinates start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public Coordinates getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Coordinates end) {
        this.end = end;
    }
}
    

