import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;
import javafx.scene.control.ProgressIndicator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author 陈刚
 * @ClassName TmtClientUtil
 * @Description //TODO
 * @create 2020-02-03 16:26
 */
public class TmtClientUtil {

    public static final String secretId = "AKIDxpW2Pi9RaaS8cJdGLFUbWFG8qFSVmFpC";

    public static final String secretKey = "OsIahjaFm085nQwqbP6f4IPOCrKf0ELM";

    public static final String region = "ap-guangzhou";

    public static final long default_sleep=2000;

    public static final String suffix=".trans.txt";

    public static double processIn = 0;
    @Test
    public void test() throws Exception {
        String path = "C:\\Users\\陈刚\\Desktop\\13 (1).txt";
        String encoding = "utf-8";
        translateTxt(path,encoding);
    }

    public static void translateTxt(String path, String encoding) throws Exception {
        TmtClientUtil.processIn = 0;
        Credential cred = new Credential(secretId, secretKey);
        TmtClient tmtClient = new TmtClient(cred,region);

        List<String> list = FileUtils.readLines(new File(path), encoding);
        List<Integer> errorInt = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <list.size(); i++) {
            if (i%5==4){
                Thread.sleep(default_sleep);
            }
            String sourceText = list.get(i);
            if ("".equals(sourceText)) continue;
            try {
                final TextTranslateRequest textTranslateRequest = new TextTranslateRequest();
                textTranslateRequest.setSource("en");
                textTranslateRequest.setSourceText(sourceText);
                textTranslateRequest.setTarget("zh");
                textTranslateRequest.setProjectId(0);
                TextTranslateResponse textTranslateResponse = tmtClient.TextTranslate(textTranslateRequest);
                stringBuilder.append(sourceText);
                stringBuilder.append("\n");
                stringBuilder.append(textTranslateResponse.getTargetText());
                stringBuilder.append("\n");
            } catch (TencentCloudSDKException e) {
                errorInt.add(i);
            }

            processIn = (i + 1) / (0.0 + list.size());
        }
        processIn = 1;

        FileUtils.write(new File(path+suffix),stringBuilder.toString(),encoding);
    }



}
