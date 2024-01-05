package tech.oorjaa;

import tech.oorjaa.datapopulation.ThymeLeafTransformation;
import tech.oorjaa.dto.Order;
import tech.oorjaa.dto.OrderItem;
import tech.oorjaa.pdf.PdfServiceUsingThymeLeafAndFlyingSaucer;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        ThymeLeafTransformation dataPopulater = new ThymeLeafTransformation();

        PdfServiceUsingThymeLeafAndFlyingSaucer pdfService = new PdfServiceUsingThymeLeafAndFlyingSaucer();

        String htmlFilePath = "D:\\OorjaaDevelopment\\OorjaaJavaCoreUtils\\src\\main\\resources\\templates\\order-template.html";


        Map<String, Object> data = createTestData();

        String html = dataPopulater.populateTemplateWithData(htmlFilePath,data);

        byte[] pdfBytes = pdfService.convertHtmlToPdf(html);

        String localPath = "D:\\SimpleSolution\\orders5.pdf";

        pdfService.savePdfToLocal(pdfBytes, localPath);


    }

    private static Map<String, Object> createTestData() {
        Map<String, Object> data = new HashMap<>();

        Order order = new Order();
        order.setOrderNo("ABC-12345");
        order.setDate(new Date());
        order.setOrderDate(new Date());
        order.setRequestDate(new Date());
        data.put("order", order);

        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setDescription("Test Order Item 1");
        orderItem1.setQuantity(1);
        orderItem1.setUnitPrice(100.0);
        orderItem1.setTotal(100.0);
        orderItems.add(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setDescription("Test Order 2");
        orderItem2.setQuantity(5);
        orderItem2.setUnitPrice(50.0);
        orderItem2.setTotal(250.0);
        orderItems.add(orderItem2);

        OrderItem orderItem3 = new OrderItem();
        orderItem3.setDescription("Test Order 3");
        orderItem3.setQuantity(2);
        orderItem3.setUnitPrice(200.0);
        orderItem3.setTotal(400.0);
        orderItems.add(orderItem3);

        data.put("orderItems", orderItems);

        return data;
    }
}