package com.epam.university.java.core.task055;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessingContextImpl implements ProcessingContext {

    private final List<HouseDefinition> houses;


    public ProcessingContextImpl(String path) {
        if (path == null) {
            throw new IllegalArgumentException();
        }
        houses = new ArrayList<>();

        XMLInputFactory factory = XMLInputFactory.newFactory();

        XMLEventReader eventReader = null;
        HouseDefinitionImpl houseDefinition = null;
        try {
            eventReader = factory.createXMLEventReader(new FileInputStream(getClass().getResource(path).getFile()));
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String elementName = startElement.getName().getLocalPart();
                    if ("passports_houses".equals(elementName)) {
                        houseDefinition = new HouseDefinitionImpl();
                    }
                    if (houseDefinition != null) {
                        if ("addr_district".equals(elementName)) {
                            event = eventReader.nextEvent();
                            String district = event.asCharacters().toString();
                            houseDefinition.setAddress(district);
                        }
                        if ("data_buildingdate".equals(elementName)) {
                            event = eventReader.nextEvent();
                            if (event.isCharacters()) {
                                String yearString = event.asCharacters().toString();
                                Pattern pattern = Pattern.compile("\\d{4}");
                                Matcher matcher = pattern.matcher(yearString);
                                String year = "0";
                                while (matcher.find()) {
                                    year = yearString.substring(matcher.start(), matcher.end());
                                }
                                houseDefinition.setYear(Integer.parseInt(year));
                            } else {
                                houseDefinition.setYear(0);
                            }
                        }
                        if ("data_buildingarea".equals(elementName)) {
                            event = eventReader.nextEvent();
                            if (event.isCharacters()) {
                                String area = event.asCharacters().toString();
                                houseDefinition.setArea(Double.parseDouble(area));
                            } else {
                                houseDefinition.setArea(0);
                            }
                        }
                        if ("comm_type".equals(elementName)) {
                            event = eventReader.nextEvent();
                            if (event.isCharacters()) {
                                houseDefinition.setCommunal(true);
                            }
                        }
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if ("passports_houses".equals(endElement.getName().getLocalPart())) {
                        houses.add(houseDefinition);
                        houseDefinition = null;
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }

        if (eventReader != null) {
            try {
                eventReader.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Collection<HouseDefinition> oldestHouses() {

        List<HouseDefinition> oldestHouses = new ArrayList<>();
        double year = Double.POSITIVE_INFINITY;
        for (HouseDefinition house : houses) {
            if (house.getYear() < year
                    && house.getYear() != 0) {
                year = house.getYear();
                oldestHouses = new ArrayList<>();
            }
            if (house.getYear() == year) {
                oldestHouses.add(house);
            }
        }
        return oldestHouses;
    }

    @Override
    public int averageAge(String district) {
        int sum = 0;
        int counter = 0;

        if ("Город".equals(district)) {
            for (HouseDefinition house : houses) {
                if (house.getYear() != 0) {
                    sum += 2020 - house.getYear();
                    counter++;
                }
            }
        } else {
            for (HouseDefinition house : houses) {
                if (house.getAddress().equals(district)
                        && house.getYear() != 0) {
                    sum += 2020 - house.getYear();
                    counter++;
                }
            }
        }
        if (counter != 0) {
            return sum / counter;
        } else {
            return sum;
        }
    }

    @Override
    public HouseDefinition biggestTotalFloorSpace() {
        HouseDefinition theBiggestHouse = new HouseDefinitionImpl();
        theBiggestHouse.setArea(0);
        for (HouseDefinition house : houses) {
            if (house.getArea() > theBiggestHouse.getArea()) {
                theBiggestHouse = house;
            }
        }
        return theBiggestHouse;
    }

    @Override
    public HouseDefinition smallestTotalFloorSpace() {
        HouseDefinition theSmallestHouse = new HouseDefinitionImpl();
        theSmallestHouse.setArea(Double.POSITIVE_INFINITY);
        for (HouseDefinition house : houses) {
            if (house.getArea() < theSmallestHouse.getArea() &&
                    house.getArea() != 0) {
                theSmallestHouse = house;
            }
        }
        return theSmallestHouse;
    }

    @Override
    public int totalCountHouses() {
        return houses.size();
    }

    @Override
    public int totalCountHousesWithCommunalFlats() {
        int count = 0;
        for (HouseDefinition house : houses) {
            HouseDefinitionImpl houseImpl = (HouseDefinitionImpl) house;
            if (houseImpl.isCommunal()) {
                count++;
            }
        }
        return count;
    }
}
