package com.kk.poi;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 参考文档：http://deepoove.com/poi-tl/apache-poi-guide.html#_%E6%96%87%E6%A1%A3xwpfdocument
 * http://deepoove.com/poi-tl/#_license
 *
 * @author KPQ
 * @date 2022/2/11
 */
public class POITest {

    private static final String dir = System.getProperty("user.dir") + File.separator + "src" + File.separator + "report" + File.separator;

    public static void main(String[] args) {
        final String simpleDoc = dir + "simple.docx";
        //XWPFDocument 对 .docx 文档操作的高级封装API
        //创建新文档
        XWPFDocument doc = new XWPFDocument();
        //创建新段落
        XWPFParagraph paragraph = doc.createParagraph();
        // 设置段落的样式：对齐方式
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        //段落边框：上下左右
        paragraph.setBorderBottom(Borders.DOUBLE);
        paragraph.setBorderTop(Borders.NONE);
        paragraph.setBorderRight(Borders.NONE);
        paragraph.setBorderLeft(Borders.NONE);
        paragraph.setBorderBetween(Borders.NONE);
        // 为段落追加文本
        XWPFRun run = paragraph.createRun();
        //段落文本的样式设置
        // 颜色
        run.setColor("00ff00");
        // 斜体
        run.setItalic(true);
        // 粗体
        run.setBold(true);
        // 字体
        run.setFontFamily("Courier");
        // 下划线
        run.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
        run.setText("段落文本");

        //生成文档
        try (FileOutputStream out = new FileOutputStream(simpleDoc)) {
            //加入图片
            InputStream stream = new FileInputStream(dir + "test.jpg");
            run.addPicture(stream, XWPFDocument.PICTURE_TYPE_PNG, "Generated", Units.toEMU(256), Units.toEMU(256));
            doc.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
