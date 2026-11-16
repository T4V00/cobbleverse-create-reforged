import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/** Draws the project logo (original artwork: a brass gear and a green sprout). Usage: java Logo.java out.png */
public class Logo {
   public static void main(String[] args) throws Exception {
      int s = 512;
      BufferedImage img = new BufferedImage(s, s, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g = img.createGraphics();
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

      // background
      g.setPaint(new RadialGradientPaint(new Point2D.Float(s / 2f, s / 2f), s * 0.75f,
         new float[]{0f, 1f}, new Color[]{new Color(0x2E4A5C), new Color(0x13202A)}));
      g.fill(new RoundRectangle2D.Float(0, 0, s, s, 96, 96));

      // gear
      double cx = s / 2.0, cy = s / 2.0 - 10, rOut = 190, rIn = 155;
      int teeth = 12;
      Path2D gear = new Path2D.Double();
      for (int i = 0; i < teeth * 4; i++) {
         double a = Math.PI * 2 * i / (teeth * 4);
         double r = (i % 4 == 0 || i % 4 == 1) ? rOut : rIn;
         double x = cx + Math.cos(a) * r, y = cy + Math.sin(a) * r;
         if (i == 0) gear.moveTo(x, y); else gear.lineTo(x, y);
      }
      gear.closePath();
      Area gearArea = new Area(gear);
      gearArea.subtract(new Area(new Ellipse2D.Double(cx - 105, cy - 105, 210, 210)));
      g.setPaint(new GradientPaint(0, 60, new Color(0xF2C46B), 0, 450, new Color(0xB07A2A)));
      g.fill(gearArea);
      g.setStroke(new BasicStroke(6f));
      g.setColor(new Color(0x6B4517));
      g.draw(gearArea);

      // inner disc
      g.setColor(new Color(0x1C2E3A));
      g.fill(new Ellipse2D.Double(cx - 105, cy - 105, 210, 210));

      // sprout
      g.setColor(new Color(0x5FBF4A));
      g.setStroke(new BasicStroke(14f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
      g.draw(new QuadCurve2D.Double(cx, cy + 70, cx - 6, cy + 10, cx, cy - 30));
      Path2D leafL = new Path2D.Double();
      leafL.moveTo(cx, cy - 10);
      leafL.curveTo(cx - 30, cy - 70, cx - 85, cy - 55, cx - 85, cy - 55);
      leafL.curveTo(cx - 80, cy - 10, cx - 35, cy + 5, cx, cy - 10);
      g.fill(leafL);
      Path2D leafR = new Path2D.Double();
      leafR.moveTo(cx, cy - 25);
      leafR.curveTo(cx + 25, cy - 90, cx + 85, cy - 80, cx + 85, cy - 80);
      leafR.curveTo(cx + 85, cy - 30, cx + 40, cy - 10, cx, cy - 25);
      g.setColor(new Color(0x7FD65E));
      g.fill(leafR);

      // label
      g.setFont(new Font("SansSerif", Font.BOLD, 64));
      String t = "C x C";
      FontMetrics fm = g.getFontMetrics();
      int tx = (s - fm.stringWidth(t)) / 2, ty = s - 34;
      g.setColor(new Color(0, 0, 0, 120));
      g.drawString(t, tx + 3, ty + 3);
      g.setColor(new Color(0xF5EBD7));
      g.drawString(t, tx, ty);
      g.dispose();
      ImageIO.write(img, "png", new File(args[0]));
   }
}
