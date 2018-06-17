# Finding shortest path on a virtual keyboard to spell out a word

Given a JSON representation of the keyboard with letters, length of each row, starting letter and the word to be spelled out. From the starting key, find the shortest path to spell out the given word by moving left, right, up or down (diagonal not allowed). And if you move past the edge of a keyboard, you wrap around from the other side.

## Prerequisites

To run the code, you would either need to have ANT installed, or Eclipse.

## Running the code

### Using ANT

An ANT build.xml file is included, to run the code without needing an IDE. Navigate to root folder ‘VirtualKeyboard’ and type ‘ant’. This would do a clean build and run the code with the default input file under ‘samples\input1.json’.

```
git\VirtualKeyboard> ant
```

Sample output
```
Buildfile: C:\Users\surab\git\VirtualKeyboard\build.xml

clean:
   [delete] Deleting directory C:\Users\surab\git\VirtualKeyboard\build

compile:
    [mkdir] Created dir: C:\Users\surab\git\VirtualKeyboard\build\classes
    [javac] Compiling 5 source files to C:\Users\surab\git\VirtualKeyboard\build\classes

jar:
    [mkdir] Created dir: C:\Users\surab\git\VirtualKeyboard\build\jar
      [jar] Building jar: C:\Users\surab\git\VirtualKeyboard\build\jar\VirtualKeyboard.jar

run:
     [java] Input JSON file: samples\input1.json
     [java] {"path":["p","r","r","d","p","d","l","l","p"],"rowLength":5,"distance":6,"alphabet":["Q","W","E","R","T","Y","U","I","B","P","A","S"],"startingFocus":"B","word":"BAR"}
     [java] {"path":["l","p","r","u","p","l","d","p","d","p"],"rowLength":3,"distance":6,"alphabet":["R","T","Y","A","S","D","E","U","I","O","L"],"startingFocus":"Y","word":"TILT"}

main:

BUILD SUCCESSFUL
Total time: 0 seconds
```

To run with a specific input file: ant -Dfile= 'path to the input file'

```
git\VirtualKeyboard> ant -Dfile=samples\input2.json
```

### Running Unittests

```
git\VirtualKeyboard> ant junit
git\VirtualKeyboard> ant junitreport
```

## Using Eclipse
Alternatively, if you have Eclipse, you can run import the project in Eclipse and run the ‘VirtualKeyboard’ class. To run the unittests, right click ‘KeyboardGraphTests.java’ -> Run as -> JUnit Test

## Authors

* **Abhishek Prateek**

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.
