package multbarcode;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.opencv.core.*;
import static org.opencv.core.Core.BORDER_DEFAULT;
import static org.opencv.core.Core.addWeighted;
import static org.opencv.core.Core.convertScaleAbs;
import static org.opencv.core.Core.subtract;
import static org.opencv.core.CvType.CV_16S;
import static org.opencv.core.CvType.CV_32F;
import static org.opencv.core.CvType.CV_64F;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
//import static org.opencv.imgcodecs.Imgcodecs.imread;
//import static org.opencv.imgcodecs.Imgcodecs.imread;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Suzana
 */
public class Multbarcode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          int scale = 1;
          int delta = 0;
          int ddepth = CV_64F;
        //magic line pls have with opencv will find out why later
        int kernelSize = 9;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // TODO code application logic here
        
        //InputStream barCodeInputStream = new FileInputStream("C:\\Users\\Suzana\\barcodetesting.PNG");
        //BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);
        Mat img = Imgcodecs.imread("C:\\Users\\Suzana\\cardinalbar.PNG",1);
        //Mat img = Imgcodecs.imread("C:\\Users\\Suzana\\idk.jpg",1);
        //Mat img = Imgcodecs.imread("C:\\Users\\SuzyQ\\Documents\\NetBeansProjects\\barcodeTest\\barcodenewtest.PNG",1);
        //Mat img = Imgcodecs.imread("C:\\Users\\SuzyQ\\AppData\\Roaming\\SPB_Data\\chbarcode.png",1);
        Mat gray = new Mat();
        Imgproc.cvtColor(img, gray, Imgproc.COLOR_BGR2GRAY);
        Imgcodecs.imwrite("1gray.PNG", gray);
        Mat destination = new Mat();
        
        Mat gradX = new Mat();
        
        //Imgproc.Sobel(gray, gradX, ddepth, 1, 0, -1, scale, delta, BORDER_DEFAULT);
        //Imgproc.Sobel(gray, gradX, ddepth, 1, 0);
        Imgproc.Sobel(gray, gradX, CV_64F, 1, 0);
        //convertScaleAbs( gradX, absGradX );
        Imgcodecs.imwrite("2sobelX.PNG", gradX);
        Mat gradY = new Mat();
        
        //Imgproc.Sobel( gray, gradY,ddepth, 0, 1);
        Imgproc.Sobel(gray, gradY, CV_64F, 0, 1);
        //convertScaleAbs( gradY, absGradY );
        Imgcodecs.imwrite("3sobelY.PNG", gradY);

       Mat grad = new Mat();

       Core.subtract(gradX, gradY, grad);
       Imgcodecs.imwrite("4subtract.PNG", grad);
       Mat absGrad = new Mat();

       convertScaleAbs( grad, absGrad );
        
        Mat thresh = new Mat();
        Mat morph = new Mat();

        Imgproc.GaussianBlur(absGrad, destination, new Size(9,9), 0);
        Imgcodecs.imwrite("5blur.PNG", destination);
        Imgproc.threshold(destination,thresh,100,255,Imgproc.THRESH_BINARY);
        Imgcodecs.imwrite("6threshold.PNG", thresh);
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(50,8));
        Imgproc.morphologyEx(thresh, morph, Imgproc.MORPH_CLOSE, element);
        Imgcodecs.imwrite("7morphology.PNG", morph);
        Mat erode = new Mat();
        Imgproc.erode(morph, erode, element, new Point(-1,-1),4);
        Imgcodecs.imwrite("8erode.PNG", erode);
        Mat dilate = new Mat();
        Imgproc.dilate(erode, dilate, element, new Point(-1,-1),4);
        Imgcodecs.imwrite("9dilate.PNG", dilate);
        //Imgproc.findContours(img, contours, gradY, scale, ddepth);
        //https://www.youtube.com/watch?v=UyLqoUNsHa0
        List<MatOfPoint> contours = new ArrayList<>();    
    Imgproc.findContours(dilate, contours, new Mat(), Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);

    //For each contour found 
        for(int i=0; i< contours.size();i++){
        //System.out.println(Imgproc.contourArea(contours.get(i)));
        if (Imgproc.contourArea(contours.get(i)) > 150 ){
            Rect rect = Imgproc.boundingRect(contours.get(i));
            //System.out.println(rect.height);
            if (rect.height > 50){
            //System.out.println(rect.x +","+rect.y+","+rect.height+","+rect.width);
            Imgproc.rectangle(img, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,0,255),2);
            }
        }
    }
        Imgcodecs.imwrite("camera9.PNG", img);
    }
    //Imgcodecs.imwrite("9final.PNG", img);
}
