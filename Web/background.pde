//Define intersection array parameters
int vertical_vertices = 6;
int horizontal_vertices = 12;

int vrt = vertical_vertices + 8;
int hrz = horizontal_vertices + 8;

PVector[][] points = new PVector[hrz][vrt];

//Define variance array parameters
float vVar = 400;
float hVar = 500;
PVector[][] variance = new PVector[hrz][vrt];

//Define random seed vals
int max = 100;
int min = -100;
PVector[][] randoms = new PVector[hrz][vrt];

//Define noise generator parameters
float scale = 0.02;
float speed = 0.0005;
float falloff = 0.9;
int octaves = 3;

//Define color values
color color_TL = #189EB1;
color color_TR = #073D6D;
color color_BL = #964E6B;
color color_BR = #0F2A54;

void setup() 
{
  //size(3200, 1800);
  size(2400, 1350);
  //size(1600, 900);
  noiseDetail(octaves, falloff);

  //Set random values
  for (int x = 0; x < hrz; x++)
  {
    for (int y = 0; y < vrt; y++)
    {
      //randoms[x][y] = new PVector(random(-vVar, vVar), random(-vVar, vVar), random(-vVar, vVar));
      randoms[x][y] = new PVector(random(min, max), random(min, max), random(min, max));
    }
  }
}

void draw()
{
  //Clear the screen
  background(0);

  //Set standard intersections
  for (int x = 0; x < hrz; x++)
  {
    for (int y = 0; y < vrt; y++)
    {
      points[x][y] = new PVector((width / (horizontal_vertices - 1)) * (x - 4), (height / (vertical_vertices - 1)) * (y - 4));
    }
  }

  //Set variances
  for (int x = 0; x < hrz; x++)
  {
    for (int y = 0; y < vrt; y++)
    {
      PVector cur = randoms[x][y];
      float xVar = noise(cur.x * scale, ((frameCount + 1000) * speed) + cur.z);
      float yVar = noise(cur.y * scale, ((frameCount + 1000) * speed) + cur.z);
      variance[x][y] = new PVector((xVar - 0.5) * vVar, (yVar - 0.5) * hVar);
    }
  }

  //Draw triangles
  for (int x = 0; x < hrz - 1; x++)
  {
    for (int y = 0; y < vrt - 1; y++)
    {
      PVector TL = new PVector(points[x][y].x + variance[x][y].x, points[x][y].y + variance[x][y].y);
      PVector TR = new PVector(points[x + 1][y].x + variance[x + 1][y].x, points[x + 1][y].y + variance[x + 1][y].y);
      PVector BL = new PVector(points[x][y + 1].x + variance[x][y + 1].x, points[x][y + 1].y + variance[x][y + 1].y);
      PVector BR = new PVector(points[x + 1][y + 1].x + variance[x + 1][y + 1].x, points[x + 1][y + 1].y + variance[x + 1][y + 1].y);

      noStroke();
      fill(getColor(TL, TR, BL));
      triangle(TL.x, TL.y, TR.x, TR.y, BL.x, BL.y);
      fill(getColor(TR, BR, BL));
      triangle(TR.x, TR.y, BR.x, BR.y, BL.x, BL.y);
    }
  }
}

public color getColor(PVector a, PVector b, PVector c)
{
  PVector o = new PVector((a.x + b.x + c.x) / 3, (a.y + b.y + c.y) / 3);
  o.x = o.x / width;
  o.y = o.y / height;
  color top = color(lerp(red(color_TL), red(color_TR), o.x), lerp(green(color_TL), green(color_TR), o.x), lerp(blue(color_TL), blue(color_TR), o.x));
  color bottom = color(lerp(red(color_BL), red(color_BR), o.x), lerp(green(color_BL), green(color_BR), o.x), lerp(blue(color_BL), blue(color_BR), o.x));
  return color(lerp(red(top), red(bottom), o.y), lerp(green(top), green(bottom), o.y), lerp(blue(top), blue(bottom), o.y));
}