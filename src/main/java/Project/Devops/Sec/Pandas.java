package Project.Devops.Sec;

import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Pandas {

    private ArrayList<String[]> dataFrames;
    private CsvParser csvParser;
    private String[] columnsName;
    private HashMap<String,String> columnsType;
    private int nbColumns ;
    private int nbLines;


    public Pandas(ArrayList<String[]> dataFrames) {
        this.dataFrames = dataFrames;
        this.csvParser = null;
        this.columnsName = this.dataFrames.get(0);
        this.columnsType = new HashMap<>();
        this.nbLines = this.dataFrames.size();
        this.nbColumns = this.columnsName.length;
        this.typeInference();
    }

    public Pandas(String filname) {
        this.csvParser = new CsvParser(filname);
        this.dataFrames = this.csvParser.getRows();
        this.columnsName = this.csvParser.getColumns();
        this.columnsType = new HashMap<>();
        this.nbColumns = this.csvParser.getNbColumns();
        this.nbLines = this.csvParser.getNbLines();
        this.typeInference();

    }

    private void typeInference(){
        for (int i =0;i<this.nbColumns;i++){
            if (this.dataFrames.get(1)[i].matches(".*[a-zA-Z]+.*")){
                this.columnsType.put(this.columnsName[i],"String");
            } else if (this.dataFrames.get(1)[i].contains(".")){
                this.columnsType.put(this.columnsName[i],"float");
            } else this.columnsType.put(this.columnsName[i],"int");
        }
    }

    public boolean containsLabel(String label){
        for (String lb : this.columnsName){
            if (lb.equals(label)){
                return true;
            }
        }
        return false;
    }

    public String getColumnLabel(int index){
        return this.columnsName[index];
    }

    public int getLabelIndex(String label){
        try {
            for (int i=0;i<this.nbColumns;i++){
                if (label.equals(this.columnsName[i])) return i;
            }
            throw new NoSuchElementException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String[] getColumn(int columnIndex){
        String[] column = new String[this.nbLines];
        for (int i = 0;i<this.nbLines;i++){
            column[i] =  this.dataFrames.get(i)[columnIndex];
        }
        return column;
    }

    public void printDataFrame(){
        int nb = 0;
        for (String[] row : this.dataFrames){
            System.out.print(nb +":");
            for (int i=0; i<this.nbColumns;i++){
                System.out.print(row[i] + ",");
            }
            nb++;
            System.out.println();
        }
    }


    public void printTopTable(){
        int nbPrintedLines = this.nbLines/10;
        if (nbPrintedLines<1) nbPrintedLines = this.nbLines;

        for (int i = 0;i<nbPrintedLines;i++){
            System.out.print(i +":");
            for (int j=0; j<this.nbColumns;j++){
                System.out.print(this.dataFrames.get(i)[j] + ",");
            }
            System.out.println();
        }
    }

    public void printBottomTable(){
        int nbStartingIndexOfPrintedLines = this.nbLines - this.nbLines/10;
        if (nbStartingIndexOfPrintedLines < 1 ) nbStartingIndexOfPrintedLines = this.nbLines;

        for (int i = nbStartingIndexOfPrintedLines;i<this.nbLines;i++){
            System.out.print(i +":");
            for (int j=0; j<this.nbColumns;j++){
                System.out.print(this.dataFrames.get(i)[j] + ",");
            }
            System.out.println();

        }
    }

    public ArrayList<String[]> selectDataframe(int indexOfLine){
        ArrayList<String[]> subSet = new ArrayList<>();

        for (int i = indexOfLine; i<this.nbLines;i++){
            subSet.add(this.dataFrames.get(i));
        }
        return subSet;
    }

    public ArrayList<String[]> selectDataframe(int startingIndex, int lastIndex){
        ArrayList<String[]> subSet = new ArrayList<>();

        if (startingIndex < 0) startingIndex = 0;
        if (lastIndex > this.nbLines) lastIndex = this.nbLines;

        for (int i = startingIndex; i<lastIndex;i++){
            subSet.add(this.dataFrames.get(i));
        }
        return subSet;
    }

    public ArrayList<String[]> selectDataframe(int... selectedLines){
        ArrayList<String[]> subSet = new ArrayList<>();
        for (int row : selectedLines){
            if (row >= 0 && row < this.nbLines){
                subSet.add(this.dataFrames.get(row));
            }
        }
        return subSet;
    }

    public ArrayList<String[]> selectDataframe(String... labels){
        ArrayList<String[]> subSet = new ArrayList<>();

        for ( String lb : labels ){
            if (this.containsLabel(lb)){
                subSet.add(this.getColumn(this.getLabelIndex(lb)));
            }
        }
        return subSet;
    }

    public ArrayList<String[]> selectDataframe(String checkedValue, String label){
        if (!this.containsLabel(label)){
            try {
                throw new Exception("Not found Label");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayList<String[]> subSet = new ArrayList<>();

        for (String [] row : this.dataFrames){
            if (row[this.getLabelIndex(label)].equals(checkedValue)){
                subSet.add(row);
            }
        }
        return subSet;
    }

    public ArrayList<String[]> selectDataframe(String checkedValue, int index){

        return selectDataframe(checkedValue, this.getColumnLabel(index));
    }

    public ArrayList<String[]> selectDataframe(int testType, int checkedValue, String label){
        if (!this.containsLabel(label) || this.columnsType.get(label).equals("String") || this.columnsType.get(label).equals("float") || testType < 0 || testType > 2){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayList<String[]> subSet = new ArrayList<>();

        for (int i = 1;i<this.dataFrames.size();i++){
            int t = Integer.parseInt(this.dataFrames.get(i)[this.getLabelIndex(label)]);
            if (this.personnalizedIs(testType,checkedValue,Integer.parseInt(this.dataFrames.get(i)[this.getLabelIndex(label)]))){
                subSet.add(this.dataFrames.get(i));
            }
        }
        return subSet;
    }

    public ArrayList<String[]> selectDataframe(int testType, float checkedValue, String label){
        if (!this.containsLabel(label) || this.columnsType.get(label).equals("String") || this.columnsType.get(label).equals("int")){
            try {
                throw new Exception("Not found Label");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayList<String[]> subSet = new ArrayList<>();

        for (int i = 1;i<this.dataFrames.size();i++){
            if (this.personnalizedIs(testType,checkedValue,Float.parseFloat(this.dataFrames.get(i)[this.getLabelIndex(label)]))){
                subSet.add(this.dataFrames.get(i));
            }
        }
        return subSet;
    }





    public float average (int index) {

        if ((index > this.nbLines || index < 0 ) || this.columnsType.get(this.getColumnLabel(index)).equals("String")){
            try {
                throw new Exception("Column dont exist ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        float sum = 0;
        for (int i =1; i<this.nbLines;i++){
            sum = sum + Float.parseFloat(this.dataFrames.get(i)[index]);
        }
        return sum/(this.nbLines -1);
    }

    public float average (String label){
        int index = this.getLabelIndex(label);
        return this.average(index);
    }

    public float min (int index) {

        if (index > this.nbLines || this.columnsType.get(this.getColumnLabel(index)).equals("String")) {
            try {
                throw new Exception("Column dont exist");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

            float min = Float.parseFloat(this.dataFrames.get(1)[index]);

            for (int i = 2; i < this.nbLines; i++) {
                if (min > Float.parseFloat(this.dataFrames.get(i)[index])) {
                    min = Float.parseFloat(this.dataFrames.get(i)[index]);
                }
            }

            return min;
    }

    public float min (String label){
        int index = this.getLabelIndex(label);
        return this.min(index);
    }

    public float max (int index) {

        if (index > this.nbLines || this.columnsType.get(this.getColumnLabel(index)).equals("String")){
            try {
                throw new Exception("Column dont exist");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        float max = Float.parseFloat(this.dataFrames.get(1)[index]);

        for (int i =2; i< this.nbLines; i++){
            if (max < Float.parseFloat(this.dataFrames.get(i)[index])){
                max = Float.parseFloat(this.dataFrames.get(i)[index]);
            }
        }

        return max;
    }

    public float max (String label){
        int index = this.getLabelIndex(label);
        return this.max(index);
    }


    public ArrayList<String[]> getDataFrames() {
        return dataFrames;
    }

    public String[] getColumnsName() {
        return columnsName;
    }

    public HashMap<String, String> getColumnsType() {
        return columnsType;
    }

    public int getNbColumns() {
        return nbColumns;
    }

    public int getNbLines() {
        return nbLines;
    }

    public Boolean personnalizedIs(int testType, int x, int y){
        switch (testType){
            case 0 :
                return x == y;
            case 1 :
                return y > x;
            default:
                return y < x ;

        }
    }

    public Boolean personnalizedIs(int testType, float x, float y){
        switch (testType){
            case 0 :
                return x == y;
            case 1 :
                return y > x;
            default:
                return y < x ;

        }
    }

    public Boolean personnalizedIs( String x, String y){
        return x.equals(y);
    }



}
