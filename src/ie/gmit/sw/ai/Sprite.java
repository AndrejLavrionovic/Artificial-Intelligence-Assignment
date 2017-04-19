package ie.gmit.sw.ai;

import javax.imageio.*;
import java.awt.image.*;

public class Sprite {
    
    // Properties
    private String name; //The name of this sprite
    private BufferedImage[] frames; //The set of image frames to animate
    private int index = 0; //Initial starting index in array

    /*
    * String... - Zero or more String objects (or an array of them) may be
    * passed as the argument(s) for that method.
    * http://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html#varargs
    */
    public Sprite(String name, String... images) throws Exception{
        this.name = name;
        this.index = 0; //Initialise the starting index to zero
        this.frames = new BufferedImage[images.length]; //Initialise the image frames

        for (int i = 0; i < images.length; i++){
                frames[i] = ImageIO.read(new java.io.File(images[i])); //Read in each image as a BufferedImage
        }
    }

    /*
    // Methods that checks life level
    // Returns true if life is grater then 0
    public boolean isAlive(){
        return lifeForce > 0;
    }
    */


    // We could get the spider moving to the next position here, Thread.sleep(10) 
    // Use Thread Executor service to manage the threading......

    /*
    * Andrej:
    * It seems like this method is returning the next frame ater
    * current one. But the purpose is not clear yet for me.
    */
    public BufferedImage getNext(){ //Returns the next image frame
        int idx = index;
        if (index < frames.length - 1){
                index++;
        }else{
                index = 0; //Circle back to the start of the array
        }
        return frames[idx]; 
    }
}
