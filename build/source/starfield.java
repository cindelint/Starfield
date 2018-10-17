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
  bobert = new Particle[130];
  for (int i=0; i<bobert.length; i++) {
    bobert[i] = new NormalParticle(width/2, height/2);
  }
  bobert[0] = new OddballParticle();
  for (int i=1; i<4; i++) {
    bobert[i] = new JumboParticle(width/2, height/2);
  }
}

public void draw() {
  background(0);
  for (int i=0; i<bobert.length; i++) {
    pushMatrix();
    bobert[i].show();
    bobert[i].move();
    popMatrix();
  }
}

class NormalParticle implements Particle{
  float xPos, yPos;
  int pColor;
  double angle, speed;
  int startX, startY;
  float size;
  NormalParticle(int x, int y) {
    xPos = (int) (Math.random() * width/2);
    yPos = 0;
    startX = x;
    startY = y;
    angle = Math.random() * 2 * Math.PI;
    speed = Math.random() * 4 + 1;
    pColor = color((int) (Math.random()*100+155), (int) (Math.random()*100+155), (int) (Math.random()*100+155));
    size = (float) (Math.random() * 3 + 1.5f);
  }
  public void show() {
    translate(startX, startY);
    rotate((float) angle);
    fill(pColor, (xPos*1.2f+10));
    noStroke();
    ellipse(xPos, yPos, size, size);
  }
  public void move() {
    xPos += speed;
    size += size/600;
    if (keyPressed) {
      if (keyCode == UP) {
        speed += .1f;
      } else if (keyCode == RIGHT) {
        angle += .01f;
      } else if (keyCode == LEFT) {
        angle -= .01f;
      }
    }
    if (xPos > 500) {
      xPos = 0;
      size = (float) (Math.random() * 3 + 1.5f);
      speed = Math.random() * 4 + 1;
      angle = Math.random() * 2 * Math.PI;
    }
  }
}

interface Particle {
  public void show();
  public void move();
}

class OddballParticle implements Particle { //uses an interface
  float xPos, yPos;
  int pColor;
  OddballParticle() {
    xPos = 300;
    yPos = 400;
    pColor = randColor();
  }
  public void move() {
    xPos += Math.random() * 4 - 2;
    yPos += Math.random() * 4 - 2;
  }
  public void show() {
    //spaceship body
    fill(pColor, 200);
    stroke(20, 160);
    strokeWeight(1);
    ellipse(xPos, yPos, 35, 13);

    //spaceship window
    fill(183, 228, 232, 145);
    stroke(20, 160);
    strokeWeight(1);
    beginShape();
    arc(xPos, yPos-4, 20, 5, 0, PI);
    arc(xPos, yPos-4, 20, 16, PI, 2*PI);
    endShape();

    //alien body
    fill(15, 102, 2, 140);
    noStroke();
    beginShape();
    arc(xPos-2, yPos-3, 8, 4, 0, PI);
    arc(xPos-2, yPos-3, 8, 14, PI, 2*PI);
    endShape();
    //alien eyes
    fill(20, 140);
    ellipse(xPos-4, yPos-5.5f, 2, 3);
    ellipse(xPos-1.5f, yPos-5.5f, 2, 3);

    //additiosn to spaceship
    fill(175, 150);
    stroke(20, 100);
    ellipse(xPos-12, yPos, 3, 3);
    ellipse(xPos-4, yPos+2, 3, 3);
    ellipse(xPos+4, yPos+2, 3, 3);
    ellipse(xPos+12, yPos, 3, 3);
  }
}

class JumboParticle extends NormalParticle{ //uses inheritance
  int ringNum;
  JumboParticle(int x, int y) {
    super(x, y);
    pColor = randColor();
    ringNum = (int) (Math.random()*3);
  }
  public void show() {
    super.show();
    if (ringNum != 0) {
      stroke(255, xPos);
      noFill();
      strokeWeight(ringNum);
      arc(xPos, yPos, size/0.9f, size/3, 5*PI/6, 13*PI/6);
    }
    if (xPos==0) {
      pColor = color(randColor(), 175);
      size = (float) (Math.random() * 10 + 10);
    }
  }
}

public int randColor() {
  return color((int) (Math.random()*150+50), (int) (Math.random()*150+50), (int) (Math.random()*150+50));
}
  public void settings() {  size(600,600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "starfield" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
