package com.technical.task.task.service;

import com.technical.task.task.model.InputUrl;
import com.technical.task.task.model.Post;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public interface XmlAnalyzerService {

    Post analyzeXmlFile(InputUrl inputUrl) throws IOException, XMLStreamException;

}