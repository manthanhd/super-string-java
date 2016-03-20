# Super String for Java
Library for performing blazing fast string operations in Java.

## Overview
Super string works in two parts.

1. Analysis
2. Operation

### Analysis
When you create a super string object, analysis starts. Analysis will vary depending on number of operations super string supports. In case of replace, analysis part identifies all the tokens within the string that need replacing. If some analysis takes too long to run, it might do it lazily.

### Operation
This is the part where you actually execute a method on an existing super string object. Because all the analysis has already been done on a string, this part is usually very quick. If you wanted super string to replace all labels in a string, this is the part where you do
```java
(new SuperString("Hello, :name:!")).replace(replaceValues);
```

## Example usage
### Create a super string object.
Make this a field level, static object if you are going to execute replace on it more than once.
```java
final SuperString superString = new SuperString("one two three :afterThree: five :afterFiveIs:");
```

### Create a hashmap of replace values.
```java
final Map<String, String> superStringReplaceValues = new HashMap<>();
superStringReplaceValues.put("afterThree", "four");
superStringReplaceValues.put("afterFiveIs", "six");
```

### Replace away!
```java
final String superStringReplacedString = superString.replace(superStringReplaceValues);
```
