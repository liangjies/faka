package cn.liangjies.faka.controller;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/product/order")
public class ShowQRController {
    /**
     * CODE_WIDTH：二维码宽度，单位像素
     * CODE_HEIGHT：二维码高度，单位像素
     * FRONT_COLOR：二维码前景色，0x000000 表示黑色
     * BACKGROUND_COLOR：二维码背景色，0xFFFFFF 表示白色
     * 演示用 16 进制表示，和前端页面 CSS 的取色是一样的，注意前后景颜色应该对比明显，如常见的黑白
     */
    private static final int CODE_WIDTH = 400;
    private static final int CODE_HEIGHT = 400;
    private static final int FRONT_COLOR = 0x000000;
    private static final int BACKGROUND_COLOR = 0xFFFFFF;

    /**
     * 生成二维码 并 输出到输出流————通常用于输出到网页上进行显示
     * 输出到网页与输出到磁盘上的文件中，区别在于最后一句 ImageIO.write
     * write(RenderedImage im,String formatName,File output)：写到文件中
     * write(RenderedImage im,String formatName,OutputStream output)：输出到输出流中
     *
     * @param codeContent  ：二维码内容
     * @param outputStream ：输出流，比如 HttpServletResponse 的 getOutputStream
     */

    public static void createCodeToOutputStream(String codeContent, OutputStream outputStream) {
        try {
            /** 参数检验*/
            if (codeContent == null || "".equals(codeContent.trim())) {
                System.out.println("二维码内容为空，不进行操作...");
                return;
            }
            codeContent = codeContent.trim();

            /**com.google.zxing.EncodeHintType：编码提示类型,枚举类型
             * EncodeHintType.CHARACTER_SET：设置字符编码类型
             * EncodeHintType.ERROR_CORRECTION：设置误差校正
             *      ErrorCorrectionLevel：误差校正等级，L = ~7% correction、M = ~15% correction、Q = ~25% correction、H = ~30% correction
             *      不设置时，默认为 L 等级，等级不一样，生成的图案不同，但扫描的结果是一样的
             * EncodeHintType.MARGIN：设置二维码边距，单位像素，值越小，二维码距离四周越近
             * */
            Map<EncodeHintType, Object> hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.MARGIN, 1);

            /**
             * MultiFormatWriter:多格式写入，这是一个工厂类，里面重载了两个 encode 方法，用于写入条形码或二维码
             *      encode(String contents,BarcodeFormat format,int width, int height,Map<EncodeHintType,?> hints)
             *      contents:条形码/二维码内容
             *      format：编码类型，如 条形码，二维码 等
             *      width：码的宽度
             *      height：码的高度
             *      hints：码内容的编码类型
             * BarcodeFormat：枚举该程序包已知的条形码格式，即创建何种码，如 1 维的条形码，2 维的二维码 等
             * BitMatrix：位(比特)矩阵或叫2D矩阵，也就是需要的二维码
             */
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(codeContent, BarcodeFormat.QR_CODE, CODE_WIDTH, CODE_HEIGHT, hints);

            /**java.awt.image.BufferedImage：具有图像数据的可访问缓冲图像，实现了 RenderedImage 接口
             * BitMatrix 的 get(int x, int y) 获取比特矩阵内容，指定位置有值，则返回true，将其设置为前景色，否则设置为背景色
             * BufferedImage 的 setRGB(int x, int y, int rgb) 方法设置图像像素
             *      x：像素位置的横坐标，即列
             *      y：像素位置的纵坐标，即行
             *      rgb：像素的值，采用 16 进制,如 0xFFFFFF 白色
             */
            BufferedImage bufferedImage = new BufferedImage(CODE_WIDTH, CODE_HEIGHT, BufferedImage.TYPE_INT_BGR);
            for (int x = 0; x < CODE_WIDTH; x++) {
                for (int y = 0; y < CODE_HEIGHT; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? FRONT_COLOR : BACKGROUND_COLOR);
                }
            }

            /**
             * 区别就是以一句，输出到输出流中，如果第三个参数是 File，则输出到文件中
             */
            ImageIO.write(bufferedImage, "png", outputStream);

            System.out.println("二维码图片生成到输出流成功...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @GetMapping("/showqr/")
    public void getQRCode(String url, HttpServletResponse response) {
        System.out.println("codeContent=" + url);
        try {
            /**
             * 调用工具类生成二维码并输出到输出流中
             */
            createCodeToOutputStream(url, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
