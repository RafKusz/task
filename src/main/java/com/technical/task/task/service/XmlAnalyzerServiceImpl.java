package com.technical.task.task.service;

import com.technical.task.task.model.InputUrl;
import com.technical.task.task.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Service
@Slf4j
public class XmlAnalyzerServiceImpl implements XmlAnalyzerService {

    @Override
    public Post analyzeXmlFile(InputUrl inputUrl) throws IOException {
        Post post = new Post();
        InputStream stream = inputUrl.getUrl().openStream();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(stream);
            int count = 0;
            int countAccept = 0;
            String lastPost = "";
            long score = 0;
            while (xmlStreamReader.hasNext()) {
                if (xmlStreamReader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    if (xmlStreamReader.getLocalName().equals("row")) {
                        count++;
                        countAccept += xmlStreamReader.getAttributeValue("", "AcceptedAnswerId") == null ? 0 : 1;
                        if (count == 1) {
                            post.setFirstPost(LocalDateTime.parse(xmlStreamReader.getAttributeValue("", "CreationDate")));
                        }
                        lastPost = xmlStreamReader.getAttributeValue("", "CreationDate");
                        score += Long.parseLong(xmlStreamReader.getAttributeValue("", "Score"));
                    }
                }
                xmlStreamReader.next();
            }
            post.setAnalyseDate(LocalDateTime.now());
            post.setLastPost(LocalDateTime.parse(lastPost));
            post.setTotalPosts(count);
            post.setTotalAcceptedPosts(countAccept);
            post.setAvgScore((float) score / (float) count);
        } catch (XMLStreamException e) {
            log.error("Unable to parse the xml");
        }
        return post;
    }
}