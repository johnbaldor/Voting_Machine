package edu.wpi.hw6;

public class AlreadyNominatedException extends Exception{
    public AlreadyNominatedException(String name){
        super(name + " is already nominated");
    }

}
