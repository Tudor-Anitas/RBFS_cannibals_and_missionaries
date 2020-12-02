package com.company;


import java.util.LinkedList;
import java.util.List;

public class State {

    public State parent;

    private int numberOfCannibalsLEFT;
    private int numberOfMissionariesLEFT;
    private int numberOfCannibalsRight;
    private int numberOfMissionariesRight;
    public int f;
    public List<State> childs;
    public int number;
    private String boatside;

    /**
     *
     * @param numberOfCannibalsLEFT number of cannibals on left side
     * @param numberOfMissionariesLEFT number of missionaries on left side
     * @param numberOfCannibalsRight nr of cannibals on right side
     * @param numberOfMissionariesRight nr of missionaries on right side
     * @param boatside the side of the boat
     */
    public State(int numberOfCannibalsLEFT, int numberOfMissionariesLEFT,int numberOfCannibalsRight, int numberOfMissionariesRight, String boatside){
        this.numberOfCannibalsLEFT = numberOfCannibalsLEFT;
        this.numberOfMissionariesLEFT = numberOfMissionariesLEFT;
        this.numberOfCannibalsRight = numberOfCannibalsRight;
        this.numberOfMissionariesRight = numberOfMissionariesRight;
        this.childs = new LinkedList<>();
        this.boatside = boatside;
        f = 0;
        number = 0;
    }

    //
    //! returns true if it's a valid state, false otherwise
    //
    public boolean validateState(int child) throws Exception {
        // exceptions
        if(this.equals(new State(3, 3, 0,0, "Left") )) {
            System.out.println("Initial state");
            return true;
        }
        if(this.compare(this.parent.parent))
            return false;

        // first rule
        // there are more cannibals than missionaries on left side
        if((numberOfMissionariesLEFT < numberOfCannibalsLEFT) && numberOfMissionariesLEFT > 0) {
                System.out.println("State " + child + " is invalid because the number of missionaries on the left side is lower than the number of cannibals");
                return false;
        }
        // there are more cannibals than missionaries on right side
        if((numberOfMissionariesRight < numberOfCannibalsRight) && numberOfMissionariesRight > 0){
            System.out.println("State " + child + " is invalid because the number of missionaries on the right side is lower than the number of cannibals");
            return false;
        }
        // there cannot be less than 0
        if(numberOfMissionariesLEFT < 0){
            System.out.println("State " + child + " is invalid because the number of missionaries on left side is lower than 0");
            return false;
        }
        // there cannot be more than 0
        if(numberOfMissionariesLEFT > 3){
            System.out.println("State " + child + " is invalid because the number of missionaries on left side is greater than 3");
            return false;
        }
        if(numberOfMissionariesRight < 0){
            System.out.println("State " + child + " is invalid because the number of missionaries on right side is lower than 0");
            return false;
        }
        if(numberOfMissionariesRight > 3){
            System.out.println("State " + child + " is invalid because the number of missionaries on right side is greater than 3");
            return false;
        }
        if(numberOfCannibalsLEFT < 0 ){
            System.out.println("State is invalid because the number of cannibals on left side is lower than 0");
            return false;
        }
        if(numberOfCannibalsLEFT > 3 ){
            System.out.println("State is invalid because the number of cannibals on left side is greater than 0");
            return false;
        }
        if(numberOfCannibalsRight < 0 ){
            System.out.println("State is invalid because the number of cannibals on right side is lower than 0");
            return false;
        }
        if(numberOfCannibalsRight > 3 ){
            System.out.println("State is invalid because the number of cannibals on right side is greater than 0");
            return false;
        }
        return true;
    }

    /**
     *? check's a state to see if it's the final state
     * @return "DONE" if this is the final state or "NOT DONE" if it's not
     * @throws Exception
     */
    public String prevalidate() throws Exception {
        // compare to final state to check if it's done
        if(this.compare( new State(0,0, 3,3, "Right"))) {
            System.out.print("Final state found on level " + Main.stepsFromInitialState);
            return "DONE";
        }else{
            return "NOT DONE";
        }

    }

