package edu.wpi.hw6;

public class MoreThanOnceException extends Exception {

    public MoreThanOnceException(String name){
        super(name + " was voted for more than once in a single vote" ); }
}
