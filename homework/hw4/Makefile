SOURCE = sensor.erl watcher.erl
OUTPUTTAR = Jake.Israel.cs511.Assignment4.tgz
OUTPUTZIP = Jake.Israel.cs511.Assignment4.zip

all: clean compile run

clean:
	-rm -f *.beam
	-rm -f erl_crash.dump

compile:
	erlc $(SOURCE)

run:

tar:
	tar -cvzf $(OUTPUTTAR) $(SOURCE) Makefile

zip:
	zip $(OUTPUTZIP) $(SOURCE) Makefile
