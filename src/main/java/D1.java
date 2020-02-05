import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈刚
 * @ClassName D1
 * @Description //TODO
 * @create 2020-02-01 16:27
 */
public class D1 {

    public static void main(String[] args) {
//        C:\Users\陈刚\Desktop\2
//        splitFile("C:\\Users\\陈刚\\Desktop\\2\\1.pdf", 1, 1);
        splitAll("C:\\Users\\陈刚\\Desktop\\2\\US9155718.pdf", 1);

    }


    public static  void splitAll(String pdfFile,int speed){
        try {
            PdfReader reader = new PdfReader(pdfFile);
            int pageNum = reader.getNumberOfPages();
            for (int i =0;i<Math.floorDiv(pageNum,speed)+(Math.floorMod(pageNum,speed)>0?1:0);i++){
                splitFile(pdfFile, i*speed + 1, Math.min(i*speed+speed,pageNum));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String splitFile(String pdfFile,Integer from,Integer end){

        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(pdfFile);
            int n = reader.getNumberOfPages();
            if(end==0){
                end = n;
            }
            List<String> savepaths = new ArrayList<String>();
            int a = pdfFile.lastIndexOf(".pdf");
            String staticpath = pdfFile.substring(0, a);
            String savepath = staticpath+ "_from_"+from+"_to_"+end+"_.pdf";
            savepaths.add(savepath);
            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(savepaths.get(0)));
            document.open();
            for(int j=from; j<=end; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();
            return new File(savepath).getName();
        } catch (IOException e) {
            e.printStackTrace();;
//            logger.error("split pdf file error:{}",e.getMessage(),e);
            return null;
        } catch(DocumentException e) {
            e.printStackTrace();
//            logger.error("split pdf file error:{}",e.getMessage(),e);
            return null;
        }
    }

}
