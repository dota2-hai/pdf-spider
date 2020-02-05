import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.tmt.v20180321.models.ImageTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.ImageTranslateResponse;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;
import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author 陈刚
 * @ClassName Test
 * @Description //TODO
 * @create 2020-02-01 22:14
 */
public class Test {

    static String imageToBase64(String path) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(path);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    public static void main(String[] args) throws TencentCloudSDKException {
        Credential cred = new Credential("AKIDxpW2Pi9RaaS8cJdGLFUbWFG8qFSVmFpC", "OsIahjaFm085nQwqbP6f4IPOCrKf0ELM");
        TmtClient tmtClient = new TmtClient(cred,"ap-guangzhou");
        ImageTranslateRequest request = new ImageTranslateRequest();
        request.setData(imageToBase64("C:\\Users\\陈刚\\Desktop\\1\\1.png"));
        request.setSource("en");
        request.setTarget("zh");
        request.setScene("doc");
        request.setProjectId(0);
        request.setSessionUuid(UUID.randomUUID().toString());
        ImageTranslateResponse response = tmtClient.ImageTranslate(request);
        Stream.of(response.getImageRecord().getValue()).forEach(itemValue -> System.out.println(itemValue.getSourceText()+"\n"+itemValue.getTargetText()));
        System.out.println();




//        TextTranslateRequest textTranslateRequest = new TextTranslateRequest();
//        textTranslateRequest.setSource("en");
//        textTranslateRequest.setSourceText("apple");
//        textTranslateRequest.setTarget("zh");
//        textTranslateRequest.setProjectId(0);
//        TextTranslateResponse textTranslateResponse = tmtClient.TextTranslate(textTranslateRequest);
//        System.out.println(textTranslateResponse.getTargetText());
    }



    @org.junit.Test
    public void test1() throws TencentCloudSDKException, IOException {
        Credential cred = new Credential("AKIDxpW2Pi9RaaS8cJdGLFUbWFG8qFSVmFpC", "OsIahjaFm085nQwqbP6f4IPOCrKf0ELM");
        TmtClient tmtClient = new TmtClient(cred,"ap-guangzhou");




       final  TextTranslateRequest textTranslateRequest = new TextTranslateRequest();
        textTranslateRequest.setSource("en");
        textTranslateRequest.setSourceText("apple");
        textTranslateRequest.setTarget("zh");
        textTranslateRequest.setProjectId(0);
        TextTranslateResponse textTranslateResponse = tmtClient.TextTranslate(textTranslateRequest);
        List<String> list = FileUtils.readLines(new File("C:\\Users\\陈刚\\Desktop\\11.txt"), "utf-8");
        list.forEach(yu-> {
            textTranslateRequest.setSourceText(yu);
            try {
                TextTranslateResponse textTranslateResponse1 = tmtClient.TextTranslate(textTranslateRequest);
                System.out.println(yu);
                System.out.println(textTranslateResponse1.getTargetText());
            } catch (TencentCloudSDKException e) {
                System.out.println(yu);
                System.out.println("error");
//                e.printStackTrace();
            }
        });

//        System.out.println(textTranslateResponse.getTargetText());
    }



    @org.junit.Test
    public void test2() throws TencentCloudSDKException, IOException, InterruptedException {
        Credential cred = new Credential("AKIDxpW2Pi9RaaS8cJdGLFUbWFG8qFSVmFpC", "OsIahjaFm085nQwqbP6f4IPOCrKf0ELM");
        TmtClient tmtClient = new TmtClient(cred,"ap-guangzhou");




//        final  TextTranslateRequest textTranslateRequest = new TextTranslateRequest();
//        textTranslateRequest.setSource("en");
//        textTranslateRequest.setSourceText("apple");
//        textTranslateRequest.setTarget("zh");
//        textTranslateRequest.setProjectId(0);
//        TextTranslateResponse textTranslateResponse = tmtClient.TextTranslate(textTranslateRequest);
        List<String> list = FileUtils.readLines(new File("C:\\Users\\陈刚\\Desktop\\11.txt"), "utf-8");
//        list.forEach(a-> System.out.println(a));
        List<Integer> errorInt = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <list.size(); i++) {
            if (i%5==4){
                Thread.sleep(2000);
            }
//            System.out.println(i+"---------------------");
            String yu = list.get(i);
            if ("".equals(yu)) continue;
//            textTranslateRequest.setSourceText(yu);
            try {
                final  TextTranslateRequest textTranslateRequest = new TextTranslateRequest();
                textTranslateRequest.setSource("en");
        textTranslateRequest.setSourceText(yu);
                textTranslateRequest.setTarget("zh");
                textTranslateRequest.setProjectId(0);
                TextTranslateResponse textTranslateResponse1 = tmtClient.TextTranslate(textTranslateRequest);
//                System.out.println(yu);
//                System.out.println(textTranslateResponse1.getTargetText());
                stringBuilder.append(yu);
                stringBuilder.append("\n");
                stringBuilder.append(textTranslateResponse1.getTargetText());
                stringBuilder.append("\n");

                Thread.sleep(1);
            } catch (TencentCloudSDKException | InterruptedException e) {
//                System.out.println(yu);
//                System.out.println("error-----"+i+"--------");
                System.out.println("-----------------");
                System.out.println(i);
                System.out.println(yu);
                errorInt.add(i);
//                e.printStackTrace();
            }
        }

