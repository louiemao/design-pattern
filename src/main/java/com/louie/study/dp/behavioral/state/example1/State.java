package com.louie.study.dp.behavioral.state.example1;

public interface State {
    public void doAction(Context context);

    class StartState implements State {

        public void doAction(Context context) {
            System.out.println("Player is in start state");
            context.setState(this);
        }

        public String toString(){
            return "Start State";
        }
    }
}
