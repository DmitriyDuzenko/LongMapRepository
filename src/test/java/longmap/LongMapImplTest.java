package longmap;

import de.comparus.opensource.longmap.LongMap;
import de.comparus.opensource.longmap.LongMapImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongMapImplTest {
    private LongMap<String> map;

    @Before
    public void setUp() {
        // given
        map = new LongMapImpl<>();
    }

    @Test
    public void putSingleValueTest() {
        //when
        String put = map.put(1, "one");
        //then
        assertNull(put); // put returns previous value, or null if none
        assertEquals("one", map.get(1));
        assertEquals(1, map.size());
    }

    @Test
    public void putDifferentValuesWithSameKeyTest() {
        //when
        map.put(1, "one");
        String putAnotherOne = map.put(1, "two");
        //then
        assertEquals("one", putAnotherOne); // put returns previous value, or null if none
        assertEquals("two", map.get(1));
        assertEquals(1, map.size());
    }

    @Test
    public void putValuesTest() {
        //when
        map.put(1, "one");
        map.put(2, "two");
        //then
        assertEquals("one", map.get(1));
        assertEquals("two", map.get(2));
        assertEquals(2, map.size());
    }

    @Test
    public void putValuesWithSameProducedIndexTest() {
        //when
        map.put(1, "one");
        map.put(17, "seventeen");
        //then
        assertEquals("one", map.get(1));
        assertEquals("seventeen", map.get(17));
        assertEquals(2, map.size());
    }

    @Test
    public void putNegativeLongKeyTest(){
        // when
        map.put(-1, "negativeOne");
        //then
        assertEquals("negativeOne", map.get(-1));
    }

    @Test
    public void putZeroLongKeyTest(){
        // when
        map.put(0, "zero");
        //then
        assertEquals("zero", map.get(0));
    }

    @Test
    public void getTest() {
        //when
        map.put(11, "eleven");
        //then
        assertEquals("eleven", map.get(11));
        assertEquals(1, map.size());
    }

    @Test
    public void removeTest() {
        //when
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        String removed = map.remove(2);
        //then
        assertEquals("two", removed);
        assertNull(map.get(2));
        assertEquals(2, map.size());
    }

    @Test
    public void removeAndAddTest() {
        //when
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        String removed = map.remove(2);
        map.put(4, "four");
        //then
        assertEquals("two", removed);
        assertEquals(3, map.size());
    }

    @Test
    public void isEmptyTest() {

        assertTrue(map.isEmpty());

        map.put(1, "one");

        assertFalse(map.isEmpty());
    }

    @Test
    public void isEmptyTestAfterRemove() {

        assertTrue(map.isEmpty());

        map.put(1, "one");
        map.remove(1);

        assertTrue(map.isEmpty());
    }

    @Test
    public void containsExistingKeyTest() {
        // when
        map.put(1, "one");
        // then
        assertTrue(map.containsKey(1));
    }

    @Test
    public void containsNonExistingKeyTest() {
        // when
        map.put(1, "one");
        // then
        assertFalse(map.containsKey(2));
    }

    @Test
    public void containsExistingValueTest() {
        // when
        map.put(1, "one");
        // then
        assertTrue(map.containsValue("one"));
    }

    @Test
    public void containsNonExistingValueTest() {
        // when
        map.put(1, "one");
        // then
        assertFalse(map.containsValue("two"));
    }

    @Test
    public void keysTest() {
        // when
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        // then
        long[] keys = map.keys();
        assertEquals(3, keys.length);
        assertEquals(1, keys[0]);
        assertEquals(2, keys[1]);
        assertEquals(3, keys[2]);
    }

    @Test
    public void valuesTest() {
        // when
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        // then
        Object[] values = map.values();
        assertEquals(3, values.length);
        assertEquals("one", values[0]);
        assertEquals("two", values[1]);
        assertEquals("three", values[2]);
    }

    @Test
    public void sizeTest() {
        // when
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        // then
        assertEquals(3, map.size());
    }

    @Test
    public void sizeAfterRemoveTest() {
        // when
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.remove(2);
        // then
        assertEquals(2, map.size());
    }

    @Test
    public void clearTest() {
        // when
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.clear();
        // then
        assertEquals(0, map.size());
        assertNull(map.get(1));
        assertNull(map.get(2));
        assertNull(map.get(3));
    }
}