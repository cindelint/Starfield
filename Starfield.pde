Particle[] bobert;

void setup() {
  size(500,500);
  noStroke();
  bobert = new Particle[130];
  for (int i=0; i<bobert.length; i++) {
    bobert[i] = new NormalParticle(250,250);
  }
  bobert[0] = new OddballParticle();
  for (int i=1; i<4; i++) {
    bobert[i] = new JumboParticle(250,250);
  }
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
  float xPos, yPos;
  color pColor;
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
    size = (float) (Math.random() * 3 + 1.5);
  }
  public void show() {
    translate(startX, startY);
    rotate((float) angle);
    fill(pColor, (xPos*1.2+10));
    noStroke();
    ellipse(xPos, yPos, size, size);
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
  float xPos, yPos;
  color pColor;
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
    fill(pColor, 200);
    stroke(20, 160);
    strokeWeight(1);
    ellipse(xPos, yPos, 35, 13);

    fill(183, 228, 232, 145);
    stroke(20, 160);
    strokeWeight(1);
    beginShape();
    arc(xPos, yPos-4, 20, 5, 0, PI);
    arc(xPos, yPos-4, 20, 16, PI, 2*PI);
    endShape();

    fill(15, 102, 2, 100);
    beginShape();
    arc(xPos-2, yPos-3, 7, 4, 0, PI);
    arc(xPos-2, yPos-3, 7, 10, PI, 2*PI);
    endShape();
    //eyes

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
      stroke(255, xPos*1.2);
      noFill();
      strokeWeight(ringNum);
      arc(xPos, yPos, size/0.9, size/3, 5*PI/6, 13*PI/6);
    }
    if (xPos==0) {
      pColor = color(randColor(), 175);
      size = (float) (Math.random() * 10 + 15);
    }
  }
}

color randColor() {
  return color((int) (Math.random()*150+50), (int) (Math.random()*150+50), (int) (Math.random()*150+50));
}
