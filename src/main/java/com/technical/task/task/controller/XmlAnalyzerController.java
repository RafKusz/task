package com.technical.task.task.controller;

import com.technical.task.task.model.InputUrl;
import com.technical.task.task.model.Post;
import com.technical.task.task.service.XmlAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

@RestController
@RequestMapping("/analyze")
public class XmlAnalyzerController {

    private XmlAnalyzerService xmlAnalyzerService;

    @Autowired
    public XmlAnalyzerController(XmlAnalyzerService xmlAnalyzerService) {
        this.xmlAnalyzerService = xmlAnalyzerService;
    }

    @PostMapping
    Post showXmlAnalyze(@RequestBody InputUrl inputUrl) throws IOException, XMLStreamException {
        return xmlAnalyzerService.analyzeXmlFile(inputUrl);
    }
}