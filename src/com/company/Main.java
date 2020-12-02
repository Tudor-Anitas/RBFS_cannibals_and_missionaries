package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.min;
import static sun.swing.MenuItemLayoutHelper.max;

public class Main {

    public static void main(String[] args) throws Exception {

        State initialState = new State(3,3, 0,0, "Left");
        //State initialState = new State(1,3, 2,0, "Right");
        //State initialState = new State(2,3, 1,0, "Left");
        //State initialState = new State(0,3,3,0 , "Right");
        //State initialState = new State(1,3,2,0 , "Left");
        //State initialState = new State(1,1, 2,2, "Right");
        //State initialState = new State(2,2,1,1 , "Left");
        //State initialState = new State(2,0,1,3, "Right");
        //State initialState = new State(3,0,0,3 , "Left");
        //State initialState = new State(1,0,2,3 , "Right");
        //State initialState = new State(2,0,1,3, "Left");

        // initialize the first state's f-value to be the biggest
        initialState.f = 10000000;
        // create the parent of initial state
        initialState.addParent(new State(-1,-1,-1,-1,"Left"));
        RBFS(initialState, initialState.f);



    }

    public static int stepsFromInitialState = 0;
    public static State best;
    public static State alternative;
    public static State result;
    public static State failure = new State(0,0,0,0,"Left");



    public static State RBFS(State state, int bound) throws Exception {
        // check if it's final state
        if(state.compare(new State(0,0,3,3,"Right")))
            return state;
        // if it's not, generate children from state
        state.generate(state.prevalidate());
        // if it doesn't generate, return failure
        if(state.childs == null)
            return failure;


        while(true){
            // the best f-value successor of state
            best = state.childs.get(0);
            // if the value of the best node of the state is bigger than the bound, return a failure
            if(best.f > bound)
                return failure;
            // second best f-value successor of state
            if(state.childs.size() > 1)
                alternative = state.childs.get(1);
            else
                alternative = state.parent;
            result = RBFS(best,min(bound,alternative.f));
            if(result.compare(failure))
                return result;
        }
    }
}
