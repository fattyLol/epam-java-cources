package com.epam.university.java.core.task034;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Task034Impl implements Task034 {

    @Override
    public Person readWithSaxParser(DefaultHandler handler, String filepath) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        Person person = new PersonImpl();
        SaxHandlerImpl saxHandler = (SaxHandlerImpl) handler;
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(getClass().getResource(filepath).toURI()), saxHandler);
            person = saxHandler.getPerson();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return person;
    }

    @Override
    public Person readWithJaxbParser(String filepath) {
        if (filepath == null) {
            throw new IllegalArgumentException();
        }
        Person person = null;
        try {
            File file = new File(getClass().getResource(filepath).toURI());
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonImpl.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            person = (PersonImpl) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Person readWithStaxParser(XMLStreamReader streamReader) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        PersonImpl person = new PersonImpl();
        List<PhoneNumber> phoneNumbers = new LinkedList<>();

        try {
            XMLEventReader eventReader = factory.createXMLEventReader(streamReader);
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String elementName = startElement.getName().getLocalPart();
                    if ("person".equalsIgnoreCase(elementName)) {
                        Attribute id = startElement.getAttributeByName(QName.valueOf("id"));
                        person.setId(Integer.parseInt(id.getValue()));
                    } else if ("first-name".equalsIgnoreCase(elementName)) {
                        event = eventReader.nextEvent();
                        person.setFirstName(event.asCharacters().getData());
                    } else if ("last-name".equalsIgnoreCase(elementName)) {
                        event = eventReader.nextEvent();
                        person.setLastName(event.asCharacters().getData());
                    } else if ("person-phone".equalsIgnoreCase(elementName)) {
                        event = eventReader.nextEvent();
                        PhoneNumber phoneNumber = new PhoneNumberImpl();
                        phoneNumber.setPhoneNumber(event.asCharacters().getData());
                        phoneNumbers.add(phoneNumber);
                    }
                }
            }
            person.setPhoneNumbers(phoneNumbers);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return person;
    }
}
