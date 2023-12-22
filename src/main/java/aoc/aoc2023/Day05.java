package aoc.aoc2023;

import aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Day05 implements Day {

    Mappings seedToSoilMappings = new Mappings();
    Mappings soilToFertilizerMappings = new Mappings();

    Mappings fertilizerToWaterMappings = new Mappings();
    Mappings waterToLightMappings = new Mappings();
    Mappings lightToTemperatureMappings = new Mappings();

    Mappings temperatureToHumidityMappings = new Mappings();
    Mappings humidityToLocationMappings = new Mappings();

    @Override
    public String part1(List<String> input) {
        List<Long> seedNumbers = readSeeds(input.get(0));
        createAllMappings(input);
        return String.valueOf(seedNumbers.stream().mapToLong(this::getLocation).min().orElse(Long.MAX_VALUE));
    }

    @Override
    public String part2(List<String> input) {
        List<Long> seedNumbers = readSeeds(input.get(0));
        List<Range> seedRanges = readSeedRanges(seedNumbers);
        createAllMappings(input);
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            long seed = getSeed(i);
            for (Range range : seedRanges) {
                if (seed > range.start && seed < range.end) {
                    return String.valueOf(i);
                }
            }
        }
        return "ERROR";
    }

    public List<Range> readSeedRanges(List<Long> seedNumbers) {
        List<Range> rangeList = new ArrayList<>();
        for (int i = 0; i < seedNumbers.size(); i += 2) {
            long start = seedNumbers.get(i);
            long end = start + seedNumbers.get(i + 1);
            rangeList.add(new Range(start, end));
        }
        return rangeList;
    }

    public List<Long> getSeedList(long start, long range) {
        List<Long> seedList = new ArrayList<>();
        long seedNumber = start;
        long endNumber = start + range;
        while (endNumber > seedNumber) {
            seedList.add(seedNumber);
            seedNumber++;
        }
        return seedList;
    }

    public long getSeedEnd(long start, long range) {
        return start + range - 1;
    }

    public List<Long> readSeeds(String input) {
        String[] seeds = input.split(": ");
        return Arrays.stream(seeds[1].split(" ")).map(Long::parseLong).toList();
    }

    public long getLocation(long seedNumber) {
        long soilNumber = seedToSoilMappings.getDestinationFromStart(seedNumber);
        long fertilizerNumber = soilToFertilizerMappings.getDestinationFromStart(soilNumber);
        long waterNumber = fertilizerToWaterMappings.getDestinationFromStart(fertilizerNumber);
        long lightNumber = waterToLightMappings.getDestinationFromStart(waterNumber);
        long temperatureNumber = lightToTemperatureMappings.getDestinationFromStart(lightNumber);
        long humidityNumber = temperatureToHumidityMappings.getDestinationFromStart(temperatureNumber);
        return humidityToLocationMappings.getDestinationFromStart(humidityNumber);
    }

    public long getSeed(long locationNumber) {
        long humidityNumber = humidityToLocationMappings.getSourceFromDestination(locationNumber);
        long temperatureNumber = temperatureToHumidityMappings.getSourceFromDestination(humidityNumber);
        long lightNumber = lightToTemperatureMappings.getSourceFromDestination(temperatureNumber);
        long waterNumber = waterToLightMappings.getSourceFromDestination(lightNumber);
        long fertilizerNumber = fertilizerToWaterMappings.getSourceFromDestination(waterNumber);
        long soilNumber = soilToFertilizerMappings.getSourceFromDestination(fertilizerNumber);
        return seedToSoilMappings.getSourceFromDestination(soilNumber);
    }

    public void createAllMappings(List<String> input) {
        Iterator<String> inputIterator = input.iterator();
        inputIterator.next();
        inputIterator.next();
        while (inputIterator.hasNext()) {
            String line = inputIterator.next();
            switch (readMappingType(line)) {
                case "seed-to-soil": {
                    seedToSoilMappings.setMappingList(generateSectionMappings(inputIterator));
                    break;
                }
                case "soil-to-fertilizer": {
                    soilToFertilizerMappings.setMappingList(generateSectionMappings(inputIterator));
                    break;
                }
                case "fertilizer-to-water": {
                    fertilizerToWaterMappings.setMappingList(generateSectionMappings(inputIterator));
                    break;
                }
                case "water-to-light": {
                    waterToLightMappings.setMappingList(generateSectionMappings(inputIterator));
                    break;
                }
                case "light-to-temperature": {
                    lightToTemperatureMappings.setMappingList(generateSectionMappings(inputIterator));
                    break;
                }
                case "temperature-to-humidity": {
                    temperatureToHumidityMappings.setMappingList(generateSectionMappings(inputIterator));
                    break;
                }
                case "humidity-to-location": {
                    humidityToLocationMappings.setMappingList(generateSectionMappings(inputIterator));
                    break;
                }
            }
        }
    }

    public String readMappingType(String input) {
        String[] mappingLine = input.split(" ");
        return mappingLine[0];
    }

    public Mapping readMapping(String input) {
        List<Long> mappingInfo = Arrays.stream(input.split(" ")).map(Long::valueOf).toList();
        return new Mapping(mappingInfo.get(0), mappingInfo.get(1), mappingInfo.get(mappingInfo.size() - 1));
    }


    public List<Mapping> generateSectionMappings(Iterator<String> inputIterator) {
        List<Mapping> partialMapping = new ArrayList<>();
        while (inputIterator.hasNext()) {
            String newLine = inputIterator.next();

            if (!newLine.isEmpty()) {
                partialMapping.add(readMapping(newLine));
            } else {
                break;
            }
        }
        return partialMapping;
    }
}


class Mappings {
    public void setMappingList(List<Mapping> mappingList) {
        this.mappingList = mappingList;
    }

    List<Mapping> mappingList;

    public Mappings() {
        this.mappingList = new ArrayList<>();
    }

    public long getDestinationFromStart(long start) {
        for (Mapping mapping : mappingList) {
            long value = mapping.getDestinationFromStart(start);
            if (mapping.getDestinationFromStart(start) != -1) {
                return value;
            }
        }
        return start;
    }

    public long getSourceFromDestination(long destination) {
        for (Mapping mapping : mappingList) {
            long value = mapping.getStartFromDestination(destination);
            if (mapping.getStartFromDestination(destination) != -1) {
                return value;
            }
        }
        return destination;
    }

    public int size() {
        return mappingList.size();
    }

}

class Mapping {
    long startPoint;
    long startPointEnd;
    long destinationPoint;
    long destinationPointEnd;

    public Mapping(long destinationPoint, long startPoint, long duration) {
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
        this.startPointEnd = startPoint + duration - 1;
        this.destinationPointEnd = destinationPoint + duration - 1;
    }

    public long getDestinationFromStart(long start) {
        if (start >= this.startPoint && start <= this.startPointEnd) {
            long add = start - this.startPoint;
            return destinationPoint + add;
        }
        return -1;
    }

    public long getStartFromDestination(long destination) {
        if (destination >= this.destinationPoint && destination <= this.destinationPointEnd) {
            long add = destination - this.destinationPoint;
            return this.startPoint + add;
        }
        return -1;
    }
}

class Range {
    long start;
    long end;

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }
}