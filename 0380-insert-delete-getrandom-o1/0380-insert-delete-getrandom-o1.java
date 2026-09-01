import java.util.*;

class RandomizedSet {

    private List<Integer> list;
    private Map<Integer, Integer> map;

    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {

        // Already present
        if (map.containsKey(val)) {
            return false;
        }

        // Add at the end
        list.add(val);

        // Store its index
        map.put(val, list.size() - 1);

        return true;
    }

    public boolean remove(int val) {

        // Not present
        if (!map.containsKey(val)) {
            return false;
        }

        int index = map.get(val);

        // Last element
        int lastIndex = list.size() - 1;
        int lastValue = list.get(lastIndex);

        // Move last element into removed element's position
        list.set(index, lastValue);

        // Update its index
        map.put(lastValue, index);

        // Remove last element
        list.remove(lastIndex);

        // Remove target from map
        map.remove(val);

        return true;
    }

    public int getRandom() {

        int index = (int) (Math.random() * list.size());

        return list.get(index);
    }
}