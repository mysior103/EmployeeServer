package pl.mysior.Logging;

import static pl.mysior.Main.*;

public class KeySearcher {
    public static boolean search(String keyToFind) {
        boolean result = false;
        for (String key : synchronizedAuthKeySet) {
            if (key.equals(keyToFind)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
