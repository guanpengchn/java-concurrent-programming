package ch6.s6;

import java.util.concurrent.locks.StampedLock;

public class Point {
    private double x,y;
    private final StampedLock s1 = new StampedLock();

    void move(double deltaX, double deltaY){
        long stamp = s1.writeLock();
        try{
            x += deltaX;
            y += deltaY;
        } finally {
            s1.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin(){
        long stamp = s1.tryOptimisticRead();
        double currentX = x, currentY = y;
        if(!s1.validate(stamp)){
            stamp = s1.readLock();
            try{
                currentX = x;
                currentY = y;
            } finally {
                s1.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX*currentX + currentY*currentY);
    }
}
