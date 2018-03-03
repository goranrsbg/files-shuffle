# Shuffle My Files

This application alters the order of files when sorted by name by adding a number in front of each file name. The primary aim of this application is to shuffle .mp3 files in a folder.

![alt text](https://github.com/goranrsbg/files-shuffle/logo/shufflemyfiles.png "Shuffle My Files)

## Getting started

Click on the green button "Clone or download" -> Download ZIP, open the "dist" folder and then run ShuffleMyFiles.jar.

## Prerequisites

In order to run this application you need to have [Java](https://java.com/en/download/) installed on your PC.

## Usage information

Button for removing leading numbers with dot is now implemented.

```
0123.filename.ext => filename.ext
```

v.0.4.2 Pattern that matches prefix number now correctly detects it.

```
012.12.filename.ext => 12.filename.ext
012..filename.ext => .filename.ext
.filename.ext => .filename.ext
012filename.ext => 012filename.ext
```

---

v.0.4.3 Trim Numbers now display message with number of files changed. Key press added for buttons :

+ Browse...     -> <kbd>B</kbd>
+ Shuffle Files -> <kbd>S</kbd>
+ Trim Numbers  -> <kbd>T</kbd>