Particle[] bobert;

void setup() {
  size(500,500);
  noStroke();
  bobert = new Particle[130];
  for (int i=0; i<bobert.length; i++) {
    bobert[i] = new NormalParticle(250,250);
  }
  bobert[0] = new OddballParticle();
  bobert[1] = new JumboParticle(250,250);
}

void draw() {
  background(0);
  for (int i=0; i<bobert.length; i++) {
    pushMatrix();
    bobert[i].show();
    bobert[i].move();
    popMatrix();
  }
}

class NormalParticle implements Particle{
  public double xPos, yPos;
  public color pColor;
  public double angle, speed;
  public int startX, startY;
  public float size;
  NormalParticle(int x, int y) {
    xPos = (int) (Math.random() * width/2);
    yPos = 0;
    startX = x;
    startY = y;
    angle = Math.random() * 2 * Math.PI;
    speed = Math.random() * 4 + 1;
    pColor = color((int) (Math.random()*100+155), (int) (Math.random()*100+155), (int) (Math.random()*100+155));
    size = (float) (Math.random() * 3 + 1.5);
  }
  public void show() {
    translate(startX, startY);
    rotate((float) angle);
    fill(pColor, (float) (xPos*1.2+10));
    ellipse((float) xPos, (float) yPos, size, size);
  }
  public void move() {
    xPos += speed;
    size += size/500;
    if (xPos > 400) {
      xPos = 0;
      size = (float) (Math.random() * 3 + 1.5);
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
  public double xPos, yPos;
  public color pColor;
  OddballParticle() {
    xPos = 250;
    yPos = 400;
    pColor = randColor();
  }
  public void move() {
    xPos += Math.random() * 2 - 1;
    yPos += Math.random() * 2 - 1;
  }
  public void show() {
    fill(pColor, 100);
    ellipse((float) xPos, (float) yPos, 20, 20);
  }
}

class JumboParticle extends NormalParticle{ //uses inheritance
  JumboParticle(int x, int y) {
    super(x, y);
    pColor = randColor();
  }
  public void show() {
    super.show();
    if (xPos==0) {
      pColor = color(randColor(), 150);
      size = (float) (Math.random() * 10 + 15);
    }
  }
}

color randColor() {
  return color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
}
