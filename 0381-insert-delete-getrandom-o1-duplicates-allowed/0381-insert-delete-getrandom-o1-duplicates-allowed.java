import java.util.*;

class RandomizedCollection {

    private final List<Integer> list;
    private final Map<Integer, Set<Integer>> map;
    private final Random random;

    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {

        boolean isNew = !map.containsKey(val);

        // Add value to the list
        list.add(val);

        // Add its index to the set
        map.computeIfAbsent(val, k -> new HashSet<>())
           .add(list.size() - 1);

        return isNew;
    }

    public boolean remove(int val) {

        if (!map.containsKey(val) || map.get(val).isEmpty()) {
            return false;
        }

        // Get any index of val
        Set<Integer> indices = map.get(val);
        int removeIndex = indices.iterator().next();

        // Last element in the list
        int lastIndex = list.size() - 1;
        int lastValue = list.get(lastIndex);

        // Remove the selected index from val's set
        indices.remove(removeIndex);

        // If removing something other than the last element,
        // move the last element into the removed position.
        if (removeIndex != lastIndex) {

            list.set(removeIndex, lastValue);

            // Update lastValue's index set
            Set<Integer> lastValueIndices = map.get(lastValue);

            lastValueIndices.remove(lastIndex);
            lastValueIndices.add(removeIndex);
        }

        // Remove last element from the list
        list.remove(lastIndex);

        // Remove empty set from map
        if (indices.isEmpty()) {
            map.remove(val);
        }

        return true;
    }

    public int getRandom() {

        int randomIndex = random.nextInt(list.size());

        return list.get(randomIndex);
    }
}