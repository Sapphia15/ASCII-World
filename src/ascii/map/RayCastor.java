package ascii.map;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import ascii.sprites.Sprite;
import gameutil.geom.g2D.PointR2;
import gameutil.geom.g2D.RectangleR2;
import gameutil.geom.g2D.VectorR2;
import gameutil.text.Console;

public class RayCastor {
	int resolution;
	VectorR2[] rays;
	int direction=1;//right is 1, left is -1
	static final Random rand=new Random();
	
	private static final VectorR2 invertX=findInvertX();
	
	
	public RayCastor(int resolution) {
		this.resolution=resolution;
		int distance;
		if (resolution%2==0) {
			distance=resolution/2;
		} else {
			distance=(resolution+1)/2;
		}
		rays=new VectorR2[resolution];
		
		for (int i=0;i<resolution;i++) {
			try {
				rays[i]=new VectorR2(distance,i-(double) resolution/2);
				rays[i]. $X$ (10);//make view distance longer
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//should never happen
			}
		}
	}
	
	private static VectorR2 findInvertX() {
		try {
			return new VectorR2(-1,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;//should never happen
		}
	}
	
	
	
	public int getResolution() {
		return resolution;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void turn() {
		for (VectorR2 v:rays) {
			direction*=-1;
			//v. $X$ (invertX);//invert X of vectors (multiplying by scalar -1 would result in one direction being perceived upside down
			/*try {
				v=new VectorR2(Math.abs(v.getMagnetudeX())*direction,v.getMagnetudeY());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		
	}
	
	/**Returns the array of sprites that are visible from the array of sprites in the level
	 * 
	 * @param sprites
	 * @return
	 */
	public Sprite[] getVisable(Sprite[] sprites,Point pos) {
		Sprite[] spritesVisable=new Sprite[resolution];
		int visableIndex=0;
		for (int i=0;i<rays.length;i++) {
			VectorR2 v=rays[i];
			try {
				v=new VectorR2(new PointR2(pos.x,pos.y),new PointR2(pos.x+direction*Math.abs(v.getMagnetudeX()),pos.y+v.getMagnetudeY()));
				/*v.move(new PointR2(pos));
				v.rotate(180*direction);*///may use these to allow for other view angles; however, for now they do not work.
			} catch (Exception e) {
				e.printStackTrace();
				//should never happen
			}
			ArrayList<Sprite> spritesClosest=new ArrayList<>();
			double closest=2147483647;
			//find sprites intersecting ray and the closest to the origin
			for (Sprite s:sprites) {
				RectangleR2 spriteRect=new RectangleR2(new PointR2(s.getPos()),1,1);
				if (s.isVisible()&&v.intersects(spriteRect)) {
					
					double spriteDistance=v.base().distance(v.intersection(spriteRect));//new PointR2(s.getPos()));
					if (spriteDistance<closest) {
						closest=spriteDistance;
						spritesClosest.clear();
						spritesClosest.add(s);
					} else if (spriteDistance==closest) {
						spritesClosest.add(s);
					}
					System.out.println("Closest"+closest);
				}
			}
			
			if (spritesClosest.size()>0) {
				//if any sprites share the closest space psuedorandomly decide what sprite to show
				int index=rand.nextInt(spritesClosest.size());
				spritesVisable[visableIndex]=spritesClosest.get(index);
				System.out.println("Sprites exist");
			} else {
				spritesVisable[visableIndex]=null;//air
			}
			visableIndex++;
			/*try {
				v=new VectorR2(v.getMagnetudeX(),v.getMagnetudeY());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		return spritesVisable;
	}
	
	
}
