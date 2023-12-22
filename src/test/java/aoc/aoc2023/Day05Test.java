package aoc.aoc2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class Day05Test {
    Day05 day05;
    String text = """
            seeds: 79 14 55 13
                        
            seed-to-soil map:
            50 98 2
            52 50 48
                        
            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15
                        
            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4
                        
            water-to-light map:
            88 18 7
            18 25 70
                        
            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13
                        
            temperature-to-humidity map:
            0 69 1
            1 0 69
                        
            humidity-to-location map:
            60 56 37
            56 93 4
            """;

    List<String> lines;

    @BeforeEach
    public void setUp() {
        lines = text.lines().toList();
        day05 = new Day05();
    }

    @Test
    void readSeedsTests() {
        List<Long> results = new ArrayList<>();

        assertIterableEquals(List.of(79L, 14L, 55L, 13L), day05.readSeeds(lines.get(0)));
    }

    @Test
    void readMappingTypeTests() {
        assertEquals("seed-to-soil", day05.readMappingType(lines.get(2)));
    }

    @Test
    void readMappingTests() {
        Mapping line3Mapping = day05.readMapping(lines.get(3));
        assertEquals(98, line3Mapping.startPoint);
        assertEquals(99, line3Mapping.startPointEnd);
        assertEquals(50, line3Mapping.destinationPoint);
        assertEquals(51, line3Mapping.destinationPointEnd);

    }

    @Test
    void getMappedValue() {
        Mapping line3Mapping = day05.readMapping(lines.get(3));
        assertEquals(50, line3Mapping.getDestinationFromStart(98));
        assertEquals(51, line3Mapping.getDestinationFromStart(99));
        assertEquals(-1, line3Mapping.getDestinationFromStart(52));

    }

    @Test
    void createSectionMappings() {
        Iterator<String> linesIterator = lines.iterator();
        linesIterator.next();
        linesIterator.next();
        linesIterator.next();

        assertEquals(2, day05.generateSectionMappings(linesIterator).size());
    }

    @Test
    void createAllMappings() {
        day05.createAllMappings(lines);
        assertEquals(2, day05.seedToSoilMappings.size());
        assertEquals(3, day05.soilToFertilizerMappings.size());
        assertEquals(4, day05.fertilizerToWaterMappings.size());
        assertEquals(2, day05.waterToLightMappings.size());
        assertEquals(3, day05.lightToTemperatureMappings.size());
        assertEquals(2, day05.temperatureToHumidityMappings.size());
        assertEquals(2, day05.humidityToLocationMappings.size());

    }

    @Test
    void getLocationFromSeed() {
        day05.createAllMappings(lines);
        assertEquals(82, day05.getLocation(79));
        assertEquals(43, day05.getLocation(14));
        assertEquals(86, day05.getLocation(55));
        assertEquals(35, day05.getLocation(13));

    }


    @Test
    void getInitialSeedList() {
        List<Long> seedList = day05.getSeedList(79, 14);
        assertEquals(List.of(79L, 80L, 81L, 82L, 83L, 84L, 85L, 86L, 87L, 88L, 89L, 90L, 91L, 92L), seedList);
    }

    @Test
    void getSeedEnd() {
        assertEquals(92L, day05.getSeedEnd(79, 14));
    }

    @Test
    void optimisingRange() {
        Mappings mappings = new Mappings();
        mappings.mappingList.add(new Mapping(100, 1, 50));
        mappings.mappingList.add(new Mapping(150, 25, 50));
        OptimisedMappingResult result = mappings.optimiseMapping(mappings.mappingList.get(0), mappings.mappingList.get(1));
        assertEquals(25L, result.optimised);
    }

//    @Test
//    void optimisingSeeds() {
//        OptimisedSeedRangeResult optimisedSeedRangeResult = day05.readSeedRanges(List.of(0L, 10L, 5L, 10L));
//        List<Range> ranges = optimisedSeedRangeResult.ranges;
//        assertEquals(0, ranges.get(0).start);
//        assertEquals(14, ranges.get(0).end);
//        assertEquals(5, optimisedSeedRangeResult.optimised);
//    }

    @Test
    void part1() {
        assertEquals("35", day05.part1(lines));
    }

    @Test
    void part2() {
        assertEquals("46", day05.part2(lines));
    }
}