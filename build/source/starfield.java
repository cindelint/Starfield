import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class starfield extends PApplet {

Particle[] bobert;

public void setup() {
  
  noStroke();
  bobert = new Particle[100];
  for (int i=0; i<bobert.length; i++) {
    bobert[i] = new NormalParticle(250,250);
  }
}

public void draw() {
  background(0);
  for (int i=0; i<bobert.length; i++) {
    bobert[i].show();
    bobert[i].move();
    if (bobert[i].xPos > 350) {
      bobert[i].xPos = 0;
      bobert[i].speed = Math.random() * 4 + 1;
    }
  }
}

class NormalParticle implements Particle{
  public double xPos, yPos;
  public int pColor;
  public double angle, speed;
  public int startX, startY;
  NormalParticle(int x, int y) {
    xPos = 0;
    yPos = 0;
    startX = x;
    startY = y;
    angle = Math.random() * 2 * PI;
    speed = Math.random() * 4 + 1;
    pColor = color((int) (Math.random()*70+185), (int) (Math.random()*70+185), (int) (Math.random()*70+185));
  }
  public void show() {
    pushMatrix();
    translate(startX, startY);
    rotate((float) angle);
    fill(pColor, (float) (xPos+20));
    ellipse((float) xPos, (float) yPos, 4, 4);
    popMatrix();
  }
  public void move() {
    xPos += speed;
  }
}

interface Particle {
  public void show();
  public void move();
}

class OddballParticle implements Particle { //uses an interface

}

class JumboParticle implements Particle{ //uses inheritance

}
  public void settings() {  size(500,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "starfield" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
