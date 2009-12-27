BUILDDIR=`pwd`/../build
all: brainstorm icons

brainstorm: bencode messages 
	javac -d $(BUILDDIR) brainstorm/*.java -Xlint:deprecation

messages: brainstorm/messages/*.java
	#javac -d $(BUILDDIR) brainstorm/messages/*.java

bencode: bencode/*.java
	javac -d $(BUILDDIR) bencode/*.java

icons: brainstorm/icons/*.png
	mkdir -p $(BUILDDIR)/brainstorm/icons
	cp brainstorm/icons/*.png $(BUILDDIR)/brainstorm/icons/ 

test: test-bencode

test-bencode:
	javac -cp $(BUILDDIR) ../tests/TestB.java
