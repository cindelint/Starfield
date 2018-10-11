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
  bobert[0] = new OddballParticle();
  bobert[1] = new JumboParticle();
}

public void draw() {
  background(0);
  for (int i=0; i<bobert.length; i++) {
    bobert[i].show();
    bobert[i].move();
  }
}

class NormalParticle implements Particle{
  public double xPos, yPos;
  public int pColor;
  public double angle, speed;
  public int startX, startY;
  NormalParticle(int x, int y) {
    xPos = (int) (Math.random() * width/2);
    yPos = 0;
    startX = x;
    startY = y;
    angle = Math.random() * 2 * Math.PI;
    speed = Math.random() * 4 + 1;
    pColor = color((int) (Math.random()*70+185), (int) (Math.random()*70+185), (int) (Math.random()*70+185));
  }
  public void show() {
    pushMatrix();
    translate(startX, startY);
    rotate((float) angle);
    fill(pColor, (float) (xPos*1.2f+10));
    ellipse((float) xPos, (float) yPos, 4, 4);
    popMatrix();
  }
  public void move() {
    xPos += speed;
    if (xPos > 350) {
      xPos = 0;
      speed = Math.random() * 4 + 1;
    }
  }
}

interface Particle {
  public void show();
  public void move();
}

class OddballParticle implements Particle { //uses an interface
  public double xPos, yPos;
  public int pColor;
  OddballParticle() {
    xPos = 250;
    yPos = 250;
    pColor = color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
  }
  public void move() {
    xPos += Math.random() * 4 - 2;
    yPos += Math.random() * 4 - 2;
  }
  public void show() {
    fill(pColor, 100);
    ellipse((float) xPos, (float) yPos, 20, 20);
  }
}

class JumboParticle extends NormalParticle{ //uses inheritance
  public void show() {
    pushMatrix();
    translate(startX, startY);
    rotate((float) angle);
    fill(pColor, (float) (xPos*1.2f+10));
    ellipse((float) xPos, (float) yPos, 10, 10);
    popMatrix();
  }
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
