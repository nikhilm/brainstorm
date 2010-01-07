BUILDDIR=`pwd`/../build

all: brainstorm bencode icons

brainstorm/Brainstorm.java: brainstorm/*.java
	javac -d $(BUILDDIR) $(SRCS) -Xlint:deprecation

bencode: bencode/*.java
	javac -d $(BUILDDIR) bencode/*.java

icons: brainstorm/icons/*.png
	mkdir -p $(BUILDDIR)/brainstorm/icons
	cp brainstorm/icons/*.png $(BUILDDIR)/brainstorm/icons/ 

test: test-bencode

test-bencode:
	javac -cp $(BUILDDIR) ../tests/TestB.java
