package tech.oorjaa.utils.dynamicdatacreation;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ThymeleafTemplateBuilder {

    public String populateTemplateWithData(String htmlTemplatePath, Map<String, Object> data) throws IOException {

        String htmlTemplate = new String(Files.readAllBytes(Paths.get(htmlTemplatePath)));

        // Initialize Thymeleaf template engine
        TemplateEngine templateEngine = new TemplateEngine();

        // Thymeleaf context
        Context context = new Context();
        context.setVariables( data);

        // Process the template
        /*StringWriter stringWriter = new StringWriter();
        templateEngine.process(htmlTemplate, context, stringWriter);*/

        return templateEngine.process(htmlTemplate, context);
    }

    public String populateResourceTemplateWithData(String resourceTemplateFileName, Map<String, Object> data) {
        TemplateEngine templateEngine = createTemplateEngine();
        Context context = new Context();
        context.setVariables(data);
        return templateEngine.process(resourceTemplateFileName, context);
    }

    private TemplateEngine createTemplateEngine() {
        ClassLoaderTemplateResolver pdfTemplateResolver = new ClassLoaderTemplateResolver();
        pdfTemplateResolver.setPrefix("templates/");
        pdfTemplateResolver.setSuffix(".html");
        pdfTemplateResolver.setTemplateMode("HTML5");
        pdfTemplateResolver.setCharacterEncoding("UTF-8");
        pdfTemplateResolver.setOrder(1);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(pdfTemplateResolver);
        return templateEngine;
    }


}
