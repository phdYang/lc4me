package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 有些easy类型题 并不太需要归纳专题，故放在此处
 */
public class MainCode {

    // paper title
    // map: p->t a->i e->l r->e
    // map: t->p i->a l->e e->r
    // foo bar
    // map: f-> o->r
    // map
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) return false;
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }
}
