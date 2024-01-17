## Introduction

This Java project implements an algorithm to determine if a target string can be constructed from elements in a provided word bank. It's suitable for applications like word games or text processing.

## Algorithm

### canConstruct Algorithm

#### Description

- `canConstruct` checks if the target string can be constructed from the word bank elements. It uses recursion and memoization for efficiency.

#### Complexity

- **Time Complexity:** O(n*m^2) where 'n' is the word bank length and 'm' is the target length.
- **Space Complexity:** O(m^2) due to memoization and substring storage.

### Implementation

```java
import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // [Test cases]
    }

    static Boolean canConstruct(String target, String[] wordBank, HashMap<String, Boolean> memo) {
        // Encountering an empty string means we've successfully constructed the word
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target.isEmpty()) {
            return true;
        }

        for (String element : wordBank) {
            // If the wordBank contains a substring prefix, then remove that prefix from element and call recursively
            if (target.startsWith(element)) {
                String suffix = target.replaceFirst(element, "").trim();
                if (canConstruct(suffix, wordBank, memo)) {
                    memo.put(target, true);
                    return true;
                }
            }
        }
        memo.put(target, false);
        return false;
    }
}
```
