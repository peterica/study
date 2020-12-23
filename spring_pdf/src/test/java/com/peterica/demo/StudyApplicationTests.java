package com.peterica.demo;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.imgscalr.Scalr;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class StudyApplicationTests {

    String tempPath ="/Users/peterseo/study/temp/";

    @Test
    void contextLoads() {
        try{
            // Resource에 있는 파일 가져오기
            ClassPathResource classPathResource = new ClassPathResource("pdf_test.pdf");
            InputStream inputStream = new BufferedInputStream(classPathResource.getInputStream());

            // 임시 저장위치
//            File file = new File(tempPath+"pdf_test.pdf");
            File file = File.createTempFile("pdf_test",".pdf");
            try (FileOutputStream outputStream = new FileOutputStream(file)){
                int read;
                byte[] bytes = new byte[1024];
                while ((read= inputStream.read(bytes)) != -1){
                    outputStream.write(bytes,0,read);
                }
            }

            File returnFile = conversionPdf2Img(file);
            System.out.println(returnFile.isFile());
            //임시파일 삭
            file.deleteOnExit();

            //섬내일
            File tnFile = thumbnailS3Upload(returnFile);
            System.out.println(tnFile.isFile());
            returnFile.deleteOnExit();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private File conversionPdf2Img(File pdfFile) {

        File pdfImgFile = null;
        if(!pdfFile.exists())
            return pdfImgFile;

        try {
            //Document 생성
            PDDocument pdfDoc = PDDocument.load(pdfFile);
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);

            String resultImgPath = "/Users/peterseo/study/temp"; //이미지가 저장될 경로
            Files.createDirectories(Paths.get(resultImgPath)); //PDF 2 Img에서는 경로가 없는 경우 이미지 파일이 생성이 안되기 때문에 디렉토리를 만들어준다.

            // 첫페이지, DPI 72, 컬러이미지 설정
            BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 72, ImageType.RGB);
            String imgFileName = tempPath + UUID.randomUUID()+"_pdf.png";
            // 이미지로 만든다.
            ImageIOUtil.writeImage(bim, imgFileName , 72);

            //저장 완료된 이미지를 File객체화
            pdfImgFile = new File(imgFileName);

            pdfDoc.close(); //모두 사용한 PDF 문서는 닫는다.

        }catch (IOException e) {
//            log.error("PDF이미지 컨버터 IOExcepion:",e);
        }catch (Exception e) {
//            log.error("PDF이미지 컨버터 Excepion:",e);
        }
        return pdfImgFile;
    }

    public File thumbnailS3Upload(File file) throws Exception {

        // 썸네일 크기는 100X100
        BufferedImage sourceImg = ImageIO.read(file);

        int ow = sourceImg.getWidth();
        int oh = sourceImg.getHeight();

        int nw = ow;
        int nh = (ow * 100) / 100;

        if (nh > oh) {
            nw = (oh * 100) / 100;
            nh = oh;
        }

        // 계산된 크기로 원본이미지를 가운데에서 crop 합니다.
        BufferedImage cropImg = Scalr.crop(sourceImg, (ow - nw) / 2, (oh - nh) / 2, nw, nh);
        BufferedImage destImg = Scalr.resize(cropImg, 100, 100);

        String thumbName = tempPath + "THUMB_" + UUID.randomUUID() + "-" + file.getName();
        File thumbFile = new File(thumbName);

        ImageIO.write(destImg, FilenameUtils.getExtension(file.getName()), thumbFile);
        return thumbFile;
    }

    @Test
    public void tempFileTest(){
        try{
            // temp_ 의 prefix 와 .dat의 subfix의 임시파일을 c:\\example\\file 의 경로에 저장한다.
            File tempfile = File.createTempFile("temp_", ".dat");
            // 생성된 파일의 실제 경로 표시
            System.out.println(tempfile.getAbsolutePath());

            // Delete temp flie
            tempfile.deleteOnExit();
            // 생성된 파일은 종료와 함께 자동으로 삭제 처리 될 것 이다.
        } catch (IOException e) {
            System.out.println(e);
        }

    }


}
