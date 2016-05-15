package plotter;


// an array of Coordinates that contains our data.

/**
 *
 * @author benjamin
 */
public class Data {
    
    private Coordinates[] data;
    
    Data(float dataIN[][]){
        
        this.data = new Coordinates[dataIN.length];
        
        for(int i = 0; i < dataIN.length; i++){
            this.data[i] = new Coordinates(dataIN[i][0], dataIN[i][1]);
        }        
    }

    /**
     * @return the data
     */
    public Coordinates[] getData() {
        return data;
    }
    
    public Coordinates getDataPoint(int index){
        return data[index];
    }
}