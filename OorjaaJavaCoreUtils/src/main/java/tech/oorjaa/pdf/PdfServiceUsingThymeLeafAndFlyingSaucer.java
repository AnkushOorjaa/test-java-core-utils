package tech.oorjaa.pdf;

import org.xhtmlrenderer.pdf.ITextRenderer;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PdfServiceUsingThymeLeafAndFlyingSaucer implements PdfService {


    public byte[] convertHtmlToPdf(String html) throws Exception {

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream, true);
            return outputStream.toByteArray();
        }
    }

    @Override
    public void savePdfToLocal(byte[] pdfBytes, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(pdfBytes);
            System.out.println("PDF saved to: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadPdfToS3Bucket(byte[] pdfBytes) {
        String accessKey = "YOUR_ACCESS_KEY";
        String secretKey = "YOUR_SECRET_KEY";
        String region = "your-region";  // e.g., "us-east-1"
        String bucketName = "your-bucket-name";

        //if (pdfBytes != null) {
            S3Client s3Client = S3Client.builder()
                    .region(Region.of(region))
                    .build();
        //}
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfBytes);

            // Specify the S3 key (file name) under which you want to save the PDF
            String key = "example.pdf";

            // Upload the PDF to S3
            PutObjectResponse response = s3Client.putObject(PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build(), RequestBody.fromInputStream(inputStream, pdfBytes.length));

            System.out.println("File uploaded successfully. ETag: " + response.eTag());

        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        } finally {
            s3Client.close();
        }

    }
}
