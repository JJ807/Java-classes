class lab01 {

    static void draw_line(double x1, double y1, double x2, double y2){
        double a,b,x,y;

        if(x1 == x2){ //pionowo
            x = x1;
            if(y1 < y2){
                for(y = y1; y<y2; y+=0.05){
                    System.out.print(x + "\t" + y + "\n");
                }
            }else{
                for(y = y2; y<y1; y+=0.05){
                    System.out.print(x + "\t" + y + "\n");
                }
            }
            
        }else if(y1 == y2){ //poziomo
            y = y1;
            if(x1 < x2){
                for(x = x1; x<x2; x+=0.05){
                    System.out.print(x + "\t" + y + "\n");
                }
            }else{
                for(x = x2; x<x1; x+=0.05){
                    System.out.print(x + "\t" + y + "\n");
                }
            }
            
        }
        else { //skos
            //y = a*x + b

            // y1= a*x1+b
            //-y2=-a*x2-b
            // ---------
            // y1-y2 = a*(x1-x2)
        
            a = (y1-y2)/(x1-x2);  
            b = y1 - a*x1;
            if(x1 < x2){
                for(x = x1; x< x2; x+=0.05){
                    y = a*x+b;
                    System.out.print(x + "\t" + y + "\n");
                }
            }else{
                for(x = x2; x< x1; x+=0.05){
                    y = a*x+b;
                    System.out.print(x + "\t" + y + "\n");
                }
            }
        }
       
      System.out.println("");
    }
    
    static void draw_circle(double a, double b, double r){
      double x1 = a-r;
      double x2 = a+r;
      double x, y1, y2;
  
      for(x = x1; x < x2; x+=0.01){
        // (x-a)^2 + (y-b)^2 = r^2
        // (y-b)^2 = r^2 - (x-a)^2
        // y = sqrt(r^2 - (x-a)^2) + b || y = -sqrt(r^2 - (x-a)^2) + b
  
        y1 = Math.sqrt(Math.pow(r,2) - Math.pow(x-a,2)) + b; 
        y2 = -1 * Math.sqrt(Math.pow(r,2) - Math.pow(x-a,2)) + b; 
        if(y1==y2){
            System.out.print(x + "\t" + y1 + "\n");  
        }else{
            System.out.print(x + "\t" + y1 + "\n");
            System.out.print(x + "\t" + y2 + "\n");
        }
  
      }
  
      System.out.println("");
    }
    
    public static void main(String[] args) {
      
  
      draw_line(28,65,28,84);
      draw_line(28,84,34,83);
      draw_line(34,83,38,79);
      draw_line(38,79,37,76);
      draw_line(37,76,34,73);
      draw_line(28,72,37,76);
      draw_line(58,25,53,23);
      draw_line(53,23,47,23);
      draw_line(47,23,44,25);
      draw_line(74,65,63,65);
      draw_line(63,65,73,74);
      draw_line(73,74,73,79);
      draw_line(73,79,70,82);
      draw_line(70,82,66,82);
      draw_line(66,82,63,80);
      draw_circle(50,74,9);
      draw_circle(50,29,14);
      draw_circle(55,34,2);
      draw_circle(46,34,2);
    }
  }