    /**
     * Generates all possible children from the state then uses validateState() to check if it respects all the game rules
     * @param STATUS "DONE" for final state or "NOT DONE" for a non-final
     * @throws Exception
     */
    public void generate(String STATUS) throws Exception {
        final String LEFTSIDE = "Left";
        final String RIGHTSIDE = "Right";
        // the branch level from the start
        Main.stepsFromInitialState++;
        System.out.println(Main.stepsFromInitialState+"\n");


        if(boatside.equals(LEFTSIDE) && !STATUS.equals("DONE")){

            // we create all possible outcomes

            // case 1: 2 cannibals in boat
            State a = new State(numberOfCannibalsLEFT - 2, numberOfMissionariesLEFT, numberOfCannibalsRight + 2, numberOfMissionariesRight,RIGHTSIDE);
            // case 2: 2 missionaries in a boat
            State b = new State(numberOfCannibalsLEFT, numberOfMissionariesLEFT - 2, numberOfCannibalsRight, numberOfMissionariesRight + 2, RIGHTSIDE);
            // case 3: 1 cannibal and 1 missionary
            State c = new State(numberOfCannibalsLEFT - 1, numberOfMissionariesLEFT - 1, numberOfCannibalsRight + 1, numberOfCannibalsRight + 1, RIGHTSIDE);
            // case 4: 1 cannibal
            State d = new State(numberOfCannibalsLEFT - 1, numberOfMissionariesLEFT, numberOfCannibalsRight + 1, numberOfMissionariesRight, RIGHTSIDE);
            // case 5: 1 missionary
            State e = new State(numberOfCannibalsLEFT, numberOfMissionariesLEFT - 1, numberOfCannibalsRight, numberOfMissionariesRight + 1, RIGHTSIDE);
            // validate all possible states
            a.parent = this;
            b.parent = this;
            c.parent = this;
            d.parent = this;
            e.parent = this;
            if(a.validateState(1)){
                // add state to children list
                childs.add(a);

                a.f = f(1);
                a.number = 0;
                a.toString(1);
            }
            // validate all possible states
            if(b.validateState(2)){
                // add state to children list
                childs.add(b);

                b.f = f(2);
                b.number = 1;
                b.toString(2);
            }
            // validate all possible states
            if(c.validateState(3)){
                // add state to children list
                childs.add(c);

                c.f = f(3);
                c.number = 3;
                c.toString(3);
            }
            if(d.validateState(4)){
                // add state to children list
                childs.add(c);

                d.f = f(4);
                d.number = 4;
                d.toString(4);
            }
            if(e.validateState(5)){
                // add state to children list
                childs.add(c);

                e.f = f(5);
                e.number = 5;
                e.toString(5);
            }
        } else if(boatside.equals(RIGHTSIDE) && !STATUS.equals("DONE")){ // if the boat is in the right side

            // we create all possible outcomes

            // case 1: 2 cannibals in boat
            State a = new State(numberOfCannibalsLEFT + 2 , numberOfMissionariesLEFT, numberOfCannibalsRight - 2, numberOfMissionariesRight ,LEFTSIDE);
            // case 2: 2 missionaries in a boat
            State b = new State(numberOfCannibalsLEFT, numberOfMissionariesLEFT + 2 , numberOfCannibalsRight, numberOfMissionariesRight - 2 , LEFTSIDE);
            // case 3: 1 cannibal and 1 missionary
            State c = new State(numberOfCannibalsLEFT + 1 , numberOfMissionariesLEFT + 1 , numberOfCannibalsRight - 1, numberOfMissionariesRight - 1, LEFTSIDE);
            // case 4: 1 cannibal
            State d = new State(numberOfCannibalsLEFT + 1,numberOfMissionariesLEFT, numberOfCannibalsRight - 1, numberOfMissionariesRight , LEFTSIDE);
            // case 5: 1 missionary
            State e = new State(numberOfCannibalsLEFT, numberOfMissionariesLEFT + 1, numberOfCannibalsRight, numberOfMissionariesRight - 1, LEFTSIDE);

            // validate all possible states
            a.parent = this;
            b.parent = this;
            c.parent = this;
            d.parent = this;
            e.parent = this;
            if(a.validateState(1)){
                // add state to children list
                childs.add(a);

                a.f = f(1);
                a.number = 1;
                a.toString(1);
            }
            // validate all possible states
            if(b.validateState(2)){
                // add state to children list
                childs.add(b);

                b.f = f(2);
                b.number = 2;
                b.toString(2);
            }
            // validate all possible states
            if(c.validateState(3)){
                // add state to children list
                childs.add(c);

                c.f = f(3);
                c.number = 3;
                c.toString(3);
            }
            // validate all possible states
            if(d.validateState(4)){
                // add state to children list
                childs.add(d);

                d.f = f(4);
                d.number = 4;
                d.toString(4);
            }
            // validate all possible states
            if(e.validateState(5)){
                // add state to children list
                childs.add(e);

                e.f = f(5);
                e.number = 5;
                e.toString(5);
            }
        }

    }

    //? the distance from the initial state to the current state
    public int g(){
        return Main.stepsFromInitialState;
    }
    //? the distance from the current state to the solution
    public int h(int child) throws Exception {
        //! every step further from the initial state is closer to the solution
        return child - Main.stepsFromInitialState * Main.stepsFromInitialState - 1;
    }
    //? calculates the f-value of the state
    public int f(int child) throws Exception { return g() + h(child);}

    public void addParent(State state){
        parent = state;
    }

    public void toString(int child){
        System.out.println("Child "+ child + "\nCannibals Left: "+ numberOfCannibalsLEFT + "\nMissionaries Left: " + numberOfMissionariesLEFT + "\nCannibals Right: " + numberOfCannibalsRight + "\nMissionaries Right: " + numberOfMissionariesRight + "\nSide of the boat: " + boatside+"\n");
    }

    /**
     * Compares two states by checking the number of cannibals and missionaries on both sides
     * @param state The state with which it must be compared
     * @return Returns true if the states are the same or false if otherwise
     */
    public boolean compare(State state){
        if(numberOfCannibalsLEFT != state.getNumberOfCannibalsLEFT())
            return false;
        if(numberOfMissionariesLEFT != state.getNumberOfMissionariesLEFT())
            return false;
        if(numberOfCannibalsRight != state.getNumberOfCannibalsRight())
            return false;
        if(numberOfMissionariesRight != state.getNumberOfMissionariesRight())
            return false;
        if(!boatside.equals(state.boatside))
            return false;
        return true;
    }


    public int getNumberOfCannibalsLEFT() {
        return numberOfCannibalsLEFT;
    }
    public int getNumberOfMissionariesLEFT() {
        return numberOfMissionariesLEFT;
    }
    public int getNumberOfCannibalsRight(){ return numberOfCannibalsRight;}
    public int getNumberOfMissionariesRight(){ return numberOfMissionariesRight;}

    public String getBoatside() {
        return boatside;
    }


}