        System.out.println(stringBuilder);
        FileUtils.write(new File("C:\\Users\\陈刚\\Desktop\\12.txt"),stringBuilder.toString(),"utf-8");
        System.out.println(errorInt.stream().map(String::valueOf).reduce("error:",(a,b)->a+","+b));
//        System.out.println(textTranslateResponse.getTargetText());
    }




    @org.junit.Test
    public void test3() throws TencentCloudSDKException, IOException, InterruptedException {
        Credential cred = new Credential("AKIDxpW2Pi9RaaS8cJdGLFUbWFG8qFSVmFpC", "OsIahjaFm085nQwqbP6f4IPOCrKf0ELM");
        TmtClient tmtClient = new TmtClient(cred,"ap-guangzhou");




        final  TextTranslateRequest textTranslateRequest = new TextTranslateRequest();
        textTranslateRequest.setSource("en");
        textTranslateRequest.setSourceText("FIELD OF THE INVENTION");
        textTranslateRequest.setTarget("zh");
        textTranslateRequest.setProjectId(0);
        TextTranslateResponse textTranslateResponse = tmtClient.TextTranslate(textTranslateRequest);
        System.out.println(textTranslateResponse.getTargetText());
    }


    @org.junit.Test
    public void test4() throws TencentCloudSDKException, IOException, InterruptedException {
        Credential cred = new Credential("AKIDxpW2Pi9RaaS8cJdGLFUbWFG8qFSVmFpC", "OsIahjaFm085nQwqbP6f4IPOCrKf0ELM");
        TmtClient tmtClient = new TmtClient(cred,"ap-guangzhou");




//        final  TextTranslateRequest textTranslateRequest = new TextTranslateRequest();
//        textTranslateRequest.setSource("en");
//        textTranslateRequest.setSourceText("apple");
//        textTranslateRequest.setTarget("zh");
//        textTranslateRequest.setProjectId(0);
//        TextTranslateResponse textTranslateResponse = tmtClient.TextTranslate(textTranslateRequest);
        List<String> list = FileUtils.readLines(new File("C:\\Users\\陈刚\\Desktop\\13.txt"), "utf-8");
//        list.forEach(a-> System.out.println(a));
        List<Integer> errorInt = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <list.size(); i++) {
            if (i%5==4){
                Thread.sleep(2000);
            }
//            System.out.println(i+"---------------------");
            String yu = list.get(i);
            if ("".equals(yu)) continue;
//            textTranslateRequest.setSourceText(yu);
            try {
                final  TextTranslateRequest textTranslateRequest = new TextTranslateRequest();
                textTranslateRequest.setSource("en");
                textTranslateRequest.setSourceText(yu);
                textTranslateRequest.setTarget("zh");
                textTranslateRequest.setProjectId(0);
                TextTranslateResponse textTranslateResponse1 = tmtClient.TextTranslate(textTranslateRequest);
//                System.out.println(yu);
//                System.out.println(textTranslateResponse1.getTargetText());
                stringBuilder.append(yu);
                stringBuilder.append("\n");
                stringBuilder.append(textTranslateResponse1.getTargetText());
                stringBuilder.append("\n");

                Thread.sleep(1);
            } catch (TencentCloudSDKException | InterruptedException e) {
//                System.out.println(yu);
//                System.out.println("error-----"+i+"--------");
                System.out.println("-----------------");
                System.out.println(i);
                System.out.println(yu);
                errorInt.add(i);
//                e.printStackTrace();
            }
        }

        System.out.println(stringBuilder);
        FileUtils.write(new File("C:\\Users\\陈刚\\Desktop\\14.txt"),stringBuilder.toString(),"utf-8");
        System.out.println(errorInt.stream().map(String::valueOf).reduce("error:",(a,b)->a+","+b));
//        System.out.println(textTranslateResponse.getTargetText());
    }


}
