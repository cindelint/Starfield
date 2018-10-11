Particle[] bobert;

void setup() {
  size(500,500);
  noStroke();
  bobert = new Particle[100];
  for (int i=0; i<bobert.length; i++) {
    bobert[i] = new NormalParticle(250,250);
  }
  bobert[0] = new OddballParticle();
  bobert[1] = new JumboParticle();
}

void draw() {
  background(0);
  for (int i=0; i<bobert.length; i++) {
    bobert[i].show();
    bobert[i].move();
  }
}

class NormalParticle implements Particle{
  public double xPos, yPos;
  public color pColor;
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
    fill(pColor, (float) (xPos*1.2+10));
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
  public color pColor;
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
    fill(pColor, (float) (xPos*1.2+10));
    ellipse((float) xPos, (float) yPos, 10, 10);
    popMatrix();
  }
}
