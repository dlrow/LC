package disjointSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DisJointSet<K> {
    Map<K, Element> dataToElementMap = new HashMap<>();

    class Element {
        K data;
        int rank;
        Element parent;

    }

    public void makeSet(List<K> elements) {
        for (int i = 0; i < elements.size(); i++) {
            Element a = new Element();
            a.data = elements.get(i);
            a.parent = a;
            a.rank = -1;
            dataToElementMap.put(a.data, a);
        }
    }

    public Element findSet(K da) {
        Element ele = dataToElementMap.get(da);
        if (ele == null)
            return null;
        if (ele.parent == ele)
            return ele;
        else {
            ele.parent = findSet(ele.parent.data);
            return ele.parent;
        }
    }

    public void union(K da1, K da2) {
        Element set1 = findSet(da1);
        Element set2 = findSet(da2);
        if (set1 == set2) {
            return;
        }
        if (set1.rank < set2.rank) {
            set2.parent = set1.parent;
        } else if (set1.rank > set2.rank) {
            set1.parent = set2.parent;
        } else {
            set1.rank += 1;
            set1.parent = set2.parent;
        }
    }
}