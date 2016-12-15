package com.tengda.agency;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by DongHao on 2016/12/15.
 * Description:
 */

public class WordUtils {
    /**
     * 读取Word文件
     */
    public static String readDoc(InputStream doc) {
        FileInputStream in = null;
        WordExtractor extractor = null;
        String text = null;
        try {
            // 创建输入流读取DOC文件
//            in = new FileInputStream(new File(doc));
            // 创建WordExtractor
            extractor = new WordExtractor(doc);
            // 对DOC文件进行提取
            //text = extractor.extractText(in);
            text = extractor.getTextFromPieces();
            // text = extractor.getText();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (extractor != null) try {extractor.close();} catch (Exception e) {e.printStackTrace();}
            if (doc != null) try {doc.close();} catch (Exception e) {e.printStackTrace();}
        }
        return text;
    }

    /**
     * 将内容写成一个doc文件，保存在磁盘上。
     */
    public static boolean writeDoc(String path, String content) {
        ByteArrayInputStream bais = null;
        FileOutputStream ostream = null;
        boolean w = false;
        try {
            content+="\r\n";
            //byte b[] = content.getBytes("ISO-8859-1");
            //byte b[] = content.getBytes("gbk");
            //byte b[] = content.getBytes("GB2312");
            //byte b[] = content.getBytes("UTF-8");
            byte b[] = content.getBytes();

            bais = new ByteArrayInputStream(b);
            POIFSFileSystem fs = new POIFSFileSystem();
            fs.getRoot().createDocument("WordDocument", bais);

            ostream = new FileOutputStream(path);

            fs.writeFilesystem(ostream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bais!=null) try {bais.close(); } catch(Exception e) {e.printStackTrace();}
            if(ostream!=null) try {ostream.close(); } catch(Exception e) {e.printStackTrace();}
        }

        return w;
    }
}
